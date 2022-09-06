package com.amazon.alexa.eventbus.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.component.api.ServiceLifecycle;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.EventBusException;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Publisher;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.publisher.BroadcastPublisher;
import com.amazon.alexa.eventbus.publisher.LocalPublisher;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import java.util.Objects;
import java.util.UUID;
/* loaded from: classes7.dex */
public class LocalEventBus implements EventBus, ServiceLifecycle {
    @NonNull
    @VisibleForTesting
    final BroadcastReceiver broadcastReceiver;
    @NonNull
    private final Context context;
    @NonNull
    private final Publisher defaultPublisher;
    @NonNull
    private final LocalDispatcher dispatcher;
    @NonNull
    private final Registry registry;
    @Nullable
    private UUID uuid;
    @NonNull
    private final Object uuidLock;

    @VisibleForTesting
    LocalEventBus(@NonNull Context context, @NonNull LocalDispatcher localDispatcher, @NonNull Registry registry) {
        this.context = context;
        this.dispatcher = localDispatcher;
        this.registry = registry;
        this.defaultPublisher = new LocalPublisher(localDispatcher);
        this.broadcastReceiver = createBroadcastReceiver();
        this.uuid = null;
        this.uuidLock = new Object();
    }

    public static LocalEventBus create(@NonNull Context context) {
        return new LocalEventBus(context);
    }

    @NonNull
    private BroadcastReceiver createBroadcastReceiver() {
        return new BroadcastReceiver() { // from class: com.amazon.alexa.eventbus.core.LocalEventBus.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Message message;
                UUID uuid = (UUID) intent.getSerializableExtra(BroadcastPublisher.INTENT_PAYLOAD_EVENT_BUS_UUID);
                if (uuid == null || uuid.equals(LocalEventBus.this.getUUID()) || (message = (Message) intent.getParcelableExtra("msg")) == null) {
                    return;
                }
                try {
                    LocalEventBus.this.dispatcher.dispatch(message);
                } catch (Exception unused) {
                }
            }
        };
    }

    public void clearRegistry() {
        this.registry.clear();
    }

    public void flushDispatcher() {
        this.dispatcher.flush();
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public Publisher getPublisher(boolean z) {
        if (z) {
            return new BroadcastPublisher(this.context, getUUID(), new LocalPublisher(this.dispatcher));
        }
        return new LocalPublisher(this.dispatcher);
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public MultiFilterSubscriber getSubscriber() {
        SimpleMultiFilterSubscriber simpleMultiFilterSubscriber = new SimpleMultiFilterSubscriber();
        subscribe(simpleMultiFilterSubscriber);
        return simpleMultiFilterSubscriber;
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public UUID getUUID() {
        UUID uuid;
        synchronized (this.uuidLock) {
            if (this.uuid == null) {
                this.uuid = UUID.randomUUID();
            }
            uuid = this.uuid;
        }
        return uuid;
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public void publish(@NonNull Message message) throws EventBusException {
        try {
            this.defaultPublisher.publish(message);
        } catch (Exception e) {
            throw new EventBusException(e);
        }
    }

    @Override // com.amazon.alexa.component.api.ServiceLifecycle
    public void start() {
        this.dispatcher.start();
        LocalBroadcastManager.getInstance(this.context).registerReceiver(this.broadcastReceiver, new IntentFilter(BroadcastPublisher.INTENT_ACTION));
    }

    @Override // com.amazon.alexa.component.api.ServiceLifecycle
    public void stop() {
        LocalBroadcastManager.getInstance(this.context).unregisterReceiver(this.broadcastReceiver);
        this.dispatcher.stop();
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public void subscribe(@NonNull Subscriber subscriber) {
        this.registry.addSubscriber(subscriber);
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public void unsubscribe(@NonNull Subscriber subscriber) {
        this.registry.removeSubscriber(subscriber);
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    public void unsubscribe(@NonNull Subscriber.SubscriberUuid subscriberUuid) {
        this.registry.removeSubscriber(subscriberUuid.getUuid());
    }

    @Override // com.amazon.alexa.eventbus.api.EventBus
    @Deprecated
    public void unsubscribe(@NonNull UUID uuid) {
        this.registry.removeSubscriber(uuid);
    }

    public LocalEventBus(@NonNull Context context) {
        Objects.requireNonNull(context);
        this.context = context;
        this.registry = new Registry();
        this.dispatcher = new LocalDispatcher(this.registry);
        this.defaultPublisher = new LocalPublisher(this.dispatcher);
        this.broadcastReceiver = createBroadcastReceiver();
        this.uuid = null;
        this.uuidLock = new Object();
    }
}
