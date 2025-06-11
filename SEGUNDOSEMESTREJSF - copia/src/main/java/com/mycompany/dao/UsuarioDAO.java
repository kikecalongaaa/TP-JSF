/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author enriq
 */

import com.mycompany.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {

   private final String URL = "jdbc:sqlite:C:/DB/TICKETS.db";


    public void insertar(Usuario usuario) throws Exception {
        String sql = "INSERT INTO usuarios (nombre_usuario, email, contraseña, rol_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(sql)) {
            System.out.println("✅ Conexión establecida con: " + URL);


            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getContrasena());
            stmt.setInt(4, usuario.getRolId());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Verificar la conexion con la base de datos , debe estar en C:\\DB " + e.getMessage(), e);
        }
    }
    
    
     public Usuario login(String email, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND contraseña = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setEmail(rs.getString("email"));
                u.setContrasena(rs.getString("contraseña"));
                u.setRolId(rs.getInt("rol_id"));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     
     public List<Usuario> listarTodos() throws Exception {
    List<Usuario> lista = new ArrayList<>();
    String sql = "SELECT * FROM usuarios";

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("id_usuario"));
            u.setNombreUsuario(rs.getString("nombre_usuario"));
            u.setEmail(rs.getString("email"));
            u.setContrasena(rs.getString("contraseña"));
            u.setRolId(rs.getInt("rol_id"));
            lista.add(u);
        }

    } catch (SQLException e) {
        throw new Exception("Error al listar usuarios: " + e.getMessage(), e);
    }

    return lista;
}

public void eliminar(int idUsuario) throws Exception {
    String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);
        stmt.executeUpdate();

    } catch (SQLException e) {
        throw new Exception("Error al eliminar usuario: " + e.getMessage(), e);
    }
}

}
