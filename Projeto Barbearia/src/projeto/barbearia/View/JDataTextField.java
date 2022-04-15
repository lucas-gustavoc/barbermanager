
package projeto.barbearia.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author lucas
 */
public class JDataTextField extends JFormattedTextField {
    
    /**
     * Construtor.
     */
    public JDataTextField() {
        configurarMascara();
    }
    
    /**
     * Aplicando a máscara.
     */
    private void configurarMascara() {
        MaskFormatter maskData;
        try {
            maskData = new MaskFormatter("##/##/####");
            maskData.install(this);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        addMouseListener(onClickTxfData());
    }
    
    /**
     * Método utilizado em conjunto com o construtor.
     * 
     * @return MouseAdapter com a função de posicionar o cursor no início da caixa 
     * de texto diante de um click
     */
    private MouseAdapter onClickTxfData() {
        
        return new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                setCaretPosition(0);
            }
        };
    }
    
    /**
     * Obtém o texto do JTextField considerando vazio em caso de existir somente a 
     * máscara no preenchimento.
     * 
     * @return texto escrito no JTextField ou uma String vazia caso o mesmo contenha 
     * somente a máscara
     */
    public String getTextMinusMask() {
        String texto = super.getText();
        if (texto.equals("  /  /    ")) texto = "";
        return texto;
    }
    
}
