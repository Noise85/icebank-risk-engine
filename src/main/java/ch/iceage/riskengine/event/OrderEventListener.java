package ch.iceage.riskengine.event;

import ch.iceage.riskengine.api.OrderEvent;

import java.util.EventListener;

public interface OrderEventListener extends EventListener {

    void on(OrderEvent event);

}
