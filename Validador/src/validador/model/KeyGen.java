
package validador.model;

/**
 *
 * @author lucas
 */
public class KeyGen {
    
    public Chave obterChave() {
        Chave c = new Chave();
        String sets = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String novaChave = "";
        int index;
        
        for (int i = 0; i < 10; i++) {
            index = (int) (Math.random() * 46);
            novaChave += sets.substring(index, index + 1);
        }
        
        c.setChave(novaChave);
        
        return c;
    }
    
}
