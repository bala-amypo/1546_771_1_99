package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service // <-- MUST have this annotation
public class UserService {

    private final List<User> users = new ArrayList<>();
    private int nextId = 1;

    public User createUser(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return users;
    }
}
