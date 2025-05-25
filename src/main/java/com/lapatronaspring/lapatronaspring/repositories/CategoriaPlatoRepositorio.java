package com.lapatronaspring.lapatronaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lapatronaspring.lapatronaspring.models.CategoriaPlato;

public interface CategoriaPlatoRepositorio  extends JpaRepository<CategoriaPlato,Long>{
    
}
