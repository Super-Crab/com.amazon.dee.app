package com.amazon.alexa.accessory.internal.connection;

import com.amazon.alexa.accessory.internal.util.Preconditions;
/* loaded from: classes.dex */
public abstract class DelegateConnectionCallback implements ConnectionCallback {
    private final ConnectionCallback callback;

    public DelegateConnectionCallback(ConnectionCallback connectionCallback) {
        Preconditions.notNull(connectionCallback, "callback");
        this.callback = connectionCallback;
    }

    @Override // com.amazon.alexa.accessory.internal.connection.ConnectionCallback
    public void onActive() {
        this.callback.onActive();
    }

    @Override // com.amazon.alexa.accessory.internal.connection.ConnectionCallback
    public void onConnected() {
        this.callback.onConnected();
    }

    @Override // com.amazon.alexa.accessory.internal.connection.ConnectionCallback
    public void onDisconnected() {
        this.callback.onDisconnected();
    }

    @Override // com.amazon.alexa.accessory.internal.connection.ConnectionCallback
    public void onFailed(Throwable th) {
        this.callback.onFailed(th);
    }

    @Override // com.amazon.alexa.accessory.internal.connection.ConnectionCallback
    public void onIdle() {
        this.callback.onIdle();
    }
}
