
package projeto.barbearia.View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import projeto.barbearia.Controller.ControladorTelaAvisos;
import projeto.barbearia.Model.Constantes;

/**
 *
 * @author lucas
 */
public class TelaAvisos extends JFrame {
    
    private ControladorTelaAvisos controller = new ControladorTelaAvisos();
    
    public TelaAvisos() {
        construirInterface();
        setVisible(true);
    }
    
    private void construirInterface() {
        
        //Definindo propriedades da janela        
        setSize(500,300);
        setTitle("Lembretes de Hoje");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new GridBagLayout());
        
        //Alterando tamanho da janela caso somente um dos lembretes esteja ativado
        boolean somente1Ativado = false;
        if ((controller.avNiverAtivado || controller.avVctoAtivado) && 
                !(controller.avNiverAtivado && controller.avVctoAtivado)) {
            setSize(500,150);
            somente1Ativado = true;
        }
        
        //Inserindo componentes
        GridBagConstraints b = new GridBagConstraints();
        b.weightx = 1;
        b.weighty = 1;
        b.fill = GridBagConstraints.BOTH;
        b.insets = new Insets(3,7,(somente1Ativado) ? 7 : 0,7); //se tiver somente um, precisamos de insets embaixo
        if (controller.avNiverAtivado) {
            //Exibindo os lembretes de aniversário somentes se
            //eles estiverem ativados
            add(obterLinha01(), b);
            b.gridy = 1;
            b.insets = new Insets(0,7,7,7);
        }
        
        //Exibindo os lembretes de vencimento somentes se
        //eles estiverem ativados
        if (controller.avVctoAtivado) add(obterLinha02(), b);
        
    }

    private JPanel obterLinha01() {
        JLabel[] lbls = controller.obterLabels(ControladorTelaAvisos.AVISO_ANIVERSARIO);
        return construirPaineis(lbls, "birthday-cake.png", "Aniversários");
    }

    private JPanel obterLinha02() {
        JLabel[] lbls = controller.obterLabels(ControladorTelaAvisos.AVISO_VENCIMENTO);
        return construirPaineis(lbls, "approve-invoice.png", "Contas a Pagar mais Próximas");
    }
    
    private JPanel construirPaineis(JLabel[] lbls, String icon, String title) {
        
        //Criando componentes
        JPanel p = new JPanel(new GridBagLayout());
        JScrollPane js = new JScrollPane();
        JPanel pInterno = new JPanel(new GridBagLayout());
        
        //Trabalhando componentes
        p.setBorder(BorderFactory.createTitledBorder(title));
        js.setBorder(null);
        js.setViewportView(pInterno);
        
        //Adicionando componentes no painel principal
        GridBagConstraints b = new GridBagConstraints();
        b.weightx = 1;
        b.weighty = 1;
        b.fill = GridBagConstraints.BOTH;
        p.add(js, b);
        
        //Construindo painel interno
        b = new GridBagConstraints();
        b.anchor = GridBagConstraints.WEST;
        for (JLabel lbl : lbls) {
            b.gridy++;
            b.weightx = 0;
            b.gridx = 0;
            b.insets = new Insets(4,5,0,5);
            ImageIcon img = new ImageIcon(Constantes.CAMINHO_IMAGENS + icon);
            pInterno.add(new JLabel(img), b);
            b.gridx = 1;
            b.weightx = 1;
            b.insets = new Insets(5,5,0,5);
            pInterno.add(lbl, b);
        }
        
        b.gridy++;
        b.weighty = 1;
        pInterno.add(new JLabel(""), b); //filler
        
        return p;
    }
    
}
