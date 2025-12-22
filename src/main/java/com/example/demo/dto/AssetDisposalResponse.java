package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.example.demo.entity.AssetDisposal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(name = "AssetDisposalResponseDto")  // Unique name
public class AssetDisposalResponse {

    private Long id;
    private Long assetId;
    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;
    private String approvedBy;
    private LocalDateTime createdAt;

    public AssetDisposalResponse() {}

    public AssetDisposalResponse(AssetDisposal entity) {
        this.id = entity.getId();
        this.assetId = entity.getAsset() != null ? entity.getAsset().getId() : null;
        this.disposalMethod = entity.getDisposalMethod();
        this.disposalValue = entity.getDisposalValue();
        this.disposalDate = entity.getDisposalDate();
        this.approvedBy = entity.getApprovedBy() != null 
                ? entity.getApprovedBy().getName()  // or .getUsername() if you have it
                : null;
        this.createdAt = entity.getCreatedAt();
    }

    // Getters and Setters
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