/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author enriq
 */


import com.mycompany.model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private final String URL = "jdbc:sqlite:C:/DB/TICKETS.db";

    public void insertar(Categoria categoria) throws Exception {
        String sql = "INSERT INTO categorias (nombre_categoria) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println("✅ Conexión establecida con: " + URL);
            stmt.setString(1, categoria.getNombreCategoria());
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new Exception("Verificar la conexión con la base de datos. Debe estar en C:\\DB. " + e.getMessage(), e);
        }
    }

    public List<Categoria> listar() throws Exception {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombreCategoria(rs.getString("nombre_categoria"));
                lista.add(c);
            }

        } catch (Exception e) {
            throw new Exception("Error al listar categorías: " + e.getMessage(), e);
        }

        return lista;
    }
    public void eliminar(int idCategoria) throws Exception {
    String sql = "DELETE FROM categorias WHERE id_categoria = ?";

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idCategoria);
        stmt.executeUpdate();

    } catch (Exception e) {
        throw new Exception("Error al eliminar la categoría: " + e.getMessage(), e);
    }
}
}



