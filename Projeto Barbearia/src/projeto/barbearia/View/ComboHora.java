
package projeto.barbearia.View;

import javax.swing.JComboBox;
import projeto.barbearia.Model.Parametros;

/**
 *
 * @author lucas
 */
public class ComboHora extends JComboBox {
    
    private String horarioAtual;
    
    public ComboHora() {
        super();
        horarioAtual = "";
        preencherCombo();
    }
    
    public ComboHora(String horaAtual) {
        super();
        this.horarioAtual = horaAtual;
        preencherCombo();
    }
    
    private void preencherCombo() {
        
        String inicioExpediente = Parametros.getInicioExpediente();
        String fimExpediente = Parametros.getFimExpediente();
        int hora;
        int minuto;
        int horaAtual = 0;
        int minutoAtual = 0;
        int horaFim;
        int minutoFim;
        int colon;
        boolean meiaHora = false;
        
        //Definindo valores da hora inicial
        colon = inicioExpediente.indexOf(":");
        hora = Integer.valueOf(inicioExpediente.substring(0, colon));
        minuto = Integer.valueOf(inicioExpediente.substring(colon + 1));
        
        //Definindo valores da hora final
        colon = fimExpediente.indexOf(":");
        horaFim = Integer.valueOf(fimExpediente.substring(0, colon));
        minutoFim = Integer.valueOf(fimExpediente.substring(colon + 1));
        
        //Definindo os valores da hora atual
        if (!this.horarioAtual.equals("")) {
            colon = this.horarioAtual.indexOf(":");
            horaAtual = Integer.valueOf(this.horarioAtual.substring(0, colon));
            minutoAtual = Integer.valueOf(this.horarioAtual.substring(colon + 1));
        }
        
        //indexAtual armazenará o index de cada item, 
        //possibilitando setar como selecionado o horarioAtual
        int indexAtual = 0;
        int i = 0;
        
        //complementoDireita será usado para colocar um 0 a mais à direita, caso necessário
        String complementoDireita = "0";
        
        //complementoEsquerda será usado para colocar um 0 à esquerda, caso necessário
        String complementoEsquerda = "0";
        
        if (minuto == 30) {
            meiaHora = true;
            complementoDireita = "";
        }
        
        if (hora >= 10) complementoEsquerda = "";
        
        while (hora != horaFim || minuto != minutoFim) {
            
            this.addItem(complementoEsquerda + hora + ":" + minuto + complementoDireita);
            
            if (hora == horaAtual && minuto == minutoAtual) indexAtual = i;
            
            if (meiaHora) {
                hora++;
                meiaHora = false;
                minuto = 0;
                complementoDireita = "0";
            } else {
                minuto = 30;
                meiaHora = true;
                complementoDireita = "";
            }
            
            if (hora >= 10) complementoEsquerda = "";
            
            i++;
            
        }
        
        setSelectedIndex(indexAtual);
        
    }
    
}
