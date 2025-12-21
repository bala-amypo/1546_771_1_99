package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;  // ← Add this import

@Schema(name = "AssetDisposalResponse")  // ← Explicit unique name for response
public class AssetDisposalResponse {

    private Long id;
    private String reason;
    private String status;
    private Long assetId;
    private Long requestedBy;
    private Long approvedBy;

    public AssetDisposalResponse() {}

    public AssetDisposalResponse(com.example.demo.entity.AssetDisposal entity) {
        this.id = entity.getId();
        this.reason = entity.getReason();
        this.status = entity.getStatus();
        this.assetId = entity.getAssetId();
        this.requestedBy = entity.getRequestedBy();
        this.approvedBy = entity.getApprovedBy();
    }

    // Getters and Setters (unchanged)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getAssetId() { return assetId; }
    public void setAssetId(Long assetId) { this.assetId = assetId; }
    public Long getRequestedBy() { return requestedBy; }
    public void setRequestedBy(Long requestedBy) { this.requestedBy = requestedBy; }
    public Long getApprovedBy() { return approvedBy; }
    public void setApprovedBy(Long approvedBy) { this.approvedBy = approvedBy; }
}