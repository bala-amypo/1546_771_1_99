package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;

@Schema(name = "AssetDisposalCreateRequest")  // This name MUST be different
public class AssetDisposalRequest {

    private String disposalMethod;

    @DecimalMin(value = "0.0", message = "disposalValue must be >= 0")
    private Double disposalValue;

    public String getDisposalMethod() {
        return disposalMethod;
    }

    public void setDisposalMethod(String disposalMethod) {
        this.disposalMethod = disposalMethod;
    }

    public Double getDisposalValue() {
        return disposalValue;
    }

    public void setDisposalValue(Double disposalValue) {
        this.disposalValue = disposalValue;
    }
}