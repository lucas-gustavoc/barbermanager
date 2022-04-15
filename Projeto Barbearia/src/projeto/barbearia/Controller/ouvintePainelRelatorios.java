/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia.Controller;

import projeto.barbearia.View.Interface;
import projeto.barbearia.View.painelCaixa;

/**
 *
 * @author mestr
 */
public class ouvintePainelRelatorios {
    public void onClickVoltar() {
        Interface inter = new Interface();
        inter.removeTabs("Caixa > Relatórios");
        inter.criandoTabs(new painelCaixa(), "Caixa");
    }
}
