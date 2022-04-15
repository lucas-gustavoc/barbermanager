package projeto.barbearia.Controller;

import projeto.barbearia.View.CadastroService;
import projeto.barbearia.View.Interface;
import projeto.barbearia.View.painelRelatorios;
import projeto.barbearia.Model.Tipos.TipoCadastro;

public class ouvintesPainelServices {
    
    public void onClickAddServico() {
        CadastroService service = new CadastroService(TipoCadastro.NOVO_CADASTRO, 0);
    }
    
    public void onClickEditarServico(int servicoid) {
        CadastroService service = new CadastroService(TipoCadastro.ALTERACAO, servicoid);
    }
    
    public void onClickRegistros() {
        Interface inter = new Interface();
        //inter.removeTabs("Profissionais");
        inter.criandoTabs(new painelRelatorios("Serviços"), "Caixa > Relatórios");
    }
}
