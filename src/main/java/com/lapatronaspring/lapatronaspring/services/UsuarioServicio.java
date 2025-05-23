package com.lapatronaspring.lapatronaspring.services;

import com.lapatronaspring.lapatronaspring.models.Usuario;
import com.lapatronaspring.lapatronaspring.models.UsuarioDTO;
import com.lapatronaspring.lapatronaspring.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEstado(usuarioDTO.isEstado());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setCodigo(usuarioDTO.getCodigo());
        usuario.setTipodoc(usuarioDTO.getTipodoc());
        usuario.setDocumento(usuarioDTO.getDocumento());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setFechaingreso(usuarioDTO.getFechaingreso());
        usuario.setSueldo(usuarioDTO.getSueldo());
        usuario.setFechaeliminado(usuarioDTO.getFechaeliminado());

        return usuarioRepositorio.save(usuario);
    }

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long idUsuario) {
        return usuarioRepositorio.findById(idUsuario);
    }

    public boolean actualizarUsuario(Long idUsuario, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setApellido(usuarioDTO.getApellido());
            usuario.setEstado(usuarioDTO.isEstado());
            usuario.setPassword(usuarioDTO.getPassword());
            usuario.setCodigo(usuarioDTO.getCodigo());
            usuario.setTipodoc(usuarioDTO.getTipodoc());
            usuario.setDocumento(usuarioDTO.getDocumento());
            usuario.setCorreo(usuarioDTO.getCorreo());
            usuario.setTelefono(usuarioDTO.getTelefono());
            usuario.setFechaingreso(usuarioDTO.getFechaingreso());
            usuario.setSueldo(usuarioDTO.getSueldo());
            usuario.setFechaeliminado(usuarioDTO.getFechaeliminado());

            usuarioRepositorio.save(usuario);
            return true;
        }
        return false;
    }

    public boolean eliminarUsuario(Long idUsuario) {
        if (usuarioRepositorio.existsById(idUsuario)) {
            usuarioRepositorio.deleteById(idUsuario);
            return true;
        }
        return false;
    }

    // Método adicional para desactivar usuario (cambiar estado)
    public boolean desactivarUsuario(Long idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setEstado(false);
            usuario.setFechaeliminado(new Date());
            usuarioRepositorio.save(usuario);
            return true;
        }
        return false;
    }
}