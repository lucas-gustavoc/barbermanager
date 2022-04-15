
package ws;

/**
 *
 * @author lucas
 */
public class PcCodGen {
    
    public static String obterCod() {
        String sets = "0123456789";
        String pccod = "";
        int index;
        
        for (int i = 0; i < 30; i++) {
            index = (int) (Math.random() * 10);
            pccod += sets.substring(index, index + 1);
        }
        
        return pccod;
    }
    
}
