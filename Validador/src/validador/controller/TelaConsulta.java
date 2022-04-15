
package validador.controller;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import validador.model.Chave;
import validador.view.TabelaConsulta;

/**
 *
 * @author lucas
 */
public class TelaConsulta {
    
    public void onClickExcluir(TabelaConsulta tabela) {
        
        String chaveSelecionada = tabela.obterValorSelecionado(0);
        
        if (chaveSelecionada.equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione uma chave, por favor.",
                        "Selecionar Chave", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //Showing confirmation message
            Object[] options = {"Sim, excluir",
                                "Não, cancelar"};
            int n = JOptionPane.showOptionDialog(null,
                "Você tem certeza que deseja excluir a chave selecionada?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

            //deleting if agreed
            if (n == JOptionPane.YES_OPTION) {
                //Executando a remoção
                boolean sucesso = Chave.excluir(chaveSelecionada);
                
                if (!sucesso) {
                    JOptionPane.showMessageDialog(null, "Houve um erro na exclusão. "
                            + "Por favor, tente novamente mais tarde.",
                            "Erro na Remoção", JOptionPane.ERROR_MESSAGE);
                } else {
                    tabela.atualizar();
                }
                
            }
        }
        
    }
    
    public void onClickRemoverPc(TabelaConsulta tabela) {
        
        String chaveSelecionada = tabela.obterValorSelecionado(0);
        
        if (chaveSelecionada.equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione uma chave, por favor.",
                        "Selecionar Chave", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //Showing confirmation message
            Object[] options = {"Sim, remover",
                                "Não, cancelar"};
            int n = JOptionPane.showOptionDialog(null,
                "Você tem certeza que deseja remover o computador registrado?",
                "Confirmar remoção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);

            //deleting if agreed
            if (n == JOptionPane.YES_OPTION) {
                //Executando a remoção
                boolean sucesso = Chave.removerPc(chaveSelecionada);
                
                if (!sucesso) {
                    JOptionPane.showMessageDialog(null, "Houve um erro na remoção. "
                            + "Por favor, tente novamente mais tarde.",
                            "Erro na Remoção", JOptionPane.ERROR_MESSAGE);
                } else {
                    tabela.atualizar();
                }
                
            }
        }
        
    }
    
    public FocusAdapter onBlurTextFieldChave(TabelaConsulta tabela) {
        
        return new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String busca = ((JTextField) e.getComponent()).getText();
                if (busca.equals("")) {
                    tabela.preencherTabela();
                } else {
                    tabela.buscar(busca);
                }
            }
        };
        
    }
    
}
