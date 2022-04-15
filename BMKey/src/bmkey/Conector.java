package bmkey;

import java.sql.ResultSet;
import bmkey.bdlocal.*;

/**
 * Objeto utilizado para acessar valores de registro cadastrados no BD Local.
 * 
 * @author lucas
 */
public class Conector {
    
    /**
     * Acessar a chave atualmente cadastrada no sistema local.
     * @return a chave cadastrada ou uma string vazia em caso de não haver chave.
     */
    public String obterChave() {
        String s = "";
        Select sl = new Select("select valor_parametro from parametro "
                + "where nome_parametro = 'CHAVE'");
        ResultSet rs;
        
        sl.execute();
        
        try {
            rs = sl.getResult();
            rs.first();
            s = rs.getString("valor_parametro");
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
        
        return s;
    }
    
    /**
     * Acessar o código de pc atualmente cadastrada no sistema local.
     * @return o código de pc cadastrado ou uma string vazia em caso 
     * de não haver código cadastrado.
     */
    public String obterPc() {
        String s = "";
        Select sl = new Select("select valor_parametro from parametro "
                + "where nome_parametro = 'PCCOD'");
        ResultSet rs;
        
        sl.execute();
        
        try {
            rs = sl.getResult();
            rs.first();
            s = rs.getString("valor_parametro");
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
        
        return s;
    }
    
    
    /**
     * Acessar o código ATS atualmente cadastrada no sistema local.
     * @return o código ATS cadastrado ou uma string vazia em caso 
     * de não haver código cadastrado.
     */
    public String obterATSCode() {
        String s = "";
        Select sl = new Select("select valor_parametro from parametro "
                + "where nome_parametro = 'ATSCODE'");
        ResultSet rs;
        
        sl.execute();
        
        try {
            rs = sl.getResult();
            rs.first();
            s = rs.getString("valor_parametro");
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
        
        return s;
    }
    
    /**
     * Acessar o código AmosNum atualmente cadastrada no sistema local.
     * @return o código AmosNum cadastrado ou uma string vazia em caso 
     * de não haver código cadastrado.
     */
    public String obterAmosNum() {
        String s = "";
        Select sl = new Select("select valor_parametro from parametro "
                + "where nome_parametro = 'AMOSNUM'");
        ResultSet rs;
        
        sl.execute();
        
        try {
            rs = sl.getResult();
            rs.first();
            s = rs.getString("valor_parametro");
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
        
        return s;
    }
    
    /**
     * Faz o cadastro da nova chave inserida. Este método não valida a chave, 
     * somente cadastra a mesma, que deve ter sido validada por outro objeto.
     * @param c chave a ser cadastrada.
     * @return boolean informado o status da operação.
     */
    public boolean cadastrarChave(String c) {
        boolean ret;
        
        Update u = new Update("PARAMETRO");
        u.setCondition("nome_parametro = 'CHAVE'");
        u.addField("valor_parametro", c, "string");
        ret = u.execute();
        
        return ret;
    }
    
    /**
     * Faz o cadastro do novo pc inserido.
     * @param p código do pc a ser cadastrado.
     * @return boolean informado o status da operação.
     */
    public boolean cadastrarPc(String p) {
        boolean ret;
        
        Update u = new Update("PARAMETRO");
        u.setCondition("nome_parametro = 'PCCOD'");
        u.addField("valor_parametro", p, "string");
        ret = u.execute();
        
        return ret;
    }
    
    
    /**
     * Faz o cadastro do novo ATS.
     * @param ats código ATS a ser cadastrado.
     * @return boolean informado o status da operação.
     */
    public boolean cadastrarATSCode(String ats) {
        boolean ret;
        
        Update u = new Update("PARAMETRO");
        u.setCondition("nome_parametro = 'ATSCODE'");
        u.addField("valor_parametro", ats, "string");
        ret = u.execute();
        
        return ret;
    }
    
    
    /**
     * Faz o cadastro do novo AmosNum.
     * @param amos código AmosNum a ser cadastrado.
     * @return boolean informado o status da operação.
     */
    public boolean cadastrarAmosNum(String amos) {
        boolean ret;
        
        Update u = new Update("PARAMETRO");
        u.setCondition("nome_parametro = 'AMOSNUM'");
        u.addField("valor_parametro", amos, "string");
        ret = u.execute();
        
        return ret;
    }
    
}
