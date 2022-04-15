
package validador.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;
import validador.model.Chave;
import validador.model.KeyGen;

/**
 *
 * @author lucas
 */
public class TelaInicial {
    
    public void onClickCopy(JLabel lbl) {
        String s = lbl.getText();
        
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(s);
        clipboard.setContents(selection, null);
        
        JOptionPane.showMessageDialog(null, "Chave copiada para o clipboard.",
                        "Chave Copiada", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void onClickGerar(JLabel lbl) {
        KeyGen k = new KeyGen();
        Chave c = k.obterChave();
        lbl.setText(c.getChave());
    }
    
    public void onClickCadastrar(String c) {
        if (c.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor, gere uma chave.",
                        "Chave Vazia!", JOptionPane.WARNING_MESSAGE);
        } else {
            Chave chave = new Chave();
            boolean sucesso;
            
            chave.setChave(c);
            sucesso = chave.cadastrar();
            
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Chave cadastrada com sucesso!",
                        "Chave Cadastrada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Problema no cadastro! Não sei o que fazer...",
                        "Erro no Cadastro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void onClickConsultar() {
        new validador.view.TelaConsulta();
    }
    
}
