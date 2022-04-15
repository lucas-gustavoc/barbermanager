
package projeto.barbearia.View;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import projeto.barbearia.Model.FormatadorParaSQLData;
import projeto.barbearia.Model.ResultSetToolBox;
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

/**
 *
 * @author lucas
 */
public class TabelaSaida extends JTable {
    
    private static TabelaSaida tabela;
    
    public TabelaSaida() {
        super();
    }
    
    public static void preencherTabela(TipoPreenchimentoTabela tipo, String cond) {
        String[][] dados2;
        String[] colunas2 = new String[]{"ID", "Descrição", "Valor", "Pago em", "Vencimento"};
        
        if(tipo == TipoPreenchimentoTabela.BUSCAR) {
            dados2 = buscarSaidas(cond);
        } else {
            dados2 = buscarSaidas("");
        }
        
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        if(tipo == TipoPreenchimentoTabela.CRIAR) tabela = new TabelaSaida();
        
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
    
    public static TabelaSaida getTabela() {
        return tabela;
    }
    
    private static String[][] buscarSaidas(String cond) {
        
        String[][] sss;
        String condicao;
        ResultSet rs;
        int nLinhas = 0;
        
        if(cond.equals("")) {
            condicao = "ativo_saida = 1";
        } else {
            condicao = "ativo_saida = 1 and " + cond;
        }
        
        rs = projeto.barbearia.Model.Saida.buscar("*", condicao, "dtvcto_saida desc, dtcad_saida desc");        
        nLinhas = ResultSetToolBox.countRows(rs);
        sss = new String[nLinhas][5];
        
        if (nLinhas > 0) {
            
            try {
                rs.beforeFirst();
                int i = 0;
                String dtpgto;
                String dtvcto;
                FormatadorParaSQLData f = new FormatadorParaSQLData();
                
                while (rs.next()) {
                    
                    dtpgto = rs.getString("dtpagto_saida");
                    if (dtpgto != null) dtpgto = f.reverse(dtpgto);
                    dtvcto = rs.getString("dtvcto_saida");
                    if (dtvcto != null) dtvcto = f.reverse(dtvcto);
                    
                    sss[i][0] = rs.getString("saidaid");
                    sss[i][1] = rs.getString("nome_saida");
                    sss[i][2] = rs.getString("valor_saida");
                    sss[i][3] = dtpgto;
                    sss[i][4] = dtvcto;
                    i++;
                }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
        return sss;
        
    }
    
}
