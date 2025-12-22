package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AssetDisposalCreateRequest")  // Unique name - prevents Swagger error
public class AssetDisposalRequest {

    private String disposalMethod;
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