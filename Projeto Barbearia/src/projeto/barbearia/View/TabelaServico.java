
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
public class TabelaServico extends JTable {
    
    private static TabelaServico tabela;
    
    public TabelaServico() {
        super();
    }
    
    public static void preencherTabela(TipoPreenchimentoTabela tipo, String cond) {
        String[][] dados2;
        String[] colunas2 = new String[]{"ID", "Tipo", "Nome", "Valor", "Comissão"};
        
        if(tipo == TipoPreenchimentoTabela.BUSCAR) {
            dados2 = buscarServicos(cond);
        } else {
            dados2 = buscarServicos("");
        }
        
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        if(tipo == TipoPreenchimentoTabela.CRIAR) tabela = new TabelaServico();
        
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

    public static TabelaServico getTabela() {
        return tabela;
    }
    
    private static String[][] buscarServicos(String cond) {
        
        String[][] servs;
        String condicao;
        ResultSet rs;
        int nLinhas = 0;
        
        if(cond.equals("")) {
            condicao = "ativo_servico = 1";
        } else {
            condicao = "ativo_servico = 1 and " + cond;
        }
        
        rs = projeto.barbearia.Model.Servico.buscar("*", condicao);        
        nLinhas = ResultSetToolBox.countRows(rs);
        servs = new String[nLinhas][5];
        
        if (nLinhas > 0) {
            
            try {
                rs.beforeFirst();
                int i = 0;
                
                while (rs.next()) {
                    servs[i][0] = rs.getString("servicoid");
                    servs[i][1] = rs.getString("tipo_servico");
                    servs[i][2] = rs.getString("nome_servico");
                    servs[i][3] = rs.getString("valor_servico");
                    servs[i][4] = rs.getString("comissao_servico");
                    i++;
                }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
        return servs;
        
    }
    
}
