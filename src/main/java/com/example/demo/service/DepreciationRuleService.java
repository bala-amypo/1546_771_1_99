package com.example.demo.service;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepreciationRuleService {
    @Autowired private DepreciationRuleRepository ruleRepository;

    public DepreciationRule createRule(DepreciationRule rule) {
        if (rule.getUsefulLifeYears() <= 0 || rule.getSalvageValue() < 0) {
            throw new BadRequestException("Invalid rule values");
        }
        if (!rule.getMethod().equals("STRAIGHT_LINE") && !rule.getMethod().equals("DECLINING_BALANCE")) {
            throw new BadRequestException("Invalid method");
        }
        return ruleRepository.save(rule);
    }
}