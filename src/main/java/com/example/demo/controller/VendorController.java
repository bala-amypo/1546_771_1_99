package com.example.demo.controller;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @PostMapping
    public ResponseEntity<?> createVendor(@Valid @RequestBody Vendor vendor) {
        try {
            if (vendorRepository.findByVendorName(vendor.getVendorName()).isPresent()) {
                return ResponseEntity.badRequest().body("Vendor name already exists");
            }
            Vendor saved = vendorRepository.save(vendor);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.ok(vendorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorById(@PathVariable Long id) {
        return vendorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
