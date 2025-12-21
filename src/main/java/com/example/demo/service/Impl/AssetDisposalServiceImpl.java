package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetDisposalService;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    @Autowired
    private AssetDisposalRepository disposalRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        if (disposal.getDisposalValue() < 0) {
            throw new RuntimeException("Disposal value must be >= 0");
        }

        disposal.setAsset(asset);
        disposal.setCreatedAt(LocalDateTime.now());

        return disposalRepository.save(disposal);
    }

    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {

        AssetDisposal disposal = disposalRepository.findById(disposalId)
                .orElseThrow(() -> new RuntimeException("Disposal not found"));

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        // Approve disposal
        disposal.setApprovedBy(admin);

        // Change asset status to DISPOSED
        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        return disposalRepository.save(disposal);
    }
}
