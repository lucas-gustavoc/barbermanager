package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import projeto.barbearia.Controller.ouvinteCancelar;
import projeto.barbearia.Model.Constantes;
import projeto.barbearia.Model.Servico;
import projeto.barbearia.Model.Tipos.TipoCadastro;
import javax.swing.JOptionPane;
import projeto.barbearia.Controller.ouvinteSalvarServico;
import projeto.barbearia.Model.ItemPacote;
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

/**
 * CLASSE COM TESTES INSTALADOS. VERIFICAR NO FIM DO CONSTRUTOR
 * @author lucas
 */
public class CadastroService extends JFrame{
    
    GridBagConstraints bagGeral;
    private JPanel painel1;
    private JPanel painel2;
    private JPanel painel3;
    private JTextField nome;
    private JTextField valor;
    private JTextField comissaoPadrao;
    private combo tipo;
    private int servicoid;
    private TipoCadastro tipoCadastro;
    private String mensagemDeErro;
    private projeto.barbearia.Testes.CadastroServico tester;
    
    public CadastroService(TipoCadastro tipo, int servicoid) {
        /**
         * tests
         */
        tester = new projeto.barbearia.Testes.CadastroServico();
        
        GridBagLayout bagLayout = new GridBagLayout();
        bagGeral = new GridBagConstraints();
        setLayout(bagLayout);
        setSize(800,200);
        setResizable(false);
        setTitle("Cadastrar Serviço");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        this.servicoid = servicoid;
        
        painel1();
        painel2();
        painel3();
        
        bagGeral.insets = new Insets(10,10,10,10);
        bagGeral.weightx = 1;
        bagGeral.weighty = 0.05;
        bagGeral.fill = GridBagConstraints.HORIZONTAL;
        bagGeral.gridx = 0;
        bagGeral.gridy = 0;
        bagGeral.anchor = GridBagConstraints.NORTH;
        add(painel1, bagGeral);
        
        bagGeral.gridy = 3;
        bagGeral.weighty = 0;
        bagGeral.anchor = GridBagConstraints.SOUTH;
        add(painel3, bagGeral);
        
        this.tipoCadastro = tipo;
        
        if (tipoCadastro == TipoCadastro.ALTERACAO) {
            Servico s = new Servico(servicoid);
            
            this.nome.setText(s.getNome());
            this.valor.setText(Float.toString(s.getValor()));
            this.comissaoPadrao.setText(Float.toString(s.getComissao()));
            if (s.getTipo().toUpperCase().equals("PACOTE")) {
                this.tipo.setSelectedIndex(1);
            } else {
                this.tipo.setSelectedIndex(0);
            }
            this.tipo.setEnabled(false);
            
        }
        
        /**
         * tests
         */
        tester.setTxfNome(nome);
        tester.setTxfValor(valor);
        tester.setTxfComissao(comissaoPadrao);
        tester.setCbTipo(this.tipo);
        //tester.executarTeste();
        
    }
    
    private void painel1() {
        painel1 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        
        //JLabel's
        JLabel textoNome = new JLabel("Nome*:");
        textoNome.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        JLabel textoValor = new JLabel("Valor*:");
        textoValor.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        JLabel textoComissaoPadrao = new JLabel("Comissão Padrão*:");
        textoComissaoPadrao.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        JLabel textoTipo = new JLabel("Tipo*:");
        textoTipo.setFont(new Font("Dialog", Font.PLAIN, 17));
        //JTextField's
        nome = new JTextField(25);
        valor = new JTextField(15);
        comissaoPadrao = new JTextField(15);
        bag.insets = new Insets(5,5,5,5);
        bag.weightx = 1;
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.gridx = 0;
        bag.gridy = 0;
        painel1.add(textoNome, bag);
        
        bag.gridy = 1;
        painel1.add(nome, bag);
        
        bag.gridx = 1;
        bag.gridy = 0;
        painel1.add(textoValor, bag);
        
        bag.gridy = 1;
        painel1.add(valor, bag);
        
        bag.gridx = 2;
        bag.gridy = 0;
        painel1.add(textoComissaoPadrao, bag);
        
        bag.gridy = 1;
        painel1.add(comissaoPadrao, bag);
        
        //Adicionando JLabel textoTipo e JComboBox Tipo 
        String[] dadosTipo = {"SERVICO", "PACOTE"};
        tipo = new combo(dadosTipo);
        ActionListener cdActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) tipo.getSelectedItem();
                tratandoPainel2(s);
            }
        };
        tipo.addActionListener(cdActionListener);
        
        bag.gridx = 3;
        bag.gridy = 0;
        painel1.add(textoTipo, bag);
        
        bag.gridy = 1;
        painel1.add(tipo, bag);
        
        
    }
    
    private void painel2() {
        painel2 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        
        button_icone add = new button_icone(Constantes.CAMINHO_IMAGENS + "add24.png", 20,10);
        button_icone deletar = new button_icone(Constantes.CAMINHO_IMAGENS + "excluir.png", 20,10);
        
        add.addActionListener((e) -> onClickAdicionarItem());
        deletar.addActionListener((e) -> onClickRemoverItem());
        
        TabelaItemPacote.preencherTabela(TipoPreenchimentoTabela.CRIAR, servicoid);
        
        JScrollPane listScroller = new JScrollPane(TabelaItemPacote.getTabela());
        TabelaItemPacote.getTabela().getParent().setBackground(Color.WHITE);
        bag.insets = new Insets(5,5,5,5);
        bag.weightx = 1;
        bag.weighty = 1;
        bag.fill = GridBagConstraints.BOTH;
        bag.gridx = 0;
        bag.gridy = 0;
        painel2.add(listScroller, bag);
        
        JPanel buttons = new JPanel(new GridBagLayout());
        GridBagConstraints bag2 = new GridBagConstraints();
            
        bag2.insets = new Insets(0,0,5,5);
        bag2.fill = GridBagConstraints.BOTH;
        bag2.gridx = 0;
        bag2.gridy = 0;
        buttons.add(add, bag2);
        bag2.anchor = GridBagConstraints.NORTH;
        bag2.gridy = 1;
        bag2.weighty = 1;
        buttons.add(deletar, bag2);
        
        bag.fill = GridBagConstraints.NONE;
        bag.anchor = GridBagConstraints.NORTH;
        bag.weighty = 1;
        bag.weightx = 0;
        bag.gridx = 1;
        bag.gridy = 0;
        painel2.add(buttons, bag);
        
    }
    
    private void painel3() {
        painel3 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
            
        button_icone salvar = new button_icone("Salvar", new Color(34,139,34), Color.white, 100, 40, 17);
        button_icone cancelar = new button_icone("Cancelar", new Color(205,92,92), Color.white, 100, 40, 17);
        JFrame janela = this;
        
        salvar.addActionListener((e) -> onClickSalvar(janela));
        
        /**
         * tests
         */
        tester.setB(salvar);
        
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ouvinteCancelar().cancelar(janela);
            }
            
        });
        bag.insets = new Insets(5,5,5,5);
        bag.gridx = 0;
        bag.gridy = 0;
        bag.ipadx = 0;
        bag.ipady = 0;
        painel3.add(salvar, bag);
        
        bag.gridx = 1;
        painel3.add(cancelar, bag);
    }
    
    private void tratandoPainel2(String s) {
        if(s.equals("PACOTE")) {
            setSize(800,500);
            setLocationRelativeTo(null);
            bagGeral.weighty = 1;
            bagGeral.gridy = 2;
            bagGeral.fill = GridBagConstraints.BOTH;
            add(painel2, bagGeral);
        }
        else if (s.equals("SERVICO")) {
            setLocationRelativeTo(null);
            remove(painel2);
            setSize(800,200);
        }
    }
    
    private void onClickAdicionarItem() {
        if (tipoCadastro == TipoCadastro.NOVO_CADASTRO) {
            JOptionPane.showMessageDialog(null, "Por favor, salve as informações do "
                        + "serviço ANTES de inserir itens no pacote.",
                            "Salve as Informações", JOptionPane.WARNING_MESSAGE);
        } else {
            new CadastroItemPacote(this.servicoid);
        }
    }

    private void onClickRemoverItem() {

        String scid;
        int itempacoteid;

        try {
            scid = (String) TabelaItemPacote.getTabela()
                .getValueAt(TabelaItemPacote.getTabela().getSelectedRow(), 0);
            itempacoteid = Integer.valueOf(scid);

            ItemPacote.remover(itempacoteid);

            TabelaItemPacote.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, this.servicoid);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um registro para remover.",
                        "Selecione um Registro", JOptionPane.WARNING_MESSAGE);
        }

    }
    
    private void onClickSalvar(JFrame janela) {
        if (validarDados()) {
                boolean sucesso;

                sucesso = ouvinteSalvarServico.salvar(nome.getText(), 
                        Float.valueOf(this.valor.getText().replace(',', '.')),
                        Float.valueOf(this.comissaoPadrao.getText().replace(',', '.')), 
                        this.tipo.getSelectedItem().toString(),
                        tipoCadastro, this.servicoid);

                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "O cadastro do serviço foi realizado com SUCESSO.",
                        "Cadastro Finalizado", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    if (!tester.isTestingNow()) janela.dispose();

                    TabelaServico.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
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
        String nome = this.nome.getText();
        String valor = this.valor.getText();
        String comissao = this.comissaoPadrao.getText();
        float teste = 0;
        
        //Validar nome
        if (nome.equals("")) {
            retorno = false;
            mensagemDeErro = "Preencha o campo NOME!";
        }
        
        //Validar valor
        if (valor.equals("")) {
            retorno = false;
            mensagemDeErro = "Preencha o campo VALOR!";
        } else {
            try {
                teste = Float.valueOf(valor.replace(',', '.'));
            } catch (Exception e) {
                retorno = false;
                mensagemDeErro = "O campo VALOR deve conter um número!";
            }
        }
        
        //Validar comissão
        if (comissao.equals("")) {
            retorno = false;
            mensagemDeErro = "Preencha o campo COMISSÃO!";
        } else {
            try {
                teste = Float.valueOf(comissao.replace(',', '.'));
            } catch (Exception e) {
                retorno = false;
                mensagemDeErro = "O campo COMISSÃO deve conter um número!";
            }
        }
        
        return retorno;
        
    }
    
}
