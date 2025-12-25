package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Component
public class JwtUtil {

    private final String SECRET = "my-secret-key-my-secret-key-my-secret-key";
    private final long EXPIRATION_MS = 1000 * 60 * 60 * 5; // 5 hours

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ===================== GENERATE TOKEN =====================
    public String generateToken(String email, Long userId, Set<String> roles) {

        return Jwts.builder()
                .setClaims(Map.of(
                        "email", email,
                        "userId", userId,
                        "roles", roles
                ))
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ===================== PARSE CLAIMS =====================
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ===================== VALIDATE =====================
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
