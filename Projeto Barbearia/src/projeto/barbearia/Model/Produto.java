
package projeto.barbearia.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class Produto {
    
    //Campos do BD
    private String nome_bd;
    private String marca_bd;
    private float custo_bd;
    private float valor_bd;
    private int qtd_bd;
    private int estoqueMinimo_bd;
    private int fornecedorid_bd;
    
    //Construtor para novos Produtos
    public Produto() {}
    
    //Construtor para produtos existentes
    public Produto(int produtoid) {
        
        ResultSet rs;
        
        rs = buscar("*", "produtoid = " + Integer.toString(produtoid));
        
        try {
            
            rs.next();
            
            nome_bd = rs.getString("nome_produto");
            marca_bd = rs.getString("marca_produto");
            custo_bd = rs.getFloat("custo_produto");
            valor_bd = rs.getFloat("valor_produto");
            qtd_bd = rs.getInt("qtd_produto");
            estoqueMinimo_bd = rs.getInt("estoqueminimo_produto");
            fornecedorid_bd = rs.getInt("fornecedorid");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    //Funções CRUD
    
    // -- Cadastrar
    public boolean cadastrar() {
        
        boolean retorno;
        
        //Criando objeto Insert e definindo tabela de cadastro
        Insert novo = new Insert("PRODUTO");
        
        //Adicionando campos
        novo.addField("nome_produto", nome_bd, "string");
        novo.addField("marca_produto", marca_bd, "string");
        novo.addField("custo_produto", custo_bd, "float");
        novo.addField("valor_produto", valor_bd, "float");
        novo.addField("qtd_produto", qtd_bd, "int");
        novo.addField("estoqueminimo_produto", estoqueMinimo_bd, "int");
        novo.addField("fornecedorid", fornecedorid_bd, "int");
        
        //Executando a inserção
        retorno = novo.execute();
        
        return retorno;
        
    }
    
    // -- Editar
    public static boolean editar(int produtoid, Object nome, Object marca, Object custo, Object valor, 
            Object qtd, Object estoqueMinimo, Object fornecedorid) {
        
        boolean retorno;
        
        //Criando objeto Update e definindo tabela de cadastro
        Update novo = new Update("PRODUTO");
        
        //Adicionando condição de edição
        novo.setCondition("produtoid = " + Integer.toString(produtoid));
        
        //Adicionando campos
        if (nome != null) novo.addField("nome_produto", nome, "string");
        if (marca != null) novo.addField("marca_produto", marca, "string");
        if (custo != null) novo.addField("custo_produto", custo, "float");
        if (valor != null) novo.addField("valor_produto", valor, "float");
        if (qtd != null) novo.addField("qtd_produto", qtd, "int");
        if (estoqueMinimo != null) novo.addField("estoqueminimo_produto", estoqueMinimo, "int");
        if (fornecedorid != null) novo.addField("fornecedorid", fornecedorid, "int");
        
        //Executando a edição
        retorno = novo.execute();
        
        return retorno;
    }
    
    // -- Remover
    public static boolean remover(int produtoid) {
        
        /*
        -- Há um segredo aqui. Ninguém irá remover nada.
        -- Vamos apenas definir esse produto como inativo.
        -- Logo logo, o coletor de lixo é que vai excluí-lo, pra valer.
        */
        
        boolean retorno = false;
        
        //Criando objeto Update e definindo tabela de cadastro
        Update targetToKill = new Update("PRODUTO");
        
        //Especificando o serviço a ser "removido"
        targetToKill.setCondition("produtoid = " + Integer.toString(produtoid));
        
        //Realizando a alteração do estado para inativo
        targetToKill.addField("ativo_produto", 0, "int");
        
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
    
    //Buscar Serviços
    public static ResultSet buscar(String campos, String condicao) {
        
        if (!condicao.equals("")) condicao = " where " + condicao;
        Select sl = new Select("select " + campos + " from produto" + condicao);
        sl.execute();
        return sl.getResult();
        
    }
    
    
    //Deduzir estoque no ato da venda
    public static boolean deduzirEstoque(int produtoid, int qtd) {
        boolean retorno;
        
        SQLQuery sq = new SQLQuery("update produto set "
                        + "qtd_produto = qtd_produto - " + qtd + 
                        " where produtoid = " + produtoid);
        retorno = sq.execute();
        
        return retorno;
    }

    public void setNome_bd(String nome_bd) {
        this.nome_bd = nome_bd;
    }

    public void setMarca_bd(String marca_bd) {
        this.marca_bd = marca_bd;
    }

    public void setCusto_bd(float custo_bd) {
        this.custo_bd = custo_bd;
    }

    public void setValor_bd(float valor_bd) {
        this.valor_bd = valor_bd;
    }

    public void setQtd_bd(int qtd_bd) {
        this.qtd_bd = qtd_bd;
    }

    public void setEstoqueMinimo_bd(int estoqueMinimo_bd) {
        this.estoqueMinimo_bd = estoqueMinimo_bd;
    }

    public void setFornecedorid_bd(int fornecedorid_bd) {
        this.fornecedorid_bd = fornecedorid_bd;
    }

    public String getNome_bd() {
        return nome_bd;
    }

    public String getMarca_bd() {
        return marca_bd;
    }

    public float getCusto_bd() {
        return custo_bd;
    }

    public float getValor_bd() {
        return valor_bd;
    }

    public int getQtd_bd() {
        return qtd_bd;
    }

    public int getEstoqueMinimo_bd() {
        return estoqueMinimo_bd;
    }

    public int getFornecedorid_bd() {
        return fornecedorid_bd;
    }
    
}
