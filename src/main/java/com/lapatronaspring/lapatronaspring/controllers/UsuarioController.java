package com.lapatronaspring.lapatronaspring.controllers;

import com.lapatronaspring.lapatronaspring.models.Usuario;
import com.lapatronaspring.lapatronaspring.models.UsuarioDTO;
import com.lapatronaspring.lapatronaspring.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/guardar")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioCreado = usuarioServicio.crearUsuario(usuarioDTO);
        return ResponseEntity.ok(usuarioCreado);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> obtenerTodosUsuarios() {
        return ResponseEntity.ok(usuarioServicio.obtenerTodosUsuarios());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long idUsuario) {
        Optional<Usuario> usuario = usuarioServicio.obtenerUsuarioPorId(idUsuario);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("actualizar/{idUsuario}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable Long idUsuario,
                                                    @RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioServicio.actualizarUsuario(idUsuario, usuarioDTO)) {
            return ResponseEntity.ok("Usuario actualizado exitosamente");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long idUsuario) {
        if (usuarioServicio.eliminarUsuario(idUsuario)) {
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{idUsuario}/desactivar")
    public ResponseEntity<String> desactivarUsuario(@PathVariable Long idUsuario) {
        if (usuarioServicio.desactivarUsuario(idUsuario)) {
            return ResponseEntity.ok("Usuario desactivado exitosamente");
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = usuarioServicio.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", ex.getMessage()));
        }
    }
}