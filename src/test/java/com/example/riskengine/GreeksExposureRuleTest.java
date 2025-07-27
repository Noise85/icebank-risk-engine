
package com.example.riskengine;

import com.example.riskengine.api.Order;
import com.example.riskengine.api.RiskDecision;
import com.example.riskengine.rules.GreeksExposureRule;
import com.example.riskengine.strategy.GreeksProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GreeksExposureRuleTest {

    @Test
    public void testAcceptableDelta() {
        GreeksProvider provider = o -> 0.3;
        Order order = new Order() {
            public String userId() { return "u2"; }
            public double size() { return 1; }
            public double price() { return 200; }
            public void accept(com.example.riskengine.api.RiskEngineVisitor v) {}
        };
        GreeksExposureRule rule = new GreeksExposureRule(provider, 0.5);
        RiskDecision decision = rule.check(order);
        assertTrue(decision.allowed());
    }

    @Test
    public void testExcessiveDelta() {
        GreeksProvider provider = o -> 0.8;
        Order order = new Order() {
            public String userId() { return "u2"; }
            public double size() { return 1; }
            public double price() { return 200; }
            public void accept(com.example.riskengine.api.RiskEngineVisitor v) {}
        };
        GreeksExposureRule rule = new GreeksExposureRule(provider, 0.5);
        RiskDecision decision = rule.check(order);
        assertFalse(decision.allowed());
    }
}
