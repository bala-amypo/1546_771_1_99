package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;

public interface AssetDisposalService {

    AssetDisposal requestDisposal(AssetDisposal disposal);

    AssetDisposal approveDisposal(Long disposalId, Long adminId);
}
