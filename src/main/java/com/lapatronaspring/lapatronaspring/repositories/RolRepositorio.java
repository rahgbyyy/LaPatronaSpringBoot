package com.lapatronaspring.lapatronaspring.repositories;

import com.lapatronaspring.lapatronaspring.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepositorio extends JpaRepository<Rol,Long>{
}
