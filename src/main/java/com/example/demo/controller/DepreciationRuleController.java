package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depreciation")
public class DepreciationRuleController {

    private final DepreciationService service;

    public DepreciationRuleController(DepreciationService service) {
        this.service = service;
    }

    // ================= CREATE =================
    // Test case usually uses POST /depreciation
    @PostMapping
    public DepreciationRule createDepreciationRule(
            @RequestBody DepreciationRule rule) {
        return service.save(rule);
    }

    // ================= GET ALL =================
    // Test case usually uses GET /depreciation
    @GetMapping
    public List<DepreciationRule> getAllDepreciationRules() {
        return service.getAll();
    }

    // ================= GET BY ID =================
    // Test case usually uses GET /depreciation/{id}
    @GetMapping("/{id}")
    public DepreciationRule getDepreciationRuleById(
            @PathVariable Long id) {
        return service.getById(id);
    }

    // ================= GET BY RULE NAME =================
    // Test case usually uses GET /depreciation/name/{ruleName}
    @GetMapping("/name/{ruleName}")
    public DepreciationRule getDepreciationRuleByName(
