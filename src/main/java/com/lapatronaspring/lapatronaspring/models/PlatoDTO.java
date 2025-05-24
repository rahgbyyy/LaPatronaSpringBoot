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
    
}
