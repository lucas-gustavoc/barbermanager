
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
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

/**
 *
 * @author mestr
 */
public class CadastroComissao extends JFrame{
    
    private JPanel painel1;
    private JPanel painel2;
    private JPanel painel3;
    private int profissionalid;
    private ComboServico combo;
    private JTextField txtComissao;
    
    public CadastroComissao(int profissionalid) {
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints bag = new GridBagConstraints();
        setLayout(bagLayout);
        setSize(400, 250);
        setResizable(false);
        setTitle("Nova Comissão Personalizada");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        this.profissionalid = profissionalid;
        
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
        Object[] proid = new Object[1];
        proid[0] = profissionalid;
        combox.preencherComboPara(ComboServicoTipo.CADASTRO_COMISSOES, proid);
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
        JLabel textoComissao = new JLabel("Comissão(%)");
        textoComissao.setFont(new Font("Dialog", Font.PLAIN, 17));
        //JTextFields
        txtComissao = new JTextField(15);
        txtComissao.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //Adicionando Itens
        bag.insets = new Insets(5,5,5,5);
        bag.fill = GridBagConstraints.BOTH;
        bag.gridx = 0;
        bag.gridy = 0;
        bag.weightx = 1;
        painel2.add(textoComissao, bag);
        
        bag.gridx = 1;
        painel2.add(txtComissao, bag);
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
        float comissao = 0;
        boolean validaDados = false;
        
        space = s.indexOf(" ");
        sid = s.substring(0, space);
        servicoid = Integer.valueOf(sid);
        
        try {
            
            s = txtComissao.getText();
            comissao = Float.valueOf(s.replace(',', '.'));
            validaDados = true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Insira somente um valor numérico para a COMISSÃO.",
                            "Erro nos Dados", JOptionPane.WARNING_MESSAGE);
        }
        
        if (validaDados) {
            if (Profissional.adicionarComissaoPersonalizada(this.profissionalid, servicoid, comissao)) {
                JOptionPane.showMessageDialog(null, "Nova comissão cadastrada com SUCESSO!",
                            "Cadastrado com Sucesso", JOptionPane.INFORMATION_MESSAGE);
                TabelaComissaoProfissional.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, profissionalid);
                
                JFrame janela = this;
                new ouvinteCancelar().cancelar(janela);
                
            } else {
                JOptionPane.showMessageDialog(null, "A comissão não pode ser cadastrada. Tente novamente mais tarde.",
                            "Erro no Cadastro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }
}