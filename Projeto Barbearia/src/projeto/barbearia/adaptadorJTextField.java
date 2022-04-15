/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

/**
 *
 * @author mestr
 */
public class adaptadorJTextField {
    
    public adaptadorJTextField() {}
    
    public void adaptando(JTextField campo) {
        String textoPadrao = campo.getText();
        campo.setFont(new Font("Dialog", Font.ITALIC, 17));
        campo.setForeground(Color.LIGHT_GRAY);
        
        campo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String texto = campo.getText();
                if(texto.equals(textoPadrao)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                    campo.setFont(new Font("Dialog", Font.PLAIN, 17));
                }
            }
        });
        
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt){
                String texto = campo.getText();
                if(texto.equals("")) {
                    campo.setForeground(Color.LIGHT_GRAY);
                    campo.setFont(new Font("Dialog", Font.ITALIC, 17));
                    campo.setText(textoPadrao);
                }
            }
        });
    }
    
}
