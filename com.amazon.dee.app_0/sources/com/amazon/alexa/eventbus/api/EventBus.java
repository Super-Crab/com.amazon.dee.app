package com.amazon.alexa.eventbus.api;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Subscriber;
import java.util.UUID;
/* loaded from: classes7.dex */
public interface EventBus {
    default MultiFilterSubscriber getNewSubscriber() {
        return getSubscriber();
    }

    Publisher getPublisher(boolean z);

    @Deprecated
    MultiFilterSubscriber getSubscriber();

    @Deprecated
    UUID getUUID();

    void publish(@NonNull Message message);

    void subscribe(@NonNull Subscriber subscriber);

    default void unsubscribe(@NonNull Subscriber.SubscriberUuid subscriberUuid) {
        throw new UnsupportedOperationException();
    }

    void unsubscribe(@NonNull Subscriber subscriber);

    @Deprecated
    void unsubscribe(@NonNull UUID uuid);
}
