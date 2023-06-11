/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package bd;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Esta classe representa uma conexão com o banco de dados MongoDB para a coleção "fornecedor".
 */
public class FornecedorBD {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    
    /**
     * Constrói uma instância da classe FornecedorBD.
     * Estabelece a conexão com o banco de dados MongoDB e a coleção "fornecedor".
     */
    public FornecedorBD() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("ProjetoPOO2");
        collection = database.getCollection("fornecedor");
    }

    /**
     * Obtém o objeto MongoDatabase para o banco de dados atualmente conectado.
     * @return o objeto MongoDatabase
     */
    public MongoDatabase getDatabase() {
        return database;
    }

    /**
     * Obtém o objeto MongoCollection para a coleção "fornecedor".
     * @return o objeto MongoCollection
     */
    public MongoCollection<Document> getCollection() {
        return collection;
    }

    
    
}