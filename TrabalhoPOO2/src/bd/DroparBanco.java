/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package bd;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DroparBanco {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public DroparBanco() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("ProjetoPOO2");

        database.drop();
    }
}