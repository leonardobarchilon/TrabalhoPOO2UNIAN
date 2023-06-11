/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package GUI.hardware;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import bd.HardwareBD;

/**
 * Esta classe representa a tela de busca de hardwares.
 */
public class BuscarHardware extends JFrame {

    private HardwareBD hardwareBD;
    private JTextArea textArea;
    private JButton buttonVoltar;
    private TextField textBuscar;

    /**
     * Constrói uma instância da classe BuscarHardware.
     * Configura os componentes da tela e define as ações do botão de busca.
     */
    public BuscarHardware() {
        hardwareBD = new HardwareBD();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Buscar hardwares");

        setLayout(new BorderLayout());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        preencherListaHardware();

        textBuscar = new TextField("Buscar");
        textBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarHardware();
            }
        });
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuHardware();
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

    /**
     * Preenche a lista com os dados dos hardwares.
     */
    private void preencherListaHardware() {
        StringBuilder builder = new StringBuilder();

        MongoCollection<Document> collection = hardwareBD.getCollection();
        collection.find().forEach((Consumer<Document>) (documento) -> {
            builder.append(documento.toString()).append("\n");
        });

        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        textArea.setText(builder.toString());
    }

    /**
     * Realiza a busca de hardwares e exibe os resultados no JTextArea.
     */
    private void buscarHardware() {
        String busca = textBuscar.getText();

        if (busca.isEmpty()) {
            textArea.setText("");
            preencherListaHardware();
            return;
        }

        StringBuilder builder = new StringBuilder();

        MongoCollection<Document> collection = hardwareBD.getCollection();
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
            JOptionPane.showMessageDialog(this, "Nenhum Hardware encontrado");
        }

        textArea.setText(builder.toString());
    }
}
