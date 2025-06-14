/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author enriq
 */
import com.mycompany.model.Ticket;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class TicketDAO {
    private final String URL = "jdbc:sqlite:C:/DB/TICKETS.db";

    public List<Ticket> listarPorUsuario(int usuarioId) throws Exception {
    String sql = "SELECT * FROM tickets WHERE usuario_creador_id = ? ORDER BY estado_id = 1 desc";
    //String sql = "SELECT * FROM tickets WHERE usuario_creador_id = ?";
    List<Ticket> lista = new java.util.ArrayList<>();

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, usuarioId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Ticket ticket = new Ticket();
            ticket.setIdTicket(rs.getInt("id_ticket"));
            ticket.setTitulo(rs.getString("titulo"));
            ticket.setDescripcion(rs.getString("descripcion"));
            ticket.setFechaCreacion(LocalDate.parse(rs.getString("fecha_creacion")));
            ticket.setEstadoId(rs.getInt("estado_id"));
            ticket.setCategoriaId(rs.getInt("categoria_id"));
            ticket.setPrioridad(rs.getString("prioridad"));

            lista.add(ticket);
        }

    } catch (Exception e) {
        throw new Exception("Error al listar tickets por usuario: " + e.getMessage(), e);
    }

    return lista;
}
    public List<Ticket> listarTodos() throws Exception {
    String sql = "SELECT * FROM tickets";
    List<Ticket> lista = new java.util.ArrayList<>();

    try (Connection conn = DriverManager.getConnection(URL);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            Ticket ticket = new Ticket();
            ticket.setIdTicket(rs.getInt("id_ticket"));
            ticket.setTitulo(rs.getString("titulo"));
            ticket.setDescripcion(rs.getString("descripcion"));
            ticket.setFechaCreacion(LocalDate.parse(rs.getString("fecha_creacion")));
            ticket.setEstadoId(rs.getInt("estado_id"));
            ticket.setUsuarioCreadorId(rs.getInt("usuario_creador_id"));
            ticket.setCategoriaId(rs.getInt("categoria_id"));
            ticket.setPrioridad(rs.getString("prioridad"));

            lista.add(ticket);
        }

    } catch (Exception e) {
        throw new Exception("Error al listar todos los tickets: " + e.getMessage(), e);
    }

    return lista;
}
    
    public void insertar(Ticket ticket) throws Exception {
        String sql = "INSERT INTO tickets (titulo, descripcion, fecha_creacion, estado_id, usuario_creador_id, categoria_id, prioridad) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println("✅ Conexión establecida con: " + URL);

            stmt.setString(1, ticket.getTitulo());
            stmt.setString(2, ticket.getDescripcion());
            stmt.setString(3, ticket.getFechaCreacion().toString());
            stmt.setInt(4, ticket.getEstadoId()); // Debe ser el ID de "ABIERTO"
            stmt.setInt(5, ticket.getUsuarioCreadorId());
            stmt.setInt(6, ticket.getCategoriaId());
            stmt.setString(7, ticket.getPrioridad());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new Exception("Error al insertar ticket. Verifica conexión a la BD en C:\\DB. " + e.getMessage(), e);
        }
    }
}
