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
import com.mycompany.model.Usuario;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;

import java.io.Serializable;
import java.util.List;

@Named("misTicketsBean")
@SessionScoped
public class MisTicketsBean implements Serializable {

    private List<Ticket> listaTickets;

    public MisTicketsBean() {
        cargarTickets();
    }

    public void cargarTickets() {
        try {
            // Obtener usuario logueado desde LoginBean
            LoginBean loginBean = FacesContext.getCurrentInstance()
                    .getApplication()
                    .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{loginBean}", LoginBean.class);

            Usuario usuario = loginBean.getUsuarioLogueado();

            if (usuario == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Usuario no autenticado."));
                return;
            }

            TicketDAO dao = new TicketDAO();
            listaTickets = dao.listarPorUsuario(usuario.getIdUsuario());

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los tickets."));
            e.printStackTrace();
        }
    }

    public List<Ticket> getListaTickets() {
        return listaTickets;
    }

    // Método auxiliar para mostrar estado legible
    public String nombreEstado(int idEstado) {
    switch (idEstado) {
        case 1: return "Abierto";
        case 2: return "En Proceso";
        case 3: return "Cerrado";
        case 4: return "Rechazado";
        default: return "Desconocido";
    }
}

    // Acción del botón "Ver Ticket"
    public String verTicket(Ticket ticket) {
        // Guardamos el ticket seleccionado en sesión o en otro bean si lo usas
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ticketSeleccionado", ticket);
        return "/ver_ticket.xhtml?faces-redirect=true";
    }

    // Volver al menú
    public String volverMenu() {
        return "/usumenu.xhtml?faces-redirect=true";
    }
}