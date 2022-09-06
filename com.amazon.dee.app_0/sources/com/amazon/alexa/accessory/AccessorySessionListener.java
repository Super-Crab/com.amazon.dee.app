package com.amazon.alexa.accessory;

import com.amazon.alexa.accessory.AccessoryTransport;
/* loaded from: classes.dex */
public abstract class AccessorySessionListener {
    public void onAccessorySessionConnected(Accessory accessory) {
    }

    public void onAccessorySessionCreated(Accessory accessory) {
    }

    public void onAccessorySessionDisconnected(Accessory accessory) {
    }

    public void onAccessorySessionFailed(Accessory accessory, Throwable th) {
    }

    public void onAccessorySessionReleased(Accessory accessory) {
    }

    public void onAccessorySessionTransportChanged(Accessory accessory, AccessoryTransport.Type type, Accessory accessory2, AccessoryTransport.Type type2) {
    }
}
