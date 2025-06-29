package com.lapatronaspring.lapatronaspring.models;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DetallePedidoDTO {
    private Long idDetallePedido;
    private Integer cantidad;
    private double subtotal;
    private String comentario;
    private Long idPedido;
    private Long idPlato;
    private String plato;
    private double precio;
}