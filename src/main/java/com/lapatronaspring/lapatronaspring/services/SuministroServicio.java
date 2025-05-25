package com.lapatronaspring.lapatronaspring.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lapatronaspring.lapatronaspring.models.Categoria;
import com.lapatronaspring.lapatronaspring.models.PlatoDTO;
import com.lapatronaspring.lapatronaspring.models.Suministro;
import com.lapatronaspring.lapatronaspring.models.SuministroDTO;
import com.lapatronaspring.lapatronaspring.repositories.CategoriaRepositorio;
import com.lapatronaspring.lapatronaspring.repositories.SuministroRepositorio;

@Service
public class SuministroServicio {

    @Autowired
    private SuministroRepositorio suministroRepositorio;
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    public boolean crear(SuministroDTO dto) {

        Optional<Categoria> categoriaOpt = categoriaRepositorio.findById(dto.getIdCategoria());
        if (!categoriaOpt.isPresent()) {
            throw new RuntimeException("Categoría no encontrada");
        }

        // mapeo
        Suministro suministro = new Suministro();
        suministro.setNombre(dto.getNombre());
        suministro.setStock(dto.getStock());
        suministro.setUnidad(dto.getUnidad());
        suministro.setEstado(dto.isEstado());
        suministro.setFechaEliminado(dto.getFechaEliminado());
        suministro.setCategoria(categoriaOpt.get());

        suministro = suministroRepositorio.save(suministro);
        return true;

    }

    // Obtener todos
    public List<SuministroDTO> obtenerTodos() {
        return suministroRepositorio.findAll()
                .stream()
                .map(this::toDTO) //
                .collect(Collectors.toList());
    }

    public List<SuministroDTO> obtenerTodosActivos() {
        return suministroRepositorio.findAll()
                .stream()
                .filter(p -> Boolean.TRUE.equals(p.getEstado())) // estado == true
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Obtener por ID
    public Optional<SuministroDTO> obtenerPorId(Long id) {
        return suministroRepositorio.findById(id)
                .map(this::toDTO);
    }

    // Actualizar
    public boolean actualizar(Long id, SuministroDTO dto) {
        Optional<Suministro> suministroOpt = suministroRepositorio.findById(id);
        Optional<Categoria> categoriaOpt = categoriaRepositorio.findById(dto.getIdCategoria());

        if (suministroOpt.isPresent() && categoriaOpt.isPresent()) {
            Suministro suministro = suministroOpt.get();
            suministro.setNombre(dto.getNombre());
            suministro.setStock(dto.getStock());
            suministro.setUnidad(dto.getUnidad());
            suministro.setEstado(dto.isEstado());
            suministro.setFechaEliminado(dto.getFechaEliminado());
            suministro.setCategoria(categoriaOpt.get());

            suministroRepositorio.save(suministro);
            return true;
        }

        return false;
    }

    // Eliminar
    public boolean eliminar(Long id) {
        if (suministroRepositorio.existsById(id)) {
            suministroRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean cambiarEstado(Long id, boolean estado) {
        Optional<Suministro> suministroOpt = suministroRepositorio.findById(id);
        if (suministroOpt.isPresent()) {
            Suministro suministro = suministroOpt.get();
            suministro.setEstado(estado);
            suministroRepositorio.save(suministro);
            return true;

        } else {
            return false;
        }

    }

    // mapeo
    private SuministroDTO toDTO(Suministro s) {
        SuministroDTO dto = new SuministroDTO();
        dto.setIdSuministro(s.getIdSuministro());
        dto.setNombre(s.getNombre());
        dto.setStock(s.getStock());
        dto.setUnidad(s.getUnidad());
        dto.setEstado(s.getEstado());
        dto.setFechaEliminado(s.getFechaEliminado());
        dto.setIdCategoria(s.getCategoria().getIdCategoria());
        dto.setCategoria(s.getCategoria().getNombre());
        return dto;
    }

}
