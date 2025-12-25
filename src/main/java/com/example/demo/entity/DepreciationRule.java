package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int usefulLifeYears;   // getUsefulLifeYears()

    private double salvageValue;   // getSalvageValue()

    private String method;         // getMethod()

    private LocalDateTime createdAt; // setCreatedAt()
}
