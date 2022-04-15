
package projeto.barbearia.Controller;

import projeto.barbearia.Model.Fornecedor;
import projeto.barbearia.Model.Tipos.TipoCadastro;

/**
 *
 * @author lucas
 */
public class ouvinteSalvarFornecedor {
    
    public static boolean salvar(TipoCadastro tipo, String nome, String nomectt, 
            String fone, String email, int fid) {
        
        boolean retorno;
        
        if (tipo == TipoCadastro.NOVO_CADASTRO) {
            Fornecedor f = new Fornecedor();
            f.setNome_bd(nome.toUpperCase());
            f.setNomectt_bd(nomectt.toUpperCase());
            f.setFone_bd(fone);
            f.setEmail_bd(email.toUpperCase());
            retorno = f.cadastrar();
        } else {
            retorno = Fornecedor.editar(fid, nome, nomectt, fone, email);
        }
        
        return retorno;
        
    }
    
}
