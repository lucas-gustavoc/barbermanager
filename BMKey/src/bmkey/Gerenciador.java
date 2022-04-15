package bmkey;

import bmkey.amosnum.AmosNum;

/**
 *
 * @author lucas
 */
public class Gerenciador {

    /**
     * Este é o processo geral de validação da chave. 
     * @return retorna uma string, com valor "valid" para quando a chave for 
     * validada com sucesso e "block" para quando houver erro na validação 
     * ou nos dados.
     */
    public String validar() {
        String ret = "block"; //block, valid
        String chave, pc, msg = "";
        Conector c = new Conector();
        Validador v = new Validador();
        boolean chaveOk, sistemaLiberado = false;
        
        //Obtendo a chave e o código do pc atuais
        chave = c.obterChave();
        pc = c.obterPc();
        
        if (chave.equals("")) {
            //Entra aqui caso ainda não tenha chave cadastrada
            Solicitador s = new Solicitador();
            chave = s.solicitarChave();
            chaveOk = (!chave.equals("")) ? v.registrar(chave) : false;
        } else {
            //Entra aqui caso já exista chave e pc cadastrados, restando apenas validar
            v.definirCredenciais(chave, pc);
            chaveOk = true;
        }
        
        if (chaveOk) {
            /**
             * Entra aqui quando já sabemos que a chave é válida. Basta agora 
             * confirmarmos se este pc ainda tem acesso ao sistema por meio dela e, 
             * claro, confirmar se o PC é de fato o PC do proprietário, por meio do Amos
             */
            AmosNum amos = new AmosNum();
            if (amos.validar()) sistemaLiberado = v.validarLiberacao();
        }
        
        if (!sistemaLiberado) {
            /**
             * Caso a validação tenha falhado, entraremos no período de testes.
             * O objeto PeriodoDeTeste é utilizado para gerenciar o prazo do 
             * teste e outros detalhes que possam ser úteis.
             */
            PeriodoDeTeste pt = new PeriodoDeTeste();
            sistemaLiberado = pt.verificar();
        }
        
        if (sistemaLiberado) {
            /**
             * Entra aqui em caso de liberação do sistema, que pode ser por meio
             * da validação da chave ou do período de teste.
             */
            
            ret = "valid";
        }
        
        return ret;
    }
    
}
