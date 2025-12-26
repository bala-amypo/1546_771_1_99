package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Component
public class JwtUtil {

    // Must be at least 32 characters long for HS256
    private final String SECRET_KEY = "your-very-secure-secret-key-at-least-32-characters-long";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // Method signature required by your project constraints
    public String generateToken(String email, Long userId, Set<String> roles) {
        return Jwts.builder()
                .subject(email) // Modern syntax for 0.12.x+
                .claim("userId", userId)
                .claim("email", email)
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(key) // Modern syntax for 0.12.x+
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key) // Modern syntax for 0.12.x+
                .build()
                .parseSignedClaims(token)
                .getPayload(); // In 0.12.x+, getPayload() replaces getBody()
    }
}