package bmkey.atscode;

import bmkey.Conector;
import bmkey.crypt.RC4;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author lucas
 */
public class ATSCode {
    
    /**
     * Método utilizado para gerar e cadastrar um novo ATSCode no banco de dados.
     * @return boolean indicando o sucesso ou não da operação.
     */
    public boolean gerar() {
        boolean ret = false;
        String ats = generateATSCode();
        Conector c = new Conector();
        
        //O código JB2A foi definido aleatoriamente para comprovar a liberação
        //do usuário para iniciar o período de testes.
        if (c.obterATSCode().equals("JB2A")) {
            ret = c.cadastrarATSCode(ats);
        }
        
        return ret;
    }
    
    /**
     * Método utilizado para retornar a quantidade de dias que o usuário 
     * ainda tem para testar o produto.
     * @return um int com a quantidade de dias, sendo -1 quando o usuário não deve
     * mais ter acesso ao software até que ele ative-o.
     */
    public int getTrialDays() {
        int ret = -1;
        
        if (validarATSCode(getATSCode())) ret = trialDays;
        
        return ret;
    }
    
    //Variável utilizada para armazenar a quantidade de dias restantes do trial.
    private int trialDays;
    
    /**
     * Este método verifica se o ATSCode cadastrado no banco de dados não foi
     * corrompido, bem como alimenta a variável trialDays com a quantidade de dias
     * restantes para o trial do usuário.
     * @param atscode ATSCode obtido no banco de dados.
     * @return boolean indicando se houve sucesso no cálculo.
     */
    private boolean validarATSCode(String atscode) {
        int ret = 0;
        boolean valid = true;
        java.util.Date data = null;
        
        /**
         * Este código valida a autenticidade do código ATS, garantindo que o
         * mesmo não tenha sido alterado por crackers diretamente no banco de dados.
         * As regras de autenticidade já foram definidas no momento da geração do código.
         */
        
        //Esta é a primeira validação de autenticidade.
        if (!atscode.startsWith("jj")) valid = false;
        
        //Esta é a segunda validação de autenticidade.
        if (!atscode.endsWith("j")) valid = false;
        
        //Esta é a terceira validação de autenticidade.
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            data = formato.parse(atscode.substring(2, 12));
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            valid = false;
        }
        
        
        if (valid) {
            
            /**
             * Caso o programa entre aqui, entende-se que o código ATS cadastrado
             * é autêntico. A partir disso, basta validar se a data atual é menor ou
             * igual à data de expiração. Sendo maior, significa que o período ATS
             * já finalizou.
             */
            
            java.util.Date dataExpiracao,
                           dataHoje = new java.util.Date();
            Calendar c = Calendar.getInstance(); 
            c.setTime(data); 
            c.add(Calendar.DATE, 7);
            dataExpiracao = c.getTime();
            
            c.setTime(dataHoje); 
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            dataHoje = c.getTime();
            
            try {
                //Comparação de datas in a crazy way
                long diff = dataExpiracao.getTime() - dataHoje.getTime();
                ret = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                //System.out.println(e.getMessage());
                valid = false;
            }
            
        }
        
        ret++;
        trialDays = ret;
        
        return valid;
    }
    
    /**
     * Método utilizado para obter o ATSCode do banco de dados.
     * @return o ATSCode cadastrado no banco de dados.
     */
    private String getATSCode() {
        String obtidaDoBancoDeDados, ret;
        Conector c = new Conector();
        RC4 crypt = new RC4();
        
        obtidaDoBancoDeDados = c.obterATSCode();
        
        try {
            ret = crypt.decrypt(crypt.getByteArrayFromString(obtidaDoBancoDeDados), "LegendOfZelda186");
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            ret = "";
        }
        
        return ret;
    }
    
    /**
     * Método utilizado internamente para gerar efetivamente um novo ATSCode a 
     * partir da data de hoje.
     * @return String com o ATSCode ou uma String vazia em caso de erro na geração.
     */
    private String generateATSCode() {
        String ret, data;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date datem = new java.util.Date();
        data = "jj" + dateFormat.format(datem) + "j";
        //data = "jj01/10/2017j";
        RC4 crypt = new RC4();
        
        try {
            ret = crypt.getStringFromByteArray(crypt.encrypt(data, "LegendOfZelda186"));
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            ret = "";
        }
        
        return ret;
    }
    
}
