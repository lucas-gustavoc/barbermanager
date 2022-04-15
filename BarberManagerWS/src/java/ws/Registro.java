package ws;

import java.sql.ResultSet;
import ws.db.*;

/**
 *
 * @author lucas
 */
public class Registro {
    
    //String contendo o PC cadastrado. Será vazia caso tenha ocorrido um erro no cadastro
    private String novoPC;
    
    //Chave fornecida para registro do PC
    private final String chave;

    public String getNovoPC() {
        return novoPC;
    }
    
    //Construtor
    public Registro(String chave) {
        novoPC = "0";
        this.chave = chave;
        executarRegistro();
    }
    
    //Execução do registro do PC
    private void executarRegistro() {
        if (buscarChave()) {
            novoPC = registrarPC();
        }
    }
    
    /**
     * Método utilizado para verificar a existência da chave fornecida.
     * @return boolean com a resposta da existência ou não da chave
     */
    private boolean buscarChave() {
        boolean ret = false;
        
        Select s = new Select("select * from CHAVE where chave = '" + chave + 
                "' and ativo_chave = 1 and computador = ''");
        s.execute();
        ResultSet rs = s.getResult();
        
        if (ResultSetToolBox.countRows(rs) > 0) {
            ret = true;
        }
        
        return ret;
    }
    
    /**
     * Método utilizado para registrar o PC. Deve ser chamado somente após 
     * a validação da chave.
     * @return o código do novo PC cadastrado (em caso de sucesso da operação) ou
     * uma String vazia (em caso de falha)
     */
    private String registrarPC() {
        String pc = "0",
               pccod = PcCodGen.obterCod();
        
        Update u = new Update("CHAVE");
        u.setCondition("chave = '" + chave + "'");
        u.addField("computador", pccod, "string");
        
        if (u.execute()) pc = pccod;
        
        return pc;
    }
    
}
