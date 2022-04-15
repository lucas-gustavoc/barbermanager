
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import projeto.barbearia.Controller.ouvinteCancelar;
import projeto.barbearia.Controller.ouvinteSalvarCliente;
import projeto.barbearia.Model.Cliente;
import projeto.barbearia.Model.FormatadorParaSQLData;
import projeto.barbearia.Model.Tipos.ComboProfissionalTipo;
import projeto.barbearia.Model.Tipos.TipoCadastro;

/**
 * CLASSE COM TESTES INSTALADOS. VERIFICAR NO FIM DO CONSTRUTOR
 * @author lucas
 */
public class CadastroCliente extends JFrame {
    
    private JPanel painel1;
    private JPanel painel2;
    private JPanel painel3;
    private JTextField nome;
    private JTextField email;
    private JTextField fone;
    private JDataTextField nascimento;
    private String mensagemDeErro;
    private TipoCadastro tipo;
    private ComboProfissional combo;
    private int clienteid;
    private projeto.barbearia.Testes.CadastroCliente tester;
    
    public CadastroCliente(TipoCadastro tipo, int clienteid) {
        
        /**
         * tests
         */
        tester = new projeto.barbearia.Testes.CadastroCliente();
        
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints bag = new GridBagConstraints();
        setLayout(bagLayout);
        setSize(500,250);
        setResizable(false);
        setTitle("Cadastrar Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        this.clienteid = clienteid;
        
        painel();
        painel2();
        painel3();
        
        bag.insets = new Insets(5,5,5,5);
        bag.fill = GridBagConstraints.BOTH;
        bag.gridx = 0;
        bag.gridy = 0;
        bag.weighty = 1;
        bag.weightx = 1;
        add(painel1, bag);
        
        bag.gridy = 1;
        add(painel2, bag);
        
        bag.gridy = 2;
        add(painel3, bag);
        
        this.tipo = tipo;
        
        if (tipo == TipoCadastro.ALTERACAO) {
            Cliente cl = new Cliente (clienteid);
            
            this.nome.setText(cl.getNome());
            this.fone.setText(cl.getFone());
            this.email.setText(cl.getEmail());
            if (!(cl.getNasc() == null)) this.nascimento.setText(new FormatadorParaSQLData().reverse(cl.getNasc().toString()));
        }
        
        /**
         * tests
         */
        tester.setTxfNome(nome);
        tester.setTxfFone(fone);
        tester.setTxfEmail(email);
        tester.setTxfNasc(nascimento);
        tester.setCbProf(combo);
        //tester.executarTeste();
        
    }
    
    private void painel() {
        painel1 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        JLabel textoNome = new JLabel("Nome*:");
        textoNome.setFont(new Font("Dialog", Font.PLAIN, 17));
        nome = new JTextField();
        nome.setPreferredSize(new java.awt.Dimension(200, 24));
        JLabel textoEmail = new JLabel("E-mail:");
        textoEmail.setFont(new Font("Dialog", Font.PLAIN, 17));
        email = new JTextField();
        
        bag.insets = new Insets(5,5,5,5);
        bag.fill = GridBagConstraints.BOTH;
        bag.gridx = 0;
        bag.gridy = 0;
        bag.weightx = 0.4;
        painel1.add(textoNome, bag);
        
        bag.gridy = 1;
        painel1.add(nome, bag);
        
        
        bag.gridy = 0;
        bag.gridx = 1;
        painel1.add(textoEmail, bag);
        
        bag.weightx = 0.6;
        bag.gridy = 1;
        bag.gridx = 1;
        painel1.add(email, bag);
        
    }
    
    private void painel2() {
        painel2 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        
        JLabel textoFone = new JLabel("Telefone: ");
        textoFone.setFont(new Font("Dialog", Font.PLAIN, 17));
        fone = new JTextField();
        JLabel textoNascimento = new JLabel("Dt. Nascimento:");
        textoNascimento.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoProfissional = new JLabel("Prof. Preferência:");
        textoProfissional.setFont(new Font("Dialog", Font.PLAIN, 17));
        nascimento = new JDataTextField();
        
        bag.insets = new Insets(5,5,5,5);
        bag.fill = GridBagConstraints.BOTH;
        bag.gridx = 0;
        bag.gridy = 0;
        bag.weightx = 4;
        
        painel2.add(textoFone, bag);
        
        bag.gridx = 0;
        bag.gridy = 1;
        painel2.add(fone, bag);
        
        bag.weightx = 1;
        bag.gridx = 1;
        bag.gridy = 0;
        painel2.add(textoNascimento, bag);
        
        bag.gridx = 1;
        bag.gridy = 1;
        painel2.add(nascimento, bag);
        
        bag.gridx = 2;
        bag.gridy = 0;
        painel2.add(textoProfissional, bag);
        
        bag.gridy = 1;
        bag.gridx = 2;
        ComboProfissional combo = new ComboProfissional();
        Object[] clid = new Object[1];
        clid[0] = clienteid;
        combo.preencherComboPara(ComboProfissionalTipo.CADASTRO_CLIENTES, clid);
        this.combo = combo.getCombo();
        painel2.add(this.combo, bag);
    }
    
    private void painel3() {
        painel3 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        button_icone salvar = new button_icone("Salvar", new Color(34,139,34), Color.white, 100, 40, 17);
        button_icone cancelar = new button_icone("Cancelar", new Color(205,92,92), Color.white, 100, 40, 17);
        JFrame janela = this;
        
        salvar.addActionListener((event) -> botaoSalvar_click(janela));
        
        /**
         * tests
         */
        tester.setB(salvar);
        
        //*** Substituição Oficial de Classes Anônimas por Expressões Lambda
        
        /*cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ouvinteCancelar().cancelar(janela);
            }
            
        });*/
        cancelar.addActionListener((ActionEvent e) -> new ouvinteCancelar().cancelar(janela));
        
        
        bag.insets = new Insets(5,5,5,5);
        bag.weightx = 1;
        bag.fill = GridBagConstraints.NONE;
        bag.anchor = GridBagConstraints.EAST;
        bag.gridx = 0;
        bag.gridy = 0;
        painel3.add(salvar, bag);
        
        bag.anchor = GridBagConstraints.WEST;
        bag.gridx = 1;
        painel3.add(cancelar, bag);
    }
    
    private void botaoSalvar_click(JFrame janela) {
        if (validarDados()) {
            Date data = null;
            boolean sucesso;
            if (!nascimento.getTextMinusMask().equals("")) {
                FormatadorParaSQLData f = new FormatadorParaSQLData();
                data = Date.valueOf(f.formatarParaSQLData(nascimento.getTextMinusMask()));
            }
            
            //Verificando Profissional selecionado
            int profissionalid;
            if (combo.getSelectedIndex() == 0) {
                profissionalid = 0;
            } else {
                String s = combo.getSelectedItem().toString();
                int space;
                String sid;
                
                space = s.indexOf(" ");
                sid = s.substring(0, space);
                profissionalid = Integer.valueOf(sid);
            }
            
            
            //Iniciando cadastro
            sucesso = ouvinteSalvarCliente.salvar(nome.getText(), 
                    fone.getText(), email.getText(), data, profissionalid, this.tipo, this.clienteid);

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "O cadastro do cliente foi realizado com SUCESSO.",
                    "Cadastro Finalizado", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                if (!tester.isTestingNow()) janela.dispose();
                
                if (TabelaCliente.getTabela() != null) TabelaCliente.atualizarTabela();
                
                CacheAtualizacao.checarEAtualizar("CadastroCliente");
                
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
        
        //validando data
        if (!this.nascimento.getTextMinusMask().equals("")) {
            try {
                Date data;
                FormatadorParaSQLData f = new FormatadorParaSQLData();
                data = Date.valueOf(f.formatarParaSQLData(this.nascimento.getTextMinusMask()));
            } catch (Exception e) {
                retorno = false;
                this.mensagemDeErro = "A DATA de nascimento inserida é inválida. "
                        + "Insira uma data no formato dd/mm/aaaa.";
            }
        }
        
        
        return retorno;
    }
    
}
