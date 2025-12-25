package com.example.demo.service;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;

public interface AssetService {
    Asset create(Asset asset, Vendor vendor, DepreciationRule rule);
    Asset findById(Long id);
}