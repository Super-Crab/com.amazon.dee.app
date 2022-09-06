package com.amazon.alexa.accessory;

import com.amazon.alexa.accessory.AccessoryTransport;
/* loaded from: classes.dex */
public abstract class SessionListener {
    public abstract void onSessionConnected();

    public abstract void onSessionDisconnected();

    public abstract void onSessionFailed(Throwable th);

    public void onSessionTransportChanged(Accessory accessory, AccessoryTransport.Type type, AccessoryTransport.Type type2) {
    }
}
