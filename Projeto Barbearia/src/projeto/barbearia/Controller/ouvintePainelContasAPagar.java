
package projeto.barbearia.Controller;

import projeto.barbearia.Model.Tipos.TipoCadastro;
import projeto.barbearia.View.CadastroSaida;
import projeto.barbearia.View.Interface;
import projeto.barbearia.View.painelCaixa;

/**
 *
 * @author mestr
 */
public class ouvintePainelContasAPagar {
    
    public void onClickVoltar() {
        Interface inter = new Interface();
        inter.removeTabs("Caixa > Contas a Pagar");
        inter.criandoTabs(new painelCaixa(), "Caixa");
    }
    
    public void onClickAdicionar() {
        new CadastroSaida(TipoCadastro.NOVO_CADASTRO, 0);
    }
    
    public void onClickVerDetalhes(int id) {
        new CadastroSaida(TipoCadastro.ALTERACAO, id);
    }
    
}
