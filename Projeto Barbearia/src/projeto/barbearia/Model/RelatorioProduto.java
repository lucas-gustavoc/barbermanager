
package projeto.barbearia.Model;

import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import projeto.barbearia.Model.db.Select;
import projeto.barbearia.View.Tabela;

/**
 *
 * @author lucas
 */
public class RelatorioProduto extends Relatorio {

    private final Date ini;
    
    private final Date fin;
    
    private final int produtoid;
    
    public RelatorioProduto(Date ini, Date fin, int produtoid) {
        this.ini = ini;
        this.fin = fin;
        this.produtoid = produtoid;
    }
    
    @Override
    public void criar() {
        String[] colunas = new String[]{"Data", "Descrição", "Profissional", "Qtd.", "Valor Total"};
        String[][] dados = obterDados();
        
        tabela = new Tabela(dados, colunas);
        formatarTabela();
    }
    
    private void formatarTabela() {
        
        //Centralizando
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabela.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        //Definindo tamanhos de colunas
        tabela.getColumnModel().getColumn(1).setMinWidth(170);
        tabela.getColumnModel().getColumn(3).setMaxWidth(50);
        
    }
    
    private String[][] obterDados() {
        
        String[][] r = new String[1][5];
        ResultSet rs;
        Select s;
        int count;
        
        s = new Select("select entrada.*, profissional.nome_profissional "
                + "from entrada, profissional "
                + "where produtoid = " + produtoid + 
                " and dtcad_entrada >= '" + ini.toString() + "'" + 
                " and dtcad_entrada <= '" + fin.toString() + "'" +
                " and profissional.profissionalid = entrada.profissionalid" +
                " order by dtcad_entrada desc, nome_entrada");
        s.execute();
        rs = s.getResult();
        
        count = ResultSetToolBox.countRows(rs);
        if (count > 0) {
            try {
                r = new String[count][5];
                rs.beforeFirst();
                count = 0;
                float totalVendas = 0;
                FormatadorParaSQLData f = new FormatadorParaSQLData();
                while (rs.next()) {
                    r[count][0] = f.reverse(rs.getString("dtcad_entrada"));
                    r[count][1] = rs.getString("nome_entrada");
                    r[count][2] = rs.getString("nome_profissional");
                    r[count][3] = rs.getString("qtd_entrada");
                    r[count][4] = "R$ " + rs.getString("valor_entrada").replace('.', ',');
                    
                    totalVendas += rs.getFloat("valor_entrada");
                    count++;
                }
                
                //Editando texto
                textoRetorno = "Valor total das vendas: R$ " + 
                        String.valueOf(totalVendas).replace('.', ',');
                //Caso tenhamos um número inteiro, adicionar 0 à esquerada.
                if (Integer.valueOf(String.valueOf(totalVendas).
                        substring(String.valueOf(totalVendas).
                                indexOf('.') + 1)) < 10) textoRetorno += "0";
                
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return r;
    }
    
}
