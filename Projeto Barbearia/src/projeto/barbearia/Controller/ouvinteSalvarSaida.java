
package projeto.barbearia.Controller;

import projeto.barbearia.Model.Saida;
import projeto.barbearia.Model.Tipos.TipoCadastro;
import java.sql.Date;

/**
 *
 * @author lucas
 */
public class ouvinteSalvarSaida {
    
    public static boolean onClickSalvarSaida(TipoCadastro tipo, String nome, float valor, 
            Date dtpagto, Date dtvcto, int fornecedorid, int profissionalid, int naturezaid, int saidaid) {
        
        boolean retorno;
        
        if (tipo == TipoCadastro.NOVO_CADASTRO) {
            Saida s = new Saida();
            s.setNome_bd(nome);
            s.setValor_bd(valor);
            s.setDtpagto_bd(dtpagto);
            s.setDtvcto_bd(dtvcto);
            s.setFornecedorid(fornecedorid);
            s.setProfissionalid(profissionalid);
            s.setNaturezaid(naturezaid);
            retorno = s.cadastrar();
        } else {
            retorno = Saida.editar(saidaid, nome, valor, dtpagto, dtvcto, profissionalid, fornecedorid, naturezaid);
        }
        
        return retorno;
        
    }
    
}
