package com.lapatronaspring.lapatronaspring.models;

import java.sql.Date;

import lombok.Data;

@Data
public class SuministroDTO {

    private Long idSuministro;
    private String nombre;
    private int stock;
    private String unidad;
    private boolean estado;
    private Date fechaEliminado;
    private Long idCategoria;
    private String categoria;

    public boolean isEstado() {
        return estado;
    }

    public Long getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(Long idSuministro) {
        this.idSuministro = idSuministro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaEliminado() {
        return fechaEliminado;
    }

    public void setFechaEliminado(Date fechaEliminado) {
        this.fechaEliminado = fechaEliminado;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
