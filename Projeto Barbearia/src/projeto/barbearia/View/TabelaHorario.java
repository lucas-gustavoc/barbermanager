
package projeto.barbearia.View;

import javax.swing.JTable;
import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import projeto.barbearia.Model.Horario;
import projeto.barbearia.Model.Profissional;
import projeto.barbearia.Model.ResultSetToolBox;

/**
 *
 * @author lucas
 */
public class TabelaHorario extends JTable implements Atualizavel {
    
    private Date data;
    
    public TabelaHorario() {
        super();
    }
    
    public void preencherTabela(Date d) {
        
        ResultSet rs = Horario.buscarParaTabela(d);
        ResultSet rsProfissionais = Profissional.buscar("profissionalid, nome_profissional", 
                "ativo_profissional = 1");
        
        //Armazenando data para possível uso posterior
        data = d;
        
        /**
         * Este combo é usado somente para obtenção das faixas de horário.
         * Neste código, ele não será exibido na tela.
        */
        ComboHora dtSource = new ComboHora();
        
        int c1 = dtSource.getItemCount();
        int c2 = ResultSetToolBox.countRows(rs);
        int c3 = ResultSetToolBox.countRows(rsProfissionais);
        
        //c1: Quantidade de faixas de horário. ex.: 12:30, 13:00, 13:30 e etc.
        //c2: Quantidade de horários marcados na data em questão.
        //c3: Quantidade de profissionais ativos.
        String[][] dados = new String[c1][c3 + 1];
        String[] headers;
        headers = new String[c3 + 1];
        
        //Definindo cabeçalho
        try {
            headers[0] = "Horário"; //primeira coluna contém a hora
            rsProfissionais.beforeFirst();
            int i = 1;
            while (rsProfissionais.next()) {
                headers[i] = rsProfissionais.getString("profissionalid") 
                        + " - " + rsProfissionais.getString("nome_profissional");
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        //Criando uma linha para cada faixa de horário
        for (int i = 0; i < c1; i++) {
            dados[i][0] = dtSource.getItemAt(i).toString(); //primeira coluna contém a hora
            
            //Para cada profissional, verificar se bate o horário
            for (int j = 1; j < c3 + 1; j++) {
                try {
                    rs.beforeFirst();
                    
                    while (rs.next()) {
                        if (headers[j].equals(rs.getString("profissionalid") 
                                + " - " + rs.getString("nome_profissional")) 
                                && (dtSource.getItemAt(i).toString() + ":00")
                                        .equals(rs.getString("hora_horario"))) {
                            dados[i][j] = rs.getString("nome_cliente") + " (" + rs.getString("nome_servico") + ")";
                        }
                    }
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        }
        
        DefaultTableModel model = new DefaultTableModel(dados, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        this.setModel(model);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setCellSelectionEnabled(true);
        this.setRowSelectionAllowed(false);
        this.setColumnSelectionAllowed(false);
        
        //Definindo tamanhos das colunas
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.getColumnModel().getColumn(0).setPreferredWidth(85);
        
        //Definindo tamanho das linhas
        this.setRowHeight(30);
        
        //Centralizando
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        this.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        
        //fazendo o mesmo nas demais colunas
        for (int i = 1; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setPreferredWidth(210);
            this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
    }
    
    @Override
    public void atualizar() {
        preencherTabela(data);
    }
    
}
