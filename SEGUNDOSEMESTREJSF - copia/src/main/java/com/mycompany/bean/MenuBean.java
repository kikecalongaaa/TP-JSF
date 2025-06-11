/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bean;

/**
 *
 * @author enriq
 */
import com.mycompany.dao.UsuarioDAO;
import com.mycompany.model.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named("menuBean")
@SessionScoped
public class MenuBean implements Serializable {
    
 
    public String irCrearTicket() {
        return "crear_ticket.xhtml?faces-redirect=true";
        
    }

    public String irVerTickets() {
        return "ver_tickets.xhtml?faces-redirect=true";
    }

    public String irUsuarios() {
        return "crearUsuario.xhtml?faces-redirect=true";
    }

    public String categoria() {
        return "categoria.xhtml?faces-redirect=true";
    }
    
    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }
}
