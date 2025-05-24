package com.lapatronaspring.lapatronaspring.controllers;

import com.lapatronaspring.lapatronaspring.models.PlatoDTO;
import com.lapatronaspring.lapatronaspring.models.ResponseCommonDTO;
import com.lapatronaspring.lapatronaspring.services.PlatoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/platos")
public class PlatoController {

    @Autowired
    private PlatoServicio platoServicio;

    @PostMapping
    public ResponseEntity<ResponseCommonDTO> crearPlato(@RequestBody PlatoDTO platoDTO) {
        boolean success = platoServicio.crear(platoDTO);
        if (success) {
            ResponseCommonDTO response = new ResponseCommonDTO(true, "Plato creado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        ResponseCommonDTO response = new ResponseCommonDTO(false, "No se pudo crear el plato");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PlatoDTO>> obtenerTodos() {
        List<PlatoDTO> platos = platoServicio.obtenerTodos();
        return ResponseEntity.ok(platos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatoDTO> obtenerPorId(@PathVariable Long id) {
        Optional<PlatoDTO> platoOpt = platoServicio.obtenerPorId(id);
        if (platoOpt.isPresent()) {
            return ResponseEntity.ok(platoOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCommonDTO> actualizar(@PathVariable Long id, @RequestBody PlatoDTO platoDTO) {
        boolean actualizado = platoServicio.actualizar(id, platoDTO);
        if (actualizado) {
            ResponseCommonDTO response = new ResponseCommonDTO(true, "Plato actualizado correctamente");
            return ResponseEntity.ok(response);
        }
        ResponseCommonDTO response = new ResponseCommonDTO(false, "No se pudo actualizar el plato");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCommonDTO> eliminar(@PathVariable Long id) {
        boolean eliminado = platoServicio.eliminar(id);
        if (eliminado) {
            ResponseCommonDTO response = new ResponseCommonDTO(true, "Plato eliminado correctamente");
            return ResponseEntity.ok(response);
        }
        ResponseCommonDTO response = new ResponseCommonDTO(false, "No se pudo eliminar el plato");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    //tenemos q mandarle un objeto y no u bolean directamente, por eso la clase estadoreqest (para deseralizar)
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ResponseCommonDTO> cambiarEstado(@PathVariable Long id, @RequestBody EstadoRequest estadoRequest) {
        boolean cambiado = platoServicio.cambiarEstado(id, estadoRequest.isEstado());
        if (cambiado) {
            ResponseCommonDTO response = new ResponseCommonDTO(true, "Estado actualizado correctamente");
            return ResponseEntity.ok(response);
        }
        ResponseCommonDTO response = new ResponseCommonDTO(false, "No se pudo actualizar el estado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    
    public static class EstadoRequest {
        private boolean estado;

        public boolean isEstado() {
            return estado;
        }

        public void setEstado(boolean estado) {
            this.estado = estado;
        }
    }
}
