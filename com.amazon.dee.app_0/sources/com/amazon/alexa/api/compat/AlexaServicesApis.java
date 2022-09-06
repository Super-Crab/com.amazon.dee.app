package com.amazon.alexa.api.compat;

import com.amazon.alexa.accessorykit.accessories.session.system.SystemModelI18nConfigTransformer;
import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaArtifactDownloadListener;
import com.amazon.alexa.api.AlexaAttentionSystemSettingsListener;
import com.amazon.alexa.api.AlexaCaptionListener;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaDriveModeListener;
import com.amazon.alexa.api.AlexaLocalesListener;
import com.amazon.alexa.api.AlexaServicesApis;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.AlexaUserSpeechListener;
import com.amazon.alexa.api.AlexaVisualTask;
import com.amazon.alexa.api.DriveModeState;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.voice.tta.Constants;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.dee.app.metrics.MetricsConstants;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaServicesApis.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\r\u0018\u00002\u00020\u0001:\u000b\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServicesApis;", "", "()V", "AttentionSystem", "AudioPlaybackControl", "Caption", "ContextProviders", "DriveMode", "Locale", Constants.Namespaces.TEXT, "UserSpeechProviders", "UserSpeechRecognizer", "VisualTaskScheduler", "WakeWord", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaServicesApis {

    /* compiled from: AlexaServicesApis.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u001c\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u001c\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010\u0012\u001a\u00020\u00112\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u001c\u0010\u0013\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u001c\u0010\u0015\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J\u001a\u0010\u0016\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u001a\u0010\u0017\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\u0011H\u0007J\u0012\u0010\u0018\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServicesApis$AttentionSystem;", "", "()V", MetricsConstants.Method.CACHE_CLEAR, "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "deregisterAttentionSystemSettingsListener", "alexaAttentionSystemSettingsListener", "Lcom/amazon/alexa/api/AlexaAttentionSystemSettingsListener;", "deregisterListener", "alexaStateListener", "Lcom/amazon/alexa/api/compat/AlexaStateListener;", "deregisterUserSpeechListener", "alexaUserSpeechListener", "Lcom/amazon/alexa/api/AlexaUserSpeechListener;", "isEndpointSoundEnabled", "", "isWakeSoundEnabled", "registerAttentionSystemSettingsListener", "registerListener", "registerUserSpeechListener", "setEndpointSoundEnabled", "setWakeSoundEnabled", "stopForegroundAudioTask", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class AttentionSystem {
        public static final AttentionSystem INSTANCE = new AttentionSystem();

        private AttentionSystem() {
        }

        @JvmStatic
        public static final void clear(@Nullable AlexaServicesConnection alexaServicesConnection) {
            AlexaServicesApis.Dialog.clear(alexaServicesConnection);
        }

        @JvmStatic
        public static final void deregisterAttentionSystemSettingsListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
            AlexaServicesApis.AttentionSystem.deregisterAttentionSystemSettingsListener(alexaServicesConnection, alexaAttentionSystemSettingsListener);
        }

        @JvmStatic
        public static final void deregisterListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaStateListener alexaStateListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaStateListener, "alexaStateListener");
            AlexaServices.Recognizer.deregisterListener(alexaServicesConnection, alexaStateListener);
        }

        @JvmStatic
        public static final void deregisterUserSpeechListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaUserSpeechListener alexaUserSpeechListener) {
            AlexaServicesApis.AttentionSystem.deregisterUserSpeechListener(alexaServicesConnection, alexaUserSpeechListener);
        }

        @Deprecated(message = "No longer supported. Register a AlexaAttentionSystemSettingsListener instead.")
        @JvmStatic
        public static final boolean isEndpointSoundEnabled(@Nullable AlexaServicesConnection alexaServicesConnection) {
            return AlexaServicesApis.AttentionSystem.isEndpointSoundEnabled(alexaServicesConnection);
        }

        @Deprecated(message = "No longer supported. Register a AlexaAttentionSystemSettingsListener instead.")
        @JvmStatic
        public static final boolean isWakeSoundEnabled(@Nullable AlexaServicesConnection alexaServicesConnection) {
            return AlexaServicesApis.AttentionSystem.isWakeSoundEnabled(alexaServicesConnection);
        }

        @JvmStatic
        public static final void registerAttentionSystemSettingsListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
            AlexaServicesApis.AttentionSystem.registerAttentionSystemSettingsListener(alexaServicesConnection, alexaAttentionSystemSettingsListener);
        }

        @JvmStatic
        public static final void registerListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaStateListener alexaStateListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaStateListener, "alexaStateListener");
            AlexaServices.Recognizer.registerListener(alexaServicesConnection, alexaStateListener);
        }

        @JvmStatic
        public static final void registerUserSpeechListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaUserSpeechListener alexaUserSpeechListener) {
            AlexaServicesApis.AttentionSystem.registerUserSpeechListener(alexaServicesConnection, alexaUserSpeechListener);
        }

        @JvmStatic
        public static final void setEndpointSoundEnabled(@Nullable AlexaServicesConnection alexaServicesConnection, boolean z) {
            AlexaServicesApis.AttentionSystem.setEndpointSoundEnabled(alexaServicesConnection, z);
        }

        @JvmStatic
        public static final void setWakeSoundEnabled(@Nullable AlexaServicesConnection alexaServicesConnection, boolean z) {
            AlexaServicesApis.AttentionSystem.setWakeSoundEnabled(alexaServicesConnection, z);
        }

        @JvmStatic
        public static final void stopForegroundAudioTask(@Nullable AlexaServicesConnection alexaServicesConnection) {
            AlexaServicesApis.AttentionSystem.stopForegroundAudioTask(alexaServicesConnection);
        }
    }

    /* compiled from: AlexaServicesApis.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServicesApis$AudioPlaybackControl;", "", "()V", "deregister", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaAudioPlaybackStatusListener", "Lcom/amazon/alexa/api/compat/AlexaAudioPlaybackStatusListener;", ADMRegistrationConstants.METHOD_REGISTER, "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class AudioPlaybackControl {
        public static final AudioPlaybackControl INSTANCE = new AudioPlaybackControl();

        private AudioPlaybackControl() {
        }

        @JvmStatic
        public static final void deregister(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaAudioPlaybackStatusListener, "alexaAudioPlaybackStatusListener");
        }

        @JvmStatic
        public static final void register(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaAudioPlaybackStatusListener, "alexaAudioPlaybackStatusListener");
        }
    }

    /* compiled from: AlexaServicesApis.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u001c\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServicesApis$Caption;", "", "()V", "deregisterCaptionListener", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaCaptionListener", "Lcom/amazon/alexa/api/AlexaCaptionListener;", "registerCaptionListener", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Caption {
        public static final Caption INSTANCE = new Caption();

        private Caption() {
        }

        @JvmStatic
        public static final void deregisterCaptionListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaCaptionListener alexaCaptionListener) {
            AlexaServicesApis.Caption.deregisterCaptionListener(alexaServicesConnection, alexaCaptionListener);
        }

        @JvmStatic
        public static final void registerCaptionListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaCaptionListener alexaCaptionListener) {
            AlexaServicesApis.Caption.registerCaptionListener(alexaServicesConnection, alexaCaptionListener);
        }
    }

    /* compiled from: AlexaServicesApis.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\bH\u0007J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J$\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\bH\u0007J\u001c\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J\u001c\u0010\u0010\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J\u001a\u0010\u0011\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J,\u0010\u0011\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServicesApis$ContextProviders;", "", "()V", "cacheContexts", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaContexts", "", "Lcom/amazon/alexa/api/AlexaContext;", "clearContextCache", "namespaces", "", "deregister", "alexaContextsProvider", "Lcom/amazon/alexa/api/AlexaContextsProvider;", ADMRegistrationConstants.METHOD_REGISTER, "setContextCachingEnabled", "enableContextCaching", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class ContextProviders {
        public static final ContextProviders INSTANCE = new ContextProviders();

        private ContextProviders() {
        }

        @JvmStatic
        public static final void cacheContexts(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable Set<? extends AlexaContext> set) {
            AlexaServicesApis.Capabilities.cacheContexts(alexaServicesConnection, set);
        }

        @JvmStatic
        public static final void clearContextCache(@Nullable AlexaServicesConnection alexaServicesConnection) {
            AlexaServicesApis.Capabilities.clearContextCache(alexaServicesConnection);
        }

        @JvmStatic
        public static final void deregister(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaContextsProvider alexaContextsProvider) {
            AlexaServicesApis.Capabilities.deregisterContextsProvider(alexaServicesConnection, alexaContextsProvider);
        }

        @JvmStatic
        public static final void register(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaContextsProvider alexaContextsProvider) {
            AlexaServicesApis.Capabilities.registerContextsProvider(alexaServicesConnection, alexaContextsProvider);
        }

        @JvmStatic
        public static final void setContextCachingEnabled(@Nullable AlexaServicesConnection alexaServicesConnection, boolean z) {
            AlexaServicesApis.Capabilities.setContextCachingEnabled(alexaServicesConnection, z);
        }

        @JvmStatic
        public static final void clearContextCache(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable Set<String> set) {
            AlexaServicesApis.Capabilities.clearContextCache(alexaServicesConnection, set);
        }

        @JvmStatic
        public static final void setContextCachingEnabled(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable Set<String> set, boolean z) {
            AlexaServicesApis.Capabilities.setContextCachingEnabled(alexaServicesConnection, set, z);
        }
    }

    /* compiled from: AlexaServicesApis.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u001c\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u001a\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u001a\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u001a\u0010\u0010\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\fH\u0007¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServicesApis$DriveMode;", "", "()V", "deregisterDriveModeListener", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaDriveModeListener", "Lcom/amazon/alexa/api/AlexaDriveModeListener;", "registerDriveModeListener", "setDriveModeEnabled", "isEnabled", "", "setDriveModeState", "driveModeState", "Lcom/amazon/alexa/api/DriveModeState;", "setDriveModeTheme", "isDarkTheme", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DriveMode {
        public static final DriveMode INSTANCE = new DriveMode();

        private DriveMode() {
        }

        @JvmStatic
        public static final void deregisterDriveModeListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaDriveModeListener alexaDriveModeListener) {
            AlexaServicesApis.DriveMode.deregisterDriveModeListener(alexaServicesConnection, alexaDriveModeListener);
        }

        @JvmStatic
        public static final void registerDriveModeListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaDriveModeListener alexaDriveModeListener) {
            AlexaServicesApis.DriveMode.registerDriveModeListener(alexaServicesConnection, alexaDriveModeListener);
        }

        @JvmStatic
        public static final void setDriveModeEnabled(@Nullable AlexaServicesConnection alexaServicesConnection, boolean z) {
            AlexaServicesApis.DriveMode.setDriveModeEnabled(alexaServicesConnection, z);
        }

        @JvmStatic
        public static final void setDriveModeState(@Nullable AlexaServicesConnection alexaServicesConnection, @NotNull DriveModeState driveModeState) {
            Intrinsics.checkParameterIsNotNull(driveModeState, "driveModeState");
            AlexaServicesApis.DriveMode.setDriveModeState(alexaServicesConnection, driveModeState);
        }

        @JvmStatic
        public static final void setDriveModeTheme(@Nullable AlexaServicesConnection alexaServicesConnection, boolean z) {
            AlexaServicesApis.DriveMode.setDriveModeTheme(alexaServicesConnection, z);
        }
    }

    /* compiled from: AlexaServicesApis.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001c\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\u001c\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007J\u001c\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007J\u001c\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0011H\u0007J\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001c\u0010\u0013\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\u001c\u0010\u0014\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007J\u001a\u0010\u0014\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u001a\u0010\u0014\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0011H\u0007J\u001c\u0010\u0015\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J&\u0010\u0015\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007J\"\u0010\u001a\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001cH\u0007J,\u0010\u001a\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001c2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServicesApis$Locale;", "", "()V", "deregisterArtifactDownloadListener", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "artifactDownloadListener", "Lcom/amazon/alexa/api/AlexaArtifactDownloadListener;", "deregisterLocalesListener", "alexaLocalesListener", "Lcom/amazon/alexa/api/AlexaLocalesListener;", "deregisterSupportedLocalesListener", "alexaSupportedLocalesListener", "Lcom/amazon/alexa/api/AlexaSupportedLocalesListener;", "compatAlexaSupportedLocalesListener", "Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListener;", "Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListenerv2;", "registerArtifactDownloadListener", "registerLocalesListener", "registerSupportedLocalesListener", "setLocale", "locale", "Ljava/util/Locale;", "alexaApiCallbacks", "Lcom/amazon/alexa/api/AlexaApiCallbacks;", "setLocales", SystemModelI18nConfigTransformer.KEY_LOCALES, "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Locale {
        public static final Locale INSTANCE = new Locale();

        private Locale() {
        }

        @JvmStatic
        public static final void deregisterArtifactDownloadListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaArtifactDownloadListener artifactDownloadListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(artifactDownloadListener, "artifactDownloadListener");
            AlexaServicesApis.Locale.deregisterListener(alexaServicesConnection, artifactDownloadListener);
        }

        @JvmStatic
        public static final void deregisterLocalesListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaLocalesListener alexaLocalesListener) {
            AlexaServicesApis.Locale.deregisterListener(alexaServicesConnection, alexaLocalesListener);
        }

        @Deprecated(message = "use compat listener")
        @JvmStatic
        public static final void deregisterSupportedLocalesListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable com.amazon.alexa.api.AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
            AlexaServicesApis.Locale.deregisterListener(alexaServicesConnection, alexaSupportedLocalesListener);
        }

        @JvmStatic
        public static final void registerArtifactDownloadListener(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaArtifactDownloadListener artifactDownloadListener) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(artifactDownloadListener, "artifactDownloadListener");
            AlexaServicesApis.Locale.registerListener(alexaServicesConnection, artifactDownloadListener);
        }

        @JvmStatic
        public static final void registerLocalesListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaLocalesListener alexaLocalesListener) {
            AlexaServicesApis.Locale.registerListener(alexaServicesConnection, alexaLocalesListener);
        }

        @Deprecated(message = "use compat listener")
        @JvmStatic
        public static final void registerSupportedLocalesListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable com.amazon.alexa.api.AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
            AlexaServicesApis.Locale.registerListener(alexaServicesConnection, alexaSupportedLocalesListener);
        }

        @Deprecated(message = "")
        @JvmStatic
        public static final void setLocale(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable java.util.Locale locale) {
            List listOf;
            listOf = CollectionsKt__CollectionsJVMKt.listOf(locale);
            AlexaServicesApis.Locale.setLocales(alexaServicesConnection, listOf);
        }

        @JvmStatic
        public static final void setLocales(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable List<java.util.Locale> list) {
            AlexaServicesApis.Locale.setLocales(alexaServicesConnection, list);
        }

        @JvmStatic
        public static final void deregisterSupportedLocalesListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
            Map<AlexaSupportedLocalesListener, AlexaSupportedLocalesListenerAdapter> compatListeners = LocaleSubcomponent.Companion.getCompatListeners();
            if (compatListeners != null) {
                AlexaSupportedLocalesListenerAdapter alexaSupportedLocalesListenerAdapter = (AlexaSupportedLocalesListenerAdapter) TypeIntrinsics.asMutableMap(compatListeners).remove(alexaSupportedLocalesListener);
                if (alexaSupportedLocalesListenerAdapter == null) {
                    return;
                }
                AlexaServicesApis.Locale.deregisterListener(alexaServicesConnection, alexaSupportedLocalesListenerAdapter);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
        }

        @JvmStatic
        public static final void registerSupportedLocalesListener(@Nullable AlexaServicesConnection alexaServicesConnection, @NotNull AlexaSupportedLocalesListener compatAlexaSupportedLocalesListener) {
            Intrinsics.checkParameterIsNotNull(compatAlexaSupportedLocalesListener, "compatAlexaSupportedLocalesListener");
            AlexaSupportedLocalesListenerAdapter alexaSupportedLocalesListenerAdapter = new AlexaSupportedLocalesListenerAdapter(compatAlexaSupportedLocalesListener);
            LocaleSubcomponent.Companion.getCompatListeners().put(compatAlexaSupportedLocalesListener, alexaSupportedLocalesListenerAdapter);
            AlexaServicesApis.Locale.registerListener(alexaServicesConnection, alexaSupportedLocalesListenerAdapter);
        }

        @Deprecated(message = "")
        @JvmStatic
        public static final void setLocale(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable java.util.Locale locale, @Nullable AlexaApiCallbacks alexaApiCallbacks) {
            List listOf;
            listOf = CollectionsKt__CollectionsJVMKt.listOf(locale);
            AlexaServicesApis.Locale.setLocales(alexaServicesConnection, listOf, alexaApiCallbacks);
        }

        @JvmStatic
        public static final void setLocales(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable List<java.util.Locale> list, @Nullable AlexaApiCallbacks alexaApiCallbacks) {
            AlexaServicesApis.Locale.setLocales(alexaServicesConnection, list, alexaApiCallbacks);
        }

        @JvmStatic
        public static final void deregisterSupportedLocalesListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaSupportedLocalesListenerv2 alexaSupportedLocalesListenerv2) {
            Map<AlexaSupportedLocalesListenerv2, AlexaSupportedLocalesListenerv2Adapter> compatv2Listeners = LocaleSubcomponent.Companion.getCompatv2Listeners();
            if (compatv2Listeners != null) {
                AlexaSupportedLocalesListenerv2Adapter alexaSupportedLocalesListenerv2Adapter = (AlexaSupportedLocalesListenerv2Adapter) TypeIntrinsics.asMutableMap(compatv2Listeners).remove(alexaSupportedLocalesListenerv2);
                if (alexaSupportedLocalesListenerv2Adapter == null) {
                    return;
                }
                AlexaServicesApis.Locale.deregisterListener(alexaServicesConnection, alexaSupportedLocalesListenerv2Adapter);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
        }

        @JvmStatic
        public static final void registerSupportedLocalesListener(@Nullable AlexaServicesConnection alexaServicesConnection, @NotNull AlexaSupportedLocalesListenerv2 compatAlexaSupportedLocalesListener) {
            Intrinsics.checkParameterIsNotNull(compatAlexaSupportedLocalesListener, "compatAlexaSupportedLocalesListener");
            AlexaSupportedLocalesListenerv2Adapter alexaSupportedLocalesListenerv2Adapter = new AlexaSupportedLocalesListenerv2Adapter(compatAlexaSupportedLocalesListener);
            LocaleSubcomponent.Companion.getCompatv2Listeners().put(compatAlexaSupportedLocalesListener, alexaSupportedLocalesListenerv2Adapter);
            AlexaServicesApis.Locale.registerListener(alexaServicesConnection, alexaSupportedLocalesListenerv2Adapter);
        }
    }

    /* compiled from: AlexaServicesApis.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u001c\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u001c\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServicesApis$Text;", "", "()V", "deregisterTextResponseListener", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaTextResponseListener", "Lcom/amazon/alexa/api/AlexaTextResponseListener;", "registerTextResponseListener", "sendMessage", "message", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Text {
        public static final Text INSTANCE = new Text();

        private Text() {
        }

        @JvmStatic
        public static final void deregisterTextResponseListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable com.amazon.alexa.api.AlexaTextResponseListener alexaTextResponseListener) {
            AlexaServicesApis.Text.deregisterListener(alexaServicesConnection, alexaTextResponseListener);
        }

        @JvmStatic
        public static final void registerTextResponseListener(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable com.amazon.alexa.api.AlexaTextResponseListener alexaTextResponseListener) {
            AlexaServicesApis.Text.registerListener(alexaServicesConnection, alexaTextResponseListener);
        }

        @JvmStatic
        public static final void sendMessage(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable String str) {
            AlexaServicesApis.Text.sendMessage(alexaServicesConnection, str);
        }
    }

    /* compiled from: AlexaServicesApis.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServicesApis$UserSpeechRecognizer;", "", "()V", "requestDialog", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaUserSpeechProvider", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProvider;", "alexaDialogRequest", "Lcom/amazon/alexa/api/AlexaDialogRequest;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class UserSpeechRecognizer {
        public static final UserSpeechRecognizer INSTANCE = new UserSpeechRecognizer();

        private UserSpeechRecognizer() {
        }

        @JvmStatic
        public static final void requestDialog(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NotNull AlexaDialogRequest alexaDialogRequest) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaUserSpeechProvider, "alexaUserSpeechProvider");
            Intrinsics.checkParameterIsNotNull(alexaDialogRequest, "alexaDialogRequest");
            if (UserSpeechProvidersSubcomponent.Companion.getCompatListeners().containsKey(alexaUserSpeechProvider)) {
                AlexaUserSpeechProviderAdapter alexaUserSpeechProviderAdapter = UserSpeechProvidersSubcomponent.Companion.getCompatListeners().get(alexaUserSpeechProvider);
                if (alexaUserSpeechProviderAdapter == null) {
                    return;
                }
                AlexaServicesApis.UserSpeechRecognizer.requestDialog(alexaServicesConnection, alexaUserSpeechProviderAdapter, alexaDialogRequest);
                return;
            }
            alexaUserSpeechProvider.onDialogRequestDenied();
        }
    }

    /* compiled from: AlexaServicesApis.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u001c\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServicesApis$VisualTaskScheduler;", "", "()V", "schedule", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaVisualTask", "Lcom/amazon/alexa/api/AlexaVisualTask;", "unschedule", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class VisualTaskScheduler {
        public static final VisualTaskScheduler INSTANCE = new VisualTaskScheduler();

        private VisualTaskScheduler() {
        }

        @JvmStatic
        public static final void schedule(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaVisualTask alexaVisualTask) {
            AlexaServicesApis.VisualTaskScheduler.schedule(alexaServicesConnection, alexaVisualTask);
        }

        @JvmStatic
        public static final void unschedule(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaVisualTask alexaVisualTask) {
            AlexaServicesApis.VisualTaskScheduler.unschedule(alexaServicesConnection, alexaVisualTask);
        }
    }

    /* compiled from: AlexaServicesApis.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u001a\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\bH\u0007¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServicesApis$WakeWord;", "", "()V", "deregister", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "isDetectingWakeWord", "", ADMRegistrationConstants.METHOD_REGISTER, "setWakeWordAllowed", "isAllowed", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class WakeWord {
        public static final WakeWord INSTANCE = new WakeWord();

        private WakeWord() {
        }

        @JvmStatic
        public static final void deregister(@Nullable AlexaServicesConnection alexaServicesConnection) {
            AlexaServicesApis.WakeWord.stopListening(alexaServicesConnection);
        }

        @Deprecated(message = "")
        @JvmStatic
        public static final boolean isDetectingWakeWord(@Nullable AlexaServicesConnection alexaServicesConnection) {
            return false;
        }

        @JvmStatic
        public static final void register(@Nullable AlexaServicesConnection alexaServicesConnection) {
            AlexaServicesApis.WakeWord.startListening(alexaServicesConnection, AlexaDialogExtras.builder().build());
        }

        @JvmStatic
        public static final void setWakeWordAllowed(@Nullable AlexaServicesConnection alexaServicesConnection, boolean z) {
        }
    }

    private AlexaServicesApis() {
        throw new UnsupportedOperationException();
    }

    /* compiled from: AlexaServicesApis.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J \u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0007J \u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\fH\u0007¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaServicesApis$UserSpeechProviders;", "", "()V", "deregister", "", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "alexaUserSpeechProvider", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProvider;", ADMRegistrationConstants.METHOD_REGISTER, "alexaUserSpeechProviderMetadata", "Lcom/amazon/alexa/api/AlexaUserSpeechProviderMetadata;", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderMetadata;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class UserSpeechProviders {
        public static final UserSpeechProviders INSTANCE = new UserSpeechProviders();

        private UserSpeechProviders() {
        }

        @JvmStatic
        public static final void deregister(@Nullable AlexaServicesConnection alexaServicesConnection, @Nullable AlexaUserSpeechProvider alexaUserSpeechProvider) {
            Map<AlexaUserSpeechProvider, AlexaUserSpeechProviderAdapter> compatListeners = UserSpeechProvidersSubcomponent.Companion.getCompatListeners();
            if (compatListeners != null) {
                AlexaUserSpeechProviderAdapter alexaUserSpeechProviderAdapter = (AlexaUserSpeechProviderAdapter) TypeIntrinsics.asMutableMap(compatListeners).remove(alexaUserSpeechProvider);
                if (alexaUserSpeechProviderAdapter == null) {
                    return;
                }
                AlexaServicesApis.UserSpeechProviders.deregister(alexaServicesConnection, alexaUserSpeechProviderAdapter);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
        }

        @JvmStatic
        public static final void register(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NotNull com.amazon.alexa.api.AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaUserSpeechProvider, "alexaUserSpeechProvider");
            Intrinsics.checkParameterIsNotNull(alexaUserSpeechProviderMetadata, "alexaUserSpeechProviderMetadata");
            AlexaUserSpeechProviderAdapter alexaUserSpeechProviderAdapter = new AlexaUserSpeechProviderAdapter(alexaUserSpeechProvider);
            UserSpeechProvidersSubcomponent.Companion.getCompatListeners().put(alexaUserSpeechProvider, alexaUserSpeechProviderAdapter);
            AlexaServicesApis.UserSpeechProviders.register(alexaServicesConnection, alexaUserSpeechProviderAdapter, alexaUserSpeechProviderMetadata);
        }

        @JvmStatic
        public static final void register(@NotNull AlexaServicesConnection alexaServicesConnection, @NotNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NotNull AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
            Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
            Intrinsics.checkParameterIsNotNull(alexaUserSpeechProvider, "alexaUserSpeechProvider");
            Intrinsics.checkParameterIsNotNull(alexaUserSpeechProviderMetadata, "alexaUserSpeechProviderMetadata");
            AlexaUserSpeechProviderAdapter alexaUserSpeechProviderAdapter = new AlexaUserSpeechProviderAdapter(alexaUserSpeechProvider);
            UserSpeechProvidersSubcomponent.Companion.getCompatListeners().put(alexaUserSpeechProvider, alexaUserSpeechProviderAdapter);
            AlexaServicesApis.UserSpeechProviders.register(alexaServicesConnection, alexaUserSpeechProviderAdapter, AlexaUserSpeechProviderMetadata.Companion.convertFromCompat$AlexaMobileAndroidVoiceSDKApiCompat_release(alexaUserSpeechProviderMetadata));
        }
    }
}
