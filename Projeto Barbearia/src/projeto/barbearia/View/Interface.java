package projeto.barbearia.View;

import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import projeto.barbearia.Model.Constantes;

public class Interface extends JFrame {

    private static JFrame frame;
    private static JTabbedPane TabsContainer;
    private static JPanel painelGeral;
    
    BufferedImage buttonIcon;

    public Interface() {
        
    }

    public void criaTudo()  {
        
        frame = this;
        frame.setSize(900, 600);
        frame.setTitle("Barber Manager");
        frame.setExtendedState( MAXIMIZED_BOTH );
        frame.setMinimumSize(new java.awt.Dimension(500,400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(new MenuBar());
        
        System.out.println("estamos aqui");
        ImageIcon img = new ImageIcon(Constantes.CAMINHO_IMAGENS + "icon.png");
        frame.setIconImage(img.getImage());
        
        TabsContainer = new JTabbedPane();
        
        painelGeral = new PainelInicial();
        TabsContainer.add("Inicio", painelGeral);
        //criandoTabs(painelGeral,"Inicio");
        frame.add(TabsContainer);
        //frame.pack();
    }
    
    
    public void criandoTabs(Component component, String nome) {
        
        int existe = TabsContainer.indexOfTab(nome);
        if (existe == -1) {
            
            TabsContainer.add(nome, component);
            int index = TabsContainer.indexOfTab(nome);
            TabsContainer.setSelectedIndex(index);
            int i = TabsContainer.getSelectedIndex();
            TabsContainer.setTabComponentAt(i, new ButtonTabComponent(TabsContainer));
        }
        
        else{
            int index = TabsContainer.indexOfTab(nome);
            TabsContainer.setSelectedIndex(index);
        }
    }
    
    public void removeTabs(String nome) {
        int index = TabsContainer.indexOfTab(nome);
        TabsContainer.remove(index);
    }
    
    public void desabilitarJanela() {
        setEnabled(false);
    }
    
    public void habilitar() {
        setEnabled(true);
    }
}
