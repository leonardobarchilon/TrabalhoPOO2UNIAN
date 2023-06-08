package bd;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.types.ObjectId;

public class ConexaoMongoDB {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    
    public ConexaoMongoDB() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("ProjetoPOO2");
        collection = database.getCollection("usuarios");
    }
    
    public void insereUsuario(int id, String nome, String senha){
        Document doc = new Document("id", id).append("nome",nome)
                .append("senha",senha);
        collection.insertOne(doc);
    }
    
    public void exibeUsuario(){
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
            Document atual = cursor.next();
        
            System.out.print(atual.get("_id") + ", ");
            System.out.print(atual.get("nome") + ", ");
            
        }
        } finally {
            cursor.close();
        }
    }
    
    public void exibeUsuario(String nome){
        MongoCursor<Document> cursor = collection.find(eq("nome",nome)).iterator();
        try {
            while (cursor.hasNext()) {
                Document atual = cursor.next();
                System.out.print(atual.get("_id") + ", ");
                System.out.print(atual.get("nome") + ", ");
            }
        } finally {
            cursor.close();
        }
    }
    
    public void alteraUsuario(String nomeAntigo, String nomeNovo){
        collection.updateOne(       eq("nome", nomeAntigo), 
                                    new Document("$set",new Document("nome", nomeNovo)));
    }
    
        
    public void alteraUsuario(String nome, String chave, Object valor){
        collection.updateOne(       eq("nome", nome), 
                                    new Document("$set",new Document(chave, valor)));
    }
      
    public void alteraUsuario(String chaveBusca, Object valorBusca, String chave, Object valor){
        collection.updateOne(       eq(chaveBusca, valorBusca), 
                                    new Document("$set",new Document(chave, valor)));
    }
    
    public void alteraUsuario(ObjectId id, String nomeNovo){
        collection.updateOne(eq("_id", id), new
                Document("$set", new Document("nome", nomeNovo)));
    }
    
    public void removeUsuario(String nome){
        collection.deleteOne(eq("nome", nome));
    }
    
    public void removeUsuario(ObjectId id){
        collection.deleteOne(eq("_id", id));
    }

    private void insereFornecedor(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("ProjetoPOO2");
        MongoCollection<Document> collection = database.getCollection("fornecedor");

        Document document = new Document("nome", "Fornecedor 1");

        collection.insertOne(document);
        mongoClient.close();
    }
}