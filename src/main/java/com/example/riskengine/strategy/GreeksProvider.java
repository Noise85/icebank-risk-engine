package com.example.riskengine.strategy;

import com.example.riskengine.api.Order;

public interface GreeksProvider {
    double delta(Order order);
}
