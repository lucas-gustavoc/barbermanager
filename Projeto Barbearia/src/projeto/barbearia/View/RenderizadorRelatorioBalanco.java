
package projeto.barbearia.View;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author lucas
 */
public class RenderizadorRelatorioBalanco extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        ((DefaultTableCellRenderer)cellComponent).setHorizontalAlignment(CENTER);
        
        
        return cellComponent;
    }
}
