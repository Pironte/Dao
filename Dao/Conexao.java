/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Cliente
 */
public class Conexao {
    private static String connectionString = "jdbc:postgresql://localhost:5432/mercado";
    private static String usuario = "usuario";
    private static String senha = "senha";
    
    public static Connection conectar(){
       try{
           Class.forName("org.postgresql.Driver");
           return DriverManager.getConnection(connectionString, usuario, senha);
       }catch(Exception ex){
           System.out.println("Erro ao conectar: " +ex.getMessage());
           return null;
       }
       
    }
    
    public static void desconectar(Connection con){
        try{
            con.close();
        }catch(SQLException ex){
            System.out.println("Erro ao desconectar");
        }
    }
}
