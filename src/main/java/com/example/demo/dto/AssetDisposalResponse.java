package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.entity.AssetDisposal;

public class AssetDisposalResponse {

    private Long id;
    private Long assetId;
    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;
    private Long approvedBy;
    private LocalDateTime createdAt;

    public AssetDisposalResponse(AssetDisposal entity) {
        this.id = entity.getId();
        this.assetId = entity.getAsset().getId();   // ✅ FIX
        this.disposalMethod = entity.getDisposalMethod();
        this.disposalValue = entity.getDisposalValue();
        this.disposalDate = entity.getDisposalDate();
        this.approvedBy = entity.getApprovedBy() != null
                ? entity.getApprovedBy().getId()
                : null;                              // ✅ FIX
        this.createdAt = entity.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public Long getAssetId() {
        return assetId;
    }

    public String getDisposalMethod() {
        return disposalMethod;
    }

    public Double getDisposalValue() {
        return disposalValue;
    }

    public LocalDate getDisposalDate() {
        return disposalDate;
    }

    public Long getApprovedBy() {
        return approvedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
