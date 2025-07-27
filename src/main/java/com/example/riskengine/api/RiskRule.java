package com.example.riskengine.api;

public interface RiskRule {
    RiskDecision check(Order order);
}