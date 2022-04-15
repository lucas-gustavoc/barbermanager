
package validador.model;

import java.sql.ResultSet;
import validador.model.db.*;

/**
 *
 * @author lucas
 */
public class Chave {
    
    /**
     * Em geral, este atributo deve ser gerado a partir da classe KeyGen, que 
     * cria uma chave já com o atributo chave preenchido, por meio do método
     * obterChave()
     */
    private String chave;

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }
    
    /**
     * Método utilizado para cadastrar a nova chave no banco.
     * @return boolean com o resultado da operação.
     */
    public boolean cadastrar() {
        boolean ret;
        
        Insert n = new Insert("CHAVE");
        n.addField("chave", chave, "string");
        n.addField("computador", "", "string");
        n.addField("ativo_chave", 1, "int");
        
        ret = n.execute();
        
        return ret;
    }
    
    /**
     * Método utilizado para inativar uma chave, podendo ser ativada novamente depois.
     * @param c chave a ser inativada.
     * @return boolean com o resultado da operação.
     */
    public static boolean excluir(String c) {
        boolean ret;
        
        Update n = new Update("CHAVE");
        n.setCondition("chave = '" + c + "'");
        n.addField("ativo_chave", 0, "int");
        ret = n.execute();
        
        return ret;
    }
    
    /**
     * Método utilizado para remover o PC ligado à chave em questão.
     * @param c chave da qual o PC será removido.
     * @return boolean com o resultado da operação.
     */
    public static boolean removerPc(String c) {
        boolean ret;
        
        Update n = new Update("CHAVE");
        n.setCondition("chave = '" + c + "'");
        n.addField("computador", "", "string");
        ret = n.execute();
        
        return ret;
    }
    
    /**
     * Método utilizado para buscar as chaves ativas no banco de dados.
     * @return uma matriz com as chaves encontradas.
     */
    public static String[][] buscarTodasAsChaves() {
        return buscarChavesInterno("select * from chave where ativo_chave = 1");
    }
    
    public static String[][] buscarChaveEspecifica(String chave) {
        return buscarChavesInterno("select * from chave where ativo_chave = 1 "
                + "and chave = '" + chave.toUpperCase() + "'");
    }
    
    private static String[][] buscarChavesInterno(String stt) {
        ResultSet rs;
        String[][] r = new String[0][2];
        int count = 0;
        
        Select s = new Select(stt);
        s.execute();
        rs = s.getResult();
        count = ResultSetToolBox.countRows(rs);
        
        if (count > 0) {
            r = new String[count][2];
            try {
                rs.beforeFirst();
                count = 0;
                while (rs.next()) {
                    r[count][0] = rs.getString("chave");
                    r[count][1] = rs.getString("computador");
                    count++;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return r;
    }
    
}
