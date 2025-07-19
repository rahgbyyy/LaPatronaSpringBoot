package com.lapatronaspring.lapatronaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lapatronaspring.lapatronaspring.models.Caja;
import java.util.List; 
import java.util.Optional;

public interface CajaRepositorio  extends JpaRepository<Caja,Long>{
    List<Caja> findAllByOrderByFechaInicioDesc();
    List<Caja> findByFechaCierreIsNullOrderByFechaInicioDesc();
    List<Caja> findByFechaCierreIsNotNullOrderByFechaCierreDesc();
    Optional<Caja> findFirstByFechaCierreIsNullOrderByFechaInicioDesc();
    Optional<Caja> findFirstByFechaCierreIsNotNullOrderByFechaCierreDesc();
}
