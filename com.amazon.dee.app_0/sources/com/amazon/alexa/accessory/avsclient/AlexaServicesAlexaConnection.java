package com.amazon.alexa.accessory.avsclient;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaCaptionListener;
import com.amazon.alexa.api.AlexaCardListener;
import com.amazon.alexa.api.AlexaDataSink;
import com.amazon.alexa.api.AlexaDialogControllerV2;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.AlexaSettingsListener;
import com.amazon.alexa.api.compat.AlexaAudioPlaybackStatusListener;
import com.amazon.alexa.api.compat.AlexaReadinessListener;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.api.compat.AlexaStateListener;
import com.amazon.alexa.api.compat.AlexaUserSpeechProvider;
import com.amazon.alexa.api.compat.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.compat.alerts.AlertsListener;
import com.amazon.alexa.api.compat.metrics.UserPerceivedLatencyListener;
import java.util.Locale;
/* loaded from: classes.dex */
public final class AlexaServicesAlexaConnection implements AlexaConnection {
    private final AlexaServicesConnection connection;
    private final Handler mainThreadHandler;

    public AlexaServicesAlexaConnection(AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "connection");
        this.connection = alexaServicesConnection;
        this.connection.setAllowsBackgroundActivityStarts(true);
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void cancelUserInteraction() {
        AlexaServices.Recognizer.cancelUserInteraction(this.connection);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void connect() {
        this.connection.connect();
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void continueDialog(AlexaDialogControllerV2 alexaDialogControllerV2, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink) {
        AlexaServices.Recognizer.continueDialog(this.connection, alexaDialogControllerV2, alexaAudioMetadata, alexaAudioSink);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void deRegisterUserSpeechProvider(AlexaUserSpeechProvider alexaUserSpeechProvider) {
        AlexaServicesApis.UserSpeechProviders.deregister(this.connection, alexaUserSpeechProvider);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void deregisterAlertsListener(AlertsListener alertsListener) {
        AlexaServices.Alerts.deregisterListener(this.connection, alertsListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void deregisterAudioPlaybackStatusListener(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        AlexaServicesApis.AudioPlaybackControl.deregister(this.connection, alexaAudioPlaybackStatusListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void deregisterCaptionListener(AlexaCaptionListener alexaCaptionListener) {
        AlexaServicesApis.Caption.deregisterCaptionListener(this.connection, alexaCaptionListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void deregisterCardListener(AlexaCardListener alexaCardListener) {
        AlexaServices.Cards.deregisterAlexaCardRendererListener(this.connection, alexaCardListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void deregisterConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener) {
        this.connection.deregisterListener(connectionListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void deregisterReadinessListener(AlexaReadinessListener alexaReadinessListener) {
        AlexaServices.Readiness.deregister(this.connection, alexaReadinessListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void deregisterSettingsListener(AlexaSettingsListener alexaSettingsListener) {
        AlexaServices.Settings.deregisterListener(this.connection, alexaSettingsListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void deregisterStateListener(AlexaStateListener alexaStateListener) {
        AlexaServices.Recognizer.deregisterListener(this.connection, alexaStateListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void deregisterUplListener(UserPerceivedLatencyListener userPerceivedLatencyListener) {
        AlexaServices.Metrics.deregisterListener(this.connection, userPerceivedLatencyListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void disconnect() {
        this.connection.disconnect();
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public Locale getLocale() {
        return AlexaServices.Settings.getLocale(this.connection);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public boolean isConnected() {
        return this.connection.isConnected();
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public boolean isConnecting() {
        return this.connection.isConnecting();
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void registerAlertsListener(AlertsListener alertsListener) {
        AlexaServices.Alerts.registerListener(this.connection, alertsListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void registerAudioPlaybackStatusListener(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        AlexaServicesApis.AudioPlaybackControl.register(this.connection, alexaAudioPlaybackStatusListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void registerCaptionListener(AlexaCaptionListener alexaCaptionListener) {
        AlexaServicesApis.Caption.registerCaptionListener(this.connection, alexaCaptionListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void registerCardListener(AlexaCardListener alexaCardListener) {
        AlexaServices.Cards.registerAlexaCardRendererListener(this.connection, alexaCardListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void registerConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener) {
        this.connection.registerListener(this.mainThreadHandler, connectionListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void registerReadinessListener(AlexaReadinessListener alexaReadinessListener) {
        AlexaServices.Readiness.register(this.connection, alexaReadinessListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void registerSettingsListener(AlexaSettingsListener alexaSettingsListener) {
        AlexaServices.Settings.registerListener(this.connection, alexaSettingsListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void registerStateListener(AlexaStateListener alexaStateListener) {
        AlexaServices.Recognizer.registerListener(this.connection, alexaStateListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void registerUplListener(UserPerceivedLatencyListener userPerceivedLatencyListener) {
        AlexaServices.Metrics.registerListener(this.connection, userPerceivedLatencyListener);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void registerUserSpeechProvider(AlexaUserSpeechProvider alexaUserSpeechProvider, AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
        AlexaServicesApis.UserSpeechProviders.register(this.connection, alexaUserSpeechProvider, alexaUserSpeechProviderMetadata);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void requestDialog(AlexaUserSpeechProvider alexaUserSpeechProvider, AlexaDialogRequest alexaDialogRequest) {
        AlexaServicesApis.UserSpeechRecognizer.requestDialog(this.connection, alexaUserSpeechProvider, alexaDialogRequest);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void setAllowsBackgroundActivityStarts(boolean z) {
        this.connection.setAllowsBackgroundActivityStarts(z);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void startDialog(AlexaDialogControllerV2 alexaDialogControllerV2, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink, AlexaDialogExtras alexaDialogExtras) {
        AlexaServices.Recognizer.startDialog(this.connection, alexaDialogControllerV2, alexaAudioMetadata, alexaAudioSink, alexaDialogExtras);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void stopDialogTurn(AlexaDialogControllerV2 alexaDialogControllerV2) {
        AlexaServices.Recognizer.stopDialogTurn(this.connection, alexaDialogControllerV2);
    }

    @Override // com.amazon.alexa.accessory.avsclient.AlexaConnection
    public void startDialog(AlexaDialogControllerV2 alexaDialogControllerV2, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink, AlexaDataSink alexaDataSink, AlexaDialogExtras alexaDialogExtras) {
        AlexaServices.Recognizer.startDialog(this.connection, alexaDialogControllerV2, alexaAudioMetadata, alexaAudioSink, alexaDataSink, alexaDialogExtras);
    }
}
