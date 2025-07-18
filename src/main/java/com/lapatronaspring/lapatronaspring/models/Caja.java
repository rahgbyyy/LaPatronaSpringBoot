package com.lapatronaspring.lapatronaspring.models;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaja;
    private Date fechainicio;
    @Column(nullable = true)
    private Date fechacierre;
    @Column(nullable = true)
    private Integer monto;

    
}
