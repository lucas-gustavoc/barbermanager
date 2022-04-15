package rmk;

import rmk.bdlocal.*;

/**
 * Objeto utilizado para acessar valores de registro cadastrados no BD Local.
 * 
 * @author lucas
 */
public class Conector {
    
    public boolean zerarAtivacao() {
        boolean ret, s1, s2;
        
        Update u = new Update("PARAMETRO");
        u.setCondition("nome_parametro = 'CHAVE'");
        u.addField("valor_parametro", "", "string");
        s1 = u.execute();
        
        u = new Update("PARAMETRO");
        u.setCondition("nome_parametro = 'PCCOD'");
        u.addField("valor_parametro", "", "string");
        s2 = u.execute();
        
        ret = s1 && s2;
        
        return ret;
    }
    
}
