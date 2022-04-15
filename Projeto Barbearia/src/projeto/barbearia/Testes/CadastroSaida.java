
package projeto.barbearia.Testes;

import javax.swing.*;

/**
 *
 * @author lucas
 */
public class CadastroSaida extends Tester {

    private JTextField txfDesc;
    private JTextField txfValor;
    private JTextField txfDtPagto;
    private JTextField txfDtVcto;
    private JComboBox cbProf;
    private JComboBox cbForn;
    private JComboBox cbNatureza;
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
            CacheReport.makeAReport("CadastroSaida", "Iniciando teste 1...");
            
            txfDesc.setText("");
            txfValor.setText("");
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            cbProf.setSelectedIndex(0);
            cbForn.setSelectedIndex(0);
            cbNatureza.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroSaida", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroSaida", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 02
         * 
         */
        try {
            CacheReport.makeAReport("CadastroSaida", "Iniciando teste 2...");
            
            txfValor.setText("20"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            cbProf.setSelectedIndex(1); //obrigatório ou subst. pelo fornecedor
            cbForn.setSelectedIndex(0); //obrigatório ou subst. pelo profissional
            cbNatureza.setSelectedIndex(1); //obrigatório
            txfDesc.setText(""); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroSaida", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroSaida", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 03
         * 
         */
        try {
            CacheReport.makeAReport("CadastroSaida", "Iniciando teste 3...");
            
            txfDesc.setText("Referente à semana do dia 2"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            cbProf.setSelectedIndex(1); //obrigatório ou subst. pelo fornecedor
            cbForn.setSelectedIndex(0); //obrigatório ou subst. pelo profissional
            cbNatureza.setSelectedIndex(1); //obrigatório
            txfValor.setText(""); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroSaida", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroSaida", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 04
         * 
         */
        try {
            CacheReport.makeAReport("CadastroSaida", "Iniciando teste 4...");
            
            txfDesc.setText("Referente à semana do dia 2"); //obrigatório
            txfValor.setText("400"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            cbProf.setSelectedIndex(0); //obrigatório ou subst. pelo fornecedor
            cbForn.setSelectedIndex(0); //obrigatório ou subst. pelo profissional
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroSaida", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroSaida", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 05
         * 
         */
        try {
            CacheReport.makeAReport("CadastroSaida", "Iniciando teste 5...");
            
            txfDesc.setText("Referente à semana do dia 2"); //obrigatório
            txfValor.setText("400"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            cbProf.setSelectedIndex(0); //obrigatório ou subst. pelo fornecedor
            cbForn.setSelectedIndex(1); //obrigatório ou subst. pelo profissional
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroSaida", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroSaida", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 06
         * 
         */
        try {
            CacheReport.makeAReport("CadastroSaida", "Iniciando teste 6...");
            
            txfDesc.setText("Referente à semana do dia 2"); //obrigatório
            txfValor.setText("400"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            cbProf.setSelectedIndex(0); //obrigatório ou subst. pelo fornecedor
            cbForn.setSelectedIndex(1); //obrigatório ou subst. pelo profissional
            cbNatureza.setSelectedIndex(0); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroSaida", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroSaida", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 07
         * 
         */
        try {
            CacheReport.makeAReport("CadastroSaida", "Iniciando teste 7...");
            
            txfDesc.setText("Referente à semana do dia 2"); //obrigatório
            txfValor.setText("400"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            cbProf.setSelectedIndex(1); //obrigatório ou subst. pelo fornecedor
            cbForn.setSelectedIndex(0); //obrigatório ou subst. pelo profissional
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroSaida", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroSaida", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 08
         * 
         */
        try {
            CacheReport.makeAReport("CadastroSaida", "Iniciando teste 8...");
            
            txfDesc.setText("Referente à semana do dia 2"); //obrigatório
            txfValor.setText("400"); //obrigatório
            txfDtPagto.setText("25/09/2017");
            txfDtVcto.setText("20/09/2017");
            cbProf.setSelectedIndex(1); //obrigatório ou subst. pelo fornecedor
            cbForn.setSelectedIndex(0); //obrigatório ou subst. pelo profissional
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroSaida", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroSaida", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 09
         * 
         */
        try {
            CacheReport.makeAReport("CadastroSaida", "Iniciando teste 9...");
            
            txfDesc.setText("Referente à semana do dia 2"); //obrigatório
            txfValor.setText("400"); //obrigatório
            txfDtPagto.setText("20/18/2017");
            txfDtVcto.setText("");
            cbProf.setSelectedIndex(1); //obrigatório ou subst. pelo fornecedor
            cbForn.setSelectedIndex(0); //obrigatório ou subst. pelo profissional
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroSaida", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroSaida", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 10
         * 
         */
        try {
            CacheReport.makeAReport("CadastroSaida", "Iniciando teste 10...");
            
            txfDesc.setText("Referente à semana do dia 2"); //obrigatório
            txfValor.setText("400"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("99/09/2017");
            cbProf.setSelectedIndex(1); //obrigatório ou subst. pelo fornecedor
            cbForn.setSelectedIndex(0); //obrigatório ou subst. pelo profissional
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroSaida", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroSaida", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 11
         * 
         */
        try {
            CacheReport.makeAReport("CadastroSaida", "Iniciando teste 11...");
            
            txfDesc.setText("Referente à semana do dia 2"); //obrigatório
            txfValor.setText("39.90"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            cbProf.setSelectedIndex(1); //obrigatório ou subst. pelo fornecedor
            cbForn.setSelectedIndex(0); //obrigatório ou subst. pelo profissional
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroSaida", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroSaida", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 12
         * 
         */
        try {
            CacheReport.makeAReport("CadastroSaida", "Iniciando teste 12...");
            
            txfDesc.setText("Referente à semana do dia 2"); //obrigatório
            txfValor.setText("39,90"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            cbProf.setSelectedIndex(1); //obrigatório ou subst. pelo fornecedor
            cbForn.setSelectedIndex(0); //obrigatório ou subst. pelo profissional
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroSaida", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroSaida", e.getMessage());
        }
        
    }

    public void setTxfDesc(JTextField txfDesc) {
        this.txfDesc = txfDesc;
    }

    public void setTxfValor(JTextField txfValor) {
        this.txfValor = txfValor;
    }

    public void setTxfDtPagto(JTextField txfDtPagto) {
        this.txfDtPagto = txfDtPagto;
    }

    public void setTxfDtVcto(JTextField txfDtVcto) {
        this.txfDtVcto = txfDtVcto;
    }

    public void setCbProf(JComboBox cbProf) {
        this.cbProf = cbProf;
    }

    public void setCbForn(JComboBox cbForn) {
        this.cbForn = cbForn;
    }

    public void setCbNatureza(JComboBox cbNatureza) {
        this.cbNatureza = cbNatureza;
    }

    public void setB(JButton b) {
        this.b = b;
    }
    
}
