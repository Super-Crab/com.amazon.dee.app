package com.amazon.alexa.hho.stickynotes;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.identity.api.IdentityEvent;
import java.util.UUID;
/* loaded from: classes8.dex */
class IdentitySubscriber implements Subscriber {
    private final Handler handler;
    private final UUID uuid = UUID.randomUUID();

    /* loaded from: classes8.dex */
    interface Handler {
        void handleIdentityChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IdentitySubscriber(Handler handler) {
        this.handler = handler;
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public UUID getUUID() {
        return this.uuid;
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public void onMessageReceived(@NonNull Message message) {
        if (IdentityEvent.IDENTITY_CHANGED.equals(message.getEventType())) {
            this.handler.handleIdentityChanged();
        }
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public boolean supportsMessage(@NonNull Message message) {
        return IdentityEvent.IDENTITY_CHANGED.equals(message.getEventType());
    }
}
