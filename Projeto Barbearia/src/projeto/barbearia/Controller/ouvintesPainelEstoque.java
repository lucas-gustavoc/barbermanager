package projeto.barbearia.Controller;

import projeto.barbearia.Model.Tipos.TipoCadastro;
import projeto.barbearia.View.CadastroProduto;
import projeto.barbearia.View.Interface;
import projeto.barbearia.View.painelRelatorios;

public class ouvintesPainelEstoque {
    
    public void onClickAddProduto() {
        CadastroProduto cadastroFrame = new CadastroProduto(TipoCadastro.NOVO_CADASTRO, 0);
    }
    
    public void onClickEditarProduto(int produtoid) {
        CadastroProduto cadastroFrame = new CadastroProduto(TipoCadastro.ALTERACAO, produtoid);
    }
    
    public void onClickResgistros() {
        Interface inter = new Interface();
        //inter.removeTabs("Estoque");
        inter.criandoTabs(new painelRelatorios("Estoque"), "Caixa > Relatórios");
    }
}
