package com.lapatronaspring.lapatronaspring.services;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lapatronaspring.lapatronaspring.models.CategoriaPlato;
import com.lapatronaspring.lapatronaspring.models.Plato;
import com.lapatronaspring.lapatronaspring.models.PlatoDTO;
import com.lapatronaspring.lapatronaspring.repositories.CategoriaPlatoRepositorio;
import com.lapatronaspring.lapatronaspring.repositories.PlatoRepositorio;
import java.time.LocalDateTime;


@Service
public class PlatoServicio {

    @Autowired
    private PlatoRepositorio platoRepositorio;
    @Autowired
    private CategoriaPlatoRepositorio categoriaPlatoRepositorio;

    // Crear plato
    public boolean crear(PlatoDTO dto) {
        Optional<CategoriaPlato> categoriaOpt = categoriaPlatoRepositorio.findById(dto.getIdCategoria());
        if (!categoriaOpt.isPresent()) {
            throw new RuntimeException("Categoría no encontrada");
        }

        Plato plato = new Plato();
        plato.setNombre(dto.getNombre());
        plato.setDescripcion(dto.getDescripcion());
        plato.setEstado(dto.isEstado());
        plato.setStock(dto.getStock());
        plato.setPrecio(dto.getPrecio());
        plato.setImgRuta(dto.getImgRuta());
        plato.setFechaRegistro(LocalDateTime.now());
        plato.setFechaEliminado(dto.getFechaEliminado());
        plato.setCategoriaPlato(categoriaOpt.get());

        platoRepositorio.save(plato);
        return true;
    }

    // Obtener todos los platos
    public List<PlatoDTO> obtenerTodos() {
        return platoRepositorio.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
   public List<PlatoDTO> obtenerTodosActivos() {
    return platoRepositorio.findAll()
            .stream()
            .filter(plato -> Boolean.TRUE.equals(plato.isEstado())) // estado == true
            .map(this::toDTO)
            .collect(Collectors.toList());
}

    // Obtener plato por ID
    public Optional<PlatoDTO> obtenerPorId(Long id) {
        return platoRepositorio.findById(id)
                .map(this::toDTO);
    }

    // Actualizar plato
    public boolean actualizar(Long id, PlatoDTO dto) {
        Optional<Plato> platoOpt = platoRepositorio.findById(id);
        Optional<CategoriaPlato> categoriaOpt = categoriaPlatoRepositorio.findById(dto.getIdCategoria());

        if (platoOpt.isPresent() && categoriaOpt.isPresent()) {
            Plato plato = platoOpt.get();
            plato.setNombre(dto.getNombre());
            plato.setDescripcion(dto.getDescripcion());
            plato.setEstado(dto.isEstado());
            plato.setStock(dto.getStock());
            plato.setPrecio(dto.getPrecio());
            plato.setImgRuta(dto.getImgRuta());
            plato.setFechaEliminado(dto.getFechaEliminado());
            plato.setCategoriaPlato(categoriaOpt.get());

            platoRepositorio.save(plato);
            return true;
        }
        return false;
    }

    // Eliminar plato
    public boolean eliminar(Long id) {
        if (platoRepositorio.existsById(id)) {
            platoRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    // Cambiar estado del plato
    public boolean cambiarEstado(Long id, boolean estado) {
        Optional<Plato> platoOpt = platoRepositorio.findById(id);
        if (platoOpt.isPresent()) {
            Plato plato = platoOpt.get();
            plato.setEstado(estado);
            platoRepositorio.save(plato);
            return true;
        }
        return false;
    }

    // Mapeo de entidad a DTO
    private PlatoDTO toDTO(Plato plato) {
        PlatoDTO dto = new PlatoDTO();
        dto.setIdPlato(plato.getIdPlato());
        dto.setNombre(plato.getNombre());
        dto.setDescripcion(plato.getDescripcion());
        dto.setEstado(plato.isEstado());
        dto.setStock(plato.getStock());
        dto.setPrecio(plato.getPrecio());
        dto.setImgRuta(plato.getImgRuta());
        dto.setFechaRegistro(plato.getFechaRegistro());
        dto.setFechaEliminado(plato.getFechaEliminado());
        dto.setIdCategoria(plato.getCategoriaPlato().getIdCategoriaPlato());
        dto.setCategoria(plato.getCategoriaPlato().getNombre());
        return dto;
    }
}