
package projeto.barbearia.Model;

import projeto.barbearia.View.Tabela;

/**
 *
 * @author lucas
 */
public abstract class Relatorio {
    
    protected Tabela tabela;
    
    protected String textoRetorno;

    public abstract void criar();
    
    public Tabela getTabela() {
        return tabela;
    }

    public String getTextoRetorno() {
        return textoRetorno;
    }
    
}
