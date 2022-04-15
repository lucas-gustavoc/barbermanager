
package projeto.barbearia.Model;

import java.sql.ResultSet;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class Comissao {
    
    //Campos do BD
    private int profissionalid;
    private int servicoid;
    private float comissao;
    
    //Construtor para novas comissões
    public Comissao(int profissionalid, int servicoid, float comissao) {
        this.profissionalid = profissionalid;
        this.servicoid = servicoid;
        this.comissao = comissao;
    }
    
    //Funções CRUD
    
    // -- Cadastrar
    public boolean cadastrar() {
        
        boolean retorno;
        
        //Criando objeto Insert e definindo tabela de cadastro
        Insert novo = new Insert("COMISSAO_PROFISSIONAL");
        
        //Adicionando campos
        novo.addField("profissionalid", profissionalid, "int");
        novo.addField("servicoid", servicoid, "int");
        novo.addField("porcentagem_comissao", comissao, "float");
        
        //Executando a inserção
        retorno = novo.execute();
        
        return retorno;
        
    }
    
    // -- Remover
    public static boolean remover(int profissionalid, int servicoid) {
        
        //Criando objeto Delete e definindo tabela de cadastro
        Delete murder = new Delete("COMISSAO_PROFISSIONAL");
        
        //Executando deleção
        return murder.deleteOnCondition("profissionalid = " + 
                profissionalid + " and servicoid = " + servicoid);
        
    }
    
    //Buscar Comissões do Profissional
    public static ResultSet buscar(int profissionalid) {
        
        Select sl = new Select("select servico.SERVICOID, servico.NOME_SERVICO, "
                + "comissao_profissional.PORCENTAGEM_COMISSAO from servico, "
                + "comissao_profissional where comissao_profissional.PROFISSIONALID = " + profissionalid
                + " and COMISSAO_PROFISSIONAL.SERVICOID = SERVICO.SERVICOID");
        sl.execute();
        return sl.getResult();
        
    }
    
}
