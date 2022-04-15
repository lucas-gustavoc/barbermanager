package projeto.barbearia.Controller;

import projeto.barbearia.View.CadastroCliente;
import projeto.barbearia.Model.Tipos.TipoCadastro;

public class ouvintesPainelCliente {
    
    public void onClickAddCliente() {
        CadastroCliente cadastroFrame = new CadastroCliente(TipoCadastro.NOVO_CADASTRO, 0);
    }
    
    public void onClickAtualizarCliente(int clienteID) {
        CadastroCliente cadastroFrame = new CadastroCliente(TipoCadastro.ALTERACAO, clienteID);
    }
    
}
