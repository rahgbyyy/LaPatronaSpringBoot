package com.lapatronaspring.lapatronaspring.services;

import com.lapatronaspring.lapatronaspring.models.Caja;  // Add this import
import com.lapatronaspring.lapatronaspring.repositories.CajaRepositorio;  
import com.lapatronaspring.lapatronaspring.models.Pedido;
import com.lapatronaspring.lapatronaspring.models.Venta;
import com.lapatronaspring.lapatronaspring.models.VentaDTO;
import com.lapatronaspring.lapatronaspring.repositories.PedidoRepository;
import com.lapatronaspring.lapatronaspring.repositories.VentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;
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
     @Autowired
    private CajaRepositorio cajaRepositorio;

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
    
    
    
    // In VentaServicio.java

// 4. Obtener totales por método de pago
public Map<String, Double> obtenerTotalesPorMetodoPago() {
    List<Venta> ventas = ventaRepositorio.findAll();
    
    Map<String, Double> totales = new HashMap<>();
    totales.put("efectivo", 0.0);
    totales.put("yape", 0.0);
    totales.put("tarjeta", 0.0);
    
    for (Venta venta : ventas) {
        if (venta.getMetodoPago() != null && venta.isEstado()) {
            String metodo = venta.getMetodoPago().toLowerCase();
            totales.put(metodo, totales.getOrDefault(metodo, 0.0) + venta.getTotal());
        }
    }
    
    return totales;
}

// 5. Obtener totales de cajas simples
public Map<String, Double> obtenerTotalesCajasSimples() {
    Map<String, Double> result = new HashMap<>();
    
    // Obtener caja abierta
    Caja cajaAbierta = cajaRepositorio.findByFechaCierreIsNullOrderByFechaInicioDesc()
        .stream()
        .findFirst()
        .orElse(null);
    
    if (cajaAbierta != null) {
        // Calcular total para caja abierta
        double totalAbierta = ventaRepositorio.findByFechaRegistroAfter(cajaAbierta.getFechaInicio())
            .stream()
            .filter(Venta::isEstado)
            .mapToDouble(Venta::getTotal)
            .sum();
        
        result.put("actual", totalAbierta);
    } else {
        result.put("actual", 0.0);
    }
    
    // Obtener última caja cerrada
    Caja ultimaCerrada = cajaRepositorio.findByFechaCierreIsNotNullOrderByFechaCierreDesc()
        .stream()
        .findFirst()
        .orElse(null);
    
    if (ultimaCerrada != null) {
        result.put("anterior", ultimaCerrada.getMonto() != null ? ultimaCerrada.getMonto() : 0.0);
    } else {
        result.put("anterior", 0.0);
    }
    
    return result;
}    // 3. Editar venta (opcional)
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