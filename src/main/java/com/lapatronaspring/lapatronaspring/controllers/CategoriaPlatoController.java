package com.lapatronaspring.lapatronaspring.controllers;
import org.springframework.web.bind.annotation.*;

import com.lapatronaspring.lapatronaspring.models.Categoria;
import com.lapatronaspring.lapatronaspring.services.CategoriaPlatoServicio;
import com.lapatronaspring.lapatronaspring.services.CategoriaServicio;

import java.util.List;

import com.lapatronaspring.lapatronaspring.models.CategoriaPlato;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/categoriasPlato")
public class CategoriaPlatoController {

    private final CategoriaPlatoServicio categoriaService;

    public CategoriaPlatoController(CategoriaPlatoServicio categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<CategoriaPlato> listar() {
        return categoriaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public CategoriaPlato obtenerPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    @PostMapping
    public CategoriaPlato crear(@RequestBody CategoriaPlato categoria) {
        return categoriaService.guardar(categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
    }
}
