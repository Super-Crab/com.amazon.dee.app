package com.amazon.alexa.voice.dagger;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.AccountUpgradeService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.alerts.AlertsFeatureEnabler;
import com.amazon.alexa.voice.alerts.AlertsModule;
import com.amazon.alexa.voice.alerts.AlertsModule_ProvideAlertsFeatureEnablerFactory;
import com.amazon.alexa.voice.app.ApplicationModule;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideAccountUpgradeServiceFactory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideApplicationLifecycleServiceFactory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideContextFactory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideDeviceInformationFactory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideEnvironmentServiceFactory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideEventBusFactory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideFeatureServiceV2Factory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideIdentityServiceFactory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideLatencyReportingDelegateFactory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideMainActivityLifecycleObserverRegistrarFactory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideMobilyticsFactory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideNetworkServiceFactory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvidePersistentStorageFactoryFactory;
import com.amazon.alexa.voice.app.ApplicationModule_ProvideRoutingServiceFactory;
import com.amazon.alexa.voice.app.LatencyReportingDelegate;
import com.amazon.alexa.voice.downchannel.DownchannelModule;
import com.amazon.alexa.voice.downchannel.DownchannelModule_ProvideDownchannelControllerFactory;
import com.amazon.alexa.voice.downchannel.DownchannelModule_ProvideVoiceMessagingReceiverFactory;
import com.amazon.alexa.voice.elements.AlexaCardModule;
import com.amazon.alexa.voice.elements.AlexaCardModule_ProvideAlexaCardAPIFactory;
import com.amazon.alexa.voice.elements.AlexaCardModule_ProvideAlexaCardEventSenderFactory;
import com.amazon.alexa.voice.enablement.ComponentEnabler;
import com.amazon.alexa.voice.enablement.EnablementModule;
import com.amazon.alexa.voice.enablement.EnablementModule_ProvideComponentEnablerFactory;
import com.amazon.alexa.voice.enablement.EnablementModule_ProvideVoiceEnablementFactory;
import com.amazon.alexa.voice.enablement.EnablementModule_ProvideVoiceIdentityAdapterFactory;
import com.amazon.alexa.voice.enablement.VoiceEnablement;
import com.amazon.alexa.voice.enablement.VoiceIdentityAdapter;
import com.amazon.alexa.voice.events.EventsModule;
import com.amazon.alexa.voice.events.EventsModule_ProvideVoiceUiEventBroadcastReceiverFactory;
import com.amazon.alexa.voice.events.EventsModule_ProvideVoxUiEventProcessingServiceFactory;
import com.amazon.alexa.voice.events.VoiceUiEventBroadcastReceiver;
import com.amazon.alexa.voice.events.VoiceUiEventBroadcastReceiver_MembersInjector;
import com.amazon.alexa.voice.events.VoxUiEventProcessingService;
import com.amazon.alexa.voice.feature.FeatureAvailability;
import com.amazon.alexa.voice.features.FeatureAvailabilityObserver;
import com.amazon.alexa.voice.features.FeatureChecker;
import com.amazon.alexa.voice.features.FeaturesModule;
import com.amazon.alexa.voice.features.FeaturesModule_ProvideFeatureAvailabilityCheckerFactory;
import com.amazon.alexa.voice.features.FeaturesModule_ProvideFeatureAvailabilityFactory;
import com.amazon.alexa.voice.features.FeaturesModule_ProvideFeatureCheckerFactory;
import com.amazon.alexa.voice.features.FeaturesModule_ProvideVoiceFeatureCheckerFactory;
import com.amazon.alexa.voice.ftue.FtueManagerProvider;
import com.amazon.alexa.voice.ftue.FtueModule;
import com.amazon.alexa.voice.ftue.FtueModule_ProvideFtueManagerProviderFactory;
import com.amazon.alexa.voice.ftue.FtueModule_ProvideFtuePreferenceFactory;
import com.amazon.alexa.voice.ftue.FtuePreference;
import com.amazon.alexa.voice.ftue.VoiceFtueActivity;
import com.amazon.alexa.voice.ftue.VoiceFtueActivity_MembersInjector;
import com.amazon.alexa.voice.locale.DlsFeatureEnabler;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.locale.LocaleModule;
import com.amazon.alexa.voice.locale.LocaleModule_ProvideDlsFeatureEnablerFactory;
import com.amazon.alexa.voice.locale.LocaleModule_ProvideLocaleAPIFactory;
import com.amazon.alexa.voice.locale.LocaleModule_ProvideLocaleInteractorFactory;
import com.amazon.alexa.voice.locale.LocaleModule_ProvideLocaleSettingFactory;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.metrics.MetricsModule;
import com.amazon.alexa.voice.metrics.MetricsModule_ProvideMetricsServiceFactory;
import com.amazon.alexa.voice.metrics.MetricsModule_ProvideVoxMetricEventProcessingServiceFactory;
import com.amazon.alexa.voice.metrics.VoiceMetricsBroadcastReceiver;
import com.amazon.alexa.voice.metrics.VoiceMetricsBroadcastReceiver_MembersInjector;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.model.HandsFreeSupportChecker;
import com.amazon.alexa.voice.model.ModelModule;
import com.amazon.alexa.voice.model.ModelModule_ProvideHandsFreeSupportCheckerFactory;
import com.amazon.alexa.voice.model.ModelModule_ProvideVoiceServiceFactory;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.navigation.NavigationModule;
import com.amazon.alexa.voice.navigation.NavigationModule_ProvidePreferredNavigationAppContentAccessorFactory;
import com.amazon.alexa.voice.navigation.NavigationModule_ProvidePreferredNavigationAppRepositoryFactory;
import com.amazon.alexa.voice.navigation.PreferredNavigationAppRepository;
import com.amazon.alexa.voice.nowplaying.AudioPlaybackController;
import com.amazon.alexa.voice.nowplaying.DefaultNowPlayingCardManager;
import com.amazon.alexa.voice.nowplaying.NowPlayingModule;
import com.amazon.alexa.voice.nowplaying.NowPlayingModule_ProvideAudioPlaybackControllerFactory;
import com.amazon.alexa.voice.nowplaying.NowPlayingModule_ProvideDefaultNowPlayingCardManagerFactory;
import com.amazon.alexa.voice.nowplaying.NowPlayingModule_ProvideVoiceNowPlayingBridgeFactory;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceNowPlayingEventBusModule;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceNowPlayingEventBusModule_MembersInjector;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority_Factory;
import com.amazon.alexa.voice.provisioning.FeatureProvisioner;
import com.amazon.alexa.voice.provisioning.ProvisioningModule;
import com.amazon.alexa.voice.provisioning.ProvisioningModule_ProvideVoiceProvisionerFactory;
import com.amazon.alexa.voice.routing.VoiceRoutingAdapter;
import com.amazon.alexa.voice.routing.VoiceRoutingAdapter_MembersInjector;
import com.amazon.alexa.voice.sdk.AlexaStateTracker;
import com.amazon.alexa.voice.sdk.AttentionSystemManager;
import com.amazon.alexa.voice.sdk.DefaultAlexaConnectionManager;
import com.amazon.alexa.voice.sdk.SdkModule;
import com.amazon.alexa.voice.sdk.SdkModule_ProvideAlexaServicesConnectionFactory;
import com.amazon.alexa.voice.sdk.SdkModule_ProvideAlexaStateTrackerFactory;
import com.amazon.alexa.voice.sdk.SdkModule_ProvideAttentionSystemManagerFactory;
import com.amazon.alexa.voice.sdk.SdkModule_ProvideDefaultAlexaConnectionManagerFactory;
import com.amazon.alexa.voice.settings.SettingsModule;
import com.amazon.alexa.voice.settings.SettingsModule_ProvideEarconSettingsHandlerFactory;
import com.amazon.alexa.voice.settings.SettingsModule_ProvideHandsfreeSettingsHandlerFactory;
import com.amazon.alexa.voice.settings.SettingsModule_ProvideHighPriorityActivityHandlerFactory;
import com.amazon.alexa.voice.settings.SettingsModule_ProvideLocaleCombinationSettingsHandlerFactory;
import com.amazon.alexa.voice.settings.SettingsModule_ProvideLocaleSettingsHandlerFactory;
import com.amazon.alexa.voice.settings.SettingsModule_ProvideTtaAvailabilityHandlerFactory;
import com.amazon.alexa.voice.settings.SettingsModule_ProvideVoiceSettingsFactory;
import com.amazon.alexa.voice.settings.VoiceSettings;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import com.amazon.alexa.voice.tta.TypeToAlexaModule;
import com.amazon.alexa.voice.tta.TypeToAlexaModule_ProvideTypeToAlexaFeatureEnablerFactory;
import com.amazon.alexa.voice.ui.UiModule;
import com.amazon.alexa.voice.ui.UiModule_ProvideAlexaLocaleAuthorityFactory;
import com.amazon.alexa.voice.ui.UiModule_ProvideMetricsBridgeFactory;
import com.amazon.alexa.voice.ui.UiModule_ProvideVoiceEventBusRebroadcastSenderFactory;
import com.amazon.alexa.voice.ui.VoiceActivity;
import com.amazon.alexa.voice.ui.VoiceActivity_MembersInjector;
import com.amazon.alexa.voice.ui.VoiceEventBusRebroadcastSender;
import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import com.amazon.alexa.voice.wakeword.AbiCompatibilityInterface;
import com.amazon.alexa.voice.wakeword.VoiceOverUtility;
import com.amazon.alexa.voice.wakeword.WakeWordAuthority;
import com.amazon.alexa.voice.wakeword.WakeWordEventHandler;
import com.amazon.alexa.voice.wakeword.WakeWordModule;
import com.amazon.alexa.voice.wakeword.WakeWordModule_ProvideCompatibilityInterfaceFactory;
import com.amazon.alexa.voice.wakeword.WakeWordModule_ProvideVoiceOverUtilityFactory;
import com.amazon.alexa.voice.wakeword.WakeWordModule_ProvideWakeWordAuthorityFactory;
import com.amazon.alexa.voice.wakeword.WakeWordModule_ProvideWakeWordFeatureGateFactory;
import com.amazon.alexa.voice.wakeword.WakeWordModule_ProvideWakewordPreferenceFactory;
import com.amazon.alexa.voice.wakeword.WakeWordModule_ProvidesWakeWordEventHandlerFactory;
import com.amazon.alexa.voice.wakeword.WakewordPermissionRequestActivity;
import com.amazon.alexa.voice.wakeword.WakewordPermissionRequestActivity_MembersInjector;
import com.amazon.alexa.voice.wakeword.WakewordPreference;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class DaggerVoiceComponent implements VoiceComponent {
    private Provider<AccountUpgradeService> provideAccountUpgradeServiceProvider;
    private Provider<AlertsFeatureEnabler> provideAlertsFeatureEnablerProvider;
    private AlexaCardModule_ProvideAlexaCardEventSenderFactory provideAlexaCardEventSenderProvider;
    private Provider<AlexaLocaleAuthority> provideAlexaLocaleAuthorityProvider;
    private Provider<AlexaServicesConnection> provideAlexaServicesConnectionProvider;
    private Provider<AlexaStateTracker> provideAlexaStateTrackerProvider;
    private Provider<ApplicationLifecycleService> provideApplicationLifecycleServiceProvider;
    private Provider<AttentionSystemManager> provideAttentionSystemManagerProvider;
    private Provider<AudioPlaybackController> provideAudioPlaybackControllerProvider;
    private Provider<AbiCompatibilityInterface> provideCompatibilityInterfaceProvider;
    private Provider<ComponentEnabler> provideComponentEnablerProvider;
    private Provider<Context> provideContextProvider;
    private Provider<DefaultAlexaConnectionManager> provideDefaultAlexaConnectionManagerProvider;
    private Provider<DefaultNowPlayingCardManager> provideDefaultNowPlayingCardManagerProvider;
    private Provider<DeviceInformation> provideDeviceInformationProvider;
    private Provider<DlsFeatureEnabler> provideDlsFeatureEnablerProvider;
    private Provider provideDownchannelControllerProvider;
    private SettingsModule_ProvideEarconSettingsHandlerFactory provideEarconSettingsHandlerProvider;
    private Provider<EnvironmentService> provideEnvironmentServiceProvider;
    private Provider<EventBus> provideEventBusProvider;
    private Provider<FeatureAvailabilityObserver> provideFeatureAvailabilityCheckerProvider;
    private Provider<FeatureAvailability> provideFeatureAvailabilityProvider;
    private Provider<FeatureChecker> provideFeatureCheckerProvider;
    private Provider<FeatureServiceV2> provideFeatureServiceV2Provider;
    private FtueModule_ProvideFtueManagerProviderFactory provideFtueManagerProvider;
    private FtueModule_ProvideFtuePreferenceFactory provideFtuePreferenceProvider;
    private Provider<HandsFreeSupportChecker> provideHandsFreeSupportCheckerProvider;
    private SettingsModule_ProvideHandsfreeSettingsHandlerFactory provideHandsfreeSettingsHandlerProvider;
    private SettingsModule_ProvideHighPriorityActivityHandlerFactory provideHighPriorityActivityHandlerProvider;
    private Provider<IdentityService> provideIdentityServiceProvider;
    private Provider<LatencyReportingDelegate> provideLatencyReportingDelegateProvider;
    private SettingsModule_ProvideLocaleCombinationSettingsHandlerFactory provideLocaleCombinationSettingsHandlerProvider;
    private Provider<LocaleInteractor> provideLocaleInteractorProvider;
    private LocaleModule_ProvideLocaleSettingFactory provideLocaleSettingProvider;
    private SettingsModule_ProvideLocaleSettingsHandlerFactory provideLocaleSettingsHandlerProvider;
    private Provider<MainActivityLifecycleObserverRegistrar> provideMainActivityLifecycleObserverRegistrarProvider;
    private Provider<MetricsBridge> provideMetricsBridgeProvider;
    private Provider<MetricsService> provideMetricsServiceProvider;
    private Provider<Mobilytics> provideMobilyticsProvider;
    private Provider<NetworkService> provideNetworkServiceProvider;
    private Provider<PersistentStorage.Factory> providePersistentStorageFactoryProvider;
    private Provider providePreferredNavigationAppContentAccessorProvider;
    private Provider<PreferredNavigationAppRepository> providePreferredNavigationAppRepositoryProvider;
    private Provider<RoutingService> provideRoutingServiceProvider;
    private SettingsModule_ProvideTtaAvailabilityHandlerFactory provideTtaAvailabilityHandlerProvider;
    private Provider<TypeToAlexaFeatureEnabler> provideTypeToAlexaFeatureEnablerProvider;
    private Provider<VoiceEnablement> provideVoiceEnablementProvider;
    private Provider<VoiceEventBusRebroadcastSender> provideVoiceEventBusRebroadcastSenderProvider;
    private Provider provideVoiceFeatureCheckerProvider;
    private Provider<VoiceIdentityAdapter> provideVoiceIdentityAdapterProvider;
    private Provider<MessagingReceiver> provideVoiceMessagingReceiverProvider;
    private Provider<VoiceNowPlayingEventBusModule> provideVoiceNowPlayingBridgeProvider;
    private Provider<VoiceOverUtility> provideVoiceOverUtilityProvider;
    private Provider<FeatureProvisioner> provideVoiceProvisionerProvider;
    private Provider<VoiceService> provideVoiceServiceProvider;
    private Provider<VoiceSettings> provideVoiceSettingsProvider;
    private Provider<VoiceUiEventBroadcastReceiver> provideVoiceUiEventBroadcastReceiverProvider;
    private Provider<VoxMetricEventProcessingService> provideVoxMetricEventProcessingServiceProvider;
    private Provider<VoxUiEventProcessingService> provideVoxUiEventProcessingServiceProvider;
    private Provider<WakeWordAuthority> provideWakeWordAuthorityProvider;
    private Provider provideWakeWordFeatureGateProvider;
    private Provider<WakewordPreference> provideWakewordPreferenceProvider;
    private Provider<WakeWordEventHandler> providesWakeWordEventHandlerProvider;
    private Provider<VoicePermissionsAuthority> voicePermissionsAuthorityProvider;

    /* loaded from: classes11.dex */
    public static final class Builder {
        private ApplicationModule applicationModule;

        @Deprecated
        public Builder alertsModule(AlertsModule alertsModule) {
            Preconditions.checkNotNull(alertsModule);
            return this;
        }

        @Deprecated
        public Builder alexaCardModule(AlexaCardModule alexaCardModule) {
            Preconditions.checkNotNull(alexaCardModule);
            return this;
        }

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = (ApplicationModule) Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public VoiceComponent build() {
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            return new DaggerVoiceComponent(this);
        }

        @Deprecated
        public Builder downchannelModule(DownchannelModule downchannelModule) {
            Preconditions.checkNotNull(downchannelModule);
            return this;
        }

        @Deprecated
        public Builder enablementModule(EnablementModule enablementModule) {
            Preconditions.checkNotNull(enablementModule);
            return this;
        }

        @Deprecated
        public Builder eventsModule(EventsModule eventsModule) {
            Preconditions.checkNotNull(eventsModule);
            return this;
        }

        @Deprecated
        public Builder featuresModule(FeaturesModule featuresModule) {
            Preconditions.checkNotNull(featuresModule);
            return this;
        }

        @Deprecated
        public Builder ftueModule(FtueModule ftueModule) {
            Preconditions.checkNotNull(ftueModule);
            return this;
        }

        @Deprecated
        public Builder localeModule(LocaleModule localeModule) {
            Preconditions.checkNotNull(localeModule);
            return this;
        }

        @Deprecated
        public Builder metricsModule(MetricsModule metricsModule) {
            Preconditions.checkNotNull(metricsModule);
            return this;
        }

        @Deprecated
        public Builder modelModule(ModelModule modelModule) {
            Preconditions.checkNotNull(modelModule);
            return this;
        }

        @Deprecated
        public Builder navigationModule(NavigationModule navigationModule) {
            Preconditions.checkNotNull(navigationModule);
            return this;
        }

        @Deprecated
        public Builder nowPlayingModule(NowPlayingModule nowPlayingModule) {
            Preconditions.checkNotNull(nowPlayingModule);
            return this;
        }

        @Deprecated
        public Builder provisioningModule(ProvisioningModule provisioningModule) {
            Preconditions.checkNotNull(provisioningModule);
            return this;
        }

        @Deprecated
        public Builder sdkModule(SdkModule sdkModule) {
            Preconditions.checkNotNull(sdkModule);
            return this;
        }

        @Deprecated
        public Builder settingsModule(SettingsModule settingsModule) {
            Preconditions.checkNotNull(settingsModule);
            return this;
        }

        @Deprecated
        public Builder typeToAlexaModule(TypeToAlexaModule typeToAlexaModule) {
            Preconditions.checkNotNull(typeToAlexaModule);
            return this;
        }

        @Deprecated
        public Builder uiModule(UiModule uiModule) {
            Preconditions.checkNotNull(uiModule);
            return this;
        }

        @Deprecated
        public Builder wakeWordModule(WakeWordModule wakeWordModule) {
            Preconditions.checkNotNull(wakeWordModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private FtueManagerProvider getFtueManagerProvider() {
        return FtueModule_ProvideFtueManagerProviderFactory.proxyProvideFtueManagerProvider(this.provideHandsFreeSupportCheckerProvider.mo10268get(), getFtuePreference(), this.provideVoxMetricEventProcessingServiceProvider.mo10268get(), this.provideLocaleInteractorProvider.mo10268get(), this.provideDlsFeatureEnablerProvider.mo10268get());
    }

    private FtuePreference getFtuePreference() {
        return FtueModule_ProvideFtuePreferenceFactory.proxyProvideFtuePreference(this.providePersistentStorageFactoryProvider.mo10268get());
    }

    private void initialize(Builder builder) {
        this.provideIdentityServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideIdentityServiceFactory.create(builder.applicationModule));
        this.providePersistentStorageFactoryProvider = DoubleCheck.provider(ApplicationModule_ProvidePersistentStorageFactoryFactory.create(builder.applicationModule));
        this.provideEventBusProvider = DoubleCheck.provider(ApplicationModule_ProvideEventBusFactory.create(builder.applicationModule));
        this.provideVoiceIdentityAdapterProvider = DoubleCheck.provider(EnablementModule_ProvideVoiceIdentityAdapterFactory.create(this.provideIdentityServiceProvider, this.providePersistentStorageFactoryProvider, this.provideEventBusProvider));
        this.provideContextProvider = DoubleCheck.provider(ApplicationModule_ProvideContextFactory.create(builder.applicationModule));
        this.provideAlexaServicesConnectionProvider = DoubleCheck.provider(SdkModule_ProvideAlexaServicesConnectionFactory.create(this.provideContextProvider));
        this.provideDeviceInformationProvider = DoubleCheck.provider(ApplicationModule_ProvideDeviceInformationFactory.create(builder.applicationModule));
        this.provideVoiceEnablementProvider = DoubleCheck.provider(EnablementModule_ProvideVoiceEnablementFactory.create(this.provideDeviceInformationProvider));
        this.provideMobilyticsProvider = DoubleCheck.provider(ApplicationModule_ProvideMobilyticsFactory.create(builder.applicationModule));
        this.provideMetricsServiceProvider = DoubleCheck.provider(MetricsModule_ProvideMetricsServiceFactory.create(this.provideMobilyticsProvider));
        this.provideEnvironmentServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideEnvironmentServiceFactory.create(builder.applicationModule));
        this.provideAudioPlaybackControllerProvider = DoubleCheck.provider(NowPlayingModule_ProvideAudioPlaybackControllerFactory.create(this.provideAlexaServicesConnectionProvider));
        this.provideVoiceNowPlayingBridgeProvider = DoubleCheck.provider(NowPlayingModule_ProvideVoiceNowPlayingBridgeFactory.create(this.provideAudioPlaybackControllerProvider));
        this.provideDefaultNowPlayingCardManagerProvider = DoubleCheck.provider(NowPlayingModule_ProvideDefaultNowPlayingCardManagerFactory.create(this.provideContextProvider, this.provideAlexaServicesConnectionProvider, this.provideVoiceNowPlayingBridgeProvider));
        this.provideDownchannelControllerProvider = DoubleCheck.provider(DownchannelModule_ProvideDownchannelControllerFactory.create(this.provideContextProvider));
        this.provideVoiceMessagingReceiverProvider = DoubleCheck.provider(DownchannelModule_ProvideVoiceMessagingReceiverFactory.create(this.provideDownchannelControllerProvider, this.provideMetricsServiceProvider));
        this.provideCompatibilityInterfaceProvider = DoubleCheck.provider(WakeWordModule_ProvideCompatibilityInterfaceFactory.create());
        this.provideHandsFreeSupportCheckerProvider = DoubleCheck.provider(ModelModule_ProvideHandsFreeSupportCheckerFactory.create(this.provideContextProvider, this.provideCompatibilityInterfaceProvider));
        this.provideApplicationLifecycleServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideApplicationLifecycleServiceFactory.create(builder.applicationModule));
        this.provideDefaultAlexaConnectionManagerProvider = DoubleCheck.provider(SdkModule_ProvideDefaultAlexaConnectionManagerFactory.create(this.provideContextProvider, this.provideAlexaServicesConnectionProvider, this.provideApplicationLifecycleServiceProvider, this.provideVoiceEnablementProvider));
        this.providePreferredNavigationAppContentAccessorProvider = DoubleCheck.provider(NavigationModule_ProvidePreferredNavigationAppContentAccessorFactory.create(this.provideContextProvider));
        this.providePreferredNavigationAppRepositoryProvider = DoubleCheck.provider(NavigationModule_ProvidePreferredNavigationAppRepositoryFactory.create(this.provideContextProvider, this.providePreferredNavigationAppContentAccessorProvider));
        this.provideAlexaStateTrackerProvider = DoubleCheck.provider(SdkModule_ProvideAlexaStateTrackerFactory.create());
        this.provideLocaleSettingProvider = LocaleModule_ProvideLocaleSettingFactory.create(this.provideContextProvider);
        this.provideVoxMetricEventProcessingServiceProvider = DoubleCheck.provider(MetricsModule_ProvideVoxMetricEventProcessingServiceFactory.create(this.provideMetricsServiceProvider));
        this.provideDlsFeatureEnablerProvider = DoubleCheck.provider(LocaleModule_ProvideDlsFeatureEnablerFactory.create());
        this.provideLocaleInteractorProvider = DoubleCheck.provider(LocaleModule_ProvideLocaleInteractorFactory.create(this.provideAlexaServicesConnectionProvider, LocaleModule_ProvideLocaleAPIFactory.create(), this.provideLocaleSettingProvider, this.provideIdentityServiceProvider, this.provideVoxMetricEventProcessingServiceProvider, this.provideDlsFeatureEnablerProvider));
        this.provideWakewordPreferenceProvider = DoubleCheck.provider(WakeWordModule_ProvideWakewordPreferenceFactory.create(this.provideContextProvider));
        this.provideWakeWordFeatureGateProvider = DoubleCheck.provider(WakeWordModule_ProvideWakeWordFeatureGateFactory.create(this.provideAlexaServicesConnectionProvider, this.provideCompatibilityInterfaceProvider));
        this.voicePermissionsAuthorityProvider = DoubleCheck.provider(VoicePermissionsAuthority_Factory.create(this.provideContextProvider, this.provideApplicationLifecycleServiceProvider));
        this.providesWakeWordEventHandlerProvider = DoubleCheck.provider(WakeWordModule_ProvidesWakeWordEventHandlerFactory.create(this.provideEventBusProvider, this.provideApplicationLifecycleServiceProvider));
        this.provideVoiceOverUtilityProvider = DoubleCheck.provider(WakeWordModule_ProvideVoiceOverUtilityFactory.create(this.provideContextProvider, this.provideVoxMetricEventProcessingServiceProvider));
        this.provideWakeWordAuthorityProvider = DoubleCheck.provider(WakeWordModule_ProvideWakeWordAuthorityFactory.create(this.provideAlexaServicesConnectionProvider, this.provideWakewordPreferenceProvider, this.provideWakeWordFeatureGateProvider, this.provideApplicationLifecycleServiceProvider, this.voicePermissionsAuthorityProvider, this.providesWakeWordEventHandlerProvider, this.provideVoiceOverUtilityProvider));
        this.provideFtuePreferenceProvider = FtueModule_ProvideFtuePreferenceFactory.create(this.providePersistentStorageFactoryProvider);
        this.provideHandsfreeSettingsHandlerProvider = SettingsModule_ProvideHandsfreeSettingsHandlerFactory.create(this.provideEventBusProvider, this.provideContextProvider, this.provideWakewordPreferenceProvider, this.provideFtuePreferenceProvider, this.voicePermissionsAuthorityProvider, this.provideVoxMetricEventProcessingServiceProvider, this.provideCompatibilityInterfaceProvider);
        this.provideFeatureServiceV2Provider = DoubleCheck.provider(ApplicationModule_ProvideFeatureServiceV2Factory.create());
        this.provideFeatureCheckerProvider = DoubleCheck.provider(FeaturesModule_ProvideFeatureCheckerFactory.create(this.provideFeatureServiceV2Provider));
        this.provideAttentionSystemManagerProvider = DoubleCheck.provider(SdkModule_ProvideAttentionSystemManagerFactory.create(this.provideAlexaServicesConnectionProvider, this.provideFeatureCheckerProvider));
        this.provideEarconSettingsHandlerProvider = SettingsModule_ProvideEarconSettingsHandlerFactory.create(this.provideEventBusProvider, this.provideAttentionSystemManagerProvider, this.provideVoxMetricEventProcessingServiceProvider, this.provideContextProvider);
        this.provideLocaleSettingsHandlerProvider = SettingsModule_ProvideLocaleSettingsHandlerFactory.create(this.provideLocaleInteractorProvider, this.provideEventBusProvider);
        this.provideLocaleCombinationSettingsHandlerProvider = SettingsModule_ProvideLocaleCombinationSettingsHandlerFactory.create(this.provideLocaleInteractorProvider, this.provideEventBusProvider);
        this.provideHighPriorityActivityHandlerProvider = SettingsModule_ProvideHighPriorityActivityHandlerFactory.create(this.provideEventBusProvider, this.provideAlexaStateTrackerProvider);
        this.provideTypeToAlexaFeatureEnablerProvider = DoubleCheck.provider(TypeToAlexaModule_ProvideTypeToAlexaFeatureEnablerFactory.create(this.provideLocaleInteractorProvider));
        this.provideTtaAvailabilityHandlerProvider = SettingsModule_ProvideTtaAvailabilityHandlerFactory.create(this.provideEventBusProvider, this.provideTypeToAlexaFeatureEnablerProvider);
        this.provideVoiceSettingsProvider = DoubleCheck.provider(SettingsModule_ProvideVoiceSettingsFactory.create(this.provideEventBusProvider, this.provideHandsfreeSettingsHandlerProvider, this.provideEarconSettingsHandlerProvider, this.provideLocaleSettingsHandlerProvider, this.provideLocaleCombinationSettingsHandlerProvider, this.provideHighPriorityActivityHandlerProvider, this.provideTtaAvailabilityHandlerProvider));
        this.provideComponentEnablerProvider = DoubleCheck.provider(EnablementModule_ProvideComponentEnablerFactory.create(this.provideContextProvider, this.provideAlexaServicesConnectionProvider));
        this.provideVoiceFeatureCheckerProvider = DoubleCheck.provider(FeaturesModule_ProvideVoiceFeatureCheckerFactory.create(this.provideFeatureServiceV2Provider));
        this.provideAlertsFeatureEnablerProvider = DoubleCheck.provider(AlertsModule_ProvideAlertsFeatureEnablerFactory.create(this.provideContextProvider));
        this.provideFeatureAvailabilityCheckerProvider = DoubleCheck.provider(FeaturesModule_ProvideFeatureAvailabilityCheckerFactory.create(this.provideVoiceFeatureCheckerProvider, this.provideAlertsFeatureEnablerProvider, this.provideTypeToAlexaFeatureEnablerProvider, this.provideDlsFeatureEnablerProvider));
        this.provideMainActivityLifecycleObserverRegistrarProvider = DoubleCheck.provider(ApplicationModule_ProvideMainActivityLifecycleObserverRegistrarFactory.create(builder.applicationModule));
        this.provideVoiceUiEventBroadcastReceiverProvider = DoubleCheck.provider(EventsModule_ProvideVoiceUiEventBroadcastReceiverFactory.create());
        this.provideVoxUiEventProcessingServiceProvider = DoubleCheck.provider(EventsModule_ProvideVoxUiEventProcessingServiceFactory.create(this.provideVoxMetricEventProcessingServiceProvider));
        this.provideFtueManagerProvider = FtueModule_ProvideFtueManagerProviderFactory.create(this.provideHandsFreeSupportCheckerProvider, this.provideFtuePreferenceProvider, this.provideVoxMetricEventProcessingServiceProvider, this.provideLocaleInteractorProvider, this.provideDlsFeatureEnablerProvider);
        this.provideAlexaCardEventSenderProvider = AlexaCardModule_ProvideAlexaCardEventSenderFactory.create(this.provideAlexaServicesConnectionProvider, AlexaCardModule_ProvideAlexaCardAPIFactory.create(), this.provideEventBusProvider);
        this.provideVoiceEventBusRebroadcastSenderProvider = DoubleCheck.provider(UiModule_ProvideVoiceEventBusRebroadcastSenderFactory.create(this.provideContextProvider, this.provideEventBusProvider));
        this.provideVoiceServiceProvider = DoubleCheck.provider(ModelModule_ProvideVoiceServiceFactory.create(this.provideContextProvider, this.provideAlexaServicesConnectionProvider, this.provideVoiceEnablementProvider, this.provideMetricsServiceProvider, this.provideEnvironmentServiceProvider, this.provideEventBusProvider, this.provideAudioPlaybackControllerProvider, this.provideVoiceNowPlayingBridgeProvider, this.provideDefaultNowPlayingCardManagerProvider, this.provideVoiceMessagingReceiverProvider, this.provideHandsFreeSupportCheckerProvider, this.provideDefaultAlexaConnectionManagerProvider, this.provideVoiceIdentityAdapterProvider, this.providePreferredNavigationAppRepositoryProvider, this.provideAlexaStateTrackerProvider, this.provideLocaleInteractorProvider, this.provideWakeWordAuthorityProvider, this.provideWakewordPreferenceProvider, this.provideVoiceSettingsProvider, this.provideComponentEnablerProvider, this.provideFeatureAvailabilityCheckerProvider, this.provideMainActivityLifecycleObserverRegistrarProvider, this.provideVoiceUiEventBroadcastReceiverProvider, this.provideVoxUiEventProcessingServiceProvider, this.provideVoxMetricEventProcessingServiceProvider, this.provideFtueManagerProvider, this.provideFtuePreferenceProvider, this.voicePermissionsAuthorityProvider, this.provideApplicationLifecycleServiceProvider, this.provideLocaleSettingProvider, this.provideAlexaCardEventSenderProvider, this.provideTypeToAlexaFeatureEnablerProvider, this.provideVoiceEventBusRebroadcastSenderProvider));
        this.provideAccountUpgradeServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideAccountUpgradeServiceFactory.create(builder.applicationModule));
        this.provideNetworkServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideNetworkServiceFactory.create(builder.applicationModule));
        this.provideVoiceProvisionerProvider = DoubleCheck.provider(ProvisioningModule_ProvideVoiceProvisionerFactory.create(this.provideAccountUpgradeServiceProvider, this.provideNetworkServiceProvider, this.provideMetricsServiceProvider));
        this.provideAlexaLocaleAuthorityProvider = DoubleCheck.provider(UiModule_ProvideAlexaLocaleAuthorityFactory.create(this.provideLocaleInteractorProvider));
        this.provideLatencyReportingDelegateProvider = DoubleCheck.provider(ApplicationModule_ProvideLatencyReportingDelegateFactory.create(builder.applicationModule));
        this.provideMetricsBridgeProvider = DoubleCheck.provider(UiModule_ProvideMetricsBridgeFactory.create(this.provideMetricsServiceProvider, this.provideVoxMetricEventProcessingServiceProvider));
        this.provideFeatureAvailabilityProvider = DoubleCheck.provider(FeaturesModule_ProvideFeatureAvailabilityFactory.create(this.provideFeatureServiceV2Provider));
        this.provideRoutingServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideRoutingServiceFactory.create(builder.applicationModule));
    }

    @CanIgnoreReturnValue
    private VoiceActivity injectVoiceActivity(VoiceActivity voiceActivity) {
        VoiceActivity_MembersInjector.injectVoiceIdentityAdapter(voiceActivity, this.provideVoiceIdentityAdapterProvider.mo10268get());
        VoiceActivity_MembersInjector.injectVoiceService(voiceActivity, this.provideVoiceServiceProvider.mo10268get());
        VoiceActivity_MembersInjector.injectVoiceProvisioner(voiceActivity, this.provideVoiceProvisionerProvider.mo10268get());
        VoiceActivity_MembersInjector.injectAlexaLocaleAuthority(voiceActivity, this.provideAlexaLocaleAuthorityProvider.mo10268get());
        VoiceActivity_MembersInjector.injectMetricsService(voiceActivity, this.provideMetricsServiceProvider.mo10268get());
        VoiceActivity_MembersInjector.injectLatencyReportingDelegate(voiceActivity, this.provideLatencyReportingDelegateProvider.mo10268get());
        VoiceActivity_MembersInjector.injectFtueManagerProvider(voiceActivity, getFtueManagerProvider());
        VoiceActivity_MembersInjector.injectVoxMetricEventProcessingService(voiceActivity, this.provideVoxMetricEventProcessingServiceProvider.mo10268get());
        VoiceActivity_MembersInjector.injectUiEventProcessingService(voiceActivity, this.provideVoxUiEventProcessingServiceProvider.mo10268get());
        VoiceActivity_MembersInjector.injectMetricsBridge(voiceActivity, this.provideMetricsBridgeProvider.mo10268get());
        VoiceActivity_MembersInjector.injectVoicePermissionsAuthority(voiceActivity, this.voicePermissionsAuthorityProvider.mo10268get());
        VoiceActivity_MembersInjector.injectFeatureAvailability(voiceActivity, this.provideFeatureAvailabilityProvider.mo10268get());
        VoiceActivity_MembersInjector.injectTypeToAlexaFeatureEnabler(voiceActivity, this.provideTypeToAlexaFeatureEnablerProvider.mo10268get());
        return voiceActivity;
    }

    @CanIgnoreReturnValue
    private VoiceFtueActivity injectVoiceFtueActivity(VoiceFtueActivity voiceFtueActivity) {
        VoiceFtueActivity_MembersInjector.injectAlexaLocaleAuthority(voiceFtueActivity, this.provideAlexaLocaleAuthorityProvider.mo10268get());
        VoiceFtueActivity_MembersInjector.injectFtuePreference(voiceFtueActivity, getFtuePreference());
        VoiceFtueActivity_MembersInjector.injectMetricsService(voiceFtueActivity, this.provideMetricsServiceProvider.mo10268get());
        VoiceFtueActivity_MembersInjector.injectVoxMetricEventProcessingService(voiceFtueActivity, this.provideVoxMetricEventProcessingServiceProvider.mo10268get());
        VoiceFtueActivity_MembersInjector.injectMetricsBridge(voiceFtueActivity, this.provideMetricsBridgeProvider.mo10268get());
        VoiceFtueActivity_MembersInjector.injectVoicePermissionsAuthority(voiceFtueActivity, this.voicePermissionsAuthorityProvider.mo10268get());
        VoiceFtueActivity_MembersInjector.injectWakewordPreference(voiceFtueActivity, this.provideWakewordPreferenceProvider.mo10268get());
        VoiceFtueActivity_MembersInjector.injectFeatureAvailability(voiceFtueActivity, this.provideFeatureAvailabilityProvider.mo10268get());
        VoiceFtueActivity_MembersInjector.injectRoutingService(voiceFtueActivity, this.provideRoutingServiceProvider.mo10268get());
        return voiceFtueActivity;
    }

    @CanIgnoreReturnValue
    private VoiceMetricsBroadcastReceiver injectVoiceMetricsBroadcastReceiver(VoiceMetricsBroadcastReceiver voiceMetricsBroadcastReceiver) {
        VoiceMetricsBroadcastReceiver_MembersInjector.injectEventProcessingService(voiceMetricsBroadcastReceiver, this.provideVoxMetricEventProcessingServiceProvider.mo10268get());
        return voiceMetricsBroadcastReceiver;
    }

    @CanIgnoreReturnValue
    private VoiceNowPlayingEventBusModule injectVoiceNowPlayingEventBusModule(VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule) {
        VoiceNowPlayingEventBusModule_MembersInjector.injectEventBus(voiceNowPlayingEventBusModule, this.provideEventBusProvider.mo10268get());
        return voiceNowPlayingEventBusModule;
    }

    @CanIgnoreReturnValue
    private VoiceRoutingAdapter injectVoiceRoutingAdapter(VoiceRoutingAdapter voiceRoutingAdapter) {
        VoiceRoutingAdapter_MembersInjector.injectVoxMetricEventProcessingService(voiceRoutingAdapter, this.provideVoxMetricEventProcessingServiceProvider.mo10268get());
        VoiceRoutingAdapter_MembersInjector.injectVoiceService(voiceRoutingAdapter, this.provideVoiceServiceProvider.mo10268get());
        VoiceRoutingAdapter_MembersInjector.injectMetricsBridge(voiceRoutingAdapter, this.provideMetricsBridgeProvider.mo10268get());
        VoiceRoutingAdapter_MembersInjector.injectTtaFeatureEnabler(voiceRoutingAdapter, this.provideTypeToAlexaFeatureEnablerProvider.mo10268get());
        return voiceRoutingAdapter;
    }

    @CanIgnoreReturnValue
    private VoiceUiEventBroadcastReceiver injectVoiceUiEventBroadcastReceiver(VoiceUiEventBroadcastReceiver voiceUiEventBroadcastReceiver) {
        VoiceUiEventBroadcastReceiver_MembersInjector.injectEventProcessingService(voiceUiEventBroadcastReceiver, this.provideVoxUiEventProcessingServiceProvider.mo10268get());
        return voiceUiEventBroadcastReceiver;
    }

    @CanIgnoreReturnValue
    private WakewordPermissionRequestActivity injectWakewordPermissionRequestActivity(WakewordPermissionRequestActivity wakewordPermissionRequestActivity) {
        WakewordPermissionRequestActivity_MembersInjector.injectAlexaLocaleAuthority(wakewordPermissionRequestActivity, this.provideAlexaLocaleAuthorityProvider.mo10268get());
        WakewordPermissionRequestActivity_MembersInjector.injectMetricsService(wakewordPermissionRequestActivity, this.provideMetricsServiceProvider.mo10268get());
        WakewordPermissionRequestActivity_MembersInjector.injectWakewordPreference(wakewordPermissionRequestActivity, this.provideWakewordPreferenceProvider.mo10268get());
        WakewordPermissionRequestActivity_MembersInjector.injectVoicePermissionsAuthority(wakewordPermissionRequestActivity, this.voicePermissionsAuthorityProvider.mo10268get());
        WakewordPermissionRequestActivity_MembersInjector.injectFeatureAvailability(wakewordPermissionRequestActivity, this.provideFeatureAvailabilityProvider.mo10268get());
        return wakewordPermissionRequestActivity;
    }

    @Override // com.amazon.alexa.voice.dagger.VoiceComponent
    public void inject(VoiceSettings voiceSettings) {
    }

    @Override // com.amazon.alexa.voice.dagger.VoiceComponent
    public void inject(VoiceActivity voiceActivity) {
        injectVoiceActivity(voiceActivity);
    }

    @Override // com.amazon.alexa.voice.dagger.VoiceComponent
    public VoiceService voiceService() {
        return this.provideVoiceServiceProvider.mo10268get();
    }

    private DaggerVoiceComponent(Builder builder) {
        initialize(builder);
    }

    @Override // com.amazon.alexa.voice.dagger.VoiceComponent
    public void inject(VoiceRoutingAdapter voiceRoutingAdapter) {
        injectVoiceRoutingAdapter(voiceRoutingAdapter);
    }

    @Override // com.amazon.alexa.voice.dagger.VoiceComponent
    public void inject(VoiceFtueActivity voiceFtueActivity) {
        injectVoiceFtueActivity(voiceFtueActivity);
    }

    @Override // com.amazon.alexa.voice.dagger.VoiceComponent
    public void inject(WakewordPermissionRequestActivity wakewordPermissionRequestActivity) {
        injectWakewordPermissionRequestActivity(wakewordPermissionRequestActivity);
    }

    @Override // com.amazon.alexa.voice.dagger.VoiceComponent
    public void inject(VoiceMetricsBroadcastReceiver voiceMetricsBroadcastReceiver) {
        injectVoiceMetricsBroadcastReceiver(voiceMetricsBroadcastReceiver);
    }

    @Override // com.amazon.alexa.voice.dagger.VoiceComponent
    public void inject(VoiceUiEventBroadcastReceiver voiceUiEventBroadcastReceiver) {
        injectVoiceUiEventBroadcastReceiver(voiceUiEventBroadcastReceiver);
    }

    @Override // com.amazon.alexa.voice.dagger.VoiceComponent
    public void inject(VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule) {
        injectVoiceNowPlayingEventBusModule(voiceNowPlayingEventBusModule);
    }
}
