
package projeto.barbearia.View;

import java.awt.FlowLayout;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import projeto.barbearia.Model.Constantes;

/**
 *
 * @author lucas
 */
public class TelaBoasVindas extends JFrame {
    
    public TelaBoasVindas() {
        construirInterface();
    }
    
    private void construirInterface() {
        //Configurando janela
        setSize(250,150);
        setResizable(false);
        setTitle("");
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        
        //Adicionando Imagem
        ImageIcon img = new ImageIcon(Constantes.CAMINHO_IMAGENS + "Banner.png");
        JLabel lblImg = new JLabel(img);
        lblImg.setBounds(0,0,250,150);
        add(lblImg);
        
        setVisible(true);
        
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        
        dispose();
    }
    
}
