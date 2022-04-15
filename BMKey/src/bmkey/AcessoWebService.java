package bmkey;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author marcelosiedler
 */
public class AcessoWebService {

    private final String USER_AGENT = "Mozilla/5.0";
    
    public String contatarWebService(String path) throws Exception {
        
        AcessoWebService http = new AcessoWebService();
        
        String r = http.sendGet(path, "GET");
        
        /* TESTES
        String r = "";
        if (path.contains("validaracesso")) {
            r = "block";
        } else {
            r = "989898";
        } TESTES */
        
        return r;
        
    }

    // HTTP GET request
    private String sendGet(String url, String method) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod(method);

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }

}
