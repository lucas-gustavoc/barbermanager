
package projeto.barbearia.View;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import projeto.barbearia.Model.ResultSetToolBox;
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

/**
 *
 * @author lucas
 */
public class TabelaFornecedor extends JTable {
    
    private static TabelaFornecedor tabela;
    
    public TabelaFornecedor() {
        super();
    }
    
    public static void preencherTabela(TipoPreenchimentoTabela tipo, String cond) {
        String[][] dados2;
        String[] colunas2 = new String[]{"ID", "Nome", "Contato", "Fone"};
        
        if(tipo == TipoPreenchimentoTabela.BUSCAR) {
            dados2 = buscarFornecedores(cond);
        } else {
            dados2 = buscarFornecedores("");
        }
        
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        if(tipo == TipoPreenchimentoTabela.CRIAR) tabela = new TabelaFornecedor();
        
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
    
    public static TabelaFornecedor getTabela() {
        return tabela;
    }
    
    private static String[][] buscarFornecedores(String cond) {
        
        String[][] forns;
        String condicao;
        ResultSet rs;
        int nLinhas;
        
        if(cond.equals("")) {
            condicao = "ativo_fornecedor = 1";
        } else {
            condicao = "ativo_fornecedor = 1 and " + cond;
        }
        
        rs = projeto.barbearia.Model.Fornecedor.buscar("*", condicao);        
        nLinhas = ResultSetToolBox.countRows(rs);
        forns = new String[nLinhas][4];
        
        if (nLinhas > 0) {
            
            try {
                rs.beforeFirst();
                int i = 0;
                
                while (rs.next()) {
                    forns[i][0] = rs.getString("fornecedorid");
                    forns[i][1] = rs.getString("nome_fornecedor");
                    forns[i][2] = rs.getString("nomectt_fornecedor");
                    forns[i][3] = rs.getString("fone_fornecedor");
                    i++;
                }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
        return forns;
        
    }
    
}
