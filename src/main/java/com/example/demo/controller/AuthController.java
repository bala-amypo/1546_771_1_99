

package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) { // only inject UserService
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {

        Optional<User> optionalUser = userService.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return "Invalid email or password";
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(password)) { // simple plain-text check
            return "Invalid email or password";
        }

        return "Login successful for " + user.getEmail();
    }
}
