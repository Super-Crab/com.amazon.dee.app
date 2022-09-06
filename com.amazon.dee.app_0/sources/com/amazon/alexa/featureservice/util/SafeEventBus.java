package com.amazon.alexa.featureservice.util;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Subscriber;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class SafeEventBus {
    private Lazy<EventBus> eventBusLazy;

    @Inject
    public SafeEventBus(Lazy<EventBus> lazy) {
        this.eventBusLazy = lazy;
    }

    public void publish(@NonNull String str, @NonNull String str2) {
        Message build = new Message.Builder().setEventType(str).setPayload(str2).build();
        if (this.eventBusLazy.mo358get() != null) {
            this.eventBusLazy.mo358get().publish(build);
        }
    }

    public MultiFilterSubscriber subscribe(@NonNull MessageFilter messageFilter, @NonNull MessageHandler messageHandler) {
        if (this.eventBusLazy.mo358get() != null) {
            MultiFilterSubscriber newSubscriber = this.eventBusLazy.mo358get().getNewSubscriber();
            newSubscriber.subscribeFilter(messageFilter, messageHandler);
            return newSubscriber;
        }
        return null;
    }

    public void unsubscribe(@NonNull Subscriber subscriber) {
        if (this.eventBusLazy.mo358get() == null || subscriber == null) {
            return;
        }
        this.eventBusLazy.mo358get().unsubscribe(subscriber);
    }
}
