/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package GUI.fornecedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Esta classe representa a tela de adicionar fornecedor.
 */
public class AdicionarFornecedor extends JFrame {

    public JLabel lblNome, lblTelefone, lblForma, lblEndereco, lblReputacao;
    public JTextField txtNome;
    public JTextField txtTelefone;
    public JTextField txtForma;
    public JTextField txtEndereco;
    public JTextField txtReputacao;
    public JComboBox cmbForma;
    public JButton btnEnviar, bntVoltar;

    private String[] formaContato = { "WhatsApp", "Ligação" };

    /**
     * Constrói uma instância da classe AdicionarFornecedor.
     * Configura os componentes da tela e define as ações dos botões.
     */
    public AdicionarFornecedor(){

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
        bntVoltar = new JButton("Voltar");

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insereFornecedor();
                dispose();
            }
        });

        bntVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFornecedor();
                dispose();
            }
        });

        // Configurações de posicionamento dos componentes
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
        btnEnviar.setBounds(115, 180, 200, 50);
        bntVoltar.setBounds(160, 250, 100, 50);

        // Adiciona os componentes ao painel
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
        getContentPane().add(bntVoltar);

        // Especificações da Tela
        setSize(450, 350);
        setTitle("Adicionar Fornecedor");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Insere um novo fornecedor no banco de dados com base nos dados fornecidos na tela.
     */
    public void insereFornecedor(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("ProjetoPOO2");
        MongoCollection<Document> collection = database.getCollection("fornecedor");

        Document document = new Document("fornecedor", txtNome.getText())
                .append("telefone", txtTelefone.getText())
                .append("forma", cmbForma.getSelectedItem().toString())
                .append("endereco", txtEndereco.getText())
                .append("reputacao", txtReputacao.getText());

        collection.insertOne(document);
        mongoClient.close();
        new AdicionarFornecedor();
        dispose();
    }
}