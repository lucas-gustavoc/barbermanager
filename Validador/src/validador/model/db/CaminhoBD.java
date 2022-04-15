
package validador.model.db;

/**
 *
 * @author lucas
 */
public class CaminhoBD {
    
    private static String dataBasePath = "";
    
    public static String getDataBasePath() {
        //System.getProperty("user.dir")
        if (dataBasePath.equals("")) {
            try {
                java.io.FileReader fr = new java.io.FileReader("dbp.bm");
                java.io.BufferedReader br = new java.io.BufferedReader(fr);
                dataBasePath = br.readLine();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                dataBasePath = "";
            }
        }
        
        return dataBasePath;
    }
    
}
