
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import projeto.barbearia.Controller.ControladorPainelParametros;
import projeto.barbearia.Model.Constantes;
import projeto.barbearia.Model.Parametros;

/**
 *
 * @author lucas
 */
public class PainelParametros extends JPanel {
    
    private ControladorPainelParametros controller;
    
    public PainelParametros() {
        super();
        controller = new ControladorPainelParametros();
        construirInterface();
    }
    
    private void construirInterface() {
        
        //preparando JPanel
        setLayout(new GridBagLayout());
        setSize(new Dimension(600, 630));
        
        //Adicionando linhas
        GridBagConstraints b = new GridBagConstraints();
        
        b.insets = new Insets(5,5,5,5);
        b.fill = GridBagConstraints.HORIZONTAL;
        b.weightx = 1;
        b.weighty = 0;
        add(linha1(), b);
        
        b.gridy = 1;
        add(linha2(), b);
        
        b.gridy = 2;
        add(linha3(), b);
        
        b.gridy = 3;
        add(linha4(), b);
        
        b.gridy = 4;
        add(linha5(), b);
        
        //Preenchendo parte inferior **** TrIcK *****
        b.weighty = 1;
        b.gridy = 5;
        JPanel filler = new JPanel();
        add(filler, b);
        
    }
    
    private JPanel linha1() {
        GridBagConstraints b = new GridBagConstraints();
        GridBagConstraints b1 = new GridBagConstraints();
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        p.setBorder(javax.swing.BorderFactory.createTitledBorder("Expediente"));
        
        //Criando itens P1
        JPanel p1 = new JPanel();
        JLabel lblHrIni = new JLabel("Início do Expediente:");
        JLabel lblHrFin = new JLabel("Final do Expediente:");
        
        //Preparando Itens P1
        p1.setLayout(new GridBagLayout());
        lblHrIni.setFont(new Font("Dialog", Font.PLAIN, 17));
        lblHrFin.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //Inserindo itens P1
        b1.anchor = GridBagConstraints.EAST;
        b1.insets = new Insets(5,5,5,5);
        b1.weightx = 1;
        p1.add(lblHrIni, b1);
        b1.gridy = 1;
        p1.add(lblHrFin, b1);
        
        //Inserindo P1
        b.fill = GridBagConstraints.BOTH;
        b.weightx = 20;
        p.add(p1, b);
        
        //Criando itens P2
        JPanel p2 = new JPanel();
        JComboBox cbHoraIni = new ComboHoraExpediente(ComboHoraExpediente.INICIO_EXPEDIENTE);
        JComboBox cbHoraFin = new ComboHoraExpediente(ComboHoraExpediente.FINAL_EXPEDIENTE);
        
        //Preparando itens P2
        p2.setLayout(new GridBagLayout());
        String horaIni = cbHoraIni.getSelectedItem().toString();
        String horaFin = cbHoraFin.getSelectedItem().toString();
        cbHoraIni.addActionListener((e) -> controller.alterarIntervalorExpediente(
                cbHoraIni, cbHoraFin, horaIni, horaFin, 
                ControladorPainelParametros.HORA_INICIAL_ALTERADA));
        cbHoraFin.addActionListener((e) -> controller.alterarIntervalorExpediente(
                cbHoraIni, cbHoraFin, horaIni, horaFin, 
                ControladorPainelParametros.HORA_FINAL_ALTERADA));
        
        //Inserindo itens P2
        b1.anchor = GridBagConstraints.WEST;
        b1.gridy = 0;
        p2.add(cbHoraIni, b1);
        b1.gridy = 1;
        p2.add(cbHoraFin, b1);
        
        //Inserindo P2
        b.gridx = 1;
        b.weightx = 80;
        p.add(p2, b);
        
        return p;
    }
    
    private JPanel linha2() {
        GridBagConstraints b = new GridBagConstraints();
        GridBagConstraints b1 = new GridBagConstraints();
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        p.setBorder(javax.swing.BorderFactory.createTitledBorder("Avisos de Vencimento"));
        
        //Criando itens P1
        JPanel p1 = new JPanel();
        JLabel lblAtivacao = new JLabel("Ativar/Desativar avisos:");
        JLabel lblDiasAntes = new JLabel("Avisar quantos dias antes:");
        
        //Preparando Itens P1
        p1.setLayout(new GridBagLayout());
        lblAtivacao.setFont(new Font("Dialog", Font.PLAIN, 17));
        lblDiasAntes.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //Inserindo itens P1
        b1.anchor = GridBagConstraints.EAST;
        b1.insets = new Insets(5,5,5,5);
        b1.weightx = 1;
        p1.add(lblAtivacao, b1);
        b1.gridy = 1;
        p1.add(lblDiasAntes, b1);
        
        //Inserindo P1
        b.fill = GridBagConstraints.BOTH;
        b.weightx = 20;
        p.add(p1, b);
        
        //Buscando dados dos itens P2 conforme banco de dados
        controller.carregarLembreteVcto(true);
        
        //Criando itens P2
        JPanel p2 = new JPanel();
        JButton btAtivacao = controller.getBtnAtivLmbVcto();
        JLabel lblStatAtiv = controller.getLblAtivLmbVcto();
        JTextField txfQtdDias = new JTextField();
        
        //Preparando itens P2
        p2.setLayout(new GridBagLayout());
        txfQtdDias.setPreferredSize(new Dimension(45, 25));
        txfQtdDias.setMargin(new Insets(3,3,3,3));
        txfQtdDias.setText(String.valueOf(Parametros.getQtdDiasLembreteVencimento()));
        txfQtdDias.setEnabled(false);
        lblStatAtiv.setFont(new Font("Dialog", Font.ITALIC, 13));
        btAtivacao.addActionListener((e) -> controller.onClickAtivLmbVcto(btAtivacao, lblStatAtiv));
        
        JPanel p22 = new JPanel();
        p22.setLayout(new FlowLayout(FlowLayout.LEFT));
        p22.add(txfQtdDias);
        
        //Criando itens p22
        JButton btMais = new JButton("+");
        JButton btMenos = new JButton("-");
        JButton btSalvar = new JButton("Salvar");
        
        //Preparando itens p22
        btMais.setMargin(new Insets(0,3,0,3));
        btMenos.setMargin(new Insets(0,4,0,4));
        btMais.setFont(new Font("Dialog", Font.PLAIN, 14));
        btMenos.setFont(new Font("Dialog", Font.PLAIN, 14));
        btSalvar.setBackground(new Color(208, 225, 225));
        btSalvar.setVisible(false);
        txfQtdDias.setHorizontalAlignment(JTextField.CENTER);
        btMais.addActionListener((e) -> controller.alterarDiasAvisos(txfQtdDias, 
                ControladorPainelParametros.ADICIONAR_DIA, btSalvar));
        btMenos.addActionListener((e) -> controller.alterarDiasAvisos(txfQtdDias, 
                ControladorPainelParametros.SUBTRAIR_DIA, btSalvar));
        btSalvar.addActionListener((e) -> controller.onClickSalvarDiasAntesAvisos(
                txfQtdDias, btSalvar, ControladorPainelParametros.AVISO_VENCIMENTO));
        p22.add(btMais);
        p22.add(btMenos);
        p22.add(btSalvar);
        
        JPanel p21 = new JPanel();
        p21.setLayout(new FlowLayout(FlowLayout.LEFT));
        p21.add(btAtivacao);
        p21.add(lblStatAtiv);
        
        //Inserindo itens P2
        b1.insets = new Insets(0,0,0,0);
        b1.anchor = GridBagConstraints.WEST;
        b1.gridy = 0;
        b1.weightx = 1;
        b1.weighty = 1;
        p2.add(p21, b1);
        b1.gridy = 1;
        p2.add(p22, b1);
        
        //Inserindo P2
        b.gridx = 1;
        b.weightx = 80;
        p.add(p2, b);
        
        return p;
    }
    
    private JPanel linha3() {
        GridBagConstraints b = new GridBagConstraints();
        GridBagConstraints b1 = new GridBagConstraints();
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        p.setBorder(javax.swing.BorderFactory.createTitledBorder("Avisos de Aniversários"));
        
        //Criando itens P1
        JPanel p1 = new JPanel();
        JLabel lblAtivacao = new JLabel("Ativar/Desativar avisos:");
        JLabel lblDiasAntes = new JLabel("Avisar quantos dias antes:");
        
        //Preparando Itens P1
        p1.setLayout(new GridBagLayout());
        lblAtivacao.setFont(new Font("Dialog", Font.PLAIN, 17));
        lblDiasAntes.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //Inserindo itens P1
        b1.anchor = GridBagConstraints.EAST;
        b1.insets = new Insets(5,5,5,5);
        b1.weightx = 1;
        p1.add(lblAtivacao, b1);
        b1.gridy = 1;
        p1.add(lblDiasAntes, b1);
        
        //Inserindo P1
        b.fill = GridBagConstraints.BOTH;
        b.weightx = 20;
        p.add(p1, b);
        
        //Preparando dados dos itens P2 conforme banco de dados
        controller.carregarLembreteAniversario(true);
        
        //Criando itens P2
        JPanel p2 = new JPanel();
        JButton btAtivacao = controller.getBtnAtivLmbNiver();
        JLabel lblStatAtiv = controller.getLblAtivLmbNiver();
        JTextField txfQtdDias = new JTextField();
        
        //Preparando itens P2
        p2.setLayout(new GridBagLayout());
        txfQtdDias.setPreferredSize(new Dimension(45, 25));
        txfQtdDias.setMargin(new Insets(3,3,3,3));
        txfQtdDias.setText(String.valueOf(Parametros.getQtdDiasLembreteAniversario()));
        txfQtdDias.setEnabled(false);
        txfQtdDias.setHorizontalAlignment(JTextField.CENTER);
        lblStatAtiv.setFont(new Font("Dialog", Font.ITALIC, 13));
        btAtivacao.addActionListener((e) -> controller.onClickAtivLmbNiver(btAtivacao, lblStatAtiv));
        
        JPanel p22 = new JPanel();
        p22.setLayout(new FlowLayout(FlowLayout.LEFT));
        p22.add(txfQtdDias);
        
        //Criando itens p22
        JButton btMais = new JButton("+");
        JButton btMenos = new JButton("-");
        JButton btSalvar = new JButton("Salvar");
        
        //Preparando itens p22
        btMais.setMargin(new Insets(0,3,0,3));
        btMenos.setMargin(new Insets(0,4,0,4));
        btMais.setFont(new Font("Dialog", Font.PLAIN, 14));
        btMenos.setFont(new Font("Dialog", Font.PLAIN, 14));
        btSalvar.setBackground(new Color(208, 225, 225));
        btSalvar.setVisible(false);
        btMais.addActionListener((e) -> controller.alterarDiasAvisos(txfQtdDias, 
                ControladorPainelParametros.ADICIONAR_DIA, btSalvar));
        btMenos.addActionListener((e) -> controller.alterarDiasAvisos(txfQtdDias, 
                ControladorPainelParametros.SUBTRAIR_DIA, btSalvar));
        btSalvar.addActionListener((e) -> controller.onClickSalvarDiasAntesAvisos(
                txfQtdDias, btSalvar, ControladorPainelParametros.AVISO_ANIVERSARIO));
        p22.add(btMais);
        p22.add(btMenos);
        p22.add(btSalvar);
        
        JPanel p21 = new JPanel();
        p21.setLayout(new FlowLayout(FlowLayout.LEFT));
        p21.add(btAtivacao);
        p21.add(lblStatAtiv);
        
        //Inserindo itens P2
        b1.insets = new Insets(0,0,0,0);
        b1.anchor = GridBagConstraints.WEST;
        b1.gridy = 0;
        b1.weightx = 1;
        p2.add(p21, b1);
        b1.gridy = 1;
        p2.add(p22, b1);
        
        //Inserindo P2
        b.gridx = 1;
        b.weightx = 80;
        p.add(p2, b);
        
        return p;
    }
    
    private JPanel linha4() {
        JPanel p = new JPanel(new GridBagLayout());
        JPanel painel1 = new JPanel(new GridBagLayout());
        JPanel painel2 = new JPanel(new GridBagLayout());
        p.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Natureza Financeira"));
        GridBagConstraints bagp = new GridBagConstraints(), 
                bagpainel1 = new GridBagConstraints(), 
                bagpainel2 = new GridBagConstraints();
        
        
        TabelaNatureza tabela = new TabelaNatureza();
        tabela.preencherTabela();
        JScrollPane listScroller = new JScrollPane(tabela);
        tabela.getParent().setBackground(Color.white);
        tabela.addMouseListener(controller.onDoubleClickTabela(tabela));
        bagpainel1.insets = new Insets(5,5,5,5);
        bagpainel1.weightx = 1;
        bagpainel1.weighty = 1;
        bagpainel1.fill = GridBagConstraints.BOTH;
        bagpainel1.gridx = 0;
        bagpainel1.gridy = 0;
        painel1.add(listScroller, bagpainel1);
        
        button_icone add = new button_icone(Constantes.CAMINHO_IMAGENS + "add24.png", 20,10);
        button_icone alterar = new button_icone(Constantes.CAMINHO_IMAGENS + "alterar24.png", 20,10);
        button_icone deletar = new button_icone(Constantes.CAMINHO_IMAGENS + "excluir.png", 20,10);
        add.setMargin(new Insets(2,2,2,2));
        alterar.setMargin(new Insets(2,2,2,2));
        deletar.setMargin(new Insets(2,2,2,2));
        add.addActionListener((e) -> controller.onClickAdicionarNatureza(tabela));
        alterar.addActionListener((e) -> controller.onClickEditarNatureza(tabela));
        deletar.addActionListener((e) -> controller.onClickRemoverNatureza(tabela));
        
        bagpainel2.insets = new Insets(5,5,5,5);
        bagpainel2.anchor = GridBagConstraints.NORTH;
        bagp.fill = GridBagConstraints.BOTH;
        bagpainel2.weightx = 1;
        bagpainel2.weighty = 0;
        bagpainel2.gridy = 0;
        painel2.add(add, bagpainel2);
        bagpainel2.gridy = 1;
        painel2.add(alterar, bagpainel2);
        bagpainel2.gridy = 2;
        painel2.add(deletar, bagpainel2);
        
        bagp.insets = new Insets(5,5,5,5);
        bagp.weightx = 1;
        bagp.weighty = 1;
        bagp.fill = GridBagConstraints.BOTH;
        bagp.gridx = 0;
        bagp.gridy = 0;
        p.add(painel1, bagp);
        bagp.weightx = 0;
        bagp.gridx = 1;
        p.add(painel2, bagp);
        
        p.setMinimumSize(new Dimension(p.getWidth(), 170));
        
        return p;
    }
    
    
    private JPanel linha5() {
        JPanel p = new JPanel(new FlowLayout());
        JButton btn = new JButton("Remover Chave de Ativação");
        p.setBorder(javax.swing.BorderFactory.createTitledBorder("Chave de Ativação"));
        p.add(btn);
        btn.addActionListener((e) -> controller.removerChaveDeAtivacao());
        
        return p;
    }
    
}
