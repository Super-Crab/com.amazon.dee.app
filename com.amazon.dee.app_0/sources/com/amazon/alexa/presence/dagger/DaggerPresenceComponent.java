package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.presence.PresenceController;
import com.amazon.alexa.presence.PresenceLifecycleManager;
import com.amazon.alexa.presence.PresenceLifecycleManager_MembersInjector;
import com.amazon.alexa.presence.PresenceSubComponents;
import com.amazon.alexa.presence.detection.BLEScannerCallback;
import com.amazon.alexa.presence.detection.BlePacketConsumer;
import com.amazon.alexa.presence.eventbus.BatteryOptimizationSubscriber;
import com.amazon.alexa.presence.eventbus.PresenceSubscriber;
import com.amazon.alexa.presence.eventbus.PushNotificationSubscriber;
import com.amazon.alexa.presence.identity.IdentityHelper_Factory;
import com.amazon.alexa.presence.library.ContextHelper_Factory;
import com.amazon.alexa.presence.receiver.AlexaPresenceBluetoothReceiver;
import com.amazon.alexa.presence.receiver.BeaconReceiver;
import com.amazon.alexa.presence.receiver.BeaconReceiver_MembersInjector;
import com.amazon.alexa.presence.receiver.ScanCheckAlarmReceiver;
import com.amazon.alexa.presence.reporter.PresenceBeaconResolverClient_Factory;
import com.amazon.alexa.presence.service.AlexaBeaconDetectorService;
import com.amazon.alexa.presence.service.AlexaBeaconDetectorService_MembersInjector;
import com.amazon.alexa.presence.service.PresenceForegroundServiceStateAdviser;
import com.amazon.alexa.presence.service.PresenceIntentHandler;
import com.amazon.alexa.presence.service.PresenceIntentHandler_Factory;
import com.amazon.alexa.presence.utils.BluetoothHelper_Factory;
import com.dee.app.metrics.MetricsServiceV2;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DaggerPresenceComponent implements PresenceComponent {
    private ContextHelper_Factory contextHelperProvider;
    private PresenceBeaconResolverClient_Factory presenceBeaconResolverClientProvider;
    private Provider<PresenceController> presenceControllerProvider;
    private PresenceModule_PresenceDataStoreFactory presenceDataStoreProvider;
    private Provider<PresenceForegroundServiceStateAdviser> presenceForegroundServiceStateAdviserProvider;
    private Provider<PresenceIntentHandler> presenceIntentHandlerProvider;
    private PresenceModule_ProvideAlarmManagerFactory provideAlarmManagerProvider;
    private Provider<AlexaPresenceBluetoothReceiver> provideAlexaPresenceBluetoothReceiverProvider;
    private PresenceModule_ProvideApplicationLifecycleServiceFactory provideApplicationLifecycleServiceProvider;
    private PresenceModule_ProvideBatteryOptimizationEventMessageFilterFactory provideBatteryOptimizationEventMessageFilterProvider;
    private PresenceModule_ProvideBatteryOptimizationFactory provideBatteryOptimizationProvider;
    private Provider<BatteryOptimizationSubscriber> provideBatteryOptimizationSubscriberProvider;
    private PresenceModule_ProvideBeaconFormatLogicFactory provideBeaconFormatLogicProvider;
    private Provider<BLEScannerCallback> provideBleScannerCallbackProvider;
    private Provider<PresenceSubComponents> provideComponentInitializationProvider;
    private PresenceModule_ProvideContextFactory provideContextProvider;
    private PresenceModule_ProvideEventBusHelperFactory provideEventBusHelperProvider;
    private PresenceModule_ProvideEventBusFactory provideEventBusProvider;
    private PresenceModule_ProvideHttpAsyncTaskInstanceFactoryFactory provideHttpAsyncTaskInstanceFactoryProvider;
    private PresenceModule_ProvideMetricsServiceV2Factory provideMetricsServiceV2Provider;
    private PresenceModule_ProvidePersonIdFactory providePersonIdProvider;
    private Provider<BlePacketConsumer> providePostDetectionLogicProvider;
    private PresenceModule_ProvidePowerManagerFactory providePowerManagerProvider;
    private PresenceModule_ProvidePresenceAccessTokenProviderFactory providePresenceAccessTokenProvider;
    private PresenceModule_ProvidePresenceAlarmManagerFactory providePresenceAlarmManagerProvider;
    private PresenceModule_ProvidePresenceApplicationLifecycleObserverFactory providePresenceApplicationLifecycleObserverProvider;
    private PresenceModule_ProvidePresenceEventMessageFilterFactory providePresenceEventMessageFilterProvider;
    private PresenceModule_ProvidePresenceRetryStrategyFactory providePresenceRetryStrategyProvider;
    private Provider<PresenceSubscriber> providePresenceSubscriberProvider;
    private PresenceModule_ProvidePushNotificationEventMessageFilterFactory providePushNotificationEventMessageFilterProvider;
    private Provider<PushNotificationSubscriber> providePushNotificationSubscriberProvider;
    private Provider<ScanCheckAlarmReceiver> provideScanCheckAlarmReceiverProvider;
    private PresenceModule_ProvideSimpleDateFormatFactory provideSimpleDateFormatProvider;
    private PresenceModule_ProvidesBluetoothAdapterFactory providesBluetoothAdapterProvider;

    /* loaded from: classes9.dex */
    public static final class Builder {
        private PresenceModule presenceModule;

        public PresenceComponent build() {
            Preconditions.checkBuilderRequirement(this.presenceModule, PresenceModule.class);
            return new DaggerPresenceComponent(this);
        }

        public Builder presenceModule(PresenceModule presenceModule) {
            this.presenceModule = (PresenceModule) Preconditions.checkNotNull(presenceModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private DeviceInformation getDeviceInformation() {
        return PresenceModule_ProvideDeviceInformationFactory.proxyProvideDeviceInformation(PresenceModule_ProvideApplicationComponentRegistryFactory.proxyProvideApplicationComponentRegistry());
    }

    private EventBus getEventBus() {
        return PresenceModule_ProvideEventBusFactory.proxyProvideEventBus(PresenceModule_ProvideApplicationComponentRegistryFactory.proxyProvideApplicationComponentRegistry());
    }

    private IdentityService getIdentityService() {
        return PresenceModule_ProvideIdentityServiceFactory.proxyProvideIdentityService(PresenceModule_ProvideApplicationComponentRegistryFactory.proxyProvideApplicationComponentRegistry());
    }

    private MetricsServiceV2 getMetricsServiceV2() {
        return PresenceModule_ProvideMetricsServiceV2Factory.proxyProvideMetricsServiceV2(PresenceModule_ProvideApplicationComponentRegistryFactory.proxyProvideApplicationComponentRegistry());
    }

    private void initialize(Builder builder) {
        this.provideEventBusProvider = PresenceModule_ProvideEventBusFactory.create(PresenceModule_ProvideApplicationComponentRegistryFactory.create());
        this.providePresenceEventMessageFilterProvider = PresenceModule_ProvidePresenceEventMessageFilterFactory.create(builder.presenceModule);
        this.provideMetricsServiceV2Provider = PresenceModule_ProvideMetricsServiceV2Factory.create(PresenceModule_ProvideApplicationComponentRegistryFactory.create());
        this.provideApplicationLifecycleServiceProvider = PresenceModule_ProvideApplicationLifecycleServiceFactory.create(PresenceModule_ProvideApplicationComponentRegistryFactory.create());
        this.presenceControllerProvider = new DelegateFactory();
        this.providePresenceApplicationLifecycleObserverProvider = PresenceModule_ProvidePresenceApplicationLifecycleObserverFactory.create(builder.presenceModule, this.provideMetricsServiceV2Provider, this.presenceControllerProvider);
        this.provideAlexaPresenceBluetoothReceiverProvider = DoubleCheck.provider(PresenceModule_ProvideAlexaPresenceBluetoothReceiverFactory.create(builder.presenceModule, this.provideMetricsServiceV2Provider, this.presenceControllerProvider));
        this.provideAlarmManagerProvider = PresenceModule_ProvideAlarmManagerFactory.create(builder.presenceModule);
        this.providePresenceAlarmManagerProvider = PresenceModule_ProvidePresenceAlarmManagerFactory.create(builder.presenceModule, this.provideAlarmManagerProvider);
        this.provideScanCheckAlarmReceiverProvider = DoubleCheck.provider(PresenceModule_ProvideScanCheckAlarmReceiverFactory.create(builder.presenceModule, this.provideMetricsServiceV2Provider, this.presenceControllerProvider));
        this.provideComponentInitializationProvider = DoubleCheck.provider(PresenceModule_ProvideComponentInitializationFactory.create(builder.presenceModule, this.provideApplicationLifecycleServiceProvider, this.providePresenceApplicationLifecycleObserverProvider, this.provideAlexaPresenceBluetoothReceiverProvider, this.providePresenceAlarmManagerProvider, this.provideScanCheckAlarmReceiverProvider));
        this.presenceDataStoreProvider = PresenceModule_PresenceDataStoreFactory.create(builder.presenceModule);
        this.presenceForegroundServiceStateAdviserProvider = DoubleCheck.provider(PresenceModule_PresenceForegroundServiceStateAdviserFactory.create(builder.presenceModule));
        DelegateFactory.setDelegate(this.presenceControllerProvider, PresenceModule_PresenceControllerFactory.create(PresenceModule_RoamingClientFactory.create(), this.provideComponentInitializationProvider, this.presenceDataStoreProvider, this.presenceForegroundServiceStateAdviserProvider, this.provideMetricsServiceV2Provider));
        this.providePresenceSubscriberProvider = DoubleCheck.provider(PresenceModule_ProvidePresenceSubscriberFactory.create(builder.presenceModule, this.providePresenceEventMessageFilterProvider, this.provideMetricsServiceV2Provider, this.presenceControllerProvider));
        this.providePowerManagerProvider = PresenceModule_ProvidePowerManagerFactory.create(builder.presenceModule);
        this.provideBatteryOptimizationProvider = PresenceModule_ProvideBatteryOptimizationFactory.create(builder.presenceModule, this.providePowerManagerProvider, this.provideMetricsServiceV2Provider);
        this.provideBatteryOptimizationEventMessageFilterProvider = PresenceModule_ProvideBatteryOptimizationEventMessageFilterFactory.create(builder.presenceModule);
        this.provideBatteryOptimizationSubscriberProvider = DoubleCheck.provider(PresenceModule_ProvideBatteryOptimizationSubscriberFactory.create(builder.presenceModule, this.provideEventBusProvider, this.provideBatteryOptimizationProvider, this.provideBatteryOptimizationEventMessageFilterProvider, this.provideMetricsServiceV2Provider));
        this.providePushNotificationEventMessageFilterProvider = PresenceModule_ProvidePushNotificationEventMessageFilterFactory.create(builder.presenceModule);
        this.providePushNotificationSubscriberProvider = DoubleCheck.provider(PresenceModule_ProvidePushNotificationSubscriberFactory.create(builder.presenceModule, this.providePushNotificationEventMessageFilterProvider, this.provideMetricsServiceV2Provider, this.presenceControllerProvider));
        this.provideEventBusHelperProvider = PresenceModule_ProvideEventBusHelperFactory.create(builder.presenceModule, this.provideEventBusProvider, this.providePresenceSubscriberProvider, this.provideBatteryOptimizationSubscriberProvider, this.providePushNotificationSubscriberProvider);
        this.provideContextProvider = PresenceModule_ProvideContextFactory.create(builder.presenceModule);
        this.providesBluetoothAdapterProvider = PresenceModule_ProvidesBluetoothAdapterFactory.create(builder.presenceModule, this.provideContextProvider);
        this.provideSimpleDateFormatProvider = PresenceModule_ProvideSimpleDateFormatFactory.create(builder.presenceModule);
        this.provideBeaconFormatLogicProvider = PresenceModule_ProvideBeaconFormatLogicFactory.create(builder.presenceModule, this.provideSimpleDateFormatProvider);
        this.providePresenceAccessTokenProvider = PresenceModule_ProvidePresenceAccessTokenProviderFactory.create(builder.presenceModule);
        this.providePresenceRetryStrategyProvider = PresenceModule_ProvidePresenceRetryStrategyFactory.create(builder.presenceModule);
        this.provideHttpAsyncTaskInstanceFactoryProvider = PresenceModule_ProvideHttpAsyncTaskInstanceFactoryFactory.create(builder.presenceModule, this.providePresenceAccessTokenProvider, this.providePresenceRetryStrategyProvider, this.provideMetricsServiceV2Provider);
        this.providePersonIdProvider = PresenceModule_ProvidePersonIdFactory.create(PresenceModule_ProvideApplicationComponentRegistryFactory.create());
        this.presenceBeaconResolverClientProvider = PresenceBeaconResolverClient_Factory.create(this.provideHttpAsyncTaskInstanceFactoryProvider, this.provideMetricsServiceV2Provider, this.provideBeaconFormatLogicProvider, this.providePersonIdProvider);
        this.providePostDetectionLogicProvider = DoubleCheck.provider(PresenceModule_ProvidePostDetectionLogicFactory.create(builder.presenceModule, this.provideMetricsServiceV2Provider, this.provideBeaconFormatLogicProvider, this.presenceBeaconResolverClientProvider));
        this.provideBleScannerCallbackProvider = DoubleCheck.provider(PresenceModule_ProvideBleScannerCallbackFactory.create(builder.presenceModule, this.providePostDetectionLogicProvider));
        this.contextHelperProvider = ContextHelper_Factory.create(this.provideContextProvider, this.presenceDataStoreProvider);
        this.presenceIntentHandlerProvider = DoubleCheck.provider(PresenceIntentHandler_Factory.create(this.providesBluetoothAdapterProvider, this.provideMetricsServiceV2Provider, this.provideBleScannerCallbackProvider, IdentityHelper_Factory.create(), BluetoothHelper_Factory.create(), this.contextHelperProvider));
    }

    @CanIgnoreReturnValue
    private AlexaBeaconDetectorService injectAlexaBeaconDetectorService(AlexaBeaconDetectorService alexaBeaconDetectorService) {
        AlexaBeaconDetectorService_MembersInjector.injectMHandler(alexaBeaconDetectorService, this.presenceIntentHandlerProvider.mo10268get());
        return alexaBeaconDetectorService;
    }

    @CanIgnoreReturnValue
    private BeaconReceiver injectBeaconReceiver(BeaconReceiver beaconReceiver) {
        BeaconReceiver_MembersInjector.injectMBlePacketConsumer(beaconReceiver, this.providePostDetectionLogicProvider.mo10268get());
        BeaconReceiver_MembersInjector.injectMMetricsServiceV2(beaconReceiver, getMetricsServiceV2());
        return beaconReceiver;
    }

    @CanIgnoreReturnValue
    private PresenceLifecycleManager injectPresenceLifecycleManager(PresenceLifecycleManager presenceLifecycleManager) {
        PresenceLifecycleManager_MembersInjector.injectEventBusHelper(presenceLifecycleManager, DoubleCheck.lazy(this.provideEventBusHelperProvider));
        PresenceLifecycleManager_MembersInjector.injectIdentityService(presenceLifecycleManager, getIdentityService());
        PresenceLifecycleManager_MembersInjector.injectMPresenceSubComponents(presenceLifecycleManager, DoubleCheck.lazy(this.provideComponentInitializationProvider));
        PresenceLifecycleManager_MembersInjector.injectMetricsServiceV2(presenceLifecycleManager, getMetricsServiceV2());
        PresenceLifecycleManager_MembersInjector.injectDeviceInformation(presenceLifecycleManager, getDeviceInformation());
        PresenceLifecycleManager_MembersInjector.injectController(presenceLifecycleManager, DoubleCheck.lazy(this.presenceControllerProvider));
        PresenceLifecycleManager_MembersInjector.injectEventBus(presenceLifecycleManager, getEventBus());
        return presenceLifecycleManager;
    }

    @Override // com.amazon.alexa.presence.dagger.PresenceComponent
    public void inject(PresenceLifecycleManager presenceLifecycleManager) {
        injectPresenceLifecycleManager(presenceLifecycleManager);
    }

    private DaggerPresenceComponent(Builder builder) {
        initialize(builder);
    }

    @Override // com.amazon.alexa.presence.dagger.PresenceComponent
    public void inject(AlexaBeaconDetectorService alexaBeaconDetectorService) {
        injectAlexaBeaconDetectorService(alexaBeaconDetectorService);
    }

    @Override // com.amazon.alexa.presence.dagger.PresenceComponent
    public void inject(BeaconReceiver beaconReceiver) {
        injectBeaconReceiver(beaconReceiver);
    }
}
