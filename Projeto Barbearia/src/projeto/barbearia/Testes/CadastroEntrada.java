
package projeto.barbearia.Testes;

import javax.swing.*;

/**
 *
 * @author lucas
 */
public class CadastroEntrada extends Tester {

    private JTextField txfDesc;
    private JTextField txfValor;
    private JTextField txfDtPagto;
    private JTextField txfDtVcto;
    private JTextField txfQtd;
    private JComboBox cbProf;
    private JComboBox cbCliente;
    private JComboBox cbServico;
    private JComboBox cbProduto;
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
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 1...");
            
            txfDesc.setText("");
            txfValor.setText("");
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            txfQtd.setText("");
            cbProf.setSelectedIndex(0);
            cbCliente.setSelectedIndex(0);
            cbServico.setSelectedIndex(0);
            cbProduto.setSelectedIndex(0);
            cbNatureza.setSelectedIndex(0);
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 02
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 2...");
            
            txfDesc.setText("ESTE TEXTO SERÁ ALTERADO"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            txfQtd.setText("1"); //obrigatório
            cbProf.setSelectedIndex(1); //obrigatório
            cbCliente.setSelectedIndex(1); //obrigatório
            cbServico.setSelectedIndex(1); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(0); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(1); //obrigatório
            txfValor.setText(""); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 03
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 3...");
            
            txfDesc.setText("Coca-Cola"); //obrigatório
            txfValor.setText("20"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            cbProf.setSelectedIndex(1); //obrigatório
            cbCliente.setSelectedIndex(1); //obrigatório
            cbServico.setSelectedIndex(1); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(0); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(1); //obrigatório
            txfQtd.setText(""); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 04
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 4...");
            
            txfDesc.setText("Coca-Cola"); //obrigatório
            txfValor.setText("20"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            txfQtd.setText("2"); //obrigatório
            cbProf.setSelectedIndex(0); //obrigatório
            cbCliente.setSelectedIndex(1); //obrigatório
            cbServico.setSelectedIndex(1); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(0); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 05
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 5...");
            
            txfDesc.setText("Coca-Cola"); //obrigatório
            txfValor.setText("20"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            txfQtd.setText("2"); //obrigatório
            cbProf.setSelectedIndex(1); //obrigatório
            cbCliente.setSelectedIndex(0); //obrigatório
            cbServico.setSelectedIndex(1); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(0); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 06
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 6...");
            
            txfDesc.setText("Coca-Cola"); //obrigatório
            txfValor.setText("20"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            txfQtd.setText("2"); //obrigatório
            cbProf.setSelectedIndex(1); //obrigatório
            cbCliente.setSelectedIndex(1); //obrigatório
            cbServico.setSelectedIndex(0); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(0); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 07
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 7...");
            
            txfDesc.setText("Coca-Cola"); //obrigatório
            txfValor.setText("20"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            txfQtd.setText("2"); //obrigatório
            cbProf.setSelectedIndex(1); //obrigatório
            cbCliente.setSelectedIndex(1); //obrigatório
            cbServico.setSelectedIndex(0); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(1); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 08
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 8...");
            
            txfDesc.setText("Coca-Cola"); //obrigatório
            txfValor.setText("20"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            txfQtd.setText("2"); //obrigatório
            cbProf.setSelectedIndex(1); //obrigatório
            cbCliente.setSelectedIndex(1); //obrigatório
            cbServico.setSelectedIndex(1); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(1); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 09
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 9...");
            
            txfDesc.setText("Coca-Cola"); //obrigatório
            txfValor.setText("20"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            txfQtd.setText("2"); //obrigatório
            cbProf.setSelectedIndex(1); //obrigatório
            cbCliente.setSelectedIndex(1); //obrigatório
            cbServico.setSelectedIndex(0); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(1); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(0); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 10
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 10...");
            
            txfDesc.setText("Coca-Cola"); //obrigatório
            txfValor.setText("20,30"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            txfQtd.setText("2"); //obrigatório
            cbProf.setSelectedIndex(1); //obrigatório
            cbCliente.setSelectedIndex(1); //obrigatório
            cbServico.setSelectedIndex(0); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(1); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 11
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 11...");
            
            txfDesc.setText("Coca-Cola"); //obrigatório
            txfValor.setText("20.30"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("");
            txfQtd.setText("2"); //obrigatório
            cbProf.setSelectedIndex(1); //obrigatório
            cbCliente.setSelectedIndex(1); //obrigatório
            cbServico.setSelectedIndex(0); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(1); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 12
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 12...");
            
            txfDesc.setText("Coca-Cola"); //obrigatório
            txfValor.setText("20.30"); //obrigatório
            txfQtd.setText("2"); //obrigatório
            cbProf.setSelectedIndex(1); //obrigatório
            cbCliente.setSelectedIndex(1); //obrigatório
            cbServico.setSelectedIndex(0); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(1); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        /**
         * 
         * TESTE 13
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 13...");
            
            txfDesc.setText("Coca-Cola"); //obrigatório
            txfValor.setText("20.30"); //obrigatório
            txfDtPagto.setText("");
            txfDtVcto.setText("20/18/9999");
            txfQtd.setText("2"); //obrigatório
            cbProf.setSelectedIndex(1); //obrigatório
            cbCliente.setSelectedIndex(1); //obrigatório
            cbServico.setSelectedIndex(0); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(1); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (!confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
        }
        
        
        
        /**
         * 
         * TESTE 14
         * 
         */
        try {
            CacheReport.makeAReport("CadastroEntrada", "Iniciando teste 14...");
            
            txfDesc.setText("Coca-Cola"); //obrigatório
            txfValor.setText("20.30"); //obrigatório
            txfDtPagto.setText("20/04/2017");
            txfDtVcto.setText("23/04/2017");
            txfQtd.setText("2"); //obrigatório
            cbProf.setSelectedIndex(1); //obrigatório
            cbCliente.setSelectedIndex(1); //obrigatório
            cbServico.setSelectedIndex(0); //obrigatório ou subst. pelo produto
            cbProduto.setSelectedIndex(1); //obrigatório ou subst. pelo serviço
            cbNatureza.setSelectedIndex(1); //obrigatório
            b.doClick();
            
            CacheReport.makeAReport("CadastroEntrada", (confirmarComUsuario()) ? "Teste ok ;)" : "Falha no teste :(");
        } catch (Exception e) {
            CacheReport.makeAReport("CadastroEntrada", e.getMessage());
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

    public void setTxfQtd(JTextField txfQtd) {
        this.txfQtd = txfQtd;
    }

    public void setCbProf(JComboBox cbProf) {
        this.cbProf = cbProf;
    }

    public void setCbCliente(JComboBox cbCliente) {
        this.cbCliente = cbCliente;
    }

    public void setCbServico(JComboBox cbServico) {
        this.cbServico = cbServico;
    }

    public void setCbProduto(JComboBox cbProduto) {
        this.cbProduto = cbProduto;
    }

    public void setCbNatureza(JComboBox cbNatureza) {
        this.cbNatureza = cbNatureza;
    }

    public void setB(JButton b) {
        this.b = b;
    }
    
}
