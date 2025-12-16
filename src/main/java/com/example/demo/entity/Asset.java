package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
public class Asset {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String assetTag;

    private String assetName;
    private Double purchaseCost;
    private String status = "ACTIVE";

    private LocalDate purchaseDate;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private DepreciationRule depreciationRule;

    // Constructors
    public Asset() {}

    public Asset(Long id, String assetTag, String assetName,
                 Double purchaseCost, String status,
                 LocalDate purchaseDate, LocalDateTime createdAt,
                 Vendor vendor, DepreciationRule depreciationRule) {
        this.id = id;
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.purchaseCost = purchaseCost;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.createdAt = createdAt;
        this.vendor = vendor;
        this.depreciationRule = depreciationRule;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(Double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public DepreciationRule getDepreciationRule() {
        return depreciationRule;
    }

    public void setDepreciationRule(DepreciationRule depreciationRule) {
        this.depreciationRule = depreciationRule;
    }
}