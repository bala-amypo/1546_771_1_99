package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.example.demo.entity.AssetDisposal;      // ← Fixed: import entity
import java.time.LocalDate;                     // ← Fixed: import LocalDate
import java.time.LocalDateTime;                 // ← Fixed: import LocalDateTime

@Schema(name = "AssetDisposalResponseDto")
public class AssetDisposalResponse {

    private Long id;
    private Long assetId;
    private String assetName;         // optional - if your Asset has a name field
    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;
    private String approvedBy;        // we will use name or username here
    private LocalDateTime createdAt;

    public AssetDisposalResponse() {}

    // Constructor mapping from entity
    public AssetDisposalResponse(AssetDisposal entity) {
        this.id = entity.getId();
        this.assetId = entity.getAsset() != null ? entity.getAsset().getId() : null;
        // Uncomment if your Asset entity has a 'name' field
        // this.assetName = entity.getAsset() != null ? entity.getAsset().getName() : null;

        this.disposalMethod = entity.getDisposalMethod();
        this.disposalValue = entity.getDisposalValue();
        this.disposalDate = entity.getDisposalDate();

        // Fixed: Use a method that exists in your User entity
        // Common options: getName(), getFullName(), getEmail(), getUsername()
        this.approvedBy = entity.getApprovedBy() != null 
                ? entity.getApprovedBy().getName()  // ← Change to your actual method
                : null;

        this.createdAt = entity.getCreatedAt();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAssetId() { return assetId; }
    public void setAssetId(Long assetId) { this.assetId = assetId; }

    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }

    public String getDisposalMethod() { return disposalMethod; }
    public void setDisposalMethod(String disposalMethod) { this.disposalMethod = disposalMethod; }

    public Double getDisposalValue() { return disposalValue; }
    public void setDisposalValue(Double disposalValue) { this.disposalValue = disposalValue; }

    public LocalDate getDisposalDate() { return disposalDate; }
    public void setDisposalDate(LocalDate disposalDate) { this.disposalDate = disposalDate; }

    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}