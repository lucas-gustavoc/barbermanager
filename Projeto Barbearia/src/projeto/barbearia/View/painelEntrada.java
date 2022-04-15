
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
import projeto.barbearia.Controller.ouvintesPainelEntrada;
import projeto.barbearia.Model.Constantes;
import projeto.barbearia.Model.Entrada;
import projeto.barbearia.Model.Tipos.TipoPreenchimentoTabela;

/**
 *
 * @author mestr
 */
public class painelEntrada extends JPanel{
    
    private JPanel painel1;
    private JPanel painel2;
    private JTextField caixaTexto;
    
    public painelEntrada() {
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints bag = new GridBagConstraints();
        setLayout(bagLayout);
        
        painel1();
        painel2();
        
        bag.insets = new Insets(5,5,5,5);
        bag.gridx = 0;
        bag.gridy = 0;
        bag.fill = GridBagConstraints.BOTH;
        add(painel1, bag);
        
        bag.gridy = 1;
        bag.weighty = 1;
        bag.weightx = 1;
        add(painel2, bag);
    }
    
    private void painel1() {
        painel1 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        
        //Cria JLabels
        JLabel busca = new JLabel("Buscar: ");
        busca.setFont(new Font("Dialog", Font.PLAIN, 15));
        
        //CriaJTextField
        caixaTexto = new JTextField(20);
        
        //Adiciona botão voltar
        button_icone voltar = new button_icone(Constantes.CAMINHO_IMAGENS + "voltar16.png", 20,10);
        voltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                new ouvintesPainelEntrada().onClickVoltar();
            }
        });
        bag.gridy = 0;
        bag.gridx = 0;
        bag.insets = new Insets(0,0,0,5);
        painel1.add(voltar, bag);
        
        //Adiciona textoBusca
        bag.insets = new Insets(0,0,0,0);
        bag.gridx = 1;
        bag.gridy = 0;
        bag.anchor = GridBagConstraints.WEST;
        painel1.add(busca, bag);
        
        //Adicionar Caixa de texto
        bag.insets = new Insets(0,5,0,0);
        bag.gridx = 2;
        bag.gridy = 0;
        painel1.add(caixaTexto, bag);
        
        //Adiciona botão de procura
        bag.weightx = 1;
        bag.gridx = 3;
        button_icone procurar = new button_icone(Constantes.CAMINHO_IMAGENS + "procurar2.png", 20,10);
        procurar.addActionListener((e) -> onClickBuscar());
        painel1.add(procurar, bag);
        
        //Adiciona botão de detalhes
        bag.insets = new Insets(0,0,0,3);
        bag.anchor = GridBagConstraints.EAST;
        bag.gridy = 0;
        bag.gridx = 4;
        button_icone detalhes = new button_icone(Constantes.CAMINHO_IMAGENS + "detalhes24.png", "Ver Detalhes", 150, 34);
        detalhes.addActionListener((e) -> onClickVerDetalhes());
        painel1.add(detalhes, bag);
        
        //Adiciona botão de adicionar
        bag.gridx = 5;
        bag.weightx = 0;
        button_icone adicionar = new button_icone(Constantes.CAMINHO_IMAGENS + "add24.png", "Nova Entrada", 150,34);
        adicionar.addActionListener((e) -> onClickAdicionar());
        painel1.add(adicionar, bag);
        
        //Adiciona botão de adicionar
        bag.gridx = 6;
        bag.weightx = 0;
        button_icone remover = new button_icone(Constantes.CAMINHO_IMAGENS + "excluir.png", "Excluir", 150,34);
        remover.addActionListener((e) -> onClickRemover());
        painel1.add(remover, bag);
        
        
        
    }
    
    private void painel2() {
        painel2 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        //Cria Tabela
        TabelaEntrada.preencherTabela(TipoPreenchimentoTabela.CRIAR, "");
        TabelaEntrada.getTabela().addMouseListener(onDoubleClickTabela());
        
        JScrollPane listScroller = new JScrollPane();
        listScroller.setBackground(Color.WHITE);
        listScroller.setViewportView(TabelaEntrada.getTabela());
        TabelaEntrada.getTabela().getParent().setBackground(Color.WHITE);
        
        bag.gridx = 0;
        bag.gridy = 0;
        bag.fill = GridBagConstraints.BOTH;
        bag.weightx = 1;
        bag.weighty = 1;
        painel2.add(listScroller, bag);
    }
    
    private void onClickBuscar() {
        
        String busca;
        
        busca = caixaTexto.getText();
        if(!busca.equals("")){
            TabelaEntrada.preencherTabela(TipoPreenchimentoTabela.BUSCAR, 
                    "nome_entrada containing '" + busca + "'");
        } else {
            TabelaEntrada.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
        }
        
    }
    
    private void onClickRemover() {
        
        String sid;
        int id;
        
        try {
            sid = (String) TabelaEntrada.getTabela()
                .getValueAt(TabelaEntrada.getTabela().getSelectedRow(), 0);
            id = Integer.valueOf(sid);

            Entrada.remover(id);

            TabelaEntrada.preencherTabela(TipoPreenchimentoTabela.ATUALIZAR, "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione uma entrada para REMOVER.",
                        "Selecione uma Entrada", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    private void onClickAdicionar() {
        new ouvintesPainelEntrada().onClickAdicionarCadastro();
    }
    
    private void onClickVerDetalhes() {
        
        String sid;
        int id;
        
        try {
            sid = (String) TabelaEntrada.getTabela()
                .getValueAt(TabelaEntrada.getTabela().getSelectedRow(), 0);
            id = Integer.valueOf(sid);

            ouvintesPainelEntrada ouvinte = new ouvintesPainelEntrada();
            ouvinte.onClickVerDetalhes(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione uma entrada para VER DETALHES.",
                        "Selecione uma Entrada", JOptionPane.WARNING_MESSAGE);
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
