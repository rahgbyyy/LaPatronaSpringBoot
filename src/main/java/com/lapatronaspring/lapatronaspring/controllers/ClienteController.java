package com.lapatronaspring.lapatronaspring.controllers;


import com.lapatronaspring.lapatronaspring.models.ResponseCommonDTO;
import com.lapatronaspring.lapatronaspring.services.ClienteServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lapatronaspring.lapatronaspring.models.Cliente;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping
    public ResponseEntity<ResponseCommonDTO> crearCliente(@RequestBody Cliente cliente) {
        boolean success = clienteServicio.crearCliente(cliente);
        if (success) {
            ResponseCommonDTO response = new ResponseCommonDTO(true, "Cliente creado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        ResponseCommonDTO response = new ResponseCommonDTO(false, "No se pudo crear el cliente");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        List<Cliente> clientes = clienteServicio.obtenerTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Cliente>> obtenerClientesActivos() {
        List<Cliente> clientes = clienteServicio.obtenerClientesActivos();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCommonDTO> editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setIdcliente(id);
        boolean actualizado = clienteServicio.editarCliente(cliente);
        if (actualizado) {
            ResponseCommonDTO response = new ResponseCommonDTO(true, "Cliente actualizado correctamente");
            return ResponseEntity.ok(response);
        }
        ResponseCommonDTO response = new ResponseCommonDTO(false, "No se pudo actualizar el cliente");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCommonDTO> eliminarCliente(@PathVariable Long id) {
        boolean eliminado = clienteServicio.eliminarCliente(id);
        if (eliminado) {
            ResponseCommonDTO response = new ResponseCommonDTO(true, "Cliente eliminado correctamente");
            return ResponseEntity.ok(response);
        }
        ResponseCommonDTO response = new ResponseCommonDTO(false, "No se pudo eliminar el cliente");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    
}
