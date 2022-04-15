
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 *
 * @author lucas
 */
public class Capsula {
    
    private int altura;
    private int largura;
    private int drones;
    
    private int peso;

    public static final String PROP_PESO = "peso";

    /**
     * Get the value of peso
     *
     * @return the value of peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Set the value of peso
     *
     * @param peso new value of peso
     */
    public void setPeso(int peso) {
        int oldPeso = this.peso;
        this.peso = peso;
        propertyChangeSupport.firePropertyChange(PROP_PESO, oldPeso, peso);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }


    public Capsula(int altura, int largura, int drones) {
        this.altura = altura;
        this.largura = largura;
        this.drones = drones;
    }
    
    public void build() {
        String base = "";
        
        for (int i = 0; i < drones; i++) System.out.print("*");
        System.out.println("");
        for (int i = 0; i < largura; i++) base += "[]";
        for (int i = 0; i < altura; i++) System.out.println(base);
        
        setPeso(altura * largura);
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getDrones() {
        return drones;
    }

    public void setDrones(int drones) {
        this.drones = drones;
    }
    
    
    
}
