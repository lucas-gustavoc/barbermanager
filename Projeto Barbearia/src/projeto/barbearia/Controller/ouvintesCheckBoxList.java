/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia.Controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import projeto.barbearia.View.painelAgenda;

/**
 *
 * @author mestr
 */
public class ouvintesCheckBoxList implements ItemListener{
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBox checkbox = (JCheckBox)e.getSource();
        String titulo = checkbox.getText();
        System.out.print(titulo + " ");
        System.out.println(e.getStateChange() == ItemEvent.SELECTED ? "SELECTED" : "DESELECTED");
        if(e.getStateChange() != ItemEvent.SELECTED && !titulo.equals("Horario")) {
            //painelAgenda.tabelaAgenda.deletarColuna(titulo);
        }
        else if (e.getStateChange() == ItemEvent.SELECTED && !titulo.equals("Horario")) {
            //painelAgenda.tabelaAgenda.adicionarColuna(titulo);
        }
    }
}
