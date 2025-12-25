package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        User user = new User();
        user.setEmail(body.get("email"));
        user.setName(body.get("name"));
        user.setPassword(passwordEncoder.encode(body.get("password")));
        user.getRoles().add(userRepository.findById(2L).orElseThrow()); // default USER role (id=2 after setup)
        User saved = userRepository.save(user);
        return ResponseEntity.ok(Map.of("email", saved.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        User user = userRepository.findByEmail(body.get("email")).orElseThrow();
        if (!passwordEncoder.matches(body.get("password"), user.getPassword())) {
            return ResponseEntity.status(401).build();
        }
        var roles = new java.util.HashSet<String>();
        user.getRoles().forEach(r -> roles.add(r.getName()));
        String token = jwtUtil.generateToken(user.getEmail(), user.getId(), roles);
        return ResponseEntity.ok(Map.of("token", token));
    }
}