package com.amazon.alexa.eventbus.stub;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Publisher;
import com.amazon.alexa.eventbus.api.Subscriber;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes7.dex */
public final class FakeEventBus implements EventBus {
    private final Map<UUID, Subscriber> subscribers = new HashMap();

    private FakeEventBus() {
    }

    public static FakeEventBus instance() {
        return new FakeEventBus();
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public Publisher getPublisher(boolean z) {
        return new Publisher() { // from class: com.amazon.alexa.eventbus.stub.-$$Lambda$FakeEventBus$9sGM51kVdaffZw48xg2aRfYfEPY
            @Override // com.amazon.alexa.eventbus.api.Publisher
            public final void publish(Message message) {
                FakeEventBus.this.lambda$getPublisher$0$FakeEventBus(message);
            }
        };
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public MultiFilterSubscriber getSubscriber() {
        return new MultiFilterSubscriber() { // from class: com.amazon.alexa.eventbus.stub.FakeEventBus.1
            @Override // com.amazon.alexa.eventbus.api.Subscriber
            public UUID getUUID() {
                return UUID.randomUUID();
            }

            @Override // com.amazon.alexa.eventbus.api.Subscriber
            public void onMessageReceived(@NonNull Message message) {
                throw new UnsupportedOperationException("Only received by individuals.");
            }

            @Override // com.amazon.alexa.eventbus.api.MultiFilterSubscriber
            public UUID subscribe(@NonNull final MessageFilter messageFilter, @NonNull final MessageHandler messageHandler) {
                final UUID randomUUID = UUID.randomUUID();
                FakeEventBus.this.subscribers.put(randomUUID, new Subscriber() { // from class: com.amazon.alexa.eventbus.stub.FakeEventBus.1.1
                    @Override // com.amazon.alexa.eventbus.api.Subscriber
                    public UUID getUUID() {
                        return randomUUID;
                    }

                    @Override // com.amazon.alexa.eventbus.api.Subscriber
                    public void onMessageReceived(@NonNull Message message) {
                        messageHandler.handle(message);
                    }

                    @Override // com.amazon.alexa.eventbus.api.Subscriber
                    public boolean supportsMessage(@NonNull Message message) {
                        return messageFilter.isMatch(message);
                    }
                });
                return randomUUID;
            }

            @Override // com.amazon.alexa.eventbus.api.MultiFilterSubscriber
            public MultiFilterSubscriber.FilterUuid subscribeFilter(@NonNull MessageFilter messageFilter, @NonNull MessageHandler messageHandler) {
                final UUID subscribe = subscribe(messageFilter, messageHandler);
                return new MultiFilterSubscriber.FilterUuid() { // from class: com.amazon.alexa.eventbus.stub.FakeEventBus.1.2
                    @Override // com.amazon.alexa.eventbus.api.MultiFilterSubscriber.FilterUuid
                    public UUID getUuid() {
                        return subscribe;
                    }
                };
            }

            @Override // com.amazon.alexa.eventbus.api.Subscriber
            public boolean supportsMessage(@NonNull Message message) {
                for (Subscriber subscriber : FakeEventBus.this.subscribers.values()) {
                    if (subscriber.supportsMessage(message)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // com.amazon.alexa.eventbus.api.MultiFilterSubscriber
            public void unsubscribe(@NonNull UUID uuid) {
                FakeEventBus.this.unsubscribe(uuid);
            }

            @Override // com.amazon.alexa.eventbus.api.MultiFilterSubscriber
            public boolean unsubscribeFilter(@NonNull MultiFilterSubscriber.FilterUuid filterUuid) {
                unsubscribe(filterUuid.getUuid());
                return true;
            }
        };
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public UUID getUUID() {
        return UUID.fromString(FakeEventBus.class.getSimpleName());
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    /* renamed from: publish */
    public void lambda$getPublisher$0$FakeEventBus(@NonNull Message message) {
        for (Subscriber subscriber : this.subscribers.values()) {
            if (subscriber.supportsMessage(message)) {
                subscriber.onMessageReceived(message);
            }
        }
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public void subscribe(@NonNull Subscriber subscriber) {
        this.subscribers.put(subscriber.getUUID(), subscriber);
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public void unsubscribe(@NonNull Subscriber subscriber) {
        unsubscribe(subscriber.getUUID());
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public void unsubscribe(@NonNull UUID uuid) {
        if (this.subscribers.containsKey(uuid)) {
            this.subscribers.remove(uuid);
        }
    }
}
