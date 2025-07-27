
package com.example.riskengine;

import com.example.riskengine.api.Order;
import com.example.riskengine.api.RiskDecision;
import com.example.riskengine.rules.InitialMarginRule;
import com.example.riskengine.strategy.MarginCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InitialMarginRuleTest {

    @Test
    public void testSufficientMargin() {
        MarginCalculator calc = new MarginCalculator() {
            public double requiredMargin(Order o) { return 100; }
            public double availableMargin(String userId) { return 200; }
        };
        Order order = new Order() {
            public String userId() { return "u1"; }
            public double size() { return 1; }
            public double price() { return 100; }
            public void accept(com.example.riskengine.api.RiskEngineVisitor v) {}
        };
        InitialMarginRule rule = new InitialMarginRule(calc);
        RiskDecision decision = rule.check(order);
        assertTrue(decision.allowed());
    }

    @Test
    public void testInsufficientMargin() {
        MarginCalculator calc = new MarginCalculator() {
            public double requiredMargin(Order o) { return 300; }
            public double availableMargin(String userId) { return 200; }
        };
        Order order = new Order() {
            public String userId() { return "u1"; }
            public double size() { return 1; }
            public double price() { return 100; }
            public void accept(com.example.riskengine.api.RiskEngineVisitor v) {}
        };
        InitialMarginRule rule = new InitialMarginRule(calc);
        RiskDecision decision = rule.check(order);
        assertFalse(decision.allowed());
    }
}
