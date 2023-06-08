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


public class TelaCadastro extends JFrame {
    JLabel lblNome, lblSenha;
    JTextField txtNome, txtSenha;
    JButton btnEnviar;

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

        lblNome.setBounds(20, 20, 100, 20);
        txtNome.setBounds(20, 40, 100, 20);

        lblSenha.setBounds(20, 60, 100, 20);
        txtSenha.setBounds(20, 80, 100, 20);

        btnEnviar.setBounds(20, 100, 100, 20);

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
        setTitle("Tela Secundária");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void insereUsuario(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("ProjetoPOO2");
        MongoCollection<Document> collection = database.getCollection("usuario");

        Document document = new Document("nome", txtNome.getText())
        .append("senha", txtSenha.getText());

        collection.insertOne(document);
        mongoClient.close();
    }


}
