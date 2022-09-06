package com.amazon.alexa.accessory.avsclient;

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
import com.amazon.alexa.api.compat.AlexaStateListener;
import com.amazon.alexa.api.compat.AlexaUserSpeechProvider;
import com.amazon.alexa.api.compat.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.compat.alerts.AlertsListener;
import com.amazon.alexa.api.compat.metrics.UserPerceivedLatencyListener;
import java.util.Locale;
/* loaded from: classes.dex */
public interface AlexaConnection {
    void cancelUserInteraction();

    void connect();

    void continueDialog(AlexaDialogControllerV2 alexaDialogControllerV2, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink);

    void deRegisterUserSpeechProvider(AlexaUserSpeechProvider alexaUserSpeechProvider);

    void deregisterAlertsListener(AlertsListener alertsListener);

    void deregisterAudioPlaybackStatusListener(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener);

    void deregisterCaptionListener(AlexaCaptionListener alexaCaptionListener);

    void deregisterCardListener(AlexaCardListener alexaCardListener);

    void deregisterConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener);

    void deregisterReadinessListener(AlexaReadinessListener alexaReadinessListener);

    void deregisterSettingsListener(AlexaSettingsListener alexaSettingsListener);

    void deregisterStateListener(AlexaStateListener alexaStateListener);

    void deregisterUplListener(UserPerceivedLatencyListener userPerceivedLatencyListener);

    void disconnect();

    Locale getLocale();

    boolean isConnected();

    boolean isConnecting();

    void registerAlertsListener(AlertsListener alertsListener);

    void registerAudioPlaybackStatusListener(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener);

    void registerCaptionListener(AlexaCaptionListener alexaCaptionListener);

    void registerCardListener(AlexaCardListener alexaCardListener);

    void registerConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener);

    void registerReadinessListener(AlexaReadinessListener alexaReadinessListener);

    void registerSettingsListener(AlexaSettingsListener alexaSettingsListener);

    void registerStateListener(AlexaStateListener alexaStateListener);

    void registerUplListener(UserPerceivedLatencyListener userPerceivedLatencyListener);

    void registerUserSpeechProvider(AlexaUserSpeechProvider alexaUserSpeechProvider, AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata);

    void requestDialog(AlexaUserSpeechProvider alexaUserSpeechProvider, AlexaDialogRequest alexaDialogRequest);

    void setAllowsBackgroundActivityStarts(boolean z);

    void startDialog(AlexaDialogControllerV2 alexaDialogControllerV2, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink, AlexaDataSink alexaDataSink, AlexaDialogExtras alexaDialogExtras);

    void startDialog(AlexaDialogControllerV2 alexaDialogControllerV2, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink, AlexaDialogExtras alexaDialogExtras);

    void stopDialogTurn(AlexaDialogControllerV2 alexaDialogControllerV2);
}
