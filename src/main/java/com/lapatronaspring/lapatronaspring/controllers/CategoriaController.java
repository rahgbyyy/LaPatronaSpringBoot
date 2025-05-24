package com.lapatronaspring.lapatronaspring.controllers;
import org.springframework.web.bind.annotation.*;
import com.lapatronaspring.lapatronaspring.models.Categoria;
import com.lapatronaspring.lapatronaspring.services.CategoriaServicio;

import java.util.List;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {

    private final CategoriaServicio categoriaService;

    public CategoriaController(CategoriaServicio categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Categoria obtenerPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return categoriaService.guardar(categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
    }
}
