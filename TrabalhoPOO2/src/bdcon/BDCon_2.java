/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdcon;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author professores
 */
public class BDCon_2 {
  
    
    public static void insereFornecedor(String nome, int telefone, String forma, String endereco, float reputacao) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String urlJDBC = "jdbc:mysql://localhost:3306/cadhard";
        Connection conexao = (Connection) DriverManager.getConnection(urlJDBC,
                                                        "root", 
                                                "anhanguera");
       
        Statement comando = (Statement) conexao.createStatement();
        
        comando.execute("INSERT INTO fornecedor_hardware (nome_fornecedor, telefone_fornecedor, forma_contato, endereco_fornecedor, reputacao_fornecedor) VALUES"
                + "('"+nome+"','"+telefone+"','"+forma+"','"+endereco+"','"+reputacao+"')");
        
        
        conexao.close();
        
    }
}
