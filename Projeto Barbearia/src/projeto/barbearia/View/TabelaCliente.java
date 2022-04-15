package projeto.barbearia.View;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import projeto.barbearia.Model.ResultSetToolBox;

/**
 *
 * @author lucas
 */
public class TabelaCliente extends JTable {
    
    private static TabelaCliente tabela;
    
    public TabelaCliente() {
        super();
    }
    
    public static void criarTabela() {
        
        String[][] dados2 = buscarClientes("");
        String[] colunas2 = new String[]{"ID", "Nome", "Telefone", "E-mail"};
                
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new TabelaCliente();
        tabela.setModel(model);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setCellSelectionEnabled(false);
        tabela.setRowSelectionAllowed(true);
        
        //Centralizando e mudando tamanho da coluna ID
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabela.getColumnModel().getColumn(0).setMaxWidth(40);
        
    }
    
    public static void atualizarTabela() {
        
        String[][] dados2 = buscarClientes("");
        String[] colunas2 = new String[]{"ID", "Nome", "Telefone", "E-mail"};
                
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela.setModel(model);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setCellSelectionEnabled(false);
        tabela.setRowSelectionAllowed(true);
        
        //Centralizando e mudando tamanho da coluna ID
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabela.getColumnModel().getColumn(0).setMaxWidth(40);
        
    }
    
    public static void buscarClientesTabela(String cond) {
        
        String[][] dados2 = buscarClientes(cond);
        String[] colunas2 = new String[]{"ID", "Nome", "Telefone", "E-mail"};
                
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela.setModel(model);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setCellSelectionEnabled(false);
        tabela.setRowSelectionAllowed(true);
        
        //Centralizando e mudando tamanho da coluna ID
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabela.getColumnModel().getColumn(0).setMaxWidth(40);
    }

    public static TabelaCliente getTabela() {
        return tabela;
    }
    
    private static String[][] buscarClientes(String cond) {
        
        String[][] clientes;
        String condicao;
        ResultSet rs;
        int nLinhas = 0;
        
        if(cond.equals("")) {
            condicao = "ativo_cliente = 1";
        } else {
            condicao = "ativo_cliente = 1 and " + cond;
        }
        
        rs = projeto.barbearia.Model.Cliente.buscar("*", condicao);        
        nLinhas = ResultSetToolBox.countRows(rs);
        clientes = new String[nLinhas][4];
        
        if (nLinhas > 0) {
            
            try {
                rs.beforeFirst();
                int i = 0;
                
                while (rs.next()) {
                    clientes[i][0] = rs.getString("clienteid");
                    clientes[i][1] = rs.getString("nome_cliente");
                    clientes[i][2] = rs.getString("fone_cliente");
                    clientes[i][3] = rs.getString("email_cliente");
                    i++;
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(painelClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return clientes;
        
    }
    
}
