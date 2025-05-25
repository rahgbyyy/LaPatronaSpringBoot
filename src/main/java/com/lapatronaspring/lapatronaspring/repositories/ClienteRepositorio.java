package com.lapatronaspring.lapatronaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lapatronaspring.lapatronaspring.models.Cliente;


    public interface ClienteRepositorio  extends JpaRepository<Cliente,Long>{

}
