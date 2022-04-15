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
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

/**
 *
 * @author lucas
 */
public class TabelaProfissional extends JTable {
    
    private static TabelaProfissional tabela;
    
    public TabelaProfissional() {
        super();
    }
    
    public static void preencherTabela(TipoPreenchimentoTabela tipo, String cond) {
        String[][] dados2;
        String[] colunas2 = new String[]{"ID", "Nome", "Fone", "Folga"};
        
        if(tipo == TipoPreenchimentoTabela.BUSCAR) {
            dados2 = buscarProfissionais(cond);
        } else {
            dados2 = buscarProfissionais("");
        }
        
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        if(tipo == TipoPreenchimentoTabela.CRIAR) tabela = new TabelaProfissional();
        
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

    public static TabelaProfissional getTabela() {
        return tabela;
    }
    
    private static String[][] buscarProfissionais(String cond) {
        
        String[][] profs;
        String condicao;
        ResultSet rs;
        int nLinhas = 0;
        
        if(cond.equals("")) {
            condicao = "ativo_profissional = 1";
        } else {
            condicao = "ativo_profissional = 1 and " + cond;
        }
        
        rs = projeto.barbearia.Model.Profissional.buscar("*", condicao);        
        nLinhas = ResultSetToolBox.countRows(rs);
        profs = new String[nLinhas][4];
        
        if (nLinhas > 0) {
            
            try {
                rs.beforeFirst();
                int i = 0;
                
                while (rs.next()) {
                    profs[i][0] = rs.getString("profissionalid");
                    profs[i][1] = rs.getString("nome_profissional");
                    profs[i][2] = rs.getString("fone1_profissional");
                    profs[i][3] = rs.getString("folga_profissional");
                    i++;
                }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
        return profs;
        
    }
    
}
