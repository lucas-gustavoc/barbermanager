
package projeto.barbearia.View;

import javax.swing.JComboBox;
import projeto.barbearia.Model.Parametros;

/**
 *
 * @author lucas
 */
public class ComboHoraExpediente extends JComboBox {
    
    /**
     * Construção do Combo para utiliza-lo no cadastro
     * do INÍCIO do expediente.
     */
    public static final int INICIO_EXPEDIENTE = 0;
    
    /**
     * Construção do Combo para utiliza-lo no cadastro
     * do FINAL do expediente.
     */
    public static final int FINAL_EXPEDIENTE = 1;
    
    private String horarioAtual;
    
    public ComboHoraExpediente(int tipo) {
        super();
        
        switch (tipo) {
            case 0:
                //INICIO_EXPEDIENTE
                horarioAtual = Parametros.getInicioExpediente();
                break;
            case 1:
                //FINAL_EXPEDIENTE
                horarioAtual = Parametros.getFimExpediente();
                break;
            default:
                //OTHERS
                horarioAtual = "";
                break;
        }
        
        preencherCombo();
    }
    
    private void preencherCombo() {
        
        String inicioCombo = "00:00";
        String fimCombo = "23:30";
        int hora;
        int minuto;
        int horaAtual = 0;
        int minutoAtual = 0;
        int horaFim;
        int minutoFim;
        int colon;
        boolean meiaHora = false;
        
        //Definindo valores da hora inicial
        colon = inicioCombo.indexOf(":");
        hora = Integer.valueOf(inicioCombo.substring(0, colon));
        minuto = Integer.valueOf(inicioCombo.substring(colon + 1));
        
        //Definindo valores da hora final
        colon = fimCombo.indexOf(":");
        horaFim = Integer.valueOf(fimCombo.substring(0, colon));
        minutoFim = Integer.valueOf(fimCombo.substring(colon + 1));
        
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
        
        /**
         * MAGIC HAPPENING ******
         * 
         */
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
