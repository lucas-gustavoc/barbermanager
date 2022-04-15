package bmkey.bdlocal;

import java.util.ArrayList;
import java.sql.PreparedStatement;

public class Insert {
	
	private String nomeTabela;
	private ArrayList<String> campos = new ArrayList<String>();
	private ArrayList<Object> camposItens = new ArrayList<Object>();
	private ArrayList<String> camposTipos = new ArrayList<String>();
	
	public Insert(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}
	
	public void addField(String nomeCampo, Object item, String tipo){
		campos.add(nomeCampo);
		camposItens.add(item);
		camposTipos.add(tipo.toLowerCase());
	}
	
	public boolean execute() {
		
		boolean sucesso = false;
		String stt = "";
		String fields = "";
		String values = "";
		PreparedStatement ps;
		
		try {
			
			for(int i = 0; i < campos.size(); i++) {
				if (i == 0) {
					fields = campos.get(i);
					values = "?";
				} else {
					fields = fields + ", " + campos.get(i);
					values = values + ", ?";
				}
			}
			
			stt = "insert into " + nomeTabela + 
					" (" + fields + ") values (" + values + ")";
			
			ps = ConexaoBd.getConexao().prepareStatement(stt);
			
			for(int i = 0; i < camposItens.size(); i++) {
				switch (camposTipos.get(i)) {
				case "string":
					ps.setString(i + 1, camposItens.get(i).toString());
					break;
				case "int":
					ps.setInt(i + 1, 
							Integer.parseInt(camposItens.get(i).toString()));
					break;
				case "date":
					java.sql.Date newDate = (java.sql.Date) camposItens.get(i);
					ps.setDate(i + 1, newDate);
					break;
				case "boolean":
					ps.setBoolean(i + 1, (Boolean) camposItens.get(i));
					break;
                                case "float":
                                        ps.setFloat(i + 1, (float) camposItens.get(i));
                                        break;
                                case "time":
                                        ps.setTime(i + 1, (java.sql.Time) camposItens.get(i));
                                    break;
				}
			}
			
			ps.execute();
			
			ConexaoBd.FecharConexao();
			
			sucesso = true;
			
		} catch (Exception ex) {
			//System.out.println(ex.getMessage());
		}
		
		return sucesso;
		
	}
	
}
