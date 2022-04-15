
package projeto.barbearia.View;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import projeto.barbearia.Model.ResultSetToolBox;
import projeto.barbearia.Model.Tipos.ComboProdutoTipo;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class ComboProduto extends JComboBox {
    
    ComboProduto combo;
    
    public ComboProduto() {
        super();
        combo = this;
    }
    
    public void preencherComboPara(ComboProdutoTipo tipo, Object args[]) {
        
        switch (tipo) {
            case CADASTRO_ENTRADA:
                preencherParaCadastroGeral((int) args[0]);
                break;
            case RELATORIO:
                preencherParaCadastroGeral(-1);
                break;
        }
        
    }
    
    /**
     * 
     * @param produtoidAtual id do produto cadastrado atualmente. Definir como
     * -1 caso deseje desconsiderar este ponto.
     */
    private void preencherParaCadastroGeral(int produtoidAtual) {
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select produtoid, nome_produto from "
                + "produto where ativo_produto = 1");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        combo.addItem("Selecione...");
        
        if (nLinhas > 0) {
            try {
                
                int nIndex = 0, i = 1;
                
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("produtoid") + " - " + rs.getString("nome_produto"));
                    
                    if (Integer.valueOf(rs.getString("produtoid")) == produtoidAtual) nIndex = i;
                    
                    i++;
                }
                
                combo.setSelectedIndex(nIndex);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
}
