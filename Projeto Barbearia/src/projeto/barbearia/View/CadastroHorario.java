
package projeto.barbearia.View;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import projeto.barbearia.Model.FormatadorParaSQLData;
import projeto.barbearia.Model.Horario;
import projeto.barbearia.Model.Tipos.ComboClienteTipo;
import projeto.barbearia.Model.Tipos.ComboProfissionalTipo;
import projeto.barbearia.Model.Tipos.ComboServicoTipo;
import projeto.barbearia.Model.Tipos.TipoCadastro;

/**
 *
 * @author lucas
 */
public class CadastroHorario extends JFrame {
    
    private JTextField txfData;
    private JComboBox cbHora;
    private ComboCliente cbCliente;
    private ComboServico cbServico;
    private ComboProfissional cbProfissional;
    private TipoCadastro tipo;
    private Horario h;
    private String mensagemDeErro;
    private Date dataParaCadastro;
    private boolean validarDisponibilidadeDeHorario;
    
    /**
     * Construir a tela <code>CadastroHorario</code> 
     * para a finalidade de um novo cadastro.
     */
    public CadastroHorario() {
        super();
        tipo = TipoCadastro.NOVO_CADASTRO;
        h = new Horario();
        h.setClienteid(0);
        h.setServicoid(0);
        h.setProfissionalid(0);
        construirInterface();
        validarDisponibilidadeDeHorario = true;
    }
    
    /**
     * Construir a tela <code>CadastroHorario</code> 
     * para a finalidade de alterar um horário existente,
     * conforme o parâmetro <code>id</code>.
     * 
     * @param id identifica o horário a ser alterado,
     * e não pode ser setado como 0.
     */
    public CadastroHorario(int id) {
        super();
        tipo = TipoCadastro.ALTERACAO;
        h = new Horario(id);
        construirInterface();
        validarDisponibilidadeDeHorario = false;
    }
    
    private void construirInterface() {
        
        //Preparação inicial da Janela
        GridBagLayout bagLayout = new GridBagLayout(); //Layout
        GridBagConstraints bag = new GridBagConstraints(); //Layout
        setLayout(bagLayout); //Layout
        setSize(400,340); //Tamanho
        setResizable(false); //Impedindo redimensionamento da janela
        setTitle("Agendar Horário"); //Título da nova janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Função do botão Fechar ('X')
        setVisible(true); //Fazendo a janela aparecer
        setLocationRelativeTo(null); //Não sei exatamente o porque de precisarmos disso...
        
        //Criando e adicionando linhas
        bag.insets = new Insets(0, 10, 5, 10);
        bag.fill = GridBagConstraints.BOTH;
        bag.weighty = 1;
        bag.weightx = 1;
        bag.gridx = 0;
        bag.gridy = 0;
        add(getLinha1(), bag);
        
        bag.gridy = 1;
        add(getLinha2(), bag);
        
        bag.gridy = 2;
        add(getLinha3(), bag);
        
        bag.insets = new Insets(0, 10, 10, 10);
        bag.gridy = 3;
        add(getLinha4(), bag);
        
    }
    
    private JPanel getLinha1() {
        
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        JLabel lblData = new JLabel("Data*:");
        JLabel lblHora = new JLabel("Hora*:");
        
        //Preparando campos
        lblData.setFont(new Font("Dialog", Font.PLAIN, 17));
        lblHora.setFont(new Font("Dialog", Font.PLAIN, 17));
        txfData = new JTextField();
        txfData.setHorizontalAlignment(JTextField.CENTER);
        txfData.setFont(new Font("Dialog", Font.PLAIN, 17));
        txfData.setText(this.h.getData_bfFormatado());
        cbHora = (this.tipo == TipoCadastro.NOVO_CADASTRO) ? new ComboHora() : 
                new ComboHora(this.h.getHora_bdFormatado());
        txfData.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                  definirAlteracaoDeAgenda();
                }
                public void removeUpdate(DocumentEvent e) {
                  definirAlteracaoDeAgenda();
                }
                public void insertUpdate(DocumentEvent e) {
                  definirAlteracaoDeAgenda();
                }});
        cbHora.addActionListener((e) -> definirAlteracaoDeAgenda());
        
        //Inserindo campos
        b.weightx = 2;
        b.insets = new Insets(5,5,5,5);
        b.fill = GridBagConstraints.HORIZONTAL;
        b.anchor = GridBagConstraints.WEST;
        b.gridx = 0;
        b.gridy = 0;
        p.add(lblData, b);
        
        b.gridy = 1;
        p.add(txfData, b);
        
        b.weightx = 1;
        b.gridx = 1;
        b.gridy = 0;
        p.add(lblHora, b);
        
        b.gridy = 1;
        p.add(cbHora, b);
        
        return p;
        
    }
    
    private JPanel getLinha2() {
        
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        JLabel lblCliente = new JLabel("Cliente*:");
        
        //Preparando campos
        lblCliente.setFont(new Font("Dialog", Font.PLAIN, 17));
        Object args[] = new Object[1];
        args[0] = h.getClienteid();
        cbCliente = new ComboCliente();
        cbCliente.preencherComboPara(ComboClienteTipo.AGENDAMENTO, args);
        
        //Inserindo campos
        b.fill = GridBagConstraints.HORIZONTAL;
        b.weightx = 1;
        b.insets = new Insets(5,5,5,5);
        p.add(lblCliente, b);
        
        b.gridy = 1;
        p.add(cbCliente, b);
        
        return p;
        
    }
    
    private JPanel getLinha3() {
        
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        JLabel lblServico = new JLabel("Serviço*:");
        JLabel lblProfissional = new JLabel("Profissional*:");
        
        //Preparando campos
        lblServico.setFont(new Font("Dialog", Font.PLAIN, 17));
        lblProfissional.setFont(new Font("Dialog", Font.PLAIN, 17));
        Object[] args = new Object[1];
        args[0] = h.getServicoid();
        cbServico = new ComboServico();
        cbServico.preencherComboPara(ComboServicoTipo.AGENDAMENTO, args);
        args[0] = h.getProfissionalid();
        cbProfissional = new ComboProfissional();
        cbProfissional.preencherComboPara(ComboProfissionalTipo.AGENDAMENTO, args);
        cbProfissional.addActionListener((e) -> definirAlteracaoDeAgenda());
        
        //Inserindo campos
        b.insets = new Insets(5,5,5,5);
        b.anchor = GridBagConstraints.WEST;
        b.fill = GridBagConstraints.HORIZONTAL;
        b.weightx = 1;
        p.add(lblServico, b);
        
        b.gridy = 1;
        p.add(cbServico, b);
        
        b.gridx = 1;
        b.gridy = 0;
        p.add(lblProfissional, b);
        
        b.gridy = 1;
        p.add(cbProfissional, b);
        
        return p;
        
    }
    
    private JPanel getLinha4() {
        
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        JButton btAgendar = new JButton("Agendar");
        JButton btCancelar = new JButton("Cancelar");
        
        //Preparando campos
        btAgendar.setFont(new Font("Dialog", Font.PLAIN, 17));
        btCancelar.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        //Adicionando ações aos botões
        btAgendar.addActionListener((e) -> onClickSalvar());
        btCancelar.addActionListener((e) -> this.dispose());
        
        //Inserindo campos
        b.insets = new Insets(5,5,5,5);
        b.anchor = GridBagConstraints.EAST;
        b.fill = GridBagConstraints.NONE;
        b.weightx = 1;
        p.add(btAgendar, b);
        
        b.anchor = GridBagConstraints.WEST;
        b.gridx = 1;
        p.add(btCancelar, b);
        
        return p;
    
    }
    
    public void definirPresetData(Date d) {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        String dataSelecionada = dt.format(d);
        txfData.setText(dataSelecionada);
    }
    
    public void definirPresetData() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date datem = new java.util.Date();
        txfData.setText(dateFormat.format(datem));
    }
            
    public void definirPresetHora(String h) {
        cbHora.setSelectedItem(h);
    }
    
    public void definirPresetProfissional(String p) {
        cbProfissional.setSelectedItem(p);
    }
    
    public void definirPresetProfissional(int profissionalid) {
        int nIndex = 0;
        for (int i = 1; i < cbProfissional.getItemCount(); i++) {
            if (cbProfissional.getItemAt(i).toString().startsWith(profissionalid + " -")) nIndex = i;
        }
        cbProfissional.setSelectedIndex(nIndex);
    }
    
    public void definirPresetCliente(int clienteid) {
        int nIndex = 0;
        for (int i = 1; i < cbCliente.getItemCount(); i++) {
            if (cbCliente.getItemAt(i).toString().startsWith(clienteid + " -")) nIndex = i;
        }
        cbCliente.setSelectedIndex(nIndex);
    }
    
    private void onClickSalvar() {
        
        if (validarDados()) {
            
            int clienteid = getIdNoCombo(cbCliente.getSelectedItem().toString(), 
                    cbCliente.getSelectedIndex());
            int profissionalid = getIdNoCombo(cbProfissional.getSelectedItem().toString(), 
                    cbProfissional.getSelectedIndex());
            int servicoid = getIdNoCombo(cbServico.getSelectedItem().toString(), 
                    cbServico.getSelectedIndex());
            Time horaParaCadastro = Time.valueOf(cbHora.getSelectedItem().toString() + ":00");
            
            if (tipo == TipoCadastro.ALTERACAO) Horario.remover(h.getHorarioid(), true);
            h.setClienteid(clienteid);
            h.setProfissionalid(profissionalid);
            h.setServicoid(servicoid);
            h.setData_bd(dataParaCadastro);
            h.setHora_bd(horaParaCadastro);
            
            if (h.cadastrar()) {
                JOptionPane.showMessageDialog(this, "Horário agendado!", 
                    "Agendamento Finalizado", JOptionPane.INFORMATION_MESSAGE);
                
                CacheAtualizacao.checarEAtualizar("CadastroHorario"); //Atualizando itens necessários
                this.dispose(); //Parabéns pelo bom trabalho, vamos fechar a janela agora...
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível agendar o horário. "
                        + "Por favor, tente novamente mais tarde.", 
                    "Ocorreu um Erro", JOptionPane.WARNING_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, mensagemDeErro, 
                    "Erro nos Dados", JOptionPane.WARNING_MESSAGE);
        }
        
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
    
    private boolean validarDados() {
        
        boolean retorno = true;
        
        //Validando data
        if (txfData.getText().equals("")) {
            retorno = false;
            mensagemDeErro = "Por favor, preencha a DATA.";
        } else {
            try {
                FormatadorParaSQLData f = new FormatadorParaSQLData();
                Date d = Date.valueOf(f.formatarParaSQLData(txfData.getText()));
                dataParaCadastro = d;
            } catch (Exception e) {
                retorno = false;
                mensagemDeErro = "Por favor, preencha uma DATA no formato dd/mm/aaaa.";
            }
        }
        
        //Validando cliente
        if (cbCliente.getSelectedIndex() <= 1) {
            retorno = false;
            mensagemDeErro = "Por favor, selecione um CLIENTE.";
        }
        
        //Validando serviço
        if (cbServico.getSelectedIndex() == 0) {
            retorno = false;
            mensagemDeErro = "Por favor, selecione um SERVIÇO.";
        }
        
        //Validando profissional
        if (cbProfissional.getSelectedIndex() == 0) {
            retorno = false;
            mensagemDeErro = "Por favor, selecione um PROFISSIONAL.";
        }
        
        /**
         * 
         * 
         * 
         * VERIFICANDO DISPONIBILIDADE DE HORÁRIO
         * 
         * 
         */
        
        //Por último, validando se, neste horário selecionado, já não há cliente marcado
        if (retorno && validarDisponibilidadeDeHorario) {
            //entra aqui somente se já estiver tudo ok, faltando apenas este ponto
            //E também somente se a verificação de disponibilidade de horário for solicitada
            
            FormatadorParaSQLData f = new FormatadorParaSQLData();
            int horarioOcupado, profissionalid;
            
            profissionalid = getIdNoCombo(cbProfissional.getSelectedItem().toString(), 
                    cbProfissional.getSelectedIndex());
            horarioOcupado = Horario.encontrarHorarioMarcado(
                    cbHora.getSelectedItem().toString(), f.formatarParaSQLData(txfData.getText()), profissionalid);
            
            if (horarioOcupado > -1) {
                retorno = false;
                mensagemDeErro = "Horário INDISPONÍVEL.";
            }
            
        }
        
        return retorno;
        
    }
    
    private void definirAlteracaoDeAgenda() {
        validarDisponibilidadeDeHorario = true;
    }
    
}
