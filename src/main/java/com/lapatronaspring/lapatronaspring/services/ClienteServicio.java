package com.lapatronaspring.lapatronaspring.services;

import com.lapatronaspring.lapatronaspring.models.Cliente;
import com.lapatronaspring.lapatronaspring.repositories.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public boolean crearCliente(Cliente cliente ) {
        clienteRepositorio.save(cliente);
        return true;
    }

    // Obtener todos los clientes
    public List<Cliente> obtenerTodos() {
        return clienteRepositorio.findAll();
    }

    public List<Cliente> obtenerClientesActivos(){
        return clienteRepositorio.findByEstadoTrue();
    }
     

    public boolean editarCliente(Cliente cliente) {
        if (clienteRepositorio.existsById(cliente.getIdcliente())) {
            clienteRepositorio.save(cliente);
            return true;
        }
        return false;
    }

    public boolean eliminarCliente(Long id) {
        if (clienteRepositorio.existsById(id)) {
            Cliente cliente = clienteRepositorio.findById(id).orElse(null);
            if (cliente != null) {
                cliente.setFechaeliminado(new Date());
                cliente.setEstado(false);
                clienteRepositorio.save(cliente);
                return true;
            }
        }
        return false;
    }

}
