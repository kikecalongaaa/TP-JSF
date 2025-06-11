/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author enriq
 */

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rol;

    private String nombre_rol;

    // Getters y setters
    public int getId_rol() { return id_rol; }
    public void setId_rol(int id_rol) { this.id_rol = id_rol; }

    public String getNombre_rol() { return nombre_rol; }
    public void setNombre_rol(String nombre_rol) { this.nombre_rol = nombre_rol; }
}
