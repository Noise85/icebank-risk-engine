
package com.example.riskengine;

import com.example.riskengine.api.RiskDecision;
import com.example.riskengine.model.SpotOrder;
import com.example.riskengine.rules.SpotBalanceRule;
import org.junit.jupiter.api.Test;
import java.util.function.BiFunction;
import static org.junit.jupiter.api.Assertions.*;

public class SpotBalanceRuleTest {

    @Test
    public void testSufficientBalance() {
        BiFunction<String, SpotOrder, Boolean> fn = (u, o) -> true;
        SpotOrder order = new SpotOrder("user1", 1, 100);
        RiskDecision decision = new SpotBalanceRule(fn).check(order);
        assertTrue(decision.allowed());
    }

    @Test
    public void testInsufficientBalance() {
        BiFunction<String, SpotOrder, Boolean> fn = (u, o) -> false;
        SpotOrder order = new SpotOrder("user1", 1, 100);
        RiskDecision decision = new SpotBalanceRule(fn).check(order);
        assertFalse(decision.allowed());
    }
}
