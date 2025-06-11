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
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;

import java.io.IOException;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    private String email;
    private String contrasena;
    private Usuario usuarioLogueado;

    public void login() {
        UsuarioDAO dao = new UsuarioDAO();
        usuarioLogueado = dao.login(email, contrasena);

        if (usuarioLogueado != null) {
            int rol = usuarioLogueado.getRolId();
            try {
                switch (rol) {
                    case 1:
                        FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
                        break;
                    case 2:
                        FacesContext.getCurrentInstance().getExternalContext().redirect("tecnico.xhtml");
                        break;
                    case 3:
                        FacesContext.getCurrentInstance().getExternalContext().redirect("usuario.xhtml");
                        break;
                    default:
                        mostrarError("Rol no reconocido.");
                        break;
                }
            } catch (IOException e) {
                mostrarError("Error al redirigir.");
            }
        } else {
            mostrarError("Credenciales inválidas.");
        }
    }

    private void mostrarError(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", mensaje));
    }

    // Getters y setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}
