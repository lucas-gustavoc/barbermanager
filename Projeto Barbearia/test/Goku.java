
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class Goku extends JLabel {

    private int ki;
    public Goku(String text) {
        super(text);
        ki = 10;
    }

    public Goku() {
        ki = 10;
    }
    
    public void kameHameHaa() {
        if (ki >= 5) {
            setText("<<<**********************");
            ki -= 5;
            JOptionPane.showMessageDialog(getParent(), "Seu ki é de: " + ki, 
                    "KINOMETRO", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(getParent(), "Lamento, vc não tem ki pra isso...", 
                    "Problema", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void genkiDama() {
        if (ki >= 3) {
            setText("((((()))))");
            ki -= 3;
            JOptionPane.showMessageDialog(getParent(), "Seu ki é de: " + ki, 
                    "KINOMETRO", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(getParent(), "Lamento, vc não tem ki pra isso...", 
                    "Problema", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void disquinhoDeEnergia() {
        if (ki >= 1) {
            setText("0");
            ki -= 1;
            JOptionPane.showMessageDialog(getParent(), "Seu ki é de: " + ki, 
                    "KINOMETRO", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(getParent(), "Lamento, vc não tem ki pra isso...", 
                    "Problema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getKi() {
        return ki;
    }

    public void setKi(int ki) {
        this.ki = ki;
    }

    
    
    
}
