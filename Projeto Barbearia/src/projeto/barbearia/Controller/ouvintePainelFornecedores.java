
package projeto.barbearia.Controller;

import projeto.barbearia.Model.Tipos.TipoCadastro;
import projeto.barbearia.View.CadastroFornecedor;

/**
 *
 * @author lucas
 */
public class ouvintePainelFornecedores {
    
    public void onClickAddFornecedor() {
        CadastroFornecedor cadastroFrame = new CadastroFornecedor(TipoCadastro.NOVO_CADASTRO, 0);
    }
    
    public void onClickEditarFornecedor(int fornecedorid) {
        CadastroFornecedor cadastroFrame = new CadastroFornecedor(TipoCadastro.ALTERACAO, fornecedorid);
    }
    
}
