
package projeto.barbearia.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class Profissional {
    
    //Campos do BD
    private String nome;
    private String fone1;
    private String fone2;
    private String email;
    private Date nasc;
    private String folga;
    
    //Construtor para novos profissionais
    public Profissional() {}
    
    //Construtor para um profissional existente
    public Profissional(int id) {
        
        ResultSet rs;
        
        rs = buscar("*", "profissionalid = " + Integer.toString(id));
        
        try {
            //rs.first();
            rs.next();
            
            nome = rs.getString("nome_profissional");
            fone1 = rs.getString("fone1_profissional");
            fone2 = rs.getString("fone2_profissional");
            email = rs.getString("email_profissional");
            nasc = rs.getDate("nasc_profissional");
            folga = rs.getString("folga_profissional");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Funções CRUD
    
    // -- Cadastrar
    public boolean cadastrar() {
        
        boolean retorno;
        
        //Criando objeto Insert e definindo tabela de cadastro
        Insert novo = new Insert("PROFISSIONAL");
        
        //Adicionando campos
        novo.addField("nome_profissional", nome, "string");
        novo.addField("fone1_profissional", fone1, "string");
        novo.addField("fone2_profissional", fone2, "string");
        novo.addField("email_profissional", email, "string");
        novo.addField("nasc_profissional", nasc, "date");
        novo.addField("folga_profissional", folga, "string");
        
        //Executando a inserção
        retorno = novo.execute();
        
        return retorno;
    }
    
    // -- Editar
    public static boolean editar(int profissionalid, Object nome, Object fone1, 
            Object fone2, Object email, Object nascimento, Object folga) {
        
        boolean retorno;
        
        //Criando objeto Update e definindo tabela de cadastro
        Update novo = new Update("PROFISSIONAL");
        
        //Adicionando condição de edição
        novo.setCondition("profissionalid = " + Integer.toString(profissionalid));
        
        //Adicionando campos
        if (nome != null) novo.addField("nome_profissional", nome, "string");
        if (fone1 != null) novo.addField("fone1_profissional", fone1, "string");
        if (fone2 != null) novo.addField("fone2_profissional", fone2, "string");
        if (email != null) novo.addField("email_profissional", email, "string");
        if (folga != null) novo.addField("folga_profissional", folga, "string");
        
        //Cuidando da data
        if (nascimento != null) {
            novo.addField("nasc_profissional", nascimento, "date");
        } else {
            novo.addField("nasc_profissional", null, "date");
        }
        
        //Executando a edição
        retorno = novo.execute();
        
        return retorno;
    }
    
    // -- Remover
    public static boolean remover(int profissionalid) {
        
        /*
        -- Há um segredo aqui. Ninguém irá remover nada.
        -- Vamos apenas definir esse profissional como inativo.
        -- Logo logo, o coletor de lixo é que vai excluí-lo, pra valer.
        */
        
        boolean retorno = false;
        
        //Criando objeto Update e definindo tabela de cadastro
        Update targetToKill = new Update("PROFISSIONAL");
        
        //Especificando o profissional a ser "removido"
        targetToKill.setCondition("profissionalid = " + Integer.toString(profissionalid));
        
        //Realizando a alteração do estado para inativo
        targetToKill.addField("ativo_profissional", 0, "int");
        
        //Showing confirmation message
        Object[] options = {"Sim, excluir",
                            "Não, cancelar"};
        int n = JOptionPane.showOptionDialog(null,
            "Você tem certeza que deseja excluir este registro?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            options,
            options[0]);

        //deleting if agreed
        if (n == JOptionPane.YES_OPTION) {
            //Executando a remoção
            retorno = targetToKill.execute();
        }
        
        return retorno;
    }
    
    //Buscar Profissionais
    public static ResultSet buscar(String campos, String condicao) {
        
        if (!condicao.equals("")) condicao = " where " + condicao;
        Select sl = new Select("select " + campos + " from profissional" + condicao);
        sl.execute();
        return sl.getResult();
        
    }
    
    //Comissões Personalizadas
    
    //Buscando comissões personalizadas
    public static ResultSet buscarComissoesPersonalizadas(int profissionalid) {
        
        return Comissao.buscar(profissionalid);
        
    }
    
    //Adicionando comissões personalizadas
    public static boolean adicionarComissaoPersonalizada(
            int profissionalid, int servicoid, float comissao) {
        
        Comissao nova = new Comissao(profissionalid, servicoid, comissao);
        
        return nova.cadastrar();
        
    }
    
    //Removendo comissões personalizadas
    public static boolean removerComissaoPersonalizada(
            int profissionalid, int servicoid) {
        
        return Comissao.remover(profissionalid, servicoid);
        
    }
    
    //getters and setters

    public String getNome() {
        return nome;
    }

    public String getFone1() {
        return fone1;
    }

    public String getFone2() {
        return fone2;
    }

    public String getEmail() {
        return email;
    }

    public Date getNasc() {
        return nasc;
    }

    public String getFolga() {
        return folga;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNasc(Date nasc) {
        this.nasc = nasc;
    }

    public void setFolga(String folga) {
        this.folga = folga;
    }
    
    
}
