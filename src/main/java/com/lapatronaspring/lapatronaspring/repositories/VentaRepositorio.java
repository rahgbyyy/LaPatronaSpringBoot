package com.lapatronaspring.lapatronaspring.repositories;

import com.lapatronaspring.lapatronaspring.models.Categoria;
import com.lapatronaspring.lapatronaspring.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDateTime;

public interface VentaRepositorio extends JpaRepository<Venta,Long>{
    List<Venta> findByFechaRegistroAfter(LocalDateTime fecha);
}
