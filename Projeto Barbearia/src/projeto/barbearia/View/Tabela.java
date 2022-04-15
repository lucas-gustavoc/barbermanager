package projeto.barbearia.View;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Tabela extends JTable{
    
    public Tabela(String[][] dados2, String[] colunas2) {
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2);
        this.setModel(model);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setCellSelectionEnabled(false);
        this.setRowSelectionAllowed(true);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {    
        if(column == 0) {
            return false;
        }
        return false; 
    }   
    
    public void adicionarColuna(String titulo) {
        TableColumn coluna = new TableColumn();
        coluna.setHeaderValue(titulo);
        addColumn(coluna);
    }
    
    public void deletarColuna(String titulo) {
        TableColumn coluna = getColumn(titulo);
        removeColumn(coluna);
    }
    
}
