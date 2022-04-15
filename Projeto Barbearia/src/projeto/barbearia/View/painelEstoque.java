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
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import projeto.barbearia.Controller.ControladorRelatorio;
import projeto.barbearia.Controller.ouvintesPainelEstoque;
import projeto.barbearia.Controller.ouvintesPainelProfissional;
import projeto.barbearia.Model.Constantes;
import projeto.barbearia.Model.Produto;
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

public class painelEstoque extends JPanel{
    
    private JPanel painel3;
    private JPanel painel2;
    private JPanel painel1;
    private JTextField nomeProduto;
    
    public painelEstoque() {
        
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
        nomeProduto = new JTextField(20);
        
        //Adicionando Texto antes da Caixa de Texto
        javax.swing.JLabel textoNomeCliente = new javax.swing.JLabel("Procurar Produto:");
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
        painel1.add(nomeProduto, bag2);
        
        //Adicionando botão de pesquisa
        bag2.weightx = 1;
        bag2.gridx = 2;
        button_icone procurar = new button_icone(Constantes.CAMINHO_IMAGENS + "procurar2.png", 20,10);
        procurar.addActionListener((e) -> onClickBuscar());
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
        
        TabelaProduto.preencherTabela(TipoPreenchimentoTabela.CRIAR, "");
        listScroller.setViewportView(TabelaProduto.getTabela());
        TabelaProduto.getTabela().getParent().setBackground(Color.WHITE);
        
        TabelaProduto.getTabela().addMouseListener(onDoubleClickTabela());
        
        painel2.add(listScroller, bag2);
    }
    
    private void painel3() {
        //Adicionando botões
        painel3 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //Adicionando botão 1
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.weightx = 0.5;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,10);

        button_icone button = new button_icone(Constantes.CAMINHO_IMAGENS + "add.png", "Adicionar Produto", 40, 50);
        button.addActionListener((e) -> onClickAdicionar());
        painel3.add(button, c);
        
        //Adicionando botão 2
        c.gridx = 1;
        c.gridy = 0;
        button_icone button2 = new button_icone(Constantes.CAMINHO_IMAGENS + "remover.png", "Remover Produto", 40, 50);
        button2.addActionListener((e) -> onClickRemover());
        painel3.add(button2, c);
        
        //Adicionando botão 3
        c.gridx = 2;
        c.gridy = 0;
        button_icone button3 = new button_icone(Constantes.CAMINHO_IMAGENS + "editar.png", "Editar Produto", 40, 50);
        button3.addActionListener((e) -> onClickEditar());
        painel3.add(button3, c);
        
        //Adicionando botão 4
        c.gridx = 3;
        c.gridy = 0;
        button_icone button4 = new button_icone(Constantes.CAMINHO_IMAGENS + "registrar.png", "Registro", 40, 50);
        button4.addActionListener((e) -> new TelaRelatorio(ControladorRelatorio.RELATORIO_PRODUTO));
        painel3.add(button4, c);
        
    }
    
    private void onClickBuscar() {
        
        String busca;
        
        busca = nomeProduto.getText();
        if(!busca.equals("")){
            TabelaProduto.preencherTabela(TipoPreenchimentoTabela.BUSCAR, 
                    "nome_produto containing '" + busca + "'");
        } else {
            TabelaProduto.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
        }
        
    }
    
    private void onClickAdicionar() {
        new ouvintesPainelEstoque().onClickAddProduto();   
    }
    
    private void onClickEditar() {
        
        String sid;
        int id;
        
        try {
            sid = (String) TabelaProduto.getTabela()
                .getValueAt(TabelaProduto.getTabela().getSelectedRow(), 0);
            id = Integer.valueOf(sid);

            ouvintesPainelEstoque ouvinte = new ouvintesPainelEstoque();
            ouvinte.onClickEditarProduto(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um produto para EDITAR.",
                        "Selecione um Produto", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    private void onClickRemover() {
        
        String sid;
        int id;
        
        try {
            sid = (String) TabelaProduto.getTabela()
                .getValueAt(TabelaProduto.getTabela().getSelectedRow(), 0);
            id = Integer.valueOf(sid);

            Produto.remover(id);

            TabelaProduto.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um produto para REMOVER.",
                        "Selecione um Produto", JOptionPane.WARNING_MESSAGE);
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
