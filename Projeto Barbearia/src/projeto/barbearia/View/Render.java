/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class Render extends JLabel implements TableCellRenderer{

    public static int contagem;
    
    public Render() {
        setOpaque(true);
        contagem = 0;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition( SwingConstants.CENTER );
        setVerticalTextPosition( SwingConstants.CENTER );
        setBackground(Color.WHITE);
        if(value != null){
            this.setText(String.valueOf(value));
        }
        else {
            this.setText("");
        }
        this.setIcon(Agendado(table, isSelected, column));
        return this;
    }
    
    public void setValue(Object value) {
        setBackground(Color.GRAY);
        setForeground(Color.WHITE);
    }
    
    private ImageIcon Agendado(Component component, boolean selecionada , int coluna) {
        if(selecionada && coluna != 0) {
            ImageIcon icone = new ImageIcon("C:\\Users\\mestr\\OneDrive\\Documents\\Mateus\\Programação\\Projetos NetBeans\\Imagens\\add24.png");
            return icone;
        }
        return null;
    }
    
}
