package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class button_icone extends JButton {
    
    public button_icone(String path, String text, int largura, int altura) {
        
        ImageIcon img = new ImageIcon(path);
        this.setIcon(img);
        this.setText(text);
        this.setFont(new Font("Dialog", Font.PLAIN, 15));
        Dimension dimen = new Dimension(largura, altura);
        setPreferredSize(dimen);
        this.setSize(largura, altura);
        this.setFocusable(false);
        this.setBackground(Color.LIGHT_GRAY);
        
    }
    
    public button_icone(String path, int largura, int altura) {
        
        ImageIcon img = new ImageIcon(path);
        this.setIcon(img);
        this.setFont(new Font("Dialog", Font.PLAIN, 15));
        setSize(largura, altura);
        this.setFocusable(false);
        this.setBackground(Color.LIGHT_GRAY);
        
    }
    
    public button_icone(String texto, Color corFundo, Color corLetras, int largura, int altura, int fontSize) {
        setText(texto);
        setSize(largura, altura);
        setFont(new Font("Dialog", Font.PLAIN, fontSize));
        Dimension dimen = new Dimension(largura, altura);
        setPreferredSize(dimen);
        setForeground(corLetras);
        setBackground(corFundo);
        setFocusable(false);
    }
}
