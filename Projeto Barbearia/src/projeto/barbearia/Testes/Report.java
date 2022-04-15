
package projeto.barbearia.Testes;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Classe utilizada para reportar, em tempo de execução, as informações geradas
 * pelos testes. 
 * 
 * @author lucas
 */
public class Report extends JFrame {
        
    private ArrayList<String> items = new ArrayList<>();
    private JLabel lbl;
    
    /**
     * Construtor.
     */
    public Report() {
        constructInterface();
    }
    
    private void constructInterface() {
        //Window
        setLayout(new FlowLayout());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        //setLocationRelativeTo(null);
        setLocation(50, 100);
        setTitle("Reports");
        setVisible(true);
        
        lbl = new JLabel();
        JScrollPane sp = new JScrollPane(lbl);
        sp.setPreferredSize(new Dimension(350, 150));
        add(sp);
    }
    
    public void write(String origem, String msg) {
        items.add(origem + ": " + msg);
        
        String text = "";
        for (String s : items) {
            text += s + "<br>";
        }
        text = "<html><body>" + text + "</body></html>";
        lbl.setText(text);
    }
    
}
