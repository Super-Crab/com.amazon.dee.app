package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public interface AlertsApi {
    void deregisterListener(@NonNull AlertsListener alertsListener);

    void registerListener(@NonNull AlertsListener alertsListener);
}
