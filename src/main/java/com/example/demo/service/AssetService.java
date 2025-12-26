package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssetService {
    @Autowired private AssetRepository assetRepository;
    @Autowired private VendorRepository vendorRepository;
    @Autowired private DepreciationRuleRepository ruleRepository;

    public Asset createAsset(Long vendorId, Long ruleId, Asset asset) {
        if (asset.getPurchaseCost() <= 0) throw new BadRequestException("Purchase cost must be positive");
        if (assetRepository.existsByAssetTag(asset.getAssetTag())) throw new BadRequestException("Asset tag already exists");

        Vendor v = vendorRepository.findById(vendorId).orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        DepreciationRule r = ruleRepository.findById(ruleId).orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        asset.setVendor(v);
        asset.setDepreciationRule(r);
        asset.setStatus("ACTIVE"); // Default requirement
        return assetRepository.save(asset);
    }

    public List<Asset> getAllAssets() { return assetRepository.findAll(); }
    public List<Asset> getAssetsByStatus(String status) { return assetRepository.findByStatus(status); }
    public Asset getAsset(Long id) { return assetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Asset not found")); }
}