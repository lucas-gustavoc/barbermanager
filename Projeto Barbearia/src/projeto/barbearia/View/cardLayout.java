/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author mestr
 */
public class cardLayout {
    public cardLayout() {
        
    }
    
    public cardLayout(String[] titulos, Container container, int largura, int altura) {
        for(int a = 0; a < titulos.length; a++) {
            card cardi = new card(titulos[a], largura, altura);
            container.add(cardi);
        }
    }
    public JLabel addCard(String nome, int largura, int altura) {
        card cardi = new card(nome, largura, altura);
        return cardi;
    }
    
    class card extends JLabel {
        private JPanel painel1;
        private String tituloTexto;
        public card(String nome, int largura, int altura) {
            Dimension dimen = new Dimension(largura, altura);
            this.setPreferredSize(dimen);
            this.setBorder(javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.lightGray, Color.darkGray));
            
            tituloTexto = nome;
            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints bag = new GridBagConstraints();
            this.setLayout(layout);
            
            painel1();
            
            bag.gridx = 0;
            bag.gridy = 0;
            bag.weightx = 1;
            bag.weighty = 0.2;
            bag.fill = GridBagConstraints.HORIZONTAL;
            bag.anchor = GridBagConstraints.NORTH;
            this.add(painel1, bag);
        }
        private void painel1() {
            painel1 = new JPanel(new GridBagLayout());
            GridBagConstraints bag = new GridBagConstraints();
            painel1.setBackground(new Color(54,54,54));
            
            //Cria jlabel
            JLabel titulo = new JLabel(tituloTexto);
            titulo.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 20));
            titulo.setForeground(Color.WHITE);
            
            //Adiciona titulo
            bag.gridx = 0;
            bag.gridy = 0;
            bag.weightx = 1;
            bag.anchor = GridBagConstraints.CENTER;
            painel1.add(titulo, bag);
        }
    }
}
