
package projeto.barbearia.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import projeto.barbearia.Model.db.Delete;
import projeto.barbearia.Model.db.Insert;
import projeto.barbearia.Model.db.Select;
import projeto.barbearia.Model.db.Update;

/**
 *
 * @author lucas
 */
public class Horario {
    
    //Variáveis BD
    private Date data_bd;
    private Time hora_bd;
    private int horarioid;
    private int clienteid;
    private int servicoid;
    private int profissionalid;
    private String nomeServico;
    private String nomeCliente;
    private String nomeProfissional;
    
    //Construtor 1
    public Horario() {}
    
    //Construtor 2
    public Horario(int id) {
        
        ResultSet rs = null;
        Select s = new Select("select horario.*, nome_servico, nome_cliente, nome_profissional "
                + "from horario, servico, cliente, profissional where horarioid = " + id + " and "
                        + "horario.clienteid = cliente.clienteid and horario.servicoid = "
                        + "servico.servicoid and horario.profissionalid = profissional.profissionalid");
        horarioid = id;
        
        if (s.execute()) {
            
            rs = s.getResult();
            
            if (ResultSetToolBox.countRows(rs) > 0) {
                
                try {
                    
                    rs.first();
                    
                    data_bd = rs.getDate("data_horario");
                    hora_bd = rs.getTime("hora_horario");
                    clienteid = rs.getInt("clienteid");
                    servicoid = rs.getInt("servicoid");
                    profissionalid = rs.getInt("profissionalid");
                    nomeCliente = rs.getString("nome_cliente");
                    nomeProfissional = rs.getString("nome_profissional");
                    nomeServico = rs.getString("nome_servico");
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        
    }
    
    //Funções CRUD
    
    // -- Cadastrar
    public boolean cadastrar() {
        
        Insert n = new Insert("HORARIO");
        
        n.addField("data_horario", data_bd, "date");
        n.addField("hora_horario", hora_bd, "time");
        n.addField("clienteid", clienteid, "int");
        n.addField("servicoid", servicoid, "int");
        n.addField("profissionalid", profissionalid, "int");
        
        return n.execute();
        
    }
    
    // -- Editar
    public static boolean editar(int horarioid, Object data, Object hora, 
            Object clienteid, Object servicoid, Object profissionalid) {
        
        Update u = new Update("HORARIO");
        
        u.setCondition("horarioid = " + horarioid);
        
        if (data != null) u.addField("data_horario", data, "date");
        if (hora != null) u.addField("hora_horario", hora, "time");
        if (clienteid != null) u.addField("clienteid", clienteid, "int");
        if (servicoid != null) u.addField("servicoid", servicoid, "int");
        if (profissionalid != null) u.addField("profissionalid", profissionalid, "int");
        
        return u.execute();
        
    }
    
    // -- Remover
    public static boolean remover(int horarioid) {
        
        //Criando objeto Delete e definindo tabela de cadastro
        Delete murder = new Delete("HORARIO");
        
        //Executando deleção
        return murder.deleteOnCondition("horarioid = " + horarioid);
        
    }
    
    // -- Remover sem perguntar
    public static boolean remover(int horarioid, boolean force) {
        
        //Criando objeto Delete e definindo tabela de cadastro
        Delete murder = new Delete("HORARIO");
        
        //Executando deleção
        return murder.deleteOnCondition("horarioid = " + horarioid, force);
        
    }
    
    //Buscar horários
    public static ResultSet buscar(String campos, String condicoes) {
       
        if (!condicoes.equals("")) condicoes = " where " + condicoes;
        Select s = new Select("select " + campos + " from horario" + condicoes);
        s.execute();
        return s.getResult();
        
    }
    
    //Buscar horários para tabela
    public static ResultSet buscarParaTabela(Date d) {
        
        ResultSet rs = null;
        
        if (d != null) {
            String stt = "select horario.horarioid, horario.clienteid, horario.PROFISSIONALID, "
                    + "horario.HORA_HORARIO, profissional.NOME_PROFISSIONAL, cliente.NOME_CLIENTE, "
                    + "servico.NOME_SERVICO from cliente, profissional, horario, servico "
                    + "where horario.profissionalid = profissional.PROFISSIONALID "
                    + "and horario.clienteid = cliente.clienteid "
                    + "and horario.servicoid = servico.servicoid "
                    + "and horario.data_horario = '" + d.toString() + "' order by hora_horario";
            Select s = new Select(stt);
            
            s.execute();
            rs = s.getResult();
        } else {
            throw new java.lang.IllegalArgumentException();
        }
        
        return rs;
        
    }
    
    /**
     * Método usado para verificar se há cliente marcado com o profissional, 
     * na dada data e na dada hora.
     * 
     * @param hora a hora a ser buscada deve ser passada no formato hh:mm
     * @param data a data a ser buscada deve ser passada no formato yyyy-MM-dd
     * @param prof  id do profissional em questão
     * @return retorna o id do horário marcado e, caso não haja cliente marcado, retorna -1.
     */
    public static int encontrarHorarioMarcado(String hora, String data, int prof) {
        
        Select s = new Select("select horarioid from horario where "
                + "hora_horario = '" + hora + ":00' and profissionalid = " 
                + prof + " and data_horario = '" + data + "'");
        ResultSet rs;
        int retorno = -1;
        
        s.execute();
        rs = s.getResult();
        
        int rsCount = ResultSetToolBox.countRows(rs);
        
        if (rsCount > 0) {
            
            try {
                rs.first();
                retorno = rs.getInt("horarioid");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return retorno;
    }
    
    //Getters and Setters

    public int getHorarioid() {
        return horarioid;
    }
    
    public Date getData_bd() {
        return data_bd;
    }
    
    public String getData_bfFormatado() {
        String retorno = "";
        
        if (data_bd != null) {
            FormatadorParaSQLData f = new FormatadorParaSQLData();
            retorno = f.reverse(data_bd.toString());
        }
        
        return retorno;
    }

    public void setData_bd(Date data_bd) {
        this.data_bd = data_bd;
    }

    public Time getHora_bd() {
        return hora_bd;
    }
    
    public String getHora_bdFormatado() {
        String retorno = "";
        
        if (hora_bd != null) {
            int colonIndex;
            String s = hora_bd.toString();
            colonIndex = s.indexOf(":");
            colonIndex = s.indexOf(":", colonIndex + 1);
            retorno = s.substring(0, colonIndex);
        }
        
        return retorno;
    }

    public void setHora_bd(Time hora_bd) {
        this.hora_bd = hora_bd;
    }

    public int getClienteid() {
        return clienteid;
    }

    public void setClienteid(int clienteid) {
        this.clienteid = clienteid;
    }

    public int getServicoid() {
        return servicoid;
    }

    public void setServicoid(int servicoid) {
        this.servicoid = servicoid;
    }

    public int getProfissionalid() {
        return profissionalid;
    }

    public void setProfissionalid(int profissionalid) {
        this.profissionalid = profissionalid;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }
    
    
    
}
