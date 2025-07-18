package com.lapatronaspring.lapatronaspring.models;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CajaDTO {
    private Long idCaja;
    private Integer monto;
    private Date fechainicio;
    private Date fechanacierre;
}