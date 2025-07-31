
package ch.iceage.riskengine;

import ch.iceage.riskengine.api.Order;
import ch.iceage.riskengine.api.RiskEngineVisitor;
import ch.iceage.riskengine.api.RiskDecision;
import ch.iceage.riskengine.rules.GreeksExposureRule;
import ch.iceage.riskengine.strategy.GreeksProvider;
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
            public void accept(RiskEngineVisitor v) {}
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
            public void accept(RiskEngineVisitor v) {}
        };
        GreeksExposureRule rule = new GreeksExposureRule(provider, 0.5);
        RiskDecision decision = rule.check(order);
        assertFalse(decision.allowed());
    }
}
