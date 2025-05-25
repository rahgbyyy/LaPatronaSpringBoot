package com.lapatronaspring.lapatronaspring.models;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PlatoDTO {
    private Long idPlato;
    private String nombre;
    private String descripcion;
    private Long idCategoria;
    private String categoria;
    private boolean  estado;
    private Integer stock;
    private Double precio;
    private String imgRuta;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaEliminado;

    public boolean isEstado() {
        return estado;
    }

    public Long getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(Long idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImgRuta() {
        return imgRuta;
    }

    public void setImgRuta(String imgRuta) {
        this.imgRuta = imgRuta;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaEliminado() {
        return fechaEliminado;
    }

    public void setFechaEliminado(LocalDateTime fechaEliminado) {
        this.fechaEliminado = fechaEliminado;
    }
}
