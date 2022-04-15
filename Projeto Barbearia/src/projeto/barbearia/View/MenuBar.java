/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import projeto.barbearia.Controller.ouvintesIconesInicio;

/**
 *
 * @author mestr
 */
public final class MenuBar extends JMenuBar{
    
    JMenu fileMenu;
    JMenu exibirMenu;
    JMenu helpMenu;
    
    public MenuBar() {
        adicionandoItens();
    }
    
    public void adicionandoItens() {
        fileMenu = new JMenu("Sistema");
	exibirMenu = new JMenu("Cadastros");
	helpMenu = new JMenu("Ajuda");
	add(fileMenu);
	add(exibirMenu);
        //add(helpMenu);
        
        JMenuItem sair = new JMenuItem("Sair");
        sair.addActionListener((e) -> System.exit(0));
        JMenuItem limpar = new JMenuItem("Limpar");
        
        JMenuItem cliente = new JMenuItem("Clientes");
        cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				new ouvintesIconesInicio().onClickClientes();
			}
		});
        
        JMenuItem profissionais = new JMenuItem("Profissionais");
        profissionais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				new ouvintesIconesInicio().onClickProfissionais();
			}
		});
        JMenuItem caixa = new JMenuItem("Caixa");
        caixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				new ouvintesIconesInicio().onClickCaixa();
			}
		});
        
        JMenuItem estoque = new JMenuItem("Estoque");
        estoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				new ouvintesIconesInicio().onClickEstoque();
			}
		});
        
        JMenuItem services = new JMenuItem("Serviços");
        services.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				new ouvintesIconesInicio().onClickServices();
			}
		});
        
        JMenuItem agenda = new JMenuItem("Agenda");
        agenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				new ouvintesIconesInicio().onClickAgenda();
			}
		});
        
        JMenuItem fornecedores = new JMenuItem("Fornecedores");
        fornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				new ouvintesIconesInicio().onClickFornecedores();
			}
		});
        
        JMenuItem parametros = new JMenuItem("Parâmetros");
        parametros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				new ouvintesIconesInicio().onClickParametros();
			}
		});
        
        JMenuItem lembretes = new JMenuItem("Lembretes");
        lembretes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				new ouvintesIconesInicio().onClickLembretes();
			}
		});
        
        JMenuItem sobre = new JMenuItem("Sobre");
        JMenuItem suporte = new JMenuItem("Solicitar Suporte");
        JMenuItem instrucoes = new JMenuItem("Instruções de Uso");
        
        
        fileMenu.add(sair);
        fileMenu.add(lembretes);
        fileMenu.add(parametros);
        
        exibirMenu.add(cliente);
        exibirMenu.add(profissionais);
        exibirMenu.add(caixa);
        exibirMenu.add(estoque);
        exibirMenu.add(services);
        exibirMenu.add(agenda);
        exibirMenu.add(fornecedores);
        
        helpMenu.add(sobre);
        helpMenu.add(suporte);
        helpMenu.add(instrucoes);
    }
    
}
