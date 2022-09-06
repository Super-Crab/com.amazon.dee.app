package com.amazon.alexa.accessory.internal.bluetooth.spp;

import android.bluetooth.BluetoothSocket;
/* loaded from: classes.dex */
public interface SppServer {

    /* loaded from: classes.dex */
    public interface Listener {
        void onSppClientConnected(BluetoothSocket bluetoothSocket);

        void onSppServerFailed(Throwable th);

        void onSppServerStarted();

        void onSppServerStopped();
    }

    void setListener(Listener listener);

    void start();

    void stop();
}
