package kryptteste;

import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class KryptTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String serial = MACAddress.get();
        JOptionPane.showMessageDialog(null, "MAC Address: " + serial, "MAC", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
}
