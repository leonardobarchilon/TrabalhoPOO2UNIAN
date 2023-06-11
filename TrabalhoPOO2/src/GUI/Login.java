/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import GUI.hardware.AdicionarHardware;
import autenticar.Autenticacao;

/**
 * Esta classe representa a tela de login do sistema.
 */
public class Login extends JFrame {

    public JLabel lblNome, lblSenha;
    public JTextField txtNome, txtSenha;
    public JButton btnEnviar, btnCadastrar;

    private Autenticacao autenticacao;

    /**
     * Constrói uma instância da classe Login.
     *
     * @param autenticacao o objeto Autenticacao usado para autenticar o usuário
     */
    public Login(Autenticacao autenticacao) {
        this.autenticacao = autenticacao;
    }

    /**
     * Configura os componentes da tela de login e define as ações dos botões.
     *
     * @throws ParseException se ocorrer um erro durante a análise do formato de data
     */
    public void Logar() throws ParseException {
        setTitle("Login");
        setLayout(null);
        lblNome = new JLabel("Nome:");
        txtNome = new JTextField();
        lblSenha = new JLabel("Senha:");
        txtSenha = new JTextField();
        btnEnviar = new JButton("login");
        btnCadastrar = new JButton("Cadastrar");

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliqueBtnEnviar();
                } catch (ParseException ex) {
                    Logger.getLogger(AdicionarHardware.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliqueBntCadastrar();
            }
        });

        lblNome.setBounds(130, 10, 100, 30);
        txtNome.setBounds(130, 40, 200, 30);
        lblSenha.setBounds(130, 90, 100, 30);
        txtSenha.setBounds(130, 120, 200, 30);
        btnEnviar.setBounds(130, 200, 200, 30);
        btnCadastrar.setBounds(130, 250, 200, 30);

        getContentPane().add(lblNome);
        getContentPane().add(txtNome);
        getContentPane().add(lblSenha);
        getContentPane().add(txtSenha);
        getContentPane().add(btnEnviar);
        getContentPane().add(btnCadastrar);

        // Especificações da Tela
        setSize(450, 350);
        setTitle("Login");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Manipula o clique do botão "Enviar".
     *
     * @throws ParseException se ocorrer um erro durante a análise do formato de data
     */
    private void cliqueBtnEnviar() throws ParseException {
        String nome = txtNome.getText();
        String senha = txtSenha.getText();

        if (autenticacao.autenticarUsuario(nome, senha)) {
            JOptionPane.showMessageDialog(Login.this, "Login realizado com sucesso");
            new Home();
            dispose();

        } else {
            JOptionPane.showMessageDialog(Login.this, "Usuário ou senha inválidos");
        }
    }

    /**
     * Manipula o clique do botão "Cadastrar".
     */
    private void cliqueBntCadastrar() {
        new TelaCadastro();
        dispose();
    }
}
