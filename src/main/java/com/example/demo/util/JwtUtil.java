

// package com.example.demo.util;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;
// import java.security.Key;
// import java.util.Date;
// import java.util.Set;
// import org.springframework.security.core.userdetails.UserDetails;


// // @Component
// // public class JwtUtil {
// //     private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
// //     public String generateToken(String email, Long userId, Set<String> roles) {
// //         return Jwts.builder()
// //                 .setSubject(email)
// //                 .claim("email", email)
// //                 .claim("userId", userId)
// //                 .claim("roles", roles)
// //                 .setIssuedAt(new Date())
// //                 .setExpiration(new Date(System.currentTimeMillis() + 3600000))
// //                 .signWith(key).compact();
// //     }
// //     public boolean validateToken(String token) {
// //         try { Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); return true; } catch (Exception e) { return false; }
// //     }
// //     public Claims getClaims(String token) {
// //         return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
// //     }
  package com.example.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JwtUtil {

    private final String secret = "mysecretkey";
    private final long jwtExpirationMs = 1000 * 60 * 60 * 10; // 10 hours

    // Generate JWT token with claims: email, userId, roles
    public String generateToken(String email, Long userId, Set<String> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Extract email from token
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }
}

