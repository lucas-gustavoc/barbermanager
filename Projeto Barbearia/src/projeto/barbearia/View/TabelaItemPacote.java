
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
public class TabelaItemPacote extends JTable {
    
    private static TabelaItemPacote tabela;
    
    public TabelaItemPacote() {
        super();
    }
    
    public static void preencherTabela(TipoPreenchimentoTabela tipo, int pacoteid) {
        String[][] dados2;
        String[] colunas2 = new String[]{"ID do Item", "Nome do Item", "QTD."};
        
        dados2 = buscarItens(pacoteid);
        
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        if(tipo == TipoPreenchimentoTabela.CRIAR) tabela = new TabelaItemPacote();
        
        tabela.setModel(model);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setCellSelectionEnabled(false);
        tabela.setRowSelectionAllowed(true);
        
        //Centralizando e mudando tamanho da coluna ID
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabela.getColumnModel().getColumn(0).setMaxWidth(70);
        tabela.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tabela.getColumnModel().getColumn(2).setMaxWidth(110);
        
    }
    
    public static TabelaItemPacote getTabela() {
        return tabela;
    }
    
    private static String[][] buscarItens(int pacoteid) {
        
        String[][] itens;
        ResultSet rs;
        int nLinhas = 0;
        
        rs = projeto.barbearia.Model.ItemPacote.buscar(pacoteid);
        nLinhas = ResultSetToolBox.countRows(rs);
        itens = new String[nLinhas][3];
        
        
        
        if (nLinhas > 0) {
            
            try {
                rs.beforeFirst();
                int i = 0;
                
                while (rs.next()) {
                    itens[i][0] = rs.getString("itempacoteid");
                    itens[i][1] = rs.getString("nome_servico");
                    itens[i][2] = rs.getString("qtd_itempacote");
                    i++;
                }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
        return itens;
        
    }
    
}
