package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.example.demo.entity.AssetDisposal;  // ← THIS IMPORT WAS MISSING

@Schema(name = "AssetDisposalResponseDto")
public class AssetDisposalResponse {

    private Long id;
    private Long assetId;
    private String assetName;  // Optional: if Asset has name field
    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;
    private String approvedBy;  // Username or full name
    private LocalDateTime createdAt;

    public AssetDisposalResponse() {}

    // Constructor from AssetDisposal entity
    public AssetDisposalResponse(AssetDisposal entity) {
        if (entity != null) {
            this.id = entity.getId();
            if (entity.getAsset() != null) {
                this.assetId = entity.getAsset().getId();
                // this.assetName = entity.getAsset().getName(); // Uncomment if Asset has name
            }
            this.disposalMethod = entity.getDisposalMethod();
            this.disposalValue = entity.getDisposalValue();
            this.disposalDate = entity.getDisposalDate();
            if (entity.getApprovedBy() != null) {
                this.approvedBy = entity.getApprovedBy().getUsername();  // Assuming User has username
            }
            this.createdAt = entity.getCreatedAt();
        }
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