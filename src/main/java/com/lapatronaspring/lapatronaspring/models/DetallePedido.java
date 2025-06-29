package com.lapatronaspring.lapatronaspring.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetallePedido;

    private Integer cantidad;
    
    private double subtotal;
    
    private String comentario;
    
    // Relación con Pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", referencedColumnName = "idPedido")
    private Pedido pedido;
    
    // Relación con Plato
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plato", referencedColumnName = "idPlato")
    private Plato plato;

}