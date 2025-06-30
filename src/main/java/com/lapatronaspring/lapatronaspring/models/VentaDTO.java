package com.lapatronaspring.lapatronaspring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VentaDTO {
    private Long idVenta;
    private int correlativo;
    private boolean estado;
    private double total;
    private String metodoPago;
    private String boletaFactura;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaAbonado;
    private Long idPedido;
}