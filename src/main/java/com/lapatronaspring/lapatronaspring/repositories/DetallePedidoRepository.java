package com.lapatronaspring.lapatronaspring.repositories;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lapatronaspring.lapatronaspring.models.DetallePedido;
import com.lapatronaspring.lapatronaspring.models.Pedido;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
    List<DetallePedido> findByPedido(Pedido pedido);
}
