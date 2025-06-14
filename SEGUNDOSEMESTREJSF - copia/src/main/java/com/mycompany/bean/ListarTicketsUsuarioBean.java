/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bean;

/**
 *
 * @author enriq
 */
import com.mycompany.dao.ListarTicketsUsuarioDAO;
import com.mycompany.model.TicketUsuario;
import com.mycompany.model.Usuario;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;

@Named("listarTicketsUsuarioBean")
@SessionScoped
public class ListarTicketsUsuarioBean implements Serializable {
    private List<TicketUsuario> tickets;

    @PostConstruct
    public void init() {
        try {
            LoginBean loginBean = FacesContext.getCurrentInstance()
                                .getApplication()
                                .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{loginBean}", LoginBean.class);
            Usuario usuario = loginBean.getUsuarioLogueado();

            if (usuario != null) {
                ListarTicketsUsuarioDAO dao = new ListarTicketsUsuarioDAO();
                tickets = dao.obtenerTicketsPorUsuario(usuario.getIdUsuario());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TicketUsuario> getTickets() {
        return tickets;
    }
}
