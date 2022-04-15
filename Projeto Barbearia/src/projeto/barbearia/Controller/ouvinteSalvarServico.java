
package projeto.barbearia.Controller;

import projeto.barbearia.Model.Servico;
import projeto.barbearia.Model.Tipos.TipoCadastro;

/**
 *
 * @author lucas
 */
public class ouvinteSalvarServico {
    
    public static boolean salvar(String nome, float valor, float comissao, 
            String tipoServico, TipoCadastro tipo, int id) {
        
        boolean retorno;
        
        if (tipo == TipoCadastro.NOVO_CADASTRO) {
            Servico srv = new Servico();
            srv.setNome(nome.toUpperCase());
            srv.setValor(valor);
            srv.setComissao(comissao);
            srv.setTipo(tipoServico.toUpperCase());
            retorno = srv.cadastrar();
        } else {
            retorno = Servico.editar(id, nome, valor, comissao, tipoServico);
        }
        
        return retorno;
        
    }
    
}
