package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByVendor(Vendor vendor);
    List<Asset> findByStatus(String status);
    boolean existsByAssetTag(String assetTag);
}
