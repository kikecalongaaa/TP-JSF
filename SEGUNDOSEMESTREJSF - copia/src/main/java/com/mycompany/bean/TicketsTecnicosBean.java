/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bean;

/**
 *
 * @author enriq
 */
import com.mycompany.dao.TicketDAO;
import com.mycompany.model.Ticket;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("ticketsTecnicosBean")
@SessionScoped
public class TicketsTecnicosBean implements Serializable {

    private List<Ticket> listaTickets;

    public TicketsTecnicosBean() {
        cargarTickets();
    }

    public void cargarTickets() {
        try {
            TicketDAO dao = new TicketDAO();
            listaTickets = dao.listarTodos(); // Este método lo vamos a crear ahora
        } catch (Exception e) {
            // Manejo básico de errores
            System.err.println("Error al cargar tickets: " + e.getMessage());
        }
    }

    public List<Ticket> getListaTickets() {
        return listaTickets;
    }

    public String nombreEstado(int idEstado) {
        switch (idEstado) {
            case 1: return "Abierto";
            case 2: return "En Proceso";
            case 3: return "Cerrado";
            case 4: return "Rechazado";
            default: return "Desconocido";
        }
    }

    public String verTicket(Ticket ticket) {
        // Guardamos el ticket seleccionado en sesión
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ticketSeleccionado", ticket);
        return "/ver_ticket.xhtml?faces-redirect=true";
    }

    public String volverMenu() {
        return "/usumenu.xhtml?faces-redirect=true";
    }
}
