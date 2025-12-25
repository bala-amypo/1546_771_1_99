package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.Optional;

public interface UserService {

    /**
     * Register a new user (used in AuthController /auth/register)
     * Encodes password and assigns default "USER" role
     */
    User registerUser(User user);

    /**
     * Save or update an existing user
     */
    User save(User user);

    /**
     * Find user by email (used for login and token generation)
     */
    Optional<User> findByEmail(String email);

    /**
     * Find user by ID (used in various services)
     */
    User findById(Long id);
}