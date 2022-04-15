
package projeto.barbearia.Model;

import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class Saida {
    
    //Campos BD
    private String nome_bd;
    private float valor_bd;
    private Date dtpagto_bd;
    private Date dtvcto_bd;
    private int fornecedorid;
    private int profissionalid;
    private int naturezaid;
    
    //Construtor para novas entradas
    public Saida() {}
    
    //Construtor para entradas existentes
    public Saida(int id) {
        
        ResultSet rs = null;
        Select s = new Select("select * from saida where saidaid = " + id);
        
        if (s.execute()) {
            
            rs = s.getResult();
            
            if (ResultSetToolBox.countRows(rs) > 0) {
                
                try {
                    
                    rs.first();
                    
                    nome_bd = rs.getString("nome_saida");
                    valor_bd = rs.getFloat("valor_saida");
                    dtpagto_bd = rs.getDate("dtpagto_saida");
                    dtvcto_bd = rs.getDate("dtvcto_saida");
                    profissionalid = rs.getInt("profissionalid");
                    fornecedorid = rs.getInt("fornecedorid");
                    naturezaid = rs.getInt("naturezaid");
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    
    //Funções CRUD
    
    // -- Cadastrar
    public boolean cadastrar() {
        
        Insert n = new Insert("SAIDA");
        
        n.addField("nome_saida", nome_bd, "string");
        n.addField("valor_saida", valor_bd, "float");
        n.addField("dtpagto_saida", dtpagto_bd, "date");
        n.addField("dtvcto_saida", dtvcto_bd, "date");
        n.addField("profissionalid", profissionalid, "int");
        n.addField("fornecedorid", fornecedorid, "int");
        n.addField("naturezaid", naturezaid, "int");
        
        return n.execute();
        
    }
    
    // -- Editar
    public static boolean editar(int saidaid, Object nome, Object valor, 
            Object dtpagto, Object dtvcto, Object profissionalid, 
            Object fornecedorid, Object naturezaid) {
        
        Update u = new Update("SAIDA");
        
        u.setCondition("saidaid = " + saidaid);
        
        if (nome != null) u.addField("nome_saida", nome, "string");
        if (valor != null) u.addField("valor_saida", valor, "float");
        if (profissionalid != null) u.addField("profissionalid", profissionalid, "int");
        if (fornecedorid != null) u.addField("fornecedorid", fornecedorid, "int");
        if (naturezaid != null) u.addField("naturezaid", naturezaid, "int");
        
        //Cuidando das datas
        if (dtpagto != null) {
            u.addField("dtpagto_saida", dtpagto, "date");
        } 
        else {
            u.addField("dtpagto_saida", null, "date");
        }
        
        if (dtvcto != null) {
            u.addField("dtvcto_saida", dtvcto, "date");
        } else {
            u.addField("dtvcto_saida", null, "date");
        }
        
        return u.execute();
        
    }
    
    // -- Remover
    public static boolean remover(int id) {
        
        boolean retorno = false;
        
        Update d = new Update("SAIDA");
        
        d.setCondition("saidaid = " + id);
        
        d.addField("ativo_saida", 0, "int");
        
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
        
        if (n == JOptionPane.YES_OPTION) retorno = d.execute();
        
        return retorno;
        
    }
    
    //Buscar saídas
    public static ResultSet buscar(String campos, String condicoes) {
       
        if (!condicoes.equals("")) condicoes = " where " + condicoes;
        Select s = new Select("select " + campos + " from saida" + condicoes);
        s.execute();
        return s.getResult();
        
    }
    
    //Buscar saídas com ordenação
    public static ResultSet buscar(String campos, String condicoes, String orderBy) {
       
        if (!condicoes.equals("")) condicoes = " where " + condicoes;
        if (!orderBy.equals("")) orderBy = "order by " + orderBy;
        Select s = new Select("select " + campos + " from saida" + condicoes + orderBy);
        s.execute();
        return s.getResult();
        
    }
    
    //Setters and Getters

    public String getNome_bd() {
        return nome_bd;
    }

    public void setNome_bd(String nome_bd) {
        this.nome_bd = nome_bd;
    }

    public float getValor_bd() {
        return valor_bd;
    }

    public void setValor_bd(float valor_bd) {
        this.valor_bd = valor_bd;
    }

    public Date getDtpagto_bd() {
        return dtpagto_bd;
    }

    public void setDtpagto_bd(Date dtpagto_bd) {
        this.dtpagto_bd = dtpagto_bd;
    }

    public Date getDtvcto_bd() {
        return dtvcto_bd;
    }

    public void setDtvcto_bd(Date dtvcto_bd) {
        this.dtvcto_bd = dtvcto_bd;
    }

    public int getFornecedorid() {
        return fornecedorid;
    }

    public void setFornecedorid(int fornecedorid) {
        this.fornecedorid = fornecedorid;
    }

    public int getProfissionalid() {
        return profissionalid;
    }

    public void setProfissionalid(int profissionalid) {
        this.profissionalid = profissionalid;
    }

    public int getNaturezaid() {
        return naturezaid;
    }

    public void setNaturezaid(int naturezaid) {
        this.naturezaid = naturezaid;
    }
    
    
    
}
