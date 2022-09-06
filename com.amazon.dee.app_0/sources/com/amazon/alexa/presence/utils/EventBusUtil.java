package com.amazon.alexa.presence.utils;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
/* loaded from: classes9.dex */
public final class EventBusUtil {
    private EventBusUtil() {
    }

    public static void publishToEventBus(EventBus eventBus, Message message) {
        eventBus.publish(message);
    }

    public static void subscribeToEventBus(EventBus eventBus, Subscriber subscriber) {
        eventBus.subscribe(subscriber);
    }

    public static void unsubscribeFromEventBus(EventBus eventBus, Subscriber subscriber) {
        eventBus.unsubscribe(subscriber);
    }
}
