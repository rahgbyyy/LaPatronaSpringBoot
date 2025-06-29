package com.lapatronaspring.lapatronaspring.services;

import com.lapatronaspring.lapatronaspring.config.JwtUtil;
import com.lapatronaspring.lapatronaspring.models.*;
import com.lapatronaspring.lapatronaspring.repositories.UsuarioRepositorio;
import com.lapatronaspring.lapatronaspring.repositories.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private RolRepositorio rolRepositorio;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;


    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEstado(usuarioDTO.isEstado());

        // Encriptar la contraseña antes de setearla
        String encodedPassword = passwordEncoder.encode(usuarioDTO.getPassword());
        usuario.setPassword(encodedPassword);
        System.out.println("Contraseña codificada: " + encodedPassword);

        usuario.setCodigo(usuarioDTO.getCodigo());
        usuario.setTipodoc(usuarioDTO.getTipodoc());
        usuario.setDocumento(usuarioDTO.getDocumento());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setFechaingreso(usuarioDTO.getFechaingreso());
        usuario.setSueldo(usuarioDTO.getSueldo());
        usuario.setFechaeliminado(usuarioDTO.getFechaeliminado());

        if (usuarioDTO.getIdrol() != null) {
            Optional<Rol> rolOptional = rolRepositorio.findById(usuarioDTO.getIdrol());
            if (rolOptional.isPresent()) {
                usuario.setRol(rolOptional.get());
            } else {
                throw new IllegalArgumentException("El rol con ID " + usuarioDTO.getIdrol() + " no existe");
            }
        }

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
            // Actualización de campos básicos
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

            // Actualización del rol
            if(usuarioDTO.getIdrol() != null) {
                Optional<Rol> rolOptional = rolRepositorio.findById(usuarioDTO.getIdrol());
                if(rolOptional.isPresent()) {
                    usuario.setRol(rolOptional.get());
                } else {
                    throw new IllegalArgumentException("El rol con ID " + usuarioDTO.getIdrol() + " no existe");
                }
            } else {
                usuario.setRol(null);
            }

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
 public LoginResponse login(LoginRequest request) {
     Usuario usuario = usuarioRepositorio.findByCodigo(request.getCodigo())
             .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

     if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
         throw new RuntimeException("Contraseña incorrecta");
     }

     String token = jwtUtil.generateToken(usuario.getCodigo(), usuario.getIdusuario());

     UsuarioLoginDTO usuarioLogin = new UsuarioLoginDTO();
     usuarioLogin.setIdUsuario(usuario.getIdusuario());
     usuarioLogin.setNombre(usuario.getNombre());
     usuarioLogin.setApellido(usuario.getApellido());
     usuarioLogin.setCodigo(usuario.getCodigo());
     usuarioLogin.setEstado(usuario.isEstado());
     usuarioLogin.setIdrol(usuario.getRol().getIdRol());
     usuarioLogin.setRol(usuario.getRol().getNombre());

     return new LoginResponse(token, usuarioLogin);

 }
}