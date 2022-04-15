package bmkey;

/**
 *
 * @author lucas
 */
public class BMKey {

    /**
     * O valor que será escrito no console por meio da função main será utilizado
     * pelo Barber Manager para validar a iniciação do sistema. Mais detalhes nas
     * classes subsequentes.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Gerenciador g = new Gerenciador();   //Produção
        System.out.println(g.validar());     //Produção
        //System.out.println("valid");           //Testes
    }
    
}
