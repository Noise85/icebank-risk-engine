package ch.iceage.riskengine.event;

import ch.iceage.riskengine.api.OrderEvent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Single event in memory pub-sub system.
 */
public class InMemoryEventPublisher {

    private final Queue<OrderEventListener> listeners = new ConcurrentLinkedDeque<>();

    public void subscribe(OrderEventListener listener) {
        this.listeners.add(listener);
    }

    public void submit(OrderEvent event) {
        for (OrderEventListener listener : listeners) {
            try {
                listener.on(event);
            } catch (Exception e) {
                System.err.println("Error processing event for listener " + listener.getClass().getName());
            }
        }
    }
}