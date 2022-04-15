
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import projeto.barbearia.Controller.ControladorCadastroNatureza;
import projeto.barbearia.Model.Natureza;
import projeto.barbearia.Model.Tipos.TipoCadastro;

/**
 *
 * @author lucas
 */
public class CadastroNatureza extends JFrame {
    
    private JTextField txfDesc;
    
    private JTextField txfRef;
    
    private JCheckBox chkEntradas;
    
    private JCheckBox chkSaidas;
    
    private int naturezaid;
    
    private TipoCadastro tipo;
    
    private ControladorCadastroNatureza controller;
    
    public CadastroNatureza() {
        super();
        naturezaid = 0;
        controller = new ControladorCadastroNatureza();
        tipo = TipoCadastro.NOVO_CADASTRO;
        construirInterface();
    }
    
    public CadastroNatureza(int id) {
        super();
        naturezaid = id;
        controller = new ControladorCadastroNatureza();
        tipo = TipoCadastro.ALTERACAO;
        construirInterface();
        
        //Adicionando dados atuais
        Natureza c = new Natureza(id);
        txfDesc.setText(c.getDescricao());
        txfRef.setText(c.getRef());
        chkEntradas.setSelected(c.isEntrada());
        chkSaidas.setSelected(c.isSaida());
    }
    
    private void construirInterface() {
        //Configurações gerais da janela
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Cadastrar Natureza");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
        //Configurações de Layout
        setLayout(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        
        //Adicionando linhas
        b.fill = GridBagConstraints.BOTH;
        b.insets = new Insets(0,5,0,5);
        b.weightx = 1;
        add(p1(), b);
        
        b.gridy = 1;
        add(p2(), b);
        
        b.insets = new Insets(10,5,0,5);
        b.gridy = 2;
        add(p3(), b);
    }
    
    private JPanel p1() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b;
        
        //Criando elementos
        JLabel lblDesc = new JLabel("Descrição*:");
        JLabel lblRef = new JLabel("Ref.:");
        txfDesc = new JTextField();
        txfRef = new JTextField();
        
        //Configurações
        lblDesc.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblRef.setFont(new Font("Dialog", Font.PLAIN, 14));
        
        //Adicionando labels
        b = new GridBagConstraints();
        b.weightx = 0.7;
        b.anchor = GridBagConstraints.WEST;
        b.insets = new Insets(5,5,0,5);
        p.add(lblDesc, b);
        b.weightx = 0.3;
        b.gridx = 1;
        p.add(lblRef, b);
        
        //Adicionando textfields
        b = new GridBagConstraints();
        b.weightx = 0.7;
        b.anchor = GridBagConstraints.WEST;
        b.insets = new Insets(5,5,5,5);
        b.fill = GridBagConstraints.HORIZONTAL;
        b.gridy = 1;
        p.add(txfDesc, b);
        b.weightx = 0.3;
        b.gridx = 1;
        p.add(txfRef, b);
        
        return p;
    }
    
    private JPanel p2() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        
        //Criando elementos
        JLabel lblTitulo = new JLabel("Aplica-se a*:");
        chkEntradas = new JCheckBox("Entradas");
        chkSaidas = new JCheckBox("Saídas");
        
        //Configurações
        lblTitulo.setFont(new Font("Dialog", Font.PLAIN, 14));
        
        //Adicionando JLabel
        b = new GridBagConstraints();
        b.insets = new Insets(5,5,5,5);
        b.weightx = 1;
        b.gridwidth = 2;
        b.anchor = GridBagConstraints.WEST;
        p.add(lblTitulo, b);
        
        //Adicionando CheckBoxes
        b = new GridBagConstraints();
        b.gridy = 1;
        b.insets = new Insets(0,20,5,0);
        b.weightx = 1;
        p.add(chkEntradas, b);
        b.insets = new Insets(0,0,5,20);
        b.gridx = 1;
        p.add(chkSaidas, b);
        
        return p;
    }
    
    private JPanel p3() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b;
        
        //Criando elementos
        button_icone btnSalvar = new button_icone("Salvar", new Color(34,139,34), Color.white, 100, 30, 14);
        button_icone btnCancelar = new button_icone("Cancelar", new Color(205,92,92), Color.white, 100, 30, 14);
        
        //Preparando elementos
        JFrame janela = this;
        btnSalvar.addActionListener((e) -> controller.onClickSalvar(tipo, 
                txfDesc.getText(), txfRef.getText(), chkEntradas.isSelected(), 
                chkSaidas.isSelected(), naturezaid, janela));
        btnCancelar.addActionListener((e) -> this.dispose());
        
        //Adicionando elementos
        b = new GridBagConstraints();
        b.insets = new Insets(5,0,5,5);
        p.add(btnSalvar, b);
        b.gridx = 1;
        b.insets = new Insets(5,5,5,0);
        p.add(btnCancelar, b);
        
        return p;
    }
        
}
