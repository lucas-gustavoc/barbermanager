package ws;

import ws.db.*;

/**
 *
 * @author lucas
 */
public class Acesso {
    
    //PC fornecido para verificação de acesso
    private final String pc;
    
    //chave fornecida para verificação de acesso
    private final String chave;
    
    //Status do acesso
    private boolean acessoValido;

    /**
     * Método utilizado para retornar o status da validação.
     * @return String "valid" para acesso validado e "block" para o contrário.
     */
    public String getStatus() {
        return (acessoValido) ? "valid" : "block";
    }
    
    //Construtor
    public Acesso(String chave, String pc) {
        this.chave = chave;
        this.pc = pc;
        validarAcesso();
    }
    
    //Método de validação do acesso. O status é armazenado na variável acessoValido
    private void validarAcesso() {
        boolean val = false;
        
        Select s = new Select("select * from chave where chave = '" + 
                chave + "' and computador = '" + pc + "' and ativo_chave = 1");
        s.execute();
        
        if (ResultSetToolBox.countRows(s.getResult()) > 0) val = true;
        
        acessoValido = val;
    }
    
}
