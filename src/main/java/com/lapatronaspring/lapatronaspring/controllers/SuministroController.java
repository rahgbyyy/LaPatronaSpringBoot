package com.lapatronaspring.lapatronaspring.controllers;

import com.lapatronaspring.lapatronaspring.models.SuministroDTO;
import com.lapatronaspring.lapatronaspring.services.SuministroServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.lapatronaspring.lapatronaspring.models.ResponseCommonDTO;

@RestController
@RequestMapping("/api/suministros")
public class SuministroController {

    @Autowired
    private SuministroServicio suministroService;

    // respuestas http con response entity
    @PostMapping
    public ResponseEntity<ResponseCommonDTO> crearSuministro(@RequestBody SuministroDTO suministroDTO) {
        boolean success = suministroService.crear(suministroDTO);
        if (success) {
            ResponseCommonDTO response = new ResponseCommonDTO(true, "Suministro creado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        ResponseCommonDTO response = new ResponseCommonDTO(false, "No se pudo crear el suministro");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @GetMapping
    public ResponseEntity<List<SuministroDTO>> obtenerTodos() {
        List<SuministroDTO> suministros = suministroService.obtenerTodos();
        return ResponseEntity.ok(suministros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuministroDTO> obtenerPorId(@PathVariable Long id) {
        Optional<SuministroDTO> suministroOpt = suministroService.obtenerPorId(id);
        if (suministroOpt.isPresent()) {
            return ResponseEntity.ok(suministroOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCommonDTO> actualizar(@PathVariable Long id,
            @RequestBody SuministroDTO suministroDTO) {
        boolean actualizado = suministroService.actualizar(id, suministroDTO);
        if (actualizado) {
            ResponseCommonDTO response = new ResponseCommonDTO(true, "Suministro actualizado correctamente");
            return ResponseEntity.ok().body(response);
        }
        ResponseCommonDTO response = new ResponseCommonDTO(false, "No se pudo actualizar el suministro");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCommonDTO> eliminar(@PathVariable Long id) {
        boolean eliminado = suministroService.eliminar(id);
        if (eliminado) {
            ResponseCommonDTO response = new ResponseCommonDTO(true, "Suministro eliminado correctamente");
            return ResponseEntity.ok().body(response);
        }
        ResponseCommonDTO response = new ResponseCommonDTO(false, "No se pudo eliminar correctamente");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}