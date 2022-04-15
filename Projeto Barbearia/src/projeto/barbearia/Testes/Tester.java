
package projeto.barbearia.Testes;

import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public abstract class Tester {
    
    protected boolean testingNow = false;
    
    public abstract void executarTeste();
    
    public boolean isTestingNow() {
        return testingNow;
    }
    
    protected boolean confirmarComUsuario() {
        boolean r = false;
        
        //Showing confirmation message
        Object[] options = {"Sim", "Não"};
        int n = JOptionPane.showOptionDialog(null,
            "O cadastro foi efetuado?",
            "Confirmação do Usuário",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            options,
            options[0]);
        
        if (n == JOptionPane.YES_OPTION) r = true;
        
        return r;
    }
    
}
