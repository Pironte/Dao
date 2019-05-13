/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



/**
 *
 * @author Cliente
 */
public class ClasseDao implements IDao {
    private Connection con;
    private PreparedStatement cmd;
    private ResultSet rs;
    @Override
    public int inserir(Contato contato) {
        try{
            String sql = "INSERT INTO tb_contatos(nome,email,telefone) VALUES(?,?,?);";
            con  = Conexao.conectar();
            cmd = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            cmd.setString(1,contato.getNome());
            cmd.setString(2, contato.getEmail());
            cmd.setString(3, contato.getTelefone());
            if(cmd.executeUpdate() > 0){
                rs = cmd.getGeneratedKeys();
                return rs.next() ? rs.getInt(1) : -1;
            }
            return -1;
       }catch(SQLException e){
           System.out.println("Erro"+ e.getMessage());
           return -1;
       } finally{
           Conexao.desconectar(con);
       }
    }

    @Override
    public int deletar(Contato contato) {
       try{
            String sql = "DELETE FROM tb_contatos WHERE id = ?;";
            con  = Conexao.conectar();
            cmd = con.prepareStatement(sql);
            cmd.setInt(1,contato.getId());
            if(cmd.executeUpdate()>0){
            return contato.getId();
            }
            return -1;
        
       }catch(SQLException e){
           System.out.println("Erro"+e.getMessage());
           return -1;
       }finally{
            Conexao.desconectar(con);
       }
      
    }

    @Override
    public int atualizar(Contato contato) {
        try{
            String sql = "UPDATE tb_contatos SET nome=?,email=?,telefone=?"+" WHERE id=?";
            con  = Conexao.conectar();
            cmd = con.prepareStatement(sql);
            cmd.setString(1,contato.getNome());
            cmd.setString(2, contato.getEmail());
            cmd.setString(3, contato.getTelefone());
            cmd.setInt(4,contato.getId());
            if(cmd.executeUpdate() > 0){
                return contato.getId();
            }
            return -1;
       }catch(SQLException e){
           System.out.println("Erro"+ e.getMessage());
           return -1;
       } finally{
           Conexao.desconectar(con);
       }
    }

    @Override
    public ArrayList<Contato> listar() {
        try{
            ArrayList<Contato> contatos = new ArrayList();
            String sql = "SELECT id,nome,email,telefone FROM tb_contatos;";
            con  = Conexao.conectar();
            cmd = con.prepareStatement(sql);
            rs = cmd.executeQuery();
            while(rs.next()){
                Contato c = new Contato();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                contatos.add(c);
               
            }
             return contatos;
       }catch(SQLException e){
           System.out.println("Erro" + e.getMessage());
           return null;
       }finally{
           Conexao.desconectar(con);
       }
    }

    @Override
    public Contato pesquisarPorId(int id) {
        try{
            
            String sql = "SELECT cod_usuario,nome,login,senha WHERE id = ?;";
            con  = Conexao.conectar();
            cmd = con.prepareStatement(sql);
            rs = cmd.executeQuery();
            cmd.setInt(1, id);
            if(rs.next()){   
                Contato c = new Contato();
                c.setId(rs.getInt("cod_usuario"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                return c;
               
            }else{
                return null;
            }
             
       }catch(SQLException e){
           System.out.println("Erro" + e.getMessage());
           return null;
       }finally{
           Conexao.desconectar(con);
       }
    }

    @Override
    public ArrayList<Contato> pesquisarPorNome(String nome) {
        try{
            ArrayList<Contato> contatos = new ArrayList();
            String sql = "SELECT id,nome,email,telefone WHERE nome like ? ;";
            con  = Conexao.conectar();
            cmd = con.prepareStatement(sql);
            cmd.setString(1,"%"+nome+"%");
            rs = cmd.executeQuery();
            while(rs.next()){
                Contato c = new Contato();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                contatos.add(c);
               
            }
             return contatos;
       }catch(SQLException e){
           System.out.println("Erro" + e.getMessage());
           return null;
       }finally{
           Conexao.desconectar(con);
       }

    

    
    }
}
