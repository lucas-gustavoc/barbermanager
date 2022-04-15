
package projeto.barbearia.View;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public abstract class CacheAtualizacao {
    
    private static ArrayList<Atualizavel> a;
    private static ArrayList<String> endereco;
    
    /**
     * Armazena um item que precisa ser atualizado após algum processo.
     * Deve ser endereçado a uma situação específica.
     * 
     * @param item contém o item que será atualizado
     * @param enderecoItem contém o endereço de onde a atualização será chamada
     */
    public static void adicionarItemAoCache(Atualizavel item, String enderecoItem) {
        
        if (a == null) a = new ArrayList<>();
        if (endereco == null) endereco = new ArrayList<>();
        
        a.add(item);
        endereco.add(enderecoItem);
        
    }
    
    /**
     * Verifica se há cache para esse endereço. Caso exista,
     * a atualização do item será chamada.
     * 
     * @param enderecoItem 
     */
    public static void checarEAtualizar(String enderecoItem) {
        
        if (a == null) a = new ArrayList<>();
        if (endereco == null) endereco = new ArrayList<>();
        
        for (int i = 0; i < a.size(); i++) {
            if (endereco.get(i).toUpperCase().equals(enderecoItem.toUpperCase())) {
                a.get(i).atualizar();
                endereco.remove(i);
                a.remove(i);
            }
        }
        
    }
    
}
