package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;        // getEmail()

    @Column(nullable = false)
    private String password;     // getPassword()

    @Column(nullable = false)
    private String username;     // getUsername()

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;   // getRoles()
}
