package com.example.demo.service.Impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final VendorRepository vendorRepository;
    private final DepreciationRuleRepository depreciationRuleRepository;

    public AssetServiceImpl(AssetRepository assetRepository,
                            VendorRepository vendorRepository,
                            DepreciationRuleRepository depreciationRuleRepository) {
        this.assetRepository = assetRepository;
        this.vendorRepository = vendorRepository;
        this.depreciationRuleRepository = depreciationRuleRepository;
    }

    @Override
    public Asset createAsset(Long vendorId, Long depreciationRuleId, Asset asset) {

        Vendor vendor = vendorRepository.findById(vendorId).orElse(null);
        DepreciationRule rule = depreciationRuleRepository.findById(depreciationRuleId).orElse(null);

        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);

        if (asset.getPurchaseCost() == null) asset.setPurchaseCost(0.0);
        if (asset.getStatus() == null) asset.setStatus("NEW");
        asset.setCreatedAt(LocalDateTime.now());

        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetById(Long id) {
        return assetRepository.findById(id).orElse(null);
    }
}
