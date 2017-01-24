package com.cosa.pojo;

import javax.persistence.*;

/**
 * Created by root on 18/01/17.
 */
@Entity
@Table(name="stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStock;
    private String detalle;
    private float precio;
    private int cantidad;
    private int codigo;

    public Stock(){
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "idStock=" + idStock +
                ", detalle='" + detalle + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", codigo=" + codigo +
                '}';
    }
}
