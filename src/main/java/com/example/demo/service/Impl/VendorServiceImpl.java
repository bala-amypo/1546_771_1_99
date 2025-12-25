package com.example.demo.service.Impl;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {

        if (vendorRepository.findByVendorName(vendor.getVendorName()).isPresent()) {
            throw new IllegalArgumentException("Vendor name already exists");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (vendor.getContactEmail() == null ||
                !Pattern.matches(emailRegex, vendor.getContactEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        vendor.setCreatedAt(LocalDateTime.now());
        return vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}
