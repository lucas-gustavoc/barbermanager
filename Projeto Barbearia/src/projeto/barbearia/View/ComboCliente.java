
package projeto.barbearia.View;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import projeto.barbearia.Model.Cliente;
import projeto.barbearia.Model.ResultSetToolBox;
import projeto.barbearia.Model.Tipos.ComboClienteTipo;
import projeto.barbearia.Model.Tipos.TipoCadastro;
import projeto.barbearia.Model.db.*;

/**
 *
 * @author lucas
 */
public class ComboCliente extends JComboBox implements Atualizavel {
    
    ComboCliente combo;
    private ComboClienteTipo tipo;
    
    public ComboCliente() {
        super();
        combo = this;
    }
    
    public void preencherComboPara(ComboClienteTipo tipo, Object args[]) {
        
        this.tipo = tipo;
        
        switch (tipo) {
            case CADASTRO_ENTRADA:
                preencherParaCadastroEntrada((int) args[0]);
                break;
            case AGENDAMENTO:
                preencherParaAgendamento((int) args[0]);
                break;
        }
        
    }
    
    private void preencherParaCadastroEntrada(int clienteidAtual) {
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select clienteid, nome_cliente from "
                + "cliente where ativo_cliente = 1");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        combo.addItem("Selecione...");
        if (nLinhas > 0) {
            try {
                
                int nIndex = 0, i = 1;
                
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("clienteid") + " - " + rs.getString("nome_cliente"));
                    
                    if (Integer.valueOf(rs.getString("clienteid")) == clienteidAtual) nIndex = i;
                    
                    i++;
                }
                
                if (nIndex == 0) {
                    if (procurarRegistroFantasma(clienteidAtual)) nIndex = i;
                }
                
                combo.setSelectedIndex(nIndex);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
    private void preencherParaAgendamento(int clienteidAtual) {
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select clienteid, nome_cliente from "
                + "cliente where ativo_cliente = 1");
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        combo.addItem("Selecione...");
        if (nLinhas > 0) {
            try {
                
                int nIndex = 0, i = 2;
                
                addItem("+ Novo Cliente");
                rs.beforeFirst();
                
                while (rs.next()) {
                    combo.addItem(rs.getString("clienteid") + " - " + rs.getString("nome_cliente"));
                    
                    if (Integer.valueOf(rs.getString("clienteid")) == clienteidAtual) nIndex = i;
                    
                    i++;
                }
                
                combo.setSelectedIndex(nIndex);
                
                this.addActionListener((e) -> cadastrarCliente_Agendamento());
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
    private void cadastrarCliente_Agendamento() {
        if (this.getSelectedIndex() == 1) {
            this.setSelectedIndex(0);
            CacheAtualizacao.adicionarItemAoCache(this, "CadastroCliente");
            new CadastroCliente(TipoCadastro.NOVO_CADASTRO, 0);
        }
    }
    
    private boolean procurarRegistroFantasma(int clienteidAtual) {
        boolean ret = false;
        
        ResultSet rs;
        int nLinhas;
        Select sl = new Select("select clienteid, nome_cliente from "
                + "cliente where ativo_cliente = 0 and "
                + "clienteid = " + clienteidAtual);
        
        sl.execute();
        rs = sl.getResult();
        nLinhas = ResultSetToolBox.countRows(rs);
        
        if (nLinhas > 0) {
            try {
                rs.first();
                combo.addItem(rs.getString("clienteid") + " - " + rs.getString("nome_cliente"));
                ret = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return ret;
    }
    
    @Override
    public void atualizar() {
        if (tipo == ComboClienteTipo.AGENDAMENTO) {
            this.removeAllItems(); //removendo itens atuais
            Object args[] = new Object[1];  
            args[0] = Cliente.obterUltimoClienteCadastrado(); //descobrindo cliente que foi cadastrado
            preencherComboPara(tipo, args); //preenchendo com novo cliente
        }
    }
    
}
