package com.example.demo.service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AssetLifecycleEventService {
    @Autowired private AssetLifecycleEventRepository eventRepository;
    @Autowired private AssetRepository assetRepository;

    public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event) {
        Asset asset = assetRepository.findById(assetId).orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        if (event.getEventDate().isAfter(LocalDate.now())) throw new BadRequestException("Date cannot be future");
        if (event.getEventDescription() == null || event.getEventDescription().isEmpty()) throw new BadRequestException("Description required");
        
        event.setAsset(asset);
        return eventRepository.save(event);
    }

    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {
        return eventRepository.findByAssetIdOrderByEventDateDesc(assetId);
    }
}