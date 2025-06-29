package com.lapatronaspring.lapatronaspring.services;

import com.lapatronaspring.lapatronaspring.models.*;
import com.lapatronaspring.lapatronaspring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServicio {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    
    @Autowired
    private PlatoRepositorio platoRepository;

    // 1. Crear pedido
    @Transactional
    public PedidoDTO crearPedido(PedidoDTO pedidoDTO) {
        // Validar usuario
        Usuario usuario = usuarioRepository.findById(pedidoDTO.getIdUsuario())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        // Crear el pedido
        Pedido pedido = new Pedido();
        pedido.setEstado(true);
        pedido.setEstadoPedido("PENDIENTE"); // Estado inicial
        pedido.setMonto(pedidoDTO.getMonto());
        pedido.setFechaRegistro(LocalDateTime.now());
        pedido.setUltModificacion(LocalDateTime.now());
        pedido.setNombreCliente(pedidoDTO.getCliente());
        pedido.setUsuario(usuario);
        
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        
        // Guardar detalles del pedido
        if (pedidoDTO.getDetallePedido() != null && !pedidoDTO.getDetallePedido().isEmpty()) {
            for (DetallePedidoDTO detalleDTO : pedidoDTO.getDetallePedido()) {
                Plato plato = platoRepository.findById(detalleDTO.getIdPlato())
                    .orElseThrow(() -> new RuntimeException("Plato no encontrado: " + detalleDTO.getIdPlato()));
                
                DetallePedido detalle = new DetallePedido();
                detalle.setCantidad(detalleDTO.getCantidad());
                detalle.setSubtotal(detalleDTO.getSubtotal());
                detalle.setComentario(detalleDTO.getComentario());
                detalle.setPedido(pedidoGuardado);
                detalle.setPlato(plato);
                
                detallePedidoRepository.save(detalle);
            }
        }
        
        return toDTO(pedidoGuardado);
    }

    // 2. Editar pedido
    @Transactional
    public PedidoDTO editarPedido(Long id, PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        
        // Actualizar campos básicos
        pedido.setEstadoPedido(pedidoDTO.getEstadoPedido());
        pedido.setMonto(pedidoDTO.getMonto());
        pedido.setUltModificacion(LocalDateTime.now());
        pedido.setNombreCliente(pedidoDTO.getCliente());
        
        Pedido pedidoActualizado = pedidoRepository.save(pedido);
        return toDTO(pedidoActualizado);
    }

    // 3. Lista de pedidos
    public List<PedidoDTO> listarPedidos() {
        return pedidoRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    // 4. Cambiar estado del pedido
    @Transactional
    public boolean cambiarEstadoPedido(Long id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        
        pedido.setEstadoPedido(nuevoEstado);
        pedido.setUltModificacion(LocalDateTime.now());
        pedidoRepository.save(pedido);
        return true;
    }

    // 5. Eliminar pedido (eliminación lógica)
    @Transactional
    public boolean eliminarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        
        pedido.setEstado(false);
        pedido.setFechaEliminado(LocalDateTime.now());
        pedidoRepository.save(pedido);
        return true;
    }

    // 6. Buscar por estados
    public List<PedidoDTO> buscarPorEstado(String estado) {
        return pedidoRepository.findByEstadoPedido(estado)
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    // Método para convertir entidad a DTO
    private PedidoDTO toDTO(Pedido pedido) {
    PedidoDTO dto = new PedidoDTO();
    dto.setIdPedido(pedido.getIdPedido());
    dto.setEstado(pedido.isEstado());
    dto.setEstadoPedido(pedido.getEstadoPedido());
    dto.setMonto(pedido.getMonto());
    dto.setFechaRegistro(pedido.getFechaRegistro());
    dto.setFechaEliminado(pedido.getFechaEliminado());
    dto.setUltModificacion(pedido.getUltModificacion());
    dto.setCliente(pedido.getNombreCliente());

    if (pedido.getUsuario() != null) {
        dto.setIdUsuario(pedido.getUsuario().getIdusuario());
        dto.setUsuario(pedido.getUsuario().getNombre() + " " + pedido.getUsuario().getApellido());
    }

    // ✅ Obtener y mapear los detalles del pedido
    List<DetallePedido> detalles = detallePedidoRepository.findByPedido(pedido);
    List<DetallePedidoDTO> detalleDTOs = detalles.stream().map(det -> {
        DetallePedidoDTO detalleDTO = new DetallePedidoDTO();
        detalleDTO.setIdDetallePedido(det.getIdDetallePedido());
        detalleDTO.setCantidad(det.getCantidad());
        detalleDTO.setComentario(det.getComentario());
        detalleDTO.setSubtotal(det.getSubtotal());
        detalleDTO.setIdPedido(pedido.getIdPedido());

        if (det.getPlato() != null) {
            detalleDTO.setIdPlato(det.getPlato().getIdPlato());
            detalleDTO.setPlato(det.getPlato().getNombre());
            detalleDTO.setPrecio(det.getPlato().getPrecio());
            // Suponiendo que `descuento` existe en la entidad Plato
        }

        return detalleDTO;
    }).collect(Collectors.toList());

    dto.setDetallePedido(detalleDTOs);
    return dto;
}

}