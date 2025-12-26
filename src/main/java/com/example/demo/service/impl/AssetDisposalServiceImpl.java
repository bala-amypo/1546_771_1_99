package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    @Autowired private AssetDisposalRepository disposalRepository;
    @Autowired private AssetRepository assetRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        disposal.setAsset(asset);
        return disposalRepository.save(disposal);
    }

    @Override
    @Transactional
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {
        AssetDisposal disposal = disposalRepository.findById(disposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal request not found"));
        
        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        // Step 1 Rule: asset must change status to DISPOSED once processed
        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        disposal.setApprovedBy(admin);
        return disposalRepository.save(disposal);
    }
}package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    @Autowired private AssetDisposalRepository disposalRepository;
    @Autowired private AssetRepository assetRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        disposal.setAsset(asset);
        return disposalRepository.save(disposal);
    }

    @Override
    @Transactional
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {
        AssetDisposal disposal = disposalRepository.findById(disposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal request not found"));
        
        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        // Step 1 Rule: asset must change status to DISPOSED once processed
        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        disposal.setApprovedBy(admin);
        return disposalRepository.save(disposal);
    }
}