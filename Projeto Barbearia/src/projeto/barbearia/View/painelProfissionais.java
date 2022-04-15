package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import projeto.barbearia.Controller.ControladorRelatorio;
import projeto.barbearia.Controller.ouvintesPainelProfissional;
import projeto.barbearia.Model.Constantes;
import projeto.barbearia.Model.Profissional;
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

public class painelProfissionais extends JPanel{
    
    private JPanel painel3;
    private JPanel painel2;
    private JPanel painel1;
    private JTextField nomeProfissional;
    
    public painelProfissionais() {
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints bag = new GridBagConstraints();
        this.setLayout(bagLayout);
        
        painel1();
        painel2();
        painel3();
        
        //Adicionando painel1
        bag.insets = new Insets(0,10,5,10);
        bag.fill = GridBagConstraints.BOTH;
        bag.anchor = GridBagConstraints.NORTH;
        bag.weightx = 1;
        bag.gridx = 0;
        bag.gridy = 0;
        this.add(painel1, bag);
        
        //Adicionando painel2
        bag.weighty = 1;
        bag.gridx = 0;
        bag.gridy = 1;
        this.add(painel2, bag);
        
        //Adicionando painel3
        bag.weighty = 0;
        bag.gridx = 0;
        bag.gridy = 2;
        this.add(painel3, bag);
    }
    
    private void painel1() {
        
        painel1 = new JPanel(new GridBagLayout());
        GridBagConstraints bag2 = new GridBagConstraints();
        nomeProfissional = new JTextField(20);
        
        //Adicionando Texto antes da Caixa de Texto
        JLabel textoNomeCliente = new JLabel("Procurar Profissional:");
        String path = "";
        textoNomeCliente.setFont(new Font("Dialog", Font.PLAIN, 15));
        
        bag2.gridx = 0;
        bag2.gridy = 0;
        bag2.anchor = GridBagConstraints.WEST;
        painel1.add(textoNomeCliente, bag2);
        
        //Adicionando Caixa de Texto
        bag2.insets = new Insets(0,5,0,0);
        bag2.gridx = 1;
        bag2.gridy = 0;
        painel1.add(nomeProfissional, bag2);
        
        //Adicionando botão de pesquisa
        bag2.weightx = 1;
        bag2.gridx = 2;
        button_icone procurar = new button_icone(Constantes.CAMINHO_IMAGENS + "procurar2.png", 20,10);
        procurar.addActionListener((ActionEvent) -> onClickBuscar());
        painel1.add(procurar, bag2);
    }
    
    private void painel2() {
        //Inicializa o painel Sul
        //painel2 = new JPanel(new GridLayout(1,2));
        
        painel2 = new JPanel(new GridBagLayout());
        GridBagConstraints bag2 = new GridBagConstraints();
        bag2.fill = GridBagConstraints.BOTH;
        bag2.weightx = 1;
        bag2.weighty = 1;
        
        //Adiciona Tabela
        JScrollPane listScroller = new JScrollPane();
        listScroller.setBackground(Color.WHITE);
        TabelaProfissional.preencherTabela(TipoPreenchimentoTabela.CRIAR, "");
        listScroller.setViewportView(TabelaProfissional.getTabela());
        TabelaProfissional.getTabela().getParent().setBackground(Color.WHITE);
        
        TabelaProfissional.getTabela().addMouseListener(onDoubleClickTabela());
        
        painel2.add(listScroller, bag2);
    }
    
    private void painel3() {
        //Adicionando botões
        painel3 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //Adicionando botão 1
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,10);

        button_icone button = new button_icone(Constantes.CAMINHO_IMAGENS + "add.png", "Adicionar Profissional", 40, 50);
        button.addActionListener((ActionEvent) -> onClickAdicionar());
        painel3.add(button, c);
        
        //Adicionando botão 2
        c.gridx = 1;
        c.gridy = 0;
        button_icone button2 = new button_icone(Constantes.CAMINHO_IMAGENS + "remover.png", "Remover Profissional", 40, 50);
        button2.addActionListener((e) -> onClickRemover());
        painel3.add(button2, c);
        
        //Adicionando botão 3
        c.gridx = 2;
        c.gridy = 0;
        button_icone button3 = new button_icone(Constantes.CAMINHO_IMAGENS + "editar.png", "Editar Profissional", 40, 50);
        button3.addActionListener((e) -> onClickEditar());
        painel3.add(button3, c);
        
        //Adicionando botão 4
        c.gridx = 3;
        c.gridy = 0;
        button_icone button4 = new button_icone(Constantes.CAMINHO_IMAGENS + "registrar.png", "Registro", 40, 50);
        button4.addActionListener((e) -> new TelaRelatorio(ControladorRelatorio.RELATORIO_COMISSIONAMENTO));
        painel3.add(button4, c);
        
    }
    
    private void onClickBuscar() {
        
        String busca;
        
        busca = nomeProfissional.getText();
        if(!busca.equals("")){
            TabelaProfissional.preencherTabela(TipoPreenchimentoTabela.BUSCAR, 
                    "nome_profissional containing '" + busca + "'");
        } else {
            TabelaProfissional.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
        }
        
    }
    
    private void onClickAdicionar() {
        ouvintesPainelProfissional ouvinte = new ouvintesPainelProfissional();
        ouvinte.onClickAddProfissional();
    }
    
    private void onClickRemover() {
        
        String scid;
        int cid;
        
        try {
            scid = (String) TabelaProfissional.getTabela()
                .getValueAt(TabelaProfissional.getTabela().getSelectedRow(), 0);
            cid = Integer.valueOf(scid);

            Profissional.remover(cid);

            TabelaProfissional.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um profissional para Remover.",
                        "Selecione um Profissional", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    private void onClickEditar() {
        
        String scid;
        int cid;
        
        try {
            scid = (String) TabelaProfissional.getTabela()
                .getValueAt(TabelaProfissional.getTabela().getSelectedRow(), 0);
            cid = Integer.valueOf(scid);

            ouvintesPainelProfissional ouvinte = new ouvintesPainelProfissional();
            ouvinte.onClickEditarProfissional(cid);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um profissional para Editar.",
                        "Selecione um Profissional", JOptionPane.WARNING_MESSAGE);
        }
        
        
    }
    
    private MouseAdapter onDoubleClickTabela() {
        
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2){
                    onClickEditar();
                }
            }
        };
        
    }
    
}
