
package projeto.barbearia.Model;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class Natureza {
    
    //Campos BD
    private String descricao;
    private String ref;
    private boolean entrada;
    private boolean saida;
    
    //Construtores
    public Natureza() {}
    
    public Natureza(int id) {
        String[][] s = buscarInterno("select * from natureza where naturezaid = " + id);
        
        if (s.length > 0) {
            descricao = s[0][1];
            ref = s[0][2];
            entrada = (s[0][3].equalsIgnoreCase("SIM")) ? true : false;
            saida = (s[0][4].equalsIgnoreCase("SIM")) ? true : false;
        }
    }
    
    //Métodos
    public boolean cadastrar() {
        Insert c = new Insert("NATUREZA");
        c.addField("nome_natureza", descricao, "string");
        c.addField("ref_natureza", ref, "string");
        c.addField("entrada_natureza", (entrada) ? 1 : 0, "int");
        c.addField("saida_natureza", (saida) ? 1 : 0, "int");
        return c.execute();
    }
    
    public boolean editar(int id) {
        Update c = new Update("NATUREZA");
        c.setCondition("naturezaid = " + id);
        c.addField("nome_natureza", descricao, "string");
        c.addField("ref_natureza", ref, "string");
        c.addField("entrada_natureza", (entrada) ? 1 : 0, "int");
        c.addField("saida_natureza", (saida) ? 1 : 0, "int");
        return c.execute();
    }
    
    public static boolean remover(int id) {
        boolean r = false;
        
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
            Update c = new Update("NATUREZA");
            c.setCondition("naturezaid = " + id);
            c.addField("ativo_natureza", 0, "int");
            r = c.execute();
        }
        
        return r;
    }
    
    public static String[][] buscar(String condicao) {
        return buscarInterno("select * from natureza where ativo_natureza = 1 and " + condicao);
    }
    
    public static String[][] buscar() {
        return buscarInterno("select * from natureza where ativo_natureza = 1");
    }
    
    private static String[][] buscarInterno(String stt) {
        ResultSet rs;
        String[][] r = new String[0][5];
        int count = 0;
        
        Select s = new Select(stt);
        s.execute();
        rs = s.getResult();
        count = ResultSetToolBox.countRows(rs);
        
        if (count > 0) {
            r = new String[count][5];
            try {
                rs.beforeFirst();
                count = 0;
                while (rs.next()) {
                    r[count][0] = rs.getString("naturezaid");
                    r[count][1] = rs.getString("nome_natureza");
                    r[count][2] = rs.getString("ref_natureza");
                    r[count][3] = (rs.getInt("entrada_natureza") == 1) ? "SIM" : "NÃO";
                    r[count][4] = (rs.getInt("saida_natureza") == 1) ? "SIM" : "NÃO";
                    count++;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return r;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public boolean isEntrada() {
        return entrada;
    }

    public void setEntrada(boolean entrada) {
        this.entrada = entrada;
    }

    public boolean isSaida() {
        return saida;
    }

    public void setSaida(boolean saida) {
        this.saida = saida;
    }
    
    
    
}
