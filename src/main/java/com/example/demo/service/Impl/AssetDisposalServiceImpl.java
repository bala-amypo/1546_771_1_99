package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.repository.AssetDisposalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetDisposalService {

    private final AssetDisposalRepository repository;

    public AssetDisposalService(AssetDisposalRepository repository) {
        this.repository = repository;
    }

    public AssetDisposal requestDisposal(AssetDisposal disposal) {
        return repository.save(disposal); // 🔴 THIS INSERTS DATA
    }

    public List<AssetDisposal> getAllDisposals() {
        return repository.findAll();
    }
}
