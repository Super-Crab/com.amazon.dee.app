package com.amazon.alexa.eventbus.api;

import androidx.annotation.NonNull;
import java.util.UUID;
/* loaded from: classes7.dex */
public interface Subscriber {

    /* loaded from: classes7.dex */
    public interface SubscriberUuid {
        UUID getUuid();
    }

    default SubscriberUuid getSubscriberUuid() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    UUID getUUID();

    void onMessageReceived(@NonNull Message message);

    boolean supportsMessage(@NonNull Message message);
}
