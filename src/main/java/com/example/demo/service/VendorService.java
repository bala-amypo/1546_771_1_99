package com.example.demo.service;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorService {
    @Autowired private VendorRepository vendorRepository;

    public Vendor createVendor(Vendor vendor) {
        if (vendorRepository.findByVendorName(vendor.getVendorName()).isPresent()) {
            throw new BadRequestException("Vendor name already exists");
        }
        return vendorRepository.save(vendor);
    }

    public List<Vendor> getAllVendors() { return vendorRepository.findAll(); }
}