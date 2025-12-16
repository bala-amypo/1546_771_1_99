package com.example.demo.entity;

import java.time.LocalDateTime;

public class User {
    private long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;

public User(){

}

public User(String name, String email, String password, LocalDateTime createdAt) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.createdAt = createdAt;
}

public void setId(long id) {
    this.id = id;
}

public void setName(String name) {
    this.name = name;
}

public void setEmail(String email) {
    this.email = email;
}

public void setPassword(String password) {
    this.password = password;
}

public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
}

public long getId() {
    return id;
}

public String getName() {
    return name;
}

public String getEmail() {
    return email;
}

public String getPassword() {
    return password;
}

public LocalDateTime getCreatedAt() {
    return createdAt;
}
}



