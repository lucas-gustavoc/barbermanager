package projeto.barbearia.View;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import projeto.barbearia.Controller.ouvintesCheckBoxList;

public class CheckBoxList extends JList {
    public CheckBoxList(String[] profissionais) {
        setCellRenderer(new CheckBoxCellRenderer());
        JList lista = this;
        Object[] cbArray = new Object[profissionais.length];
        for(int i = 0; i < profissionais.length; i++){
            cbArray[i] = new JCheckBox(profissionais[i]);
        }
        setListData(cbArray);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        for(int i = 0; i < lista.getModel().getSize(); i++){
            JCheckBox checkbox = (JCheckBox) lista.getModel().getElementAt(i);
            checkbox.setSelected(true);
            checkbox.addItemListener(new ouvintesCheckBoxList());
        }
    }
    
    public CheckBoxList(String[] profissionais, boolean removerIndex1) {
        String[] profissionais2 = profissionais;
        ArrayList<String> tirahora = new ArrayList<>();
        for(int b = 0; b < profissionais2.length; b++) {
            tirahora.add(profissionais2[b]);
            //System.out.println(profissionais2[b]);
        }
        tirahora.remove(0);
        profissionais2 = tirahora.toArray(new String[tirahora.size()]);
        
        setCellRenderer(new CheckBoxCellRenderer());
        JList lista = this;
        Object[] cbArray = new Object[profissionais2.length];
        for(int i = 0; i < profissionais2.length; i++){
            cbArray[i] = new JCheckBox(profissionais2[i]);
        }
        setListData(cbArray);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        for(int i = 0; i < lista.getModel().getSize(); i++){
            JCheckBox checkbox = (JCheckBox) lista.getModel().getElementAt(i);
            checkbox.setSelected(true);
            checkbox.addItemListener(new ouvintesCheckBoxList());
        }
    }
    
    private class CheckBoxCellRenderer implements ListCellRenderer{
        Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
        @Override
        public Component getListCellRendererComponent(
        JList list, Object value, int index,
        boolean isSelected, boolean cellHasFocus){
            JCheckBox checkbox = (JCheckBox) value;
            checkbox.setBackground(isSelected ?
            list.getSelectionBackground() : list.getBackground());
            checkbox.setForeground(isSelected ?
            list.getSelectionForeground() : list.getForeground());
            checkbox.setEnabled(list.isEnabled());
            checkbox.setFont(list.getFont());
            checkbox.setFocusPainted(false);
            checkbox.setBorderPainted(true);
            checkbox.setBorder(isSelected ? UIManager.getBorder(
            "List.focusCellHighlightBorder") : noFocusBorder);
            return checkbox;
        }
    }
    
    
}
