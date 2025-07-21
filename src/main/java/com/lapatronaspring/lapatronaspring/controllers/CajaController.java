package com.lapatronaspring.lapatronaspring.controllers;

import com.lapatronaspring.lapatronaspring.models.*;
import com.lapatronaspring.lapatronaspring.services.CajaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/caja")
public class CajaController {

    @Autowired
    private CajaServicio cajaServicio;

    @GetMapping
    public ResponseEntity<List<CajaDTO>> getList(@RequestParam(required = false) String search) {
        List<CajaDTO> cajas = cajaServicio.getList(search);
        return ResponseEntity.ok(cajas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        CajaDTO caja = cajaServicio.getById(id);
        if (caja != null) {
            return ResponseEntity.ok(caja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseCommonDTO(false, "Caja no encontrada"));
    }

    @PostMapping
    public ResponseEntity<ResponseCommonDTO> saveItem(@RequestBody CajaDTO cajaDTO) {
        ResponseCommonDTO response = cajaServicio.saveItem(cajaDTO);
        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCommonDTO> updateItem(@PathVariable Long id, @RequestBody CajaDTO cajaDTO) {
        cajaDTO.setIdCaja(id);
        ResponseCommonDTO response = cajaServicio.updateItem(cajaDTO);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCommonDTO> deleteItem(@PathVariable Long id) {
        ResponseCommonDTO response = cajaServicio.deleteItem(id);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseCommonDTO> patchCaja(@PathVariable Long id, @RequestBody CajaDTO patchDTO) {
        ResponseCommonDTO response = cajaServicio.patchCaja(id, patchDTO);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/abierta")
    public ResponseEntity<?> getCajaAbierta() {
        CajaDTO caja = cajaServicio.getCajaAbierta();
        if (caja != null) {
            return ResponseEntity.ok(caja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseCommonDTO(false, "No hay cajas abiertas"));
    }

    @GetMapping("/ultima-cerrada")
    public ResponseEntity<?> getUltimaCajaCerrada() {
        CajaDTO caja = cajaServicio.getUltimaCajaCerrada();
        if (caja != null) {
            return ResponseEntity.ok(caja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseCommonDTO(false, "No hay cajas cerradas"));
    }

    @GetMapping("/pedidos-entregados")
    public ResponseEntity<List<PedidoDTO>> getPedidosEntregadosDuranteCajaAbierta() {
        List<PedidoDTO> pedidos = cajaServicio.getPedidosEntregadosDuranteCajaAbierta();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}/total-pedidos")
    public ResponseEntity<?> getTotalPedidosAbonados(@PathVariable Long id) {
        Double total = cajaServicio.getTotalPedidosAbonados(id);
        return ResponseEntity.ok(new TotalResponse(total));
    }

    public static class TotalResponse {
        private Double total;

        public TotalResponse(Double total) {
            this.total = total;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }
    }
}