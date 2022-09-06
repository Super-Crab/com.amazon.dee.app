package com.amazon.alexa.alertsca;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.telephony.TelephonyManager;
import com.amazon.alexa.accessories.protocols.ConnectedAccessoryInquirer;
import com.amazon.alexa.accessory.sco.ScoPrioritizer;
import com.amazon.alexa.alertsca.AlertsCapabilityAgentService;
import com.amazon.alexa.alertsca.dependencies.AccessoryModule;
import com.amazon.alexa.alertsca.dependencies.AccessoryModule_ProvidesConnectedAccessoryInquirerFactory;
import com.amazon.alexa.alertsca.dependencies.AccessoryModule_ProvidesScoPrioritizerFactory;
import com.amazon.alexa.alertsca.dependencies.AlertsEventBusModule;
import com.amazon.alexa.alertsca.dependencies.AlertsEventBusModule_ProvidesEventBusFactory;
import com.amazon.alexa.alertsca.dependencies.AlexaServicesModule_ProvideAmfApisFactory;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule_ProvidesAlarmManagerFactory;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule_ProvidesAndroidNotificationManagerFactory;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule_ProvidesApplicationContextFactory;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule_ProvidesAudioManagerFactory;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule_ProvidesConnectivityManagerFactory;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule_ProvidesMainThreadHandlerFactory;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule_ProvidesScoControllerFactory;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule_ProvidesSharedPreferencesFactory;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule_ProvidesTelephonyManagerFactory;
import com.amazon.alexa.alertsca.dependencies.DataModule;
import com.amazon.alexa.alertsca.dependencies.DataModule_ProvidesCacheDirectoryFileFactory;
import com.amazon.alexa.alertsca.dependencies.DeviceInformationModule;
import com.amazon.alexa.alertsca.dependencies.DeviceInformationModule_ProvideDeviceInformationFactory;
import com.amazon.alexa.alertsca.dependencies.ExecutorModule;
import com.amazon.alexa.alertsca.dependencies.ExecutorModule_ProvidesSharedSchedulerFactory;
import com.amazon.alexa.alertsca.dependencies.GsonModule;
import com.amazon.alexa.alertsca.dependencies.GsonModule_ProvidesGsonFactory;
import com.amazon.alexa.alertsca.dependencies.MetricsModule;
import com.amazon.alexa.alertsca.dependencies.MetricsModule_ProvideMetricsServiceFactory;
import com.amazon.alexa.alertsca.dependencies.MetricsModule_ProvideMobilyticsFactory;
import com.amazon.alexa.alertsca.exoplayer.AmplitudeCalculatingAudioSink;
import com.amazon.alexa.alertsca.exoplayer.AmplitudeCalculatingAudioSink_Factory;
import com.amazon.alexa.alertsca.exoplayer.AmplitudeMultiplyingAudioProcessor;
import com.amazon.alexa.alertsca.exoplayer.AmplitudeMultiplyingAudioProcessor_Factory;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.alertsca.networking.ConnectivityAuthority;
import com.amazon.alexa.alertsca.networking.ConnectivityAuthority_Factory;
import com.amazon.alexa.alertsca.notification.AlexaAlertsNotificationManager;
import com.amazon.alexa.alertsca.notification.AlexaAlertsNotificationManager_Factory;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.bluetooth.sco.BluetoothScoController;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.io.File;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes6.dex */
public final class DaggerAlertsCapabilityAgentService_Injector implements AlertsCapabilityAgentService.Injector {
    private Provider<AlertView> alertViewProvider;
    private Provider<AlertsAnalyzer> alertsAnalyzerProvider;
    private Provider<AlertsAuthority> alertsAuthorityProvider;
    private Provider<AlertsCapabilityAgent> alertsCapabilityAgentProvider;
    private Provider<AlertsDatabaseHelper> alertsDatabaseHelperProvider;
    private AlertsEventBus_Factory alertsEventBusProvider;
    private Provider<AlertsExoPlayer> alertsExoPlayerProvider;
    private Provider<AlertsScheduler> alertsSchedulerProvider;
    private Provider<AlertsStore> alertsStoreProvider;
    private Provider<AlexaAlertsNotificationManager> alexaAlertsNotificationManagerProvider;
    private Provider<AlexaEventSender> alexaEventSenderProvider;
    private Provider<AlexaInteractionScheduler> alexaInteractionSchedulerProvider;
    private Provider<AmplitudeCalculatingAudioSink> amplitudeCalculatingAudioSinkProvider;
    private Provider<AmplitudeMultiplyingAudioProcessor> amplitudeMultiplyingAudioProcessorProvider;
    private Provider<AssetDownloader> assetDownloaderProvider;
    private Provider<AudioFocusManager> audioFocusManagerProvider;
    private Provider<ConnectivityAuthority> connectivityAuthorityProvider;
    private Provider<AlexaMobileFrameworkApis> provideAmfApisProvider;
    private Provider<DeviceInformation> provideDeviceInformationProvider;
    private Provider<MetricsService> provideMetricsServiceProvider;
    private Provider<Mobilytics> provideMobilyticsProvider;
    private Provider<AlarmManager> providesAlarmManagerProvider;
    private Provider<NotificationManager> providesAndroidNotificationManagerProvider;
    private Provider<Context> providesApplicationContextProvider;
    private Provider<AudioManager> providesAudioManagerProvider;
    private Provider<File> providesCacheDirectoryFileProvider;
    private Provider<ConnectedAccessoryInquirer> providesConnectedAccessoryInquirerProvider;
    private Provider<ConnectivityManager> providesConnectivityManagerProvider;
    private Provider<EventBus> providesEventBusProvider;
    private Provider<Gson> providesGsonProvider;
    private Provider<Handler> providesMainThreadHandlerProvider;
    private Provider<BluetoothScoController> providesScoControllerProvider;
    private Provider<ScoPrioritizer> providesScoPrioritizerProvider;
    private Provider<SharedPreferences> providesSharedPreferencesProvider;
    private Provider<ScheduledExecutorService> providesSharedSchedulerProvider;
    private Provider<TelephonyManager> providesTelephonyManagerProvider;
    private Provider<ScoAuthority> scoAuthorityProvider;
    private Provider<VolumeAdjuster> volumeAdjusterProvider;

    /* loaded from: classes6.dex */
    public static final class Builder {
        private AccessoryModule accessoryModule;
        private AlertsEventBusModule alertsEventBusModule;
        private ApplicationModule applicationModule;
        private DataModule dataModule;
        private DeviceInformationModule deviceInformationModule;
        private ExecutorModule executorModule;
        private MetricsModule metricsModule;

        public Builder accessoryModule(AccessoryModule accessoryModule) {
            this.accessoryModule = (AccessoryModule) Preconditions.checkNotNull(accessoryModule);
            return this;
        }

        public Builder alertsEventBusModule(AlertsEventBusModule alertsEventBusModule) {
            this.alertsEventBusModule = (AlertsEventBusModule) Preconditions.checkNotNull(alertsEventBusModule);
            return this;
        }

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = (ApplicationModule) Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public AlertsCapabilityAgentService.Injector build() {
            if (this.alertsEventBusModule == null) {
                this.alertsEventBusModule = new AlertsEventBusModule();
            }
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            if (this.metricsModule == null) {
                this.metricsModule = new MetricsModule();
            }
            if (this.executorModule == null) {
                this.executorModule = new ExecutorModule();
            }
            if (this.deviceInformationModule == null) {
                this.deviceInformationModule = new DeviceInformationModule();
            }
            if (this.dataModule == null) {
                this.dataModule = new DataModule();
            }
            if (this.accessoryModule == null) {
                this.accessoryModule = new AccessoryModule();
            }
            return new DaggerAlertsCapabilityAgentService_Injector(this);
        }

        public Builder dataModule(DataModule dataModule) {
            this.dataModule = (DataModule) Preconditions.checkNotNull(dataModule);
            return this;
        }

        public Builder deviceInformationModule(DeviceInformationModule deviceInformationModule) {
            this.deviceInformationModule = (DeviceInformationModule) Preconditions.checkNotNull(deviceInformationModule);
            return this;
        }

        public Builder executorModule(ExecutorModule executorModule) {
            this.executorModule = (ExecutorModule) Preconditions.checkNotNull(executorModule);
            return this;
        }

        @Deprecated
        public Builder gsonModule(GsonModule gsonModule) {
            Preconditions.checkNotNull(gsonModule);
            return this;
        }

        public Builder metricsModule(MetricsModule metricsModule) {
            this.metricsModule = (MetricsModule) Preconditions.checkNotNull(metricsModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private AlertsEventBus getAlertsEventBus() {
        return new AlertsEventBus(this.providesEventBusProvider.mo10268get());
    }

    private void initialize(Builder builder) {
        this.providesEventBusProvider = DoubleCheck.provider(AlertsEventBusModule_ProvidesEventBusFactory.create(builder.alertsEventBusModule));
        this.providesGsonProvider = DoubleCheck.provider(GsonModule_ProvidesGsonFactory.create());
        this.providesApplicationContextProvider = DoubleCheck.provider(ApplicationModule_ProvidesApplicationContextFactory.create(builder.applicationModule));
        this.provideAmfApisProvider = DoubleCheck.provider(AlexaServicesModule_ProvideAmfApisFactory.create(this.providesApplicationContextProvider));
        this.alertsEventBusProvider = AlertsEventBus_Factory.create(this.providesEventBusProvider);
        this.providesAndroidNotificationManagerProvider = DoubleCheck.provider(ApplicationModule_ProvidesAndroidNotificationManagerFactory.create(builder.applicationModule));
        this.provideMobilyticsProvider = DoubleCheck.provider(MetricsModule_ProvideMobilyticsFactory.create(builder.metricsModule));
        this.provideMetricsServiceProvider = DoubleCheck.provider(MetricsModule_ProvideMetricsServiceFactory.create(builder.metricsModule, this.provideMobilyticsProvider));
        this.providesSharedSchedulerProvider = DoubleCheck.provider(ExecutorModule_ProvidesSharedSchedulerFactory.create(builder.executorModule));
        this.provideDeviceInformationProvider = DoubleCheck.provider(DeviceInformationModule_ProvideDeviceInformationFactory.create(builder.deviceInformationModule));
        this.alexaAlertsNotificationManagerProvider = DoubleCheck.provider(AlexaAlertsNotificationManager_Factory.create(this.providesApplicationContextProvider, this.alertsEventBusProvider, this.providesAndroidNotificationManagerProvider, this.provideMetricsServiceProvider, this.providesSharedSchedulerProvider, this.provideDeviceInformationProvider));
        this.providesAlarmManagerProvider = DoubleCheck.provider(ApplicationModule_ProvidesAlarmManagerFactory.create(builder.applicationModule));
        this.alertsSchedulerProvider = DoubleCheck.provider(AlertsScheduler_Factory.create(this.providesAlarmManagerProvider, this.provideMetricsServiceProvider));
        this.alertsDatabaseHelperProvider = DoubleCheck.provider(AlertsDatabaseHelper_Factory.create(this.providesApplicationContextProvider));
        this.providesCacheDirectoryFileProvider = DoubleCheck.provider(DataModule_ProvidesCacheDirectoryFileFactory.create(builder.dataModule, this.providesApplicationContextProvider));
        this.assetDownloaderProvider = DoubleCheck.provider(AssetDownloader_Factory.create(this.providesCacheDirectoryFileProvider));
        this.alertsStoreProvider = DoubleCheck.provider(AlertsStore_Factory.create(this.alertsDatabaseHelperProvider, this.assetDownloaderProvider));
        this.alertsAnalyzerProvider = DoubleCheck.provider(AlertsAnalyzer_Factory.create());
        this.providesAudioManagerProvider = DoubleCheck.provider(ApplicationModule_ProvidesAudioManagerFactory.create(builder.applicationModule));
        this.amplitudeMultiplyingAudioProcessorProvider = DoubleCheck.provider(AmplitudeMultiplyingAudioProcessor_Factory.create());
        this.amplitudeCalculatingAudioSinkProvider = DoubleCheck.provider(AmplitudeCalculatingAudioSink_Factory.create());
        this.volumeAdjusterProvider = DoubleCheck.provider(VolumeAdjuster_Factory.create(this.providesApplicationContextProvider, this.providesAudioManagerProvider, this.amplitudeMultiplyingAudioProcessorProvider, this.amplitudeCalculatingAudioSinkProvider));
        this.alertsExoPlayerProvider = DoubleCheck.provider(AlertsExoPlayer_Factory.create(this.providesApplicationContextProvider, this.provideMetricsServiceProvider, this.volumeAdjusterProvider, this.amplitudeMultiplyingAudioProcessorProvider));
        this.audioFocusManagerProvider = DoubleCheck.provider(AudioFocusManager_Factory.create(this.providesAudioManagerProvider));
        this.alexaEventSenderProvider = DoubleCheck.provider(AlexaEventSender_Factory.create(this.provideAmfApisProvider));
        this.alexaInteractionSchedulerProvider = DoubleCheck.provider(AlexaInteractionScheduler_Factory.create(this.provideAmfApisProvider));
        this.alertViewProvider = DoubleCheck.provider(AlertView_Factory.create(this.providesApplicationContextProvider, this.provideMetricsServiceProvider));
        this.providesMainThreadHandlerProvider = DoubleCheck.provider(ApplicationModule_ProvidesMainThreadHandlerFactory.create(builder.applicationModule));
        this.providesConnectedAccessoryInquirerProvider = DoubleCheck.provider(AccessoryModule_ProvidesConnectedAccessoryInquirerFactory.create(builder.accessoryModule));
        this.providesTelephonyManagerProvider = DoubleCheck.provider(ApplicationModule_ProvidesTelephonyManagerFactory.create(builder.applicationModule));
        this.providesScoControllerProvider = DoubleCheck.provider(ApplicationModule_ProvidesScoControllerFactory.create(builder.applicationModule, this.providesApplicationContextProvider, this.providesAudioManagerProvider, this.providesTelephonyManagerProvider));
        this.providesScoPrioritizerProvider = DoubleCheck.provider(AccessoryModule_ProvidesScoPrioritizerFactory.create(builder.accessoryModule));
        this.scoAuthorityProvider = DoubleCheck.provider(ScoAuthority_Factory.create(this.providesApplicationContextProvider, this.providesScoControllerProvider, this.providesScoPrioritizerProvider));
        this.alertsAuthorityProvider = DoubleCheck.provider(AlertsAuthority_Factory.create(this.providesApplicationContextProvider, this.alertsEventBusProvider, this.alertsSchedulerProvider, this.alertsStoreProvider, this.alertsAnalyzerProvider, this.providesGsonProvider, this.alertsExoPlayerProvider, this.audioFocusManagerProvider, this.alexaEventSenderProvider, this.alexaInteractionSchedulerProvider, this.providesSharedSchedulerProvider, this.alertViewProvider, this.providesMainThreadHandlerProvider, this.providesConnectedAccessoryInquirerProvider, this.scoAuthorityProvider, this.provideMetricsServiceProvider));
        this.providesSharedPreferencesProvider = DoubleCheck.provider(ApplicationModule_ProvidesSharedPreferencesFactory.create(builder.applicationModule));
        this.alertsCapabilityAgentProvider = DoubleCheck.provider(AlertsCapabilityAgent_Factory.create(this.alertsAuthorityProvider, this.providesGsonProvider, this.alertsEventBusProvider, this.providesApplicationContextProvider, this.alexaEventSenderProvider, this.provideMetricsServiceProvider, this.providesSharedPreferencesProvider));
        this.providesConnectivityManagerProvider = DoubleCheck.provider(ApplicationModule_ProvidesConnectivityManagerFactory.create(builder.applicationModule));
        this.connectivityAuthorityProvider = DoubleCheck.provider(ConnectivityAuthority_Factory.create(this.providesApplicationContextProvider, this.providesConnectivityManagerProvider, this.alertsEventBusProvider));
    }

    @CanIgnoreReturnValue
    private AlertsCapabilityAgentService injectAlertsCapabilityAgentService(AlertsCapabilityAgentService alertsCapabilityAgentService) {
        AlertsCapabilityAgentService_MembersInjector.injectAlertsEventBus(alertsCapabilityAgentService, getAlertsEventBus());
        AlertsCapabilityAgentService_MembersInjector.injectGson(alertsCapabilityAgentService, this.providesGsonProvider.mo10268get());
        AlertsCapabilityAgentService_MembersInjector.injectAlexaMobileFrameworkApis(alertsCapabilityAgentService, this.provideAmfApisProvider.mo10268get());
        AlertsCapabilityAgentService_MembersInjector.injectAlexaAlertsNotificationManager(alertsCapabilityAgentService, this.alexaAlertsNotificationManagerProvider.mo10268get());
        AlertsCapabilityAgentService_MembersInjector.injectAlertsCapabilityAgent(alertsCapabilityAgentService, this.alertsCapabilityAgentProvider.mo10268get());
        AlertsCapabilityAgentService_MembersInjector.injectConnectivityAuthority(alertsCapabilityAgentService, this.connectivityAuthorityProvider.mo10268get());
        AlertsCapabilityAgentService_MembersInjector.injectMetricsService(alertsCapabilityAgentService, this.provideMetricsServiceProvider.mo10268get());
        return alertsCapabilityAgentService;
    }

    @Override // com.amazon.alexa.alertsca.AlertsCapabilityAgentService.Injector
    public void inject(AlertsCapabilityAgentService alertsCapabilityAgentService) {
        injectAlertsCapabilityAgentService(alertsCapabilityAgentService);
    }

    private DaggerAlertsCapabilityAgentService_Injector(Builder builder) {
        initialize(builder);
    }
}
