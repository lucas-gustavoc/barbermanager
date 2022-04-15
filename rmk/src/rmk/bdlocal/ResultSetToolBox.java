package rmk.bdlocal;

/**
 *
 * @author lucas
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultSetToolBox {
    
    public static int countRows(ResultSet rs) {
        
        int count = 0;
        
        try {
            
            rs.beforeFirst();
            
            if (rs.next()) {
                rs.beforeFirst();
                
                while (rs.next()) count++;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetToolBox.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
        
    }
    
}
