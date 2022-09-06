package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public interface MetricsApi {
    void deregisterListener(@NonNull AlexaMetricsListener alexaMetricsListener);

    void registerListener(@NonNull AlexaMetricsListener alexaMetricsListener);
}
