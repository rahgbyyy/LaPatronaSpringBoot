package com.lapatronaspring.lapatronaspring.repositories;

import com.lapatronaspring.lapatronaspring.models.Rol;
import com.lapatronaspring.lapatronaspring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
}
