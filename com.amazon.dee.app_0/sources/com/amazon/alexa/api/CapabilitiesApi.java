package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.Set;
/* loaded from: classes6.dex */
public interface CapabilitiesApi {
    void cacheContexts(@NonNull Set<AlexaContext> set);

    void clearContextCache();

    void clearContextCache(@NonNull Set<String> set);

    void deregisterContextsProvider(@NonNull AlexaContextsProvider alexaContextsProvider);

    void registerContextsProvider(@NonNull AlexaContextsProvider alexaContextsProvider);

    void sendEvent(@NonNull AlexaEvent alexaEvent);

    void sendEvent(@NonNull AlexaEvent alexaEvent, boolean z);

    void sendEvent(@NonNull AlexaEvent alexaEvent, boolean z, @Nullable AlexaApiCallbacks alexaApiCallbacks);

    void setContextCachingEnabled(@NonNull Set<String> set, boolean z);

    void setContextCachingEnabled(boolean z);
}
