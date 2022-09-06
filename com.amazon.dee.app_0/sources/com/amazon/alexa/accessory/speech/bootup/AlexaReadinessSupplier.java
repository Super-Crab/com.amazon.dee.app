package com.amazon.alexa.accessory.speech.bootup;

import com.amazon.alexa.accessory.avsclient.bootup.ReadinessSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener;
import com.amazon.alexa.accessory.speechapi.listeners.ReadinessListener;
import com.amazon.alexa.accessory.speechapi.listeners.ReadyState;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public class AlexaReadinessSupplier implements ReadinessSupplier {
    private static final String TAG = "AlexaReadinessSupplier";
    private final AlexaConnection alexaConnection;
    private final ReadinessListener readinessListener = new VoiceReadinessListener();
    private final ConnectionListener connectionListener = new ServiceConnectionListener();
    private final List<ReadinessSupplier.Listener> listeners = new ArrayList();

    /* loaded from: classes6.dex */
    private final class ServiceConnectionListener implements ConnectionListener {
        private ServiceConnectionListener() {
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
        public void onConnected() {
            Logger.d("AlexaReadinessSupplier ConnectionListener onConnected");
            synchronized (AlexaReadinessSupplier.this.listeners) {
                if (!AlexaReadinessSupplier.this.listeners.isEmpty()) {
                    AlexaReadinessSupplier.this.alexaConnection.registerReadinessListener(AlexaReadinessSupplier.this.readinessListener);
                }
            }
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
        public void onConnectingFailed(ConnectionListener.ConnectingFailedReason connectingFailedReason, String str) {
            Logger.d("%s : ConnectionListener onConnectingFailed for reason: %s and message: %s", AlexaReadinessSupplier.TAG, connectingFailedReason, str);
            if (!AlexaReadinessSupplier.this.alexaConnection.isConnected()) {
                return;
            }
            synchronized (AlexaReadinessSupplier.this.listeners) {
                for (ReadinessSupplier.Listener listener : AlexaReadinessSupplier.this.listeners) {
                    listener.onReadyState(false);
                }
            }
            AlexaReadinessSupplier.this.alexaConnection.deregisterReadinessListener(AlexaReadinessSupplier.this.readinessListener);
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener
        public void onDisconnected() {
            Logger.d("%s : ConnectionListener onDisconnected", AlexaReadinessSupplier.TAG);
            synchronized (AlexaReadinessSupplier.this.listeners) {
                for (ReadinessSupplier.Listener listener : AlexaReadinessSupplier.this.listeners) {
                    listener.onReadyState(false);
                }
            }
            AlexaReadinessSupplier.this.alexaConnection.deregisterReadinessListener(AlexaReadinessSupplier.this.readinessListener);
        }
    }

    /* loaded from: classes6.dex */
    private final class VoiceReadinessListener implements ReadinessListener {
        private VoiceReadinessListener() {
        }

        @Override // com.amazon.alexa.accessory.speechapi.listeners.ReadinessListener
        public void onReadinessChanged(ReadyState readyState) {
            Logger.d("%s : ReadinessListener onReadinessChanged alexaReadyState=%b", AlexaReadinessSupplier.TAG, Boolean.valueOf(readyState.isReady()));
            synchronized (AlexaReadinessSupplier.this.listeners) {
                for (ReadinessSupplier.Listener listener : AlexaReadinessSupplier.this.listeners) {
                    listener.onReadyState(readyState.isReady());
                }
            }
        }
    }

    public AlexaReadinessSupplier(AlexaConnection alexaConnection) {
        this.alexaConnection = alexaConnection;
    }

    @Override // com.amazon.alexa.accessory.avsclient.bootup.ReadinessSupplier
    public void deregisterReadinessListener(ReadinessSupplier.Listener listener) {
        synchronized (this.listeners) {
            this.listeners.remove(listener);
            if (this.listeners.isEmpty()) {
                this.alexaConnection.deregisterConnectionListener(this.connectionListener);
            }
        }
    }

    @Override // com.amazon.alexa.accessory.avsclient.bootup.ReadinessSupplier
    public void registerReadinessListener(ReadinessSupplier.Listener listener) {
        synchronized (this.listeners) {
            this.listeners.add(listener);
        }
        if (this.listeners.size() == 1) {
            this.alexaConnection.registerConnectionListener(this.connectionListener);
        }
    }
}
