package com.example.demo.service.Impl;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetDisposalRepository repository;

    public AssetDisposalServiceImpl(AssetDisposalRepository repository) {
        this.repository = repository;
    }

    // Request disposal
    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {
        disposal.setAssetId(assetId);
        disposal.setStatus("REQUESTED");
        return repository.save(disposal);
    }

    // Approve disposal
    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {
        AssetDisposal disposal =
                repository.findById(disposalId)
                        .orElseThrow(() -> new RuntimeException("Disposal not found"));

        disposal.setStatus("APPROVED");
        disposal.setApprovedBy(adminId);

        return repository.save(disposal);
    }
}
