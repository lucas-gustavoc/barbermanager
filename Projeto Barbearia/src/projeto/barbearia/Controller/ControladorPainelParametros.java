
package projeto.barbearia.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import projeto.barbearia.Model.Natureza;
import projeto.barbearia.Model.Parametros;
import projeto.barbearia.View.CacheAtualizacao;
import projeto.barbearia.View.CadastroNatureza;
import projeto.barbearia.View.TabelaNatureza;

/**
 *
 * @author lucas
 */
public class ControladorPainelParametros {
    
    /**
     * 
     * ******************************************
     * Controlando combos de hora do Expediente
     * ******************************************
     * 
     */
    
    /**
     * Indica que a hora inicial é que gerou o evento de validação e cadastro.
     * Esta variável tem utilidade para que o método de cadastro saiba quem 
     * foi alterado, modificando assim a maneira como ele manipulará a situação.
     */
    public static final String HORA_INICIAL_ALTERADA = "ini";
    
    /**
     * Indica que a hora final é que gerou o evento de validação e cadastro.
     * Esta variável tem utilidade para que o método de cadastro saiba quem 
     * foi alterado, modificando assim a maneira como ele manipulará a situação.
     */
    public  static final String HORA_FINAL_ALTERADA = "fin";
    
    /**
     * Executa a validação do intervalo e o cadastro, em caso de validação estar ok.
     * 
     * @param cbIni JComboBox contendo a hora do início do expediente.
     * @param cbFin JComboBox contendo a hora do final do expediente.
     * @param hrAtualIni hora atual, a ser selecionada no JComboBox de início do expediente
     * caso o intervalo pedido não esteja correto.
     * @param hrAtualFin hora atual, a ser selecionada no JComboBox de final do expediente
     * caso o intervalo pedido não esteja correto.
     * @param quemFoiAlterado indica qual dos dois JComboBox gerou o evento de alteração; isso
     * modifica a maneira como este método irá lidar com a situação. As possibilidade são 
     * {@code HORA_INICIAL_ALTERADA} ou {@code HORA_FINAL_ALTERADA}
     * 
     */
    public void alterarIntervalorExpediente(JComboBox cbIni, JComboBox cbFin, 
            String hrAtualIni, String hrAtualFin, String quemFoiAlterado) {
        
        boolean validacao = validaIntervaloExpediente(
                cbIni.getSelectedItem().toString(), 
                cbFin.getSelectedItem().toString());
        
        if (validacao) {
            //Cadastra a alteração e atualiza o combo
            
            boolean sucesso;
            
            if (quemFoiAlterado.equals("ini")) {
                sucesso = Parametros.setInicioExpediente(cbIni.getSelectedItem().toString());
            } else {
                sucesso = Parametros.setFimExpediente(cbFin.getSelectedItem().toString());
            }
            
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Parametro atualizado com sucesso!", 
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar o parâmetro. "
                        + "Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            //Não cadastra, alerta o erro e defini a seleção dos JComboBox conforme a antiga
            JOptionPane.showMessageDialog(null, "O horário final do expediente precisa ser "
                    + "maior que o horário inicial.", "Intervalor Incorreto", JOptionPane.ERROR_MESSAGE);
            
            if (quemFoiAlterado.equals("ini")) {
                cbIni.setSelectedItem(hrAtualIni);
                cbFin.setSelectedItem(hrAtualFin);
            } else {
                cbFin.setSelectedItem(hrAtualFin);
                cbIni.setSelectedItem(hrAtualIni);
            }
        }
        
    }
    
    /**
     * Utilizada em Conjunto com o método 'alterarIntervalorExpediente'. Verifica se
     * os horário de início e final de expediente estão ok no que diz respeito ao intervalo.
     * 
     * @return <code>true</code> em caso de validação ok e <code>false</code>
     * em caso de erro.
     */
    private boolean validaIntervaloExpediente(String hrIni, String hrFin) {
        boolean r = true;
        
        int hora;
        int minuto;
        int horaFim;
        int minutoFim;
        int colon;
        
        //Definindo valores da hora inicial
        colon = hrIni.indexOf(":");
        hora = Integer.valueOf(hrIni.substring(0, colon));
        minuto = Integer.valueOf(hrIni.substring(colon + 1));
        
        //Definindo valores da hora final
        colon = hrFin.indexOf(":");
        horaFim = Integer.valueOf(hrFin.substring(0, colon));
        minutoFim = Integer.valueOf(hrFin.substring(colon + 1));
        
        if (horaFim < hora) {
            //Caso a hora de final seja menor que a de início, gerar erro de validação
            r = false;
        } else {
            if (horaFim ==  hora) {
                //Caso a hora de final seja igual à hora de início, vamos ver se os minutos resolvem
                if (minutoFim <= minuto) {
                    //Entra aqui se o horário final é, definitivamente, menor ou igual ao horário inicial
                    r = false;
                }
            }
        }
        
        return r;
    }
    
    
    
    
    
    
    /**
     * 
     * ************************************************
     * Controlando ativação e cadastros dos lembretes
     * ************************************************
     * 
     */
    
    //Botão usado na GUI para ativar e desativar o lembrete de aniversário
    private JButton btnAtivLmbNiver;
    
    //Botão usado na GUI para ativar e desativar o lembrete de vencimentos
    private JButton btnAtivLmbVcto;
    
    //Label usada na GUI para informar se o lembrete de aniversário está ou não ativado
    private JLabel lblAtivLmbNiver;
    
    //Label usada na GUI para informar se o lembrete de vencimentos está ou não ativado
    private JLabel lblAtivLmbVcto;
    
    /**
     * Variável interna que recebe do banco de dados o 
     * valor referente à ativação do lembrete de aniversário
     */
    private boolean ativadoLembreteNiver;
    
    /**
     * Variável interna que recebe do banco de dados o 
     * valor referente à ativação do lembrete de vencimentos
     */
    private boolean ativadoLembreteVcto;
    
    /**
     * Método utilizado preparar os elementos da GUI utilizados para gestão do lembrete de aniversário.
     * 
     * @param buscarAtivacao informa ao método se ele deve buscar a informação de 
     * ativação no banco de dados ou se ele pode usar o valor da variável interna
     */
    public void carregarLembreteAniversario(boolean buscarAtivacao) {
        if (buscarAtivacao) ativadoLembreteNiver = Parametros.getLembreteAniversarioAtivado();
        
        if (ativadoLembreteNiver) {
            btnAtivLmbNiver = new JButton("Desativar");
            lblAtivLmbNiver = new JLabel("(ativado neste momento)");
        } else {
            btnAtivLmbNiver = new JButton("Ativar");
            lblAtivLmbNiver = new JLabel("(desativado neste momento)");
        }
    }
    
    /**
     * Método utilizado preparar os elementos da GUI utilizados para gestão do lembrete de vencimento.
     * 
     * @param buscarAtivacao informa ao método se ele deve buscar a informação de 
     * ativação no banco de dados ou se ele pode usar o valor da variável interna
     */
    public void carregarLembreteVcto(boolean buscarAtivacao) {
        if (buscarAtivacao) ativadoLembreteVcto = Parametros.getLembreteVencimentosAtivado();
        
        if (ativadoLembreteVcto) {
            btnAtivLmbVcto = new JButton("Desativar");
            lblAtivLmbVcto = new JLabel("(ativado neste momento)");
        } else {
            btnAtivLmbVcto = new JButton("Ativar");
            lblAtivLmbVcto = new JLabel("(desativado neste momento)");
        }
    }
    
    /**
     * Ação executada no click do botão de lembrete de aniversário.
     * 
     * @param btn botão na GUI referente ao processo de ativação do lembrete de aniversário
     * @param lbl label na GUI referente ao processo de ativação do lembrete de aniversário
     */
    public void onClickAtivLmbNiver(JButton btn, JLabel lbl) {
        //Executando alteração
        ativadoLembreteNiver = !ativadoLembreteNiver;
        boolean sucesso = Parametros.setLembreteAniversarioAtivado(ativadoLembreteNiver);
        carregarLembreteAniversario(false);
        btn.setText(btnAtivLmbNiver.getText());
        lbl.setText(lblAtivLmbNiver.getText());
        
        //Informando usuário do resultado
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Parametro atualizado com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar o parâmetro. "
                    + "Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Ação executada no click do botão de lembrete de vencimento.
     * 
     * @param btn botão na GUI referente ao processo de ativação do lembrete de vencimento
     * @param lbl label na GUI referente ao processo de ativação do lembrete de vencimento
     */
    public void onClickAtivLmbVcto(JButton btn, JLabel lbl) {
        //Executando alteração
        ativadoLembreteVcto = !ativadoLembreteVcto;
        boolean sucesso = Parametros.setLembreteVencimentosAtivado(ativadoLembreteVcto);
        carregarLembreteVcto(false);
        btn.setText(btnAtivLmbVcto.getText());
        lbl.setText(lblAtivLmbVcto.getText());
        
        //Informando usuário do resultado
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Parametro atualizado com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar o parâmetro. "
                    + "Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JButton getBtnAtivLmbNiver() {
        return btnAtivLmbNiver;
    }

    public JButton getBtnAtivLmbVcto() {
        return btnAtivLmbVcto;
    }

    public JLabel getLblAtivLmbNiver() {
        return lblAtivLmbNiver;
    }

    public JLabel getLblAtivLmbVcto() {
        return lblAtivLmbVcto;
    }
    
    
    /**
     * Valor utilizado na operação de adicionar um dia aos avisos
     */
    public static final int ADICIONAR_DIA = 1;
    
    /**
     * Valor utilizado na operação de subtrair um dia aos avisos
     */
    public static final int SUBTRAIR_DIA = -1;
    
    /**
     * Método utilizado para gerenciar, na GUI (não no banco de dados), 
     * as alterações nos dias antes dos avisos em geral.
     * 
     * @param txf TextField que contém os dias para o aviso
     * @param op valor da operação a ser realizada na quantidade de dias, em geral, 
     * utilizamos {@code ADICIONAR_DIA} ou {@code SUBTRAIR_DIA}
     * @param btnSalvar botão que será utilizado posteriormente para efetivar a alteração de dias
     */
    public void alterarDiasAvisos(JTextField txf, int op, JButton btnSalvar) {
        //Descobrindo o valor atual e já tratando possível erro
        int vlAtual = 1;
        try {
            vlAtual = Integer.valueOf(txf.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        //Realizando operação
        vlAtual += op;
        
        //Validando operação
        if (vlAtual < 0) {
            JOptionPane.showMessageDialog(null, "A quantidade de dias precisa ser 0 ou mais.", 
                    "Quantidade Incorreta", JOptionPane.WARNING_MESSAGE);
        } else if (vlAtual > 10) {
            JOptionPane.showMessageDialog(null, "A quantidade de dias precisa ser no máximo 10.", 
                    "Quantidade Incorreta", JOptionPane.WARNING_MESSAGE);
        } else {
            btnSalvar.setVisible(true);
            txf.setText(String.valueOf(vlAtual));
        }
    }
    
    //Definição do tipo de aviso, sendo este o de aniversário
    public static final int AVISO_ANIVERSARIO = 1;
    
    //Definição do tipo de aviso, sendo este o de vencimento
    public static final int AVISO_VENCIMENTO = 2;
    
    /**
     * Método utilizado para efetivar a alteração de dias antes dos avisos no banco de dados.
     * 
     * @param txf TextField que contém os dias para o aviso
     * @param btnSalvar botão que foi usado para efetivar a alteração de dias; 
     * ele deve ser ocultado após a operação
     * @param tipoAviso define o tipo de aviso que será alterado. Como o processo de alteração 
     * é praticamente o mesmo para qualquer aviso, com exceção de apenas um método, utilizamos 
     * este indicador de tipo para definir qual aviso alterar; em geral, utilizamos ou {@code AVISO_ANIVERSARIO}
     * ou {@code AVISO_VENCIMENTO}
     */
    public void onClickSalvarDiasAntesAvisos(JTextField txf, JButton btnSalvar, int tipoAviso) {
        //Descobrindo o valor atual e já tratando possível erro
        boolean validacao = false;
        boolean sucesso = false;
        int vlAtual = 0;
        try {
            vlAtual = Integer.valueOf(txf.getText());
            validacao = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        //Cadastrando
        if (validacao) {
            switch (tipoAviso) {
                case 1:
                    sucesso = Parametros.setQtdDiasLembreteAniversario(vlAtual);
                    break;
                case 2:
                    sucesso = Parametros.setQtdDiasLembreteVencimento(vlAtual);
                    break;
            }
        }
        
        //Informando Resultados
        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Parametro atualizado com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            btnSalvar.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar o parâmetro. "
                    + "Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
    
    /**
     * 
     * ***********************************************
     * Controlando cadastros das Naturezas Financeiras
     * ***********************************************
     * 
     * 
     */
    
    
     /**
      * Método que manipula o clique do botão adicionar Natureza na GUI.
      * 
      * @param tabela tabela a ser atualizada após as alterações
      */
    public void onClickAdicionarNatureza(TabelaNatureza tabela) {
        CacheAtualizacao.adicionarItemAoCache(tabela, "CadastroNatureza");
        new CadastroNatureza();
    }
    
    /**
      * Método que manipula o clique do botão remover Natureza na GUI.
      * 
      * @param tabela tabela a ser atualizada após as alterações
      */
    public void onClickRemoverNatureza(TabelaNatureza tabela) {
        int id = getIdNaTabelaNatureza(tabela);
        if (id > -1) {
            Natureza.remover(id);
            tabela.preencherTabela();
        }
    }
    
    /**
      * Método que manipula o clique do botão editar Natureza na GUI.
      * 
      * @param tabela tabela a ser atualizada após as alterações
      */
    public void onClickEditarNatureza(TabelaNatureza tabela) {
        int id = getIdNaTabelaNatureza(tabela);
        if (id > -1) {
            CacheAtualizacao.adicionarItemAoCache(tabela, "CadastroNatureza");
            new CadastroNatureza(id);
        }
    }
    
    /**
     * Método usado em conjunto com os métodos {@code onClickEditarNatureza} e {@code onClickRemoverNatureza}.
     * Este método já nofica o cliente caso ele não tenha selecionado item algum.
     * 
     * @param tabela tabela que será considerada para procurar o id
     * @return int com o id do item selecionado na tabela ou -1, caso nenhuma linha esteja selecionada
     */
    private int getIdNaTabelaNatureza(TabelaNatureza tabela) {
        String sid;
        int id;
        
        if (tabela.getSelectedRow() > -1) {
            sid = (String) tabela.getValueAt(tabela.getSelectedRow(), 0);
            id = Integer.valueOf(sid);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecione uma natureza para EDITAR.",
                        "Selecione uma Natureza", JOptionPane.WARNING_MESSAGE);
            id = -1;
        }
        
        return id;
    }
    
    /**
     * Método que manipula o duplo clique em registros da tabela.
     * 
     * @param tabela tabela a ser atualizada após as alterações
     * @return MouseAdapter inserido como MouseListener da tabela
     */
    public MouseAdapter onDoubleClickTabela(TabelaNatureza tabela) {
        
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2){
                    onClickEditarNatureza(tabela);
                }
            }
        };
        
    }
    
    
    
    
    
    /**
     * 
     * ***********************************************
     * Controlando Remoção da Chave de Ativação
     * ***********************************************
     * 
     * 
     */
    
    /**
     * Método utilizado para remover a chave de ativação atual.
     */
    public void removerChaveDeAtivacao() {
        //Showing confirmation message
        Object[] options = {"Sim, remover",
                            "Não, cancelar"};
        int n = JOptionPane.showOptionDialog(null,
            "Você tem certeza que deseja remover a chave de ativação atual?",
            "Confirmar remoção",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            options,
            options[0]);

        //deleting if agreed
        if (n == JOptionPane.YES_OPTION) {
            try {
                Process ps=Runtime.getRuntime().exec(new String[]{"java","-jar","rmk.jar"});
                ps.waitFor();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        
        
        
    }
    
    
}
