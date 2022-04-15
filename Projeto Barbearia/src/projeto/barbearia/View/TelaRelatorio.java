
package projeto.barbearia.View;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import projeto.barbearia.Controller.ControladorRelatorio;

/**
 *
 * @author lucas
 */
public class TelaRelatorio extends JFrame {
    
    private ControladorRelatorio controller;
    
    //Construtor
    public TelaRelatorio(int TipoRelatorio) {
        controller = new ControladorRelatorio(TipoRelatorio);
        construirInterface();
        setVisible(true);
    }
    
    //Interface geral
    private void construirInterface() {
        
        //Configurando janela
        setSize(500, 600);
        setTitle(controller.getNomeTela());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        /**
         * 
         * LAYOUT
         * 
         * 
         */
        setLayout(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        b.insets = new Insets(0,0,0,0);
        b.weightx = 1;
        b.weighty = 0;
        b.fill = GridBagConstraints.BOTH;
        
        
        /**
         * 
         * INSERIR CAMPOS DO RELATÓRIO
         * 
         */
        add(obterPainelCampos(), b);
        
        
        /**
         * 
         * INSERIR BOTÃO DO RELATÓRIO
         * 
         */
        b.insets = new Insets(0,0,10,0);
        b.gridy = 1;
        add(obterPainelBotao(), b);
        b.insets = new Insets(0,0,0,0);
        
        /**
         * 
         * INSERIR TABELA E SCROLLPANE
         * 
         */
        b.gridy = 2;
        add(obterPainelResultados(), b);
        
        
        /**
         * 
         * INSERIR LABEL DE RESULTADOS
         * 
         */
        b.gridy = 3;
        b.weighty = 1;
        add(obterPainelLabel(), b);
        
    }
    
    
    /**
    * 
    * CAMPOS DO RELATÓRIO
    * 
    */
    private JPanel obterPainelCampos() {
        JPanel p = new JPanel(new GridBagLayout());
        Object[][] campos = controller.obterCampos();
        
        int gridx = 0, gridy = 0;
        boolean primeiraColuna = true;
        GridBagConstraints b = new GridBagConstraints();
        b.weightx = 0;
        b.weighty = 1;
        b.fill = GridBagConstraints.BOTH;
        b.insets = new Insets(10,0,10,10);
        for (Object[] s : campos) {
            p.add(obterSubPainelCampos((String)s[0], (Component)s[1], (String)s[2]), b);
            if (primeiraColuna) {
                gridx = 1;
                primeiraColuna = false;
                //Em caso da primeira linha, coloca insets no top, caso contrário não
                b.insets = (gridy == 0) ? new Insets(10,10,10,0) : new Insets(0,10,10,0);
            } else {
                gridx = 0;
                gridy++;
                primeiraColuna = true;
                b.insets = new Insets(0,0,10,10);
            }
            b.gridx = gridx;
            b.gridy = gridy;
        }
        
        return p;
    }

    private JPanel obterSubPainelCampos(String nome, Component c, String tipo) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel lbl = new JLabel(nome);
        
        if (tipo.equalsIgnoreCase("jcombobox")) {
            ((JComboBox)c).setPreferredSize(new Dimension(108,25));
        } else if (tipo.equalsIgnoreCase("jcheckbox")) {
            ((JCheckBox)c).setMargin(new Insets(0,46,0,46));
        } else {
            ((JTextField)c).setColumns(8);
            ((JTextField)c).setFont(new Font("Dialog", Font.PLAIN, 15));
        }
        
        lbl.setFont(new Font("Dialog", Font.PLAIN, 15));
        
        p.add(lbl);
        p.add(c);
        return p;
    }
    
    
    /**
    * 
    * BOTÃO
    * 
    */
    private JPanel obterPainelBotao() {
        JPanel p = new JPanel (new FlowLayout());
        JButton b = new JButton("Executar Relatório");
        b.addActionListener((e) -> controller.onClickExecutarRelatorio());
        p.add(b);
        return p;
    }
    
    /**
    * 
    * TABELA E SCROLLPANE
    * 
    */
    private JPanel obterPainelResultados() {
        JPanel p = new JPanel(new GridBagLayout());
        JScrollPane js = controller.getJsTabela();
        js.setMinimumSize(new Dimension(300, 300));
        
        GridBagConstraints b = new GridBagConstraints();
        b.weightx = 1;
        b.weighty = 1;
        b.fill = GridBagConstraints.BOTH;
        b.insets = new Insets(0,10,10,10);
        p.add(js, b);
        
        return p;
    }

    
    /**
    * 
    * LABEL DE RESULTADOS
    * 
    */
    private JPanel obterPainelLabel() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        JLabel lbl = controller.getLblResultados();
        
        lbl.setFont(new Font("Dialog", Font.BOLD, 17));
        b.insets = new Insets(0, 10, 0, 10);
        b.weightx = 1;
        b.anchor = GridBagConstraints.WEST;
        b.fill = GridBagConstraints.BOTH;
        p.add(lbl, b);
        
        b.gridy = 1;
        b.weighty = 1;
        p.add(new JPanel(), b);
        
        return p;
    }

}
