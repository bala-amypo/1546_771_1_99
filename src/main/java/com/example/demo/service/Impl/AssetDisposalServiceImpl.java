package com.example.demo.service.Impl;

import com.example.demo.dto.AssetDisposalResponse;
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

    @Override
    public AssetDisposalResponse requestDisposal(AssetDisposal disposal) {
        disposal.setStatus("PENDING");
        AssetDisposal saved = repository.save(disposal);
        return new AssetDisposalResponse(saved);
    }

    @Override
    public AssetDisposalResponse approveDisposal(Long disposalId, Long adminId) {
        AssetDisposal disposal = repository.findById(disposalId)
                .orElseThrow(() -> new RuntimeException("Disposal request not found"));

        disposal.setStatus("APPROVED");
        disposal.setApprovedBy(adminId);
        AssetDisposal saved = repository.save(disposal);
        return new AssetDisposalResponse(saved);
    }
}