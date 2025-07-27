package com.example.riskengine.event;

import com.example.riskengine.api.OrderEvent;

import java.util.EventListener;

public interface OrderEventListener extends EventListener {

    void on(OrderEvent event);

}
