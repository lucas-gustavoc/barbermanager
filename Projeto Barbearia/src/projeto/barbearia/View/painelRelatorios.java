/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import projeto.barbearia.Controller.ouvintePainelRelatorios;

/**
 *
 * @author mestr
 */
public class painelRelatorios extends JPanel{
    private JPanel painel1;
    private JPanel painel2;
    private JPanel painelAlterar;
    private JPanel painelAlterado;
    private combo combox;
    private JLabel textoTipo;
    private GridBagConstraints bag1;
    
    public painelRelatorios() {
        preparaTudo();
    }
    
    public painelRelatorios(String texto) {
        System.out.println(texto);
        preparaTudo();
        mudaCombox(texto);
        //textoTipo.setText(combox.getSelectedItem().toString());
        textoTipo.setText(texto);
        repaint();
        validate();
    }
    
    public void preparaTudo() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints bag = new GridBagConstraints();
        setLayout(layout);
        
        painelAlterar();
        painelAlterado();
        painel1();
        painel2();
        
        bag.fill = GridBagConstraints.BOTH;
        bag.gridy = 0;
        bag.gridx = 0;
        bag.weighty = 0.1;
        bag.weightx = 1;
        add(painel1, bag);
        
        bag.weighty = 1;
        bag.gridy = 1;
        add(painel2, bag);
    }
    
    private void painel1() {
        painel1 = new JPanel(new GridBagLayout());
        bag1 = new GridBagConstraints();
        painel1.setBackground(new Color(207,207,207));
        painel1.setBorder(javax.swing.BorderFactory.createRaisedSoftBevelBorder());
        //Cria botão voltar
        button_icone voltar = new button_icone("C:\\Users\\mestr\\OneDrive\\Documents\\Mateus\\Programação\\Projetos NetBeans\\Imagens\\voltar16.png", 20,10);
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ouvintePainelRelatorios().onClickVoltar();
            }
        });
        //Cria Jlabel
        JLabel textoTopo = new JLabel("Você está vendo os relatórios disponíveis para: ");
        textoTopo.setFont(new Font("Dialog", Font.PLAIN, 20));
        //Adiciona componentes
        bag1.insets = new Insets(0,5,0,0);
        bag1.gridx = 0;
        bag1.gridy = 1;
        bag1.weightx = 1;
        bag1.anchor = GridBagConstraints.NORTHWEST;
        painel1.add(voltar, bag1);
        
        bag1.anchor = GridBagConstraints.WEST;
        bag1.gridy = 1;
        bag1.insets = new Insets(0,0,0,0);
        bag1.weightx = 0;
        bag1.anchor = GridBagConstraints.CENTER;
        bag1.gridx = 1;
        painel1.add(textoTopo, bag1);
        
        bag1.anchor = GridBagConstraints.WEST;
        bag1.weightx = 1;
        bag1.gridx = 2;
        painel1.add(painelAlterar, bag1);
    }
    
    private void painelAlterar() {
        painelAlterar = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        painelAlterar.setBackground(new Color(207,207,207));
        
        //Cria JLabel
        textoTipo = new JLabel("Profissionais");
        textoTipo.setFont(new Font("Dialog", Font.ITALIC, 20));
        
        //Cria botão
        button_icone alterar = new button_icone("C:\\Users\\mestr\\OneDrive\\Documents\\Mateus\\Programação\\Projetos NetBeans\\Imagens\\alterar24.png","Alterar", 150,40);
        alterar.setFont(new Font("Dialog", Font.PLAIN, 20));
        alterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bag1.gridx = 2;
                painel1.remove(painelAlterar);
                painel1.add(painelAlterado, bag1);
                repaint();
                validate();
            }
        });
        //Adiciona componentes
        bag.gridx = 0;
        bag.insets = new Insets(0,0,0,10);
        bag.gridy = 0;
        painelAlterar.add(textoTipo,bag);
        
        bag.gridx = 1;
        painelAlterar.add(alterar, bag);
    }
    
    private void painelAlterado() {
        painelAlterado = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        painelAlterado.setBackground(new Color(207,207,207));
        //Cria combo
        String[] campos = {"Profissionais", "Clientes", "Serviços", "Entradas", "Contas", "Estoque"};
        combox = new combo(campos);
        combox.setPreferredSize(new Dimension(130, 40));
        combox.setFont(new Font("Dialog", Font.ITALIC, 18));
        //Cria buttons
        String path = "C:\\Users\\mestr\\OneDrive\\Documents\\Mateus\\Programação\\Projetos NetBeans\\Imagens\\pronto24.png";
        String path2 = "C:\\Users\\mestr\\OneDrive\\Documents\\Mateus\\Programação\\Projetos NetBeans\\Imagens\\remover24.png";
        button_icone pronto = new button_icone(path, 30,30);
        pronto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bag1.gridx = 2;
                painel1.remove(painelAlterado);
                textoTipo.setText(combox.getSelectedItem().toString());
                painel1.add(painelAlterar, bag1);
                repaint();
                validate();
            }
        });
        button_icone cancelar = new button_icone(path2, 30,30);
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bag1.gridx = 2;
                painel1.remove(painelAlterado);
                painel1.add(painelAlterar, bag1);
                String texto = textoTipo.getText();
                mudaCombox(texto);
                repaint();
                validate();
            }
        });
        //Adiciona componentes
        bag.insets = new Insets(0,0,0,5);
        bag.gridx = 0;
        bag.gridy = 0;
        painelAlterado.add(combox, bag);
        
        bag.gridx = 1;
        painelAlterado.add(pronto, bag);
        
        bag.gridx = 2;
        painelAlterado.add(cancelar, bag);
    }
    
    private void painel2() {
        String[] titulos = {"Card 1", "Card 2", "Card 3", "Card 4", "Card 5"};
        painel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cardLayout cardlayout = new cardLayout();
        for(int a = 0; a < titulos.length; a++) {
            JLabel card = cardlayout.addCard(titulos[a], 150, 200);
            painel2.add(card);
        }
    }
    
    private void mudaCombox(String item) {
        combox.setSelectedItem(item);
    }
}
