package com.example.demo.service;

import com.example.demo.entity.DepreciationRule;
import java.util.List;

public interface DepreciationService {

    DepreciationRule save(DepreciationRule rule);

    List<DepreciationRule> getAll();

    DepreciationRule getById(Long id);

    DepreciationRule getByRuleName(String ruleName);
}
