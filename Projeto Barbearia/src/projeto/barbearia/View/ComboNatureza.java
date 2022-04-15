
package projeto.barbearia.View;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import projeto.barbearia.Model.ResultSetToolBox;
import projeto.barbearia.Model.Tipos.ComboNaturezaTipo;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class ComboNatureza extends JComboBox {
    
    ComboNatureza combo;
    
    public ComboNatureza() {
        super();
        combo = this;
    }
    
    public void preencherComboPara(ComboNaturezaTipo tipo, Object args[]) {
        
        switch (tipo) {
            case CADASTRO_ENTRADA:
                preencherParaCadastroEntrada((int) args[0]);
                break;
            case CADASTRO_SAIDA:
                preencherParaCadastroSaida((int) args[0]);
                break;
        }
        
    }
    
    private void preencherParaCadastroEntrada(int naturezaidAtual) {
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select naturezaid, nome_natureza from natureza "
                + "where entrada_natureza = 1 and ativo_natureza = 1");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        combo.addItem("Selecione...");
                
        if (nLinhas > 0) {
            try {
                
                int nIndex = 0, i = 1;
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("naturezaid") + " - " + rs.getString("nome_natureza"));
                    
                    if (Integer.valueOf(rs.getString("naturezaid")) == naturezaidAtual) nIndex = i;
                    
                    i++;
                }
                
                if (nIndex == 0) {
                    if (procurarRegistroFantasma(naturezaidAtual)) nIndex = i;
                }
                
                combo.setSelectedIndex(nIndex);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
    private void preencherParaCadastroSaida(int naturezaidAtual) {
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select naturezaid, nome_natureza from natureza "
                + "where saida_natureza = 1 and ativo_natureza = 1");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        combo.addItem("Selecione...");
        
        if (nLinhas > 0) {
            try {
                
                int nIndex = 0, i = 1;
                
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("naturezaid") + " - " + rs.getString("nome_natureza"));
                    
                    if (Integer.valueOf(rs.getString("naturezaid")) == naturezaidAtual) nIndex = i;
                    
                    i++;
                }
                
                if (nIndex == 0) {
                    if (procurarRegistroFantasma(naturezaidAtual)) nIndex = i;
                }
                
                combo.setSelectedIndex(nIndex);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
    private boolean procurarRegistroFantasma(int naturezaidAtual) {
        boolean ret = false;
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select naturezaid, nome_natureza from natureza "
                + "where naturezaid = " + naturezaidAtual + " and ativo_natureza = 0");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        if (nLinhas > 0) {
            try {
                rs.first();
                combo.addItem(rs.getString("naturezaid") + " - " + rs.getString("nome_natureza"));
                ret = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return ret;
    }
    
}
