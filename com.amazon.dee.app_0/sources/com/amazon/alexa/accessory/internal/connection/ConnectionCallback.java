package com.amazon.alexa.accessory.internal.connection;
/* loaded from: classes.dex */
public interface ConnectionCallback {
    void onActive();

    void onConnected();

    void onDisconnected();

    void onFailed(Throwable th);

    void onIdle();
}
