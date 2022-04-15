
package projeto.barbearia.Testes;

import javax.swing.*;

/**
 *
 * @author lucas
 */
public class CadastroProfissional extends Tester {
    
    private JTextField txfNome;
    private JTextField txfFone1;
    private JTextField txfFone2;
    private JTextField txfEmail;
    private JTextField txfNasc;
    private JComboBox cbFolga;
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
            CacheReport.makeAReport("CadastroProfissional", "Iniciando teste 1...");
            
            txfNome.setText("");
            txfFone1.setText("");
            txfFone2.setText("");
            txfEmail.setText("");
            txfNasc.setText("");
            cbFolga.setSelectedIndex(0);
            
            b.doClick();
            CacheReport.makeAReport("CadastroProfissional", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProfissional", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 02
         * 
         */
        try {
            CacheReport.makeAReport("CadastroProfissional", "Iniciando teste 2...");
            
            txfNome.setText("Diogo");
            txfFone1.setText("");
            txfFone2.setText("");
            txfEmail.setText("");
            txfNasc.setText("");
            cbFolga.setSelectedIndex(0);
            
            b.doClick();
            CacheReport.makeAReport("CadastroProfissional", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProfissional", e.getMessage());
        }
        
        
        
        /**
         * 
         * TESTE 03
         * 
         */
        try {
            CacheReport.makeAReport("CadastroProfissional", "Iniciando teste 3...");
            
            txfNome.setText("Diogo");
            txfFone1.setText("(44) 9 9909-0293");
            txfFone2.setText("");
            txfEmail.setText("diogo@evolucaoinox.com.br");
            txfNasc.setText("kkkk");
            cbFolga.setSelectedIndex(1);
            
            b.doClick();
            CacheReport.makeAReport("CadastroProfissional", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProfissional", e.getMessage());
        }
        
        
        
        /**
         * 
         * TESTE 04
         * 
         */
        try {
            CacheReport.makeAReport("CadastroProfissional", "Iniciando teste 4...");
            
            txfNome.setText("Diogo");
            txfFone1.setText("");
            txfFone2.setText("(44) 9 9909-0293");
            txfEmail.setText("diogo@evolucaoinox.com.br");
            txfNasc.setText("10/09/1989");
            cbFolga.setSelectedIndex(1);
            
            b.doClick();
            CacheReport.makeAReport("CadastroProfissional", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProfissional", e.getMessage());
        }
        
        
        
        /**
         * 
         * TESTE 05
         * 
         */
        try {
            CacheReport.makeAReport("CadastroProfissional", "Iniciando teste 5...");
            
            txfNome.setText("Elias");
            txfFone1.setText("(44) 3228-9484");
            txfFone2.setText("(44) 9 9909-0293");
            txfEmail.setText("elias@evolucaoinox.com.br");
            txfNasc.setText("10/09/1971");
            cbFolga.setSelectedIndex(4);
            
            b.doClick();
            CacheReport.makeAReport("CadastroProfissional", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProfissional", e.getMessage());
        }
        
    }

    public void setTxfNome(JTextField txfNome) {
        this.txfNome = txfNome;
    }

    public void setTxfFone1(JTextField txfFone1) {
        this.txfFone1 = txfFone1;
    }

    public void setTxfFone2(JTextField txfFone2) {
        this.txfFone2 = txfFone2;
    }

    public void setTxfEmail(JTextField txfEmail) {
        this.txfEmail = txfEmail;
    }

    public void setTxfNasc(JTextField txfNasc) {
        this.txfNasc = txfNasc;
    }

    public void setCbFolga(JComboBox cbFolga) {
        this.cbFolga = cbFolga;
    }

    public void setB(JButton b) {
        this.b = b;
    }
    
}
