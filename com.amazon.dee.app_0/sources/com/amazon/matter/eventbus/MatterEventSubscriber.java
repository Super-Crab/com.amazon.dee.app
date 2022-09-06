package com.amazon.matter.eventbus;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import java.util.UUID;
/* loaded from: classes9.dex */
public class MatterEventSubscriber implements Subscriber {
    private static final String TAG = "MatterEventSubscriber";
    private final MatterEventHandler eventHandler;
    private final UUID uuid = UUID.randomUUID();

    public MatterEventSubscriber(MatterEventHandler matterEventHandler) {
        this.eventHandler = matterEventHandler;
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public UUID getUUID() {
        return this.uuid;
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public void onMessageReceived(@NonNull Message message) {
        String.format("Matter Message received. messageType= %s & messagePayload= %s", message.getEventType(), message.getPayloadAsString());
        this.eventHandler.handleMatterRequest(message);
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public boolean supportsMessage(@NonNull Message message) {
        return MatterEventType.getEventTypeFromString(message.getEventType()) != MatterEventType.GENERIC_EVENT;
    }
}
