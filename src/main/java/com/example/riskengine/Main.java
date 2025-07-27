package com.example.riskengine;

import com.example.riskengine.api.OrderEvent;
import com.example.riskengine.core.CompositeRiskEngine;
import com.example.riskengine.event.InMemoryEventPublisher;
import com.example.riskengine.model.SpotOrder;
import com.example.riskengine.rules.SpotBalanceRule;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        InMemoryEventPublisher publisher = new InMemoryEventPublisher();
        publisher.subscribe(new CompositeRiskEngine(List.of(new SpotBalanceRule(
                (id, order) -> ThreadLocalRandom.current().nextBoolean())),
                List.of(), List.of()));
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        long now = System.currentTimeMillis();
        while (System.currentTimeMillis()<=now+10000) {
            executorService.submit(() -> publisher.submit(new OrderEvent(new SpotOrder("1", 2.0, 1045.34))));
        }
        executorService.shutdown();
        executorService.awaitTermination(10000, java.util.concurrent.TimeUnit.MILLISECONDS);
    }

}
