package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired private AssetRepository assetRepository;
    @Autowired private VendorRepository vendorRepository;
    @Autowired private DepreciationRuleRepository ruleRepository;

    @Override
    public Asset createAsset(Long vendorId, Long ruleId, Asset asset) {
        // Step 1 Constraint: purchaseCost > 0
        if (asset.getPurchaseCost() == null || asset.getPurchaseCost() <= 0) {
            throw new BadRequestException("Purchase cost must be positive");
        }
        // Step 1 Constraint: assetTag must be unique
        if (assetRepository.existsByAssetTag(asset.getAssetTag())) {
            throw new BadRequestException("Asset tag already exists");
        }

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        DepreciationRule rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);
        asset.setStatus("ACTIVE"); // Step 1 Rule: status defaults to ACTIVE
        
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAssetsByStatus(String status) {
        return assetRepository.findByStatus(status);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAsset(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
    }
}