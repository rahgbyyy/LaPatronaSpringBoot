package com.lapatronaspring.lapatronaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.lapatronaspring.lapatronaspring.models.Cliente;


    public interface ClienteRepositorio  extends JpaRepository<Cliente,Long> {
    List<Cliente> findByEstadoTrue();

}
