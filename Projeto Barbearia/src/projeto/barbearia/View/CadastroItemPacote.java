
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import projeto.barbearia.Controller.ouvinteCancelar;
import projeto.barbearia.Model.Tipos.ComboServicoTipo;
import projeto.barbearia.Model.Profissional;
import javax.swing.JOptionPane;
import projeto.barbearia.Model.ItemPacote;
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

/**
 *
 * @author lucas
 */
public class CadastroItemPacote extends JFrame {
    
    private JPanel painel1;
    private JPanel painel2;
    private JPanel painel3;
    private int pacoteid;
    private ComboServico combo;
    private JTextField txtQtd;
    
    public CadastroItemPacote(int pacoteid) {
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints bag = new GridBagConstraints();
        setLayout(bagLayout);
        setSize(400, 250);
        setResizable(false);
        setTitle("Novo Item do Pacote");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        this.pacoteid = pacoteid;
        
        painel1();
        painel2();
        painel3();
        
        bag.insets = new Insets(20,0,5,0);
        bag.gridx = 0;
        bag.gridy = 0;
        bag.anchor = GridBagConstraints.WEST;
        add(painel1, bag);
        
        bag.insets = new Insets(0,0,10,0);
        bag.weighty = 1;
        bag.gridy = 1;
        add(painel2, bag);
        
        bag.insets = new Insets(0,0,20,30);
        bag.weighty = 0;
        bag.gridy = 2;
        bag.anchor = GridBagConstraints.SOUTH;
        add(painel3, bag);
    }
    
    private void painel1() {
        painel1 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        //JLabels
        JLabel textoServico = new JLabel("Serviço*:");
        textoServico.setFont(new Font("Dialog", Font.PLAIN, 17));
        //Combobox
        ComboServico combox = new ComboServico();
        combox.preencherComboPara(ComboServicoTipo.CADASTRO_ITENSPACOTE, null);
        combo = combox.getCombo();
        //combo.setPreferredSize();
        //Adicionando Itens
        bag.insets = new Insets(5,5,5,5);
        bag.fill = GridBagConstraints.BOTH;
        bag.gridx = 0;
        bag.gridy = 0;
        bag.weightx = 1;
        painel1.add(textoServico, bag);
        
        bag.gridx = 1;
        painel1.add(combo, bag);
    }
    
    private void painel2() {
        painel2 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        //JLabels
        JLabel textoComissao = new JLabel("Qtd.:");
        textoComissao.setFont(new Font("Dialog", Font.PLAIN, 17));
        //JTextFields
        txtQtd = new JTextField(15);
        txtQtd.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //Adicionando Itens
        bag.insets = new Insets(5,5,5,5);
        bag.fill = GridBagConstraints.BOTH;
        bag.gridx = 0;
        bag.gridy = 0;
        bag.weightx = 1;
        painel2.add(textoComissao, bag);
        
        bag.gridx = 1;
        painel2.add(txtQtd, bag);
    }
    
    private void painel3() {
        painel3 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        button_icone salvar = new button_icone("Salvar", new Color(34,139,34), Color.white, 90, 35, 17);
        button_icone cancelar = new button_icone("Cancelar", new Color(205,92,92), Color.white, 100, 35, 17);
        JFrame janela = this;
        
        salvar.addActionListener((e) -> onClickSalvar());
        cancelar.addActionListener((e) -> new ouvinteCancelar().cancelar(janela));
        
        bag.insets = new Insets(5,5,5,5);
        bag.weightx = 1;
        bag.fill = GridBagConstraints.BOTH;
        bag.gridx = 0;
        bag.gridy = 0;
        painel3.add(salvar, bag);
        
        bag.gridx = 1;
        painel3.add(cancelar, bag);
    }
    
    private void onClickSalvar() {
        
        String s = combo.getSelectedItem().toString();
        String sid;
        int space;
        int servicoid;
        int qtd = 0;
        boolean validaDados = false;
        
        space = s.indexOf(" ");
        sid = s.substring(0, space);
        servicoid = Integer.valueOf(sid);
        
        try {
            
            s = txtQtd.getText();
            qtd = Integer.valueOf(s);
            if (qtd > 0) {
                validaDados = true;
            } else {
                JOptionPane.showMessageDialog(null, "A QUANTIDADE deve ser maior que 0.",
                            "Erro nos Dados", JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Insira somente um valor numérico para a QUANTIDADE.",
                            "Erro nos Dados", JOptionPane.WARNING_MESSAGE);
        }
        
        if (validaDados) {
            
            ItemPacote item = new ItemPacote(this.pacoteid, servicoid, qtd);
            
            if (item.cadastrar()) {
                JOptionPane.showMessageDialog(null, "Novo item cadastrado com SUCESSO!",
                            "Cadastrado com Sucesso", JOptionPane.INFORMATION_MESSAGE);
                TabelaItemPacote.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, this.pacoteid);
                
                JFrame janela = this;
                new ouvinteCancelar().cancelar(janela);
                
            } else {
                JOptionPane.showMessageDialog(null, "O item não pôde ser cadastrado. "
                        + "Tente novamente mais tarde.",
                            "Erro no Cadastro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }
    
}
