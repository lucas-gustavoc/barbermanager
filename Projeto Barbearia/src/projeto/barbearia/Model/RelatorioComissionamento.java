
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
public class RelatorioComissionamento extends Relatorio {

    private final Date ini;
    
    private final Date fin;
    
    private final int profissionalid;
    
    public RelatorioComissionamento(Date ini, Date fin, int profissionalid) {
        this.ini = ini;
        this.fin = fin;
        this.profissionalid = profissionalid;
    }
    
    @Override
    public void criar() {
        String[] colunas = new String[]{"Data", "Descrição", "Valor Total", "Com. (%)", "Com. Total"};
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
        tabela.getColumnModel().getColumn(3).setMaxWidth(70);
        
    }
    
    private String[][] obterDados() {
        
        String[][] r = new String[1][5];
        ResultSet rs;
        Select s;
        int count;
        
        s = new Select("select entrada.*, (valor_entrada * "
                + "(comissao_entrada / 100)) as comissaocalculada from entrada "
                + "where profissionalid = " + profissionalid + 
                " and dtcad_entrada >= '" + ini.toString() + "'" + 
                " and dtcad_entrada <= '" + fin.toString() + "'" + 
                " order by dtcad_entrada desc, nome_entrada");
        s.execute();
        rs = s.getResult();
        
        count = ResultSetToolBox.countRows(rs);
        if (count > 0) {
            try {
                r = new String[count][5];
                rs.beforeFirst();
                count = 0;
                float totalComissoes = 0;
                FormatadorParaSQLData f = new FormatadorParaSQLData();
                while (rs.next()) {
                    r[count][0] = f.reverse(rs.getString("dtcad_entrada"));
                    r[count][1] = rs.getString("nome_entrada");
                    r[count][2] = "R$ " + rs.getString("valor_entrada").replace('.', ',');
                    r[count][3] = rs.getString("comissao_entrada") + "%";
                    r[count][4] = "R$ " + rs.getString("comissaocalculada").replace('.', ',');
                    
                    totalComissoes += rs.getFloat("comissaocalculada");
                    count++;
                }
                
                //Editando texto
                textoRetorno = "Valor total das comissões: R$ " + 
                        String.valueOf(totalComissoes).replace('.', ',');
                //Caso tenhamos um número inteiro, adicionar 0 à esquerada.
                if (Integer.valueOf(String.valueOf(totalComissoes).
                        substring(String.valueOf(totalComissoes).
                                indexOf('.') + 1)) < 10) textoRetorno += "0";
                
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return r;
    }
    
}
