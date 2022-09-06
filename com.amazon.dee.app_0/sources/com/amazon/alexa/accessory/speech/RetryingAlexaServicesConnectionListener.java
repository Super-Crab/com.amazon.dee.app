package com.amazon.alexa.accessory.speech;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener;
/* loaded from: classes6.dex */
public class RetryingAlexaServicesConnectionListener implements ConnectionListener {
    private int attempts;
    protected final AlexaConnection connection;

    public RetryingAlexaServicesConnectionListener(AlexaConnection alexaConnection, int i) {
        this.connection = alexaConnection;
        this.attempts = i;
    }

    @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
    public void onConnected() {
        this.connection.deregisterConnectionListener(this);
    }

    @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
    public void onConnectingFailed(ConnectionListener.ConnectingFailedReason connectingFailedReason, String str) {
        Logger.d("AlexaServicesConnection request failed. %d retry attempts left. Reason: %s Message: %s", Integer.valueOf(this.attempts), connectingFailedReason, str);
        int i = this.attempts;
        if (i > 0) {
            this.attempts = i - 1;
            this.connection.connect();
            return;
        }
        this.connection.deregisterConnectionListener(this);
        Logger.e("RetryingAlexaServicesConnectionListener giving up trying to connect.");
    }

    @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
    public void onDisconnected() {
        this.connection.deregisterConnectionListener(this);
    }
}
