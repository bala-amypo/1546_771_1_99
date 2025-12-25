package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetDisposalService {

    @Autowired
    private AssetDisposalRepository disposalRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private UserRepository userRepository;

    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {
        var asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        disposal.setAsset(asset);
        return disposalRepository.save(disposal);
    }

    public AssetDisposal approveDisposal(Long disposalId, Long approverId) {
        var disposal = disposalRepository.findById(disposalId)
                .orElseThrow(() -> new RuntimeException("Disposal not found"));
        var approver = userRepository.findById(approverId)
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        disposal.setApprovedBy(approver);
        var saved = disposalRepository.save(disposal);

        // Update asset status
        var asset = saved.getAsset();
        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        return saved;
    }

    public List<AssetDisposal> getAllDisposals() {
        return disposalRepository.findAll();
    }
}