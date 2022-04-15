/*

[x] Criar objeto Cliente com todas as funções CRUD
[x] Vincular o cadastro do objeto Cliente à tela de cadastro
[x] Fazer lista de clientes exibir os dados da tabela de clientes do BD
[x] Fazer pesquisa de clientes funcionar
[x] Fazer edição de clientes funcionar
[x] Fazer exclusão de clientes funcionar
[x] Corrigir os ícones da tela

*/

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import projeto.barbearia.Controller.ouvintesPainelCliente;
import projeto.barbearia.Model.Constantes;
import javax.swing.JOptionPane;
import projeto.barbearia.Model.Cliente;

public class painelClientes extends JPanel {

    
    private JPanel painel3;
    private JPanel painel2;
    private JPanel painel1;
    private JTextField nomeCliente;
    private int[] clientesComPacote;
    private button_icone verPacote;

    public painelClientes() {
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
        nomeCliente = new JTextField(20);
        
        //Adicionando Texto antes da Caixa de Texto
        JLabel textoNomeCliente = new JLabel("Procurar Cliente:");
        textoNomeCliente.setFont(new Font("Dialog", Font.PLAIN, 15));
        
        bag2.gridx = 0;
        bag2.gridy = 0;
        bag2.anchor = GridBagConstraints.WEST;
        painel1.add(textoNomeCliente, bag2);
        
        //Adicionando Caixa de Texto
        bag2.insets = new Insets(0,5,0,0);
        bag2.gridx = 1;
        bag2.gridy = 0;
        painel1.add(nomeCliente, bag2);
        
        //Adicionando botão de pesquisa
        bag2.weightx = 1;
        bag2.gridx = 2;
        button_icone procurar = new button_icone(Constantes.CAMINHO_IMAGENS + "procurar2.png", 20,10);
        procurar.addActionListener((ActionEvent e) -> onClickBuscar());
        painel1.add(procurar, bag2);
        
        bag2.insets = new Insets(5,5,5,5);
        bag2.gridx = 3;
        bag2.anchor = GridBagConstraints.EAST;
        verPacote = new button_icone(Constantes.CAMINHO_IMAGENS + "detalhes24.png", "Ver Itens de Pacote", 200,30);
        painel1.add(verPacote, bag2);
        verPacote.addActionListener((e) -> onClickVerPacote());
        
        //Adicionando lista de clientes que possuem itens de pacote
        verPacote.setEnabled(false);
        clientesComPacote = Cliente.obterClientesComPacote();
        
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
        TabelaCliente.criarTabela();
        listScroller.setViewportView(TabelaCliente.getTabela());
        TabelaCliente.getTabela().getParent().setBackground(Color.WHITE);
        
        //Adicionando evento para identificar duplo clique
        TabelaCliente.getTabela().addMouseListener(onDoubleClickTabela());
        TabelaCliente.getTabela().addMouseListener(onClickTabela());
        
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

        button_icone button = new button_icone(Constantes.CAMINHO_IMAGENS + "add.png", "Adicionar Cliente", 40, 50);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ouvintesPainelCliente ouvinte =  new ouvintesPainelCliente();
                ouvinte.onClickAddCliente();
            }
        });
        painel3.add(button, c);
        
        //Adicionando botão 2
        c.gridx = 1;
        c.gridy = 0;
        button_icone button2 = new button_icone(Constantes.CAMINHO_IMAGENS + "remover.png", "Remover Cliente", 40, 50);
        button2.addActionListener((ActionEvent e) -> onClickRemoverCliente());
        painel3.add(button2, c);
        
        //Adicionando botão 3
        c.gridx = 2;
        c.gridy = 0;
        button_icone button3 = new button_icone(Constantes.CAMINHO_IMAGENS + "editar.png", "Editar Cliente", 40, 50);
        button3.addActionListener((ActionEvent e) -> onClickAtualizarCliente());
        painel3.add(button3, c);
        
        //Adicionando botão 4
        c.gridx = 3;
        c.gridy = 0;
        button_icone button4 = new button_icone(Constantes.CAMINHO_IMAGENS + "horario.png", "Marcar Horário", 40, 50);
        button4.addActionListener((e) -> onClickAgendar());
        painel3.add(button4, c);
        
    }
    
    private void onClickBuscar() {
        
        String busca;
        
        busca = nomeCliente.getText();
        if(!busca.equals("")){
            TabelaCliente.buscarClientesTabela("nome_cliente containing '" + busca + "'");
        } else {
            TabelaCliente.atualizarTabela();
        }
        
        
    }
    
    private void onClickVerPacote() {
        
        try {
            String scid, nome;
            int cid;
            scid = (String) TabelaCliente.getTabela().getValueAt(TabelaCliente.getTabela().getSelectedRow(), 0);
            cid = Integer.valueOf(scid);
            nome = (String) TabelaCliente.getTabela().getValueAt(TabelaCliente.getTabela().getSelectedRow(), 1);
            
            new AcompanhamentoPacote(nome, cid);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um cliente para ver o pacote.",
                        "Selecione um Cliente", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    private void onClickRemoverCliente() {
        String scid;
        int cid;
        
        try {
            scid = (String) TabelaCliente.getTabela().getValueAt(TabelaCliente.getTabela().getSelectedRow(), 0);
            cid = Integer.valueOf(scid);

            Cliente.remover(cid);

            TabelaCliente.atualizarTabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um cliente para Remover.",
                        "Selecione um Cliente", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    private void onClickAgendar() {
        
        if (TabelaCliente.getTabela().getSelectedRow() >= 0) {
            //Entra aqui se houver alguma linha selecionada
            
            String scid;
            int cid;
            scid = (String) TabelaCliente.getTabela().getValueAt(TabelaCliente.getTabela().getSelectedRow(), 0);
            cid = Integer.valueOf(scid);
            Cliente c = new Cliente(cid);
            
            CadastroHorario h = new CadastroHorario();
            h.definirPresetCliente(cid);
            h.definirPresetProfissional(c.getProfissionalpref());
            h.definirPresetData();
            
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um cliente para Agendar.",
                        "Selecione um Cliente", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    private void onClickAtualizarCliente() {
        ouvintesPainelCliente ls = new ouvintesPainelCliente();        
        
        try {
            String scid;
            int cid;

            scid = (String) TabelaCliente.getTabela().getValueAt(TabelaCliente.getTabela().getSelectedRow(), 0);
            cid = Integer.valueOf(scid);

            ls.onClickAtualizarCliente(cid);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um cliente para Editar.",
                        "Selecione um Cliente", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    private void verificarQuestaoDoPacote() {
        verPacote.setEnabled(false);
        if (TabelaCliente.getTabela().getSelectedRow() > -1) {
            String scid;
            int cid;
            scid = (String) TabelaCliente.getTabela().getValueAt(TabelaCliente.getTabela().getSelectedRow(), 0);
            cid = Integer.valueOf(scid);
            
            for (int i : clientesComPacote) {
                if (i == cid) verPacote.setEnabled(true);
            }
            
        }
    }
    
    private MouseAdapter onClickTabela() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                verificarQuestaoDoPacote();
            }
        };
    }
    
    private MouseAdapter onDoubleClickTabela() {
        
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2){
                    onClickAtualizarCliente();
                }
            }
        };
        
    }
    
}
