package com.example.riskengine.model;

import com.example.riskengine.api.Order;
import com.example.riskengine.api.RiskEngineVisitor;

public record OptionsOrder(String userId, double size, double price, double strike, long expiry) implements Order {
    public void accept(RiskEngineVisitor visitor) {
        visitor.check(this);
    }
}