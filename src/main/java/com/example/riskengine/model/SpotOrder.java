package com.example.riskengine.model;

import com.example.riskengine.api.Order;
import com.example.riskengine.api.RiskEngineVisitor;

public record SpotOrder(String userId, double size, double price) implements Order {

    public void accept(RiskEngineVisitor visitor) {
        visitor.check(this);
    }
}