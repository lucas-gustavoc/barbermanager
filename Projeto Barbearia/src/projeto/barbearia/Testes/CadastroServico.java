
package projeto.barbearia.Testes;

import javax.swing.*;

/**
 *
 * @author lucas
 */
public class CadastroServico extends Tester {

    private JTextField txfNome;
    private JTextField txfValor;
    private JTextField txfComissao;
    private JComboBox cbTipo;
    private JButton b;
    
    @Override
    public void executarTeste() {
        testingNow = true;
        
        /**
         * 
         * TESTE 01
         * 
         */
        try {
            CacheReport.makeAReport("CadastroServico", "Iniciando teste 1...");
            
            txfNome.setText("");
            txfValor.setText("");
            txfComissao.setText("");
            cbTipo.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroServico", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroServico", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 02
         * 
         */
        try {
            CacheReport.makeAReport("CadastroServico", "Iniciando teste 2...");
            
            txfNome.setText("Corte");
            txfValor.setText("");
            txfComissao.setText("");
            cbTipo.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroServico", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroServico", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 03
         * 
         */
        try {
            CacheReport.makeAReport("CadastroServico", "Iniciando teste 3...");
            
            txfNome.setText("Corte");
            txfValor.setText("20");
            txfComissao.setText("");
            cbTipo.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroServico", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroServico", e.getMessage());
        }
        
        
        
        /**
         * 
         * TESTE 04
         * 
         */
        try {
            CacheReport.makeAReport("CadastroServico", "Iniciando teste 4...");
            
            txfNome.setText("Corte");
            txfValor.setText("");
            txfComissao.setText("50");
            cbTipo.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroServico", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroServico", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 05
         * 
         */
        try {
            CacheReport.makeAReport("CadastroServico", "Iniciando teste 5...");
            
            txfNome.setText("Corte");
            txfValor.setText("20");
            txfComissao.setText("50");
            cbTipo.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroServico", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroServico", e.getMessage());
        }
        
        
        
        /**
         * 
         * TESTE 06
         * 
         */
        try {
            CacheReport.makeAReport("CadastroServico", "Iniciando teste 6...");
            
            txfNome.setText("Barba");
            txfValor.setText("20.5");
            txfComissao.setText("50.5");
            cbTipo.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroServico", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroServico", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 07
         * 
         */
        try {
            CacheReport.makeAReport("CadastroServico", "Iniciando teste 7...");
            
            txfNome.setText("Barba Especial");
            txfValor.setText("20,35");
            txfComissao.setText("50");
            cbTipo.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroServico", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroServico", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 08
         * 
         */
        try {
            CacheReport.makeAReport("CadastroServico", "Iniciando teste 8...");
            
            txfNome.setText("Barba Especial");
            txfValor.setText("20");
            txfComissao.setText("52,50");
            cbTipo.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroServico", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroServico", e.getMessage());
        }
        
    }

    public void setTxfNome(JTextField txfNome) {
        this.txfNome = txfNome;
    }

    public void setTxfValor(JTextField txfValor) {
        this.txfValor = txfValor;
    }

    public void setTxfComissao(JTextField txfComissao) {
        this.txfComissao = txfComissao;
    }

    public void setCbTipo(JComboBox cbTipo) {
        this.cbTipo = cbTipo;
    }

    public void setB(JButton b) {
        this.b = b;
    }
    
    
    
}
