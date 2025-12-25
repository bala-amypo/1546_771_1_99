package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
    name = "depreciation_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    @Column(nullable = false)
    private String method; // STRAIGHT_LINE / DECLINING_BALANCE

    private Integer usefulLifeYears;
    private Double salvageValue;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "depreciationRule")
    private List<Asset> assets;

    public DepreciationRule() {}

    public DepreciationRule(String ruleName, String method, Integer usefulLifeYears, Double salvageValue) {
        this.ruleName = ruleName;
        this.method = method;
        this.usefulLifeYears = usefulLifeYears;
        this.salvageValue = salvageValue;
    }

    // getters and setters
}
