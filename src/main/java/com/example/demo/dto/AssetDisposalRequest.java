package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "AssetDisposalCreateRequest")  // Unique name - fixes wrong example in Swagger
@JsonIgnoreProperties(ignoreUnknown = true)   // Extra local safety
public class AssetDisposalRequest {

    private String reason;

    @NotNull(message = "assetId is required")
    private Long assetId;

    private Long requestedBy;

    // Getters and Setters (unchanged)
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public Long getAssetId() { return assetId; }
    public void setAssetId(Long assetId) { this.assetId = assetId; }
    public Long getRequestedBy() { return requestedBy; }
    public void setRequestedBy(Long requestedBy) { this.requestedBy = requestedBy; }
}