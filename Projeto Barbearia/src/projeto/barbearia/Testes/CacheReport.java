
package projeto.barbearia.Testes;

/**
 *
 * @author lucas
 */
public abstract class CacheReport {
    
    private static Report r;
    
    public static void makeAReport(String origem, String msg) {
        if (r == null) r = new Report();
        r.write(origem, msg);
    }
    
}
