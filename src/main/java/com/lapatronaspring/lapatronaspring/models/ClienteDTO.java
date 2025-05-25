package com.lapatronaspring.lapatronaspring.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    @Getter
    private String nombre;
    @Getter
    private String apellido;
    @Getter
    private boolean estado;
    @Getter
    private String password;
    @Getter
    private String direccion;
    @Getter
    private String tipodoc;
    @Getter
    private String documento;
    @Getter
    private String correo;
    @Getter
    private String telefono;
    @Getter
    private Date fechaingreso;
    @Getter
    private Date fechanacimiento;
    private Date fechaeliminado;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getPassword() {
        return password;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTipodoc() {
        return tipodoc;
    }

    public String getDocumento() {
        return documento;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public Date getFechaeliminado() {
        return fechaeliminado;
    }

    public void setFechaeliminado(Date fechaeliminado) {
        this.fechaeliminado = fechaeliminado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
}
