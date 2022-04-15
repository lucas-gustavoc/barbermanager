package bmkey.amosnum;

/**
 *
 * @author lucas
 */
public class TesteAMOSNum {
    
    public static void TTmain() {
        AmosNum amos = new AmosNum();
        amos.gerar();
        
        if (amos.validar()) {
            System.out.println("Tudo ok!");
        } else {
            System.out.println("Ixii....");
        }
    }
    
    
    
}
