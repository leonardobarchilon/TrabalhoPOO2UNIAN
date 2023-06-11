/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package GUI.fornecedor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import GUI.hardware.MenuHardware;
import bd.FornecedorBD;
import java.util.function.Consumer;

/**
 * Esta classe representa a tela de busca de fornecedores.
 */
public class BuscarFornecedor extends JFrame {

    private FornecedorBD fornecedorBD;
    private JTextArea textAreaResultado;
    private JButton buttonVoltar;
    private JTextField textBuscar;

    /**
     * Constrói uma instância da classe BuscarFornecedor.
     * Configura os componentes da tela e define as ações dos botões.
     */
    public BuscarFornecedor() {
        fornecedorBD = new FornecedorBD();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Buscar Fornecedor");

        textAreaResultado = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textAreaResultado);

        preencherListaFornecedor();

        textBuscar = new JTextField("Buscar");
        textBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarFornecedor();
            }
        });
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFornecedor();
                dispose();
            }
        });

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButtons.add(textBuscar);
        panelButtons.add(buttonVoltar);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelButtons, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        pack();

        // Especificações da Tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void preencherListaFornecedor() {
        StringBuilder builder = new StringBuilder();

        MongoCollection<Document> collection = fornecedorBD.getCollection();
        collection.find().forEach((Consumer<Document>) (documento) -> {
            builder.append(documento.toString()).append("\n");
        });

        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        textAreaResultado.setText(builder.toString());
    }
    /**
     * Realiza a busca de fornecedores no banco de dados e exibe o resultado na área de texto.
     */
    private void BuscarFornecedor() {
        String busca = textBuscar.getText();

        if (busca.isEmpty()) {
            textAreaResultado.setText("");
            preencherListaFornecedor();
            return;
        }

        StringBuilder builder = new StringBuilder();

        MongoCollection<Document> collection = fornecedorBD.getCollection();
        collection.find().forEach((Consumer<Document>) (documento) -> {
            String documentoString = documento.toString();
            if (documentoString.contains(busca)) {
                builder.append(documentoString).append("\n");
            }
        });

        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        if (builder.length() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum Fornecedor encontrado");
        }

        textAreaResultado.setText(builder.toString());
    }

    public static void main(String[] args) {
        new BuscarFornecedor();
    }
}
