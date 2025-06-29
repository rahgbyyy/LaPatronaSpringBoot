package com.lapatronaspring.lapatronaspring.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // genera el constructor con todos los campos
@NoArgsConstructor  // constructor vacío (por si lo necesitas también)


public class UsuarioLoginDTO {
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String codigo;
    private boolean estado;
    private Long idrol;
    private String rol;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Long getIdrol() {
        return idrol;
    }

    public void setIdrol(Long idrol) {
        this.idrol = idrol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
