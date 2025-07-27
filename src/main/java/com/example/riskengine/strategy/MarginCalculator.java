package com.example.riskengine.strategy;

import com.example.riskengine.api.Order;

public interface MarginCalculator {
    double requiredMargin(Order order);

    double availableMargin(String userId);
}
