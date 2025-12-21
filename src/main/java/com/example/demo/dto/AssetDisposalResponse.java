package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(name = "AssetDisposalResponseDto")
public class AssetDisposalResponse {

    private Long id;
    private Long assetId;
    private String assetName; // optional - if you want to show asset details
    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;
    private String approvedBy; // username or full name
    private LocalDateTime createdAt;

    public AssetDisposalResponse() {}

    public AssetDisposalResponse(AssetDisposal entity) {
        this.id = entity.getId();
        this.assetId = entity.getAsset().getId();
        // this.assetName = entity.getAsset().getName(); // if Asset has name
        this.disposalMethod = entity.getDisposalMethod();
        this.disposalValue = entity.getDisposalValue();
        this.disposalDate = entity.getDisposalDate();
        this.approvedBy = entity.getApprovedBy() != null ? entity.getApprovedBy().getUsername() : null;
        this.createdAt = entity.getCreatedAt();
    }

    // Getters and Setters (all fields)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAssetId() { return assetId; }
    public void setAssetId(Long assetId) { this.assetId = assetId; }
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