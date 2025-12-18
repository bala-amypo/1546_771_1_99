package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private Double rate;
    private Integer usefulLife; 

    
    @OneToMany(mappedBy = "depreciationRule", cascade = CascadeType.ALL)
    private List<Asset> assets;

    
    public DepreciationRule() {
    }


    public DepreciationRule(Long id, String ruleName, Double rate, Integer usefulLife) {
        this.id = id;
        this.ruleName = ruleName;
        this.rate = rate;
        this.usefulLife = usefulLife;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getUsefulLife() {
        return usefulLife;
    }

    public void setUsefulLife(Integer usefulLife) {
        this.usefulLife = usefulLife;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
