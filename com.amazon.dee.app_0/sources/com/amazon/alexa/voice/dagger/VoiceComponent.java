package com.amazon.alexa.voice.dagger;

import com.amazon.alexa.voice.alerts.AlertsModule;
import com.amazon.alexa.voice.app.ApplicationModule;
import com.amazon.alexa.voice.downchannel.DownchannelModule;
import com.amazon.alexa.voice.elements.AlexaCardModule;
import com.amazon.alexa.voice.enablement.EnablementModule;
import com.amazon.alexa.voice.events.EventsModule;
import com.amazon.alexa.voice.events.VoiceUiEventBroadcastReceiver;
import com.amazon.alexa.voice.features.FeaturesModule;
import com.amazon.alexa.voice.ftue.FtueModule;
import com.amazon.alexa.voice.ftue.VoiceFtueActivity;
import com.amazon.alexa.voice.locale.LocaleModule;
import com.amazon.alexa.voice.metrics.MetricsModule;
import com.amazon.alexa.voice.metrics.VoiceMetricsBroadcastReceiver;
import com.amazon.alexa.voice.model.ModelModule;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.navigation.NavigationModule;
import com.amazon.alexa.voice.nowplaying.NowPlayingModule;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceNowPlayingEventBusModule;
import com.amazon.alexa.voice.provisioning.ProvisioningModule;
import com.amazon.alexa.voice.routing.VoiceRoutingAdapter;
import com.amazon.alexa.voice.sdk.SdkModule;
import com.amazon.alexa.voice.settings.SettingsModule;
import com.amazon.alexa.voice.settings.VoiceSettings;
import com.amazon.alexa.voice.tta.TypeToAlexaModule;
import com.amazon.alexa.voice.ui.UiModule;
import com.amazon.alexa.voice.ui.VoiceActivity;
import com.amazon.alexa.voice.wakeword.WakeWordModule;
import com.amazon.alexa.voice.wakeword.WakewordPermissionRequestActivity;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {ApplicationModule.class, DownchannelModule.class, EnablementModule.class, FeaturesModule.class, FtueModule.class, LocaleModule.class, MetricsModule.class, ModelModule.class, NavigationModule.class, NowPlayingModule.class, ProvisioningModule.class, SdkModule.class, UiModule.class, WakeWordModule.class, AlertsModule.class, SettingsModule.class, EventsModule.class, AlexaCardModule.class, TypeToAlexaModule.class})
@Singleton
/* loaded from: classes11.dex */
public interface VoiceComponent {
    void inject(VoiceUiEventBroadcastReceiver voiceUiEventBroadcastReceiver);

    void inject(VoiceFtueActivity voiceFtueActivity);

    void inject(VoiceMetricsBroadcastReceiver voiceMetricsBroadcastReceiver);

    void inject(VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule);

    void inject(VoiceRoutingAdapter voiceRoutingAdapter);

    void inject(VoiceSettings voiceSettings);

    void inject(VoiceActivity voiceActivity);

    void inject(WakewordPermissionRequestActivity wakewordPermissionRequestActivity);

    VoiceService voiceService();
}
