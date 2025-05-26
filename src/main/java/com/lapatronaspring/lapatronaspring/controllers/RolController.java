package com.lapatronaspring.lapatronaspring.controllers;

import com.lapatronaspring.lapatronaspring.models.Rol;
import com.lapatronaspring.lapatronaspring.models.RolDTO;
import com.lapatronaspring.lapatronaspring.services.RolServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/roles")
public class RolController {
    @Autowired
    private RolServicio rolServicio;

    @PostMapping("/crear")
    public String crear(@RequestBody RolDTO rolDTO){
        rolServicio.crear(rolDTO);
        return"Rol creado";
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Rol>> obtenerTodos() {
        return ResponseEntity.ok(rolServicio.obtenerTodos());
    }


    @GetMapping("/{idrol}")
    public ResponseEntity<Rol> obtenerPorId(@PathVariable Long idrol) {
        Optional<Rol> rol = rolServicio.obtenerPorId(idrol);
        return rol.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{idrol}")
    public ResponseEntity<String> actualizar(@PathVariable Long idrol, @RequestBody RolDTO rolDTO) {
        if (rolServicio.actualizar(idrol, rolDTO)) {
            return ResponseEntity.ok("Rol actualizado exitosamente");
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{idrol}")
    public ResponseEntity<String> eliminar(@PathVariable Long idrol) {
        if (rolServicio.eliminar(idrol)) {
            return ResponseEntity.ok("Rol eliminado exitosamente");
        }
        return ResponseEntity.notFound().build();
    }
}