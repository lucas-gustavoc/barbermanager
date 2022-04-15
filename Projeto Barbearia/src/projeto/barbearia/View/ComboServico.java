
package projeto.barbearia.View;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import projeto.barbearia.Model.ResultSetToolBox;
import projeto.barbearia.Model.Tipos.ComboServicoTipo;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class ComboServico extends JComboBox {
    
    ComboServico combo;
    
    public ComboServico() {
        super();
        combo = this;
    }
    
    public void preencherComboPara(ComboServicoTipo tipo, Object args[]) {
        
        switch (tipo) {
            case CADASTRO_COMISSOES:
                preencherParaCadastroComissoes((int) args[0]);
                break;
            case CADASTRO_ITENSPACOTE:
                preencherParaCadastroItensPacote();
                break;
            case CADASTRO_ENTRADA:
                preencherParaCadastroGeral((int) args[0]);
                break;
            case AGENDAMENTO:
                preencherParaAgendamento((int) args[0]);
                break;
            case RELATORIO:
                preencherParaCadastroGeral(-1);
                break;
        }
        
    }
    
    private void preencherParaCadastroComissoes(int profissionalid) {
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select servicoid, nome_servico from "
                + "servico where ativo_servico = 1 and servicoid not in "
                + "(select comissao_profissional.servicoid "
                + "from comissao_profissional "
                + "where COMISSAO_PROFISSIONAL.profissionalid = " + profissionalid + ")");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        if (nLinhas > 0) {
            try {
                
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("servicoid") + " - " + rs.getString("nome_servico"));
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        
    }
    
    private void preencherParaCadastroItensPacote() {
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select servicoid, nome_servico from "
                + "servico where tipo_servico = 'SERVICO' and ativo_servico = 1");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        if (nLinhas > 0) {
            try {
                
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("servicoid") + " - " + rs.getString("nome_servico"));
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        
    }
    
    /**
     * 
     * @param servicoidAtual id do serviço cadastrado atualmente. Definir como
     * -1 caso deseje desconsiderar esta opção.
     */
    private void preencherParaCadastroGeral(int servicoidAtual) {
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select servicoid, nome_servico from "
                + "servico where ativo_servico = 1");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        combo.addItem("Selecione...");
        
        if (nLinhas > 0) {
            try {
                
                int nIndex = 0, i = 1;
                
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("servicoid") + " - " + rs.getString("nome_servico"));
                    
                    if (Integer.valueOf(rs.getString("servicoid")) == servicoidAtual) nIndex = i;
                    
                    i++;
                }
                
                if (nIndex == 0) {
                    if (procurarRegistroFantasma(servicoidAtual)) nIndex = i;
                }
                
                combo.setSelectedIndex(nIndex);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
    private void preencherParaAgendamento(int servicoidAtual) {
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select servicoid, nome_servico from "
                + "servico where ativo_servico = 1 and tipo_servico = 'SERVICO'");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        combo.addItem("Selecione...");
        
        if (nLinhas > 0) {
            try {
                
                int nIndex = 0, i = 1;
                
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("servicoid") + " - " + rs.getString("nome_servico"));
                    
                    if (Integer.valueOf(rs.getString("servicoid")) == servicoidAtual) nIndex = i;
                    
                    i++;
                }
                
                combo.setSelectedIndex(nIndex);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }

    public ComboServico getCombo() {
        return combo;
    }
    
    private boolean procurarRegistroFantasma(int servicoidAtual) {
        boolean ret = false;
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select servicoid, nome_servico from "
                + "servico where ativo_servico = 0 and "
                + "servicoid = " + servicoidAtual);
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        if (nLinhas > 0) {
            try {
                rs.first();
                combo.addItem(rs.getString("servicoid") + " - " + rs.getString("nome_servico"));
                ret = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return ret;
    }
    
}
