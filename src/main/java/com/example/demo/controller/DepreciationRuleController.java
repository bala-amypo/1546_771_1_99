package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depreciation")
public class DepreciationRuleController {

    private final DepreciationRuleService service;

    public DepreciationRuleController(DepreciationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DepreciationRule> createRule(
            @RequestBody DepreciationRule rule) {

        return ResponseEntity.ok(service.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<DepreciationRule>> getAllRules() {
        return ResponseEntity.ok(service.getAllRules());
    }
}
