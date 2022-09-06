package com.amazon.alexa.voice.sdk;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.voice.enablement.VoiceEnablement;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
/* loaded from: classes11.dex */
public final class DefaultAlexaConnectionManager implements AlexaConnectionManager, AlexaServicesConnection.ConnectionListener {
    private final AlexaServicesConnection alexaServicesConnection;
    private final AppForegroundChecker appForegroundChecker = new AppForegroundChecker();
    private final InvalidSigningReporter invalidSigningReporter;
    private final VoiceEnablement voiceEnablement;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class AppForegroundChecker implements ApplicationLifecycleObserver {
        private boolean isAppInForeground;

        AppForegroundChecker() {
        }

        public boolean isAppInForeground() {
            return this.isAppInForeground;
        }

        @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
        public void onStart() {
            this.isAppInForeground = true;
        }

        @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
        public void onStop() {
            this.isAppInForeground = false;
        }
    }

    @FunctionalInterface
    /* loaded from: classes11.dex */
    public interface InvalidSigningReporter {
        void showToast(String str);
    }

    public DefaultAlexaConnectionManager(@NonNull InvalidSigningReporter invalidSigningReporter, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull ApplicationLifecycleService applicationLifecycleService, @NonNull VoiceEnablement voiceEnablement) {
        this.alexaServicesConnection = alexaServicesConnection;
        this.voiceEnablement = voiceEnablement;
        this.invalidSigningReporter = invalidSigningReporter;
        applicationLifecycleService.addObserver(this.appForegroundChecker);
        initialize();
    }

    private void connect() {
        if (!this.voiceEnablement.isVoicePossible()) {
            Logger.debug("Voice is not possible");
            return;
        }
        Logger.debug("try to connect to Alexa Service");
        this.alexaServicesConnection.connect();
    }

    private void disconnect() {
        if (!this.appForegroundChecker.isAppInForeground()) {
            Logger.debug("try to disconnect from Alexa Service");
            this.alexaServicesConnection.disconnect();
        }
    }

    @NonNull
    public AlexaServicesConnection getAlexaServicesConnection() {
        return this.alexaServicesConnection;
    }

    @VisibleForTesting
    AppForegroundChecker getAppForegroundChecker() {
        return this.appForegroundChecker;
    }

    public void initialize() {
        this.alexaServicesConnection.registerListener(this);
    }

    @Override // com.amazon.alexa.voice.sdk.AlexaConnectionManager
    public void onBackground() {
        disconnect();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        Logger.debug("Connected to Alexa Service");
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        Logger.debug("Failed to connect to Alexa Service. Reason: " + alexaConnectingFailedReason + ". Message:" + str);
        if (AlexaConnectingFailedReason.UNAUTHORIZED.equals(alexaConnectingFailedReason)) {
            Logger.error("This app was signed incorrectly. Voice features will not work", null);
            this.invalidSigningReporter.showToast("This app was signed incorrectly. Voice features will not work");
        }
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        Logger.debug("Disconnected from Alexa Service");
        if (this.appForegroundChecker.isAppInForeground()) {
            Logger.debug("Reconnecting to AlexaService because app is still in foreground");
            connect();
        }
    }

    @Override // com.amazon.alexa.voice.sdk.AlexaConnectionManager
    public void onForeground() {
        connect();
    }

    public void release() {
        this.alexaServicesConnection.disconnect();
        this.alexaServicesConnection.deregisterListener(this);
    }
}
