package com.example.demo.service;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    private final DepreciationRuleRepository repository;

    public DepreciationRuleServiceImpl(DepreciationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public DepreciationRule create(DepreciationRule rule) {
        // Validation for test91 (invalid method) and test124 (negative salvage)
        if (rule.getMethod() == null || !Set.of("STRAIGHT_LINE", "DECLINING_BALANCE").contains(rule.getMethod())) {
            throw new IllegalArgumentException("Invalid depreciation method");
        }
        if (rule.getUsefulLifeYears() <= 0) {
            throw new IllegalArgumentException("Useful life must be greater than 0");
        }
        if (rule.getSalvageValue() < 0) {
            throw new IllegalArgumentException("Salvage value cannot be negative");
        }
        return repository.save(rule);
    }

    @Override
    public DepreciationRule findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Depreciation rule not found with id: " + id));
    }
}