package rmk;

import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class Rmk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conector c = new Conector();
        if (c.zerarAtivacao()) {
            System.out.println("activation removed successfully");
            javax.swing.JOptionPane.showMessageDialog(null, 
                    "Chave de ativação removida com sucesso!", 
                    "Operação bem-sucedida", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("error trying to remove activation...");
            javax.swing.JOptionPane.showMessageDialog(null, 
                    "Erro ao remover a chave de ativação!", 
                    "Erro na Remoção", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
