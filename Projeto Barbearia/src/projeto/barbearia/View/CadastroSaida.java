
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import projeto.barbearia.Controller.ouvinteCancelar;
import projeto.barbearia.Model.FormatadorParaSQLData;
import projeto.barbearia.Model.Saida;
import projeto.barbearia.Model.Tipos.*;

/**
 * CLASSE COM TESTES INSTALADOS. VERIFICAR NO FIM DO CONSTRUTOR
 * @author metr
 */
public class CadastroSaida extends JFrame{
    private JPanel painel1;
    private JPanel painel2;
    private JPanel painel3;
    private JPanel painel4;
    private ComboProfissional cbProfissional;
    private ComboFornecedor cbFornecedor;
    private ComboNatureza cbNatureza;
    private JTextField txfDescricao;
    private JTextField txfValor;
    private JDataTextField txfDtPagto;
    private JDataTextField txfDtVcto;
    private int profissionalid = 0;
    private int fornecedorid = 0;
    private int naturezaid = 0;
    private String mensagemDeErro;
    private int saidaid;
    private TipoCadastro tipo;
    private projeto.barbearia.Testes.CadastroSaida tester;
    
    public CadastroSaida(TipoCadastro tipo, int saidaid) {
        /**
         * tests
         */
        tester = new projeto.barbearia.Testes.CadastroSaida();
        
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints bag = new GridBagConstraints();
        setLayout(bagLayout);
        setSize(650,330);
        setResizable(false);
        setTitle("Cadastrar Saída");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        Saida ns = new Saida();
        this.tipo = tipo;
        this.saidaid = saidaid;
        if (this.tipo == TipoCadastro.ALTERACAO) {
            ns = new Saida(saidaid);
            this.profissionalid = ns.getProfissionalid();
            this.fornecedorid = ns.getFornecedorid();
            this.naturezaid = ns.getNaturezaid();
        }
        
        painel1();
        painel2();
        painel3();
        painel4();
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.weightx = 1;
        add(painel1, bag);
        
        bag.weighty = 1;
        bag.gridy = 1;
        add(painel2, bag);
        
        bag.gridy = 2;
        bag.anchor = GridBagConstraints.NORTH;
        add(painel3, bag);
        
        bag.gridy = 3;
        bag.anchor = GridBagConstraints.SOUTH;
        bag.fill = GridBagConstraints.NONE;
        add(painel4, bag);
        
        if (this.tipo == TipoCadastro.ALTERACAO) {
            this.txfDescricao.setText(ns.getNome_bd());
            this.txfValor.setText(String.valueOf(ns.getValor_bd()).replace('.',','));
            
            if (ns.getDtpagto_bd() != null) {
                this.txfDtPagto.setText(new FormatadorParaSQLData().reverse(ns.getDtpagto_bd().toString()));
            }
            
            if (ns.getDtvcto_bd()!= null) {
                this.txfDtVcto.setText(new FormatadorParaSQLData().reverse(ns.getDtvcto_bd().toString()));
            }
            
        }
        
        
        /**
         * tests
         */
        tester.setTxfDesc(txfDescricao);
        tester.setTxfValor(txfValor);
        tester.setTxfDtPagto(txfDtPagto);
        tester.setTxfDtVcto(txfDtVcto);
        tester.setCbForn(cbFornecedor);
        tester.setCbProf(cbProfissional);
        tester.setCbNatureza(cbNatureza);
        //tester.executarTeste();
        
    }
    private void painel1() {
        painel1 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        //JLabels
        JLabel textoDesc = new JLabel("Descrição*:");
        textoDesc.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoValor = new JLabel("Valor*:");
        textoValor.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //JTextFields
        txfDescricao = new JTextField(30);
        txfDescricao.setFont(new Font("Dialog", Font.PLAIN, 17));
        txfValor = new JTextField(10);
        txfValor.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //Adicionando Itens
        bag.insets = new Insets(5,5,5,5);
        bag.anchor = GridBagConstraints.WEST;
        bag.weightx = 1;
        bag.fill = GridBagConstraints.HORIZONTAL;
        painel1.add(textoDesc, bag);
        
        bag.gridy = 1;
        painel1.add(txfDescricao, bag);
        
        bag.gridy = 0;
        bag.gridx = 1;
        bag.anchor = GridBagConstraints.WEST;
        painel1.add(textoValor, bag);
        
        bag.gridy = 1;
        painel1.add(txfValor, bag);
    }
    
    private void painel2() {
        painel2 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        //JLabels
        JLabel textoTipo = new JLabel("Tipo de Saída*:");
        textoTipo.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoData = new JLabel("Data do Pagto.:");
        textoData.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoPrazo = new JLabel("Prazo do Pagto.:");
        textoPrazo.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //JTextFields e ComboBox
        cbNatureza = new ComboNatureza();
        Object[] args = new Object[1];
        args[0] = naturezaid;
        cbNatureza.preencherComboPara(ComboNaturezaTipo.CADASTRO_SAIDA, args);
        txfDtPagto = new JDataTextField();
        txfDtPagto.setColumns(15);
        txfDtPagto.setFont(new Font("Dialog", Font.PLAIN, 17));
        txfDtVcto = new JDataTextField();
        txfDtVcto.setColumns(15);
        txfDtVcto.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //Adicionando Itens
        bag.weightx = 1;
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.insets = new Insets(5,5,5,5);
        bag.anchor = GridBagConstraints.WEST;
        painel2.add(textoTipo, bag);
        
        bag.gridy = 1;
        painel2.add(cbNatureza, bag);
        
        bag.gridy = 0;
        bag.fill = GridBagConstraints.NONE;
        bag.gridx = 1;
        bag.weightx = 0;
        painel2.add(textoData, bag);
        
        bag.gridy = 1;
        painel2.add(txfDtPagto, bag);
        
        bag.gridy = 0;
        bag.gridx = 2;
        painel2.add(textoPrazo, bag);
        
        bag.gridy = 1;
        painel2.add(txfDtVcto, bag);
    }
    
    private void painel3() {
        painel3 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        //JLabels
        JLabel textoProfissional = new JLabel("Profissional:");
        textoProfissional.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoFornecedor = new JLabel("Fornecedor:");
        textoFornecedor.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //JTextFields e ComboBox
        cbProfissional = new ComboProfissional();
        Object[] args = new Object[1];
        args[0] = profissionalid;
        cbProfissional.preencherComboPara(ComboProfissionalTipo.CADASTRO_SAIDA, args);
        cbFornecedor = new ComboFornecedor();
        args[0] = fornecedorid;
        cbFornecedor.preencherComboPara(ComboFornecedorTipo.CADASTRO_SAIDA, args);
        
        //Impedindo seleção simultânea dos dois
        cbFornecedor.addActionListener((e) -> impedirFornecedorEProfissional("FORNECEDOR"));
        cbProfissional.addActionListener((e) -> impedirFornecedorEProfissional("PROFISSIONAL"));
        
        //Adicionando Itens
        bag.weightx = 0.3;
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.insets = new Insets(5,5,5,5);
        bag.anchor = GridBagConstraints.WEST;
        painel3.add(textoProfissional, bag);
        
        bag.gridy = 1;
        painel3.add(cbProfissional, bag);
        
        bag.gridy = 0;
        bag.gridx = 1;
        bag.insets = new Insets(5,5,5,50);
        painel3.add(textoFornecedor, bag);
        
        bag.gridy = 1;
        painel3.add(cbFornecedor, bag);
    }
    
    private void painel4() {
        painel4 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        button_icone salvar = new button_icone("Salvar", new Color(34,139,34), Color.white, 100, 40, 17);
        button_icone cancelar = new button_icone("Cancelar", new Color(205,92,92), Color.white, 100, 40, 17);
        JFrame janela = this;
        
        salvar.addActionListener((e) -> onClickSalvar());
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ouvinteCancelar().cancelar(janela);
            }
            
        });
        
        /**
         * tests
         */
        tester.setB(salvar);
        
        bag.insets = new Insets(5,5,5,5);
        bag.weightx = 1;
        bag.fill = GridBagConstraints.BOTH;
        bag.gridx = 0;
        bag.gridy = 0;
        painel4.add(salvar, bag);
        
        bag.gridx = 1;
        painel4.add(cancelar, bag);
    }
    
    private void impedirFornecedorEProfissional(String qual) {
        
        if (qual.toUpperCase().equals("FORNECEDOR")) {
            if (cbFornecedor.getSelectedIndex() > 0) cbProfissional.setSelectedIndex(0);
        } else {
            if (cbProfissional.getSelectedIndex() > 0) cbFornecedor.setSelectedIndex(0);
        }
        
    }
    
    private void onClickSalvar() {
        
        //adquirindo todos os dados
        String stDescricao = txfDescricao.getText(), stValor = txfValor.getText(), 
                stDtPagto = txfDtPagto.getTextMinusMask(), stDtVcto = txfDtVcto.getTextMinusMask();
        profissionalid = getIdNoCombo(cbProfissional.getSelectedItem().toString(), 
                cbProfissional.getSelectedIndex());
        fornecedorid = getIdNoCombo(cbFornecedor.getSelectedItem().toString(), 
                cbFornecedor.getSelectedIndex());
        naturezaid = getIdNoCombo(cbNatureza.getSelectedItem().toString(), 
                cbNatureza.getSelectedIndex());
        
        validarDadosESalvar(stDescricao, stValor, stDtPagto, 
                stDtVcto, profissionalid, fornecedorid, naturezaid);
        
    }
    
    private int getIdNoCombo(String s, int index) {
        int retorno = index;
        if (index > 0) {
            int space = s.indexOf(" ");
            String sid = s.substring(0, space);
            retorno = Integer.valueOf(sid);
        }
        return retorno;
    }
    
    private void validarDadosESalvar(String stDescricao, String stValor, String stDtPagto, String stDtVcto, 
            int profissionalid, int fornecedorid, int naturezaid) {
        
        boolean validacao = true;
        Saida ns = new Saida();
        
        //validando descrição
        if (stDescricao.equals("")) {
            validacao = false;
            mensagemDeErro = "Por favor, preencha uma DESCRIÇÃO.";
        } else {
            ns.setNome_bd(stDescricao.toUpperCase());
        }
        
        //validando valor
        if (stValor.equals("")) {
            validacao = false;
            mensagemDeErro = "Por favor, preencha um VALOR.";
        } else {
            try {
                Float f = Float.valueOf(stValor.replace(',', '.'));
                ns.setValor_bd(f);
            } catch (Exception e) {
                validacao = false;
                mensagemDeErro = "Por favor, preencha o campo VALOR com um número.";
            }
        }
        
        //validando data de pagamento
        if (!stDtPagto.equals("")) {
            try {
                FormatadorParaSQLData f = new FormatadorParaSQLData();
                Date d = Date.valueOf(f.formatarParaSQLData(stDtPagto));
                ns.setDtpagto_bd(d);
            } catch (Exception e) {
                validacao = false;
                mensagemDeErro = "Por favor, preencha uma DATA DE PAGAMENTO em formato dd/mm/aaaa.";
            }
        } else {
            ns.setDtpagto_bd(null);
        }
        
        //validando data de vencimento
        if (!stDtVcto.equals("")) {
            try {
                FormatadorParaSQLData f = new FormatadorParaSQLData();
                Date d = Date.valueOf(f.formatarParaSQLData(stDtVcto));
                ns.setDtvcto_bd(d);
            } catch (Exception e) {
                validacao = false;
                mensagemDeErro = "Por favor, preencha uma DATA DE VENCIMENTO em formato dd/mm/aaaa.";
            }
        } else {
            ns.setDtvcto_bd(null);
        }
        
        
        //validando natureza
        if (naturezaid == 0) {
            validacao = false;
            mensagemDeErro = "Por favor, selecione um TIPO DE SAÍDA.";
        } else {
            ns.setNaturezaid(naturezaid);
        }
        
        //validando profissional e fornecedor
        if (profissionalid == 0 && fornecedorid == 0) {
            validacao = false;
            mensagemDeErro = "Por favor, selecione um PROFISSIONAL ou FORNECEDOR.";
        } else {
            ns.setProfissionalid(profissionalid);
            ns.setFornecedorid(fornecedorid);
        }
        
        if (validacao) {
            boolean sucesso;
            
            if (this.tipo == TipoCadastro.NOVO_CADASTRO) {
                sucesso = ns.cadastrar();
            } else {
                sucesso = Saida.editar(saidaid, ns.getNome_bd(), ns.getValor_bd(), 
                        ns.getDtpagto_bd(), ns.getDtvcto_bd(), ns.getProfissionalid(), 
                        ns.getFornecedorid(), ns.getNaturezaid());
            }
            
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "O cadastro da saída foi realizado com SUCESSO.",
                    "Cadastro Finalizado", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                if (!tester.isTestingNow()) this.dispose();
                
                TabelaSaida.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
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
    
}
