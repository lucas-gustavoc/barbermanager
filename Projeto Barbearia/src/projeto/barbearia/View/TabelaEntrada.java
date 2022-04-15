
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
 * @author mestr
 */
public class TabelaEntrada extends JTable{
    
    private static TabelaEntrada tabela;
    
    public TabelaEntrada() {
        super();
    }
    
    public static void preencherTabela(TipoPreenchimentoTabela tipo, String cond) {
        String[][] dados2;
        String[] colunas2 = new String[]{"ID", "Descrição", "Valor", "Pago em"};
        
        if(tipo == TipoPreenchimentoTabela.BUSCAR) {
            dados2 = buscarEntradas(cond);
        } else {
            dados2 = buscarEntradas("");
        }
        
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        if(tipo == TipoPreenchimentoTabela.CRIAR) tabela = new TabelaEntrada();
        
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
    
    public static TabelaEntrada getTabela() {
        return tabela;
    }
    
    private static String[][] buscarEntradas(String cond) {
        
        String[][] ents;
        String condicao;
        ResultSet rs;
        int nLinhas = 0;
        
        if(cond.equals("")) {
            condicao = "ativo_entrada = 1";
        } else {
            condicao = "ativo_entrada = 1 and " + cond;
        }
        
        rs = projeto.barbearia.Model.Entrada.buscar("*", condicao);        
        nLinhas = ResultSetToolBox.countRows(rs);
        ents = new String[nLinhas][4];
        
        if (nLinhas > 0) {
            
            try {
                rs.beforeFirst();
                int i = 0;
                String dtpgto;
                FormatadorParaSQLData f = new FormatadorParaSQLData();
                
                while (rs.next()) {
                    
                    dtpgto = rs.getString("dtpagto_entrada");
                    if (dtpgto != null) dtpgto = f.reverse(dtpgto);
                    
                    ents[i][0] = rs.getString("entradaid");
                    ents[i][1] = rs.getString("nome_entrada");
                    ents[i][2] = rs.getString("valor_entrada");
                    ents[i][3] = dtpgto;
                    i++;
                }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
        return ents;
        
    }
}
