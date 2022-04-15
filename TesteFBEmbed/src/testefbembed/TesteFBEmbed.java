package testefbembed;

import testefbembed.bdremoto.*;

/**
 *
 * @author lucas
 */
public class TesteFBEmbed {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Starting...");
        
        Insert i = new Insert("REGISTRO");
        i.addField("nome_registro", "Show", "string");
        
        System.out.println((i.execute()) ? "Perfeito, conseguimos!" : "Hmmm....");
        
        //ConexaoBd.getConexao();
        //System.out.println(ConexaoBd.status);
        //ConexaoBd.FecharConexao();
        
        System.out.println("Finalizado");
    }
    
}
