package projeto.barbearia.Controller;

import projeto.barbearia.View.CadastroProfissional;
import projeto.barbearia.View.Interface;
import projeto.barbearia.View.painelRelatorios;
import projeto.barbearia.Model.Tipos.TipoCadastro;

public class ouvintesPainelProfissional {
    public void onClickAddProfissional() {
        CadastroProfissional cadastroFrame = new CadastroProfissional(TipoCadastro.NOVO_CADASTRO, 0);
    }
    
    public void onClickEditarProfissional(int profissionalid) {
        CadastroProfissional cadastroFrame = new CadastroProfissional(TipoCadastro.ALTERACAO, profissionalid);
    }
    
    public void onClickRegistros() {
        Interface inter = new Interface();
        //inter.removeTabs("Profissionais");
        inter.criandoTabs(new painelRelatorios("Profissionais"), "Caixa > Relatórios");
    }
}
