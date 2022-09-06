package com.amazon.alexa.dnssd.subscribers;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import java.util.UUID;
/* loaded from: classes7.dex */
public interface DnssdEventSubscriber extends Subscriber {
    String getEventType();

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    default UUID getUUID() {
        return UUID.randomUUID();
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    default boolean supportsMessage(@NonNull Message message) {
        return getEventType().equals(message.getEventType());
    }
}
