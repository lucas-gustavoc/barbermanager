
package projeto.barbearia.View;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import projeto.barbearia.Model.ResultSetToolBox;
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

/**
 *
 * @author lucas
 */
public class TabelaComissaoProfissional extends JTable {
    
    private static TabelaComissaoProfissional tabela;
    
    public TabelaComissaoProfissional() {
        super();
    }
    
    public static void preencherTabela(TipoPreenchimentoTabela tipo, int profissionalid) {
        String[][] dados2;
        String[] colunas2 = new String[]{"ID", "Serviço", "Comissão"};
        
        dados2 = buscarComissoes(profissionalid);
        
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        if(tipo == TipoPreenchimentoTabela.CRIAR) tabela = new TabelaComissaoProfissional();
        
        tabela.setModel(model);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setCellSelectionEnabled(false);
        tabela.setRowSelectionAllowed(true);
        
    }

    public static TabelaComissaoProfissional getTabela() {
        return tabela;
    }
    
    private static String[][] buscarComissoes(int profissionalid) {
        
        String[][] coms;
        ResultSet rs;
        int nLinhas = 0;
        
        rs = projeto.barbearia.Model.Profissional.buscarComissoesPersonalizadas(profissionalid);
        nLinhas = ResultSetToolBox.countRows(rs);
        coms = new String[nLinhas][3];
        
        
        
        if (nLinhas > 0) {
            
            try {
                rs.beforeFirst();
                int i = 0;
                
                while (rs.next()) {
                    coms[i][0] = rs.getString("servicoid");
                    coms[i][1] = rs.getString("nome_servico");
                    coms[i][2] = rs.getString("porcentagem_comissao");
                    i++;
                }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
        return coms;
        
    }
    
}
