package com.amazon.alexa.api.compat;

import android.content.Context;
import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaAudioInteraction;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioPlaybackListener;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaCardListener;
import com.amazon.alexa.api.AlexaCardRendererListener;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextProvider;
import com.amazon.alexa.api.AlexaDataSink;
import com.amazon.alexa.api.AlexaDialogController;
import com.amazon.alexa.api.AlexaDialogControllerV2;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaMetricsListener;
import com.amazon.alexa.api.AlexaPlayerInfoCardListener;
import com.amazon.alexa.api.AlexaServices;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.AlexaSettingsListener;
import com.amazon.alexa.api.AlexaSettingsListenerProxy;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.AlexaUserSpeechListener;
import com.amazon.alexa.api.LaunchType;
import com.amazon.alexa.api.compat.alerts.AlertsListener;
import com.amazon.alexa.api.compat.alerts.AlertsListenerAdapter;
import com.amazon.alexa.api.compat.metrics.UserPerceivedLatencyListener;
import com.amazon.alexa.api.compat.metrics.UserPerceivedLatencyListenerAdapter;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
import com.amazon.device.messaging.ADMRegistrationConstants;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaServices.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000e\u0018\u0000 \u00072\u00020\u0001:\f\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices;", "", "()V", "Account", "Alerts", "AudioPlaybackControl", "Cards", "Companion", "ContextProvider", "EventSender", "InteractionScheduler", "Metrics", "Readiness", "Recognizer", MetricsConstants.UserInteractionMetrics.SETTINGS_PREFIX, "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaServices {
    public static final Companion Companion = new Companion(null);

    /* compiled from: AlexaServices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices$Account;", "", "()V", "logOut", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Account {
        public static final Account INSTANCE = new Account();

        private Account() {
        }

        @JvmStatic
        public static final void logOut(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.Account.logOut(alexaServicesConnection);
        }
    }

    /* compiled from: AlexaServices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices$Alerts;", "", "()V", "deregisterListener", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alertsListener", "Lcom/amazon/alexa/api/compat/alerts/AlertsListener;", "registerListener", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Alerts {
        public static final Alerts INSTANCE = new Alerts();

        private Alerts() {
        }

        @JvmStatic
        public static final void deregisterListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlertsListener alertsListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alertsListener, "alertsListener");
            AlertsListenerAdapter remove = AlertsSubcomponent.Companion.getListeners().remove(alertsListener);
            if (remove != null) {
                AlexaServices.Alerts.deregisterListener(alexaServicesConnection, remove);
            }
        }

        @JvmStatic
        public static final void registerListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlertsListener alertsListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alertsListener, "alertsListener");
            AlertsListenerAdapter alertsListenerAdapter = new AlertsListenerAdapter(alertsListener);
            AlertsSubcomponent.Companion.getListeners().put(alertsListener, alertsListenerAdapter);
            AlexaServices.Alerts.registerListener(alexaServicesConnection, alertsListenerAdapter);
        }
    }

    /* compiled from: AlexaServices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices$AudioPlaybackControl;", "", "()V", "deregister", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaAudioPlaybackStatusListener", "Lcom/amazon/alexa/api/compat/AlexaAudioPlaybackStatusListener;", "deregisterListener", "alexaAudioPlaybackListener", "Lcom/amazon/alexa/api/AlexaAudioPlaybackListener;", "next", "pause", VoiceBridgePayloadUtil.PayloadCommand.PLAY, "previous", ADMRegistrationConstants.METHOD_REGISTER, "registerListener", "stop", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class AudioPlaybackControl {
        public static final AudioPlaybackControl INSTANCE = new AudioPlaybackControl();

        private AudioPlaybackControl() {
        }

        @JvmStatic
        public static final void deregister(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaAudioPlaybackStatusListener, "alexaAudioPlaybackStatusListener");
            AlexaAudioPlaybackStatusListenerAdapter alexaAudioPlaybackStatusListenerAdapter = new AlexaAudioPlaybackStatusListenerAdapter(alexaAudioPlaybackStatusListener);
            AudioPlaybackControlSubcomponent.Companion.getPlaybackStatusListeners().remove(alexaAudioPlaybackStatusListener, alexaAudioPlaybackStatusListenerAdapter);
            AlexaServices.AudioPlaybackControl.deregister(alexaServicesConnection, alexaAudioPlaybackStatusListenerAdapter);
        }

        @JvmStatic
        public static final void deregisterListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaAudioPlaybackListener alexaAudioPlaybackListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaAudioPlaybackListener, "alexaAudioPlaybackListener");
            AlexaServices.AudioPlaybackControl.deregisterListener(alexaServicesConnection, alexaAudioPlaybackListener);
        }

        @JvmStatic
        public static final void next(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.AudioPlaybackControl.next(alexaServicesConnection);
        }

        @JvmStatic
        public static final void pause(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.AudioPlaybackControl.pause(alexaServicesConnection);
        }

        @JvmStatic
        public static final void play(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.AudioPlaybackControl.play(alexaServicesConnection);
        }

        @JvmStatic
        public static final void previous(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.AudioPlaybackControl.previous(alexaServicesConnection);
        }

        @JvmStatic
        public static final void register(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaAudioPlaybackStatusListener, "alexaAudioPlaybackStatusListener");
            AlexaAudioPlaybackStatusListenerAdapter alexaAudioPlaybackStatusListenerAdapter = new AlexaAudioPlaybackStatusListenerAdapter(alexaAudioPlaybackStatusListener);
            AudioPlaybackControlSubcomponent.Companion.getPlaybackStatusListeners().put(alexaAudioPlaybackStatusListener, alexaAudioPlaybackStatusListenerAdapter);
            AlexaServices.AudioPlaybackControl.register(alexaServicesConnection, alexaAudioPlaybackStatusListenerAdapter);
        }

        @JvmStatic
        public static final void registerListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaAudioPlaybackListener alexaAudioPlaybackListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaAudioPlaybackListener, "alexaAudioPlaybackListener");
            AlexaServices.AudioPlaybackControl.registerListener(alexaServicesConnection, alexaAudioPlaybackListener);
        }

        @JvmStatic
        public static final void stop(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.AudioPlaybackControl.stop(alexaServicesConnection);
        }
    }

    /* compiled from: AlexaServices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nH\u0007J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0007J\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nH\u0007J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0007¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices$Cards;", "", "()V", "deregisterAlexaCardRendererListener", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaCardListener", "Lcom/amazon/alexa/api/AlexaCardListener;", "deregisterCardRendererListener", "Lcom/amazon/alexa/api/AlexaCardRendererListener;", "deregisterPlayerInfoCardListener", "alexaPlayerInfoCardListener", "Lcom/amazon/alexa/api/AlexaPlayerInfoCardListener;", "registerAlexaCardRendererListener", "registerPlayerInfoCardListener", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Cards {
        public static final Cards INSTANCE = new Cards();

        private Cards() {
        }

        @JvmStatic
        public static final void deregisterAlexaCardRendererListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaCardListener alexaCardListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaCardListener, "alexaCardListener");
            AlexaServices.Cards.deregisterAlexaCardRendererListener(alexaServicesConnection, alexaCardListener);
        }

        @JvmStatic
        public static final void deregisterCardRendererListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaCardRendererListener alexaCardListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaCardListener, "alexaCardListener");
            AlexaServices.Cards.deregisterCardRendererListener(alexaServicesConnection, alexaCardListener);
        }

        @JvmStatic
        public static final void deregisterPlayerInfoCardListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaPlayerInfoCardListener alexaPlayerInfoCardListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaPlayerInfoCardListener, "alexaPlayerInfoCardListener");
            AlexaServices.Cards.deregisterPlayerInfoCardListener(alexaServicesConnection, alexaPlayerInfoCardListener);
        }

        @JvmStatic
        public static final void registerAlexaCardRendererListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaCardRendererListener alexaCardListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaCardListener, "alexaCardListener");
            AlexaServices.Cards.registerAlexaCardRendererListener(alexaServicesConnection, alexaCardListener);
        }

        @JvmStatic
        public static final void registerPlayerInfoCardListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaPlayerInfoCardListener alexaPlayerInfoCardListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaPlayerInfoCardListener, "alexaPlayerInfoCardListener");
            AlexaServices.Cards.registerPlayerInfoCardListener(alexaServicesConnection, alexaPlayerInfoCardListener);
        }

        @JvmStatic
        public static final void registerAlexaCardRendererListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaCardListener alexaCardListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaCardListener, "alexaCardListener");
            AlexaServices.Cards.registerAlexaCardRendererListener(alexaServicesConnection, alexaCardListener);
        }
    }

    /* compiled from: AlexaServices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices$Companion;", "", "()V", "disable", "", "context", "Landroid/content/Context;", "enable", "isAlexaEnabled", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final void disable(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            com.amazon.alexa.api.AlexaServices.disable(context);
        }

        @JvmStatic
        public final void enable(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            com.amazon.alexa.api.AlexaServices.enable(context);
        }

        @JvmStatic
        public final boolean isAlexaEnabled(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return com.amazon.alexa.api.AlexaServices.isAlexaEnabled(context);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: AlexaServices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices$ContextProvider;", "", "()V", "deregister", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaContextProvider", "Lcom/amazon/alexa/api/AlexaContextProvider;", ADMRegistrationConstants.METHOD_REGISTER, "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class ContextProvider {
        public static final ContextProvider INSTANCE = new ContextProvider();

        private ContextProvider() {
        }

        @JvmStatic
        public static final void deregister(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaContextProvider alexaContextProvider) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaContextProvider, "alexaContextProvider");
            AlexaServices.ContextProvider.deregister(alexaServicesConnection, alexaContextProvider);
        }

        @JvmStatic
        public static final void register(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaContextProvider alexaContextProvider) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaContextProvider, "alexaContextProvider");
            AlexaServices.ContextProvider.register(alexaServicesConnection, alexaContextProvider);
        }
    }

    /* compiled from: AlexaServices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0007¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices$EventSender;", "", "()V", "send", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaEvent", "Lcom/amazon/alexa/api/AlexaEvent;", "eventRequiresContexts", "", "alexaApiCallbacks", "Lcom/amazon/alexa/api/AlexaApiCallbacks;", "alexaContexts", "", "Lcom/amazon/alexa/api/AlexaContext;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class EventSender {
        public static final EventSender INSTANCE = new EventSender();

        private EventSender() {
        }

        @JvmStatic
        public static final void send(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaEvent alexaEvent, boolean z) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
            AlexaServices.EventSender.send(alexaServicesConnection, alexaEvent, z);
        }

        @JvmStatic
        public static final void send(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaEvent alexaEvent) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
            AlexaServices.EventSender.send(alexaServicesConnection, alexaEvent);
        }

        @JvmStatic
        public static final void send(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaEvent alexaEvent, boolean z, @Nullable AlexaApiCallbacks alexaApiCallbacks) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
            AlexaServices.EventSender.send(alexaServicesConnection, alexaEvent, z, alexaApiCallbacks);
        }

        @Deprecated(message = "")
        @JvmStatic
        public static final void send(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaEvent alexaEvent, @NotNull List<? extends AlexaContext> alexaContexts) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
            Intrinsics.checkParameterIsNotNull(alexaContexts, "alexaContexts");
            AlexaServices.EventSender.send(alexaServicesConnection, alexaEvent, (List<AlexaContext>) alexaContexts);
        }
    }

    /* compiled from: AlexaServices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices$InteractionScheduler;", "", "()V", "schedule", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaAudioInteraction", "Lcom/amazon/alexa/api/AlexaAudioInteraction;", "unschedule", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class InteractionScheduler {
        public static final InteractionScheduler INSTANCE = new InteractionScheduler();

        private InteractionScheduler() {
        }

        @JvmStatic
        public static final void schedule(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaAudioInteraction alexaAudioInteraction) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaAudioInteraction, "alexaAudioInteraction");
            AlexaServices.InteractionScheduler.schedule(alexaServicesConnection, alexaAudioInteraction);
        }

        @JvmStatic
        public static final void unschedule(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaAudioInteraction alexaAudioInteraction) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaAudioInteraction, "alexaAudioInteraction");
            AlexaServices.InteractionScheduler.unschedule(alexaServicesConnection, alexaAudioInteraction);
        }
    }

    /* compiled from: AlexaServices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0007¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices$Metrics;", "", "()V", "deregisterListener", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "userPerceivedLatencyListener", "Lcom/amazon/alexa/api/compat/metrics/UserPerceivedLatencyListener;", "deregisterMetricsListener", "alexaMetricsListener", "Lcom/amazon/alexa/api/AlexaMetricsListener;", "registerListener", "registerMetricsListener", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Metrics {
        public static final Metrics INSTANCE = new Metrics();

        private Metrics() {
        }

        @JvmStatic
        public static final void deregisterListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull UserPerceivedLatencyListener userPerceivedLatencyListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(userPerceivedLatencyListener, "userPerceivedLatencyListener");
            UserPerceivedLatencyListenerAdapter remove = MetricsSubcomponent.Companion.getUserPerceivedLatencyListeners().remove(userPerceivedLatencyListener);
            if (remove != null) {
                AlexaServices.Metrics.deregisterListener(alexaServicesConnection, remove);
            }
        }

        @JvmStatic
        public static final void deregisterMetricsListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaMetricsListener alexaMetricsListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaMetricsListener, "alexaMetricsListener");
            AlexaServices.Metrics.deregisterMetricsListener(alexaServicesConnection, alexaMetricsListener);
        }

        @JvmStatic
        public static final void registerListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull UserPerceivedLatencyListener userPerceivedLatencyListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(userPerceivedLatencyListener, "userPerceivedLatencyListener");
            UserPerceivedLatencyListenerAdapter userPerceivedLatencyListenerAdapter = new UserPerceivedLatencyListenerAdapter(userPerceivedLatencyListener);
            MetricsSubcomponent.Companion.getUserPerceivedLatencyListeners().put(userPerceivedLatencyListener, userPerceivedLatencyListenerAdapter);
            AlexaServices.Metrics.registerListener(alexaServicesConnection, userPerceivedLatencyListenerAdapter);
        }

        @JvmStatic
        public static final void registerMetricsListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaMetricsListener alexaMetricsListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaMetricsListener, "alexaMetricsListener");
            AlexaServices.Metrics.registerMetricsListener(alexaServicesConnection, alexaMetricsListener);
        }
    }

    /* compiled from: AlexaServices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices$Readiness;", "", "()V", "deregister", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaReadinessListener", "Lcom/amazon/alexa/api/compat/AlexaReadinessListener;", "getReadyState", "Lcom/amazon/alexa/api/compat/AlexaReadyState;", ADMRegistrationConstants.METHOD_REGISTER, "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Readiness {
        public static final Readiness INSTANCE = new Readiness();

        private Readiness() {
        }

        @JvmStatic
        public static final void deregister(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaReadinessListener alexaReadinessListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaReadinessListener, "alexaReadinessListener");
            AlexaReadinessListenerAdapter remove = ReadinessSubcomponent.Companion.getListeners().remove(alexaReadinessListener);
            if (remove != null) {
                AlexaServices.Readiness.deregister(alexaServicesConnection, remove);
            }
        }

        @JvmStatic
        @Nullable
        public static final AlexaReadyState getReadyState(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            return AlexaReadyState.Companion.convertToCompat(AlexaServices.Readiness.getReadyState(alexaServicesConnection));
        }

        @JvmStatic
        public static final void register(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaReadinessListener alexaReadinessListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaReadinessListener, "alexaReadinessListener");
            AlexaReadinessListenerAdapter alexaReadinessListenerAdapter = new AlexaReadinessListenerAdapter(alexaReadinessListener);
            ReadinessSubcomponent.Companion.getListeners().put(alexaReadinessListener, alexaReadinessListenerAdapter);
            AlexaServices.Readiness.register(alexaServicesConnection, alexaReadinessListenerAdapter);
        }
    }

    /* compiled from: AlexaServices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J(\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J(\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0018\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u001a\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J$\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0007J$\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010 \u001a\u0004\u0018\u00010!H\u0007J.\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0007J(\u0010\"\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J2\u0010\"\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010#\u001a\u0004\u0018\u00010$H\u0007J(\u0010\"\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J0\u0010\"\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010%\u001a\u00020&H\u0007J<\u0010\"\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010#\u001a\u0004\u0018\u00010$H\u0007J0\u0010\"\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010#\u001a\u00020$H\u0007J\u0010\u0010'\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010(\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0007J\u0018\u0010(\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u000eH\u0007¨\u0006)"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices$Recognizer;", "", "()V", "cancelUserInteraction", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "continueDialog", "alexaDialogController", "Lcom/amazon/alexa/api/AlexaDialogController;", "alexaAudioMetadata", "Lcom/amazon/alexa/api/AlexaAudioMetadata;", "alexaAudioSink", "Lcom/amazon/alexa/api/AlexaAudioSink;", "Lcom/amazon/alexa/api/AlexaDialogControllerV2;", "deregisterListener", "alexaStateListener", "Lcom/amazon/alexa/api/compat/AlexaStateListener;", "deregisterUserSpeechListener", "alexaUserSpeechListener", "Lcom/amazon/alexa/api/AlexaUserSpeechListener;", "muteMicrophone", "isMuted", "", "prepare", "registerListener", "registerUserSpeechListener", "start", AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, "", "alexaUserInterfaceOptions", "Lcom/amazon/alexa/api/AlexaUserInterfaceOptions;", "launchType", "Lcom/amazon/alexa/api/LaunchType;", "startDialog", "dialogExtras", "Lcom/amazon/alexa/api/AlexaDialogExtras;", "alexaDataSink", "Lcom/amazon/alexa/api/AlexaDataSink;", "stop", "stopDialogTurn", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Recognizer {
        public static final Recognizer INSTANCE = new Recognizer();

        private Recognizer() {
        }

        @JvmStatic
        public static final void cancelUserInteraction(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.Recognizer.cancelUserInteraction(alexaServicesConnection);
        }

        @JvmStatic
        public static final void continueDialog(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaDialogController alexaDialogController, @NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaDialogController, "alexaDialogController");
            Intrinsics.checkParameterIsNotNull(alexaAudioMetadata, "alexaAudioMetadata");
            Intrinsics.checkParameterIsNotNull(alexaAudioSink, "alexaAudioSink");
            AlexaServices.Recognizer.continueDialog(alexaServicesConnection, alexaDialogController, alexaAudioMetadata, alexaAudioSink);
        }

        @JvmStatic
        public static final void deregisterListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaStateListener alexaStateListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaStateListener, "alexaStateListener");
            AlexaStateListenerAdapter remove = AttentionSystemSubcomponent.Companion.getListeners().remove(alexaStateListener);
            if (remove != null) {
                AlexaServices.Recognizer.deregisterListener(alexaServicesConnection, remove);
            }
        }

        @JvmStatic
        public static final void deregisterUserSpeechListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaUserSpeechListener alexaUserSpeechListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaUserSpeechListener, "alexaUserSpeechListener");
            AlexaServices.Recognizer.deregisterUserSpeechListener(alexaServicesConnection, alexaUserSpeechListener);
        }

        @JvmStatic
        public static final void muteMicrophone(@NotNull AlexaServicesConnection alexaServicesConnection, boolean z) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.Recognizer.muteMicrophone(alexaServicesConnection, z);
        }

        @JvmStatic
        public static final void prepare(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.Recognizer.prepare(alexaServicesConnection);
        }

        @JvmStatic
        public static final void registerListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaStateListener alexaStateListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaStateListener, "alexaStateListener");
            AlexaStateListenerAdapter alexaStateListenerAdapter = new AlexaStateListenerAdapter(alexaStateListener);
            AttentionSystemSubcomponent.Companion.getListeners().put(alexaStateListener, alexaStateListenerAdapter);
            AlexaServices.Recognizer.registerListener(alexaServicesConnection, alexaStateListenerAdapter);
        }

        @JvmStatic
        public static final void registerUserSpeechListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaUserSpeechListener alexaUserSpeechListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaUserSpeechListener, "alexaUserSpeechListener");
            AlexaServices.Recognizer.registerUserSpeechListener(alexaServicesConnection, alexaUserSpeechListener);
        }

        @Deprecated(message = "")
        @JvmStatic
        public static final void start(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.Recognizer.start(alexaServicesConnection);
        }

        @Deprecated(message = "")
        @JvmStatic
        public static final void startDialog(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaDialogController alexaDialogController, @NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaDialogController, "alexaDialogController");
            Intrinsics.checkParameterIsNotNull(alexaAudioMetadata, "alexaAudioMetadata");
            Intrinsics.checkParameterIsNotNull(alexaAudioSink, "alexaAudioSink");
            AlexaServices.Recognizer.startDialog(alexaServicesConnection, alexaDialogController, alexaAudioMetadata, alexaAudioSink);
        }

        @JvmStatic
        public static final void stop(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.Recognizer.stop(alexaServicesConnection);
        }

        @JvmStatic
        public static final void stopDialogTurn(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaDialogController alexaDialogController) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaDialogController, "alexaDialogController");
            AlexaServices.Recognizer.stopDialogTurn(alexaServicesConnection, alexaDialogController);
        }

        @JvmStatic
        public static final void continueDialog(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaDialogControllerV2 alexaDialogController, @NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaDialogController, "alexaDialogController");
            Intrinsics.checkParameterIsNotNull(alexaAudioMetadata, "alexaAudioMetadata");
            Intrinsics.checkParameterIsNotNull(alexaAudioSink, "alexaAudioSink");
            AlexaServices.Recognizer.continueDialog(alexaServicesConnection, alexaDialogController, alexaAudioMetadata, alexaAudioSink);
        }

        @JvmStatic
        public static final void start(@NotNull AlexaServicesConnection alexaServicesConnection, @Nullable String str) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.Recognizer.start(alexaServicesConnection, str);
        }

        @JvmStatic
        public static final void startDialog(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaDialogController alexaDialogController, @NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink, @Nullable AlexaDialogExtras alexaDialogExtras) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaDialogController, "alexaDialogController");
            Intrinsics.checkParameterIsNotNull(alexaAudioMetadata, "alexaAudioMetadata");
            Intrinsics.checkParameterIsNotNull(alexaAudioSink, "alexaAudioSink");
            AlexaServices.Recognizer.startDialog(alexaServicesConnection, alexaDialogController, alexaAudioMetadata, alexaAudioSink, alexaDialogExtras);
        }

        @JvmStatic
        public static final void stopDialogTurn(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaDialogControllerV2 alexaDialogController) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaDialogController, "alexaDialogController");
            AlexaServices.Recognizer.stopDialogTurn(alexaServicesConnection, alexaDialogController);
        }

        @JvmStatic
        public static final void start(@NotNull AlexaServicesConnection alexaServicesConnection, @Nullable String str, @Nullable LaunchType launchType) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.Recognizer.start(alexaServicesConnection, str, launchType);
        }

        @Deprecated(message = "")
        @JvmStatic
        public static final void startDialog(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaDialogControllerV2 alexaDialogController, @NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaDialogController, "alexaDialogController");
            Intrinsics.checkParameterIsNotNull(alexaAudioMetadata, "alexaAudioMetadata");
            Intrinsics.checkParameterIsNotNull(alexaAudioSink, "alexaAudioSink");
            AlexaServices.Recognizer.startDialog(alexaServicesConnection, alexaDialogController, alexaAudioMetadata, alexaAudioSink);
        }

        @JvmStatic
        public static final void start(@NotNull AlexaServicesConnection alexaServicesConnection, @Nullable String str, @Nullable AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.Recognizer.start(alexaServicesConnection, str, alexaUserInterfaceOptions);
        }

        @JvmStatic
        public static final void startDialog(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaDialogControllerV2 alexaDialogController, @NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink, @NotNull AlexaDataSink alexaDataSink) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaDialogController, "alexaDialogController");
            Intrinsics.checkParameterIsNotNull(alexaAudioMetadata, "alexaAudioMetadata");
            Intrinsics.checkParameterIsNotNull(alexaAudioSink, "alexaAudioSink");
            Intrinsics.checkParameterIsNotNull(alexaDataSink, "alexaDataSink");
            AlexaServices.Recognizer.startDialog(alexaServicesConnection, alexaDialogController, alexaAudioMetadata, alexaAudioSink, alexaDataSink);
        }

        @JvmStatic
        public static final void start(@NotNull AlexaServicesConnection alexaServicesConnection, @Nullable String str, @Nullable LaunchType launchType, @Nullable AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            AlexaServices.Recognizer.start(alexaServicesConnection, str, launchType, alexaUserInterfaceOptions);
        }

        @Deprecated(message = "")
        @JvmStatic
        public static final void startDialog(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaDialogControllerV2 alexaDialogController, @NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink, @NotNull AlexaDialogExtras dialogExtras) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaDialogController, "alexaDialogController");
            Intrinsics.checkParameterIsNotNull(alexaAudioMetadata, "alexaAudioMetadata");
            Intrinsics.checkParameterIsNotNull(alexaAudioSink, "alexaAudioSink");
            Intrinsics.checkParameterIsNotNull(dialogExtras, "dialogExtras");
            AlexaServices.Recognizer.startDialog(alexaServicesConnection, alexaDialogController, alexaAudioMetadata, alexaAudioSink, dialogExtras);
        }

        @JvmStatic
        public static final void startDialog(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaDialogControllerV2 alexaDialogController, @NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink, @Nullable AlexaDataSink alexaDataSink, @Nullable AlexaDialogExtras alexaDialogExtras) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaDialogController, "alexaDialogController");
            Intrinsics.checkParameterIsNotNull(alexaAudioMetadata, "alexaAudioMetadata");
            Intrinsics.checkParameterIsNotNull(alexaAudioSink, "alexaAudioSink");
            AlexaServices.Recognizer.startDialog(alexaServicesConnection, alexaDialogController, alexaAudioMetadata, alexaAudioSink, alexaDataSink, alexaDialogExtras);
        }
    }

    /* compiled from: AlexaServices.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0007J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0007J\u0018\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\fH\u0007¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServices$Settings;", "", "()V", "deregisterListener", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaSettingsListener", "Lcom/amazon/alexa/api/AlexaSettingsListener;", "proxy", "Lcom/amazon/alexa/api/AlexaSettingsListenerProxy;", "getLocale", "Ljava/util/Locale;", "getSupportedLocales", "", "registerListener", "setLocale", "locale", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Settings {
        public static final Settings INSTANCE = new Settings();

        private Settings() {
        }

        @JvmStatic
        public static final void deregisterListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaSettingsListener alexaSettingsListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaSettingsListener, "alexaSettingsListener");
            AlexaServices.Settings.deregisterListener(alexaServicesConnection, alexaSettingsListener);
        }

        @JvmStatic
        @com.amazon.alexa.client.annotations.Nullable
        @Nullable
        public static final Locale getLocale(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            return AlexaServices.Settings.getLocale(alexaServicesConnection);
        }

        @JvmStatic
        @NotNull
        public static final Set<Locale> getSupportedLocales(@NotNull AlexaServicesConnection alexaServicesConnection) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Set<Locale> supportedLocales = AlexaServices.Settings.getSupportedLocales(alexaServicesConnection);
            Intrinsics.checkExpressionValueIsNotNull(supportedLocales, "com.amazon.alexa.api.Ale… alexaServicesConnection)");
            return supportedLocales;
        }

        @JvmStatic
        public static final void registerListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaSettingsListener alexaSettingsListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaSettingsListener, "alexaSettingsListener");
            AlexaServices.Settings.registerListener(alexaServicesConnection, alexaSettingsListener);
        }

        @JvmStatic
        public static final void setLocale(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull Locale locale) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(locale, "locale");
            AlexaServices.Settings.setLocale(alexaServicesConnection, locale);
        }

        @JvmStatic
        public static final void deregisterListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaSettingsListenerProxy proxy) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(proxy, "proxy");
            AlexaServices.Settings.deregisterListener(alexaServicesConnection, proxy);
        }

        @JvmStatic
        public static final void registerListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaSettingsListenerProxy proxy) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(proxy, "proxy");
            AlexaServices.Settings.registerListener(alexaServicesConnection, proxy);
        }
    }

    private AlexaServices() {
        throw new UnsupportedOperationException();
    }

    @JvmStatic
    public static final void disable(@NotNull Context context) {
        Companion.disable(context);
    }

    @JvmStatic
    public static final void enable(@NotNull Context context) {
        Companion.enable(context);
    }

    @JvmStatic
    public static final boolean isAlexaEnabled(@NotNull Context context) {
        return Companion.isAlexaEnabled(context);
    }
}
