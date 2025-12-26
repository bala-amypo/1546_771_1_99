package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssetDisposalService {
    @Autowired private AssetDisposalRepository disposalRepository;
    @Autowired private AssetRepository assetRepository;
    @Autowired private UserRepository userRepository;

    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        disposal.setAsset(asset);
        return disposalRepository.save(disposal);
    }

    @Transactional
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {
        AssetDisposal disposal = disposalRepository.findById(disposalId).orElseThrow();
        User admin = userRepository.findById(adminId).orElseThrow();
        
        disposal.setApprovedBy(admin);
        // Requirement: asset must change status to DISPOSED
        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");
        assetRepository.save(asset);
        
        return disposalRepository.save(disposal);
    }
}