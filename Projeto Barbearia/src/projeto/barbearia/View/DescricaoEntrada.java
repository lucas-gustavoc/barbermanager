
package projeto.barbearia.View;

/**
 *
 * @author lucas
 */
public class DescricaoEntrada {
    
    public static final int SERVICO = 0;
    
    public static final int PACOTE = 1;
    
    public static final int PRODUTO = 2;
    
    private String prefixo = "NOVA VENDA";
    
    private String cliente = "";
    
    private String prodOuServ = "";
    
    public String getDescricao() {
        return prefixo + prodOuServ + cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = (cliente.equals("")) ? "" : " PARA " + cliente;
    }

    public void setProdOuServ(String prodOuServ, int tipo) {
        this.prodOuServ = prodOuServ;
        switch (tipo) {
            case 0:
                this.prefixo = "SERVICO DE ";
                break;
            case 1:
                this.prefixo = "VENDA DE PACOTE ";
                break;
            case 2:
                this.prefixo = "VENDA DE ";
                break;
            default:
                this.prefixo = "NOVA VENDA";
                break;
        }
    }
    
}
