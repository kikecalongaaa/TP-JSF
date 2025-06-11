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

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;

@Named("usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    private Usuario usuario = new Usuario();

    public void registrar() {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.insertar(usuario);

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario registrado correctamente."));
            usuario = new Usuario(); // Reset
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error al registrar usuario: " + e.getMessage()));
            e.printStackTrace(); // Esto lo imprime en la consola
        }

    }

    // Getter y Setter
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
