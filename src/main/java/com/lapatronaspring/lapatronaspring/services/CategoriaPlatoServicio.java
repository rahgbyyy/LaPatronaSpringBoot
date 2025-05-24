
package com.lapatronaspring.lapatronaspring.services;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.lapatronaspring.lapatronaspring.models.Categoria;
import com.lapatronaspring.lapatronaspring.models.CategoriaPlato;
import com.lapatronaspring.lapatronaspring.repositories.CategoriaPlatoRepositorio;
import com.lapatronaspring.lapatronaspring.repositories.CategoriaRepositorio;

@Service
public class CategoriaPlatoServicio {

    private final CategoriaPlatoRepositorio categoriaRepository;

    public CategoriaPlatoServicio(CategoriaPlatoRepositorio categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaPlato> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<CategoriaPlato> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public CategoriaPlato guardar(CategoriaPlato categoria) {
        return categoriaRepository.save(categoria);
    }

    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}
