package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "depreciation_rules")
public class DepreciationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ruleName;

    @Column(nullable = false)
    private String method;

    @Min(1)
    @Column(nullable = false)
    private Integer usefulLifeYears;

    @Min(0)
    private Double salvageValue;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public Integer getUsefulLifeYears() { return usefulLifeYears; }
    public void setUsefulLifeYears(Integer usefulLifeYears) { this.usefulLifeYears = usefulLifeYears; }
    public Double getSalvageValue() { return salvageValue; }
    public void setSalvageValue(Double salvageValue) { this.salvageValue = salvageValue; }
}

// Asset.java
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Table(name = "assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String assetTag;

    private String assetName;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    @Min(0)
    @Column(nullable = false)
    private Double purchaseCost;

    @Column(nullable = false)
    private String status = "ACTIVE";

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private DepreciationRule depreciationRule;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAssetTag() { return assetTag; }
    public void setAssetTag(String assetTag) { this.assetTag = assetTag; }
    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    public Double getPurchaseCost() { return purchaseCost; }
    public void setPurchaseCost(Double purchaseCost) { this.purchaseCost = purchaseCost; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    public DepreciationRule getDepreciationRule() { return depreciationRule; }
    public void setDepreciationRule(DepreciationRule depreciationRule) { this.depreciationRule = depreciationRule; }
}