package com.lapatronaspring.lapatronaspring.services;

import com.lapatronaspring.lapatronaspring.models.Rol;
import com.lapatronaspring.lapatronaspring.models.RolDTO;
import com.lapatronaspring.lapatronaspring.repositories.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServicio {
    @Autowired
    private RolRepositorio rolRepositorio;

    public void crear(RolDTO rolDTO) {
        Rol rol = new Rol();
        rol.setNombre(rolDTO.getNombre());
        rol.setDescripcion(rolDTO.getDescripcion());
        rolRepositorio.save(rol);
    }

    public List<Rol> obtenerTodos() {
        return rolRepositorio.findAll();
    }


    public boolean actualizar(Long idrol, RolDTO rolDTO) {
        Optional<Rol> rolOptional = rolRepositorio.findById(idrol);
        if (rolOptional.isPresent()) {
            Rol rol = rolOptional.get();
            rol.setNombre(rolDTO.getNombre());
            rol.setDescripcion(rolDTO.getDescripcion());
            rolRepositorio.save(rol);
            return true;
        }
        return false;
    }

    public boolean eliminar(Long idrol) {
        if (rolRepositorio.existsById(idrol)) {
            rolRepositorio.deleteById(idrol);
            return true;
        }
        return false;
    }


    public Optional<Rol> obtenerPorId(Long idrol) {
        return rolRepositorio.findById(idrol);
    }
}