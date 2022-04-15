/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia.Controller;

import projeto.barbearia.View.Interface;
import projeto.barbearia.View.painelContasAPagar;
import projeto.barbearia.View.painelEntrada;
import projeto.barbearia.View.painelRelatorios;

/**
 *
 * @author mestr
 */
public class ouvintePainelCaixa {
    public void onClickEntrada() {
        Interface inter = new Interface();
        inter.removeTabs("Caixa");
        inter.criandoTabs(new painelEntrada(), "Caixa > Entrada");
    }
    
    public void onClickContasAPagar() {
        Interface inter = new Interface();
        inter.removeTabs("Caixa");
        inter.criandoTabs(new painelContasAPagar(), "Caixa > Contas a Pagar");
    }
    
    public void onClickRelatorios() {
        Interface inter = new Interface();
        inter.removeTabs("Caixa");
        inter.criandoTabs(new painelRelatorios(), "Caixa > Relatórios");
    }
}
