package com.amazon.alexa.voice.model;

import android.content.Context;
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
import com.amazon.alexa.voice.wakeword.WakeWordAuthority;
import com.amazon.alexa.voice.wakeword.WakewordPreference;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class ModelModule_ProvideVoiceServiceFactory implements Factory<VoiceService> {
    private final Provider<AlexaCardEventSender> alexaCardEventSenderProvider;
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<AlexaStateTracker> alexaStateTrackerProvider;
    private final Provider<ApplicationLifecycleService> applicationLifecycleServiceProvider;
    private final Provider<AudioPlaybackController> audioPlaybackControllerProvider;
    private final Provider<ComponentEnabler> componentEnablerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DefaultAlexaConnectionManager> defaultAlexaConnectionManagerProvider;
    private final Provider<DefaultNowPlayingCardManager> defaultNowPlayingCardManagerProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<VoiceEventBusRebroadcastSender> eventBusRebroadcastSenderProvider;
    private final Provider<FeatureAvailabilityObserver> featureAvailabilityObserverProvider;
    private final Provider<FtueManagerProvider> ftueManagerProvider;
    private final Provider<FtuePreference> ftuePreferenceProvider;
    private final Provider<HandsFreeSupportChecker> handsFreeSupportCheckerProvider;
    private final Provider<LocaleInteractor> localeInteractorProvider;
    private final Provider<LocalePreference> localePreferenceProvider;
    private final Provider<MainActivityLifecycleObserverRegistrar> mainActivityLifecycleObserverRegistrarProvider;
    private final Provider<MessagingReceiver> messagingReceiverLazyProvider;
    private final Provider<VoxMetricEventProcessingService> metricEventProcessingServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<PreferredNavigationAppRepository> preferredNavigationAppRepositoryProvider;
    private final Provider<TypeToAlexaFeatureEnabler> ttaFeatureEnablerProvider;
    private final Provider<VoiceUiEventBroadcastReceiver> uiEventBroadcastReceiverProvider;
    private final Provider<VoxUiEventProcessingService> uiEventProcessingServiceProvider;
    private final Provider<VoiceEnablement> voiceEnablementProvider;
    private final Provider<VoiceIdentityAdapter> voiceIdentityAdapterProvider;
    private final Provider<VoiceNowPlayingEventBusModule> voiceNowPlayingEventBusModuleProvider;
    private final Provider<VoicePermissionsAuthority> voicePermissionsAuthorityProvider;
    private final Provider<VoiceSettings> voiceSettingsProvider;
    private final Provider<WakeWordAuthority> wakeWordAuthorityProvider;
    private final Provider<WakewordPreference> wakewordPreferenceProvider;

    public ModelModule_ProvideVoiceServiceFactory(Provider<Context> provider, Provider<AlexaServicesConnection> provider2, Provider<VoiceEnablement> provider3, Provider<MetricsService> provider4, Provider<EnvironmentService> provider5, Provider<EventBus> provider6, Provider<AudioPlaybackController> provider7, Provider<VoiceNowPlayingEventBusModule> provider8, Provider<DefaultNowPlayingCardManager> provider9, Provider<MessagingReceiver> provider10, Provider<HandsFreeSupportChecker> provider11, Provider<DefaultAlexaConnectionManager> provider12, Provider<VoiceIdentityAdapter> provider13, Provider<PreferredNavigationAppRepository> provider14, Provider<AlexaStateTracker> provider15, Provider<LocaleInteractor> provider16, Provider<WakeWordAuthority> provider17, Provider<WakewordPreference> provider18, Provider<VoiceSettings> provider19, Provider<ComponentEnabler> provider20, Provider<FeatureAvailabilityObserver> provider21, Provider<MainActivityLifecycleObserverRegistrar> provider22, Provider<VoiceUiEventBroadcastReceiver> provider23, Provider<VoxUiEventProcessingService> provider24, Provider<VoxMetricEventProcessingService> provider25, Provider<FtueManagerProvider> provider26, Provider<FtuePreference> provider27, Provider<VoicePermissionsAuthority> provider28, Provider<ApplicationLifecycleService> provider29, Provider<LocalePreference> provider30, Provider<AlexaCardEventSender> provider31, Provider<TypeToAlexaFeatureEnabler> provider32, Provider<VoiceEventBusRebroadcastSender> provider33) {
        this.contextProvider = provider;
        this.alexaServicesConnectionProvider = provider2;
        this.voiceEnablementProvider = provider3;
        this.metricsServiceProvider = provider4;
        this.environmentServiceProvider = provider5;
        this.eventBusProvider = provider6;
        this.audioPlaybackControllerProvider = provider7;
        this.voiceNowPlayingEventBusModuleProvider = provider8;
        this.defaultNowPlayingCardManagerProvider = provider9;
        this.messagingReceiverLazyProvider = provider10;
        this.handsFreeSupportCheckerProvider = provider11;
        this.defaultAlexaConnectionManagerProvider = provider12;
        this.voiceIdentityAdapterProvider = provider13;
        this.preferredNavigationAppRepositoryProvider = provider14;
        this.alexaStateTrackerProvider = provider15;
        this.localeInteractorProvider = provider16;
        this.wakeWordAuthorityProvider = provider17;
        this.wakewordPreferenceProvider = provider18;
        this.voiceSettingsProvider = provider19;
        this.componentEnablerProvider = provider20;
        this.featureAvailabilityObserverProvider = provider21;
        this.mainActivityLifecycleObserverRegistrarProvider = provider22;
        this.uiEventBroadcastReceiverProvider = provider23;
        this.uiEventProcessingServiceProvider = provider24;
        this.metricEventProcessingServiceProvider = provider25;
        this.ftueManagerProvider = provider26;
        this.ftuePreferenceProvider = provider27;
        this.voicePermissionsAuthorityProvider = provider28;
        this.applicationLifecycleServiceProvider = provider29;
        this.localePreferenceProvider = provider30;
        this.alexaCardEventSenderProvider = provider31;
        this.ttaFeatureEnablerProvider = provider32;
        this.eventBusRebroadcastSenderProvider = provider33;
    }

    public static ModelModule_ProvideVoiceServiceFactory create(Provider<Context> provider, Provider<AlexaServicesConnection> provider2, Provider<VoiceEnablement> provider3, Provider<MetricsService> provider4, Provider<EnvironmentService> provider5, Provider<EventBus> provider6, Provider<AudioPlaybackController> provider7, Provider<VoiceNowPlayingEventBusModule> provider8, Provider<DefaultNowPlayingCardManager> provider9, Provider<MessagingReceiver> provider10, Provider<HandsFreeSupportChecker> provider11, Provider<DefaultAlexaConnectionManager> provider12, Provider<VoiceIdentityAdapter> provider13, Provider<PreferredNavigationAppRepository> provider14, Provider<AlexaStateTracker> provider15, Provider<LocaleInteractor> provider16, Provider<WakeWordAuthority> provider17, Provider<WakewordPreference> provider18, Provider<VoiceSettings> provider19, Provider<ComponentEnabler> provider20, Provider<FeatureAvailabilityObserver> provider21, Provider<MainActivityLifecycleObserverRegistrar> provider22, Provider<VoiceUiEventBroadcastReceiver> provider23, Provider<VoxUiEventProcessingService> provider24, Provider<VoxMetricEventProcessingService> provider25, Provider<FtueManagerProvider> provider26, Provider<FtuePreference> provider27, Provider<VoicePermissionsAuthority> provider28, Provider<ApplicationLifecycleService> provider29, Provider<LocalePreference> provider30, Provider<AlexaCardEventSender> provider31, Provider<TypeToAlexaFeatureEnabler> provider32, Provider<VoiceEventBusRebroadcastSender> provider33) {
        return new ModelModule_ProvideVoiceServiceFactory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33);
    }

    public static VoiceService provideInstance(Provider<Context> provider, Provider<AlexaServicesConnection> provider2, Provider<VoiceEnablement> provider3, Provider<MetricsService> provider4, Provider<EnvironmentService> provider5, Provider<EventBus> provider6, Provider<AudioPlaybackController> provider7, Provider<VoiceNowPlayingEventBusModule> provider8, Provider<DefaultNowPlayingCardManager> provider9, Provider<MessagingReceiver> provider10, Provider<HandsFreeSupportChecker> provider11, Provider<DefaultAlexaConnectionManager> provider12, Provider<VoiceIdentityAdapter> provider13, Provider<PreferredNavigationAppRepository> provider14, Provider<AlexaStateTracker> provider15, Provider<LocaleInteractor> provider16, Provider<WakeWordAuthority> provider17, Provider<WakewordPreference> provider18, Provider<VoiceSettings> provider19, Provider<ComponentEnabler> provider20, Provider<FeatureAvailabilityObserver> provider21, Provider<MainActivityLifecycleObserverRegistrar> provider22, Provider<VoiceUiEventBroadcastReceiver> provider23, Provider<VoxUiEventProcessingService> provider24, Provider<VoxMetricEventProcessingService> provider25, Provider<FtueManagerProvider> provider26, Provider<FtuePreference> provider27, Provider<VoicePermissionsAuthority> provider28, Provider<ApplicationLifecycleService> provider29, Provider<LocalePreference> provider30, Provider<AlexaCardEventSender> provider31, Provider<TypeToAlexaFeatureEnabler> provider32, Provider<VoiceEventBusRebroadcastSender> provider33) {
        return proxyProvideVoiceService(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), DoubleCheck.lazy(provider10), provider11.mo10268get(), provider12.mo10268get(), provider13.mo10268get(), provider14.mo10268get(), provider15.mo10268get(), provider16.mo10268get(), provider17.mo10268get(), provider18.mo10268get(), provider19.mo10268get(), provider20.mo10268get(), provider21.mo10268get(), provider22.mo10268get(), provider23.mo10268get(), provider24.mo10268get(), provider25.mo10268get(), provider26.mo10268get(), provider27.mo10268get(), provider28.mo10268get(), provider29.mo10268get(), provider30.mo10268get(), provider31.mo10268get(), provider32.mo10268get(), provider33.mo10268get());
    }

    public static VoiceService proxyProvideVoiceService(Context context, AlexaServicesConnection alexaServicesConnection, VoiceEnablement voiceEnablement, MetricsService metricsService, EnvironmentService environmentService, EventBus eventBus, AudioPlaybackController audioPlaybackController, VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule, DefaultNowPlayingCardManager defaultNowPlayingCardManager, Lazy<MessagingReceiver> lazy, HandsFreeSupportChecker handsFreeSupportChecker, DefaultAlexaConnectionManager defaultAlexaConnectionManager, VoiceIdentityAdapter voiceIdentityAdapter, PreferredNavigationAppRepository preferredNavigationAppRepository, AlexaStateTracker alexaStateTracker, LocaleInteractor localeInteractor, WakeWordAuthority wakeWordAuthority, WakewordPreference wakewordPreference, VoiceSettings voiceSettings, ComponentEnabler componentEnabler, FeatureAvailabilityObserver featureAvailabilityObserver, MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar, VoiceUiEventBroadcastReceiver voiceUiEventBroadcastReceiver, VoxUiEventProcessingService voxUiEventProcessingService, VoxMetricEventProcessingService voxMetricEventProcessingService, FtueManagerProvider ftueManagerProvider, FtuePreference ftuePreference, VoicePermissionsAuthority voicePermissionsAuthority, ApplicationLifecycleService applicationLifecycleService, LocalePreference localePreference, AlexaCardEventSender alexaCardEventSender, TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler, VoiceEventBusRebroadcastSender voiceEventBusRebroadcastSender) {
        return (VoiceService) Preconditions.checkNotNull(ModelModule.provideVoiceService(context, alexaServicesConnection, voiceEnablement, metricsService, environmentService, eventBus, audioPlaybackController, voiceNowPlayingEventBusModule, defaultNowPlayingCardManager, lazy, handsFreeSupportChecker, defaultAlexaConnectionManager, voiceIdentityAdapter, preferredNavigationAppRepository, alexaStateTracker, localeInteractor, wakeWordAuthority, wakewordPreference, voiceSettings, componentEnabler, featureAvailabilityObserver, mainActivityLifecycleObserverRegistrar, voiceUiEventBroadcastReceiver, voxUiEventProcessingService, voxMetricEventProcessingService, ftueManagerProvider, ftuePreference, voicePermissionsAuthority, applicationLifecycleService, localePreference, alexaCardEventSender, typeToAlexaFeatureEnabler, voiceEventBusRebroadcastSender), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceService mo10268get() {
        return provideInstance(this.contextProvider, this.alexaServicesConnectionProvider, this.voiceEnablementProvider, this.metricsServiceProvider, this.environmentServiceProvider, this.eventBusProvider, this.audioPlaybackControllerProvider, this.voiceNowPlayingEventBusModuleProvider, this.defaultNowPlayingCardManagerProvider, this.messagingReceiverLazyProvider, this.handsFreeSupportCheckerProvider, this.defaultAlexaConnectionManagerProvider, this.voiceIdentityAdapterProvider, this.preferredNavigationAppRepositoryProvider, this.alexaStateTrackerProvider, this.localeInteractorProvider, this.wakeWordAuthorityProvider, this.wakewordPreferenceProvider, this.voiceSettingsProvider, this.componentEnablerProvider, this.featureAvailabilityObserverProvider, this.mainActivityLifecycleObserverRegistrarProvider, this.uiEventBroadcastReceiverProvider, this.uiEventProcessingServiceProvider, this.metricEventProcessingServiceProvider, this.ftueManagerProvider, this.ftuePreferenceProvider, this.voicePermissionsAuthorityProvider, this.applicationLifecycleServiceProvider, this.localePreferenceProvider, this.alexaCardEventSenderProvider, this.ttaFeatureEnablerProvider, this.eventBusRebroadcastSenderProvider);
    }
}
