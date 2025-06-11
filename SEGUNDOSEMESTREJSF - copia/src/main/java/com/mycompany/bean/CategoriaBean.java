/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bean;

/**
 *
 * @author enriq
 */
import com.mycompany.dao.CategoriaDAO;
import com.mycompany.model.Categoria;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;

@Named("categoriaBean")
@SessionScoped
public class CategoriaBean implements Serializable {

    private Categoria categoria = new Categoria();
    private List<Categoria> categorias;

    @PostConstruct
    public void init() {
        listar();
    }

    public void registrar() {
        try {
            CategoriaDAO dao = new CategoriaDAO();
            dao.insertar(categoria);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría registrada."));
            categoria = new Categoria();
            listar(); // refrescar lista
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            e.printStackTrace();
        }
    }

    public void listar() {
        try {
            CategoriaDAO dao = new CategoriaDAO();
            categorias = dao.listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo cargar la lista."));
        }
    }
    
    public void eliminar(Categoria categoria) {
    try {
        CategoriaDAO dao = new CategoriaDAO();
        dao.eliminar(categoria.getIdCategoria());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Categoría eliminada."));
        listar(); // actualizar lista
    } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
    }
}


    // Getters y Setters
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}

