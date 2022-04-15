
package validador.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

/**
 *
 * @author lucas
 */
public class TelaInicial extends JFrame {
    
    /**
     * ******************
     * VARIÁVEIS GLOBAIS
     * ******************
     */
    
    private JLabel lbl;
    private JButton btCpy;
    private JButton btnGerar;
    private JButton btnCadastrar;
    private JButton btnConsultar;
    private validador.controller.TelaInicial controller = new validador.controller.TelaInicial();
    
    
    
    /**
     * ******************
     * MÉTODOS
     * ******************
     */
    
    public TelaInicial() {
        construirInterface();
        setVisible(true);
    }
    
    private void construirInterface() {
        
        //Definindo propriedades da janela        
        setSize(370,320);
        setTitle("Barber Manager Keygen");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridBagLayout());
        
        
        
        //Inserindo componentes
        GridBagConstraints b = new GridBagConstraints();
        b.weightx = 1;
        b.weighty = 0;
        b.insets = new Insets(40, 0, 0, 0);
        b.fill = GridBagConstraints.BOTH;
        add(obterLinha01(), b);
        
        b.gridy = 1;
        b.insets = new Insets(10, 0, 0, 0);
        add(obterLinha02(), b);
        
        b.gridy = 2;
        b.insets = new Insets(30, 0, 0, 0);
        add(obterLinha03(), b);
        
        b.gridy = 3;
        b.weighty = 1;
        add(new JPanel(), b);
        
    }

    private JPanel obterLinha01() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        
        lbl = new JLabel("");
        btCpy = new JButton();
        lbl.setFont(new Font("Dialog", Font.BOLD, 27));
        lbl.setOpaque(true);
        lbl.setBackground(Color.white);
        lbl.setPreferredSize(new Dimension(231, 60));
        lbl.setHorizontalAlignment(JLabel.CENTER);
        btCpy.setFont(new Font("Dialog", Font.BOLD, 17));
        btCpy.setPreferredSize(new Dimension(70, 60));
        btCpy.setBackground(Color.GRAY);
        btCpy.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon img = new ImageIcon("assets\\images\\copy.png");
        btCpy.setFocusable(false);
        btCpy.setIcon(img);
        btCpy.setToolTipText("Copiar chave para a área de transferência.");
        btCpy.addActionListener((e) -> controller.onClickCopy(lbl));
        
        b.anchor = GridBagConstraints.CENTER;
        b.fill = GridBagConstraints.NONE;
        p.add(lbl, b);
        
        b.gridx = 1;
        p.add(btCpy, b);
        
        return p;
    }

    private JPanel obterLinha02() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        
        btnGerar = new JButton("Gerar");
        btnCadastrar = new JButton("Cadastrar");
        btnGerar.setPreferredSize(new Dimension(140, 50));
        btnGerar.setFont(new Font("Dialog", Font.BOLD, 17));
        btnGerar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGerar.setFocusable(false);
        btnGerar.setToolTipText("Gerar uma nova chave aleatória.");
        btnGerar.addActionListener((e) -> controller.onClickGerar(lbl));
        btnCadastrar.setPreferredSize(new Dimension(151, 50));
        btnCadastrar.setFont(new Font("Dialog", Font.BOLD, 17));
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrar.setFocusable(false);
        btnCadastrar.setToolTipText("Cadastrar a chave gerada acima.");
        btnCadastrar.addActionListener((e) -> controller.onClickCadastrar(lbl.getText()));
        
        b.insets = new Insets(0, 0, 0, 5);
        b.anchor = GridBagConstraints.EAST;
        b.fill = GridBagConstraints.NONE;
        p.add(btnGerar, b);
        
        b.insets = new Insets(0, 5, 0, 0);
        b.gridx = 1;
        p.add(btnCadastrar, b);
        
        return p;
    }
    
    private JPanel obterLinha03() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        
        btnConsultar = new JButton("Consultar Chaves");
        btnConsultar.setPreferredSize(new Dimension(301, 50));
        btnConsultar.setFont(new Font("Dialog", Font.BOLD, 17));
        btnConsultar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConsultar.setFocusable(false);
        btnConsultar.setToolTipText("Consultar chaves já cadastradas.");
        btnConsultar.addActionListener((e) -> controller.onClickConsultar());
        
        b.anchor = GridBagConstraints.CENTER;
        b.fill = GridBagConstraints.NONE;
        p.add(btnConsultar, b);
        
        return p;
    }
    
    
}
