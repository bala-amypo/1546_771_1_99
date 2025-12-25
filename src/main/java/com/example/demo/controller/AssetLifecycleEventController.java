package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {

    private final AssetLifecycleEventRepository eventRepository;
    private final AssetRepository assetRepository;

    public AssetLifecycleEventController(AssetLifecycleEventRepository eventRepository,
                                         AssetRepository assetRepository) {
        this.eventRepository = eventRepository;
        this.assetRepository = assetRepository;
    }

    @PostMapping("/{assetId}")
    public ResponseEntity<AssetLifecycleEvent> logEvent(@PathVariable Long assetId,
                                                        @RequestBody AssetLifecycleEvent event) {
        Asset asset = assetRepository.findById(assetId).orElseThrow();
        event.setAsset(asset);
        return ResponseEntity.ok(eventRepository.save(event));
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getByAsset(@PathVariable Long assetId) {
        return ResponseEntity.ok(eventRepository.findByAssetIdOrderByEventDateDesc(assetId));
    }
}