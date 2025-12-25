package com.example.demo.service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.AssetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Asset create(Asset asset, Vendor vendor, DepreciationRule rule) {
        // Critical validations from tests: test15 (negative cost), test94 (duplicate tag)
        if (asset.getPurchaseCost() < 0) {
            throw new IllegalArgumentException("Purchase cost cannot be negative");
        }
        if (asset.getAssetTag() == null || asset.getAssetTag().trim().isEmpty()) {
            throw new IllegalArgumentException("Asset tag is required");
        }
        if (assetRepository.existsByAssetTag(asset.getAssetTag().trim())) {
            throw new IllegalArgumentException("Asset tag already exists");
        }

        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);
        asset.setStatus("ACTIVE");

        return assetRepository.save(asset);
    }

    @Override
    public Asset findById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found with id: " + id));
    }
}