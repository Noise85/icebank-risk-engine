package com.example.riskengine.api;

public interface Order {
    String userId();

    double size();

    double price();

    void accept(RiskEngineVisitor visitor);
}