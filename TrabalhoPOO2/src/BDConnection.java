/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author professores
 */
public class BDConnection {
  
    
    public static void insereInformacao(String nome, String categoria, String estado, int quantidade, float preco) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String urlJDBC = "jdbc:mysql://localhost:3306/cadhard";
        Connection conexao = (Connection) DriverManager.getConnection(urlJDBC,
                                                        "root", 
                                                "anhanguera");
       
        Statement comando = (Statement) conexao.createStatement();
        
        comando.execute("INSERT INTO informacao_hardware (nome_peca, categoria, estado, quantidade, preco) VALUES"
                + "('"+nome+"','"+categoria+"','"+estado+"','"+quantidade+"','"+preco+"')");
        
        conexao.close();
        
    }
}
