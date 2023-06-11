/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package GUI.hardware;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import GUI.Home;

/**
 * Esta classe representa o menu principal de operações de hardware.
 */
public class MenuHardware extends JFrame {

    private JButton adicionaHardware;
    private JButton atualizaHardware;
    private JButton buscaHardware;
    private JButton deletaHardware;
    private JButton buttonHome;

    /**
     * Constrói uma instância da classe MenuHardware.
     * Configura os componentes do menu e define as ações dos botões.
     */
    public MenuHardware() {

        setLayout(null);

        adicionaHardware = new JButton("Adicionar Hardware");
        atualizaHardware = new JButton("Atualizar Hardware");
        buscaHardware = new JButton("Buscar Hardware");
        deletaHardware = new JButton("Deletar Hardware");
        buttonHome = new JButton("Home");

        adicionaHardware.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdicionarHardware();
                dispose();
            }
        });

        atualizaHardware.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AtualizarHardware();
                dispose();
            }
        });

        buscaHardware.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarHardware();
                dispose();
            }
        });

        
        deletaHardware.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeletarHardware();
                dispose();
            }
        });
        buttonHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                dispose();
            }
        });
        
        adicionaHardware.setBounds(100, 10, 200, 40);
        atualizaHardware.setBounds(100, 60, 200, 40);
        buscaHardware.setBounds(100, 110, 200, 40);
        deletaHardware.setBounds(100, 160, 200, 40);
        buttonHome.setBounds(100, 250, 200, 25);

        // Especificações da Tela
        setSize(400, 350);
        setTitle("Menu Hardware");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(adicionaHardware);
        getContentPane().add(atualizaHardware);
        getContentPane().add(buscaHardware);
        getContentPane().add(deletaHardware);
        getContentPane().add(buttonHome);

    }

}
