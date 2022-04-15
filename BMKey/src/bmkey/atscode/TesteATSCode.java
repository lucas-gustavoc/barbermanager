package bmkey.atscode;

/**
 *
 * @author lucas
 */
public class TesteATSCode {
    
    public static void TTmain(String[] args) {
        
        ATSCode ats = new ATSCode();
        
        bmkey.Conector c = new bmkey.Conector();
        c.cadastrarATSCode("JB2A");
        ats.gerar();
        System.out.println(ats.getTrialDays());
        
    }
    
}
