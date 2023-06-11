/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import GUI.fornecedor.MenuFornecedor;
import GUI.hardware.MenuHardware;

/**
 * Esta classe representa a tela inicial do sistema.
 */
public class Home extends JFrame {

    public JButton bntFornecedor, bntHardware;
    public JLabel lblTitulo;

    /**
     * Constrói uma instância da classe Home.
     * Configura os componentes da tela inicial e define as ações dos botões.
     */
    public Home() {

        setLayout(null);

        lblTitulo = new JLabel("Bem vindo ao sistema");

        bntFornecedor = new JButton("Fornecedor");
        bntHardware = new JButton("Hardware");

        lblTitulo.setBounds(90, 10, 500, 25);
        bntFornecedor.setBounds(90, 80, 200, 50);
        bntHardware.setBounds(90, 180, 200, 50);

        getContentPane().add(lblTitulo);
        getContentPane().add(bntFornecedor);
        getContentPane().add(bntHardware);

        lblTitulo.setVisible(true);
        bntFornecedor.setVisible(true);
        bntHardware.setVisible(true);

        // Especificações da Tela
        setSize(400, 350);
        setTitle("Menu Fornecedor");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 23)); // NOI18N
        lblTitulo.setText("Bem vindo ao sistema");

        bntFornecedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFornecedor();
                dispose();
            }
        });

        bntHardware.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuHardware();
                dispose();
            }
        });
    }

}
