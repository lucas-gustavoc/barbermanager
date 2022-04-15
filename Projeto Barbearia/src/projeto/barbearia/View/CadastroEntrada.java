
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import projeto.barbearia.Model.FormatadorParaSQLData;
import projeto.barbearia.Controller.ouvinteCancelar;
import projeto.barbearia.Model.Cliente;
import projeto.barbearia.Model.Entrada;
import projeto.barbearia.Model.Produto;
import projeto.barbearia.Model.Servico;
import projeto.barbearia.Model.Tipos.*;

/**
 * CLASSE COM TESTES INSTALADOS. VERIFICAR NO FIM DO CONSTRUTOR
 * @author metr
 */
public class CadastroEntrada extends JFrame{
    private JPanel painel1;
    private JPanel painel2;
    private JPanel painel3;
    private JPanel painel4;
    private ComboProfissional cbProfissional;
    private ComboCliente cbCliente;
    private ComboServico cbServico;
    private ComboProduto cbProduto;
    private ComboNatureza cbNatureza;
    private JTextField txfDescricao;
    private JTextField txfValor;
    private JDataTextField txfDtPagto;
    private JDataTextField txfDtVcto;
    private JTextField txfQtd;
    private int profissionalid = 0;
    private int clienteid = 0;
    private int servicoid = 0;
    private int produtoid = 0;
    private int naturezaid = 0;
    private String mensagemDeErro;
    private int entradaid;
    private TipoCadastro tipo;
    private projeto.barbearia.Testes.CadastroEntrada tester;
    private DescricaoEntrada textoDaDescricao = new DescricaoEntrada();
    
    public CadastroEntrada(TipoCadastro tipo, int entradaid) {
        /**
         * tests
         */
        tester = new projeto.barbearia.Testes.CadastroEntrada();
        
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints bag = new GridBagConstraints();
        setLayout(bagLayout);
        setSize(650,330);
        setResizable(false);
        setTitle("Cadastrar Entrada");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        Entrada ne = new Entrada();
        this.tipo = tipo;
        this.entradaid = entradaid;
        if (this.tipo == TipoCadastro.ALTERACAO) {
            ne = new Entrada(entradaid);
            this.profissionalid = ne.getProfissionalid();
            this.clienteid = ne.getClienteid();
            this.servicoid = ne.getServicoid();
            this.produtoid = ne.getProdutoid();
            this.naturezaid = ne.getNaturezaid();
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
            this.txfDescricao.setText(ne.getNome_bd());
            this.txfValor.setText(String.valueOf(ne.getValor_bd()).replace('.',','));
            this.txfQtd.setText(String.valueOf(ne.getQtd_bd()));
            
            if (ne.getDtpagto_bd() != null) {
                this.txfDtPagto.setText(new FormatadorParaSQLData().reverse(ne.getDtpagto_bd().toString()));
            }
            
            if (ne.getDtvcto_bd()!= null) {
                this.txfDtVcto.setText(new FormatadorParaSQLData().reverse(ne.getDtvcto_bd().toString()));
            }
            
        } else {
            
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date datem = new java.util.Date();
            
            this.txfDtPagto.setText(dateFormat.format(datem));
            this.txfDtVcto.setText(dateFormat.format(datem));
        }
        
        /**
         * tests
         */
        tester.setTxfDesc(txfDescricao);
        tester.setTxfValor(txfValor);
        tester.setTxfQtd(txfQtd);
        tester.setTxfDtPagto(txfDtPagto);
        tester.setTxfDtVcto(txfDtVcto);
        tester.setCbProf(cbProfissional);
        tester.setCbProduto(cbProduto);
        tester.setCbCliente(cbCliente);
        tester.setCbServico(cbServico);
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
        JLabel textoProfissas = new JLabel("Profissional*:");
        textoValor.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //JTextFields
        txfDescricao = new JTextField(30);
        txfDescricao.setText("NOVA VENDA");
        txfDescricao.setFont(new Font("Dialog", Font.PLAIN, 17));
        txfValor = new JTextField(10);
        txfValor.setFont(new Font("Dialog", Font.PLAIN, 17));
        Object[] args = new Object[1];
        args[0] = profissionalid;
        cbProfissional = new ComboProfissional();
        cbProfissional.preencherComboPara(ComboProfissionalTipo.CADASTRO_ENTRADA, args);
        
        //Adicionando Itens
        bag.insets = new Insets(5,5,5,5);
        bag.anchor = GridBagConstraints.WEST;
        bag.weightx = 2;
        bag.fill = GridBagConstraints.HORIZONTAL;
        painel1.add(textoDesc, bag);
        
        bag.gridy = 1;
        painel1.add(txfDescricao, bag);
        
        bag.weightx = 1;
        bag.gridy = 0;
        bag.gridx = 1;
        bag.anchor = GridBagConstraints.WEST;
        painel1.add(textoValor, bag);
        
        bag.gridy = 1;
        painel1.add(txfValor, bag);
        
        bag.gridy = 0;
        bag.gridx = 2;
        bag.anchor = GridBagConstraints.WEST;
        painel1.add(textoProfissas, bag);
        
        bag.gridy = 1;
        painel1.add(cbProfissional, bag);
    }
    
    private void painel2() {
        painel2 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        //JLabels
        JLabel textoCliente = new JLabel("Cliente*:");
        textoCliente.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoData = new JLabel("Data do Pagto.:");
        textoData.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoPrazo = new JLabel("Prazo do Pagto.:");
        textoPrazo.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //JTextFields e ComboBox
        Object[] args = new Object[1];
        args[0] = clienteid;
        cbCliente = new ComboCliente();
        cbCliente.preencherComboPara(ComboClienteTipo.CADASTRO_ENTRADA, args);
        cbCliente.addActionListener((e) -> tratarDescricao());
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
        painel2.add(textoCliente, bag);
        
        bag.gridy = 1;
        painel2.add(cbCliente, bag);
        
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
        JLabel textoService = new JLabel("Serviço:");
        textoService.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoProduto = new JLabel("Produto:");
        textoProduto.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoQtd = new JLabel("Qtd.*:");
        textoQtd.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoTipo = new JLabel("Tipo de Entrada*:");
        textoTipo.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //JTextFields e ComboBox
        Object[] args = new Object[1];
        args[0] = servicoid;
        cbServico = new ComboServico();
        cbServico.preencherComboPara(ComboServicoTipo.CADASTRO_ENTRADA, args);
        args[0] = produtoid;
        cbProduto = new ComboProduto();
        cbProduto.preencherComboPara(ComboProdutoTipo.CADASTRO_ENTRADA, args);
        args[0] = naturezaid;
        cbNatureza = new ComboNatureza();
        cbNatureza.preencherComboPara(ComboNaturezaTipo.CADASTRO_ENTRADA, args);
        txfQtd = new JTextField(2);
        txfQtd.setFont(new Font("Dialog", Font.PLAIN, 17));
        txfQtd.setText("1");
        txfQtd.setHorizontalAlignment(JTextField.CENTER);
        txfQtd.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e) {
                tratarValor();
            }
        });
        
        
        //impedindo a seleção simultânea de produto e serviço
        cbServico.addActionListener((e) -> impedirProdutoEServico("SERVICO"));
        cbProduto.addActionListener((e) -> impedirProdutoEServico("PRODUTO"));
        
        //Adicionando Itens
        bag.weightx = 0.3;
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.insets = new Insets(5,5,5,5);
        bag.anchor = GridBagConstraints.WEST;
        painel3.add(textoService, bag);
        
        bag.gridy = 1;
        painel3.add(cbServico, bag);
        
        bag.gridy = 0;
        bag.gridx = 1;
        painel3.add(textoProduto, bag);
        
        bag.gridy = 1;
        painel3.add(cbProduto, bag);
        
        bag.gridy = 0;
        bag.gridx = 2;
        painel3.add(textoQtd, bag);
        
        bag.gridy = 1;
        painel3.add(txfQtd, bag);
        
        bag.gridy = 0;
        bag.insets = new Insets(5,5,5,50);
        bag.gridx = 3;
        painel3.add(textoTipo, bag);
        
        bag.gridy = 1;
        painel3.add(cbNatureza, bag);
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
    
    private void impedirProdutoEServico(String qual) {
        
        if (qual.toUpperCase().equals("SERVICO")) {
            if (cbServico.getSelectedIndex() > 0) {
                cbProduto.setSelectedIndex(0);
                tratarValor();
            }
            
        } else {
            if (cbProduto.getSelectedIndex() > 0) {
                cbServico.setSelectedIndex(0);
                tratarValor();
            }
        }
        tratarDescricao();
        
        
    }
    
    private void tratarValor() {
        int s = cbServico.getSelectedIndex(), p = cbProduto.getSelectedIndex();
        int qtd;
        String sQtd = txfQtd.getText();
        
        try {
            qtd = Integer.valueOf(sQtd);
        } catch (Exception e) {
            qtd = 1;
            txfQtd.setText("1");
        }
        
        if ((s + p) == 0) {
            //Nada selecionado
        } else if (s > 0) {
            //Serviço selecionado
            String c = cbServico.getSelectedItem().toString();
            int sid = getIdNoCombo(c, s);
            Servico itemS = new Servico(sid);
            txfValor.setText(String.valueOf(qtd * itemS.getValor()).replace('.', ','));
        } else {
            //Produto selecionado
            String c = cbProduto.getSelectedItem().toString();
            int pid = getIdNoCombo(c, p);
            Produto itemP = new Produto(pid);
            txfValor.setText(String.valueOf(qtd * itemP.getValor_bd()).replace('.', ','));
        }
    }
    
    private void tratarDescricao() {
        //Definindo Texto do cliente
        if (cbCliente.getSelectedIndex() == 0) {
            textoDaDescricao.setCliente("");
        } else {
            String c = cbCliente.getSelectedItem().toString();
            int starts = c.indexOf('-');
            textoDaDescricao.setCliente(c.substring(starts + 2));
        }
        
        //Definindo texto Produt/Serviço/Pacote
        int s = cbServico.getSelectedIndex(), p = cbProduto.getSelectedIndex();
        if ((s + p) == 0) {
            //Nada selecionado
            textoDaDescricao.setProdOuServ("", -1);
        } else if (s > 0) {
            //Serviço selecionado
            String c = cbServico.getSelectedItem().toString();
            int sid = getIdNoCombo(c, s), starts = c.indexOf('-');
            int tipoDaDescricao = (Servico.isPackage(sid)) ? DescricaoEntrada.PACOTE : DescricaoEntrada.SERVICO;
            textoDaDescricao.setProdOuServ(c.substring(starts + 2), tipoDaDescricao);
        } else {
            //Produto selecionado
            String c = cbProduto.getSelectedItem().toString();
            int starts = c.indexOf('-');
            textoDaDescricao.setProdOuServ(c.substring(starts + 2), DescricaoEntrada.PRODUTO);
        }
        
        txfDescricao.setText(textoDaDescricao.getDescricao());
        
    }
    
    private void onClickSalvar() {
        
        //adquirindo todos os dados
        String stDescricao = txfDescricao.getText(), stValor = txfValor.getText(), 
                stDtPagto = txfDtPagto.getTextMinusMask(), stDtVcto = txfDtVcto.getTextMinusMask(), 
                stQtd = txfQtd.getText();
        profissionalid = getIdNoCombo(cbProfissional.getSelectedItem().toString(), 
                cbProfissional.getSelectedIndex());
        clienteid = getIdNoCombo(cbCliente.getSelectedItem().toString(), 
                cbCliente.getSelectedIndex());
        servicoid = getIdNoCombo(cbServico.getSelectedItem().toString(), 
                cbServico.getSelectedIndex());
        produtoid = getIdNoCombo(cbProduto.getSelectedItem().toString(), 
                cbProduto.getSelectedIndex());
        naturezaid = getIdNoCombo(cbNatureza.getSelectedItem().toString(), 
                cbNatureza.getSelectedIndex());
        
        validarDadosESalvar(stDescricao, stValor, stDtPagto, 
                stDtVcto, profissionalid, clienteid, 
                servicoid, produtoid, naturezaid, stQtd);
        
    }
    
    /**
     * Método para obter o id presente em um item de combo.
     * 
     * @param s String contendo todo o texto do item selecionado do Combo (inclusive o id)
     * @param index int com o index selecionado no Combo
     * @return int com o id identificado
     */
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
            int profissionalid, int clienteid, int servicoid, int produtoid, int naturezaid, String qtd) {
        
        boolean validacao = true;
        Entrada ne = new Entrada();
        
        //validando descrição
        if (stDescricao.equals("")) {
            validacao = false;
            mensagemDeErro = "Por favor, preencha uma DESCRIÇÃO.";
        } else {
            ne.setNome_bd(stDescricao.toUpperCase());
        }
        
        //validando valor
        if (stValor.equals("")) {
            validacao = false;
            mensagemDeErro = "Por favor, preencha um VALOR.";
        } else {
            try {
                Float f = Float.valueOf(stValor.replace(',', '.'));
                ne.setValor_bd(f);
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
                ne.setDtpagto_bd(d);
            } catch (Exception e) {
                validacao = false;
                mensagemDeErro = "Por favor, preencha uma DATA DE PAGAMENTO em formato dd/mm/aaaa.";
            }
        } else {
            ne.setDtpagto_bd(null);
        }
        
        //validando data de vencimento
        if (!stDtVcto.equals("")) {
            try {
                FormatadorParaSQLData f = new FormatadorParaSQLData();
                Date d = Date.valueOf(f.formatarParaSQLData(stDtVcto));
                ne.setDtvcto_bd(d);
            } catch (Exception e) {
                validacao = false;
                mensagemDeErro = "Por favor, preencha uma DATA DE VENCIMENTO em formato dd/mm/aaaa.";
            }
        } else {
            ne.setDtvcto_bd(null);
        }
        
        //validando profissional
        if (profissionalid == 0) {
            validacao = false;
            mensagemDeErro = "Por favor, selecione um PROFISSIONAL.";
        } else {
            ne.setProfissionalid(profissionalid);
        }
        
        //validando cliente
        if (clienteid == 0) {
            validacao = false;
            mensagemDeErro = "Por favor, selecione um CLIENTE.";
        } else {
            ne.setClienteid(clienteid);
        }
        
        //validando natureza
        if (naturezaid == 0) {
            validacao = false;
            mensagemDeErro = "Por favor, selecione um TIPO DE ENTRADA.";
        } else {
            ne.setNaturezaid(naturezaid);
        }
        
        //validando produto e serviço
        if (servicoid == 0 && produtoid == 0) {
            validacao = false;
            mensagemDeErro = "Por favor, selecione um PRODUTO ou SERVIÇO para incluir como entrada.";
        } else {
            ne.setProdutoid(produtoid);
            ne.setServicoid(servicoid);
        }
        
        //Validando quantidade
        if (qtd.equals("")) {
            validacao = false;
            mensagemDeErro = "Por favor, preencha uma QUANTIDADE.";
        } else {
            try {
                int i = Integer.valueOf(qtd);
                
                if (i < 0) {
                    validacao = false;
                    mensagemDeErro = "Por favor, preencha um número positivo na QUANTIDADE.";
                } else {
                    ne.setQtd_bd(i);
                }
            } catch (Exception e) {
                validacao = false;
                mensagemDeErro = "Por favor, preencha um número na QUANTIDADE.";
            }
        }
        
        /**
         * 
         * VERIFICANDO SE É SERVIÇO E SE O CLIENTE TEM ITENS NO PACOTE
         * 
         * 
         */
        
        if (this.tipo == TipoCadastro.NOVO_CADASTRO) {
            if (validacao) {
                if (ne.getServicoid() > 0) {
                    SituacaoItemPacoteCliente sit;
                    sit = Cliente.verificarSeTemItemNoPacote(ne.getClienteid(), ne.getServicoid(), ne.getQtd_bd());

                    switch (sit) {
                        case POSSUI_EM_QTD_MAIOR:
                            //Confirma com o usuário e, sendo ok, zera o valor da entrada e 
                            //debida do pacote do cliente
                            int i = JOptionPane.showOptionDialog(this, "Cliente possui pacote com este item. " + 
                                    "O valor será zerado e a quantidade DEDUZIDA DO PACOTE.", "Cliente Possui Pacote", 
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

                            if (i == JOptionPane.OK_OPTION) {
                                txfValor.setText("0");
                                ne.setValor_bd(0);
                            }

                            break;
                        case POSSUI_EM_QTD_MENOR:
                            //Confirma com o usuário e, sendo ok, zera o valor da entrada e
                            //altera a qtd para o quanto o cliente possui em pacote
                            int i2 = JOptionPane.showOptionDialog(this, "Cliente possui pacote com este item. " + 
                                    "Porém em quantidade menor. O valor será zerado e a quantidade "
                                    + "readequada e DEDUZIDA DO PACOTE. Efetue outra venda com o restante em seguida.", 
                                    "Cliente Possui Pacote", 
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

                            if (i2 == JOptionPane.OK_OPTION) {
                                int novaQtd;
                                novaQtd = Cliente.obterQtdItensPacote(ne.getClienteid(), ne.getServicoid());
                                txfQtd.setText(String.valueOf(novaQtd));
                                ne.setQtd_bd(novaQtd);
                                txfValor.setText("0");
                                ne.setValor_bd(0);
                            }
                            break;
                    }

                }
            }
        }
        
        
        if (validacao) {
            boolean sucesso;
            
            if (this.tipo == TipoCadastro.NOVO_CADASTRO) {
                sucesso = ne.cadastrar();
            } else {
                sucesso = Entrada.editar(entradaid, ne.getNome_bd(), ne.getValor_bd(), 
                        ne.getDtpagto_bd(), ne.getDtvcto_bd(), ne.getServicoid(), ne.getProdutoid(), 
                        ne.getProfissionalid(), ne.getClienteid(), ne.getNaturezaid(), ne.getQtd_bd());
            }
            
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "O cadastro da entrada foi realizado com SUCESSO.",
                    "Cadastro Finalizado", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                if (!tester.isTestingNow()) this.dispose();
                
                if (TabelaEntrada.getTabela() != null) 
                    TabelaEntrada.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
                
                /**
                 * 
                 * TRATANDO OS SERVIÇOS E PACOTES
                 * 
                 * --- PRODUTOS NÃO SÃO TRATADOS NESTE TRECHO ---
                 * 
                 * 
                 */
                if (this.tipo == TipoCadastro.NOVO_CADASTRO) {
                    if (ne.getServicoid() > 0) {
                        //ENTRAMOS AQUI SOMENTE EM CASO DE SERVIÇO

                        if (Servico.isPackage(ne.getServicoid())) {
                            //Caso o cliente tenha comprado um pacote, é aqui que adicionaremos os itens
                            //deste pacote ao inventário dele
                            Cliente.adicionarItensPacoteAoCliente(ne.getClienteid(), ne.getServicoid(), ne.getQtd_bd());
                        } else {
                            //Caso não seja um pacote, iremos verificar se ele tem o serviço
                            //disponível em seu inventário de pacote. Caso tenha, vamos deduzir
                            //na quantidade adequada
                            Cliente.deduzirItemPacote(ne.getClienteid(), ne.getServicoid(), ne.getQtd_bd());
                        }
                    }
                }
                
                
                
                /**
                 * 
                 * TRATANDO OS PRODUTOS (DEDUZINDO O ESTOQUE)
                 * 
                 * --- SERVIÇOS E PACOTES NÃO SÃO TRATADOS NESTE TRECHO ---
                 * 
                 * 
                 */
                if (this.tipo == TipoCadastro.NOVO_CADASTRO) {
                    if (ne.getProdutoid() > 0) {
                        Produto.deduzirEstoque(ne.getProdutoid(), ne.getQtd_bd());
                    }
                }
                
                
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
    
    /*
    * 
    * PRESETS
    *
    *
    *
    */
    
    public void preSetDescricao(String s) {
        txfDescricao.setText(s);
    }
    
    public void preSetValor(float f) {
        txfValor.setText(String.valueOf(f).replace('.', ','));
    }
    
    public void preSetValor(int servicoid) {
        Servico s = new Servico(servicoid);
        txfValor.setText(String.valueOf(s.getValor()).replace('.', ','));
    }
    
    public void preSetCliente(int clienteid) {
        int nIndex = 0;
        for (int i = 1; i < cbCliente.getItemCount(); i++) {
            if (cbCliente.getItemAt(i).toString().startsWith(clienteid + " -")) nIndex = i;
        }
        cbCliente.setSelectedIndex(nIndex);
    }
    
    public void preSetServico(int servicoid) {
        int nIndex = 0;
        for (int i = 1; i < cbServico.getItemCount(); i++) {
            if (cbServico.getItemAt(i).toString().startsWith(servicoid + " -")) nIndex = i;
        }
        cbServico.setSelectedIndex(nIndex);
    }
    
    public void preSetProfissional(int profissionalid) {
        int nIndex = 0;
        for (int i = 1; i < cbProfissional.getItemCount(); i++) {
            if (cbProfissional.getItemAt(i).toString().startsWith(profissionalid + " -")) nIndex = i;
        }
        cbProfissional.setSelectedIndex(nIndex);
    }
    
}
