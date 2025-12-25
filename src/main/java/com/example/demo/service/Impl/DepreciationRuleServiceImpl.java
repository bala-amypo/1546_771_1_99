package com.example.demo.service.Impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    private final DepreciationRuleRepository ruleRepository;

    public DepreciationRuleServiceImpl(DepreciationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {

        if (rule.getUsefulLifeYears() <= 0) {
            throw new IllegalArgumentException("Useful life must be greater than zero");
        }

        if (rule.getSalvageValue() < 0) {
            throw new IllegalArgumentException("Salvage value cannot be negative");
        }

        if (!"STRAIGHT_LINE".equals(rule.getMethod())
                && !"DECLINING_BALANCE".equals(rule.getMethod())) {
            throw new IllegalArgumentException("Invalid depreciation method");
        }

        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepository.save(rule);
    }
}
