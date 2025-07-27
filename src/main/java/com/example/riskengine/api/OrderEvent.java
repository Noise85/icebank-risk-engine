package com.example.riskengine.api;

public class OrderEvent {

    private final Order order;

    public OrderEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

}
