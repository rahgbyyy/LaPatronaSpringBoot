package com.lapatronaspring.lapatronaspring.models;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDTO {
    private Long idPedido;
    private Boolean estado;
    private String estadoPedido;
    private double monto;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaEliminado;
    private LocalDateTime ultModificacion;
    private Long idUsuario;
    private String usuario;
    private String cliente;
    private List<DetallePedidoDTO> detallePedido;

    public Long getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }
    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
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
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public List<DetallePedidoDTO> getDetallePedido() {
        return detallePedido;
    }
    public void setDetallePedido(List<DetallePedidoDTO> detallePedido) {
        this.detallePedido = detallePedido;
    }

}