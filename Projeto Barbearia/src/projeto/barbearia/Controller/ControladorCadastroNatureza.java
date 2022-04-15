
package projeto.barbearia.Controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import projeto.barbearia.Model.Natureza;
import projeto.barbearia.Model.Tipos.TipoCadastro;
import projeto.barbearia.View.CacheAtualizacao;

/**
 *
 * @author lucas
 */
public class ControladorCadastroNatureza {
    
    /**
     * Método que manipula o click do botão "Salvar", na GUI do CadastroNatureza.
     * 
     * @param tipo tipo de cadastro, referente ao enum {@code TipoCadastro}
     * @param desc texto com a descrição preenchida pelo usuário
     * @param ref texto com a ref. preenchida pelo usuário
     * @param ckEntrada status de seleção da aplicação da natureza às entradas
     * @param ckSaida status de seleção da aplicação da natureza às saídas
     * @param id id da natureza para fins de edição. Mandar 0 em caso de novo cadastro.
     * @param janela janela de cadastro de natureza, enviada aqui para 
     * ser fechada em caso de finalização do cadastro.
     */
    public void onClickSalvar(TipoCadastro tipo, String desc, String ref, 
            boolean ckEntrada, boolean ckSaida, int id, JFrame janela) {
        
        boolean validacao = validar(desc, ckEntrada, ckSaida);
        
        if (validacao) {
            boolean sucesso;
            Natureza c = new Natureza();
            c.setDescricao(desc.toUpperCase());
            c.setRef(ref.toUpperCase());
            c.setEntrada(ckEntrada);
            c.setSaida(ckSaida);
            
            if (tipo == TipoCadastro.NOVO_CADASTRO) {
                sucesso = c.cadastrar();
            } else {
                sucesso = c.editar(id);
            }
            
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Natureza cadastrada com sucesso!", 
                        "Cadastrado com Sucesso", JOptionPane.INFORMATION_MESSAGE);
                CacheAtualizacao.checarEAtualizar("CadastroNatureza");
                janela.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível cadastrar a natureza. "
                        + "Tente novamente, mais tarde.", "Erro no Cadastro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
    /**
     * Método usado em conjunto com {@code onClickSalvar}, com a finalidade 
     * de validar a tentativa de cadastro.
     * 
     * @param s string referente à descrição
     * @param b1 boolean referente ao status de ativação da referência às entradas
     * @param b2 boolean referente ao status de ativação da referência às saídas
     * @return boolean com o status da validação.
     */
    private boolean validar(String s, boolean b1, boolean b2) {
        boolean valido = true;
        String erro = "";
        
        if (s.equals("")) {
            valido = false;
            erro = "Preencha a DESCRIÇÃO!";
        } else if (!b1 && !b2) {
            valido = false;
            erro = "Selecione ao menos uma aplicação: Entradas ou Saídas.";
        }
        
        if (!valido) JOptionPane.showMessageDialog(null, erro, "Dados Incorretos", JOptionPane.WARNING_MESSAGE);
        
        return valido;
    }
    
}
