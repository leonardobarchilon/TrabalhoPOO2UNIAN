/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package GUI.fornecedor.test;

import static org.junit.Assert.assertTrue;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import GUI.fornecedor.AdicionarFornecedor;

public class FornecedorTest {

    private AdicionarFornecedor adicionarFornecedor;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @Before
    public void setUp() {
        adicionarFornecedor = new AdicionarFornecedor();
        // Conecta ao banco de dados de teste
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("ProjetoPOO2");
        collection = database.getCollection("fornecedor");
    }

    @After
    public void tearDown() {
        adicionarFornecedor.dispose();
        // Limpa os dados de teste no banco de dados
        collection.drop();
        mongoClient.close();
    }

    @Test
    public void testInsereFornecedor() {
        // Executa o método insereFornecedor()
        adicionarFornecedor.insereFornecedor();

        // Verifica se o fornecedor foi inserido corretamente no banco de dados
        Document result = collection.find().first();
        assertTrue("Fornecedor não foi inserido corretamente no banco de dados", result != null);
    }

}
