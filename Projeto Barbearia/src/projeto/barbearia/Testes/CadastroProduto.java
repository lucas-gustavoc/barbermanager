
package projeto.barbearia.Testes;

import javax.swing.*;

/**
 *
 * @author lucas
 */
public class CadastroProduto extends Tester {
    
    private JTextField txfNome;
    private JTextField txfMarca;
    private JTextField txfQtd;
    private JTextField txfCusto;
    private JTextField txfValor;
    private JTextField txfEstoque;
    private JComboBox cbForn;
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
            CacheReport.makeAReport("CadastroProduto", "Iniciando teste 1...");
            
            txfNome.setText("");
            txfMarca.setText("");
            txfQtd.setText("");
            txfCusto.setText("");
            txfValor.setText("");
            txfEstoque.setText("");
            cbForn.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroProduto", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProduto", e.getMessage());
        }
        
        
        
        /**
         * 
         * TESTE 02
         * 
         */
        try {
            CacheReport.makeAReport("CadastroProduto", "Iniciando teste 2...");
            
            txfNome.setText("Gel");
            txfMarca.setText("");
            txfQtd.setText("");
            txfCusto.setText("15");
            txfValor.setText("20");
            txfEstoque.setText("");
            cbForn.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroProduto", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProduto", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 03
         * 
         */
        try {
            CacheReport.makeAReport("CadastroProduto", "Iniciando teste 3...");
            
            txfNome.setText("Gel");
            txfMarca.setText("");
            txfQtd.setText("2");
            txfCusto.setText("");
            txfValor.setText("");
            txfEstoque.setText("");
            cbForn.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroProduto", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProduto", e.getMessage());
        }
        
        
        
        /**
         * 
         * TESTE 04
         * 
         */
        try {
            CacheReport.makeAReport("CadastroProduto", "Iniciando teste 4...");
            
            txfNome.setText("Gel");
            txfMarca.setText("");
            txfQtd.setText("2");
            txfCusto.setText("20");
            txfValor.setText("");
            txfEstoque.setText("");
            cbForn.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroProduto", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProduto", e.getMessage());
        }
        
        
        
        /**
         * 
         * TESTE 05
         * 
         */
        try {
            CacheReport.makeAReport("CadastroProduto", "Iniciando teste 5...");
            
            txfNome.setText("Gel");
            txfMarca.setText("");
            txfQtd.setText("2");
            txfCusto.setText("20");
            txfValor.setText("30");
            txfEstoque.setText("");
            cbForn.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroProduto", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProduto", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 06
         * 
         */
        try {
            CacheReport.makeAReport("CadastroProduto", "Iniciando teste 6...");
            
            txfNome.setText("Pomada");
            txfMarca.setText("Omo");
            txfQtd.setText("2");
            txfCusto.setText("19,90");
            txfValor.setText("30");
            txfEstoque.setText("");
            cbForn.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroProduto", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProduto", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 07
         * 
         */
        try {
            CacheReport.makeAReport("CadastroProduto", "Iniciando teste 7...");
            
            txfNome.setText("Tesoura");
            txfMarca.setText("Omo");
            txfQtd.setText("2");
            txfCusto.setText("19");
            txfValor.setText("29,90");
            txfEstoque.setText("");
            cbForn.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroProduto", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProduto", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 08
         * 
         */
        try {
            CacheReport.makeAReport("CadastroProduto", "Iniciando teste 8...");
            
            txfNome.setText("Tesoura");
            txfMarca.setText("Omo");
            txfQtd.setText("2");
            txfCusto.setText("19.90");
            txfValor.setText("29.90");
            txfEstoque.setText("13");
            cbForn.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroProduto", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroProduto", e.getMessage());
        }
    }

    public void setTxfNome(JTextField txfNome) {
        this.txfNome = txfNome;
    }

    public void setTxfMarca(JTextField txfMarca) {
        this.txfMarca = txfMarca;
    }

    public void setTxfQtd(JTextField txfQtd) {
        this.txfQtd = txfQtd;
    }

    public void setTxfCusto(JTextField txfCusto) {
        this.txfCusto = txfCusto;
    }

    public void setTxfValor(JTextField txfValor) {
        this.txfValor = txfValor;
    }

    public void setTxfEstoque(JTextField txfEstoque) {
        this.txfEstoque = txfEstoque;
    }

    public void setCbForn(JComboBox cbForn) {
        this.cbForn = cbForn;
    }

    public void setB(JButton b) {
        this.b = b;
    }
    
}
