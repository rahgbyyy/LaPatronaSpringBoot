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
    

}
