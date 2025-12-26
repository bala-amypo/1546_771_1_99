package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Asset Lifecycle Event Controller", description = "Endpoints for logging and viewing asset history")
public class AssetLifecycleEventController {

    @Autowired
    private AssetLifecycleEventService eventService;

    @PostMapping("/{assetId}")
    public AssetLifecycleEvent logEvent(@PathVariable Long assetId, 
                                        @RequestBody AssetLifecycleEvent event) {
        return eventService.logEvent(assetId, event);
    }

    @GetMapping("/asset/{assetId}")
    public List<AssetLifecycleEvent> getEventsForAsset(@PathVariable Long assetId) {
        return eventService.getEventsForAsset(assetId);
    }
}