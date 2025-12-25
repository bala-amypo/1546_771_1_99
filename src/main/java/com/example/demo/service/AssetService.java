package com.example.demo.service;

import com.example.demo.entity.Asset;

import java.util.List;

public interface AssetService {
    Asset createAsset(Long vendorId, Long depreciationRuleId, Asset asset);
    List<Asset> getAssets();
    Asset getAssetById(Long id);
}
