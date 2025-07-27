package com.example.riskengine.model;

import com.example.riskengine.api.Order;
import com.example.riskengine.api.RiskEngineVisitor;

public record FuturesOrder(String userId, double size, double price, double leverage,
                           double markPrice) implements Order {
    public void accept(RiskEngineVisitor visitor) {
        visitor.check(this);
    }
}