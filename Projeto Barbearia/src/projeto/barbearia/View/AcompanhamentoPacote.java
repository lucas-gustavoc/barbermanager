
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import projeto.barbearia.Model.Cliente;

/**
 *
 * @author lucas
 */
public class AcompanhamentoPacote extends JFrame {

    private String nomeCliente;
    private int clienteid;
    
    public AcompanhamentoPacote(String nomeCliente, int clienteid) {
        super();
        this.nomeCliente = nomeCliente;
        this.clienteid = clienteid;
        construirInterface();
    }
    
    private void construirInterface() {
        
        //Configurando janela
        setTitle("Itens de Pacote");
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setVisible(true);
        
        //Adicionando itens
        GridBagConstraints b = new GridBagConstraints();
        b.insets = new Insets(5,5,5,5);
        b.fill = GridBagConstraints.BOTH;
        b.weightx = 1;
        b.weighty = 10;
        b.gridy = 0;
        add(getLinha1(), b);
        
        b.fill = GridBagConstraints.BOTH;
        b.weighty = 90;
        b.gridy = 1;
        add(getLinha2(), b);
        
    }
    
    private JPanel getLinha1() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        JLabel lblCliente = new JLabel();
        
        lblCliente.setText("Cliente: " + this.nomeCliente);
        lblCliente.setFont(new Font("Dialog", Font.BOLD, 14));
        
        b.anchor = GridBagConstraints.WEST;
        b.fill = GridBagConstraints.NONE;
        b.weightx = 1;
        b.weighty = 1;
        p.add(lblCliente, b);
        
        return p;
    }
    
    private JPanel getLinha2() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        JScrollPane sp;
        JTable table;
        
        table = obterTabela();
        sp = new JScrollPane();
        sp.setBackground(Color.WHITE);
        sp.setViewportView(table);
        table.getParent().setBackground(Color.WHITE);
        
        b.fill = GridBagConstraints.BOTH;
        b.weightx = 1;
        b.weighty = 1;
        p.add(sp, b);
        
        return p;
    }
    
    private JTable obterTabela() {
        String[] headers;
        String[][] dados;
        JTable table = new JTable();
        
        headers = new String[]{"Serviço", "Qtd."};
        dados = Cliente.obterListaServicosPacote(this.clienteid);
        
        DefaultTableModel model = new DefaultTableModel(dados, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table.setModel(model);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        
        //Tamanho das colunas
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(211);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        
        //Centralizando quantidade
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        return table;
    }
    
}
