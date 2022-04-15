package projeto.barbearia.View;

import java.awt.BorderLayout;
import java.awt.Cursor;
import static java.awt.FlowLayout.CENTER;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import projeto.barbearia.Controller.ouvintesIconesInicio;
import projeto.barbearia.Model.Constantes;
import projeto.barbearia.Model.Parametros;

public class PainelInicial extends JPanel{
    
    private JButton criaTabs;
    private JLabel textoInicio;
    private JPanel grid;
    private JPanel grid2;
    private ImageIcon imagemlogo;
    private ImageIcon imagemclientes;
    private ImageIcon imagemprofissionais;
    private ImageIcon imagemcaixa;
    private ImageIcon imagemestoque;
    private ImageIcon imagemservices;
    private ImageIcon imagemagenda;


    
    public PainelInicial() {
        setLayout(new GridLayout(3,1,0,15));
        grid = new JPanel();
        grid2 = new JPanel();
        grid.setLayout(new GridLayout(1,3,100,0));
        grid2.setLayout(new GridLayout(1,3,100,0));
        adicionandoLogo();
        desenhando();
        add(grid);
        add(grid2);
        
        //Verificando Lembretes
        verificarLembretes();
    }
    
    private void adicionandoLogo() {
        //imagemlogo = new ImageIcon("C:\\Users\\mestr\\OneDrive\\Documents\\Mateus\\Programação\\Projetos NetBeans\\Imagens\\neres-logo.png");
        imagemlogo = new ImageIcon(Constantes.CAMINHO_IMAGENS + "logoC.png");
        JLabel labellogo = new JLabel(imagemlogo);
        add(labellogo);
    }
    
    private void desenhando() {
        imagemclientes = new ImageIcon(Constantes.CAMINHO_IMAGENS + "client.png");
        JLabel labelcliente = new JLabel("Clientes", imagemclientes, SwingConstants.CENTER);
        labelcliente.setHorizontalTextPosition(SwingConstants.CENTER);
        labelcliente.setVerticalTextPosition( SwingConstants.BOTTOM);
        labelcliente.setFont(new Font("Dialog", Font.PLAIN, 20));
        labelcliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //CROSSHAIR_CURSOR mais loco
        
        imagemprofissionais = new ImageIcon(Constantes.CAMINHO_IMAGENS + "profissional.png");
        JLabel labelprofissionais = new JLabel("Profissionais", imagemprofissionais, SwingConstants.CENTER);
        labelprofissionais.setHorizontalTextPosition( SwingConstants.CENTER );
        labelprofissionais.setVerticalTextPosition( SwingConstants.BOTTOM );
        labelprofissionais.setFont(new Font("Dialog", Font.PLAIN, 20));
        labelprofissionais.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        imagemcaixa = new ImageIcon(Constantes.CAMINHO_IMAGENS + "caixa.png");
        JLabel labelcaixa = new JLabel("Caixa",imagemcaixa, SwingConstants.CENTER);
        labelcaixa.setHorizontalTextPosition( SwingConstants.CENTER );
        labelcaixa.setVerticalTextPosition( SwingConstants.BOTTOM );
        labelcaixa.setFont(new Font("Dialog", Font.PLAIN, 20));
        labelcaixa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        imagemestoque = new ImageIcon(Constantes.CAMINHO_IMAGENS + "estoque.png");
        JLabel labelestoque = new JLabel("Estoque",imagemestoque, SwingConstants.CENTER);
        labelestoque.setHorizontalTextPosition( SwingConstants.CENTER );
        labelestoque.setVerticalTextPosition( SwingConstants.BOTTOM );
        labelestoque.setFont(new Font("Dialog", Font.PLAIN, 20));
        labelestoque.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        imagemservices = new ImageIcon(Constantes.CAMINHO_IMAGENS + "services.png");
        JLabel labelservices = new JLabel("Serviços",imagemservices, SwingConstants.CENTER);
        labelservices.setHorizontalTextPosition( SwingConstants.CENTER );
        labelservices.setVerticalTextPosition( SwingConstants.BOTTOM );
        labelservices.setFont(new Font("Dialog", Font.PLAIN, 20));
        labelservices.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        imagemagenda = new ImageIcon(Constantes.CAMINHO_IMAGENS + "agenda.png");
        JLabel labelagenda = new JLabel("Agenda",imagemagenda, SwingConstants.CENTER);
        labelagenda.setHorizontalTextPosition( SwingConstants.CENTER );
        labelagenda.setVerticalTextPosition( SwingConstants.BOTTOM );
        labelagenda.setFont(new Font("Dialog", Font.PLAIN, 20));
        labelagenda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        labelcliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
            @Override
            public void mousePressed(MouseEvent e) {
                new ouvintesIconesInicio().onClickClientes();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
        });
        
        labelprofissionais.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new ouvintesIconesInicio().onClickProfissionais();
            }
        });
        
        labelcaixa.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) 
            {
                new ouvintesIconesInicio().onClickCaixa();
            }
        });
        
        labelestoque.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) 
            {
                new ouvintesIconesInicio().onClickEstoque();
            }
        });
        
        labelservices.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new ouvintesIconesInicio().onClickServices();
            }
        });
        
        labelagenda.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new ouvintesIconesInicio().onClickAgenda();
            }
        });
        
        grid.add(labelcliente);
        grid.add(labelprofissionais);
        grid.add(labelcaixa);
        
        grid2.add(labelestoque);
        grid2.add(labelservices);
        grid2.add(labelagenda);
        
    }

    private void verificarLembretes() {
        if (Parametros.getLembreteAniversarioAtivado() || 
                Parametros.getLembreteVencimentosAtivado()) {
            new projeto.barbearia.View.TelaAvisos();
        }
    }
    
}
