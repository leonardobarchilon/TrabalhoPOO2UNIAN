package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class TelaHardware extends JFrame{
    
    public JLabel lblNome, lblCategoria, lblEstado, lblQuantidade, lblPreco;
    public JTextField txtNome;
    public JTextField txtQuantidade;
    public JTextField txtPreco;
    public JComboBox cmbCategoria;
    public JComboBox cmbEstado;
    public JButton btnEnviar;
    
    private String[] categoriaPeca = {"Servidor", "Desktop", "Notebook"};
    private String[] estadoPeca = {"Novo", "Usado"};
    
    public TelaHardware() throws ParseException{
        
        setLayout(null);
        
        lblNome = new JLabel("Nome da Peça:");
        txtNome = new JTextField();
        lblCategoria = new JLabel("Categoria:");
        cmbCategoria = new JComboBox(categoriaPeca);
        lblEstado = new JLabel("Estado:");
        cmbEstado = new JComboBox(estadoPeca);
        lblQuantidade = new JLabel("Quantidade:");
        txtQuantidade = new JTextField();
        lblPreco = new JLabel("Preço:");
        txtPreco = new JTextField();
        btnEnviar = new JButton("Enviar e Prosseguir");
        
        
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insereHardware();
            }
        });
        
        
        lblNome.setBounds(10, 10, 200, 25);
        txtNome.setBounds(120, 10, 200, 25);
        lblCategoria.setBounds(10, 40, 200, 25);
        cmbCategoria.setBounds(120,40,200,25);
        lblEstado.setBounds(10, 70, 200, 25);
        cmbEstado.setBounds(120,70,200,25);
        lblQuantidade.setBounds(10,100,200,25);
        txtQuantidade.setBounds(120,100,200,25);
        lblPreco.setBounds(10,130,200,25);
        txtPreco.setBounds(120,130,200,25);
        btnEnviar.setBounds(125, 220, 150, 50);

        getContentPane().add(lblNome);
        getContentPane().add(txtNome);
        getContentPane().add(lblCategoria);
        getContentPane().add(cmbCategoria);
        getContentPane().add(lblEstado);
        getContentPane().add(cmbEstado);
        getContentPane().add(lblQuantidade);
        getContentPane().add(txtQuantidade);
        getContentPane().add(lblPreco);
        getContentPane().add(txtPreco);
        getContentPane().add(btnEnviar);
        
        //Especificações da Tela
        setSize(400, 350);
        setTitle("Tela Inicial");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void insereHardware(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("ProjetoPOO2");
        MongoCollection collection = database.getCollection("Hardware");

        Document document = new Document("nome", txtNome.getText())
                .append("categoria", cmbCategoria.getSelectedItem().toString())
                .append("estado", cmbEstado.getSelectedItem().toString())
                .append("quantidade", txtQuantidade.getText())
                .append("preco", txtPreco.getText());

        collection.insertOne(document);
        mongoClient.close();
    }
}