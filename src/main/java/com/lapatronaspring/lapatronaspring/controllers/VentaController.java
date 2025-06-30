package com.lapatronaspring.lapatronaspring.controllers;

import com.lapatronaspring.lapatronaspring.models.ResponseCommonDTO;
import com.lapatronaspring.lapatronaspring.models.VentaDTO;
import com.lapatronaspring.lapatronaspring.services.VentaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaServicio ventaServicio;

    @PostMapping
    public ResponseEntity<ResponseCommonDTO> crearVenta(@RequestBody VentaDTO ventaDTO) {
        boolean creada = ventaServicio.crearVenta(ventaDTO);
        if (creada) {
            ResponseCommonDTO response = new ResponseCommonDTO(true, "Venta creada correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        ResponseCommonDTO response = new ResponseCommonDTO(false, "No se pudo crear la venta");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/historial")
    public ResponseEntity<List<VentaDTO>> obtenerHistorial() {
        List<VentaDTO> ventas = ventaServicio.obtenerHistorial();
        return ResponseEntity.ok(ventas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCommonDTO> editarVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
        boolean editada = ventaServicio.editarVenta(id, ventaDTO);
        if (editada) {
            ResponseCommonDTO response = new ResponseCommonDTO(true, "Venta editada correctamente");
            return ResponseEntity.ok(response);
        }
        ResponseCommonDTO response = new ResponseCommonDTO(false, "No se pudo editar la venta");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}