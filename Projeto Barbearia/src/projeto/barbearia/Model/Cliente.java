package projeto.barbearia.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import projeto.barbearia.Model.Tipos.SituacaoItemPacoteCliente;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class Cliente {
    
    //Campos do BD
    private String nome;
    private String fone;
    private String email;
    private Date nasc;
    private int profissionalpref;
    
    //Construtor para novos clientes
    public Cliente() {}
    
    //Construtor para um cliente existente
    public Cliente(int id) {
        
        ResultSet rs;
        
        rs = buscar("*", "clienteid = " + Integer.toString(id));
        
        try {
            //rs.first();
            rs.next();
            
            nome = rs.getString("nome_cliente");
            fone = rs.getString("fone_cliente");
            email = rs.getString("email_cliente");
            nasc = rs.getDate("nasc_cliente");
            profissionalpref = rs.getInt("profissionalprefid_cliente");
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    //Funções CRUD
    
    // -- Cadastrar
    public boolean cadastrar() {
        
        boolean retorno;
        
        //Criando objeto Insert e definindo tabela de cadastro
        Insert novo = new Insert("CLIENTE");
        
        //Adicionando campos
        novo.addField("nome_cliente", nome, "string");
        novo.addField("fone_cliente", fone, "string");
        novo.addField("email_cliente", email, "string");
        novo.addField("nasc_cliente", nasc, "date");
        novo.addField("profissionalprefid_cliente", profissionalpref, "int");
        
        //Executando a inserção
        retorno = novo.execute();
        
        return retorno;
    }
    
    // -- Editar
    public static boolean editar(int clienteid, Object nome, Object fone, 
            Object email, Object nascimento, Object profissionalpref) {
        
        boolean retorno;
        
        //Criando objeto Update e definindo tabela de cadastro
        Update novo = new Update("CLIENTE");
        
        //Adicionando condição de edição
        novo.setCondition("clienteid = " + Integer.toString(clienteid));
        
        //Adicionando campos
        if (nome != null) novo.addField("nome_cliente", nome, "string");
        if (fone != null) novo.addField("fone_cliente", fone, "string");
        if (email != null) novo.addField("email_cliente", email, "string");
        if (profissionalpref != null) novo.addField("profissionalprefid_cliente", profissionalpref, "int");
        
        //Cuidando da data
        if (nascimento != null) {
            novo.addField("nasc_cliente", nascimento, "date");
        } else {
            novo.addField("nasc_cliente", null, "date");
        }
        
        //Executando a edição
        retorno = novo.execute();
        
        return retorno;
    }
    
    
    // -- Remover
    public static boolean remover(int clienteid) {
        
        /*
        -- Há um segredo aqui. Ninguém irá remover nada.
        -- Vamos apenas definir esse cliente como inativo.
        -- Logo logo, o coletor de lixo é que vai excluí-lo, pra valer.
        */
        
        boolean retorno = false;
        
        //Criando objeto Update e definindo tabela de cadastro
        Update targetToKill = new Update("CLIENTE");
        
        //Especificando o cliente a ser "removido"
        targetToKill.setCondition("clienteid = " + Integer.toString(clienteid));
        
        //Realizando a alteração do estado para inativo
        targetToKill.addField("ativo_cliente", 0, "int");
        
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
    
    //Buscar Clientes
    public static ResultSet buscar(String campos, String condicao) {
        
        if (!condicao.equals("")) condicao = " where " + condicao;
        Select sl = new Select("select " + campos + " from cliente" + condicao);
        sl.execute();
        return sl.getResult();
        
    }
    
    //Obter último cliente cadastrado
    public static int obterUltimoClienteCadastrado() {
        
        int retorno = 0;
        ResultSet rs;
        Select s = new Select("select clienteid from cliente order by clienteid desc");
        s.execute();
        rs = s.getResult();
        
        try {
            if (ResultSetToolBox.countRows(rs) > 0) {
                rs.first();
                retorno = rs.getInt("clienteid");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return retorno;
        
    }
    
    //Adicionar itens de pacote para o cliente
    public static boolean adicionarItensPacoteAoCliente(int clienteid, int pacoteid, int qtd) {
        
        boolean retorno = false;
        String[][] itens = Servico.obterItensDeUmPacote(pacoteid);
        
        for (String[] item : itens) {
            int itemExistente = verificarSeItemJaExisteNoPacote(clienteid, Integer.valueOf(item[0]));
            
            if (itemExistente > 0) {
                //Item já existe, vamos apenas atualizar a quantidade
                SQLQuery sq = new SQLQuery("update pctcliente set "
                        + "qtd_pctcliente = qtd_pctcliente + " + (Integer.valueOf(item[2]) * qtd) + 
                        " where pctclienteid = " + itemExistente);
                retorno = sq.execute();
            } else {
                //Item ainda não existe, vamos cadastra-lo
                Insert i = new Insert("PCTCLIENTE");
                i.addField("clienteid", clienteid, "int");
                i.addField("servicoid", Integer.valueOf(item[0]), "int");
                i.addField("nomeservico_pctcliente", item[1], "string");
                i.addField("qtd_pctcliente", Integer.valueOf(item[2]) * qtd, "int");
                retorno = i.execute();
            }
        }
        
        return retorno;
        
    }
    
    //método usado em conjunto com o adicionarItensPacoteAoCliente
    //Retorna -1 se o item não existe ou, caso exista, retorna o id do item
    private static int verificarSeItemJaExisteNoPacote(int clienteid, int servicoid) {
        int retorno = -1;
        
        ResultSet rs;
        Select s = new Select("select pctclienteid from pctcliente where "
                + "clienteid = " + clienteid + " and servicoid = " + servicoid);
        
        s.execute();
        rs = s.getResult();
        
        try {
            if (ResultSetToolBox.countRows(rs) > 0) {
                rs.first();
                retorno = rs.getInt("pctclienteid");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return retorno;
    }
    
    //Obter lista de serviços que o cliente comprou em pacotes
    public static String[][] obterListaServicosPacote(int clienteid) {
        String[][] s = null;
        
        Select sl = new Select("select nomeservico_pctcliente, qtd_pctcliente "
                + "from pctcliente where clienteid = " + clienteid);
        
        if (sl.execute()) {
            ResultSet rs = sl.getResult();
            int count = ResultSetToolBox.countRows(rs);
            
            if (count > 0) {
                s = new String[count][2];
                count = 0;
                try {
                    rs.beforeFirst();
                    while (rs.next()) {
                        s[count][0] = rs.getString("nomeservico_pctcliente");
                        s[count][1] = rs.getString("qtd_pctcliente");
                        count++;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        
        return s;
    }
    
    //Obter lista de clientes que possuem pacote
    public static int[] obterClientesComPacote() {
        int r[] = new int[] {0};
        
        Select sl = new Select("select clienteid from pctcliente group by clienteid");
        
        if (sl.execute()) {
            ResultSet rs = sl.getResult();
            int count = ResultSetToolBox.countRows(rs);
            
            if (count > 0) {
                r = new int[count];
                count = 0;
                try {
                    rs.beforeFirst();
                    while (rs.next()) {
                        r[count] = rs.getInt("clienteid");
                        count++;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        
        return r;
    }
    
    public static void deduzirItemPacote(int clienteid, int servicoid, int qtd) {
        int itemExistente = verificarSeItemJaExisteNoPacote(clienteid, servicoid);
        
        if (itemExistente > -1) {
            SQLQuery sq = new SQLQuery("update pctcliente set "
                            + "qtd_pctcliente = qtd_pctcliente - " + qtd + 
                            " where pctclienteid = " + itemExistente);
            sq.execute();
            
            /**
            * 
            * EXCLUIR CASO NECESSÁRIO
            * 
            */
            
            int novaQtd = obterQtdItensPacote(clienteid, servicoid);
            if (novaQtd <= 0) {
                sq = new SQLQuery("delete from pctcliente " + 
                            " where pctclienteid = " + itemExistente);
                sq.execute();
            } 
            
        }
        
    }

    public static SituacaoItemPacoteCliente verificarSeTemItemNoPacote(int clienteid, int servicoid, int qtd) {
        SituacaoItemPacoteCliente ret;
        
        int qtdAtual = obterQtdItensPacote(clienteid, servicoid);
        
        if (qtdAtual >= qtd) {
            //Possui o suficiente
            ret = SituacaoItemPacoteCliente.POSSUI_EM_QTD_MAIOR;
        } else if (qtdAtual == -1) {
            //Não possui
            ret = SituacaoItemPacoteCliente.NAO_POSSUI;
        } else {
            //Possui, mas não o suficiente
            ret = SituacaoItemPacoteCliente.POSSUI_EM_QTD_MENOR;
        }
        
        return ret;
    }

    public static int obterQtdItensPacote(int clienteid, int servicoid) {
        int retorno = -1;
        Select s = new Select("select qtd_pctcliente from pctcliente "
                + "where clienteid = " + clienteid + " and servicoid = " + servicoid);
        
        s.execute();
        
        ResultSet rs = s.getResult();
        
        if (ResultSetToolBox.countRows(rs) > 0) {
            try {
                rs.first();
                retorno = rs.getInt("qtd_pctcliente");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return retorno;
    }
    
    //Getters e Setters dos Campos do BD
    public String getNome() {
        return nome;
    }

    public String getFone() {
        return fone;
    }

    public String getEmail() {
        return email;
    }

    public Date getNasc() {
        return nasc;
    }

    public int getProfissionalpref() {
        return profissionalpref;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNasc(Date nasc) {
        this.nasc = nasc;
    }

    public void setProfissionalpref(int profissionalpref) {
        this.profissionalpref = profissionalpref;
    }
    
    
    
}
