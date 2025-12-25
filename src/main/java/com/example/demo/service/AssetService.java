package com.example.demo.service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.Vendor;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private DepreciationRuleRepository ruleRepository;

    public Asset createAsset(Asset asset, Long vendorId, Long ruleId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        DepreciationRule rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new ResourceNotFoundException("Depreciation rule not found"));
        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);
        return assetRepository.save(asset);
    }
}
