package com.example.demo.service;

import com.example.demo.entity.Vendor;

public interface VendorService {
    Vendor create(Vendor vendor);
    Vendor findById(Long id);
}