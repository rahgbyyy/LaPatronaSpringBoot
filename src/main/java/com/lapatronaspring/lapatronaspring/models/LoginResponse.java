// src/main/java/com/example/secur/dto/JwtResponseDTO.java
package com.lapatronaspring.lapatronaspring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos

public class LoginResponse {
    private String token;
    private UsuarioLoginDTO usuario;


    public LoginResponse(String token, UsuarioLoginDTO usuario) {
        this.token = token;
        this.usuario = usuario;
    }
    public String getToken() { return token; }
    public UsuarioLoginDTO getUsuario() { return usuario; }
}