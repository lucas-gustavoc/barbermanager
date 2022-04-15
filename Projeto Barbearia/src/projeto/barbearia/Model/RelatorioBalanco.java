
package projeto.barbearia.Model;

import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import projeto.barbearia.Model.db.Select;
import projeto.barbearia.View.RenderizadorRelatorioBalanco;
import projeto.barbearia.View.Tabela;

/**
 *
 * @author lucas
 */
public class RelatorioBalanco extends Relatorio {

    private final Date ini;
    
    private final Date fin;
    
    private int status = 0;
    
    public RelatorioBalanco(Date ini, Date fin) {
        this.ini = ini;
        this.fin = fin;
    }
    
    @Override
    public void criar() {
        String[] colunas = new String[]{"Data", "Descrição", "Valor", "Tipo"};
        String[][] dados = obterDados();
        
        tabela = new Tabela(dados, colunas);
        formatarTabela();
    }
    
    private void formatarTabela() {
        
        //Centralizando
        tabela.getColumnModel().getColumn(0).setCellRenderer(new RenderizadorRelatorioBalanco());
        tabela.getColumnModel().getColumn(3).setCellRenderer(new RenderizadorRelatorioBalanco());
        
        //Definindo tamanhos
        tabela.getColumnModel().getColumn(1).setMinWidth(200);
        tabela.getColumnModel().getColumn(3).setMaxWidth(50);
        
    }
    
    private String[][] obterDados() {
        
        String[][] r = new String[1][4];
        ResultSet rs1, rs2;
        Select s;
        int count;
        
        s = new Select("select * "
                + "from entrada where dtcad_entrada >= '" + ini.toString() + "'" + 
                " and dtcad_entrada <= '" + fin.toString() + "'" +
                " order by dtcad_entrada desc, nome_entrada");
        s.execute();
        rs1 = s.getResult();
        
        s = new Select("select * "
                + "from saida where dtcad_saida >= '" + ini.toString() + "'" + 
                " and dtcad_saida <= '" + fin.toString() + "'" +
                " order by dtcad_saida desc, nome_saida");
        s.execute();
        rs2 = s.getResult();
        
        count = ResultSetToolBox.countRows(rs1) + ResultSetToolBox.countRows(rs2);
        if (count > 0) {
            
            r = new String[count + 5][5];
            float totalVendas = 0;
            FormatadorParaSQLData f = new FormatadorParaSQLData();

            //Obtendo valores da entrada
            r[0][1] = ">>> ENTRADAS <<<";

            count = 2;
            try {
                rs1.beforeFirst();
                while (rs1.next()) {
                    r[count][0] = f.reverse(rs1.getString("dtcad_entrada"));
                    r[count][1] = rs1.getString("nome_entrada");
                    r[count][2] = "R$ " + rs1.getString("valor_entrada").replace('.', ',');
                    r[count][3] = "(+)";

                    totalVendas += rs1.getFloat("valor_entrada");
                    count++;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            

            //Obtendo valores da saída
            count++;
            r[count][1] = ">>> SAÍDAS <<<";

            count += 2;
            try {
                rs2.beforeFirst();
                while (rs2.next()) {
                    r[count][0] = f.reverse(rs2.getString("dtcad_saida"));
                    r[count][1] = rs2.getString("nome_saida");
                    r[count][2] = "R$ " + rs2.getString("valor_saida").replace('.', ',');
                    r[count][3] = "(-)";

                    totalVendas -= rs2.getFloat("valor_saida");
                    count++;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            

            //Editando texto
            textoRetorno = "Balaço do Período: R$ " + 
                    String.valueOf(totalVendas).replace('.', ',');
            
            if (totalVendas > 0) {
                status = 1;
            } else if (totalVendas < 0) {
                status = -1;
            }
            
            //Caso tenhamos um número inteiro, adicionar 0 à esquerada.
            if (Integer.valueOf(String.valueOf(totalVendas).
                    substring(String.valueOf(totalVendas).
                            indexOf('.') + 1)) < 10) textoRetorno += "0";
                
        }
        
        return r;
    }

    public int getStatus() {
        return status;
    }
    
}
