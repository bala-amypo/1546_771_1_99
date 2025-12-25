package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "ruleName"))
public class DepreciationRule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String ruleName;

    @Column(nullable = false)
    private String method; // STRAIGHT_LINE, DECLINING_BALANCE, etc.

    @Min(1)
    private int usefulLifeYears;

    @Min(0)
    private double salvageValue = 0.0;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public int getUsefulLifeYears() { return usefulLifeYears; }
    public void setUsefulLifeYears(int usefulLifeYears) { this.usefulLifeYears = usefulLifeYears; }
    public double getSalvageValue() { return salvageValue; }
    public void setSalvageValue(double salvageValue) { this.salvageValue = salvageValue; }
}