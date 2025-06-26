// src/main/java/com/example/secur/dto/JwtResponseDTO.java
package com.lapatronaspring.lapatronaspring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
public class JwtResponseDTO {
    private String token;
    private UsuarioDTO usuario;
    public LoginResponse(String token, UsuarioDTO usuario){
        this.token = token;
        this.usuario = usuario;
        }

    public String getToken() {return token;}
    public UsuarioDTO getUsuario() {return usuario;}     
}