

package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;

public interface AssetDisposalService {


    AssetDisposal disposeAsset(Long assetId, Long userId, AssetDisposal disposal);

    AssetDisposal approveDisposal(Long disposalId, Long adminId);
}
