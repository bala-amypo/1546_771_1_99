package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Fix findByEmail issue
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    // Example: create user (make sure ID is Long)
    public User saveUser(User user) {
        // Ensure ID is Long
        if (user.getId() == null) {
            user.setId(null); // Let JPA generate ID
        }
        return userRepository.save(user);
    }
}
