
package ch.iceage.riskengine;

import ch.iceage.riskengine.api.Order;
import ch.iceage.riskengine.api.RiskEngineVisitor;
import ch.iceage.riskengine.api.RiskDecision;
import ch.iceage.riskengine.rules.InitialMarginRule;
import ch.iceage.riskengine.strategy.MarginCalculator;
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
            public void accept(RiskEngineVisitor v) {}
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
            public void accept(RiskEngineVisitor v) {}
        };
        InitialMarginRule rule = new InitialMarginRule(calc);
        RiskDecision decision = rule.check(order);
        assertFalse(decision.allowed());
    }
}
