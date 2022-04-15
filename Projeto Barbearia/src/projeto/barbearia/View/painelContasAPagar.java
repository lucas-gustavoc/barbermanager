/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import projeto.barbearia.Controller.ouvintePainelContasAPagar;
import projeto.barbearia.Model.Constantes;
import projeto.barbearia.Model.FormatadorParaSQLData;
import projeto.barbearia.Model.Saida;
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

/**
 *
 * @author mestr
 */
public class painelContasAPagar extends JPanel{
    
    private JPanel painel1;
    private JPanel painel2;
    private JTextField descri;
    private JDataTextField aPartir;
    private JDataTextField ate;
    
    public painelContasAPagar() {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints bag = new GridBagConstraints();
        
        painel1();
        painel2();
        
        bag.insets = new Insets(5,5,5,5);
        bag.gridx = 0;
        bag.gridy = 0;
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.anchor = GridBagConstraints.NORTH;
        bag.weightx = 1;
        //bag.weighty = 0.1;
        add(painel1, bag);
        
        bag.weighty = 1;
        bag.fill = GridBagConstraints.BOTH;
        bag.gridy = 1;
        add(painel2, bag);
        
    }
    
    private void painel1() {
        painel1 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        
        //Criando JLabel
        JLabel tituloBuscar = new JLabel("Buscar:");
        tituloBuscar.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        JLabel aPartirTexto = new JLabel("Vencimento a paritr de:");
        aPartirTexto.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        JLabel ateTexto = new JLabel("Vencimento até:");
        ateTexto.setFont(new Font("Dialog", Font.PLAIN, 17));
        //Criando JTextField
        descri = new JTextField(25);
        descri.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        aPartir = new JDataTextField();
        aPartir.setColumns(12);
        aPartir.setFont(new Font("Dialog", Font.PLAIN, 17));
        ate = new JDataTextField();
        ate.setColumns(12);
        ate.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //Cria JButton's
        button_icone buscar = new button_icone("Buscar", Color.lightGray, Color.BLACK, 100, 30, 17);
        buscar.addActionListener((e) -> onClickBuscar());
        
        button_icone verDetalhes = new button_icone(Constantes.CAMINHO_IMAGENS + "detalhes24.png", "Ver Detalhes", 150, 34);
        verDetalhes.addActionListener((e) -> onClickVerDetalhes());
        
        button_icone adicionar = new button_icone(Constantes.CAMINHO_IMAGENS + "add24.png", "Nova Saída", 150, 34);
        adicionar.addActionListener((e) -> onClickAdicionar());
        
        button_icone remover = new button_icone(Constantes.CAMINHO_IMAGENS + "excluir.png", "Excluir", 150, 34);
        remover.addActionListener((e) -> onClickRemover());
        
        //Cria paineis
        JPanel linha0 = new JPanel(new FlowLayout());
        
        JPanel linha1 = new JPanel(new FlowLayout());
        linha1.add(aPartirTexto);
        linha1.add(aPartir);
        
        JPanel linha2 = new JPanel(new FlowLayout());
        linha2.add(ateTexto);
        linha2.add(ate);
        
        //Adiciona componentes
        button_icone voltar = new button_icone(Constantes.CAMINHO_IMAGENS + "voltar16.png", 20,10);
        voltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                new ouvintePainelContasAPagar().onClickVoltar();
            }
        });
        
        //from here
        
        linha0.add(voltar);
        linha0.add(tituloBuscar);
        linha0.add(descri);
        
        bag.insets = new Insets(0,0,10,0);
        bag.anchor = GridBagConstraints.WEST;
        bag.gridx = 0;
        bag.gridy = 0;
        bag.weightx = 1;
        painel1.add(linha0, bag);
        
        //até aqui
        
        bag.insets = new Insets(0,0,5,0);
        bag.gridy = 1;
        painel1.add(linha1, bag);
        
        bag.gridy = 2;
        painel1.add(linha2, bag);
        
        bag.gridy = 3;
        bag.insets = new Insets(0,70,5,0);
        painel1.add(buscar, bag);
        
        bag.weightx = 0;
        bag.gridy = 3;
        bag.gridx = 1;
        bag.anchor = GridBagConstraints.EAST;
        bag.insets = new Insets(0,0,0,3);
        painel1.add(verDetalhes, bag);
        
        bag.gridx = 2;
        painel1.add(adicionar, bag);
        
        bag.gridx = 3;
        painel1.add(remover, bag);
    }
    
    private void painel2() {
        painel2 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        
        //Cria tabela
        TabelaSaida.preencherTabela(TipoPreenchimentoTabela.CRIAR, "");
        TabelaSaida.getTabela().addMouseListener(onDoubleClickTabela());
        
        JScrollPane listScroller = new JScrollPane();
        listScroller.setBackground(Color.WHITE);
        listScroller.setViewportView(TabelaSaida.getTabela());
        TabelaSaida.getTabela().getParent().setBackground(Color.WHITE);
        bag.gridx = 0;
        bag.gridy = 0;
        bag.fill = GridBagConstraints.BOTH;
        bag.weightx = 1;
        bag.weighty = 1;
        painel2.add(listScroller, bag);
    }
    
    private void onClickBuscar() {
        //cast('Now' as date)
        String busca;
        String buscaData = "";
        String mensagemDeErro = "";
        boolean okDtVcto1 = true;
        boolean okDtVcto2 = true;
        
        if (!aPartir.getTextMinusMask().equals("")) {
            try {
                Date data;
                FormatadorParaSQLData f = new FormatadorParaSQLData();
                String dataFormatada = f.formatarParaSQLData(this.aPartir.getTextMinusMask());
                data = Date.valueOf(dataFormatada);
                buscaData = buscaData + " and dtvcto_saida >= cast('" + dataFormatada + "' as date)";
            } catch (Exception e) {
                okDtVcto1 = false;
                mensagemDeErro = "A DATA de \"Vencimento a partir de\" inserida é inválida. "
                        + "Insira uma data no formato dd/mm/aaaa.";
            }
        }
        
        if (!ate.getTextMinusMask().equals("")) {
            try {
                Date data;
                FormatadorParaSQLData f = new FormatadorParaSQLData();
                String dataFormatada = f.formatarParaSQLData(this.ate.getTextMinusMask());
                data = Date.valueOf(dataFormatada);
                buscaData = buscaData + " and dtvcto_saida <= cast('" + dataFormatada + "' as date)";
            } catch (Exception e) {
                okDtVcto1 = false;
                mensagemDeErro = "A DATA de \"Vencimento até\" inserida é inválida. "
                        + "Insira uma data no formato dd/mm/aaaa.";
            }
        }
        
        busca = descri.getText();
        
        if(!busca.equals("") && okDtVcto1 && okDtVcto2){
            //Entra aqui quando há uma palavra chave e as datas estão ok (preenchidas corretamente ou não preenchidas)
            TabelaSaida.preencherTabela(TipoPreenchimentoTabela.BUSCAR, 
                    "nome_saida containing '" + busca + "'" + buscaData);
        } else if (!buscaData.equals("") && okDtVcto1 && okDtVcto2) {
            //Entra aqui quando não há uma palavra chave, mas uma das datas está preenchida
            TabelaSaida.preencherTabela(TipoPreenchimentoTabela.BUSCAR, buscaData.substring(5));
        } else if (okDtVcto1 && okDtVcto2) {
            //Entra aqui quando nem as datas nem a palavra chave está preenchida
            TabelaSaida.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
        } else {
            //Entra aqui quando as datas estão preenchidas incorretamente
            JOptionPane.showMessageDialog(null, mensagemDeErro,
                    "Dados Incorretos", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void onClickRemover() {
        
        String sid;
        int id;
        
        try {
            sid = (String) TabelaSaida.getTabela()
                .getValueAt(TabelaSaida.getTabela().getSelectedRow(), 0);
            id = Integer.valueOf(sid);

            Saida.remover(id);

            TabelaSaida.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione uma saída para REMOVER.",
                        "Selecione uma Saída", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    private void onClickAdicionar() {
        new ouvintePainelContasAPagar().onClickAdicionar();
    }
    
    private void onClickVerDetalhes() {
        
        String sid;
        int id;
        
        try {
            sid = (String) TabelaSaida.getTabela()
                .getValueAt(TabelaSaida.getTabela().getSelectedRow(), 0);
            id = Integer.valueOf(sid);

            ouvintePainelContasAPagar ouvinte = new ouvintePainelContasAPagar();
            ouvinte.onClickVerDetalhes(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione uma saída para VER DETALHES.",
                        "Selecione uma Saída", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    private MouseAdapter onDoubleClickTabela() {
        
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2){
                    onClickVerDetalhes();
                }
            }
        };
        
    }
    
}
