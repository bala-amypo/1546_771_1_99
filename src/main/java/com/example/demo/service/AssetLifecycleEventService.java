package com.example.demo.service;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AssetLifecycleEventService {

    @Autowired
    private AssetLifecycleEventRepository eventRepository;

    @Autowired
    private AssetRepository assetRepository;

    public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event) {
        if (event.getEventDate() != null && event.getEventDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("Event date cannot be in the future");
        }
        if (event.getEventDescription() == null || event.getEventDescription().trim().isEmpty()) {
            throw new RuntimeException("Event description is required");
        }

        var asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        event.setAsset(asset);
        return eventRepository.save(event);
    }

    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {
        return eventRepository.findByAssetIdOrderByEventDateDesc(assetId);
    }
}