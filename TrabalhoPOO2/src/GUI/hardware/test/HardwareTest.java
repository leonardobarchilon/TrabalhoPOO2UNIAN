/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package GUI.hardware.test;

import static org.junit.Assert.assertTrue;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import GUI.hardware.AdicionarHardware;

public class HardwareTest {
    private AdicionarHardware adicionarHardware;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @Before
    public void setUp() {
        adicionarHardware = new AdicionarHardware();
        // Conecta ao banco de dados de teste
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("ProjetoPOO2");
        collection = database.getCollection("hardware");
    }

    @After
    public void tearDown() {
        adicionarHardware.dispose();
        // Limpa os dados de teste no banco de dados
        collection.drop();
        mongoClient.close();
    }

    @Test
    public void testInsereHardware() {
        
        adicionarHardware.insereHardware();

        // Verifica se o hardware foi inserido corretamente no banco de dados
        Document result = collection.find().first();
        assertTrue("hardware não foi inserido corretamente no banco de dados", result != null);
    }

}
