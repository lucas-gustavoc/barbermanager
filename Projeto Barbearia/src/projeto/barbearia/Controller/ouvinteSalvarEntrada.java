
package projeto.barbearia.Controller;

import projeto.barbearia.Model.Entrada;
import projeto.barbearia.Model.Tipos.TipoCadastro;
import java.sql.Date;

/**
 *
 * @author lucas
 */
public class ouvinteSalvarEntrada {
    
    public static boolean onClickSalvarEntrada(TipoCadastro tipo, String nome, float valor, 
            Date dtpagto, Date dtvcto, int servicoid, int produtoid, 
            int profissionalid, int clienteid, int naturezaid, int entradaid) {
        
        boolean retorno;
        
        if (tipo == TipoCadastro.NOVO_CADASTRO) {
            Entrada e = new Entrada();
            e.setNome_bd(nome);
            e.setValor_bd(valor);
            e.setDtpagto_bd(dtpagto);
            e.setDtvcto_bd(dtvcto);
            e.setServicoid(servicoid);
            e.setProdutoid(produtoid);
            e.setProfissionalid(profissionalid);
            e.setClienteid(clienteid);
            e.setNaturezaid(naturezaid);
            retorno = e.cadastrar();
        } else {
            retorno = Entrada.editar(entradaid, nome, valor, dtpagto, dtvcto, 
                    servicoid, produtoid, profissionalid, clienteid, naturezaid, 1);
        }
        
        return retorno;
        
    }
    
}
