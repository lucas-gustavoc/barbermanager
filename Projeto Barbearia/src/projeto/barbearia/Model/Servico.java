
package projeto.barbearia.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class Servico {
    
    //Campos do BD
    private String nome;
    private float valor;
    private float comissao;
    private String tipo;
    
    //Construtor para novos Serviços
    public Servico() {}
    
    //Construtor para um Serviço existente
    public Servico(int id) {
        
        ResultSet rs;
        
        rs = buscar("*", "servicoid = " + Integer.toString(id));
        
        try {
            
            rs.next();
            
            nome = rs.getString("nome_servico");
            valor = rs.getFloat("valor_servico");
            comissao = rs.getFloat("comissao_servico");
            tipo = rs.getString("tipo_servico");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    //Funções CRUD
    
    // -- Cadastrar
    public boolean cadastrar() {
        
        boolean retorno;
        
        //Criando objeto Insert e definindo tabela de cadastro
        Insert novo = new Insert("SERVICO");
        
        //Adicionando campos
        novo.addField("nome_servico", nome, "string");
        novo.addField("valor_servico", valor, "float");
        novo.addField("comissao_servico", comissao, "float");
        novo.addField("tipo_servico", tipo, "string");
        
        //Executando a inserção
        retorno = novo.execute();
        
        return retorno;
    }
    
    // -- Editar
    public static boolean editar(int servicoid, Object nome, Object valor, Object comissao, Object tipo) {
        
        boolean retorno;
        
        //Criando objeto Update e definindo tabela de cadastro
        Update novo = new Update("SERVICO");
        
        //Adicionando condição de edição
        novo.setCondition("servicoid = " + Integer.toString(servicoid));
        
        //Adicionando campos
        if (nome != null) novo.addField("nome_servico", nome, "string");
        if (valor != null) novo.addField("valor_servico", valor, "float");
        if (comissao != null) novo.addField("comissao_servico", comissao, "float");
        if (tipo != null) novo.addField("tipo_servico", tipo, "string");
        
        //Executando a edição
        retorno = novo.execute();
        
        return retorno;
        
    }
    
    // -- Remover
    public static boolean remover(int servicoid) {
        
        /*
        -- Há um segredo aqui. Ninguém irá remover nada.
        -- Vamos apenas definir esse serviço como inativo.
        -- Logo logo, o coletor de lixo é que vai excluí-lo, pra valer.
        */
        
        boolean retorno = false;
        
        //Criando objeto Update e definindo tabela de cadastro
        Update targetToKill = new Update("SERVICO");
        
        //Especificando o serviço a ser "removido"
        targetToKill.setCondition("servicoid = " + Integer.toString(servicoid));
        
        //Realizando a alteração do estado para inativo
        targetToKill.addField("ativo_servico", 0, "int");
        
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
        Select sl = new Select("select " + campos + " from servico" + condicao);
        sl.execute();
        return sl.getResult();
        
    }
    
    //Verificar se determinado serviço trata-se de pacote ou não
    public static boolean isPackage(int servicoid) {
        boolean retorno = false;
        
        ResultSet rs = buscar("tipo_servico", "servicoid = " + servicoid);
        
        if (ResultSetToolBox.countRows(rs) > 0) {
            try {
                rs.first();
                if (rs.getString("tipo_servico").toUpperCase().equals("PACOTE")) retorno = true; 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return retorno;
    }
    
    /**
     * Método utilizado para obter os itens de um pacote específico.
     * 
     * @param pacoteid id do pacote cujos itens serão buscados.
     * @return uma matriz de String, no formato String[i][j], sendo 
     * i o índice dos itens e j o índice dos campos (sendo eles 0: id do item e 1: nome do item
     *  e 2: a quantidade).
     * Caso haja um problema na identificação ou o pacote esteja vazio, o retorno é <code>null</code>.
     */
    public static String[][] obterItensDeUmPacote(int pacoteid) {
        String[][] retorno = null;
        
        ResultSet rs;
        Select s = new Select("select itempacote.SERVICOID, servico.NOME_SERVICO, "
                + "itempacote.QTD_ITEMPACOTE from itempacote, servico where "
                + "itempacote.SERVICOID = servico.SERVICOID and pacoteid = " + pacoteid);
        
        s.execute();
        rs = s.getResult();
        
        int count = ResultSetToolBox.countRows(rs);
        
        if (count > 0) {
            try {
                retorno = new String[count][3];
                rs.beforeFirst();
                
                count = 0;
                while (rs.next()) {
                    retorno[count][0] = rs.getString("SERVICOID");
                    retorno[count][1] = rs.getString("NOME_SERVICO");
                    retorno[count][2] = rs.getString("QTD_ITEMPACOTE");
                    count++;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return retorno;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setComissao(float comissao) {
        this.comissao = comissao;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public float getValor() {
        return valor;
    }

    public float getComissao() {
        return comissao;
    }

    public String getTipo() {
        return tipo;
    }
    
    
    
}
