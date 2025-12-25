package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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
    public ResponseEntity<?> logEvent(@PathVariable Long assetId,
                                     @Valid @RequestBody AssetLifecycleEvent event) {
        try {
            if (event.getEventDate().isAfter(LocalDate.now())) {
                return ResponseEntity.status(401).body("Event date cannot be in the future");
            }
            if (event.getEventDescription() == null || event.getEventDescription().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Event description is required");
            }

            Asset asset = assetRepository.findById(assetId)
                    .orElseThrow(() -> new RuntimeException("Asset not found"));

            event.setAsset(asset);
            AssetLifecycleEvent saved = eventRepository.save(event);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getEventsForAsset(@PathVariable Long assetId) {
        return ResponseEntity.ok(eventRepository.findByAssetIdOrderByEventDateDesc(assetId));
    }
}
