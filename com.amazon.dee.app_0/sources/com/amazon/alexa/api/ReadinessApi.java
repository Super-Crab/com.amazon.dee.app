package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public interface ReadinessApi {
    void deregisterListener(@NonNull AlexaReadinessListener alexaReadinessListener);

    void registerListener(@NonNull AlexaReadinessListener alexaReadinessListener);
}
