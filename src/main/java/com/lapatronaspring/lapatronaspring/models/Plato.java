package com.lapatronaspring.lapatronaspring.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plato")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlato;
    private String nombre;
    private String descripcion;
    private boolean  estado;
    private Integer stock;
    private Double precio;
    private String imgRuta;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaEliminado;

    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    private CategoriaPlato categoriaPlato;
}