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

public class HardwareDataLoader {

    public HardwareDataLoader(){
        // Configurar detalhes de conexão com o MongoDB usando a classe ConexaoMongoDB
        HardwareBD hardwareBD = new HardwareBD();
        String collectionName = "hardware";
        String currentDirectory = System.getProperty("user.dir");
        String filePath = currentDirectory + "\\src\\bd\\hardwares.txt";

        // Obter o objeto MongoCollection para a coleção desejada
        MongoCollection<Document> collection = hardwareBD.getCollection();

        // Ler o arquivo de texto e inserir os dados no MongoDB
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Extrair os campos do registro
                String[] fields = line.split(",");

                // Criar um documento do MongoDB com os campos
                Document document = new Document()

                        .append("nome", fields[0])
                        .append("categoria", fields[1])
                        .append("estado", fields[2])
                        .append("quantidade", fields[3])
                        .append("preco", fields[4]);

                // Inserir o documento no MongoDB
                collection.insertOne(document);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
