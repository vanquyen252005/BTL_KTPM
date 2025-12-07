package com.example.apigateway;

import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class CircuitBreakerStateLogger {
    public CircuitBreakerStateLogger() {
        log.warn(">>> CircuitBreakerStateLogger LOADED");
    }

    @EventListener
    public void onStateTransition(CircuitBreakerOnStateTransitionEvent event) {
        log.warn("⚡ CIRCUIT BREAKER [{}] STATE CHANGE: {} -> {}",
                event.getCircuitBreakerName(),
                event.getStateTransition().getFromState(),
                event.getStateTransition().getToState()
        );
    }
}

