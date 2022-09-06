package com.amazon.alexa.voice.model;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.voice.elements.AlexaCardEventSender;
import com.amazon.alexa.voice.enablement.ComponentEnabler;
import com.amazon.alexa.voice.enablement.VoiceEnablement;
import com.amazon.alexa.voice.enablement.VoiceIdentityAdapter;
import com.amazon.alexa.voice.events.VoiceUiEventBroadcastReceiver;
import com.amazon.alexa.voice.events.VoxUiEventProcessingService;
import com.amazon.alexa.voice.features.FeatureAvailabilityObserver;
import com.amazon.alexa.voice.ftue.FtueManagerProvider;
import com.amazon.alexa.voice.ftue.FtuePreference;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.locale.LocalePreference;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.navigation.PreferredNavigationAppRepository;
import com.amazon.alexa.voice.nowplaying.AudioPlaybackController;
import com.amazon.alexa.voice.nowplaying.DefaultNowPlayingCardManager;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceNowPlayingEventBusModule;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.sdk.AlexaStateTracker;
import com.amazon.alexa.voice.sdk.DefaultAlexaConnectionManager;
import com.amazon.alexa.voice.settings.VoiceSettings;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import com.amazon.alexa.voice.ui.VoiceEventBusRebroadcastSender;
import com.amazon.alexa.voice.utils.LogLevelHelper;
import com.amazon.alexa.voice.wakeword.AbiCompatibilityInterface;
import com.amazon.alexa.voice.wakeword.WakeWordAuthority;
import com.amazon.alexa.voice.wakeword.WakewordPreference;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class ModelModule {
    private ModelModule() {
    }

    @Provides
    @Singleton
    public static HandsFreeSupportChecker provideHandsFreeSupportChecker(Context context, AbiCompatibilityInterface abiCompatibilityInterface) {
        return new DefaultHandsFreeSupportChecker(context, abiCompatibilityInterface);
    }

    @Provides
    @Singleton
    public static VoiceService provideVoiceService(Context context, AlexaServicesConnection alexaServicesConnection, VoiceEnablement voiceEnablement, MetricsService metricsService, EnvironmentService environmentService, EventBus eventBus, AudioPlaybackController audioPlaybackController, VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule, DefaultNowPlayingCardManager defaultNowPlayingCardManager, Lazy<MessagingReceiver> lazy, HandsFreeSupportChecker handsFreeSupportChecker, DefaultAlexaConnectionManager defaultAlexaConnectionManager, VoiceIdentityAdapter voiceIdentityAdapter, PreferredNavigationAppRepository preferredNavigationAppRepository, AlexaStateTracker alexaStateTracker, LocaleInteractor localeInteractor, WakeWordAuthority wakeWordAuthority, WakewordPreference wakewordPreference, VoiceSettings voiceSettings, ComponentEnabler componentEnabler, FeatureAvailabilityObserver featureAvailabilityObserver, MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar, VoiceUiEventBroadcastReceiver voiceUiEventBroadcastReceiver, VoxUiEventProcessingService voxUiEventProcessingService, VoxMetricEventProcessingService voxMetricEventProcessingService, FtueManagerProvider ftueManagerProvider, FtuePreference ftuePreference, VoicePermissionsAuthority voicePermissionsAuthority, ApplicationLifecycleService applicationLifecycleService, LocalePreference localePreference, AlexaCardEventSender alexaCardEventSender, TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler, VoiceEventBusRebroadcastSender voiceEventBusRebroadcastSender) {
        LogLevelHelper.setLogLevel(environmentService);
        return voiceEnablement.isVoicePossible() ? new DefaultVoiceService(context, alexaServicesConnection, voiceEnablement, metricsService, eventBus, audioPlaybackController, voiceNowPlayingEventBusModule, defaultNowPlayingCardManager, lazy, handsFreeSupportChecker, defaultAlexaConnectionManager, voiceIdentityAdapter, preferredNavigationAppRepository, alexaStateTracker, wakeWordAuthority, localeInteractor, wakewordPreference, voiceSettings, componentEnabler, featureAvailabilityObserver, mainActivityLifecycleObserverRegistrar, voiceUiEventBroadcastReceiver, voxUiEventProcessingService, voxMetricEventProcessingService, ftueManagerProvider, ftuePreference, voicePermissionsAuthority, applicationLifecycleService, localePreference, alexaCardEventSender, new Handler(Looper.getMainLooper()), typeToAlexaFeatureEnabler, voiceEventBusRebroadcastSender) : new NoOpVoiceService(componentEnabler);
    }
}
