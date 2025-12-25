package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class DepreciationRule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String ruleName;

    @NotBlank
    private String method;

    @Min(1)
    private Integer usefulLifeYears;

    @DecimalMin("0.0")
    private Double salvageValue = 0.0;
}