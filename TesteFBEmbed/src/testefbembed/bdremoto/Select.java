package testefbembed.bdremoto;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Select {
	
	private String statement;
	private ResultSet rs = null;

	public Select(String statement) {
		this.statement = statement;
	}
	
	public boolean execute() {
		
		PreparedStatement pst;
		boolean sucesso = false;
		
		try {
			pst = ConexaoBd.getConexao().prepareStatement(statement, 
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pst.executeQuery();
			ConexaoBd.FecharConexao();
			sucesso = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return sucesso;
		
	}

	public ResultSet getResult() {
		return rs;
	}
	
}
