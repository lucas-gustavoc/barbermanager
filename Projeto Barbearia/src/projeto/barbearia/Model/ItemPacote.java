
package projeto.barbearia.Model;

import java.sql.ResultSet;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class ItemPacote {
    
    //Campos do BD
    private int pacoteid;
    private int servicoid;
    private int qtd;
    private int possivelQtdExistente;
    
    /**
     * Bom, aqui temos algo que podemos chamar de JavaDOC.
     * Que legal podermos fazer isso, fico tão feliz!
     * 
     * @param pacoteid significa, obviamente, o ID do pacote 
     *                  no qual você cadastrará o item.
     * 
     * @param servicoid significa o ID do serviço que será 
     *                  incluído como item do seu pacote.
     * 
     * @param qtd ... acho que não preciso dizer nada...
     */
    public ItemPacote(int pacoteid, int servicoid, int qtd) {
        this.pacoteid = pacoteid;
        this.servicoid = servicoid;
        this.qtd = qtd;
    }
    
    //Funções CRUD
    
    // -- Cadastrar
    public boolean cadastrar() {
        
        boolean retorno;
        
        if (!itemExiste()) {
            //Criando objeto Insert e definindo tabela de cadastro
            Insert novo = new Insert("ITEMPACOTE");

            //Adicionando campos
            novo.addField("pacoteid", pacoteid, "int");
            novo.addField("servicoid", servicoid, "int");
            novo.addField("qtd_itempacote", qtd, "int");

            //Executando a inserção
            retorno = novo.execute();
        } else {
            retorno = cadastrarItemExistente();
        }
        
        return retorno;
        
    }
    
    //Caso o item já exista no pacote, vamos adicionar a quantidade à atual
    
    //-- Verificando se o item existe
    private boolean itemExiste() {
        
        boolean retorno = false;
        int count;
        ResultSet rs;
        Select sl = new Select("select * from itempacote where pacoteid = " 
                + this.pacoteid + " and servicoid = " + this.servicoid);
        
        sl.execute();
        rs = sl.getResult();
        count = ResultSetToolBox.countRows(rs);
        
        if (count > 0) {
            retorno = true;
            
            try {
                rs.first();
                this.possivelQtdExistente = rs.getInt("qtd_itempacote");
            } catch (Exception e) {
                this.possivelQtdExistente = 0;
            }
        }
        
        return retorno;
        
    }
    
    //-- Adicionando a quantidade
    private boolean cadastrarItemExistente() {
       
        int novaQtd = this.qtd + this.possivelQtdExistente;
        
        //Criando objeto Update e definindo tabela de cadastro
        Update up = new Update("ITEMPACOTE");
        
        //Definindo condição de atualização
        up.setCondition("pacoteid = " + this.pacoteid + " and servicoid = " + this.servicoid);
        
        //Adicionando campo
        up.addField("qtd_itempacote", novaQtd, "int");

        //Executando a atualização
        return up.execute();
        
    }
    
    // -- Remover
    public static boolean remover(int itempacoteid) {
        
        //Criando objeto Delete e definindo tabela de cadastro
        Delete murder = new Delete("ITEMPACOTE");
        
        //Executando deleção
        return murder.deleteOnCondition("itempacoteid = " + itempacoteid);
        
    }
    
    //Buscar Comissões do Profissional
    public static ResultSet buscar(int pacoteid) {
        
        Select sl = new Select("select itempacote.ITEMPACOTEID, "
                + "servico.NOME_SERVICO, itempacote.QTD_ITEMPACOTE "
                + "from itempacote, servico where itempacote.PACOTEID = " + pacoteid
                + " and servico.servicoid = itempacote.servicoid");
        sl.execute();
        return sl.getResult();
        
    }
    
}
