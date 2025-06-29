package com.lapatronaspring.lapatronaspring.repositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lapatronaspring.lapatronaspring.models.Pedido;
import com.lapatronaspring.lapatronaspring.models.Usuario;


public interface PedidoRepository extends JpaRepository<Pedido,Long>{

    List<Pedido> findByEstadoPedido(String estado);

}
