
package projeto.barbearia.Model;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author lucas
 */
public class Formatador {
    
    
    
    /**
     * 
     * 
     * CAIXA DE TEXTO FORMATADA PARA DATAS DD/MM/AAAA
     * 
     */
    
    
    /**
     * Método utilizado para aplicar uma máscara de data para um JFormattedTextField. 
     * O método não gera o objeto, mas apenas o formata conforme solicitado, incluindo 
     * também um MouseListener que, ao receber um click sobre o objeto, posiciona o cursor 
     * no início da caixa de texto.
     * 
     * @param txf FormattedTextField no qual a máscara será aplicada
     */
    public static void formatarTxfData(JFormattedTextField txf) {
        MaskFormatter maskData;
        try {
            maskData = new MaskFormatter("##/##/####");
            maskData.install(txf);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        txf.addMouseListener(onClickTxfData(txf));
    }
    
    /**
     * Método utilizado em conjunto com {@code formatarTxfData}.
     * 
     * @param txf FormattedTextField que receberá o MouseAdapter
     * @return MouseAdapter com a função de posicionar o cursor no início da caixa 
     * de texto diante de um click
     */
    private static MouseAdapter onClickTxfData(JFormattedTextField txf) {
        
        return new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                txf.setCaretPosition(0);
            }
        };
        
    }
}
