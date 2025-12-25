package com.example.demo.service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;

public interface AssetLifecycleEventService {
    AssetLifecycleEvent logEvent(AssetLifecycleEvent event, Asset asset);
}