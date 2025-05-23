package com.lapatronaspring.lapatronaspring.services;


import com.lapatronaspring.lapatronaspring.models.Rol;
import com.lapatronaspring.lapatronaspring.models.RolDTO;
import com.lapatronaspring.lapatronaspring.repositories.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServicio {
    @Autowired
    private RolRepositorio rolrepositorio;
public void crear(RolDTO rolDTO){
    Rol rol= new Rol();
    rol.setNombre(rolDTO.getNombre());
    rol.setDescripcion(rolDTO.getDescripcion());
    rolrepositorio.save(rol);

}

}
