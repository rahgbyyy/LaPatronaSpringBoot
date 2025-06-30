package com.lapatronaspring.lapatronaspring.controllers;

import com.lapatronaspring.lapatronaspring.models.PedidoDTO;
import com.lapatronaspring.lapatronaspring.models.ResponseCommonDTO;
import com.lapatronaspring.lapatronaspring.services.PedidoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoServicio pedidoServicio;

    // Crear un nuevo pedido
    @PostMapping
    public ResponseEntity<ResponseCommonDTO> crearPedido(@RequestBody PedidoDTO pedidoDTO) {
        try {
            PedidoDTO pedidoCreado = pedidoServicio.crearPedido(pedidoDTO);
            if (pedidoCreado == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseCommonDTO(false, "No se pudo crear el pedido"));
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseCommonDTO(true, "Pedido creado correctamente"));

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseCommonDTO(false, ex.getMessage()));
        }
    }

    // Editar un pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<?> editarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        try {
            PedidoDTO actualizado = pedidoServicio.editarPedido(id, pedidoDTO);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseCommonDTO(false, ex.getMessage()));
        }
    }

    // Listar todos los pedidos
    @GetMapping("/all")
    public ResponseEntity<List<PedidoDTO>> listarPedidos() {
        List<PedidoDTO> pedidos = pedidoServicio.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    // Listar todos los pedidos
    @GetMapping("/lista")
    public ResponseEntity<List<PedidoDTO>> listarPedidosActivos() {
        List<PedidoDTO> pedidos = pedidoServicio.listarPedidosActivos();
        return ResponseEntity.ok(pedidos);
    }


    // Cambiar el estado del pedido (por ejemplo: PENDIENTE, EN PREPARACIÓN, ENTREGADO)
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ResponseCommonDTO> cambiarEstado(@PathVariable Long id, @RequestBody EstadoRequest estadoRequest) {
        try {
            boolean actualizado = pedidoServicio.cambiarEstadoPedido(id, estadoRequest.getEstadoPedido());
            if (actualizado) {
                return ResponseEntity.ok(new ResponseCommonDTO(true, "Estado del pedido actualizado correctamente"));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseCommonDTO(false, "No se pudo cambiar el estado del pedido"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseCommonDTO(false, ex.getMessage()));
        }
    }

    // Eliminación lógica del pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCommonDTO> eliminarPedido(@PathVariable Long id) {
        try {
            boolean eliminado = pedidoServicio.eliminarPedido(id);
            if (eliminado) {
                return ResponseEntity.ok(new ResponseCommonDTO(true, "Pedido eliminado correctamente"));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseCommonDTO(false, "No se pudo eliminar el pedido"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseCommonDTO(false, ex.getMessage()));
        }
    }

    // Buscar pedidos por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PedidoDTO>> buscarPorEstado(@PathVariable String estado) {
        List<PedidoDTO> pedidos = pedidoServicio.buscarPorEstado(estado);
        return ResponseEntity.ok(pedidos);
    }

    // Buscar pedidos por ID de usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> buscarPedidosPorUsuario(@PathVariable Long idUsuario) {
        try {
            List<PedidoDTO> pedidos = pedidoServicio.buscarPedidosPorUsuario(idUsuario);
            return ResponseEntity.ok(pedidos);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseCommonDTO(false, ex.getMessage()));
        }
    }


    // Clase interna para deserializar el cambio de estado
    public static class EstadoRequest {
        private String estadoPedido;

        public String getEstadoPedido() {
            return estadoPedido;
        }

        public void setEstadoPedido(String estadoPedido) {
            this.estadoPedido = estadoPedido;
        }
    }


}
