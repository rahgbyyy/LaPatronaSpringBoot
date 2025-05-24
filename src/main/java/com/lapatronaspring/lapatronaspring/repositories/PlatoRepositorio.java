package com.lapatronaspring.lapatronaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lapatronaspring.lapatronaspring.models.Plato;


public interface PlatoRepositorio  extends JpaRepository<Plato,Long>{
    
}
