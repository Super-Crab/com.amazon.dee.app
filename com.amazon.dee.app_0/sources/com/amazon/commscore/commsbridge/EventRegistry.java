package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import com.amazon.commscore.api.commsbridge.EventListener;
import java.util.Set;
import java.util.UUID;
/* loaded from: classes12.dex */
interface EventRegistry {
    boolean addEventListener(@NonNull String str, @NonNull EventListener<String> eventListener, @NonNull UUID uuid);

    EventListener<String> getEventListener(@NonNull UUID uuid);

    Set<EventListener<String>> getEventListeners(@NonNull String str);

    EventListener<String> removeEventListener(@NonNull UUID uuid);
}
