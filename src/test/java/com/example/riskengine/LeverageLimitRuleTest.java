
package com.example.riskengine;

import com.example.riskengine.api.RiskDecision;
import com.example.riskengine.model.FuturesOrder;
import com.example.riskengine.rules.LeverageLimitRule;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeverageLimitRuleTest {

    @Test
    public void testAcceptableLeverage() {
        FuturesOrder order = new FuturesOrder("u1", 1, 1000, 10, 990);
        RiskDecision decision = new LeverageLimitRule(20).check(order);
        assertTrue(decision.allowed());
    }

    @Test
    public void testExcessiveLeverage() {
        FuturesOrder order = new FuturesOrder("u1", 1, 1000, 25, 990);
        RiskDecision decision = new LeverageLimitRule(20).check(order);
        assertFalse(decision.allowed());
    }
}
