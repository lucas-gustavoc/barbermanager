
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
public class TabelaProduto extends JTable {
    
    private static TabelaProduto tabela;
    
    public TabelaProduto() {
        super();
    }
    
    public static void preencherTabela(TipoPreenchimentoTabela tipo, String cond) {
        String[][] dados2;
        String[] colunas2 = new String[]{"ID", "Nome", "Marca", "Valor", "Qtd. Estoque"};
        
        if(tipo == TipoPreenchimentoTabela.BUSCAR) {
            dados2 = buscarProdutos(cond);
        } else {
            dados2 = buscarProdutos("");
        }
        
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        if(tipo == TipoPreenchimentoTabela.CRIAR) tabela = new TabelaProduto();
        
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
    
    public static TabelaProduto getTabela() {
        return tabela;
    }
    
    private static String[][] buscarProdutos(String cond) {
        
        String[][] prods;
        String condicao;
        ResultSet rs;
        int nLinhas = 0;
        
        if(cond.equals("")) {
            condicao = "ativo_produto = 1";
        } else {
            condicao = "ativo_produto = 1 and " + cond;
        }
        
        rs = projeto.barbearia.Model.Produto.buscar("*", condicao);        
        nLinhas = ResultSetToolBox.countRows(rs);
        prods = new String[nLinhas][5];
        
        if (nLinhas > 0) {
            
            try {
                rs.beforeFirst();
                int i = 0;
                
                while (rs.next()) {
                    prods[i][0] = rs.getString("produtoid");
                    prods[i][1] = rs.getString("nome_produto");
                    prods[i][2] = rs.getString("marca_produto");
                    prods[i][3] = rs.getString("valor_produto");
                    prods[i][4] = rs.getString("qtd_produto");
                    i++;
                }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
        return prods;
        
    }
    
}
