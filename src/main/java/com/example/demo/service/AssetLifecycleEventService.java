package com.example.demo.service;

import com.example.demo.entity.AssetLifecycleEvent;

import java.util.List;

public interface AssetLifecycleEventService {

    AssetLifecycleEvent addEvent(Long assetId, AssetLifecycleEvent event);

    List<AssetLifecycleEvent> getEventsByAsset(Long assetId);
}
