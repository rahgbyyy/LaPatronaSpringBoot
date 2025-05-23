package com.lapatronaspring.lapatronaspring.controllers;

import com.lapatronaspring.lapatronaspring.models.RolDTO;
import com.lapatronaspring.lapatronaspring.services.RolServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RolController {
    @Autowired
    private RolServicio rolServicio;
    @PostMapping("/crear")
    public String crear(@RequestBody RolDTO rolDTO){
        rolServicio.crear(rolDTO);
        return"Rol creado";
    }
}
