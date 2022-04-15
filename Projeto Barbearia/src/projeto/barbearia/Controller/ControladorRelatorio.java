
package projeto.barbearia.Controller;

import java.awt.Color;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import projeto.barbearia.Model.FormatadorParaSQLData;
import projeto.barbearia.Model.RelatorioBalanco;
import projeto.barbearia.Model.RelatorioComissionamento;
import projeto.barbearia.Model.RelatorioProduto;
import projeto.barbearia.Model.RelatorioServico;
import projeto.barbearia.Model.Tipos.ComboProdutoTipo;
import projeto.barbearia.Model.Tipos.ComboProfissionalTipo;
import projeto.barbearia.Model.Tipos.ComboServicoTipo;
import projeto.barbearia.View.ComboProduto;
import projeto.barbearia.View.ComboProfissional;
import projeto.barbearia.View.ComboServico;
import projeto.barbearia.View.Tabela;

/**
 *
 * @author lucas
 */
public class ControladorRelatorio {
    
    /**
     * Definição do tipo de relatório COMISSIONAMENTO DE PROFISSIONAIS
     */
    public static final int RELATORIO_COMISSIONAMENTO = 0;
    
    /**
     * Definição do tipo de relatório VENDAS DE PRODUTOS
     */
    public static final int RELATORIO_PRODUTO = 1;
    
    /**
     * Definição do tipo de relatório VENDAS DE SERVIÇOS
     */
    public static final int RELATORIO_SERVICO = 2;
    
    /**
     * Definição do tipo de relatório BALANÇO FINANCEIRO
     */
    public static final int RELATORIO_BALANCO = 3;
    
    //Armazena o tipo selecionado
    private int tipo;
    
    //Aramazena o nome da tela
    private String nomeTela = "Relatórios";
    
    //Armazena os nomes dos campos
    private ArrayList<Object> listaNomes = new ArrayList<>();
    
    //Armazena os componenstes em si, que serão utilizados na GUI
    private ArrayList<Object> listaComponentes = new ArrayList<>();
    
    //Armazena os tipos dos componentes
    private ArrayList<Object> listaTipos = new ArrayList<>();
    
    //Componente a ser utilizado na GUI para mostrar a conclusão
    private JLabel lblResultados = new JLabel();
    
    //Componente a ser utilizado na GUI para mostrar os resultados do relatório
    private JScrollPane jsTabela = new JScrollPane();
    
    /**
     * Construtor.
     * 
     * @param tipo dipo do relatório, conforme constantes presentes nesta classe
     */
    public ControladorRelatorio(int tipo) {
        this.tipo = tipo;
        carregarCampos();
    }
    
    /**
     * 
     * CONTROLE DOS CAMPOS - CONSTRUÇÃO DA TELA
     * 
     * 
     */
    
    /**
     * Efetua a tradução dos ArrayLists da classe para um array de arrays de 
     * Object.
     * 
     * @return retorna um array de arrays de Object, contendo os nomes, compo-
     * nentes e tipos.
     */
    public Object[][] obterCampos() {
        Object[][] campos = new Object[listaNomes.size()][3];
        
        for (int i = 0; i < listaNomes.size(); i++) {
            campos[i][0] = listaNomes.get(i);
            campos[i][1] = listaComponentes.get(i);
            campos[i][2] = listaTipos.get(i);
        }
        
        return campos;
    }
    
    
    /**
     * Seletor de métodos baseado no tipo de relatório.
     */
    private void carregarCampos() {
        switch (tipo) {
            case 0:
                nomeTela = "Relatório de Comissionamento";
                carregarCamposComissionamento();
                break;
            case 1:
                nomeTela = "Relatório de Vendas de Produtos";
                carregarCamposProduto();
                break;
            case 2:
                nomeTela = "Relatório de Vendas de Serviços";
                carregarCamposServico();
                break;
            case 3:
                nomeTela = "Relatório de Balanço Financeiro";
                carregarCamposBalanco();
                break;
        }
    }
    
    /**
     * Gerar a data de hoje e alterar em dias, se necessário.
     * @param diasDeAcrescimo dias a se acrescentar à data de hoje. Números 
     * negativos permitem a subtração. Definir como 0 para não alterar a data de
     * hoje.
     * @return data no formato dd/mm/aaaa
     */
    private String obterDataDeHoje(int diasDeAcrescimo) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date datem = new java.util.Date();
        Calendar c = Calendar.getInstance(); 
        c.setTime(datem); 
        c.add(Calendar.DATE, diasDeAcrescimo);
        datem = c.getTime();
        return dateFormat.format(datem);
    }
    
    /**
     * Carregar campos relativos ao relatório de Comissionamento
     */
    private void carregarCamposComissionamento() {
        //campo 1: Profissional
        ComboProfissional cb = new ComboProfissional();
        cb.preencherComboPara(ComboProfissionalTipo.RELATORIO, null);
        listaNomes.add("Profissional: ");
        listaComponentes.add(cb);
        listaTipos.add("jcombobox");
        
        //campo 2: Início do Período
        listaNomes.add("Dt. Início: ");
        listaComponentes.add(new JTextField(obterDataDeHoje(-6)));
        listaTipos.add("jtextfield");
        
        //campo 3: Fim do Período
        listaNomes.add("Dt. Final: ");
        listaComponentes.add(new JTextField(obterDataDeHoje(0)));
        listaTipos.add("jtextfield");
    }
    
    /**
     * Carregar campos relativos ao relatório de Produtos
     */
    private void carregarCamposProduto() {
        //campo 1: Produto
        ComboProduto cb = new ComboProduto();
        cb.preencherComboPara(ComboProdutoTipo.RELATORIO, null);
        listaNomes.add("Produto: ");
        listaComponentes.add(cb);
        listaTipos.add("jcombobox");
        
        //campo 2: Início do Período
        listaNomes.add("Dt. Início: ");
        listaComponentes.add(new JTextField(obterDataDeHoje(-6)));
        listaTipos.add("jtextfield");
        
        //campo 3: Fim do Período
        listaNomes.add("Dt. Final: ");
        listaComponentes.add(new JTextField(obterDataDeHoje(0)));
        listaTipos.add("jtextfield");
    }
    
    /**
     * Carregar campos relativos ao relatório de Serviços
     */
    private void carregarCamposServico() {
        //campo 1: Produto
        ComboServico cb = new ComboServico();
        cb.preencherComboPara(ComboServicoTipo.RELATORIO, null);
        listaNomes.add("Serviço: ");
        listaComponentes.add(cb);
        listaTipos.add("jcombobox");
        
        //campo 2: Início do Período
        listaNomes.add("Dt. Início: ");
        listaComponentes.add(new JTextField(obterDataDeHoje(-6)));
        listaTipos.add("jtextfield");
        
        //campo 3: Fim do Período
        listaNomes.add("Dt. Final: ");
        listaComponentes.add(new JTextField(obterDataDeHoje(0)));
        listaTipos.add("jtextfield");
    }
    
    /**
     * Carregar campos relativos ao relatório de Balanço
     */
    private void carregarCamposBalanco() {
        //campo 1: Início do Período
        listaNomes.add("Dt. Início: ");
        listaComponentes.add(new JTextField(obterDataDeHoje(-6)));
        listaTipos.add("jtextfield");
        
        //campo 2: Fim do Período
        listaNomes.add("Dt. Final: ");
        listaComponentes.add(new JTextField(obterDataDeHoje(0)));
        listaTipos.add("jtextfield");
    }
    
    
    /**
     * 
     * EXECUÇÃO DO RELATÓRIO
     * 
     * 
     */
    
    
    /**
     * Handler do click do botão de "Executar Relatório". Funciona como seletor
     * de métodos baseado no tipo de relatório.
     */
    public void onClickExecutarRelatorio() {
        switch (tipo) {
            case 0:
                executarRelatorioComissionamento();
                break;
            case 1:
                executarRelatorioProduto();
                break;
            case 2:
                executarRelatorioServico();
                break;
            case 3:
                executarRelatorioBalanco();
                break;
        }
    }
    
    /**
     * Executar relatório de Comissionamento. Os campos da GUI têm seus valores
     * definidos neste método.
     */
    private void executarRelatorioComissionamento() {
        ComboProfissional cb = (ComboProfissional)listaComponentes.get(0);
        JTextField txfIni = (JTextField)listaComponentes.get(1);
        JTextField txfFim = (JTextField)listaComponentes.get(2);
        
        //Validações
        String mensagemDeErro = "";
        
        String stIni = validarData(txfIni.getText());
        if (stIni.equals("")) mensagemDeErro = "Por favor, preencha uma "
                + "DATA INICIAL no formato dd/mm/aaaa.";
        
        String stFim = validarData(txfFim.getText());
        if (stFim.equals("")) mensagemDeErro = "Por favor, preencha uma "
                + "DATA FINAL no formato dd/mm/aaaa.";
        
        int profissionalid = getIdNoCombo(cb.getSelectedItem()
                .toString(), cb.getSelectedIndex());
        if (profissionalid == 0) mensagemDeErro = "Por favor, selecione"
                + " um PROFISSIONAL.";
        
        //Executando relatório
        if (mensagemDeErro.equals("")) {
            //Entra aqui em caso de validação ok
            RelatorioComissionamento r = 
                    new RelatorioComissionamento(Date.valueOf(stIni), 
                            Date.valueOf(stFim), profissionalid);
            r.criar();
            
            Tabela tabela = r.getTabela();
            jsTabela.setViewportView(tabela);
            tabela.getParent().setBackground(Color.white);
            lblResultados.setText(r.getTextoRetorno());
        } else {
            //Entra aqui em caso de erro na validação
            JOptionPane.showMessageDialog(null, mensagemDeErro, "Erro nos Dados", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    /**
     * Executar relatório de Produto. Os campos da GUI têm seus valores
     * definidos neste método.
     */
    private void executarRelatorioProduto() {
        ComboProduto cb = (ComboProduto)listaComponentes.get(0);
        JTextField txfIni = (JTextField)listaComponentes.get(1);
        JTextField txfFim = (JTextField)listaComponentes.get(2);
        
        //Validações
        String mensagemDeErro = "";
        
        String stIni = validarData(txfIni.getText());
        if (stIni.equals("")) mensagemDeErro = "Por favor, preencha uma "
                + "DATA INICIAL no formato dd/mm/aaaa.";
        
        String stFim = validarData(txfFim.getText());
        if (stFim.equals("")) mensagemDeErro = "Por favor, preencha uma "
                + "DATA FINAL no formato dd/mm/aaaa.";
        
        int produtoid = getIdNoCombo(cb.getSelectedItem()
                .toString(), cb.getSelectedIndex());
        if (produtoid == 0) mensagemDeErro = "Por favor, selecione"
                + " um PRODUTO.";
        
        //Executando relatório
        if (mensagemDeErro.equals("")) {
            //Entra aqui em caso de validação ok
            RelatorioProduto r = 
                    new RelatorioProduto(Date.valueOf(stIni), 
                            Date.valueOf(stFim), produtoid);
            r.criar();
            
            Tabela tabela = r.getTabela();
            jsTabela.setViewportView(tabela);
            tabela.getParent().setBackground(Color.white);
            lblResultados.setText(r.getTextoRetorno());
        } else {
            //Entra aqui em caso de erro na validação
            JOptionPane.showMessageDialog(null, mensagemDeErro, "Erro nos Dados", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Executar relatório de Serviço. Os campos da GUI têm seus valores
     * definidos neste método.
     */
    private void executarRelatorioServico() {
        ComboServico cb = (ComboServico)listaComponentes.get(0);
        JTextField txfIni = (JTextField)listaComponentes.get(1);
        JTextField txfFim = (JTextField)listaComponentes.get(2);
        
        //Validações
        String mensagemDeErro = "";
        
        String stIni = validarData(txfIni.getText());
        if (stIni.equals("")) mensagemDeErro = "Por favor, preencha uma "
                + "DATA INICIAL no formato dd/mm/aaaa.";
        
        String stFim = validarData(txfFim.getText());
        if (stFim.equals("")) mensagemDeErro = "Por favor, preencha uma "
                + "DATA FINAL no formato dd/mm/aaaa.";
        
        int servicoid = getIdNoCombo(cb.getSelectedItem()
                .toString(), cb.getSelectedIndex());
        if (servicoid == 0) mensagemDeErro = "Por favor, selecione"
                + " um SERVIÇO.";
        
        //Executando relatório
        if (mensagemDeErro.equals("")) {
            //Entra aqui em caso de validação ok
            RelatorioServico r = 
                    new RelatorioServico(Date.valueOf(stIni), 
                            Date.valueOf(stFim), servicoid);
            r.criar();
            
            Tabela tabela = r.getTabela();
            jsTabela.setViewportView(tabela);
            tabela.getParent().setBackground(Color.white);
            lblResultados.setText(r.getTextoRetorno());
        } else {
            //Entra aqui em caso de erro na validação
            JOptionPane.showMessageDialog(null, mensagemDeErro, "Erro nos Dados", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Executar relatório de Balanço. Os campos da GUI têm seus valores
     * definidos neste método.
     */
    private void executarRelatorioBalanco() {
        JTextField txfIni = (JTextField)listaComponentes.get(0);
        JTextField txfFim = (JTextField)listaComponentes.get(1);
        
        //Validações
        String mensagemDeErro = "";
        
        String stIni = validarData(txfIni.getText());
        if (stIni.equals("")) mensagemDeErro = "Por favor, preencha uma "
                + "DATA INICIAL no formato dd/mm/aaaa.";
        
        String stFim = validarData(txfFim.getText());
        if (stFim.equals("")) mensagemDeErro = "Por favor, preencha uma "
                + "DATA FINAL no formato dd/mm/aaaa.";
        
        //Executando relatório
        if (mensagemDeErro.equals("")) {
            //Entra aqui em caso de validação ok
            RelatorioBalanco r = 
                    new RelatorioBalanco(Date.valueOf(stIni), Date.valueOf(stFim));
            r.criar();
            
            Tabela tabela = r.getTabela();
            jsTabela.setViewportView(tabela);
            tabela.getParent().setBackground(Color.white);
            lblResultados.setText(r.getTextoRetorno());
            
            if (r.getStatus() > 0) {
                lblResultados.setForeground(new Color(57, 172, 57));
            } else if (r.getStatus() < 0) {
                lblResultados.setForeground(new Color(255, 0, 0));
            }
        } else {
            //Entra aqui em caso de erro na validação
            JOptionPane.showMessageDialog(null, mensagemDeErro, "Erro nos Dados", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Valida uma string como data.
     * 
     * @param s string a ser testada
     * @return uma string com a data formatada em caso de válido ou uma string
     * vazia em caso de inválido
     */
    private String validarData(String s) {
        String retorno = "";
        
        try {
            FormatadorParaSQLData f = new FormatadorParaSQLData();
            Date d = Date.valueOf(f.formatarParaSQLData(s));
            retorno = d.toString();
        } catch (Exception e) {
            retorno = "";
            System.out.println(e.getMessage());
        }
        
        return retorno;
    }
    
    private int getIdNoCombo(String s, int index) {
        int retorno = index;
        if (index > 0) {
            int space = s.indexOf(" ");
            String sid = s.substring(0, space);
            retorno = Integer.valueOf(sid);
        }
        return retorno;
    }

    public JLabel getLblResultados() {
        return lblResultados;
    }

    public JScrollPane getJsTabela() {
        return jsTabela;
    }

    public String getNomeTela() {
        return nomeTela;
    }
    
}
