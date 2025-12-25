package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetDisposalRepository disposalRepository;
    private final AssetRepository assetRepository;

    public AssetDisposalServiceImpl(AssetDisposalRepository disposalRepository,
                                    AssetRepository assetRepository) {
        this.disposalRepository = disposalRepository;
        this.assetRepository = assetRepository;
    }

    @Override
    public AssetDisposal requestDisposal(AssetDisposal disposal) {
        if (disposal.getAsset() == null || disposal.getAsset().getId() == null) {
            throw new BusinessValidationException("Asset is required for disposal request");
        }
        if (disposal.getDisposalMethod() == null || disposal.getDisposalMethod().isBlank()) {
            throw new BusinessValidationException("Disposal method is required");
        }

        Asset asset = assetRepository.findById(disposal.getAsset().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found for disposal"));

        // Optional: Prevent disposal if already disposed
        if ("DISPOSED".equals(asset.getStatus())) {
            throw new BusinessValidationException("Asset is already disposed");
        }

        return disposalRepository.save(disposal);
    }

    @Override
    public AssetDisposal approveDisposal(Long disposalId, User approvedBy) {
        AssetDisposal disposal = disposalRepository.findById(disposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal request not found with id: " + disposalId));

        // Prevent double approval
        if (disposal.getApprovedBy() != null) {
            throw new BusinessValidationException("Disposal already approved");
        }

        disposal.setApprovedBy(approvedBy);

        // Update asset status to DISPOSED
        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        return disposalRepository.save(disposal);
    }

    @Override
    public List<AssetDisposal> findByApprovedBy(User user) {
        return disposalRepository.findByApprovedBy(user);
    }
}