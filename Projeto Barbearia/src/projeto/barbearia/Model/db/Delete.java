package projeto.barbearia.Model.db;

import javax.swing.JOptionPane;

public class Delete {
	
	private String nomeTabela;
	
	public Delete(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}
	
	//in this method, the user'll be prompted about the exclusion
	public boolean deleteOnCondition(String condition) {
		
		boolean retorno = false;
		
		//Showing confirmation message
		Object[] options = {"Sim, excluir",
		                    "Não, cancelar"};
		int n = JOptionPane.showOptionDialog(null,
		    "Você tem certeza que deseja excluir este registro?",
		    "Confirmar exclusão",
		    JOptionPane.YES_NO_OPTION,
		    JOptionPane.WARNING_MESSAGE,
		    null,
		    options,
		    options[0]);
		
		//deleting if agreed
		if (n == JOptionPane.YES_OPTION) {
			SQLQuery query = new SQLQuery
					("delete from " + nomeTabela + " where " + condition);
			retorno = query.execute();
		}
		
		return retorno;
	}
	
	//in this method, the programmer defines if the user needs to be prompted
	//the 'force' argument is used to this aim
	public boolean deleteOnCondition(String condition, boolean force) {
		
		boolean retorno = false;
		
		if (force) {
			SQLQuery query = new SQLQuery
					("delete from " + nomeTabela + " where " + condition);
			retorno = query.execute();
		} else {
			retorno = deleteOnCondition(condition);
		}
		
		return retorno;
	}
	
}
