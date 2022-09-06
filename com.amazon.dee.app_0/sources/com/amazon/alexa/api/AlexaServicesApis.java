package com.amazon.alexa.api;

import android.app.PendingIntent;
import java.util.List;
import java.util.Set;
/* loaded from: classes6.dex */
public final class AlexaServicesApis {

    /* loaded from: classes6.dex */
    public static class AttentionSystem {
        private AttentionSystem() {
            throw new UnsupportedOperationException();
        }

        public static void deregisterAttentionSystemListener(AlexaServicesConnection alexaServicesConnection, AlexaAttentionSystemListener alexaAttentionSystemListener) {
            j.b(alexaServicesConnection, alexaAttentionSystemListener);
        }

        public static void deregisterAttentionSystemSettingsListener(AlexaServicesConnection alexaServicesConnection, AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
            j.b(alexaServicesConnection, alexaAttentionSystemSettingsListener);
        }

        public static void deregisterListener(AlexaServicesConnection alexaServicesConnection, AlexaStateListener alexaStateListener) {
            j.b(alexaServicesConnection, alexaStateListener);
        }

        public static void deregisterUserSpeechListener(AlexaServicesConnection alexaServicesConnection, AlexaUserSpeechListener alexaUserSpeechListener) {
            j.b(alexaServicesConnection, alexaUserSpeechListener);
        }

        public static boolean isEndpointSoundEnabled(AlexaServicesConnection alexaServicesConnection) {
            return j.b(alexaServicesConnection);
        }

        public static boolean isWakeSoundEnabled(AlexaServicesConnection alexaServicesConnection) {
            return j.a(alexaServicesConnection);
        }

        public static void registerAttentionSystemListener(AlexaServicesConnection alexaServicesConnection, AlexaAttentionSystemListener alexaAttentionSystemListener) {
            j.a(alexaServicesConnection, alexaAttentionSystemListener);
        }

        public static void registerAttentionSystemSettingsListener(AlexaServicesConnection alexaServicesConnection, AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
            j.a(alexaServicesConnection, alexaAttentionSystemSettingsListener);
        }

        public static void registerListener(AlexaServicesConnection alexaServicesConnection, AlexaStateListener alexaStateListener) {
            j.a(alexaServicesConnection, alexaStateListener);
        }

        public static void registerUserSpeechListener(AlexaServicesConnection alexaServicesConnection, AlexaUserSpeechListener alexaUserSpeechListener) {
            j.a(alexaServicesConnection, alexaUserSpeechListener);
        }

        public static void setEndpointSoundEnabled(AlexaServicesConnection alexaServicesConnection, boolean z) {
            j.b(alexaServicesConnection, z);
        }

        public static void setWakeSoundEnabled(AlexaServicesConnection alexaServicesConnection, boolean z) {
            j.a(alexaServicesConnection, z);
        }

        public static void stopForegroundAudioTask(AlexaServicesConnection alexaServicesConnection) {
            j.c(alexaServicesConnection);
        }
    }

    /* loaded from: classes6.dex */
    public static class Capabilities {
        private Capabilities() {
            throw new UnsupportedOperationException();
        }

        public static void cacheContexts(AlexaServicesConnection alexaServicesConnection, Set<AlexaContext> set) {
            y.a(alexaServicesConnection, set);
        }

        public static void clearContextCache(AlexaServicesConnection alexaServicesConnection) {
            y.a(alexaServicesConnection);
        }

        public static void clearContextCache(AlexaServicesConnection alexaServicesConnection, Set<String> set) {
            y.b(alexaServicesConnection, set);
        }

        public static void deregisterContextsProvider(AlexaServicesConnection alexaServicesConnection, AlexaContextsProvider alexaContextsProvider) {
            y.b(alexaServicesConnection, alexaContextsProvider);
        }

        public static void registerContextsProvider(AlexaServicesConnection alexaServicesConnection, AlexaContextsProvider alexaContextsProvider) {
            y.a(alexaServicesConnection, alexaContextsProvider);
        }

        public static void sendEvent(AlexaServicesConnection alexaServicesConnection, AlexaEvent alexaEvent) {
            y.a(alexaServicesConnection, alexaEvent);
        }

        public static void sendEvent(AlexaServicesConnection alexaServicesConnection, AlexaEvent alexaEvent, boolean z) {
            y.a(alexaServicesConnection, alexaEvent, z);
        }

        public static void sendEvent(AlexaServicesConnection alexaServicesConnection, AlexaEvent alexaEvent, boolean z, AlexaApiCallbacks alexaApiCallbacks) {
            y.a(alexaServicesConnection, alexaEvent, z, alexaApiCallbacks);
        }

        public static void setContextCachingEnabled(AlexaServicesConnection alexaServicesConnection, Set<String> set, boolean z) {
            y.a(alexaServicesConnection, set, z);
        }

        public static void setContextCachingEnabled(AlexaServicesConnection alexaServicesConnection, boolean z) {
            y.a(alexaServicesConnection, z);
        }
    }

    /* loaded from: classes6.dex */
    public static class CapabilityAgentsRegistration {
        private CapabilityAgentsRegistration() {
            throw new UnsupportedOperationException();
        }

        public static void disable(AlexaServicesConnection alexaServicesConnection, AlexaCapabilityAgentRegistration alexaCapabilityAgentRegistration) {
            ad.a(alexaServicesConnection, alexaCapabilityAgentRegistration);
        }
    }

    /* loaded from: classes6.dex */
    public static class Caption {
        private Caption() {
            throw new UnsupportedOperationException();
        }

        public static void deregisterCaptionListener(AlexaServicesConnection alexaServicesConnection, AlexaCaptionListener alexaCaptionListener) {
            af.b(alexaServicesConnection, alexaCaptionListener);
        }

        public static void registerCaptionListener(AlexaServicesConnection alexaServicesConnection, AlexaCaptionListener alexaCaptionListener) {
            af.a(alexaServicesConnection, alexaCaptionListener);
        }
    }

    /* loaded from: classes6.dex */
    public static class Dialog {
        private Dialog() {
            throw new UnsupportedOperationException();
        }

        public static void clear(AlexaServicesConnection alexaServicesConnection) {
            aj.b(alexaServicesConnection);
        }
    }

    /* loaded from: classes6.dex */
    public static class DriveMode {
        private DriveMode() {
            throw new UnsupportedOperationException();
        }

        public static void deregisterDriveModeListener(AlexaServicesConnection alexaServicesConnection, AlexaDriveModeListener alexaDriveModeListener) {
            ap.b(alexaServicesConnection, alexaDriveModeListener);
        }

        public static void registerDriveModeListener(AlexaServicesConnection alexaServicesConnection, AlexaDriveModeListener alexaDriveModeListener) {
            ap.a(alexaServicesConnection, alexaDriveModeListener);
        }

        public static void setDriveModeEnabled(AlexaServicesConnection alexaServicesConnection, boolean z) {
            ap.a(alexaServicesConnection, z);
        }

        public static void setDriveModeState(AlexaServicesConnection alexaServicesConnection, DriveModeState driveModeState) {
            ap.a(alexaServicesConnection, driveModeState);
        }

        public static void setDriveModeTheme(AlexaServicesConnection alexaServicesConnection, boolean z) {
            ap.b(alexaServicesConnection, z);
        }
    }

    /* loaded from: classes6.dex */
    public static class Locale {
        private Locale() {
            throw new UnsupportedOperationException();
        }

        public static void deregisterListener(AlexaServicesConnection alexaServicesConnection, AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
            com.amazon.alexa.api.Locale.deregisterListener(alexaServicesConnection, alexaArtifactDownloadListener);
        }

        public static void deregisterListener(AlexaServicesConnection alexaServicesConnection, AlexaLocalesListener alexaLocalesListener) {
            com.amazon.alexa.api.Locale.deregisterListener(alexaServicesConnection, alexaLocalesListener);
        }

        public static void deregisterListener(AlexaServicesConnection alexaServicesConnection, AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
            com.amazon.alexa.api.Locale.deregisterListener(alexaServicesConnection, alexaSupportedLocalesListener);
        }

        public static void registerListener(AlexaServicesConnection alexaServicesConnection, AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
            com.amazon.alexa.api.Locale.registerListener(alexaServicesConnection, alexaArtifactDownloadListener);
        }

        public static void registerListener(AlexaServicesConnection alexaServicesConnection, AlexaLocalesListener alexaLocalesListener) {
            com.amazon.alexa.api.Locale.registerListener(alexaServicesConnection, alexaLocalesListener);
        }

        public static void registerListener(AlexaServicesConnection alexaServicesConnection, AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
            com.amazon.alexa.api.Locale.registerListener(alexaServicesConnection, alexaSupportedLocalesListener);
        }

        public static void setLocales(AlexaServicesConnection alexaServicesConnection, List<java.util.Locale> list) {
            com.amazon.alexa.api.Locale.setLocales(alexaServicesConnection, list);
        }

        public static void setLocales(AlexaServicesConnection alexaServicesConnection, List<java.util.Locale> list, AlexaApiCallbacks alexaApiCallbacks) {
            com.amazon.alexa.api.Locale.setLocales(alexaServicesConnection, list, alexaApiCallbacks);
        }
    }

    /* loaded from: classes6.dex */
    public static class MediaPlaybackController {
        private MediaPlaybackController() {
            throw new UnsupportedOperationException();
        }

        public static void deregister(AlexaServicesConnection alexaServicesConnection, AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
            com.amazon.alexa.api.MediaPlaybackController.deregister(alexaServicesConnection, alexaAudioPlaybackStatusListener);
        }

        public static void deregisterListener(AlexaServicesConnection alexaServicesConnection, AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
            com.amazon.alexa.api.MediaPlaybackController.deregisterListener(alexaServicesConnection, alexaMediaPlaybackListener);
        }

        public static void next(AlexaServicesConnection alexaServicesConnection) {
            com.amazon.alexa.api.MediaPlaybackController.next(alexaServicesConnection);
        }

        public static void pause(AlexaServicesConnection alexaServicesConnection) {
            com.amazon.alexa.api.MediaPlaybackController.pause(alexaServicesConnection);
        }

        public static void play(AlexaServicesConnection alexaServicesConnection) {
            com.amazon.alexa.api.MediaPlaybackController.play(alexaServicesConnection);
        }

        public static void previous(AlexaServicesConnection alexaServicesConnection) {
            com.amazon.alexa.api.MediaPlaybackController.previous(alexaServicesConnection);
        }

        public static void register(AlexaServicesConnection alexaServicesConnection, AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
            com.amazon.alexa.api.MediaPlaybackController.register(alexaServicesConnection, alexaAudioPlaybackStatusListener);
        }

        public static void registerListener(AlexaServicesConnection alexaServicesConnection, AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
            com.amazon.alexa.api.MediaPlaybackController.registerListener(alexaServicesConnection, alexaMediaPlaybackListener);
        }

        public static void setMediaNotificationContentIntent(AlexaServicesConnection alexaServicesConnection, PendingIntent pendingIntent) {
            com.amazon.alexa.api.MediaPlaybackController.setMediaNotificationContentIntent(alexaServicesConnection, pendingIntent);
        }

        public static void stop(AlexaServicesConnection alexaServicesConnection) {
            com.amazon.alexa.api.MediaPlaybackController.stop(alexaServicesConnection);
        }
    }

    /* loaded from: classes6.dex */
    public static class System {
        private System() {
            throw new UnsupportedOperationException();
        }

        public static void setBaseURLs(AlexaServicesConnection alexaServicesConnection, AlexaBaseURLs alexaBaseURLs) {
            com.amazon.alexa.api.System.setBaseURLs(alexaServicesConnection, alexaBaseURLs);
        }
    }

    /* loaded from: classes6.dex */
    public static class Text {
        private Text() {
            throw new UnsupportedOperationException();
        }

        public static void deregisterExpectTextListener(AlexaServicesConnection alexaServicesConnection, AlexaExpectTextListener alexaExpectTextListener) {
            bv.b(alexaServicesConnection, alexaExpectTextListener);
        }

        public static void deregisterListener(AlexaServicesConnection alexaServicesConnection, AlexaTextResponseListener alexaTextResponseListener) {
            bv.b(alexaServicesConnection, alexaTextResponseListener);
        }

        public static void registerExpectTextListener(AlexaServicesConnection alexaServicesConnection, AlexaExpectTextListener alexaExpectTextListener) {
            bv.a(alexaServicesConnection, alexaExpectTextListener);
        }

        public static void registerListener(AlexaServicesConnection alexaServicesConnection, AlexaTextResponseListener alexaTextResponseListener) {
            bv.a(alexaServicesConnection, alexaTextResponseListener);
        }

        @Deprecated
        public static void sendMessage(AlexaServicesConnection alexaServicesConnection, String str) {
            bv.a(alexaServicesConnection, str);
        }

        public static void sendMessage(AlexaServicesConnection alexaServicesConnection, String str, TextAlexaDialogExtras textAlexaDialogExtras) {
            bv.a(alexaServicesConnection, str, textAlexaDialogExtras);
        }
    }

    /* loaded from: classes6.dex */
    public static class UserSpeechProviders {
        private UserSpeechProviders() {
            throw new UnsupportedOperationException();
        }

        public static void deregister(AlexaServicesConnection alexaServicesConnection, AlexaUserSpeechProvider alexaUserSpeechProvider) {
            bx.a(alexaServicesConnection, alexaUserSpeechProvider);
        }

        public static void register(AlexaServicesConnection alexaServicesConnection, AlexaUserSpeechProvider alexaUserSpeechProvider, AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
            bx.a(alexaServicesConnection, alexaUserSpeechProvider, alexaUserSpeechProviderMetadata);
        }
    }

    /* loaded from: classes6.dex */
    public static class UserSpeechRecognizer {
        private UserSpeechRecognizer() {
            throw new UnsupportedOperationException();
        }

        public static void requestDialog(AlexaServicesConnection alexaServicesConnection, AlexaUserSpeechProvider alexaUserSpeechProvider, AlexaDialogRequest alexaDialogRequest) {
            bz.a(alexaServicesConnection, alexaUserSpeechProvider, alexaDialogRequest);
        }
    }

    /* loaded from: classes6.dex */
    public static class VisualTaskScheduler {
        private VisualTaskScheduler() {
            throw new UnsupportedOperationException();
        }

        public static void schedule(AlexaServicesConnection alexaServicesConnection, AlexaVisualTask alexaVisualTask) {
            cd.a(alexaServicesConnection, alexaVisualTask);
        }

        public static void unschedule(AlexaServicesConnection alexaServicesConnection, AlexaVisualTask alexaVisualTask) {
            cd.b(alexaServicesConnection, alexaVisualTask);
        }
    }

    /* loaded from: classes6.dex */
    public static class WakeWord {
        private WakeWord() {
            throw new UnsupportedOperationException();
        }

        public static void deregisterListener(AlexaServicesConnection alexaServicesConnection, AlexaWakeWordListener alexaWakeWordListener) {
            cf.b(alexaServicesConnection, alexaWakeWordListener);
        }

        @Deprecated
        public static void disable(AlexaServicesConnection alexaServicesConnection) {
            cf.b(alexaServicesConnection);
        }

        @Deprecated
        public static void enable(AlexaServicesConnection alexaServicesConnection, AlexaDialogExtras alexaDialogExtras) {
            cf.b(alexaServicesConnection, alexaDialogExtras);
        }

        public static void registerListener(AlexaServicesConnection alexaServicesConnection, AlexaWakeWordListener alexaWakeWordListener) {
            cf.a(alexaServicesConnection, alexaWakeWordListener);
        }

        public static void setWakeWords(AlexaServicesConnection alexaServicesConnection, List<String> list, AlexaApiCallbacks alexaApiCallbacks) {
            cf.a(alexaServicesConnection, list, alexaApiCallbacks);
        }

        public static void startListening(AlexaServicesConnection alexaServicesConnection, AlexaDialogExtras alexaDialogExtras) {
            cf.a(alexaServicesConnection, alexaDialogExtras);
        }

        public static void stopListening(AlexaServicesConnection alexaServicesConnection) {
            cf.a(alexaServicesConnection);
        }
    }

    private AlexaServicesApis() {
        throw new UnsupportedOperationException();
    }
}
