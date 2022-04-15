
package projeto.barbearia.Controller;

import projeto.barbearia.Model.Profissional;
import projeto.barbearia.Model.Tipos.TipoCadastro;

/**
 *
 * @author lucas
 */
public class ouvinteSalvarProfissional {
    
    public static boolean salvar(String nome, String fone1, String fone2, String email, 
            java.sql.Date data, String folga, TipoCadastro tipo, int id) {
        
        boolean retorno;
        
        if (tipo == TipoCadastro.NOVO_CADASTRO) {
            Profissional pro = new Profissional();
            pro.setNome(nome.toUpperCase());
            pro.setFone1(fone1);
            pro.setFone2(fone2);
            pro.setEmail(email.toUpperCase());
            pro.setNasc(data);
            pro.setFolga(folga.toUpperCase());
            retorno = pro.cadastrar();
        } else {
            retorno = Profissional.editar(id, nome, fone1, fone2, email, data, folga);
        }
        
        return retorno;
        
    }
    
}
