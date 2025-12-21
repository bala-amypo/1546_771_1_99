package com.example.demo.service;

import com.example.demo.dto.AssetDisposalResponse;
import com.example.demo.entity.AssetDisposal;

public interface AssetDisposalService {
    AssetDisposalResponse requestDisposal(AssetDisposal disposal);
    AssetDisposalResponse approveDisposal(Long disposalId, Long adminId);
}