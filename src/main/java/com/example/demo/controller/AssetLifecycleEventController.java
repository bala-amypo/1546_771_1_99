package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asset-events")
public class AssetLifecycleEventController {

    private final AssetLifecycleEventService service;

    public AssetLifecycleEventController(AssetLifecycleEventService service) {
        this.service = service;
    }

    @PostMapping
    public AssetLifecycleEvent createEvent(
            @RequestBody AssetLifecycleEvent event) {
        return service.save(event);
    }

    @GetMapping("/asset/{assetId}")
    public List<AssetLifecycleEvent> getEventsByAsset(
            @PathVariable Long assetId) {
        return service.getByAssetId(assetId);
    }
}
