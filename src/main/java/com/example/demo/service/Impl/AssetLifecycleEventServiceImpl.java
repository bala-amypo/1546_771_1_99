package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {

    private final AssetLifecycleEventRepository eventRepo;
    private final AssetRepository assetRepo;

    public AssetLifecycleEventServiceImpl(AssetLifecycleEventRepository eventRepo,
                                          AssetRepository assetRepo) {
        this.eventRepo = eventRepo;
        this.assetRepo = assetRepo;
    }

    @Override
    public AssetLifecycleEvent addEvent(Long assetId, AssetLifecycleEvent event) {
        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
        event.setAsset(asset);
        return eventRepo.save(event);
    }

    @Override
    public List<AssetLifecycleEvent> getEventsByAsset(Long assetId) {
        return eventRepo.findByAssetId(assetId);
    }
}
