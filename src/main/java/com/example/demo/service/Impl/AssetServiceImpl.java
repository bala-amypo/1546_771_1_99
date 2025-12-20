package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository repository;

    public AssetServiceImpl(AssetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Asset createAsset(Long vendorId, Long ruleId, Asset asset) {
        asset.setVendorId(vendorId);
        asset.setRuleId(ruleId);
        return repository.save(asset); // INSERTS INTO asset TABLE
    }

    @Override
    public List<Asset> getAllAssets() {
        return repository.findAll();
    }
}
