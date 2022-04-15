package bmkey;

import bmkey.atscode.ATSCode;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class PeriodoDeTeste {
    
    public boolean verificar() {
        boolean ret = false;
        String msg;
        ATSCode ats = new ATSCode();
        
        if (!ats.gerar()) {
            //Entra aqui se já existe um período de teste correndo
            int dias = ats.getTrialDays();
            
            if (dias < 0) {
                //Entra aqui quando o período de teste já expirou
                msg = "Período de testes já encerrado. Por favor, ative seu "
                        + "Barber Manager para continuar utilizando.";
            } else {
                //Entra aqui quando ainda existe período de teste ativo
                switch (dias) {
                    case 1:
                        msg = "Seu período de testes expira em 1 dia.";
                        break;
                    case 0: 
                        msg = "Seu período de testes expira hoje!";
                        break;
                    default:
                        msg = "Seu período de testes expira em " + dias + " dias.";
                        break;
                }
                ret = true;
            }
            
        } else {
            //Entra aqui se o período acabou de ser inicializado
            ret = true;
            msg = "Parabéns! Seu período de testes de 7 dias acaba de ser iniciado.";
        }
        
        JOptionPane.showMessageDialog(null, msg, "Período de Testes", JOptionPane.INFORMATION_MESSAGE);
        
        return ret;
    }
    
}
