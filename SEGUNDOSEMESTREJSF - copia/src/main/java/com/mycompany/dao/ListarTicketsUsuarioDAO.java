/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author enriq
 */
import com.mycompany.model.TicketUsuario;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListarTicketsUsuarioDAO {
    private final String URL = "jdbc:sqlite:C:/DB/TICKETS.db";

    public List<TicketUsuario> obtenerTicketsPorUsuario(int usuarioId) throws Exception {
        List<TicketUsuario> lista = new ArrayList<>();

        String sql = "SELECT t.id_ticket, t.titulo, t.descripcion, t.fecha_creacion, " +
             "e.nombre AS estado_nombre, c.nombre AS categoria_nombre, t.prioridad " +
             "FROM tickets t " +
             "JOIN estados e ON t.estado_id = e.id_estado " +
             "JOIN categorias c ON t.categoria_id = c.id_categoria " +
             "WHERE t.usuario_creador_id = ? " +
             "ORDER BY t.fecha_creacion DESC";


        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TicketUsuario ticket = new TicketUsuario();
                    ticket.setIdTicket(rs.getInt("id_ticket"));
                    ticket.setTitulo(rs.getString("titulo"));
                    ticket.setDescripcion(rs.getString("descripcion"));
                    ticket.setFechaCreacion(LocalDate.parse(rs.getString("fecha_creacion")));
                    ticket.setEstadoNombre(rs.getString("estado_nombre"));
                    ticket.setCategoriaNombre(rs.getString("categoria_nombre"));
                    ticket.setPrioridad(rs.getString("prioridad"));
                    lista.add(ticket);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al listar tickets del usuario: " + e.getMessage(), e);
        }

        return lista;
    }
}

