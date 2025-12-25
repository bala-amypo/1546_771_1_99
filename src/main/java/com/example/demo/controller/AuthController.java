package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> payload) {
        User user = new User();
        user.setEmail(payload.get("email"));
        user.setName(payload.get("name"));
        user.setPassword(passwordEncoder.encode(payload.get("password")));

        Role userRole = new Role("USER");
        user.getRoles().add(userRole);

        User saved = userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("id", saved.getId());
        response.put("email", saved.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> payload) {
        User user = userRepository.findByEmail(payload.get("email"))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        Set<String> roles = new HashSet<>();
        user.getRoles().forEach(r -> roles.add(r.getName()));

        String token = jwtService.generateToken(user.getEmail(), user.getId(), roles);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}