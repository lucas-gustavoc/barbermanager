
package projeto.barbearia.View;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import projeto.barbearia.Model.Natureza;

/**
 *
 * @author lucas
 */
public class TabelaNatureza extends JTable implements Atualizavel {
    
    public TabelaNatureza() {
        super();
    }
    
    public void preencherTabela() {
        String[][] dados2;
        String[] colunas2 = new String[]{"ID", "Descrição", "Ref.", "Entrada", "Saída"};
        
        dados2 = Natureza.buscar();
        
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        this.setModel(model);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setCellSelectionEnabled(false);
        this.setRowSelectionAllowed(true);
        
        //Centralizando e mudando tamanho da coluna ID
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        this.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        this.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        this.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        this.getColumnModel().getColumn(0).setMaxWidth(40);
        this.getColumnModel().getColumn(1).setMinWidth(250);
    }

    @Override
    public void atualizar() {
        preencherTabela();
    }
}
