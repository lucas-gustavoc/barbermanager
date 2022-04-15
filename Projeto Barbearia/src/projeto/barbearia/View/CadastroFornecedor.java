/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import projeto.barbearia.Controller.ouvinteCancelar;
import projeto.barbearia.Controller.ouvinteSalvarFornecedor;
import projeto.barbearia.Model.Fornecedor;
import projeto.barbearia.Model.Tipos.TipoCadastro;
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

/**
 *
 * @author mestr
 */
public class CadastroFornecedor extends JFrame{
    private JPanel painel1;
    private JPanel painel2;
    private JPanel painel3;
    private JTextField nome;
    private JTextField fone;
    private JTextField email;
    private JTextField nomeContato;
    private TipoCadastro tipo;
    private int fornecedorid;
    private String mensagemDeErro;
    
    public CadastroFornecedor(TipoCadastro tipo, int id) {
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints bag = new GridBagConstraints();
        setLayout(bagLayout);
        setSize(650,250);
        setResizable(false);
        setTitle("Cadastrar Fornecedor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        this.fornecedorid = id;
        this.tipo = tipo;
        
        painel1();
        painel2();
        painel3();
        
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.weightx = 1;
        add(painel1, bag);
        
        bag.weighty = 1;
        bag.anchor = GridBagConstraints.NORTH;
        bag.gridy = 1;
        add(painel2, bag);
        
        bag.gridy = 2;
        bag.fill = GridBagConstraints.NONE;
        bag.anchor = GridBagConstraints.SOUTH;
        add(painel3, bag);
        
        if (tipo == TipoCadastro.ALTERACAO) {
            Fornecedor f = new Fornecedor(id);
            nome.setText(f.getNome_bd());
            nomeContato.setText(f.getNomectt_bd());
            fone.setText(f.getFone_bd());
            email.setText(f.getEmail_bd());
        }
        
    }
    
    private void painel1() {
        painel1 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        //JLabels
        JLabel textoNome = new JLabel("Nome*:");
        textoNome.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoNomeContato = new JLabel("Nome do Contato:");
        textoNomeContato.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoFone = new JLabel("Fone:");
        textoFone.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoEmail = new JLabel("E-mail:");
        textoEmail.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //JTextFields
        nome = new JTextField(20);
        nome.setFont(new Font("Dialog", Font.PLAIN, 17));
        nomeContato = new JTextField(20);
        nomeContato.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //Adicionando Itens
        bag.insets = new Insets(5,5,5,5);
        bag.anchor = GridBagConstraints.WEST;
        painel1.add(textoNome, bag);
        
        bag.gridy = 1;
        painel1.add(nome, bag);
        
        bag.gridy = 0;
        bag.weightx = 1;
        bag.gridx = 1;
        painel1.add(textoNomeContato, bag);
        
        bag.gridy = 1;
        painel1.add(nomeContato, bag);
    }
    
    private void painel2() {
        painel2 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        //JLabels
        JLabel textoFone = new JLabel("Fone:");
        textoFone.setFont(new Font("Dialog", Font.PLAIN, 17));
        JLabel textoEmail = new JLabel("E-mail:");
        textoEmail.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //JTextFields
        fone = new JTextField(15);
        fone.setFont(new Font("Dialog", Font.PLAIN, 17));
        email = new JTextField(30);
        email.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //Adicionando Itens
        bag.insets = new Insets(5,5,5,5);
        bag.anchor = GridBagConstraints.WEST;
        painel2.add(textoFone, bag);
        
        bag.gridy = 1;
        painel2.add(fone, bag);
        
        bag.gridy = 0;
        bag.weightx = 1;
        bag.gridx = 1;
        painel2.add(textoEmail, bag);
        
        bag.gridy = 1;
        painel2.add(email, bag);
    }
    
    private void painel3() {
        painel3 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        button_icone salvar = new button_icone("Salvar", new Color(34,139,34), Color.white, 100, 40, 17);
        button_icone cancelar = new button_icone("Cancelar", new Color(205,92,92), Color.white, 100, 40, 17);
        JFrame janela = this;
        
        salvar.addActionListener((e) -> onClickSalvar(janela));
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ouvinteCancelar().cancelar(janela);
            }
            
        });
        bag.insets = new Insets(5,5,5,5);
        bag.weightx = 1;
        bag.fill = GridBagConstraints.BOTH;
        bag.gridx = 0;
        bag.gridy = 0;
        painel3.add(salvar, bag);
        
        bag.gridx = 1;
        painel3.add(cancelar, bag);
    }
    
    private void onClickSalvar(JFrame janela) {
        if (validarDados()) {
            boolean sucesso;
            
            sucesso = ouvinteSalvarFornecedor.salvar(tipo, nome.getText(), nomeContato.getText(), 
                    fone.getText(), email.getText(), fornecedorid);

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "O cadastro do fornecedor foi realizado com SUCESSO.",
                    "Cadastro Finalizado", 
                    JOptionPane.INFORMATION_MESSAGE);
                janela.dispose();
                
                CacheAtualizacao.checarEAtualizar("CadastroFornecedor");
                if (TabelaFornecedor.getTabela() != null) 
                    TabelaFornecedor.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
                
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
        
        if (nome.getText().equals("")) {
            retorno = false;
            mensagemDeErro = "Por favor, preencha um NOME!";
        }
        
        return retorno;
        
    }
    
}
