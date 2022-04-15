package bmkey;

import bmkey.amosnum.AmosNum;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class Validador {
    
    private String chave;
    private String pc;
    
    public Validador() {
        chave = "";
        pc = "";
    }
    
    /**
     * Método utilizado para ativar o pc no BD central.
     * @param chave chave a ser registrada.
     * @return boolean com o status da operação.
     */
    public boolean registrar(String chave) {
        boolean ret = false;
        this.chave = chave;
        
        pc = WebService.registrar(chave);

        if (!pc.equals("0")) {
            //Se entrar aqui, é porque o registro da chave ocorreu com sucesso
            Conector cn = new Conector();
            AmosNum amos = new AmosNum();
            cn.cadastrarPc(pc);
            cn.cadastrarChave(chave);
            cn.cadastrarATSCode("JB2A"); //ATSCode cadastrando
            amos.gerar();
            ret = true;
            JOptionPane.showMessageDialog(null, "Seu Barber Manager foi ativado com sucesso!", 
                    "Ativação bem-sucedida", JOptionPane.INFORMATION_MESSAGE);
        }
        
        return ret;
    }
    
    /**
     * Método utilizado para inserir as credenciais para validação. Geralmente, 
     * esse método só será chamado caso o método registrar não tenha sido utilizado.
     * @param chave chave a ser validada.
     * @param pc código de pc a ser validado.
     */
    public void definirCredenciais(String chave, String pc) {
        this.chave = chave;
        this.pc = pc;
    }
    
    /**
     * Validar as credenciais fornecidas.
     * @return boolean indicado se as credenciais são válidas ou não.
     */
    public boolean validarLiberacao() {
        boolean ret = false;
        
        ret = WebService.validar(chave, pc);
        
        return ret;
    }
    
}
