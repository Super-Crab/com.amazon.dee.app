package com.amazon.alexa.voice.wakeword;

import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import java.util.LinkedHashSet;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class WakeWordFeatureGate implements AlexaServicesConnection.ConnectionListener {
    private final AlexaServicesConnection alexaServicesConnection;
    private volatile boolean isConnected;
    private final Set<AvailabilityListener> listeners = new LinkedHashSet();
    private boolean wasAvailable;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public interface AvailabilityListener {
        void onAvailable();

        void onUnavailable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WakeWordFeatureGate(AlexaServicesConnection alexaServicesConnection, AbiCompatibilityInterface abiCompatibilityInterface) {
        this.alexaServicesConnection = alexaServicesConnection;
        if (abiCompatibilityInterface.isCompatible()) {
            alexaServicesConnection.registerListener(this);
        }
    }

    private void updateWakeWordRegistration() {
        if (this.wasAvailable && !isAvailable()) {
            this.wasAvailable = false;
            for (AvailabilityListener availabilityListener : this.listeners) {
                availabilityListener.onUnavailable();
            }
        } else if (!this.wasAvailable && isAvailable()) {
            this.wasAvailable = true;
            for (AvailabilityListener availabilityListener2 : this.listeners) {
                availabilityListener2.onAvailable();
            }
        }
    }

    public synchronized void deregister(AvailabilityListener availabilityListener) {
        this.listeners.remove(availabilityListener);
    }

    public synchronized boolean isAvailable() {
        return this.isConnected;
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        this.isConnected = this.alexaServicesConnection.isConnected();
        updateWakeWordRegistration();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        this.isConnected = false;
        updateWakeWordRegistration();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        this.isConnected = false;
        updateWakeWordRegistration();
    }

    public synchronized void register(AvailabilityListener availabilityListener) {
        this.listeners.add(availabilityListener);
        if (isAvailable()) {
            availabilityListener.onAvailable();
        } else {
            availabilityListener.onUnavailable();
        }
    }
}
