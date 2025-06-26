package com.lapatronaspring.lapatronaspring.config;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET = "tu_clave_secreta_muy_segura";
    private final long EXPIRATION_MS = 1000 * 60 * 60 * 10; // 10 horas

    public String generateToken(String codigo) {
        return Jwts.builder()
            .setSubject(codigo)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }

    public String extractCodigo(String token) {
        return Jwts.parser()
                   .setSigningKey(SECRET)
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }
}