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
import com.mycompany.dao.CategoriaDAO;
import com.mycompany.model.Ticket;
import com.mycompany.model.Usuario;
import com.mycompany.model.Categoria;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named("ticketBean")
@SessionScoped
public class TicketBean implements Serializable {
    private Ticket ticket = new Ticket();
    private List<Categoria> categorias;

    public TicketBean() {
        cargarCategorias();
    }

    public void crearTicket() {
        try {
            // Establece datos fijos
            ticket.setFechaCreacion(LocalDate.now());
            ticket.setEstadoId(1); // ID del estado "ABIERTO"

            // Obtener usuario logueado desde el LoginBean
            LoginBean loginBean = FacesContext.getCurrentInstance()
                                    .getApplication()
                                    .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{loginBean}", LoginBean.class);
            Usuario usuario = loginBean.getUsuarioLogueado();

            if (usuario != null) {
                ticket.setUsuarioCreadorId(usuario.getIdUsuario());
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario no autenticado."));
                return;
            }

            TicketDAO dao = new TicketDAO();
            dao.insertar(ticket);

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Ticket creado correctamente."));

            ticket = new Ticket(); // reset

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            e.printStackTrace();
        }
    }

    public void cargarCategorias() {
        try {
            CategoriaDAO dao = new CategoriaDAO();
            categorias = dao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  public String menu() {
        return "/usumenu.xhtml?faces-redirect=true";
    }
    // Getters y Setters
    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public List<Categoria> getCategorias() { return categorias; }
    public void setCategorias(List<Categoria> categorias) { this.categorias = categorias; }
}
