package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;

public interface AssetDisposalService {

    /**
     * Request a new asset disposal (anyone can request)
     */
    AssetDisposal requestDisposal(AssetDisposal disposal);

    /**
     * Approve a disposal request (only ADMIN)
     */
    AssetDisposal approveDisposal(Long disposalId, User approvedBy);

    /**
     * Find disposals approved by a specific user
     */
    java.util.List<AssetDisposal> findByApprovedBy(User user);
}