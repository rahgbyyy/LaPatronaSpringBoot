package com.lapatronaspring.lapatronaspring.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private boolean estado;
    
    private String estadoPedido;
    
    private double monto;
    
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime  fechaRegistro;
    
    @Column(name = "fecha_eliminado", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime  fechaEliminado;
    
    @Column(name = "ult_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ultModificacion;
    
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "idusuario")
    private Usuario usuario;

    public Long getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public String getEstadoPedido() {
        return estadoPedido;
    }
    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
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
    public LocalDateTime getUltModificacion() {
        return ultModificacion;
    }
    public void setUltModificacion(LocalDateTime ultModificacion) {
        this.ultModificacion = ultModificacion;
    }
    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}