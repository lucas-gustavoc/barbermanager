
package projeto.barbearia.Testes;

import javax.swing.*;

/**
 *
 * @author lucas
 */
public class CadastroCliente extends Tester {
    
    private JTextField txfNome; 
    private JTextField txfEmail; 
    private JTextField txfFone; 
    private JTextField txfNasc; 
    private JComboBox cbProf; 
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
            CacheReport.makeAReport("CadastroCliente", "Iniciando teste 1...");
            txfNome.setText("");
            txfEmail.setText("");
            txfFone.setText("");
            txfNasc.setText("");
            b.doClick();
            CacheReport.makeAReport("CadastroCliente", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroCliente", e.getMessage());
        }
        
        /**
         * 
         * TESTE 02
         * 
         */
        try {
            CacheReport.makeAReport("CadastroCliente", "Iniciando teste 2...");
            txfNome.setText("Eliane");
            txfEmail.setText("");
            txfFone.setText("");
            txfNasc.setText("");
            b.doClick();
            CacheReport.makeAReport("CadastroCliente", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroCliente", e.getMessage());
        }
        
        /**
         * 
         * TESTE 03
         * 
         */
        try {
            CacheReport.makeAReport("CadastroCliente", "Iniciando teste 3...");
            txfNome.setText("Marcos");
            txfEmail.setText("lucas@lucas.com");
            txfFone.setText("");
            txfNasc.setText("");
            b.doClick();
            CacheReport.makeAReport("CadastroCliente", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroCliente", e.getMessage());
        }
        
        /**
         * 
         * TESTE 04
         * 
         */
        try {
            CacheReport.makeAReport("CadastroCliente", "Iniciando teste 4...");
            txfNome.setText("João");
            txfEmail.setText("");
            txfFone.setText("");
            txfNasc.setText("kkk");
            b.doClick();
            CacheReport.makeAReport("CadastroCliente", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroCliente", e.getMessage());
        }
        
        /**
         * 
         * TESTE 05
         * 
         */
        try {
            CacheReport.makeAReport("CadastroCliente", "Iniciando teste 5...");
            txfNome.setText("Johnahthan");
            txfEmail.setText("");
            txfFone.setText("");
            txfNasc.setText("20/09/99");
            b.doClick();
            CacheReport.makeAReport("CadastroCliente", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroCliente", e.getMessage());
        }
        
        /**
         * 
         * TESTE 06
         * 
         */
        try {
            CacheReport.makeAReport("CadastroCliente", "Iniciando teste 6...");
            txfNome.setText("Arieli");
            txfEmail.setText("");
            txfFone.setText("");
            txfNasc.setText("20/09/1999");
            b.doClick();
            CacheReport.makeAReport("CadastroCliente", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroCliente", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 07
         * 
         */
        try {
            CacheReport.makeAReport("CadastroCliente", "Iniciando teste 7...");
            txfNome.setText("Urik");
            txfEmail.setText("kkkkk");
            txfFone.setText("kkkkkk");
            txfNasc.setText("20/09/1999");
            b.doClick();
            CacheReport.makeAReport("CadastroCliente", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroCliente", e.getMessage());
        }
        
    }
    
    public void setTxfNome(JTextField txfNome) {
        this.txfNome = txfNome;
    }

    public void setTxfEmail(JTextField txfEmail) {
        this.txfEmail = txfEmail;
    }

    public void setTxfFone(JTextField txfFone) {
        this.txfFone = txfFone;
    }

    public void setTxfNasc(JTextField txfNasc) {
        this.txfNasc = txfNasc;
    }

    public void setCbProf(JComboBox cbProf) {
        this.cbProf = cbProf;
    }

    public void setB(JButton b) {
        this.b = b;
    }
    
}
