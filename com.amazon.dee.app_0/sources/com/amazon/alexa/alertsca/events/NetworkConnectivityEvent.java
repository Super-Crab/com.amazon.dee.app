package com.amazon.alexa.alertsca.events;

import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class NetworkConnectivityEvent extends Event {
    public static NetworkConnectivityEvent create(boolean z) {
        return new AutoValue_NetworkConnectivityEvent(z);
    }

    public abstract boolean isConnected();
}
