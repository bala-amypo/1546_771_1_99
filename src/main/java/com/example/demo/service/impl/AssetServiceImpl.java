package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository repository;

    public AssetServiceImpl(AssetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Asset save(Asset asset) {
        return repository.save(asset);
    }

    @Override
    public List<Asset> getAll() {
        return repository.findAll();
    }

    @Override
    public Asset getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
