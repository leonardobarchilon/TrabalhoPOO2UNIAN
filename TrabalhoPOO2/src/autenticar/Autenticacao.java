/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package autenticar;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import bd.ConexaoMongoDB;

import org.bson.Document;

/**
 * Esta classe representa a autenticação de usuários em um banco de dados.
 */
public class Autenticacao {

    private ConexaoMongoDB conexaoMongoDB;

    /**
     * Constrói uma instância da classe Autenticacao.
     * @param conexaoMongoDB a conexão com o banco de dados MongoDB
     */
    public Autenticacao(ConexaoMongoDB conexaoMongoDB) {
        this.conexaoMongoDB = conexaoMongoDB;
    }

    /**
     * Autentica um usuário com base no nome e senha fornecidos.
     * @param nome o nome do usuário
     * @param senha a senha do usuário
     * @return true se a autenticação for bem-sucedida, false caso contrário
     */
    public boolean autenticarUsuario(String nome, String senha){
        MongoDatabase database = conexaoMongoDB.getDatabase();
        MongoCollection<Document> collection = conexaoMongoDB.getCollection();

        BasicDBObject query = new BasicDBObject();
        query.put("nome", nome);

        FindIterable<Document> cursor = collection.find(query);

        if(cursor.iterator().hasNext()){
            Document user = cursor.iterator().next();

            String senhaUsuario = user.getString("senha");

            if(senha.equals(senhaUsuario)){
                return true;
            }
        }
        return false;
    }  
}


