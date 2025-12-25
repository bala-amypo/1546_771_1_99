package com.example.demo.controller;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
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
    public ResponseEntity<Vendor> create(@RequestBody Vendor vendor) {
        return ResponseEntity.ok(vendorRepository.save(vendor));
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getAll() {
        return ResponseEntity.ok(vendorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getById(@PathVariable Long id) {
        return vendorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}