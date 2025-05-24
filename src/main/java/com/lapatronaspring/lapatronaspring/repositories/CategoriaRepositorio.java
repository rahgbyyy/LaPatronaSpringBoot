package com.lapatronaspring.lapatronaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lapatronaspring.lapatronaspring.models.Categoria;
import com.lapatronaspring.lapatronaspring.models.Rol;

public interface CategoriaRepositorio  extends JpaRepository<Categoria,Long>{
    
}
