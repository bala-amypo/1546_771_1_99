package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.service.AssetLifecycleEventService;
import com.example.demo.service.AssetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {

    private final AssetLifecycleEventService eventService;
    private final AssetService assetService;
    private final AssetLifecycleEventRepository eventRepository;

    public AssetLifecycleEventController(AssetLifecycleEventService eventService,
                                         AssetService assetService,
                                         AssetLifecycleEventRepository eventRepository) {
        this.eventService = eventService;
        this.assetService = assetService;
        this.eventRepository = eventRepository;
    }

    @PostMapping("/{assetId}")
    public ResponseEntity<?> logEvent(@PathVariable Long assetId,
                                      @Valid @RequestBody AssetLifecycleEvent event) {
        try {
            Asset asset = assetService.findById(assetId);
            AssetLifecycleEvent saved = eventService.logEvent(event, asset);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/asset/{assetId}")
    public List<AssetLifecycleEvent> getEventsForAsset(@PathVariable Long assetId) {
        return eventRepository.findByAssetIdOrderByEventDateDesc(assetId);
    }
}