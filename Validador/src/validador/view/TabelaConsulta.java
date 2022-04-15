
package validador.view;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import interfaces.Atualizavel;
import validador.model.Chave;

/**
 *
 * @author lucas
 */
public class TabelaConsulta extends JTable implements Atualizavel {
    
    //Valor utilizado para indicar o preenchimento total da tabela
    private static final String PREENCHIMENTO_TOTAL = "total";
    
    //Valor utilizado para indicar o preenchimento parcial da tabela (por meio de busca)
    private static final String PREENCHIMENTO_COM_BUSCA = "busca";
    
    //Valor que armazena o tipo de preenchimento efetuado
    private String tipoPreenchimento = "";
    
    //Valor que armazena a busca efetuada, caso exista
    private String buscaEfetuada = "";
    
    public TabelaConsulta() {
        super();
    }
    
    public void preencherTabela() {
        String[][] d = Chave.buscarTodasAsChaves();
        tipoPreenchimento = PREENCHIMENTO_TOTAL;
        buscaEfetuada = "";
        preencherTabelaInterno(d);
    }
    
    public void buscar(String s) {
        String[][] d = Chave.buscarChaveEspecifica(s);
        tipoPreenchimento = PREENCHIMENTO_COM_BUSCA;
        buscaEfetuada = s;
        preencherTabelaInterno(d);
    }
    
    private void preencherTabelaInterno(String[][] dados2) {
        String[] colunas2 = new String[]{"Chave", "Cod. PC"};
        
        DefaultTableModel model = new DefaultTableModel(dados2, colunas2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        this.setModel(model);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setCellSelectionEnabled(false);
        this.setRowSelectionAllowed(true);
        
        //Centralizando e mudando tamanho da coluna ID
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        this.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        this.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        this.getColumnModel().getColumn(0).setMinWidth(250);
    }
    
    /**
     * Método utilizado para retornar o valor da linha selecionada da tabela. 
     * Caso não existam linhas selecionadas, o retorno é vazio.
     * @param coluna número da coluna da qual o valor será retornado, considerando
     * que a primeira coluna é referida pelo número 0.
     * @return o valor da linha selecionada na coluna solicitada ou vazio para 
     * quando não há linha selecionada.
     */
    public String obterValorSelecionado(int coluna) {
        String v;
        if (getSelectedRow() > -1) {
            v = (String) getValueAt(getSelectedRow(), coluna);
        } else {
            v = "";
        }
        return v;
    }

    @Override
    public void atualizar() {
        if (tipoPreenchimento.equals(PREENCHIMENTO_TOTAL)) {
            preencherTabela();
        } else {
            buscar(buscaEfetuada);
        }
    }
    
}
