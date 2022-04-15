package projeto.barbearia.Controller;

import projeto.barbearia.Model.Cliente;
import projeto.barbearia.Model.Tipos.TipoCadastro;

/**
 *
 * @author lucas
 */
public class ouvinteSalvarCliente {
    
    public static boolean salvar(String nome, String fone, String email, 
            java.sql.Date data, int professionalPref, TipoCadastro tipo, int id) {
        
        boolean retorno;
        
        if (tipo == TipoCadastro.NOVO_CADASTRO) {
            Cliente cl = new Cliente();
            cl.setNome(nome.toUpperCase());
            cl.setFone(fone);
            cl.setEmail(email.toUpperCase());
            cl.setNasc(data);
            cl.setProfissionalpref(professionalPref);
            retorno = cl.cadastrar();
        } else {
            retorno = Cliente.editar(id, nome, fone, email, data, professionalPref);
        }
        
        return retorno;
        
    }
    
}
