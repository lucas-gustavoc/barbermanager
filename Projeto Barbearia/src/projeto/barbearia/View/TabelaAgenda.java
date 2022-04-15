/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.barbearia.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author mestr
 */
public class TabelaAgenda extends JTable{
    
    private String[] colunas;
    
    public TabelaAgenda(String[][] dados2, String[] colunas2) {
        colunas = colunas2;
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2);
        this.setModel(model);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setCellSelectionEnabled(true);
        this.setRowHeight(25);
        this.setDefaultRenderer(Object.class, new Render());
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for(int i = 1; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setPreferredWidth(150);
        }
        
        addMouseListener(new innerClass(this));
        
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; 
    } 
    
    public void adicionarColuna(String titulo) {
        int index = 0;
        for(int a = 0; a < colunas.length; a++) {
            if(colunas[a].equals(titulo)) {
                System.out.println(colunas[a]);
                index = a;
                break;
            }
        }
        TableColumn coluna = new TableColumn(index);
        coluna.setHeaderValue(titulo);
        coluna.setPreferredWidth(150);
        coluna.setCellRenderer(new Render());
        addColumn(coluna);
        int index2 = getColumnModel().getColumnIndex(titulo);
        moveColumn(index2, 1);
    }
    
    public void deletarColuna(String titulo) {
        TableColumn coluna = getColumn(titulo);
        removeColumn(coluna);
    }
    
    class innerClass implements MouseListener{
        private JTable tabela;
        public innerClass(JTable tabela) {
            this.tabela = tabela;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
    }
}
