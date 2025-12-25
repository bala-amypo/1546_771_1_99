package com.example.demo.service.Impl;

import com.example.demo.entity.Asset;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Asset createAsset(Asset asset) {

        // Null-safe check for purchaseCost
        if (asset.getPurchaseCost() == null) {
            asset.setPurchaseCost(0.0);
        }

        // Default status if null
        if (asset.getStatus() == null) {
            asset.setStatus("NEW");
        }

        // Set creation time
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
