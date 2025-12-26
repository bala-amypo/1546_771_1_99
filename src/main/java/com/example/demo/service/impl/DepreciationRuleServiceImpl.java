package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    @Autowired private DepreciationRuleRepository ruleRepository;

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {
        // Step 1 Rule: usefulLifeYears > 0
        if (rule.getUsefulLifeYears() == null || rule.getUsefulLifeYears() <= 0) {
            throw new BadRequestException("Useful life must be greater than zero");
        }
        // Step 1 Rule: salvageValue >= 0
        if (rule.getSalvageValue() == null || rule.getSalvageValue() < 0) {
            throw new BadRequestException("Salvage value cannot be negative");
        }
        return ruleRepository.save(rule);
    }
}