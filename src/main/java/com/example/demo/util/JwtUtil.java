

package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.Set;

// @Component
// public class JwtUtil {
//     private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//     public String generateToken(String email, Long userId, Set<String> roles) {
//         return Jwts.builder()
//                 .setSubject(email)
//                 .claim("email", email)
//                 .claim("userId", userId)
//                 .claim("roles", roles)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + 3600000))
//                 .signWith(key).compact();
//     }
//     public boolean validateToken(String token) {
//         try { Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); return true; } catch (Exception e) { return false; }
//     }
//     public Claims getClaims(String token) {
//         return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//     }
    @Component
public class JwtUtil {

    private String secret = "mysecretkey";

    public String extractUsername(String token) {
        return io.jsonwebtoken.Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean validateToken(String token, User userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }

    public String generateToken(String username) {
        return io.jsonwebtoken.Jwts.builder()
                .setSubject(username)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret)
                .compact();
    }
}


