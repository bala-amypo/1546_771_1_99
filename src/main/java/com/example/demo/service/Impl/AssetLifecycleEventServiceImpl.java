package com.example.demo.service.Impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {

    private final AssetLifecycleEventRepository repository;

    public AssetLifecycleEventServiceImpl(AssetLifecycleEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssetLifecycleEvent logEvent(AssetLifecycleEvent event, Asset asset) {
        if (event.getEventDate() != null && event.getEventDate().isAfter(LocalDate.now())) {
            throw new BusinessValidationException("Event date cannot be in the future");
        }
        if (event.getEventDescription() == null || event.getEventDescription().trim().isEmpty()) {
            throw new BusinessValidationException("Event description cannot be empty");
        }

        event.setAsset(asset);
        return repository.save(event);
    }
}