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

    // ================= POST : Request Disposal =================
    @Override
    public AssetDisposal requestDisposal(AssetDisposal disposal) {

        // default status
        if (disposal.getStatus() == null) {
            disposal.setStatus("PENDING");
        }

        return repository.save(disposal);
    }

    // ================= PUT : Approve Disposal =================
    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {

        AssetDisposal disposal = repository.findById(disposalId)
                .orElseThrow(() ->
                        new RuntimeException("AssetDisposal not found with id: " + disposalId));

        disposal.setStatus("APPROVED");
        disposal.setApprovedBy(adminId);

        return repository.save(disposal);
    }
}
