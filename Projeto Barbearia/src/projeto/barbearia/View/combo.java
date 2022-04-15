package projeto.barbearia.View;

import javax.swing.JComboBox;

public class combo extends JComboBox{
    
    public combo(String[] profissionais) {
        for(int a = 0; a < profissionais.length; a++) {
            addItem(profissionais[a]);
        }
    }
    
    public void addItemUnico(String item) {
        addItem(item);
    }
    
    public void removerItemUnico(String item) {
        removeItem(item);
    }
}
