package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.Accessory;
/* loaded from: classes.dex */
public interface PeripheralConnectivity {

    /* loaded from: classes.dex */
    public interface Callback {
        void onConnectionStatus(ConnectionStatus connectionStatus);
    }

    /* loaded from: classes.dex */
    public enum ConnectionStatus {
        UNKNOWN,
        CONNECTED,
        DISCONNECTED
    }

    void getConnectionStatus(Accessory accessory, Callback callback);
}
