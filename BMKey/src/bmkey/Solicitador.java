package bmkey;

import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class Solicitador {
    
    /**
     * Método responsável por solicitar a chave ao usuário.
     * @return a chave inserida pelo usuário.
     */
    public String solicitarChave() {
        String chave;
        
        chave = JOptionPane.showInputDialog(null, "Por favor, insira sua chave de ativação", 
                    "Chave de Ativação", JOptionPane.PLAIN_MESSAGE);
        
        if (chave != null) {
            if (chave.equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, insira uma chave válida!", 
                        "Inserir Chave", JOptionPane.WARNING_MESSAGE);
                chave = JOptionPane.showInputDialog(null, "Por favor, insira sua chave de ativação", 
                        "Chave de Ativação", JOptionPane.PLAIN_MESSAGE);
                
                if (chave == null) chave = "";
            }
        } else {
            chave = "";
        }
        
        return chave;
    }
    
}
