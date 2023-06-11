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
import javax.swing.JTextField;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Esta classe representa a tela de cadastro de usuários.
 */
public class TelaCadastro extends JFrame {
    JLabel lblNome, lblSenha;
    JTextField txtNome, txtSenha;
    JButton btnEnviar;

    /**
     * Constrói uma instância da classe TelaCadastro.
     */
    public TelaCadastro(){
        setTitle("Cadastro");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblNome = new JLabel("Nome:");
        txtNome = new JTextField();

        lblSenha = new JLabel("Senha:");
        txtSenha = new JTextField();

        btnEnviar = new JButton("Cadastrar");

        lblNome.setBounds(150, 10, 100, 30);
        txtNome.setBounds(150, 40, 100, 30);

        lblSenha.setBounds(150, 90, 100, 30);
        txtSenha.setBounds(150, 120, 100, 30);

        btnEnviar.setBounds(150, 200, 100, 55);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insereUsuario();;
            }
        });    

        getContentPane().add(lblNome);
        getContentPane().add(txtNome);
        getContentPane().add(lblSenha);
        getContentPane().add(txtSenha);
        getContentPane().add(btnEnviar);

        setVisible(true);

        // Especificações da Tela
        setSize(400, 350);
        setTitle("Tela Cadastro");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Insere um novo usuário no banco de dados.
     */
    public void insereUsuario(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("ProjetoPOO2");
        MongoCollection<Document> collection = database.getCollection("usuario");

        Document document = new Document("nome", txtNome.getText())
        .append("senha", txtSenha.getText());

        collection.insertOne(document);
        mongoClient.close();
        new Home();
        dispose();
    } 
}
