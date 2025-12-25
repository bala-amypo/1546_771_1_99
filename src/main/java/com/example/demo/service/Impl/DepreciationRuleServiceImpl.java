package com.example.demo.service.Impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
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
        if (rule.getMethod() == null || !Set.of("STRAIGHT_LINE", "DECLINING_BALANCE").contains(rule.getMethod())) {
            throw new BusinessValidationException("Invalid depreciation method. Allowed: STRAIGHT_LINE, DECLINING_BALANCE");
        }
        if (rule.getUsefulLifeYears() <= 0) {
            throw new BusinessValidationException("Useful life years must be greater than 0");
        }
        if (rule.getSalvageValue() < 0) {
            throw new BusinessValidationException("Salvage value cannot be negative");
        }
        return repository.save(rule);
    }

    @Override
    public DepreciationRule findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Depreciation rule not found with id: " + id));
    }
}