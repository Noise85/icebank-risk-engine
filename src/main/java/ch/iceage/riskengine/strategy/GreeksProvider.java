package ch.iceage.riskengine.strategy;

import ch.iceage.riskengine.api.Order;

public interface GreeksProvider {
    double delta(Order order);
}
