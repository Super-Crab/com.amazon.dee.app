package com.amazon.alexa.voice.sdk;

import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes11.dex */
public class AlexaStateAdapter implements AlexaServicesConnection.ConnectionListener {
    private final AlexaServicesConnection alexaServicesConnection;
    private final AlexaStateTracker alexaStateTracker;

    public AlexaStateAdapter(AlexaServicesConnection alexaServicesConnection, AlexaStateTracker alexaStateTracker) {
        this.alexaServicesConnection = alexaServicesConnection;
        this.alexaStateTracker = alexaStateTracker;
    }

    public void initialize() {
        this.alexaServicesConnection.registerListener(this);
    }

    public boolean isListeningOrProcessing() {
        return this.alexaStateTracker.isListeningOrProcessing();
    }

    public boolean isSpeaking() {
        return this.alexaStateTracker.isSpeaking();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        AlexaServices.Recognizer.registerListener(this.alexaServicesConnection, this.alexaStateTracker);
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        AlexaServices.Recognizer.deregisterListener(this.alexaServicesConnection, this.alexaStateTracker);
    }

    public Observable<Boolean> onIdle() {
        return this.alexaStateTracker.onIdle();
    }

    public Observable<Boolean> onListening() {
        return this.alexaStateTracker.onListening();
    }

    public Observable<Boolean> onListeningOrProcessing() {
        return this.alexaStateTracker.onListeningOrProcessing();
    }

    public Observable<Boolean> onProcessing() {
        return this.alexaStateTracker.onProcessing();
    }

    public Observable<Boolean> onSpeaking() {
        return this.alexaStateTracker.onSpeaking();
    }

    public void release() {
        this.alexaServicesConnection.deregisterListener(this);
    }
}
