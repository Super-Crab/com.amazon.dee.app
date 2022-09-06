package com.amazon.alexa.eventbus.api;

import androidx.annotation.NonNull;
import java.util.UUID;
/* loaded from: classes7.dex */
public interface MultiFilterSubscriber extends Subscriber {

    /* loaded from: classes7.dex */
    public interface FilterUuid {
        UUID getUuid();
    }

    @Deprecated
    UUID subscribe(@NonNull MessageFilter messageFilter, @NonNull MessageHandler messageHandler);

    default FilterUuid subscribeFilter(@NonNull MessageFilter messageFilter, @NonNull MessageHandler messageHandler) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    void unsubscribe(@NonNull UUID uuid);

    default boolean unsubscribeFilter(@NonNull FilterUuid filterUuid) {
        throw new UnsupportedOperationException();
    }
}
