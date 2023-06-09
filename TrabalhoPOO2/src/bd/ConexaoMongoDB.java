/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package bd;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


/**
 * Esta classe representa uma conexão com o banco de dados MongoDB.
 */
public class ConexaoMongoDB {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    
    /**
     * Constrói uma instância da classe ConexaoMongoDB.
     * Estabelece a conexão com o banco de dados MongoDB.
     */
    public ConexaoMongoDB() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("ProjetoPOO2");
        collection = database.getCollection("usuario");
    }

    /**
     * Obtém o objeto MongoDatabase para o banco de dados atualmente conectado.
     * @return o objeto MongoDatabase
     */
    public MongoDatabase getDatabase() {
        return database;
    }

    /**
     * Obtém o objeto MongoCollection para a coleção atualmente conectada.
     * @return o objeto MongoCollection
     */
    public MongoCollection<Document> getCollection() {
        return collection;
    }
    
}