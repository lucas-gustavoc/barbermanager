
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
public class TelaConsulta extends JFrame {
    
    /**
     * VARIÁVEIS GLOBAIS
     */
    
    private JTextField txtChave;
    private JButton btnExcluir;
    private JButton btnRemoverPc;
    private TabelaConsulta tabela;
    private validador.controller.TelaConsulta controller = new validador.controller.TelaConsulta();
    
    /**
     * MÉTODOS
     */
    
    public TelaConsulta() {
        carregarInterface();
        setVisible(true);
    }
    
    private void carregarInterface() {
        
        //Definindo propriedades da janela        
        setSize(450,370);
        setTitle("Consultar Chaves");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new GridBagLayout());
        
        //Inserindo elementos
        GridBagConstraints b = new GridBagConstraints();
        b.fill = GridBagConstraints.BOTH;
        b.weightx = 1;
        b.weighty = 0;
        add(obterLinha01(), b);
        
        b.gridy = 1;
        b.weighty = 1;
        add(obterLinha02(), b);
        
    }
    
    private JPanel obterLinha01() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        txtChave = new JTextField();
        btnExcluir = new JButton("Excluir");
        btnRemoverPc = new JButton("Remover PC");
        
        txtChave.setFont(new Font("Dialog", Font.BOLD, 20));
        txtChave.setPreferredSize(new Dimension(170, 50));
        txtChave.setHorizontalAlignment(JTextField.CENTER);
        btnExcluir.setFont(new Font("Dialog", Font.BOLD, 17));
        btnExcluir.setPreferredSize(new Dimension(90, 50));
        btnExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExcluir.setFocusable(false);
        btnExcluir.addActionListener((e) -> controller.onClickExcluir(tabela));
        btnRemoverPc.setFont(new Font("Dialog", Font.BOLD, 17));
        btnRemoverPc.setPreferredSize(new Dimension(140, 50));
        btnRemoverPc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRemoverPc.setFocusable(false);
        btnRemoverPc.addActionListener((e) -> controller.onClickRemoverPc(tabela));
        
        //Inserindo elementos
        b.insets = new Insets(5, 5, 5, 5);
        b.fill = GridBagConstraints.NONE;
        b.anchor = GridBagConstraints.WEST;
        b.weightx = 1;
        p.add(txtChave, b);
        
        b.gridx = 1;
        b.anchor = GridBagConstraints.EAST;
        p.add(btnExcluir, b);
        
        b.gridx = 2;
        p.add(btnRemoverPc, b);
        
        return p;
    }
    
    private JPanel obterLinha02() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        JScrollPane jsp = new JScrollPane();
        tabela = new TabelaConsulta();
        
        tabela.preencherTabela();
        jsp.setViewportView(tabela);
        jsp.setPreferredSize(new Dimension(432,100));
        tabela.getParent().setBackground(Color.white);
        
        //Estamos adicionando este focuslistener aqui pois lá em cima, onde 
        //configuramos essa txtChave, a tabela ainda não tinha sido definida
        txtChave.addFocusListener(controller.onBlurTextFieldChave(tabela));
                
        //Inserindo elementos
        b.insets = new Insets(0, 5, 5, 5);
        b.fill = GridBagConstraints.BOTH;
        b.weightx = 1;
        b.weighty = 1;
        p.add(jsp, b);
        
        return p;
    }
    
}
