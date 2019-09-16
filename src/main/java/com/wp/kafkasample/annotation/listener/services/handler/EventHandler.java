package com.wp.kafkasample.annotation.listener.services.handler;

import com.wp.kafkasample.annotation.listener.model.PaymentEvent;
import org.springframework.core.GenericTypeResolver;

public interface EventHandler<T extends PaymentEvent> {

    void handle(T t);

    default Class getEventType() {
        return GenericTypeResolver.resolveTypeArgument(getClass(), EventHandler.class);
    }
}
