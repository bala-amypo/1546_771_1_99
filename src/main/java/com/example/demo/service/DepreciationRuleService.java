package com.example.demo.service;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepreciationRuleService {
    @Autowired
    private DepreciationRuleRepository ruleRepository;

    public DepreciationRule createRule(DepreciationRule rule) {
        return ruleRepository.save(rule);
    }

    public List<DepreciationRule> getAllRules() {
        return ruleRepository.findAll();
    }

    public DepreciationRule getRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }
}
