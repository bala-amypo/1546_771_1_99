package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {

    private final DepreciationRuleRepository ruleRepository;
    private static final List<String> VALID_METHODS = Arrays.asList("STRAIGHT_LINE", "DECLINING_BALANCE");

    public DepreciationRuleController(DepreciationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @PostMapping
    public ResponseEntity<?> createRule(@Valid @RequestBody DepreciationRule rule) {
        try {
            if (rule.getUsefulLifeYears() == null || rule.getUsefulLifeYears() <= 0) {
                return ResponseEntity.badRequest().body("Useful life must be greater than 0");
            }
            if (rule.getSalvageValue() != null && rule.getSalvageValue() < 0) {
                return ResponseEntity.badRequest().body("Salvage value cannot be negative");
            }
            if (!VALID_METHODS.contains(rule.getMethod())) {
                return ResponseEntity.badRequest().body("Invalid depreciation method");
            }
            DepreciationRule saved = ruleRepository.save(rule);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<DepreciationRule>> getAllRules() {
        return ResponseEntity.ok(ruleRepository.findAll());
    }
}
