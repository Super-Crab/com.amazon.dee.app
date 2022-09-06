package com.amazon.alexa.voice.ui;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.core.EventBusUuid;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes11.dex */
public class DefaultVoiceEventBusRebroadcastSender implements VoiceEventBusRebroadcastSender, Subscriber {
    static final String EXTRA_MESSAGE = "com.amazon.alexa.voice.ui.intent.extras.REBROADCAST_EVENT_MESSAGE";
    static final String INTENT_ACTION = "com.amazon.alexa.voice.ui.intent.REBROADCAST_EVENT";
    private final Context context;
    private final EventBus eventBus;
    private final Subscriber.SubscriberUuid uuid = new SubscriberUuidImpl();
    private static final String THEME_PREFERENCE_ACTION = "ui:theme:preference";
    static final Set<String> EVENT_TYPES = ImmutableSet.of(THEME_PREFERENCE_ACTION);

    /* loaded from: classes11.dex */
    private class SubscriberUuidImpl extends EventBusUuid implements Subscriber.SubscriberUuid {
        private SubscriberUuidImpl() {
        }
    }

    public DefaultVoiceEventBusRebroadcastSender(@NonNull Context context, @NonNull EventBus eventBus) {
        this.context = context;
        this.eventBus = eventBus;
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public Subscriber.SubscriberUuid getSubscriberUuid() {
        return this.uuid;
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public UUID getUUID() {
        return this.uuid.getUuid();
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public void onMessageReceived(@NonNull @NotNull Message message) {
        Intent intent = new Intent(INTENT_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        this.context.sendBroadcast(intent);
    }

    @Override // com.amazon.alexa.voice.ui.VoiceEventBusRebroadcastSender
    public void start() {
        this.eventBus.subscribe(this);
    }

    @Override // com.amazon.alexa.voice.ui.VoiceEventBusRebroadcastSender
    public void stop() {
        this.eventBus.unsubscribe(this);
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public boolean supportsMessage(@NonNull @NotNull Message message) {
        return EVENT_TYPES.contains(message.getEventType());
    }
}
