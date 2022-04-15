
package projeto.barbearia.Model;

/**
 *
 * @author lucas
 */
public class FormatadorParaSQLData {
    
    public String formatarParaSQLData(String s) {

        int firstSlash;
        int secondSlash;
        String d = null;
        String yyyy;
        String mm;
        String dd;
        
        firstSlash = s.indexOf('/');
        secondSlash = s.indexOf('/', firstSlash + 1);
        
        if (firstSlash > 0 && secondSlash > 0 && secondSlash < s.length() - 1) {
            yyyy = s.substring(secondSlash + 1);
            mm = s.substring(firstSlash + 1, secondSlash);
            dd = s.substring(0, firstSlash);
            
            if (yyyy.length() == 4) {
                d = yyyy + "-" + mm + "-" + dd;
            } else {
                throw new java.lang.IllegalArgumentException("A data precisa estar no formato dd/mm/yyyy.");
            }
            
        } else {
            throw new java.lang.IllegalArgumentException("A data precisa estar no formato dd/mm/yyyy.");
        }
        
        return d;
        
    }
    
    public String reverse(String s) {
    
        int firstDash;
        int secondDash;
        String d = null;
        String yyyy;
        String mm;
        String dd;
        
        firstDash = s.indexOf('-');
        secondDash = s.indexOf('-', firstDash + 1);
        
        if (firstDash > 0 && secondDash > 0 && secondDash < s.length() - 1) {
            yyyy = s.substring(0, firstDash);
            mm = s.substring(firstDash + 1, secondDash);
            dd = s.substring(secondDash + 1);
            
            if (yyyy.length() == 4) {
                d = dd + "/" + mm + "/" + yyyy;
            } else {
                throw new java.lang.IllegalArgumentException("A data precisa estar no formato dd/mm/yyyy.");
            }
            
        } else {
            throw new java.lang.IllegalArgumentException("A data precisa estar no formato dd/mm/yyyy.");
        }
        
        return d;
        
    }
    
    
}
