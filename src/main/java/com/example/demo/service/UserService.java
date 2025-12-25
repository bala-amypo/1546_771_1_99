package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByEmail(String email);
    User findById(Long id);
}