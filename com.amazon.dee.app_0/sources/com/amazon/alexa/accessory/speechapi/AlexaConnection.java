package com.amazon.alexa.accessory.speechapi;

import com.amazon.alexa.accessory.speechapi.context.MessageContextProvider;
import com.amazon.alexa.accessory.speechapi.events.AccessoryEventSender;
import com.amazon.alexa.accessory.speechapi.events.AccessoryStateReportGenerator;
import com.amazon.alexa.accessory.speechapi.events.ApiCallbacks;
import com.amazon.alexa.accessory.speechapi.events.MessageEvent;
import com.amazon.alexa.accessory.speechapi.listeners.AlertsListener;
import com.amazon.alexa.accessory.speechapi.listeners.AudioPlaybackStatusListener;
import com.amazon.alexa.accessory.speechapi.listeners.CaptionListener;
import com.amazon.alexa.accessory.speechapi.listeners.CardListener;
import com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener;
import com.amazon.alexa.accessory.speechapi.listeners.ReadinessListener;
import com.amazon.alexa.accessory.speechapi.listeners.SettingsListener;
import com.amazon.alexa.accessory.speechapi.listeners.StateListener;
import com.amazon.alexa.accessory.speechapi.speech.AccessorySink;
import com.amazon.alexa.accessory.speechapi.speech.DialogRequest;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProviderMetadata;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Locale;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaConnection.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\tH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0014H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0018H&J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH&J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u001dH&J\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u001fH&J\u0010\u0010 \u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020!H&J\b\u0010\"\u001a\u00020\u0003H&J\n\u0010#\u001a\u0004\u0018\u00010$H&J\b\u0010%\u001a\u00020&H&J\b\u0010'\u001a\u00020&H&J\u0010\u0010(\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u0010)\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0012H&J\u0010\u0010*\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0014H&J\u0010\u0010+\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0016H&J\u0010\u0010,\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0018H&J\u0010\u0010-\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH&J\u0010\u0010.\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u001dH&J\u0010\u0010/\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u001fH&J\u0010\u00100\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020!H&J\u0018\u00101\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u00102\u001a\u000203H&J\u0018\u00104\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u00105\u001a\u000206H&J\u0018\u00107\u001a\u00020\u00032\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H&J\u0010\u0010<\u001a\u00020\u00032\u0006\u0010=\u001a\u00020&H&J \u0010>\u001a\u00020\u00032\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DH&¨\u0006E"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/AlexaConnection;", "", "bindAmbientSoundDispatcher", "", "ambientSoundDispatcher", "Lcom/amazon/alexa/accessory/speechapi/AmbientSoundDispatcher;", "cancelUserInteraction", EmulateConnection.EXTRA_CONNECT, "createAccessoryAudioSink", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessorySink;", "createAccessoryDataSink", "deRegisterUserSpeechProvider", "userSpeechProvider", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;", "deregisterAlertsListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/alexa/accessory/speechapi/listeners/AlertsListener;", "deregisterAudioPlaybackStatusListener", "Lcom/amazon/alexa/accessory/speechapi/listeners/AudioPlaybackStatusListener;", "deregisterCaptionListener", "Lcom/amazon/alexa/accessory/speechapi/listeners/CaptionListener;", "deregisterCardListener", "Lcom/amazon/alexa/accessory/speechapi/listeners/CardListener;", "deregisterConnectionListener", "Lcom/amazon/alexa/accessory/speechapi/listeners/ConnectionListener;", "deregisterMessageContextProvider", "messageContextProvider", "Lcom/amazon/alexa/accessory/speechapi/context/MessageContextProvider;", "deregisterReadinessListener", "Lcom/amazon/alexa/accessory/speechapi/listeners/ReadinessListener;", "deregisterSettingsListener", "Lcom/amazon/alexa/accessory/speechapi/listeners/SettingsListener;", "deregisterStateListener", "Lcom/amazon/alexa/accessory/speechapi/listeners/StateListener;", Metrics.DISCONNECT, "getLocale", "Ljava/util/Locale;", "isConnected", "", "isConnecting", "registerAlertsListener", "registerAudioPlaybackStatusListener", "registerCaptionListener", "registerCardListener", "registerConnectionListener", "registerMessageContextProvider", "registerReadinessListener", "registerSettingsListener", "registerStateListener", "registerUserSpeechProvider", "metadata", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProviderMetadata;", "requestDialog", "dialogRequest", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogRequest;", "sendEvent", "event", "Lcom/amazon/alexa/accessory/speechapi/events/MessageEvent;", "apiCallbacks", "Lcom/amazon/alexa/accessory/speechapi/events/ApiCallbacks;", "setAllowsBackgroundActivityStarts", "allowsBackgroundActivityStarts", "setReportAccessoryStateDependencies", "reportGenerator", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryStateReportGenerator;", "accessoryEventSender", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryEventSender;", "featureChecker", "Lcom/amazon/alexa/accessory/utils/feature/FeatureChecker;", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AlexaConnection {
    void bindAmbientSoundDispatcher(@NotNull AmbientSoundDispatcher ambientSoundDispatcher);

    void cancelUserInteraction();

    void connect();

    @NotNull
    AccessorySink createAccessoryAudioSink() throws Exception;

    @NotNull
    AccessorySink createAccessoryDataSink() throws Exception;

    void deRegisterUserSpeechProvider(@NotNull UserSpeechProvider userSpeechProvider);

    void deregisterAlertsListener(@NotNull AlertsListener alertsListener);

    void deregisterAudioPlaybackStatusListener(@NotNull AudioPlaybackStatusListener audioPlaybackStatusListener);

    void deregisterCaptionListener(@NotNull CaptionListener captionListener);

    void deregisterCardListener(@NotNull CardListener cardListener);

    void deregisterConnectionListener(@NotNull ConnectionListener connectionListener);

    void deregisterMessageContextProvider(@NotNull MessageContextProvider messageContextProvider);

    void deregisterReadinessListener(@NotNull ReadinessListener readinessListener);

    void deregisterSettingsListener(@NotNull SettingsListener settingsListener);

    void deregisterStateListener(@NotNull StateListener stateListener);

    void disconnect();

    @Nullable
    Locale getLocale();

    boolean isConnected();

    boolean isConnecting();

    void registerAlertsListener(@NotNull AlertsListener alertsListener);

    void registerAudioPlaybackStatusListener(@NotNull AudioPlaybackStatusListener audioPlaybackStatusListener);

    void registerCaptionListener(@NotNull CaptionListener captionListener);

    void registerCardListener(@NotNull CardListener cardListener);

    void registerConnectionListener(@NotNull ConnectionListener connectionListener);

    void registerMessageContextProvider(@NotNull MessageContextProvider messageContextProvider);

    void registerReadinessListener(@NotNull ReadinessListener readinessListener);

    void registerSettingsListener(@NotNull SettingsListener settingsListener);

    void registerStateListener(@NotNull StateListener stateListener);

    void registerUserSpeechProvider(@NotNull UserSpeechProvider userSpeechProvider, @NotNull UserSpeechProviderMetadata userSpeechProviderMetadata);

    void requestDialog(@NotNull UserSpeechProvider userSpeechProvider, @NotNull DialogRequest dialogRequest);

    void sendEvent(@NotNull MessageEvent messageEvent, @NotNull ApiCallbacks apiCallbacks);

    void setAllowsBackgroundActivityStarts(boolean z);

    void setReportAccessoryStateDependencies(@NotNull AccessoryStateReportGenerator accessoryStateReportGenerator, @NotNull AccessoryEventSender accessoryEventSender, @NotNull FeatureChecker featureChecker);
}
