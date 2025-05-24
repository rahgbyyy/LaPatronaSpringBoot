
package com.lapatronaspring.lapatronaspring.services;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.lapatronaspring.lapatronaspring.models.Categoria;
import com.lapatronaspring.lapatronaspring.repositories.CategoriaRepositorio;

@Service
public class CategoriaServicio {

    private final CategoriaRepositorio categoriaRepository;

    public CategoriaServicio(CategoriaRepositorio categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}
