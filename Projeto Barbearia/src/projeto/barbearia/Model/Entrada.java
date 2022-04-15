
package projeto.barbearia.Model;

import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class Entrada {
    
    //Campos BD
    private String nome_bd;
    private float valor_bd;
    private Date dtpagto_bd;
    private Date dtvcto_bd;
    private int servicoid;
    private int produtoid;
    private int profissionalid;
    private int clienteid;
    private int naturezaid;
    private int qtd_bd;
    
    //Construtor para novas entradas
    public Entrada() {}
    
    //Construtor para entradas existentes
    public Entrada(int id) {
        
        ResultSet rs = null;
        Select s = new Select("select * from entrada where entradaid = " + id);
        
        if (s.execute()) {
            
            rs = s.getResult();
            
            if (ResultSetToolBox.countRows(rs) > 0) {
                
                try {
                    
                    rs.first();
                    
                    nome_bd = rs.getString("nome_entrada");
                    valor_bd = rs.getFloat("valor_entrada");
                    dtpagto_bd = rs.getDate("dtpagto_entrada");
                    dtvcto_bd = rs.getDate("dtvcto_entrada");
                    servicoid = rs.getInt("servicoid");
                    produtoid = rs.getInt("produtoid");
                    profissionalid = rs.getInt("profissionalid");
                    clienteid = rs.getInt("clienteid");
                    naturezaid = rs.getInt("naturezaid");
                    qtd_bd = rs.getInt("qtd_entrada");
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    
    //Funções CRUD
    
    // -- Cadastrar
    public boolean cadastrar() {
        
        Insert n = new Insert("ENTRADA");
        
        n.addField("nome_entrada", nome_bd, "string");
        n.addField("valor_entrada", valor_bd, "float");
        n.addField("dtpagto_entrada", dtpagto_bd, "date");
        n.addField("dtvcto_entrada", dtvcto_bd, "date");
        n.addField("servicoid", servicoid, "int");
        n.addField("produtoid", produtoid, "int");
        n.addField("profissionalid", profissionalid, "int");
        n.addField("clienteid", clienteid, "int");
        n.addField("naturezaid", naturezaid, "int");
        n.addField("qtd_entrada", qtd_bd, "int");
        n.addField("comissao_entrada", calcularComissao(), "float");
        
        return n.execute();
        
    }
    
    //Calculando a comissão para o cadastro
    private float calcularComissao() {
        float r = 0;
        ResultSet rs;
        Select s = new Select("select porcentagem_comissao from comissao_profissional "
                + "where profissionalid = " + profissionalid + " and servicoid = " + servicoid);
        
        s.execute();
        rs = s.getResult();
        
        if (ResultSetToolBox.countRows(rs) > 0) {
            try {
                rs.first();
                r = rs.getFloat("porcentagem_comissao");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            s = new Select("select comissao_servico from servico where servicoid = " + servicoid);
            s.execute();
            rs = s.getResult();
            if (ResultSetToolBox.countRows(rs) > 0) {
                try {
                    rs.first();
                    r = rs.getFloat("comissao_servico");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        
        return r;
    }
    
    // -- Editar
    public static boolean editar(int entradaid, Object nome, Object valor, 
            Object dtpagto, Object dtvcto, Object servicoid, Object produtoid, 
            Object profissionalid, Object clienteid, Object naturezaid, Object qtd) {
        
        Update u = new Update("ENTRADA");
        
        u.setCondition("entradaid = " + entradaid);
        
        if (nome != null) u.addField("nome_entrada", nome, "string");
        if (valor != null) u.addField("valor_entrada", valor, "float");
        if (servicoid != null) u.addField("servicoid", servicoid, "int");
        if (produtoid != null) u.addField("produtoid", produtoid, "int");
        if (profissionalid != null) u.addField("profissionalid", profissionalid, "int");
        if (clienteid != null) u.addField("clienteid", clienteid, "int");
        if (naturezaid != null) u.addField("naturezaid", naturezaid, "int");
        if (qtd != null) u.addField("qtd_entrada", qtd, "int");
        
        //Cuidando das datas
        if (dtpagto != null) {
            u.addField("dtpagto_entrada", dtpagto, "date");
        } else {
            u.addField("dtpagto_entrada", null, "date");
        }
        
        if (dtvcto != null) {
            u.addField("dtvcto_entrada", dtvcto, "date");
        } else {
            u.addField("dtvcto_entrada", null, "date");
        }
        
        return u.execute();
        
    }
    
    // -- Remover
    public static boolean remover(int id) {
        
        boolean retorno = false;
        
        Update d = new Update("ENTRADA");
        
        d.setCondition("entradaid = " + id);
        
        d.addField("ativo_entrada", 0, "int");
        
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
    
    //Buscar entradas
    public static ResultSet buscar(String campos, String condicoes) {
       
        if (!condicoes.equals("")) condicoes = " where " + condicoes;
        Select s = new Select("select " + campos + " from entrada" + condicoes);
        s.execute();
        return s.getResult();
        
    }
    
    //Setters and Getters

    public int getQtd_bd() {
        return qtd_bd;
    }

    public void setQtd_bd(int qtd_bd) {
        this.qtd_bd = qtd_bd;
    }
    
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

    public int getServicoid() {
        return servicoid;
    }

    public void setServicoid(int servicoid) {
        this.servicoid = servicoid;
    }

    public int getProdutoid() {
        return produtoid;
    }

    public void setProdutoid(int produtoid) {
        this.produtoid = produtoid;
    }

    public int getProfissionalid() {
        return profissionalid;
    }

    public void setProfissionalid(int profissionalid) {
        this.profissionalid = profissionalid;
    }

    public int getClienteid() {
        return clienteid;
    }

    public void setClienteid(int clienteid) {
        this.clienteid = clienteid;
    }

    public int getNaturezaid() {
        return naturezaid;
    }

    public void setNaturezaid(int naturezaid) {
        this.naturezaid = naturezaid;
    }
    
    
}
