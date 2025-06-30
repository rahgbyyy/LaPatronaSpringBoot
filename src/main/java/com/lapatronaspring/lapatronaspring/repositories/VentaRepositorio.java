package com.lapatronaspring.lapatronaspring.repositories;

import com.lapatronaspring.lapatronaspring.models.Categoria;
import com.lapatronaspring.lapatronaspring.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepositorio extends JpaRepository<Venta,Long>{
    
}
