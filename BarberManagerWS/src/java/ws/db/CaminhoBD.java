
package ws.db;

/**
 *
 * @author lucas
 */
public class CaminhoBD {
    
    private static String dataBasePath = "";
    
    public static String getDataBasePath() {
        
        if (dataBasePath.equals("")) {
            try {
                java.io.FileReader fr = new java.io.FileReader("C:\\Program Files\\tomcat8\\webapps\\barbermanagerws\\dbp.bm");
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
