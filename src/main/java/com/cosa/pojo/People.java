package com.cosa.pojo;


import com.sun.xml.internal.ws.api.ha.StickyFeature;

import javax.persistence.*;

/**
 * Created by kp0cH4 on 28/01/17.
 */
@Entity
@Table(name="personas")
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersona;
    private String nombre;
    private int dni;
    private String direccion;
    private String email;
    private String telefono;
    private String cuit;
    private String Localidad;
    private String condicionIva;
    private String observaciones;

    @Override
    public String toString() {
        return "People{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", dni=" + dni +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", cuit=" + cuit +
                ", Localidad='" + Localidad + '\'' +
                ", condicionIva='" + condicionIva + '\'' +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public void setLocalidad(String localidad) {
        Localidad = localidad;
    }

    public void setCondicionIva(String condicionIva) {
        this.condicionIva = condicionIva;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdPersona() {

        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCuit() {
        return cuit;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public String getCondicionIva() {
        return condicionIva;
    }

    public String getObservaciones() {
        return observaciones;
    }
}
