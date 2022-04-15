/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class Fornecedor {
    
    //Campos do BD
    private String nome_bd;
    private String nomectt_bd;
    private String fone_bd;
    private String email_bd;
    
    //Construtor para novos Fornecedores
    public Fornecedor() {}
    
    //Construtor para um Fornecedor existente
    public Fornecedor(int id) {
        
        ResultSet rs;
        
        rs = buscar("*", "fornecedorid = " + Integer.toString(id));
        
        try {
            //rs.first();
            rs.next();
            
            nome_bd = rs.getString("nome_fornecedor");
            nomectt_bd = rs.getString("nomectt_fornecedor");
            fone_bd = rs.getString("fone_fornecedor");
            email_bd = rs.getString("email_fornecedor");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    //Funções CRUD
    
    // -- Cadastrar
    public boolean cadastrar() {
        
        boolean retorno;
        
        //Criando objeto Insert e definindo tabela de cadastro
        Insert novo = new Insert("FORNECEDOR");
        
        //Adicionando campos
        novo.addField("nome_fornecedor", nome_bd, "string");
        novo.addField("nomectt_fornecedor", nomectt_bd, "string");
        novo.addField("fone_fornecedor", fone_bd, "string");
        novo.addField("email_fornecedor", email_bd, "string");
        
        //Executando a inserção
        retorno = novo.execute();
        
        return retorno;
    }
    
    // -- Editar
    public static boolean editar(int fornecedorid, Object nome, 
            Object nomectt, Object fone, Object email) {
        
        boolean retorno;
        
        //Criando objeto Update e definindo tabela de cadastro
        Update novo = new Update("FORNECEDOR");
        
        //Adicionando condição de edição
        novo.setCondition("fornecedorid = " + Integer.toString(fornecedorid));
        
        //Adicionando campos
        if (nome != null) novo.addField("nome_fornecedor", nome, "string");
        if (nomectt != null) novo.addField("nomectt_fornecedor", nomectt, "string");
        if (fone != null) novo.addField("fone_fornecedor", fone, "string");
        if (email != null) novo.addField("email_fornecedor", email, "string");
        
        //Executando a edição
        retorno = novo.execute();
        
        return retorno;
    }
    
    // -- Remover
    public static boolean remover(int fornecedorid) {
        
        /*
        -- Há um segredo aqui. Ninguém irá remover nada.
        -- Vamos apenas definir esse fornecedor como inativo.
        */
        
        boolean retorno = false;
        
        //Criando objeto Update e definindo tabela de cadastro
        Update targetToKill = new Update("FORNECEDOR");
        
        //Especificando o fornecedor a ser "removido"
        targetToKill.setCondition("fornecedorid = " + Integer.toString(fornecedorid));
        
        //Realizando a alteração do estado para inativo
        targetToKill.addField("ativo_fornecedor", 0, "int");
        
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
    
    //Buscar Fornecedores
    public static ResultSet buscar(String campos, String condicao) {
        
        if (!condicao.equals("")) condicao = " where " + condicao;
        Select sl = new Select("select " + campos + " from fornecedor" + condicao);
        sl.execute();
        return sl.getResult();
        
    }
    
    //Obter último fornecedor cadastrado
    public static int obterUltimoFornecedorCadastrado() {
        
        int retorno = 0;
        ResultSet rs;
        Select s = new Select("select fornecedorid from fornecedor order by fornecedorid desc");
        s.execute();
        rs = s.getResult();
        
        try {
            if (ResultSetToolBox.countRows(rs) > 0) {
                rs.first();
                retorno = rs.getInt("fornecedorid");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return retorno;
        
    }

    public void setNome_bd(String nome_bd) {
        this.nome_bd = nome_bd;
    }

    public void setNomectt_bd(String nomectt_bd) {
        this.nomectt_bd = nomectt_bd;
    }

    public void setFone_bd(String fone_bd) {
        this.fone_bd = fone_bd;
    }

    public void setEmail_bd(String email_bd) {
        this.email_bd = email_bd;
    }

    public String getNome_bd() {
        return nome_bd;
    }

    public String getNomectt_bd() {
        return nomectt_bd;
    }

    public String getFone_bd() {
        return fone_bd;
    }

    public String getEmail_bd() {
        return email_bd;
    }
   
}
