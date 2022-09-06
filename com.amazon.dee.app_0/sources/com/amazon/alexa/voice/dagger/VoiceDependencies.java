package com.amazon.alexa.voice.dagger;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.app.ApplicationModule;
import com.amazon.alexa.voice.events.VoiceUiEventBroadcastReceiver;
import com.amazon.alexa.voice.ftue.VoiceFtueActivity;
import com.amazon.alexa.voice.metrics.VoiceMetricsBroadcastReceiver;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceNowPlayingEventBusModule;
import com.amazon.alexa.voice.routing.VoiceRoutingAdapter;
import com.amazon.alexa.voice.settings.VoiceSettings;
import com.amazon.alexa.voice.ui.VoiceActivity;
import com.amazon.alexa.voice.wakeword.WakewordPermissionRequestActivity;
import com.amazon.regulator.Component;
/* loaded from: classes11.dex */
public final class VoiceDependencies {
    @VisibleForTesting
    static VoiceComponent voiceComponent;

    private VoiceDependencies() {
    }

    public static synchronized boolean areInitialized() {
        boolean z;
        synchronized (VoiceDependencies.class) {
            z = voiceComponent != null;
        }
        return z;
    }

    public static synchronized void initialize(Component component) {
        synchronized (VoiceDependencies.class) {
            voiceComponent = DaggerVoiceComponent.builder().applicationModule(new ApplicationModule(component)).build();
        }
    }

    public static synchronized void inject(VoiceActivity voiceActivity) {
        synchronized (VoiceDependencies.class) {
            voiceComponent.inject(voiceActivity);
        }
    }

    public static synchronized VoiceService voiceService() {
        VoiceService voiceService;
        synchronized (VoiceDependencies.class) {
            voiceService = voiceComponent.voiceService();
        }
        return voiceService;
    }

    public static synchronized void inject(VoiceRoutingAdapter voiceRoutingAdapter) {
        synchronized (VoiceDependencies.class) {
            voiceComponent.inject(voiceRoutingAdapter);
        }
    }

    public static synchronized void inject(VoiceFtueActivity voiceFtueActivity) {
        synchronized (VoiceDependencies.class) {
            voiceComponent.inject(voiceFtueActivity);
        }
    }

    public static synchronized void inject(WakewordPermissionRequestActivity wakewordPermissionRequestActivity) {
        synchronized (VoiceDependencies.class) {
            voiceComponent.inject(wakewordPermissionRequestActivity);
        }
    }

    public static synchronized void inject(VoiceMetricsBroadcastReceiver voiceMetricsBroadcastReceiver) {
        synchronized (VoiceDependencies.class) {
            voiceComponent.inject(voiceMetricsBroadcastReceiver);
        }
    }

    public static synchronized void inject(VoiceUiEventBroadcastReceiver voiceUiEventBroadcastReceiver) {
        synchronized (VoiceDependencies.class) {
            voiceComponent.inject(voiceUiEventBroadcastReceiver);
        }
    }

    public static synchronized void inject(VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule) {
        synchronized (VoiceDependencies.class) {
            voiceComponent.inject(voiceNowPlayingEventBusModule);
        }
    }

    public static synchronized void inject(VoiceSettings voiceSettings) {
        synchronized (VoiceDependencies.class) {
            voiceComponent.inject(voiceSettings);
        }
    }
}
