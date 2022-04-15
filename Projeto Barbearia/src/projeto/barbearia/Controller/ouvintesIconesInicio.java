/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia.Controller;

import projeto.barbearia.Model.Parametros;
import projeto.barbearia.View.painelServices;
import projeto.barbearia.View.painelProfissionais;
import projeto.barbearia.View.painelEstoque;
import projeto.barbearia.View.painelCaixa;
import projeto.barbearia.View.painelAgenda;
import projeto.barbearia.View.painelClientes;
import projeto.barbearia.View.Interface;
import projeto.barbearia.View.PainelParametros;
import projeto.barbearia.View.painelFornecedores;

/**
 *
 * @author mestr
 */
public class ouvintesIconesInicio {
    
    
    public void onClickClientes() {
        //Código para abrir a tab do cliente
        Interface iterface = new Interface();
        painelClientes cliente = new painelClientes();
        iterface.criandoTabs(cliente, "Clientes");
    }
    
    public void onClickProfissionais() {
        //Código para abrir a tab dos profissionais
        Interface iterface = new Interface();
        painelProfissionais profissionais = new painelProfissionais();
        iterface.criandoTabs(profissionais, "Profissionais");
    }
    
    public void onClickCaixa() {
        //Código para abrir a tab da caixa
        Interface iterface = new Interface();
        painelCaixa caixa = new painelCaixa();
        iterface.criandoTabs(caixa, "Caixa");
    }
    
    public void onClickEstoque() {
        //Código para abrir a tab do estoque
        Interface iterface = new Interface();
        painelEstoque estoque = new painelEstoque();
        iterface.criandoTabs(estoque, "Estoque");
    }
    
    public void onClickServices() {
        //Código para abrir a tab dos serviços
        Interface iterface = new Interface();
        painelServices services = new painelServices();
        iterface.criandoTabs(services, "Serviços");
    }
    
    public void onClickAgenda() {
        //Código para abrir a tab da agenda
        Interface iterface = new Interface();
        painelAgenda agenda = new painelAgenda();
        iterface.criandoTabs(agenda, "Agenda");
    }
    
    public void onClickFornecedores() {
        //Código para abrir a tab dos fornecedores
        Interface iterface = new Interface();
        painelFornecedores fornecedores = new painelFornecedores();
        iterface.criandoTabs(fornecedores, "Fornecedores");
    }
    
    public void onClickParametros() {
        //Código para abrir a tab dos parametros
        Interface iterface = new Interface();
        javax.swing.JPanel p = new javax.swing.JPanel(null);
        PainelParametros pParametros = new PainelParametros();
        p.add(pParametros);
        iterface.criandoTabs(p, "Parâmetros");
    }
    
    public void onClickLembretes() {
        
        if (Parametros.getLembreteAniversarioAtivado() || 
                Parametros.getLembreteVencimentosAtivado()) {
            new projeto.barbearia.View.TelaAvisos();
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Não há lembretes "
                    + "ativados. Acesse Sistema > Parâmetros para ativa-los.", 
                    "Lembretes Desativados", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
}
