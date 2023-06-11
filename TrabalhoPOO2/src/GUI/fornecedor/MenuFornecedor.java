/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package GUI.fornecedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import GUI.Home;

/**
 * Esta classe representa o menu principal para as operações relacionadas aos fornecedores.
 */
public class MenuFornecedor extends JFrame {

    private JButton adicionaFornecedor;
    private JButton atualizaFornecedor;
    private JButton buscaFornecedor;
    private JButton deletaFornecedor;
    private JButton buttonHome;

    /**
     * Constrói uma instância da classe MenuFornecedor.
     * Configura os componentes da tela e define as ações dos botões.
     */
    public MenuFornecedor() {

        setLayout(null);

        adicionaFornecedor = new JButton("Adicionar Fornecedor");
        atualizaFornecedor = new JButton("Atualizar Fornecedor");
        buscaFornecedor = new JButton("Buscar Fornecedor");
        deletaFornecedor = new JButton("Deletar Fornecedor");
        buttonHome = new JButton("Home");

        adicionaFornecedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdicionarFornecedor();
                dispose();
            }
        });

        atualizaFornecedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AtualizarFornecedor();
                dispose();
            }
        });

        buscaFornecedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarFornecedor();
                dispose();
            }
        });

        
        deletaFornecedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeletarFornecedor();
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

        adicionaFornecedor.setBounds(100, 10, 200, 40);
        atualizaFornecedor.setBounds(100, 60, 200, 40);
        buscaFornecedor.setBounds(100, 110, 200, 40);
        deletaFornecedor.setBounds(100, 160, 200, 40);
        buttonHome.setBounds(100, 250, 200, 25);

        // Especificações da Tela
        setSize(400, 350);
        setTitle("Menu Fornecedor");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(adicionaFornecedor);
        getContentPane().add(atualizaFornecedor);
        getContentPane().add(buscaFornecedor);
        getContentPane().add(buttonHome);
        getContentPane().add(deletaFornecedor);

    }
}
