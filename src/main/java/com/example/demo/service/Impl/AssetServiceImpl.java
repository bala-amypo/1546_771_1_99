package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepo;
    private final VendorRepository vendorRepo;
    private final DepreciationRuleRepository ruleRepo;

    public AssetServiceImpl(AssetRepository assetRepo,
                            VendorRepository vendorRepo,
                            DepreciationRuleRepository ruleRepo) {
        this.assetRepo = assetRepo;
        this.vendorRepo = vendorRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public Asset createAsset(Long vendorId, Long ruleId, Asset asset) {

        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        DepreciationRule rule = ruleRepo.findById(ruleId)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        // ✅ THESE WILL NOW WORK
        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);

        return assetRepo.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepo.findAll();
    }

    @Override
    public Asset getAsset(Long id) {
        return assetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
    }

    @Override
    public List<Asset> getAssetsByStatus(String status) {
        return assetRepo.findByStatus(status);
    }
}
