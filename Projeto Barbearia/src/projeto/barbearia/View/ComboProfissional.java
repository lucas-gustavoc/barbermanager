
package projeto.barbearia.View;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import projeto.barbearia.Model.ResultSetToolBox;
import projeto.barbearia.Model.Tipos.ComboProfissionalTipo;
import projeto.barbearia.Model.db.Select;

/**
 *
 * @author lucas
 */
public class ComboProfissional extends JComboBox {
    
    ComboProfissional combo;
    
    public ComboProfissional() {
        super();
        combo = this;
    }
    
    public void preencherComboPara(ComboProfissionalTipo tipo, Object args[]) {
        
        switch (tipo) {
            case CADASTRO_CLIENTES:
                preencherParaCadastroClientes((int) args[0]);
                break;
            case CADASTRO_ENTRADA:
                preencherParaCadastroEntradaESaida((int) args[0]);
                break;
            case CADASTRO_SAIDA:
                preencherParaCadastroEntradaESaida((int) args[0]);
                break;
            case AGENDAMENTO:
                preencherParaAgendamentoERelatorio((int) args[0]);
                break;
            case RELATORIO:
                preencherParaAgendamentoERelatorio(-1);
                break;
        }
        
    }
    
    private void preencherParaCadastroClientes(int clienteid) {
        
        ResultSet rs;
        int nLinhas;
        int currentPro = getCurrentProfessional(clienteid);
        Select sl = new Select("select profissionalid, nome_profissional "
                + "from profissional where ativo_profissional = 1");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        combo.addItem("Selecione...");
        
        if (nLinhas > 0) {
            try {
                
                int i = 0;
                int selecaoAtual = 0;
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("profissionalid") + " - " + rs.getString("nome_profissional"));
                    
                    if (rs.getInt("profissionalid") == currentPro) {
                        selecaoAtual = i;
                    } else {
                        i++;
                    }
                    
                    combo.setSelectedIndex(selecaoAtual);
                    
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        
    }
    
    private void preencherParaCadastroEntradaESaida(int profissionalidAtual) {
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select profissionalid, nome_profissional from "
                + "profissional where ativo_profissional = 1");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        combo.addItem("Selecione...");
        
        if (nLinhas > 0) {
            try {
                
                int nIndex = 0, i = 1;
                
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("profissionalid") + " - " + rs.getString("nome_profissional"));
                    if (Integer.valueOf(rs.getString("profissionalid")) == profissionalidAtual) nIndex = i;
                    
                    i++;
                }
                
                if (nIndex == 0) {
                    if (procurarRegistroFantasma(profissionalidAtual)) nIndex = i;
                }
                
                combo.setSelectedIndex(nIndex);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
    private int getCurrentProfessional(int clienteid) {
        
        Select sl = new Select("select profissionalprefid_cliente from cliente where clienteid = " + clienteid);
        ResultSet rs;
        int retorno = 0;
        
        try {
            
            sl.execute();        
            rs = sl.getResult();
            
            if (ResultSetToolBox.countRows(rs) > 0) {
                rs.first();
                retorno = rs.getInt("profissionalprefid_cliente");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return retorno;
        
    }
    
    /**
     * 
     * @param profissionalidAtual id do profissional atualmente cadastrado. Definir
     * como -1 para os casos em que não se queira considerar esse caso.
     */
    private void preencherParaAgendamentoERelatorio(int profissionalidAtual) {
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select profissionalid, nome_profissional from "
                + "profissional where ativo_profissional = 1");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        if (nLinhas > 0) {
            try {
                
                int nIndex = 0, i = 1;
                combo.addItem("Selecione...");
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("profissionalid") + " - " + rs.getString("nome_profissional"));
                    if (Integer.valueOf(rs.getString("profissionalid")) == profissionalidAtual) nIndex = i;
                    
                    i++;
                }
                
                combo.setSelectedIndex(nIndex);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }

    public ComboProfissional getCombo() {
        return combo;
    }
    
    private boolean procurarRegistroFantasma(int profissionalidAtual) {
        boolean ret = false;
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select profissionalid, nome_profissional from "
                + "profissional where ativo_profissional = 0 and "
                + "profissionalid = " + profissionalidAtual);
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        if (nLinhas > 0) {
            try {
                rs.first();
                combo.addItem(rs.getString("profissionalid") + " - " + rs.getString("nome_profissional"));
                ret = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return ret;
    }
    
}
