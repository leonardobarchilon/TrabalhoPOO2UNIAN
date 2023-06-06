import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;

public class UsuarioBd {
    public static String URL = "jdbc:mysql://localhost:3306/usuario";

    public static void insereUsuario(Usuario usuario){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexao = (Connection) DriverManager.getConnection(URL, "root", "anhanguera");
            Statement comando = (Statement) conexao.createStatement();
            comando.execute("INSERT INTO usuario (nome, cpf, email) VALUES ('"+usuario.getNome()+"','"+usuario.getCpf()+"','"+usuario.getEmail()+"')");
            conexao.close();
        }catch(Exception e){
            System.out.println("Erro: "+e.getMessage());
        }
    }
}
