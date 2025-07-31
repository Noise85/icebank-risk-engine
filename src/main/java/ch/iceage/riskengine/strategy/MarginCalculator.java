package ch.iceage.riskengine.strategy;

import ch.iceage.riskengine.api.Order;

public interface MarginCalculator {
    double requiredMargin(Order order);

    double availableMargin(String userId);
}
