package com.wp.kafkasample.services.handler;

import com.wp.kafkasample.model.PaymentEvent;
import org.springframework.core.GenericTypeResolver;

public interface EventHandler<T extends PaymentEvent> {

    void handle(T t);

    default Class getEventType() {
        return GenericTypeResolver.resolveTypeArgument(getClass(), EventHandler.class);
    }
}
