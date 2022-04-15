
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import projeto.barbearia.Controller.ControladorRelatorio;
import projeto.barbearia.Controller.ouvintePainelCaixa;

/**
 *
 * @author mestr
 */
public class painelCaixa extends JPanel {
    
    private JPanel painel1;
    
    public painelCaixa() {
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints bag = new GridBagConstraints();
        setLayout(bagLayout);
        
        painel1();
        
        bag.gridx = 0;
        bag.gridy = 0;
        bag.weightx = 1;
        add(painel1, bag);
    }
    
    private void painel1() {
        painel1 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        
        //Cria botões
        button_icone buttonEntrada = new button_icone("Entrada", Color.LIGHT_GRAY, Color.BLACK, 250, 80, 35);
        buttonEntrada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ouvintePainelCaixa caixa = new ouvintePainelCaixa();
                caixa.onClickEntrada();
            }
        });
        button_icone buttonContas = new button_icone("Contas a Pagar", Color.LIGHT_GRAY, Color.BLACK, 250, 80, 30);
        buttonContas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ouvintePainelCaixa caixa = new ouvintePainelCaixa();
                caixa.onClickContasAPagar();
            }
        });
        button_icone buttonRelatorios = new button_icone("Relatórios", Color.LIGHT_GRAY, Color.BLACK, 250, 80, 35);
        buttonRelatorios.addActionListener((e) -> new TelaRelatorio(ControladorRelatorio.RELATORIO_BALANCO));
        
        bag.insets = new Insets(10,10,10,10);
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.gridx = 0;
        bag.gridy = 0;
        bag.weightx = 1;
        painel1.add(buttonEntrada, bag);
        
        bag.gridy = 1;
        painel1.add(buttonContas, bag);
        
        bag.gridy = 2;
        painel1.add(buttonRelatorios, bag);
    }
    
}
