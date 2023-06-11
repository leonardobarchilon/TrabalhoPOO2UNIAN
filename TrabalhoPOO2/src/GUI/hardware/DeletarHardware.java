/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package GUI.hardware;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import GUI.fornecedor.MenuFornecedor;
import bd.HardwareBD;

/**
 * Esta classe representa a tela de exclusão de hardwares.
 */
public class DeletarHardware extends JFrame {

    private HardwareBD hardwareBD;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton buttonExcluir;
    private JButton buttonVoltar;

    /**
     * Constrói uma instância da classe DeletarHardware.
     * Configura os componentes da tela e define as ações do botão de exclusão.
     */
    public DeletarHardware() {
        hardwareBD = new HardwareBD();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Excluir Hardware");

        JPanel panel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Categoria");
        tableModel.addColumn("Estado");
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Preço");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        preencherTabelaHardware();

        buttonExcluir = new JButton("Excluir");
        buttonExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirHardwareSelecionado();
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

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(buttonExcluir);
        buttonPanel.add(buttonVoltar);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);

        pack();
        // Especificações da Tela
        setSize(600, 500);
        setTitle("Deletar Hardware");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Preenche a tabela de hardwares com os registros do banco de dados.
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
     * Obtém o valor de um campo em um documento, convertendo-o para String.
     *
     * @param document o documento
     * @param field o nome do campo
     * @return o valor do campo como uma String, ou uma String vazia se o campo for nulo
     */
    private String getStringValue(Document document, String field) {
        Object value = document.get(field);
        return value != null ? value.toString() : "";
    }

    /**
     * Exclui o hardware selecionado na tabela.
     */
    private void excluirHardwareSelecionado() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um Hardware para excluir");
            return;
        }

        String id = table.getValueAt(row, 0).toString();
        ObjectId objectId = new ObjectId(id);

        hardwareBD.getCollection().deleteOne(Filters.eq("_id", objectId));

        preencherTabelaHardware();

        JOptionPane.showMessageDialog(this, "Hardware excluído com sucesso");
    }
}
