package rmk.bdlocal;

import java.sql.PreparedStatement;

public class SQLQuery {
	
	private String query;
	
	public SQLQuery(String query){
		this.query = query;
	}
	
	public boolean execute() {
		
		boolean sucesso = false;
		PreparedStatement ps;
		
		try {
			ps = ConexaoBd.getConexao().prepareStatement(query);
			ps.execute();
			ConexaoBd.FecharConexao();
			sucesso = true;
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return sucesso;
	}
	
}
