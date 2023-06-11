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
import com.mongodb.client.model.Filters;

import bd.FornecedorBD;

/**
 * Esta classe representa a tela de exclusão de fornecedores.
 */
public class DeletarFornecedor extends JFrame {

    private FornecedorBD fornecedorBD;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton buttonExcluir;
    private JButton buttonVoltar;

    /**
     * Constrói uma instância da classe DeletarFornecedor.
     * Configura os componentes da tela e define as ações dos botões.
     */
    public DeletarFornecedor() {
        fornecedorBD = new FornecedorBD();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Excluir Fornecedor");

        JPanel panel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Fornecedor");
        tableModel.addColumn("Telefone");
        tableModel.addColumn("Forma de contato");
        tableModel.addColumn("Endereço");
        tableModel.addColumn("Reputação");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        preencherTabelaFornecedores();

        buttonExcluir = new JButton("Excluir");
        buttonExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirFornecedorSelecionado();
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
        setTitle("Deletar Fornecedor");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Preenche a tabela com os fornecedores existentes no banco de dados.
     */
    private void preencherTabelaFornecedores() {
        tableModel.setNumRows(0);

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
     * Obtém o valor de um campo do documento como uma string.
     * Se o valor for nulo, retorna uma string vazia.
     * 
     * @param document o documento
     * @param field    o nome do campo
     * @return o valor do campo como uma string
     */
    private String getStringValue(Document document, String field) {
        Object value = document.get(field);
        return value != null ? value.toString() : "";
    }

    /**
     * Exclui o fornecedor selecionado na tabela.
     * Se nenhum fornecedor estiver selecionado, exibe uma mensagem de erro.
     */
    private void excluirFornecedorSelecionado() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um fornecedor para excluir");
            return;
        }

        String id = table.getValueAt(row, 0).toString();
        ObjectId objectId = new ObjectId(id);

        fornecedorBD.getCollection().deleteOne(Filters.eq("_id", objectId));

        preencherTabelaFornecedores();

        JOptionPane.showMessageDialog(this, "Fornecedor excluído com sucesso");
    }
}
