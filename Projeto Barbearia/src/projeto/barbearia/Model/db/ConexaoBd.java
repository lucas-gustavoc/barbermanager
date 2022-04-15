package projeto.barbearia.Model.db;

//Need classes for the connection //
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import projeto.barbearia.Model.Parametros;

public class ConexaoBd {

	public static String status = "not connected...";
	
	public ConexaoBd() {
		
	}
	
	//magic for connection
	public static java.sql.Connection getConexao() {
		 
        Connection connection = null; //atributo do tipo Connection
 
		try {
		 
			// Carregando o JDBC Driver padr�o
			String driverName = "org.firebirdsql.jdbc.FBDriver";
			Class.forName(driverName);
 
			// Configurando a nossa conex�o com um banco de dados//
            String mydatabase =Parametros.getDataBasePath();        //nome do seu banco de dados
            String url = "jdbc:firebirdsql:embedded:" + mydatabase;
            String username = "SYSDBA";        //nome de um usu�rio de seu BD
            String password = "masterkey";      //sua senha de acesso
            connection = DriverManager.getConnection(url, username, password);
 
            //Testa sua conex�o//
            if (connection != null) {
                status = ("STATUS--->Conectado com sucesso!");
            } else {
                status = ("STATUS--->N�o foi possivel realizar conex�o");
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "<html><body>Ocorreu um erro ao tentarmos acessar o banco " + 
                    "de dados. Por favor, contate o suporte informando esta mensagem.<br><br>" +
                    "DETALHES: " + ConexaoBd.statusConection() + "</body></html>", 
                    "Erro no Banco de Dados", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
 
            return connection;
 
        } catch (ClassNotFoundException e) {  //Driver n�o encontrado
            System.out.println("O driver expecificado nao foi encontrado.");
            status = "O driver expecificado nao foi encontrado.";
            javax.swing.JOptionPane.showMessageDialog(null, 
                    "<html><body>Ocorreu um erro ao tentarmos acessar o banco " + 
                    "de dados. Por favor, contate o suporte informando esta mensagem.<br><br>" +
                    "DETALHES: " + ConexaoBd.statusConection() + "</body></html>", 
                    "Erro no Banco de Dados", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return null;
        } catch (SQLException e) {
        	//N�o conseguindo se conectar ao banco
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            status = "Nao foi possivel conectar ao Banco de Dados.";
            javax.swing.JOptionPane.showMessageDialog(null, 
                    "<html><body>Ocorreu um erro ao tentarmos acessar o banco " + 
                    "de dados. Por favor, contate o suporte informando esta mensagem.<br><br>" +
                    "DETALHES: " + ConexaoBd.statusConection() + "</body></html>", 
                    "Erro no Banco de Dados", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return null;
        } catch (Exception e) {
            status = "Erro ao acessar banco de dados. " + e.getMessage();
            javax.swing.JOptionPane.showMessageDialog(null, 
                    "<html><body>Ocorreu um erro ao tentarmos acessar o banco " + 
                    "de dados. Por favor, contate o suporte informando esta mensagem.<br><br>" +
                    "DETALHES: " + ConexaoBd.statusConection() + "</body></html>", 
                    "Erro no Banco de Dados", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }
	
	//Returning the connection status//
    public static String statusConection() {
        return status;
    }
    
  //Here we can close the connection//
    public static boolean FecharConexao() {
        try {
            ConexaoBd.getConexao().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
	
  //Restarting the connection//
    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return ConexaoBd.getConexao();
    }
	
}
