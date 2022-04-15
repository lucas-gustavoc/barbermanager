
package projeto.barbearia.Model;

import java.io.FileNotFoundException;
import projeto.barbearia.Model.db.*;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilizada para obter os parâmetros sistêmicos do Software.
 * 
 * @author lucas
 */
public abstract class Parametros {
    
    /*
    * Enquanto não temos nada aqui, vamos usar este 
    * ponto para listar algumas tarefas a serem feitas depois:
    *  -> Fazer com que o Enter realize a busca nas caixas de busca;
    *  -> IMPORTANTE: Desenvolver um software de configuração dos parâmetros iniciais
    *     do sistema.
    *  -> Formatar campos numéricos e para data, impedindo a inserção de dados incorretos.
    *  -> Implementar avisos e alertas relativos às saídas vencidas.
    *  -> Implementar inserção de datas via calendário suspenso
    *  -> IMPORTANTE: Incluir a definição de duração de serviços
    */
    
    public static String getDataBasePath() {
        return "BARBEARIA.FDB";
    }
    
    /**
     * Método utilizado para obter o horário de início do expediente.
     * 
     * @return uma String com o horário de início do expediente, no formato hh:mm.
     */
    public static String getInicioExpediente() {
        
        String s = "";
        Select sl = new Select("select valor_parametro from parametro "
                + "where nome_parametro = 'INICIOEXPEDIENTE'");
        ResultSet rs;
        
        sl.execute();
        
        try {
            rs = sl.getResult();
            rs.first();
            s = rs.getString("valor_parametro");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return s;
        
    }
    
    /**
     * Método utilizado para obter o horário de fim do expediente.
     * 
     * @return uma String com o horário de fim do expediente, no formato hh:mm.
     */
    public static String getFimExpediente() {
        
        String s = "";
        Select sl = new Select("select valor_parametro from parametro "
                + "where nome_parametro = 'FIMEXPEDIENTE'");
        ResultSet rs;
        
        sl.execute();
        
        try {
            rs = sl.getResult();
            rs.first();
            s = rs.getString("valor_parametro");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return s;
        
    }
    
    /**
     * Método utilizado para alterar o horário do início do expediente.
     * 
     * @param hora define a hora a ser cadastrada como Início de Expediente
     * @return valor boolean indicando o sucesso ({@code true}) ou não ({@code false}) da operação
     */
    public static boolean setInicioExpediente(String hora) {
        Update u = new Update("PARAMETRO");
        u.setCondition("nome_parametro = 'INICIOEXPEDIENTE'");
        u.addField("valor_parametro", hora, "string");
        return u.execute();
    }
    
    /**
     * Método utilizado para alterar o horário do fim do expediente.
     * 
     * @param hora define a hora a ser cadastrada como Final de Expediente
     * @return valor boolean indicando o sucesso ({@code true}) ou não ({@code false}) da operação
     */
    public static boolean setFimExpediente(String hora) {
        Update u = new Update("PARAMETRO");
        u.setCondition("nome_parametro = 'FIMEXPEDIENTE'");
        u.addField("valor_parametro", hora, "string");
        return u.execute();
    }
    
    /**
     * Método utilizado para obter o status de ativação do Lembrete de Vencimento.
     * 
     * @return {@code true} em caso de ativado e {@code false} em caso de desativado.
     */
    public static boolean getLembreteVencimentosAtivado() {
        
        boolean r = false;
        String s = "";
        Select sl = new Select("select valor_parametro from parametro "
                + "where nome_parametro = 'LEMBRETEVENCIMENTOSATIVADO'");
        ResultSet rs;
        
        sl.execute();
        
        try {
            rs = sl.getResult();
            rs.first();
            s = rs.getString("valor_parametro");
            
            if (s.equals("1")) r = true;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return r;
        
    }
    
    /**
     * Método utilizado para obter o status de ativação do Lembrete de Aniversários.
     * 
     * @return {@code true} em caso de ativado e {@code false} em caso de desativado.
     */
    public static boolean getLembreteAniversarioAtivado() {
        
        boolean r = false;
        String s = "";
        Select sl = new Select("select valor_parametro from parametro "
                + "where nome_parametro = 'LEMBRETEANIVERSARIOATIVADO'");
        ResultSet rs;
        
        sl.execute();
        
        try {
            rs = sl.getResult();
            rs.first();
            s = rs.getString("valor_parametro");
            
            if (s.equals("1")) r = true;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return r;
        
    }
    
    
    
    /**
     * Método utilizado para obter a quantidade de dias antes do Lembrete de Vencimentos.
     * 
     * @return número de dias antes para lembrar o usuário.
     */
    public static int getQtdDiasLembreteVencimento() {
        
        int r = 1;
        String s = "";
        Select sl = new Select("select valor_parametro from parametro "
                + "where nome_parametro = 'LEMBRETEVENCIMENTOSDIASANTES'");
        ResultSet rs;
        
        sl.execute();
        
        try {
            rs = sl.getResult();
            rs.first();
            s = rs.getString("valor_parametro");
            
            r = Integer.valueOf(s);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return r;
        
    }
    
    
    /**
     * Método utilizado para obter a quantidade de dias antes do Lembrete de Aniversários.
     * 
     * @return número de dias antes para lembrar o usuário.
     */
    public static int getQtdDiasLembreteAniversario() {
        
        int r = 1;
        String s = "";
        Select sl = new Select("select valor_parametro from parametro "
                + "where nome_parametro = 'LEMBRETEANIVERSARIODIASANTES'");
        ResultSet rs;
        
        sl.execute();
        
        try {
            rs = sl.getResult();
            rs.first();
            s = rs.getString("valor_parametro");
            
            r = Integer.valueOf(s);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return r;
        
    }
    
    /**
     * Método utilizado para alterar o status de ativação do Lembrete de vencimento.
     * 
     * @param ativado define o valor que será cadastrado no banco de dados, após feitas as devidas conversões
     * @return valor boolean indicando o sucesso ({@code true}) ou não ({@code false}) da operação
     */
    public static boolean setLembreteVencimentosAtivado(boolean ativado) {
        Update u = new Update("PARAMETRO");
        int atualizacao = (ativado) ? 1 : 0;
        u.setCondition("nome_parametro = 'LEMBRETEVENCIMENTOSATIVADO'");
        u.addField("valor_parametro", atualizacao, "string");
        return u.execute();
    }

    /**
     * Método utilizado para alterar o status de ativação do Lembrete de aniversário.
     * 
     * @param ativado define o valor que será cadastrado no banco de dados, após feitas as devidas conversões
     * @return valor boolean indicando o sucesso ({@code true}) ou não ({@code false}) da operação
     */
    public static boolean setLembreteAniversarioAtivado(boolean ativado) {
        Update u = new Update("PARAMETRO");
        int atualizacao = (ativado) ? 1 : 0;
        u.setCondition("nome_parametro = 'LEMBRETEANIVERSARIOATIVADO'");
        u.addField("valor_parametro", atualizacao, "string");
        return u.execute();
    }
    
    /**
     * Método utilizado para alterar os dias antes para o Lembrete de vencimento.
     * 
     * @param dias define o valor que será cadastrado no banco de dados, após feitas as devidas conversões
     * @return valor boolean indicando o sucesso ({@code true}) ou não ({@code false}) da operação
     */
    public static boolean setQtdDiasLembreteVencimento(int dias) {
        Update u = new Update("PARAMETRO");
        u.setCondition("nome_parametro = 'LEMBRETEVENCIMENTOSDIASANTES'");
        u.addField("valor_parametro", dias, "string");
        return u.execute();
    }

    /**
     * Método utilizado para alterar os dias antes para o Lembrete de aniversário.
     * 
     * @param dias define o valor que será cadastrado no banco de dados, após feitas as devidas conversões
     * @return valor boolean indicando o sucesso ({@code true}) ou não ({@code false}) da operação
     */
    public static boolean setQtdDiasLembreteAniversario(int dias) {
        Update u = new Update("PARAMETRO");
        u.setCondition("nome_parametro = 'LEMBRETEANIVERSARIODIASANTES'");
        u.addField("valor_parametro", dias, "string");
        return u.execute();
    }
    
}
