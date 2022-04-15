
package projeto.barbearia.Controller;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import projeto.barbearia.Model.FormatadorParaSQLData;
import projeto.barbearia.Model.Parametros;
import projeto.barbearia.Model.ResultSetToolBox;
import projeto.barbearia.Model.db.Select;

/**
 *
 * @author lucas
 */
public class ControladorTelaAvisos {
    
    public static final int AVISO_ANIVERSARIO = 1;
    
    public static final int AVISO_VENCIMENTO = 2;
    
    public final boolean avNiverAtivado = Parametros.getLembreteAniversarioAtivado();
    
    public final boolean avVctoAtivado = Parametros.getLembreteVencimentosAtivado();
    
    public JLabel[] obterLabels(int tipo) {
        JLabel[] r = new JLabel[1];
        
        switch (tipo) {
            case 1:
                r = obterLabelsAvNiver();
                break;
            case 2:
                r = obterLabelsAvVecto();
                break;
        }
        
        return r;
    }

    private JLabel[] obterLabelsAvNiver() {
        
        JLabel[] r;
        String[] datas = getDateAndDateWithDaysAgo(
                Parametros.getQtdDiasLembreteAniversario());
        /**
         * WOOOOOWWWWWW :o \o/
         */
        String stt = "select nome_cliente as nome, nasc_cliente as " +
                    "datacad, CAST(EXTRACT(month from nasc_cliente) as int) as dataref1, " +
                    "CAST(EXTRACT(day from nasc_cliente) as int) as dataref2 from cliente " +
                    "where nasc_cliente is not null and " +
                    "CAST(EXTRACT(year from CURRENT_DATE) || '-' || " +
                    "EXTRACT(month from nasc_cliente) || '-' || EXTRACT(day from nasc_cliente) " +
                    "as DATE) >= '" + datas[0] + "' and CAST(EXTRACT(year from CURRENT_DATE) " +
                    "|| '-' || EXTRACT(month from nasc_cliente) || '-' " +
                    "|| EXTRACT(day from nasc_cliente) " +
                    "as DATE) <= '" + datas[1] + "' " +
                    "order by dataref1, dataref2, nome";
        Select s = new Select(stt);
        
        s.execute();
        r = lerResultSet(s.getResult());
       
        return r;
    }

    private JLabel[] obterLabelsAvVecto() {
        
        JLabel[] r;
        String[] datas = getDateAndDateWithDaysAgo(
                Parametros.getQtdDiasLembreteVencimento());
        String stt = "select nome_saida as nome, dtvcto_saida as "
                + "datacad from saida where dtvcto_saida is not null and "
                + "dtpagto_saida is null and dtvcto_saida <= '" + datas[1] + "' "
                + "order by dtvcto_saida, nome";
        Select s = new Select(stt);
        
        s.execute();
        r = lerResultSet(s.getResult());
       
        return r;
    }
    
    private String[] getDateAndDateWithDaysAgo(int daysAgo) {
        String[] r = new String[2];
        FormatadorParaSQLData f = new FormatadorParaSQLData();
        
        //Obtendo data de hoje
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date datem = new java.util.Date();
        r[0] = f.formatarParaSQLData(dateFormat.format(datem));
        
        //Obtendo data com os daysAgo (no caso, será uma data  
        //com (daysAgo X dias) a mais do que hoje)
        Calendar c = Calendar.getInstance(); 
        c.setTime(datem); 
        c.add(Calendar.DATE, daysAgo);
        datem = c.getTime();
        r[1] = f.formatarParaSQLData(dateFormat.format(datem));
        
        return r;
    }
    
    private JLabel[] lerResultSet(ResultSet rs) {
        JLabel[] r = new JLabel[1];
        r[0] = new JLabel("Nada por aqui.");
        int count = ResultSetToolBox.countRows(rs);
        
        if (count > 0) {
            try {
                r = new JLabel[count];
                String textoData;
                String[] datasParaComparacao = getDateAndDateWithDaysAgo(1);
                
                //o int a seguir é usado para identificar o primeiro traço, que permitirá
                //abaixo a comparação ignorando o ano, como deve ser feito no caso de 
                //aniversários.
                int firstDash = datasParaComparacao[0].indexOf('-');
                
                rs.beforeFirst();
                count = 0;
                
                while (rs.next()) {
                    textoData = rs.getString("datacad");
                    if (textoData.endsWith(datasParaComparacao[0].substring(firstDash))) {
                        //Quer dizer que é a data de hoje
                        textoData = "Hoje!";
                    } else if (textoData.endsWith(datasParaComparacao[1].substring(firstDash))) {
                        //Quer dizer que é a data de amanhã
                        textoData = "Amanhã!";
                    } else {
                        textoData = new FormatadorParaSQLData().reverse(textoData);
                        textoData = textoData.substring(0, 5);
                    }
                    r[count] = new JLabel(rs.getString("nome") + ": " + textoData);
                    count++;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return r;
    }
    
}
