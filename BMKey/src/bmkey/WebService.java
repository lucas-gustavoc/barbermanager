package bmkey;

import javax.swing.JOptionPane;

/**
 * Classe utilizada para comunicar-se com o WebService do Barber Manager.
 * 
 * @author lucas
 */
public class WebService {
    
    /**
     * Variável utilizada para indicar o status da operação de comunicação. Os
     * possíveis valores para esta variável são definidos nesta própria classe.
     * 
     */
    private static String status;

    public static String getStatus() {
        return status;
    }
    
    //Status definido sempre que o webservice não responde no momento do registro
    public static final String PROBLEMA_NA_CONEXAO_AO_REGISTRAR = "5400";
    
    //Status definido sempre que o webservice não responde no momento da vlidação
    public static final String PROBLEMA_NA_CONEXAO_AO_VALIDAR = "6400";
    
    public static final String CONEXAO_BEM_SUCEDIDA = "1200";
    
    /**
     * Método utilizado para registrar uma chave pela primeira vez.
     * 
     * @param chave chave a ser registrada
     * @return o código do PC, registrado junto à chave. Retorna uma String vazia
     * em caso de erro no registro.
     */
    public static String registrar(String chave) {
        AcessoWebService ws = new AcessoWebService();
        String ret = "0", wsresponse = "";
        
        try {
            wsresponse = ws.contatarWebService("http://104.41.42.113:8080/barbermanagerws/webresources/ws/registrarpc/" 
                    + chave);
            status = CONEXAO_BEM_SUCEDIDA;
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            status = PROBLEMA_NA_CONEXAO_AO_REGISTRAR;
        }
        
        if (!(wsresponse == null) && !wsresponse.equals("")) {
            ret = wsresponse;
            if (wsresponse.equals("0")) JOptionPane.showMessageDialog(null, "Chave inválida. "
                    + "Por favor, insira uma chave válida ou contate suporte@barbermanager.com", 
                    "Chave Inválida", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Erro de Conexão. "
                    + "Não foi possível conectar-se aos servidores de ativação. Contate suporte@barbermanager.com", 
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
        
        return ret;
    }
    
    /**
     * Método utilizado para fazer a validação da chave e do PC cadastrados no 
     * sistema do usuário.
     * @param chave chave utilizada na verificação.
     * @param pc código do PC a ser verificado.
     * @return boolean indicado a validade das credenciais oferecidas.
     */
    public static boolean validar(String chave, String pc) {
        AcessoWebService ws = new AcessoWebService();
        String wsresponse = "";
        boolean ret = false;
        
        try {
            wsresponse = ws.contatarWebService("http://104.41.42.113:8080/barbermanagerws/webresources/ws/validaracesso/" 
                    + chave + "/" + pc);
            status = CONEXAO_BEM_SUCEDIDA;
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            status = PROBLEMA_NA_CONEXAO_AO_VALIDAR;
        }
        
        if (!(wsresponse == null) && !wsresponse.equals("")) {
            if (wsresponse.equalsIgnoreCase("valid")) ret = true;
        }
        
        return ret;
    }
    
}
