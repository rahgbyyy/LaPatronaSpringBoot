package com.lapatronaspring.lapatronaspring.services;

import com.lapatronaspring.lapatronaspring.models.*;
import com.lapatronaspring.lapatronaspring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CajaServicio {

    @Autowired
    private CajaRepositorio cajaRepositorio;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List<CajaDTO> getList(String pSearch) {
        List<Caja> cajas;
        
        if (pSearch != null && !pSearch.isEmpty()) {
            cajas = cajaRepositorio.findAll().stream()
                .filter(c -> 
                    String.valueOf(c.getIdCaja()).contains(pSearch) ||
                    (c.getFechaInicio() != null && c.getFechaInicio().toString().contains(pSearch)) ||
                    (c.getFechaCierre() != null && c.getFechaCierre().toString().contains(pSearch)) ||
                    (c.getMonto() != null && c.getMonto().toString().contains(pSearch)))
                .collect(Collectors.toList());
        } else {
            cajas = cajaRepositorio.findAllByOrderByFechaInicioDesc();
        }
        
        return cajas.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public CajaDTO getById(Long pId) {
        Optional<Caja> caja = cajaRepositorio.findById(pId);
        return caja.map(this::convertToDTO).orElse(null);
    }

    public ResponseCommonDTO saveItem(CajaDTO pItem) {
        try {
            Caja nuevaCaja = new Caja();
            nuevaCaja.setFechaInicio(pItem.getFechaInicio() != null ? pItem.getFechaInicio() : LocalDateTime.now());
            nuevaCaja.setFechaCierre(pItem.getFechaCierre());
            nuevaCaja.setMonto(pItem.getMonto());
            
            cajaRepositorio.save(nuevaCaja);
            
            return new ResponseCommonDTO(true, "Caja creada correctamente");
        } catch (Exception ex) {
            return new ResponseCommonDTO(false, "Error al crear caja: " + ex.getMessage());
        }
    }

    public ResponseCommonDTO updateItem(CajaDTO pItem) {
        try {
            if (pItem.getIdCaja() == null || pItem.getIdCaja() == 0) {
                return new ResponseCommonDTO(false, "ID de caja no válido");
            }

            Optional<Caja> cajaOpt = cajaRepositorio.findById(pItem.getIdCaja());
            if (!cajaOpt.isPresent()) {
                return new ResponseCommonDTO(false, "Caja no encontrada");
            }

            Caja caja = cajaOpt.get();

            if (pItem.getFechaCierre() != null && pItem.getFechaInicio() != null && 
                pItem.getFechaCierre().isBefore(pItem.getFechaInicio())) {
                return new ResponseCommonDTO(false, "La fecha de cierre no puede ser anterior a la fecha de inicio");
            }

            if (pItem.getFechaInicio() != null) {
                caja.setFechaInicio(pItem.getFechaInicio());
            }
            if (pItem.getFechaCierre() != null) {
                caja.setFechaCierre(pItem.getFechaCierre());
            }
            if (pItem.getMonto() != null) {
                caja.setMonto(pItem.getMonto());
            }

            cajaRepositorio.save(caja);
            return new ResponseCommonDTO(true, "Caja actualizada correctamente");
        } catch (Exception ex) {
            return new ResponseCommonDTO(false, "Error al actualizar caja: " + ex.getMessage());
        }
    }

    public ResponseCommonDTO deleteItem(Long pId) {
        try {
            Optional<Caja> caja = cajaRepositorio.findById(pId);
            if (!caja.isPresent()) {
                return new ResponseCommonDTO(false, "Caja no encontrada");
            }

            cajaRepositorio.delete(caja.get());
            return new ResponseCommonDTO(true, "Caja eliminada correctamente");
        } catch (Exception ex) {
            return new ResponseCommonDTO(false, "Error al eliminar caja: " + ex.getMessage());
        }
    }

    public ResponseCommonDTO patchCaja(Long id, CajaDTO patchDTO) {
        try {
            Optional<Caja> cajaOpt = cajaRepositorio.findById(id);
            if (!cajaOpt.isPresent()) {
                return new ResponseCommonDTO(false, "Caja no encontrada");
            }

            Caja caja = cajaOpt.get();

            if (patchDTO.getIdCaja() != null && patchDTO.getIdCaja() != 0 && !patchDTO.getIdCaja().equals(id)) {
                return new ResponseCommonDTO(false, "No se puede modificar el ID de la caja");
            }

            if (patchDTO.getMonto() == null && patchDTO.getFechaCierre() == null) {
                return new ResponseCommonDTO(false, "Debe proporcionar al menos un campo para actualizar (Monto o FechaCierre)");
            }

            if (patchDTO.getFechaCierre() != null) {
                if (caja.getFechaInicio() == null) {
                    return new ResponseCommonDTO(false, "No se puede cerrar caja sin fecha de inicio");
                }

                if (patchDTO.getFechaCierre().isBefore(caja.getFechaInicio())) {
                    return new ResponseCommonDTO(false, "La fecha de cierre no puede ser anterior a la fecha de inicio");
                }

                if (caja.getFechaCierre() != null) {
                    return new ResponseCommonDTO(false, "La caja ya está cerrada");
                }

                caja.setFechaCierre(patchDTO.getFechaCierre());
            } else if (patchDTO.getMonto() != null) {
                caja.setMonto(patchDTO.getMonto());
            }

            cajaRepositorio.save(caja);
            return new ResponseCommonDTO(true,
                patchDTO.getFechaCierre() != null ? 
                "Caja cerrada correctamente" : 
                "Caja actualizada correctamente");
        } catch (Exception ex) {
            return new ResponseCommonDTO(false, "Error al actualizar caja: " + ex.getMessage());
        }
    }
public CajaDTO getCajaAbierta() {
    List<Caja> cajasAbiertas = cajaRepositorio.findByFechaCierreIsNullOrderByFechaInicioDesc();
    return cajasAbiertas.isEmpty() ? null : convertToDTO(cajasAbiertas.get(0));
}

public CajaDTO getUltimaCajaCerrada() {
    List<Caja> cajasCerradas = cajaRepositorio.findByFechaCierreIsNotNullOrderByFechaCierreDesc();
    return cajasCerradas.isEmpty() ? null : convertToDTO(cajasCerradas.get(0));
}


    public List<PedidoDTO> getPedidosEntregadosDuranteCajaAbierta() {
        try {
            List<Caja> cajasAbiertas = cajaRepositorio.findByFechaCierreIsNullOrderByFechaInicioDesc();
            if (cajasAbiertas.isEmpty()) {
                return new ArrayList<>();
            }

            Caja cajaAbierta = cajasAbiertas.get(0);
            LocalDateTime fechaInicioCaja = cajaAbierta.getFechaInicio();

            List<Pedido> pedidos = pedidoRepository.findByEstadoTrueAndEstadoPedidoAndFechaRegistroGreaterThanEqual(
                "ENTREGADO", fechaInicioCaja);

            return pedidos.stream()
                .map(this::convertPedidoToDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public Double getTotalPedidosAbonados(Long idCaja) {
        Optional<Caja> cajaOpt = cajaRepositorio.findById(idCaja);
        if (!cajaOpt.isPresent() || cajaOpt.get().getFechaInicio() == null) {
            return 0.0;
        }

        Caja caja = cajaOpt.get();
        LocalDateTime fechaInicio = caja.getFechaInicio();
        LocalDateTime fechaCierre = caja.getFechaCierre();

        List<Pedido> pedidos;
        if (fechaCierre != null) {
            pedidos = pedidoRepository.findByEstadoTrueAndEstadoPedidoAndFechaRegistroBetween(
                "ABONADO", fechaInicio, fechaCierre);
        } else {
            pedidos = pedidoRepository.findByEstadoTrueAndEstadoPedidoAndFechaRegistroGreaterThanEqual(
                "ABONADO", fechaInicio);
        }

        return pedidos.stream()
            .mapToDouble(Pedido::getMonto)
            .sum();
    }

    private CajaDTO convertToDTO(Caja caja) {
        CajaDTO dto = new CajaDTO();
        dto.setIdCaja(caja.getIdCaja());
        dto.setFechaInicio(caja.getFechaInicio());
        dto.setFechaCierre(caja.getFechaCierre());
        dto.setMonto(caja.getMonto());
        return dto;
    }

    private PedidoDTO convertPedidoToDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(pedido.getIdPedido());
        dto.setEstado(pedido.isEstado());
        dto.setEstadoPedido(pedido.getEstadoPedido());
        dto.setMonto(pedido.getMonto());
        dto.setFechaRegistro(pedido.getFechaRegistro());
        dto.setCliente(pedido.getNombreCliente());

        if (pedido.getUsuario() != null) {
            dto.setIdUsuario(pedido.getUsuario().getIdusuario());
            dto.setUsuario(pedido.getUsuario().getNombre());
        }

        List<DetallePedido> detalles = detallePedidoRepository.findByPedido(pedido);
        dto.setDetallePedido(detalles.stream()
            .map(this::convertDetalleToDTO)
            .collect(Collectors.toList()));

        return dto;
    }

    private DetallePedidoDTO convertDetalleToDTO(DetallePedido detalle) {
        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setIdDetallePedido(detalle.getIdDetallePedido());
        dto.setCantidad(detalle.getCantidad());
        dto.setSubtotal(detalle.getSubtotal());
        dto.setComentario(detalle.getComentario());
        dto.setIdPedido(detalle.getPedido().getIdPedido());

        if (detalle.getPlato() != null) {
            dto.setIdPlato(detalle.getPlato().getIdPlato());
            dto.setPlato(detalle.getPlato().getNombre());
            dto.setPrecio(detalle.getPlato().getPrecio());
        }

        return dto;
    }
}