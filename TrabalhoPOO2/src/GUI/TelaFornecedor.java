package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaFornecedor extends JFrame {

    public JLabel lblNome, lblTelefone, lblForma, lblEndereco, lblReputacao;
    public JTextField txtNome;
    public JTextField txtTelefone;
    public JTextField txtForma;
    public JTextField txtEndereco;
    public JTextField txtReputacao;
    public JComboBox cmbForma;
    public JButton btnEnviar;

    private String[] formaContato = { "WhatsApp", "Ligação" };

    public TelaFornecedor() throws ParseException {

        setLayout(null);

        lblNome = new JLabel("Fornecedor:");
        txtNome = new JTextField();
        lblTelefone = new JLabel("Telefone:");
        txtTelefone = new JTextField();
        lblForma = new JLabel("Forma de contato:");
        cmbForma = new JComboBox(formaContato);
        lblEndereco = new JLabel("Endereço:");
        txtEndereco = new JTextField();
        lblReputacao = new JLabel("Reputação (1 a 10):");
        txtReputacao = new JTextField();

        btnEnviar = new JButton("Enviar e criar novo cadastro");

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insereFornecedor();
            }
        });

        lblNome.setBounds(10, 10, 200, 25);
        txtNome.setBounds(130, 10, 200, 25);
        lblTelefone.setBounds(10, 40, 200, 25);
        txtTelefone.setBounds(130, 40, 200, 25);
        lblForma.setBounds(10, 70, 200, 25);
        cmbForma.setBounds(130, 70, 200, 25);
        lblEndereco.setBounds(10, 100, 200, 25);
        txtEndereco.setBounds(130, 100, 200, 25);
        lblReputacao.setBounds(10, 130, 200, 25);
        txtReputacao.setBounds(130, 130, 200, 25);
        btnEnviar.setBounds(115, 210, 200, 50);

        getContentPane().add(lblNome);
        getContentPane().add(txtNome);
        getContentPane().add(lblTelefone);
        getContentPane().add(txtTelefone);
        getContentPane().add(lblForma);
        getContentPane().add(cmbForma);
        getContentPane().add(lblEndereco);
        getContentPane().add(txtEndereco);
        getContentPane().add(lblReputacao);
        getContentPane().add(txtReputacao);
        getContentPane().add(btnEnviar);

        // Especificações da Tela
        setSize(400, 350);
        setTitle("Tela Secundária");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void insereFornecedor(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("ProjetoPOO2");
        MongoCollection<Document> collection = database.getCollection("fornecedor");

        Document document = new Document("nome", txtNome.getText())
                .append("telefone", txtTelefone.getText())
                .append("forma", cmbForma.getSelectedItem().toString())
                .append("endereco", txtEndereco.getText())
                .append("reputacao", txtReputacao.getText());

        collection.insertOne(document);
        mongoClient.close();
    }
}