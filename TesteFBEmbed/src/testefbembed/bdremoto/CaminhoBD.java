
package testefbembed.bdremoto;

/**
 *
 * @author lucas
 */
public class CaminhoBD {
    
    private static String dataBasePath = "";
    
    public static String getDataBasePath() {
        
        if (dataBasePath.equals("")) {
            dataBasePath = "TESTE.FDB";
        }
        
        return dataBasePath;
    }
    
}
