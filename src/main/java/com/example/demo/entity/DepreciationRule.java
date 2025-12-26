package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleName;

    private String method;
    private int usefulLifeYears;
    private double salvageValue;

    public Long getId() { return id; }
    public String getRuleName() { return ruleName; }

    public void setRuleName(String ruleName) { this.ruleName = ruleName; }
    public void setMethod(String method) { this.method = method; }
    public void setUsefulLifeYears(int usefulLifeYears) { this.usefulLifeYears = usefulLifeYears; }
    public void setSalvageValue(double salvageValue) { this.salvageValue = salvageValue; }
}
