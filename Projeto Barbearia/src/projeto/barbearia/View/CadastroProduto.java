package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import projeto.barbearia.Controller.ouvinteCancelar;
import projeto.barbearia.Controller.ouvintePainelFornecedores;
import projeto.barbearia.Controller.ouvinteSalvarProduto;
import projeto.barbearia.Model.Produto;
import projeto.barbearia.Model.Tipos.ComboFornecedorTipo;
import projeto.barbearia.Model.Tipos.TipoCadastro;
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

/**
 * CLASSE COM TESTES INSTALADOS. VERIFICAR NO FIM DO CONSTRUTOR
 * @author lucas
 */
public class CadastroProduto extends JFrame{
    
    private JPanel painel1;
    private JPanel painel2;
    private JPanel painel3;
    private JPanel painel4;
    private JTextField nome;
    private JTextField quantidade;
    private JTextField marca;
    private JTextField custo;
    private JTextField valorDeVenda;
    private JTextField estoqueMinimo;
    private ComboFornecedor fornecedorid;
    private TipoCadastro tipoCadastro;
    private int produtoid;
    private String mensagemDeErro;
    private projeto.barbearia.Testes.CadastroProduto tester;
    
    public CadastroProduto(TipoCadastro tipoCadastro, int produtoid) {
        /**
         * tests
         */
        tester = new projeto.barbearia.Testes.CadastroProduto();
        
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints bag = new GridBagConstraints();
        setLayout(bagLayout);
        setSize(800,350);
        setResizable(false);
        setTitle("Cadastrar Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        this.produtoid = produtoid;
        
        painel1();
        painel2();
        painel3();
        painel4();
        
        bag.weightx = 1;
        
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.gridx = 0;
        bag.gridy = 0;
        bag.anchor = GridBagConstraints.NORTH;
        add(painel1, bag);
        
        bag.gridy = 1;
        add(painel2, bag);
        
        bag.weighty = 1;
        bag.gridy = 2;
        add(painel3, bag);
        
        bag.insets = new Insets(0, 0, 20, 0);
        bag.gridy = 3;
        bag.anchor = GridBagConstraints.SOUTH;
        add(painel4, bag);
        
        this.tipoCadastro = tipoCadastro;
        
        if (this.tipoCadastro == TipoCadastro.ALTERACAO) {
            
            Produto p = new Produto(produtoid);
            this.nome.setText(p.getNome_bd());
            this.quantidade.setText(Integer.toString(p.getQtd_bd()));
            this.marca.setText(p.getMarca_bd());
            this.custo.setText(Float.toString(p.getCusto_bd()));
            this.valorDeVenda.setText(Float.toString(p.getValor_bd()));
            this.estoqueMinimo.setText(Integer.toString(p.getEstoqueMinimo_bd()));
            
        }
        
        /**
         * tests
         */
        tester.setTxfNome(nome);
        tester.setTxfMarca(marca);
        tester.setTxfQtd(quantidade);
        tester.setTxfCusto(custo);
        tester.setTxfValor(valorDeVenda);
        tester.setTxfEstoque(estoqueMinimo);
        tester.setCbForn(fornecedorid);
        //tester.executarTeste();
        
    }
    
    private void painel1() {
        painel1 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        
        //JLabel's
        JLabel textoNome = new JLabel("Nome*:");
        textoNome.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        JLabel textoQuantidade = new JLabel("Quantidade*:");
        textoQuantidade.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        JLabel textoMarca = new JLabel("Marca:");
        textoMarca.setFont(new Font("Dialog", Font.PLAIN, 17));
        //JTextField's
        nome = new JTextField(25);
        quantidade = new JTextField(10);
        marca = new JTextField(15);
        
        bag.insets = new Insets(5,5,5,5);
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.gridx = 0;
        bag.gridy = 0;
        bag.weightx = 1;
        painel1.add(textoNome, bag);
        
        bag.gridy = 1;
        painel1.add(nome, bag);
        
        bag.gridx = 1;
        bag.gridy = 0;
        painel1.add(textoMarca, bag);
        
        bag.gridy = 1;
        painel1.add(marca, bag);
        
        bag.gridx = 2;
        bag.gridy = 0;
        painel1.add(textoQuantidade, bag);
        
        bag.gridy = 1;
        painel1.add(quantidade, bag);
    }
    
    private void painel2() {
        painel2 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        
        //JLabel's
        JLabel textoCusto = new JLabel("Custo:");
        textoCusto.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        JLabel textoValorDeVenda = new JLabel("Valor de Venda*:");
        textoValorDeVenda.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        JLabel textoEstoqueMinimo = new JLabel("Estoque Mínimo:");
        textoEstoqueMinimo.setFont(new Font("Dialog", Font.PLAIN, 17));
        //JTextField's
        custo = new JTextField(20);
        valorDeVenda = new JTextField(15);
        estoqueMinimo = new JTextField(15);
        
        bag.insets = new Insets(5,5,5,5);
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.weightx = 1;
        bag.gridx = 0;
        bag.gridy = 0;
        painel2.add(textoCusto, bag);
        
        bag.gridy = 1;
        painel2.add(custo, bag);
        
        bag.gridx = 1;
        bag.gridy = 0;
        painel2.add(textoValorDeVenda, bag);
        
        bag.gridy = 1;
        painel2.add(valorDeVenda, bag);
        
        bag.gridx = 2;
        bag.gridy = 0;
        painel2.add(textoEstoqueMinimo, bag);
        
        bag.gridy = 1;
        painel2.add(estoqueMinimo, bag);
    }
    
    private void painel3() {
        painel3 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        
        //JLabel's
        JLabel textoFornecedor = new JLabel("Fornecedor:");
        textoFornecedor.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //JButton
        JButton novoForn = new JButton("+ Novo Forncedor");
        novoForn.addActionListener((e) -> onClickNovoFornecedor());
        
        //ComboFornecedor
        Object[] arg_id = new Object[1];
        arg_id[0] = produtoid;
        fornecedorid = new ComboFornecedor();
        fornecedorid.preencherComboPara(ComboFornecedorTipo.CADASTRO_PRODUTOS, arg_id);
        
        bag.insets = new Insets(5,5,5,5);
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.weightx = 1;
        bag.gridx = 0;
        bag.gridy = 0;
        painel3.add(textoFornecedor, bag);
        
        bag.gridx = 0;
        bag.gridy = 1;
        painel3.add(fornecedorid, bag);
        
        bag.fill = GridBagConstraints.NONE;
        bag.anchor = GridBagConstraints.WEST;
        bag.gridx = 1;
        bag.gridy = 1;
        painel3.add(novoForn, bag);
    }
    
    private void painel4() {
        painel4 = new JPanel();
        GridBagConstraints bag = new GridBagConstraints();
        
        button_icone salvar = new button_icone("Salvar", new Color(34,139,34), Color.white, 100, 40, 17);
        button_icone cancelar = new button_icone("Cancelar", new Color(205,92,92), Color.white, 100, 40, 17);
        JFrame janela = this;
        
        salvar.addActionListener((e) -> onClickSalvar(janela));
        cancelar.addActionListener((e) -> new ouvinteCancelar().cancelar(janela));
        
        /**
         * tests
         */
        tester.setB(salvar);
        
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.weightx = 1;
        bag.gridx = 0;
        bag.gridy = 0;
        painel4.add(salvar, bag);
            
        bag.gridx = 1;
        painel4.add(cancelar, bag);
            
    }
    
    private void onClickNovoFornecedor() {
        
        CacheAtualizacao.adicionarItemAoCache(fornecedorid, "CadastroFornecedor");
        
        new ouvintePainelFornecedores().onClickAddFornecedor();
    }
    
    private void onClickSalvar(JFrame janela) {
        if (validarDados()) {
            boolean sucesso;
            
            //Verificando Profissional selecionado
            String s = fornecedorid.getSelectedItem().toString();
            int space;
            String sid;
            int fid;
            
            if (fornecedorid.getSelectedIndex() == 0) {
                fid = 0;
            } else {
                space = s.indexOf(" ");
                sid = s.substring(0, space);
                fid = Integer.valueOf(sid);
            }
            

            sucesso = ouvinteSalvarProduto.salvar(nome.getText(), marca.getText(), 
                    Integer.valueOf(quantidade.getText()), Float.valueOf(custo.getText().replace(',', '.')), 
                    Float.valueOf(valorDeVenda.getText().replace(',', '.')), Integer.valueOf(estoqueMinimo.getText()), 
                    fid, tipoCadastro, produtoid);

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "O cadastro do produto foi realizado com SUCESSO.",
                    "Cadastro Finalizado", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                if (!tester.isTestingNow()) janela.dispose();

                TabelaProduto.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um problema em seu cadastro. "
                        + "Por favor, tente novamente mais tarde.",
                    "Falha", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, mensagemDeErro,
                    "Dados Incorretos", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validarDados() {
        
        boolean retorno = true;
        int testeInt;
        float testeFloat;
        
        //Testando nome
        if (nome.getText().equals("")) {
            mensagemDeErro = "Preencha um NOME para o produto!";
            retorno = false;
        }
        
        //Testando quantidade
        try {
            testeInt = Integer.valueOf(quantidade.getText());
        } catch (Exception e) {
            mensagemDeErro = "Preencha um valor numérico para QUANTIDADE!";
            retorno = false;
        }
        
        //Testando valor de Custo
        try {
            testeFloat = Float.valueOf(custo.getText().replace(',', '.'));
        } catch (Exception e) {
            custo.setText("0");
        }
        
        //Testando valor de Venda
        try {
            testeFloat = Float.valueOf(valorDeVenda.getText().replace(',', '.'));
        } catch (Exception e) {
            mensagemDeErro = "Preencha o VALOR DE VENDA com um valor numérico!";
            retorno = false;
        }
        
        //Testando estoque mínimo
        try {
            testeInt = Integer.valueOf(estoqueMinimo.getText());
        } catch (Exception e) {
            estoqueMinimo.setText("0");
        }
        
        return retorno;
        
    }
    
}
