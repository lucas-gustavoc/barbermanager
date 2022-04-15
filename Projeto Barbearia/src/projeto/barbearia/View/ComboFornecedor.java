
package projeto.barbearia.View;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import projeto.barbearia.Model.Fornecedor;
import projeto.barbearia.Model.ResultSetToolBox;
import projeto.barbearia.Model.Tipos.ComboFornecedorTipo;
import projeto.barbearia.Model.db.Select;

/**
 *
 * @author lucas
 */
public class ComboFornecedor extends JComboBox implements Atualizavel {
    
    ComboFornecedor combo;
    ComboFornecedorTipo tipoAtual;
    Object[] argsAtuais;
    boolean objetoFoiAtualizado;
    
    public ComboFornecedor() {
        super();
        combo = this;
        objetoFoiAtualizado = false;
    }
    
    public void preencherComboPara(ComboFornecedorTipo tipo, Object args[]) {
        
        this.tipoAtual = tipo;
        this.argsAtuais = args;
        
        switch (tipo) {
            case CADASTRO_PRODUTOS:
                preencherParaCadastroProdutos((int) args[0]);
                break;
            case CADASTRO_ENTRADA:
                preencherParaCadastroEntradaESaida((int) args[0]);
                break;
            case CADASTRO_SAIDA:
                preencherParaCadastroEntradaESaida((int) args[0]);
                break;
        }
        
    }
    
    private void preencherParaCadastroProdutos(int produtoid) {
        
        ResultSet rs;
        int nLinhas;
        int currentForn = (objetoFoiAtualizado) 
                ? Fornecedor.obterUltimoFornecedorCadastrado() 
                : getCurrentFornecedor(produtoid);
        
        Select sl = new Select("select fornecedorid, nome_fornecedor "
                + "from fornecedor where ativo_fornecedor = 1");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
                
        combo.addItem("Selecione...");
        
        if (nLinhas > 0) {
            try {
                
                int i = 1;
                int selecaoAtual = 0;
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("fornecedorid") + " - " + rs.getString("nome_fornecedor"));
                    
                    if (rs.getInt("fornecedorid") == currentForn) {
                        selecaoAtual = i;
                    } else {
                        i++;
                    }
                    
                }
                
                combo.setSelectedIndex(selecaoAtual);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        
    }
    
    private void preencherParaCadastroEntradaESaida(int fornecedoridAtual) {
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select fornecedorid, nome_fornecedor "
                + "from fornecedor where ativo_fornecedor = 1");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
                
        combo.addItem("Selecione...");
        
        if (nLinhas > 0) {
            try {
                
                int i = 1;
                int selecaoAtual = 0;
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("fornecedorid") + " - " + rs.getString("nome_fornecedor"));
                    
                    if (rs.getInt("fornecedorid") == fornecedoridAtual) {
                        selecaoAtual = i;
                    } else {
                        i++;
                    }
                    
                }
                
                if (selecaoAtual == 0) {
                    if (procurarRegistroFantasma(fornecedoridAtual)) selecaoAtual = i;
                }
                
                combo.setSelectedIndex(selecaoAtual);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
    private int getCurrentFornecedor(int produtoid) {
        
        Select sl = new Select("select fornecedorid from produto where produtoid = " + produtoid);
        ResultSet rs;
        int retorno = 0;
        
        try {
            
            sl.execute();        
            rs = sl.getResult();
            
            if (ResultSetToolBox.countRows(rs) > 0) {
                rs.first();
                retorno = rs.getInt("fornecedorid");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return retorno;
        
    }

    public ComboFornecedor getCombo() {
        return combo;
    }

    @Override
    public void atualizar() {
        if (tipoAtual == ComboFornecedorTipo.CADASTRO_PRODUTOS) {
            this.removeAllItems(); //removendo itens atuais
            Object args[] = new Object[1];  
            args[0] = 0;
            objetoFoiAtualizado = true;
            preencherComboPara(tipoAtual, args); //preenchendo com novo fornecedor
        }
    }

    private boolean procurarRegistroFantasma(int fornecedoridAtual) {
        boolean ret = false;
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select fornecedorid, nome_fornecedor "
                + "from fornecedor where ativo_fornecedor = 0 and "
                + "fornecedorid = " + fornecedoridAtual);
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        if (nLinhas > 0) {
            try {
                rs.first();
                combo.addItem(rs.getString("fornecedorid") + " - " + rs.getString("nome_fornecedor"));
                ret = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return ret;
    }
    
}
