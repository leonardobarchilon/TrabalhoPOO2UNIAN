/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package GUI.hardware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.model.Filters;

import GUI.fornecedor.MenuFornecedor;
import bd.HardwareBD;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoCollection;

/**
 * Esta classe representa a tela de atualização de um hardware.
 */
public class AtualizarHardware extends JFrame {

    private HardwareBD hardwareBD;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton buttonAtualizar, buttonVoltar;

    /**
     * Constrói uma instância da classe AtualizarHardware.
     * Configura os componentes da tela e define as ações do botão.
     */
    public AtualizarHardware() {
        hardwareBD = new HardwareBD();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Atualizar hardwares");

        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("nome");
        tableModel.addColumn("categoria");
        tableModel.addColumn("estado");
        tableModel.addColumn("quantidade");
        tableModel.addColumn("preco");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        preencherTabelaHardware();

        buttonAtualizar = new JButton("Atualizar");
        buttonAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCampoSelecionado();
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
        panelButtons.add(buttonAtualizar);
        panelButtons.add(buttonVoltar);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelButtons, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);

        pack();

        // Especificações da Tela
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Preenche a tabela com os dados dos hardwares.
     */
    private void preencherTabelaHardware() {
        tableModel.setNumRows(0);

        MongoCollection<Document> collection = hardwareBD.getCollection();
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                String id = document.get("_id").toString();
                String nome = getStringValue(document, "nome");
                String categoria = getStringValue(document, "categoria");
                String estado = getStringValue(document, "estado");
                String quantidade = getStringValue(document, "quantidade");
                String preco = getStringValue(document, "preco");
                tableModel.addRow(new Object[] { id, nome, categoria, estado, quantidade, preco });
            }
        } finally {
            cursor.close();
        }
    }

    /**
     * Obtém o valor de um campo como uma String a partir de um Document.
     *
     * @param document  o Document que contém os dados
     * @param fieldName o nome do campo
     * @return o valor do campo como uma String, ou uma String vazia se o valor for
     *         nulo
     */
    private String getStringValue(Document document, String fieldName) {
        Object value = document.get(fieldName);
        if (value != null) {
            return value.toString();
        } else {
            return "";
        }
    }

    /**
     * Atualiza o campo selecionado para o hardware selecionado na tabela.
     */
    private void atualizarCampoSelecionado() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um hardware para atualizar");
            return;
        }

        String id = table.getValueAt(row, 0).toString();

        String[] opcoes = { "nome", "categoria", "estado", "quantidade", "preco" };
        String campoSelecionado = (String) JOptionPane.showInputDialog(this, "Selecione o campo a ser atualizado:",
                "Atualizar Hardware",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (campoSelecionado == null) {
            return;
        }

        String novoValor = JOptionPane.showInputDialog(this, "Digite o novo valor para " + campoSelecionado + ":");
        if (novoValor == null) {
            return;
        }

        MongoCollection<Document> collection = hardwareBD.getCollection();
        ObjectId objectId = new ObjectId(id);

        Document filtro = new Document("_id", objectId);
        Document atualizacao = new Document("$set", new Document(campoSelecionado.toLowerCase(), novoValor));
        collection.updateOne(filtro, atualizacao);

        preencherTabelaHardware();

        JOptionPane.showMessageDialog(this, "Campo atualizado com sucesso");
    }

}
