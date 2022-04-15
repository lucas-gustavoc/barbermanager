package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import projeto.barbearia.Controller.ouvinteCancelar;
import projeto.barbearia.Controller.ouvinteSalvarProfissional;
import projeto.barbearia.Model.Profissional;
import projeto.barbearia.Model.Constantes;
import projeto.barbearia.Model.FormatadorParaSQLData;
import projeto.barbearia.Model.Tipos.TipoCadastro;
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

/**
 * CLASSE COM TESTES INSTALADOS. VERIFICAR NO FIM DO CONSTRUTOR
 * @author lucas
 */
public class CadastroProfissional extends JFrame{
    private JPanel painel1;
    private JPanel painel2;
    private JPanel painel3;
    private JPanel painel4;
    private JTextField nome;
    private JTextField foneResidencial;
    private JTextField foneCelular;
    private JTextField email;
    private JDataTextField aniversario;
    private combo combo;
    private String mensagemDeErro;
    private TipoCadastro tipo;
    private int profissionalid;
    private projeto.barbearia.Testes.CadastroProfissional tester;
    
        public CadastroProfissional(TipoCadastro tipo, int profissionalid){
            /**
            * tests
            */
            tester = new projeto.barbearia.Testes.CadastroProfissional();
            
            GridBagLayout bagLayout = new GridBagLayout();
            GridBagConstraints bag = new GridBagConstraints();
            setLayout(bagLayout);
            setSize(800,500);
            setResizable(false);
            setTitle("Cadastrar Profissional");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            setLocationRelativeTo(null);
            
            this.profissionalid = profissionalid;
            
            painel1();
            painel2();
            painel3();
            painel4();
            
            bag.weightx = 1;
            bag.weighty = 0;
            bag.fill = GridBagConstraints.BOTH;
            bag.insets = new Insets(0,0,20,0);
            bag.gridx = 0;
            bag.gridy = 0;
            bag.anchor = GridBagConstraints.NORTH;
            add(painel1, bag);
            
            bag.gridy = 1;
            add(painel2, bag);
            
            bag.gridy = 2;
            bag.weighty = 1;
            add(painel3, bag);
            
            bag.gridy = 3;
            bag.weighty = 0;
            add(painel4, bag);
            
            this.tipo = tipo;
            
            if (tipo == TipoCadastro.ALTERACAO) {
                Profissional pro = new Profissional (profissionalid);

                this.nome.setText(pro.getNome());
                this.foneResidencial.setText(pro.getFone1());
                this.foneCelular.setText(pro.getFone2());
                this.email.setText(pro.getEmail());
                if (!(pro.getNasc() == null)) this.aniversario.setText(new FormatadorParaSQLData().reverse(pro.getNasc().toString()));
                this.combo.setSelectedIndex(findIndexFolga(pro.getFolga()));
            }
            
            /**
            * tests
            */
            tester.setTxfNome(nome);
            tester.setTxfFone1(foneResidencial);
            tester.setTxfFone2(foneCelular);
            tester.setTxfEmail(email);
            tester.setTxfNasc(aniversario);
            tester.setCbFolga(combo);
            //tester.executarTeste();
            
        }
        
        private void painel1 () {
            painel1 = new JPanel(new GridBagLayout());
            GridBagConstraints bag = new GridBagConstraints();
            //Inicializando JLabels
            JLabel textoNome = new JLabel("Nome*:");
            textoNome.setFont(new Font("Dialog", Font.PLAIN, 17));
            
            JLabel textoTelefoneRes = new JLabel("Telefone Principal:");
            textoTelefoneRes.setFont(new Font("Dialog", Font.PLAIN, 17));
            
            JLabel textoTelefoneCell = new JLabel("Telefone Opcional:");
            textoTelefoneCell.setFont(new Font("Dialog", Font.PLAIN, 17));
            //Inicializando JTextFields
            nome = new JTextField(25);
            foneResidencial = new JTextField(15);
            foneCelular = new JTextField(15);
            //Adicionando itens ao layout
            bag.insets = new Insets(5,5,5,5);
            bag.fill = GridBagConstraints.BOTH;
            bag.gridx = 0;
            bag.gridy = 0;
            bag.weightx = 1;
            painel1.add(textoNome, bag);
            
            bag.gridy = 1;
            painel1.add(nome, bag);
            
            bag.gridy = 0;
            bag.gridx = 1;
            painel1.add(textoTelefoneRes, bag);
            
            bag.gridy = 1;
            bag.gridx = 1;
            painel1.add(foneResidencial, bag);
            
            bag.gridy = 0;
            bag.gridx = 2;
            painel1.add(textoTelefoneCell, bag);
            
            bag.gridy = 1;
            bag.gridx = 2;
            painel1.add(foneCelular, bag);
        }
        
        private void painel2() {
            painel2 = new JPanel(new GridBagLayout());
            GridBagConstraints bag = new GridBagConstraints();
            //Inicializando JLabels
            JLabel textoEmail = new JLabel("Email:");
            textoEmail.setFont(new Font("Dialog", Font.PLAIN, 17));
            
            JLabel textoAniversario = new JLabel("Data de Nascimento:");
            textoAniversario.setFont(new Font("Dialog", Font.PLAIN, 17));
            
            JLabel textoFolga = new JLabel("Dia de Folga:");
            textoFolga.setFont(new Font("Dialog", Font.PLAIN, 17));
            //Inicializando JTextFields
            email = new JTextField(25);
            aniversario = new JDataTextField();
            aniversario.setColumns(12);
            String[] dias = {"Selecione...", "Segunda", "Terça", "Quarta", "Quinta", 
                "Sexta", "Sábado", "N/A"};
            combo = new combo(dias);
            //Adicionando itens ao layout
            bag.insets = new Insets(5,5,5,5);
            bag.fill = GridBagConstraints.BOTH;
            bag.gridx = 0;
            bag.gridy = 0;
            bag.weightx = 1;
            painel2.add(textoEmail, bag);
            
            bag.gridy = 1;
            painel2.add(email, bag);
            
            bag.gridx = 1;
            bag.gridy = 0;
            painel2.add(textoAniversario, bag);
            
            bag.gridy = 1;
            painel2.add(aniversario, bag);
            
            bag.gridx = 2;
            bag.insets = new Insets(5,20,5,5);
            bag.gridy = 0;
            painel2.add(textoFolga, bag);
            
            bag.gridy = 1;
            painel2.add(combo, bag);
        }
        
        private void painel3() {
            painel3 = new JPanel(new GridBagLayout());
            GridBagConstraints bag = new GridBagConstraints();
            button_icone add = new button_icone(Constantes.CAMINHO_IMAGENS + "add24.png", 20,10);
            button_icone deletar = new button_icone(Constantes.CAMINHO_IMAGENS + "excluir.png", 20,10);
            
            add.addActionListener((e) -> onClickAdicionarComissao());
            deletar.addActionListener((e) -> onClickRemoverComissao());
            
            TabelaComissaoProfissional.preencherTabela(TipoPreenchimentoTabela.CRIAR, this.profissionalid);
            
            bag.insets = new Insets(5,5,5,5);
            bag.fill = GridBagConstraints.BOTH;
            bag.weightx = 1;
            bag.weighty = 1;
            bag.gridx = 0;
            bag.gridy = 0;
            //bag.ipadx = 400;
            //bag.ipady = 150;
            JScrollPane listScroller = new JScrollPane(TabelaComissaoProfissional.getTabela());
            TabelaComissaoProfissional.getTabela().getParent().setBackground(Color.WHITE);
            painel3.add(listScroller, bag);
            
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
            bag.ipady = 0;
            bag.ipadx = 0;
            bag.gridx = 1;
            bag.gridy = 0;
            painel3.add(buttons, bag);
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
            
            bag.insets = new Insets(5,5,5,5);
            bag.gridx = 0;
            bag.gridy = 0;
            bag.ipadx = 40;
            bag.ipady = 30;
            painel4.add(salvar, bag);
            
            bag.gridx = 1;
            painel4.add(cancelar, bag);
        }
        
        private void onClickAdicionarComissao() {
            
            if (this.tipo == TipoCadastro.NOVO_CADASTRO) {
                JOptionPane.showMessageDialog(null, "Por favor, salve as informações do "
                        + "profissional ANTES de inserir comissões personalizadas.",
                            "Salve as Informações", JOptionPane.WARNING_MESSAGE);
            } else {
                new CadastroComissao(this.profissionalid);
            }
            
        }
        
        private void onClickRemoverComissao() {
            
            String scid;
            int servicoid;

            try {
                scid = (String) TabelaComissaoProfissional.getTabela()
                    .getValueAt(TabelaComissaoProfissional.getTabela().getSelectedRow(), 0);
                servicoid = Integer.valueOf(scid);

                Profissional.removerComissaoPersonalizada(this.profissionalid, servicoid);

                TabelaComissaoProfissional.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, this.profissionalid);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Por favor, selecione um registro para remover.",
                            "Selecione um Registro", JOptionPane.WARNING_MESSAGE);
            }
            
        }
        
        private void onClickSalvar(JFrame janela) {
            if (validarDados()) {
                Date data = null;
                boolean sucesso;
                if (!aniversario.getTextMinusMask().equals("")) {
                    FormatadorParaSQLData f = new FormatadorParaSQLData();
                    data = Date.valueOf(f.formatarParaSQLData(aniversario.getTextMinusMask()));
                }
                sucesso = ouvinteSalvarProfissional.salvar(nome.getText(), 
                        foneResidencial.getText(), foneCelular.getText(), email.getText(), data, 
                        combo.getSelectedItem().toString(), this.tipo, this.profissionalid);

                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "O cadastro do profissional foi realizado com SUCESSO.",
                        "Cadastro Finalizado", 
                        JOptionPane.INFORMATION_MESSAGE);
                    if (!tester.isTestingNow()) janela.dispose();

                    TabelaProfissional.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
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

            //validando nome
            if (this.nome.getText().equals("")) {
                retorno = false;
                this.mensagemDeErro = "Por favor, preencha um NOME.";
            }
            
            //validando dia da folga
            if (this.combo.getSelectedIndex() == 0) {
                retorno = false;
                this.mensagemDeErro = "Por favor, selecione um dia para a FOLGA.";
            }

            //validando data
            if (!this.aniversario.getTextMinusMask().equals("")) {
                try {
                    Date data;
                    FormatadorParaSQLData f = new FormatadorParaSQLData();
                    data = Date.valueOf(f.formatarParaSQLData(this.aniversario.getTextMinusMask()));
                } catch (Exception e) {
                    retorno = false;
                    this.mensagemDeErro = "A DATA de nascimento inserida é inválida. "
                            + "Insira uma data no formato dd/mm/aaaa.";
                }
            }


            return retorno;
        }
        
        private int findIndexFolga(String dia) {
            
            int retorno = 0;
            dia = dia.toLowerCase();
            
            switch (dia) {
                case "n/a":
                    retorno = 7;
                    break;
                case "segunda":
                    retorno = 1;
                    break;
                case "terça":
                    retorno = 2;
                    break;
                case "quarta":
                    retorno = 3;
                    break;
                case "quinta":
                    retorno = 4;
                    break;
                case "sexta":
                    retorno = 5;
                    break;
                case "sábado":
                    retorno = 6;
                    break;
                    
            }
            
            return retorno;
            
        }
}
