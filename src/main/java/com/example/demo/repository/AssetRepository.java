package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByVendor(Vendor vendor);
    List<Asset> findByStatus(String status);
    boolean existsByAssetTag(String assetTag);
}