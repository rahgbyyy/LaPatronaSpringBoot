package com.lapatronaspring.lapatronaspring.services;

import com.lapatronaspring.lapatronaspring.models.Pedido;
import com.lapatronaspring.lapatronaspring.models.Venta;
import com.lapatronaspring.lapatronaspring.models.VentaDTO;
import com.lapatronaspring.lapatronaspring.repositories.PedidoRepository;
import com.lapatronaspring.lapatronaspring.repositories.VentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaServicio {

    @Autowired
    private VentaRepositorio ventaRepositorio;

    @Autowired
    private PedidoRepository pedidoRepositorio;

    // 1. Crear venta
    public boolean crearVenta(VentaDTO dto) {
        Optional<Pedido> pedidoOpt = pedidoRepositorio.findById(dto.getIdPedido());
        if (!pedidoOpt.isPresent()) {
            throw new RuntimeException("Pedido no encontrado");
        }

        Pedido pedido = pedidoOpt.get();

        Venta venta = new Venta();
        venta.setCorrelativo(dto.getCorrelativo());
        venta.setEstado(dto.isEstado());
        venta.setTotal(pedido.getMonto()); // total se obtiene del pedido
        venta.setMetodoPago(dto.getMetodoPago());
        venta.setBoletaFactura(dto.getBoletaFactura());
        venta.setFechaRegistro(LocalDateTime.now());
        venta.setFechaAbonado(dto.getFechaAbonado());
        venta.setPedido(pedido);

        ventaRepositorio.save(venta);
        return true;
    }

    // 2. Historial de ventas
    public List<VentaDTO> obtenerHistorial() {
        return ventaRepositorio.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // 3. Editar venta (opcional)
    public boolean editarVenta(Long id, VentaDTO dto) {
        Optional<Venta> ventaOpt = ventaRepositorio.findById(id);
        if (!ventaOpt.isPresent()) {
            return false;
        }

        Venta venta = ventaOpt.get();

        // Solo editamos campos editables
        venta.setMetodoPago(dto.getMetodoPago());
        venta.setBoletaFactura(dto.getBoletaFactura());
        venta.setEstado(dto.isEstado());
        venta.setFechaAbonado(dto.getFechaAbonado());

        ventaRepositorio.save(venta);
        return true;
    }

    // Mapeo de entidad a DTO
    private VentaDTO toDTO(Venta venta) {
        VentaDTO dto = new VentaDTO();
        dto.setIdVenta(venta.getIdVenta());
        dto.setCorrelativo(venta.getCorrelativo());
        dto.setEstado(venta.isEstado());
        dto.setTotal(venta.getTotal());
        dto.setMetodoPago(venta.getMetodoPago());
        dto.setBoletaFactura(venta.getBoletaFactura());
        dto.setFechaRegistro(venta.getFechaRegistro());
        dto.setFechaAbonado(venta.getFechaAbonado());
        dto.setIdPedido(venta.getPedido().getIdPedido());
        return dto;
    }
}