/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package GUI.fornecedor;

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

import bd.FornecedorBD;

/**
 * Esta classe representa a tela de atualização de fornecedores.
 */
public class AtualizarFornecedor extends JFrame {

    private FornecedorBD fornecedorBD;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton buttonAtualizar, buttonVoltar;

    /**
     * Constrói uma instância da classe AtualizarFornecedor.
     * Configura os componentes da tela e define as ações dos botões.
     */
    public AtualizarFornecedor() {
        fornecedorBD = new FornecedorBD();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Atualizar Fornecedores");

        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("fornecedor");
        tableModel.addColumn("telefone");
        tableModel.addColumn("forma");
        tableModel.addColumn("endereco");
        tableModel.addColumn("reputacao");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        preencherTabelaFornecedores();

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
                new MenuFornecedor();
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
     * Preenche a tabela de fornecedores com os dados do banco de dados.
     */
    private void preencherTabelaFornecedores() {
        tableModel.setRowCount(0);

        MongoCollection<Document> collection = fornecedorBD.getCollection();
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                String id = document.get("_id").toString();
                String nome = getStringValue(document, "fornecedor");
                String telefone = getStringValue(document, "telefone");
                String formaContato = getStringValue(document, "forma");
                String endereco = getStringValue(document, "endereco");
                String reputacao = getStringValue(document, "reputacao");
                tableModel.addRow(new Object[] { id, nome, telefone, formaContato, endereco, reputacao });
            }
        } finally {
            cursor.close();
        }
    }

    /**
     * Obtém o valor de uma propriedade do documento como uma string.
     * Se o valor for nulo, retorna uma string vazia.
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
     * Atualiza o campo selecionado do fornecedor selecionado na tabela.
     */
    private void atualizarCampoSelecionado() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um fornecedor para atualizar");
            return;
        }

        String id = table.getValueAt(row, 0).toString();

        String[] opcoes = { "fornecedor", "telefone", "forma", "endereco", "reputacao" };
        String campoSelecionado = (String) JOptionPane.showInputDialog(this, "Selecione o campo a ser atualizado:",
                "Atualizar Fornecedor",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (campoSelecionado == null) {
            return;
        }

        String novoValor = JOptionPane.showInputDialog(this, "Digite o novo valor para " + campoSelecionado + ":");
        if (novoValor == null) {
            return;
        }

        MongoCollection<Document> collection = fornecedorBD.getCollection();
        ObjectId objectId = new ObjectId(id);

        Document filtro = new Document("_id", objectId);
        Document atualizacao = new Document("$set", new Document(campoSelecionado.toLowerCase(), novoValor));
        collection.updateOne(filtro, atualizacao);

        preencherTabelaFornecedores();

        JOptionPane.showMessageDialog(this, "Campo atualizado com sucesso");
    }
}
