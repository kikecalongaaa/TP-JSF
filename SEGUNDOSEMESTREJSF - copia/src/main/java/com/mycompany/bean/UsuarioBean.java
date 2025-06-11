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
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

@Named("usuarioBean")
@SessionScoped



public class UsuarioBean implements Serializable {

    private Usuario usuario = new Usuario();
    private List<Usuario> listaUsuarios = new ArrayList<>();

    public void registrar() {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.insertar(usuario);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario registrado correctamente."));
            usuario = new Usuario(); // Reset
            listar(); // actualizar lista
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error al registrar usuario: " + e.getMessage()));
            e.printStackTrace();
        }
    }

    public void listar() {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            listaUsuarios = dao.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar(Usuario u) {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.eliminar(u.getIdUsuario());
            listar(); // recarga la lista
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Usuario eliminado."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el usuario."));
        }
    }

    public String menu() {
        return "/admenu.xhtml?faces-redirect=true";
    }

    // Getters y setters
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Usuario> getListaUsuarios() { return listaUsuarios; }
    public void setListaUsuarios(List<Usuario> listaUsuarios) { this.listaUsuarios = listaUsuarios; }
}
