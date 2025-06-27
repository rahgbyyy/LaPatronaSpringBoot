// src/main/java/com/example/secur/dto/UserLoginDTO.java
package com.lapatronaspring.lapatronaspring.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
public class LoginRequest {

public String codigo;
public String password;

public String getCodigo() {return codigo;}
public void setCodigo(String codigo){this.codigo = codigo;}
public String getPassword () {return password;}
public void setPassword(String password) {this.password = password;}
}