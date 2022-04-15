package bmkey.amosnum;

import bmkey.crypt.HashMD5;
import bmkey.Conector;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class AmosNum {
    
    public boolean gerar() {
        boolean ret;
        String mac = MACAddress.get();
        String amos = HashMD5.getHashMD5("TheGreatLink59" + mac);
        Conector c = new Conector();
        
        ret = c.cadastrarAmosNum(amos);
        
        return ret;
    }
    
    public boolean validar() {
        boolean ret = false;
        String mac = MACAddress.get();
        String amos = HashMD5.getHashMD5("TheGreatLink59" + mac);
        String amosAtual;
        Conector c = new Conector();
        
        amosAtual = c.obterAmosNum();
        
        if (amos.equals(amosAtual)) {
            ret = true;
        } else {
            JOptionPane.showMessageDialog(null, "Houve um erro na validação do computador. "
                    + "Por favor, entre em contato com suporte@barbermanager.com para mais informações.", 
                    "Computador Inválido", JOptionPane.ERROR_MESSAGE);
        }
        
        return ret;
    }
    
}
