/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia.Controller;

import projeto.barbearia.View.CadastroEntrada;
import projeto.barbearia.View.Interface;
import projeto.barbearia.View.painelCaixa;
import projeto.barbearia.Model.Tipos.TipoCadastro;

/**
 *
 * @author mestr
 */
public class ouvintesPainelEntrada {
    public void onClickAdicionarCadastro() {
        CadastroEntrada cadastro = new CadastroEntrada(TipoCadastro.NOVO_CADASTRO, 0);
    }
    
    public void onClickVerDetalhes(int id) {
        CadastroEntrada cadastro = new CadastroEntrada(TipoCadastro.ALTERACAO, id);
    }
    
    public void onClickVoltar() {
        Interface inter = new Interface();
        inter.removeTabs("Caixa > Entrada");
        inter.criandoTabs(new painelCaixa(), "Caixa");
    }
}
