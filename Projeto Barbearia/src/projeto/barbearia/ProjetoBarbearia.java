package projeto.barbearia;

import projeto.barbearia.Model.db.ConexaoBd;
import projeto.barbearia.View.Interface;
import projeto.barbearia.View.TelaBoasVindas;

public class ProjetoBarbearia {
    
    public static void main(String[] args) {
       if (verificarConexao()) {
           new TelaBoasVindas();
           if (verificarAtivacao()) {
               new Interface().criaTudo();
           }
       }
    }
    
    /**
     * Se a conexão não estiver ok, o sistema não pode iniciar. 
     * @return confirmação do sucesso ou não do acesso ao banco de dados
     */
    private static boolean verificarConexao() {
        boolean r = true;
        
        java.sql.Connection c;
        c = ConexaoBd.getConexao();
        
        if (c == null) {
            r = false;
        }
        
        return r;
    }
    
    /**
     * Verifica a ativação e a possibilidade de acesso via licença de teste.
     * @return status da ativação.
     */
    private static boolean verificarAtivacao() {
        boolean ret = false;
        String status = "";
        /****/
        try {
            Process ps=Runtime.getRuntime().exec(new String[]{"java","-jar","BMKey.jar"});
            ps.waitFor();
            java.io.InputStream is=ps.getInputStream();
            byte b[]=new byte[is.available()];
            is.read(b,0,b.length);
            status = new String(b);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        if (status.toLowerCase().contains("valid")) {
            ret = true;
        }
        /**/
        return ret;
    }
    
}
