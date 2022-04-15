package projeto.barbearia.View;

import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import projeto.barbearia.Model.Constantes;
import projeto.barbearia.Model.Horario;
import projeto.barbearia.Model.Tipos.TipoCadastro;

public class painelAgenda extends JPanel{
    
    private JPanel painel1;
    private JPanel painel2;
    public static TabelaHorario tabelaAgenda;
    String dadosProfissionais;
    String[] profissionais;
    JCalendar calendario;
    
    public painelAgenda() {
        dadosProfissionais = "Horario,Bia,Mateus,Barney,Ted,Lily,Mario,Luigi,Toad,Link,Sonic,Peach,Zelda";
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints bag = new GridBagConstraints();
        this.setLayout(bagLayout);
        
        painel1();
        painel2();
        
        bag.insets = new Insets(10,10,10,10);
        bag.fill = GridBagConstraints.VERTICAL;
        bag.anchor = GridBagConstraints.NORTHWEST;
        bag.weighty = 1;
        bag.gridx = 0;
        bag.gridy = 0;
        this.add(painel1, bag);
        
        bag.anchor = GridBagConstraints.NORTH;
        bag.fill = GridBagConstraints.BOTH;
        bag.weightx = 1;
        bag.weighty = 1;
        bag.gridx = 1;
        bag.gridy = 0;
        this.add(painel2, bag);
        
    }
    
    private void painel1() {
        painel1 = new JPanel(new GridBagLayout());
        //painel1.setBackground(Color.WHITE);
        GridBagConstraints bag = new GridBagConstraints();
        
        //Cria Calendario
        calendario = new JCalendar();
        calendario.addPropertyChangeListener("calendar", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                onClickCalendario();
            }
        });
        
        
        //Cria botão
        button_icone buttonAgendar = new button_icone(Constantes.CAMINHO_IMAGENS + "horario.png", 
                "Marcar Horário", 40, 50);
        buttonAgendar.addActionListener((e) -> onClickAgendar(true));
        
        JPanel painelOpcoes = new JPanel();
        javax.swing.JButton btExcluir = new javax.swing.JButton("Excluir Horário");
        javax.swing.ImageIcon img = new javax.swing.ImageIcon(Constantes.CAMINHO_IMAGENS + "excluir.png");
        btExcluir.setIcon(img);
        btExcluir.addActionListener((e) -> onClickExcluir());
        javax.swing.JButton btPagar = new javax.swing.JButton("Efetuar Pagto.");
        img = new javax.swing.ImageIcon(Constantes.CAMINHO_IMAGENS + "pagamento.png");
        btPagar.setIcon(img);
        btPagar.addActionListener((e) -> onClickEfetuarPagto());
        painelOpcoes.setLayout(new GridBagLayout());
        painelOpcoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Opções"));
        GridBagConstraints b = new GridBagConstraints();
        b.fill = GridBagConstraints.NONE;
        b.anchor = GridBagConstraints.CENTER;
        b.insets = new Insets(5,5,5,5);
        painelOpcoes.add(btExcluir, b);
        b.gridy = 1;
        painelOpcoes.add(btPagar, b);
        
        bag.fill = GridBagConstraints.BOTH;
        bag.gridx = 0;
        bag.gridy = 0;
        bag.insets = new Insets(0,0,5,0);
        painel1.add(buttonAgendar, bag);
        
        bag.insets = new Insets(0,0,0,0);
        bag.gridy = 1;
        bag.weighty = 1;
        painel1.add(calendario, bag);
        
        //calendario.getParent().setBackground(Color.red);
        bag.insets = new Insets(7,0,7,0);
        bag.weighty = 3;
        bag.gridy = 2;
        painel1.add(painelOpcoes, bag);
    }
    
    private void painel2() {
        painel2 = new JPanel(new GridBagLayout());
        GridBagConstraints bag = new GridBagConstraints();
        bag.fill = GridBagConstraints.BOTH;
        bag.weightx = 1;
        bag.weighty = 1;
        bag.gridx = 0;
        bag.gridy = 0;
        
        //Captura da data
        java.util.Date data = calendario.getDate();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String dataSelecionada = dt.format(data);
        //--Fim captura de data
        
        tabelaAgenda = new TabelaHorario();
        tabelaAgenda.preencherTabela(Date.valueOf(dataSelecionada));
        JScrollPane scroll = new JScrollPane(tabelaAgenda,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tabelaAgenda.getParent().setBackground(Color.WHITE);
        painel2.add(scroll, bag);
        
        tabelaAgenda.addMouseListener(onClickHorario());
        
    }
    
    private MouseAdapter onClickHorario() {
        
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int col = tabelaAgenda.columnAtPoint(e.getPoint());
                
                if (col > 0 && e.getClickCount() == 2) {
                    onClickAgendar(false);
                }
                
            }
        };
        
    }
    
    private void onClickCalendario() {
        
        //Captura da data
        java.util.Date data = calendario.getDate();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String dataSelecionada = dt.format(data);
        //--Fim captura de data

        tabelaAgenda.preencherTabela(Date.valueOf(dataSelecionada));
        
    }
    
    private void onClickExcluir() {
        
        boolean horarioSelecionado = true;
        
        if (tabelaAgenda.getSelectedColumn() > 0) {
            //Entra aqui caso haja uma linha selecionada na tabela
            
            if (tabelaAgenda.getValueAt(tabelaAgenda.getSelectedRow(), 
                    tabelaAgenda.getSelectedColumn()) != null) {
                //Entra aqui caso a célula selecionada seja um horário marcado
                
                java.util.Date data = calendario.getDate();
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                String dataSelecionada = dt.format(data);
                String hora = tabelaAgenda.getValueAt(tabelaAgenda.getSelectedRow(), 0).toString();
                String prof = tabelaAgenda.getColumnName(tabelaAgenda.getSelectedColumn());
                int horarioOcupado, pid, space = prof.indexOf(" ");
                String sid = prof.substring(0, space);
                pid = Integer.valueOf(sid);
                
                horarioOcupado = Horario.encontrarHorarioMarcado(hora, dataSelecionada, pid);
                
                Horario.remover(horarioOcupado);
                tabelaAgenda.preencherTabela(Date.valueOf(dataSelecionada));
                
            } else {
                horarioSelecionado = false;
            }
            
        } else {
            horarioSelecionado = false;
        }
        
        if (!horarioSelecionado) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um HORÁRIO!", 
                    "Selecionar Horário", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    private void onClickEfetuarPagto() {
        
        boolean horarioSelecionado = true;
        
        if (tabelaAgenda.getSelectedColumn() > 0) {
            //Entra aqui caso haja uma linha selecionada na tabela
            
            if (tabelaAgenda.getValueAt(tabelaAgenda.getSelectedRow(), 
                    tabelaAgenda.getSelectedColumn()) != null) {
                //Entra aqui caso a célula selecionada seja um horário marcado
                
                int horarioOcupado;
                String prof;
                int space, pid;
                String hora;
                String dataSelecionada;
                
                //Obtendo o id do profissional
                prof = tabelaAgenda.getColumnName(tabelaAgenda.getSelectedColumn());
                space = prof.indexOf(" ");
                String sid = prof.substring(0, space);
                pid = Integer.valueOf(sid);
                
                //Obtendo hora
                hora = tabelaAgenda.getValueAt(tabelaAgenda.getSelectedRow(), 0).toString();
                
                //Obtendo data
                java.util.Date data = calendario.getDate();
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                dataSelecionada = dt.format(data);
                
                horarioOcupado = Horario.encontrarHorarioMarcado(hora, dataSelecionada, pid);
                
                if (horarioOcupado > 0) {
                    Horario h = new Horario(horarioOcupado);
                    
                    CadastroEntrada c = new CadastroEntrada(TipoCadastro.NOVO_CADASTRO, 0);
                    c.preSetValor(h.getServicoid());
                    c.preSetCliente(h.getClienteid());
                    c.preSetProfissional(h.getProfissionalid());
                    c.preSetServico(h.getServicoid());
                    
                } else {
                    horarioSelecionado = false;
                }
                
            } else {
                horarioSelecionado = false;
            }
            
        } else {
            horarioSelecionado = false;
        }
        
        if (!horarioSelecionado) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um HORÁRIO!", 
                    "Selecionar Horário", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void onClickAgendar(boolean desconsiderarTabela) {
        CacheAtualizacao.adicionarItemAoCache(tabelaAgenda, "CadastroHorario");
        CadastroHorario c;
        
        //Definindo preset de data
        java.util.Date data = calendario.getDate();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String dataSelecionada = dt.format(data);
        
        if (tabelaAgenda.getSelectedRow() >= 0 && !desconsiderarTabela) {
            //Entra aqui se, clicando duas vezes na tabela, alguma linha estiver selecionada
            //desde que não seja a primeira coluna.
            
            String hora = tabelaAgenda.getValueAt(tabelaAgenda.getSelectedRow(), 0).toString();
            String prof = tabelaAgenda.getColumnName(tabelaAgenda.getSelectedColumn());
            
            if (tabelaAgenda.getValueAt(tabelaAgenda.getSelectedRow(), 
                    tabelaAgenda.getSelectedColumn()) != null) {
                //Entra aqui caso já tenha um cliente marcado no horário e prof. selecionado
                
                //Obtendo profissionalid
                int horarioOcupado, pid, space = prof.indexOf(" ");
                String sid = prof.substring(0, space);
                pid = Integer.valueOf(sid);
                
                horarioOcupado = Horario.encontrarHorarioMarcado(hora, dataSelecionada, pid);
                
                if (horarioOcupado == -1) {
                    //horário vago
                    c = new CadastroHorario();
                    c.definirPresetData(Date.valueOf(dataSelecionada));
                    c.definirPresetHora(hora);
                    c.definirPresetProfissional(prof);
                } else {
                    //horário ocupado
                    c = new CadastroHorario(horarioOcupado);
                }
                
            } else {
                c = new CadastroHorario();
                c.definirPresetData(Date.valueOf(dataSelecionada));
                c.definirPresetHora(hora);
                c.definirPresetProfissional(prof);
            }
            
        } else {
            c = new CadastroHorario();
            c.definirPresetData(Date.valueOf(dataSelecionada));
        }
        
    }
    
}
