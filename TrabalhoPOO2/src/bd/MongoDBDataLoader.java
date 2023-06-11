/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package bd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class MongoDBDataLoader {

    public MongoDBDataLoader() {
        // Configurar detalhes de conexão com o MongoDB usando a classe ConexaoMongoDB
        ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();
        String collectionName = "usuario";
        String currentDirectory = System.getProperty("user.dir");
        String filePath = currentDirectory + "\\src\\bd\\usuarios.txt";

        // Obter o objeto MongoCollection para a coleção desejada
        MongoCollection<Document> collection = conexaoMongoDB.getCollection();

        // Ler o arquivo de texto e inserir os dados no MongoDB
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Extrair os campos do registro
                String[] fields = line.split(",");

                // Criar um documento do MongoDB com os campos
                Document document = new Document()

                        .append("nome", fields[0])
                        .append("senha", fields[1]);

                // Inserir o documento no MongoDB
                collection.insertOne(document);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
