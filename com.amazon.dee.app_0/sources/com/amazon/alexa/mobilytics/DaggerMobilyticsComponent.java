package com.amazon.alexa.mobilytics;

import com.amazon.alexa.mobilytics.auth.CognitoCredentialsProvider;
import com.amazon.alexa.mobilytics.auth.CognitoCredentialsProvider_Factory_Factory;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.mobilytics.configuration.ConfigManager;
import com.amazon.alexa.mobilytics.configuration.ConfigManager_Factory;
import com.amazon.alexa.mobilytics.configuration.Config_Factory;
import com.amazon.alexa.mobilytics.configuration.DefaultApplicationConfiguration;
import com.amazon.alexa.mobilytics.configuration.DefaultApplicationConfiguration_Factory;
import com.amazon.alexa.mobilytics.configuration.DefaultDeviceConfiguration;
import com.amazon.alexa.mobilytics.configuration.DefaultDeviceConfiguration_Factory;
import com.amazon.alexa.mobilytics.configuration.DefaultEndpointManager;
import com.amazon.alexa.mobilytics.configuration.DefaultEndpointManager_Factory;
import com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker;
import com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker_Factory;
import com.amazon.alexa.mobilytics.configuration.PersistentConfigStorage;
import com.amazon.alexa.mobilytics.configuration.PersistentConfigStorage_Factory;
import com.amazon.alexa.mobilytics.configuration.S3ConfigPuller;
import com.amazon.alexa.mobilytics.configuration.S3ConfigPuller_Factory;
import com.amazon.alexa.mobilytics.connector.ConnectorManager;
import com.amazon.alexa.mobilytics.connector.ConnectorManager_Factory;
import com.amazon.alexa.mobilytics.connector.DCMConnector;
import com.amazon.alexa.mobilytics.connector.DCMConnector_Factory_Factory;
import com.amazon.alexa.mobilytics.connector.DefaultConnectorExecutor;
import com.amazon.alexa.mobilytics.connector.DefaultConnectorExecutor_Factory_Factory;
import com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector;
import com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector_Factory_Factory;
import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule;
import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule_ProvideCognitoPoolIdsFactory;
import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule_ProvideContextFactory;
import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule_ProvideDefaultDataHandlersFactory;
import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule_ProvideGsonFactory;
import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule_ProvideInstallationIdFactory;
import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule_ProvideJsonConverterFactory;
import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule_ProvideMetricsFactoryFactory;
import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule_ProvideMobilyticsConfigurationFactory;
import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule_ProvideProtobufProtobufHandlersFactory;
import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule_ProvideUserProviderFactory;
import com.amazon.alexa.mobilytics.event.serializer.DefaultEventSerializer;
import com.amazon.alexa.mobilytics.event.serializer.DefaultEventSerializer_Factory;
import com.amazon.alexa.mobilytics.event.serializer.DefaultProtobufSerializer;
import com.amazon.alexa.mobilytics.event.serializer.DefaultProtobufSerializer_Factory;
import com.amazon.alexa.mobilytics.event.serializer.handlers.ApplicationDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.ApplicationDataHandler_Factory;
import com.amazon.alexa.mobilytics.event.serializer.handlers.DeviceDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.DeviceDataHandler_Factory;
import com.amazon.alexa.mobilytics.event.serializer.handlers.EventDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.EventDataHandler_Factory;
import com.amazon.alexa.mobilytics.event.serializer.handlers.MwsPivotsDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.MwsPivotsDataHandler_Factory;
import com.amazon.alexa.mobilytics.event.serializer.handlers.SessionDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.SessionDataHandler_Factory;
import com.amazon.alexa.mobilytics.event.serializer.handlers.UserDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.UserDataHandler_Factory;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ApplicationProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ApplicationProtobufHandler_Factory;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.DeviceProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.DeviceProtobufHandler_Factory;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.EventProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.EventProtobufHandler_Factory;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.MwsPivotsProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.MwsPivotsProtobufHandler_Factory;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.SessionProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.SessionProtobufHandler_Factory;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.UserProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.UserProtobufHandler_Factory;
import com.amazon.alexa.mobilytics.executor.DefaultExecutor;
import com.amazon.alexa.mobilytics.executor.DefaultExecutor_Factory;
import com.amazon.alexa.mobilytics.internal.DCMMetricsFactoryProvider;
import com.amazon.alexa.mobilytics.internal.DCMMetricsFactoryProvider_Factory;
import com.amazon.alexa.mobilytics.internal.InstallationIdProvider;
import com.amazon.alexa.mobilytics.internal.InstallationIdProvider_Factory;
import com.amazon.alexa.mobilytics.lifecycle.Lifecycle;
import com.amazon.alexa.mobilytics.lifecycle.Lifecycle_Factory;
import com.amazon.alexa.mobilytics.recorder.KinesisEventRecorder;
import com.amazon.alexa.mobilytics.recorder.KinesisEventRecorder_Factory_Factory;
import com.amazon.alexa.mobilytics.s3.S3ClientProvider;
import com.amazon.alexa.mobilytics.s3.S3ClientProvider_Factory;
import com.amazon.alexa.mobilytics.session.DefaultSessionStorage;
import com.amazon.alexa.mobilytics.session.DefaultSessionStorage_Factory;
import com.amazon.alexa.mobilytics.session.SessionManager;
import com.amazon.alexa.mobilytics.session.SessionManager_Factory;
import com.amazon.alexa.mobilytics.storage.PreferencesStorage;
import com.amazon.alexa.mobilytics.storage.PreferencesStorage_Factory_Factory;
import com.amazon.alexa.mobilytics.stream.KinesisClientProvider;
import com.amazon.alexa.mobilytics.stream.KinesisClientProvider_Factory;
import com.amazon.alexa.mobilytics.timeline.TimelineDataPublisher;
import com.amazon.alexa.mobilytics.timeline.TimelineDataPublisher_Factory;
import com.amazon.alexa.mobilytics.timeline.TimelineManager;
import com.amazon.alexa.mobilytics.timeline.TimelineManager_Factory;
import com.amazon.alexa.mobilytics.timeline.TimelineStorage;
import com.amazon.alexa.mobilytics.timeline.TimelineStorage_Factory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DaggerMobilyticsComponent implements MobilyticsComponent {
    private Provider<ApplicationDataHandler> applicationDataHandlerProvider;
    private Provider<ApplicationProtobufHandler> applicationProtobufHandlerProvider;
    private Provider<ConfigManager> configManagerProvider;
    private Provider<Config> configProvider;
    private Provider<ConnectorManager> connectorManagerProvider;
    private Provider<DCMMetricsFactoryProvider> dCMMetricsFactoryProvider;
    private Provider<DefaultApplicationConfiguration> defaultApplicationConfigurationProvider;
    private Provider<DefaultDeviceConfiguration> defaultDeviceConfigurationProvider;
    private Provider<DefaultEndpointManager> defaultEndpointManagerProvider;
    private Provider<DefaultEventSerializer> defaultEventSerializerProvider;
    private Provider<DefaultExecutor> defaultExecutorProvider;
    private Provider<DefaultMobilytics> defaultMobilyticsProvider;
    private Provider<DefaultProtobufSerializer> defaultProtobufSerializerProvider;
    private Provider<DefaultRecordChecker> defaultRecordCheckerProvider;
    private Provider<DefaultSessionStorage> defaultSessionStorageProvider;
    private Provider<DeviceDataHandler> deviceDataHandlerProvider;
    private Provider<DeviceProtobufHandler> deviceProtobufHandlerProvider;
    private Provider<EventDataHandler> eventDataHandlerProvider;
    private Provider<EventProtobufHandler> eventProtobufHandlerProvider;
    private Provider<PreferencesStorage.Factory> factoryProvider;
    private Provider<CognitoCredentialsProvider.Factory> factoryProvider2;
    private Provider<KinesisEventRecorder.Factory> factoryProvider3;
    private Provider<DefaultKinesisConnector.Factory> factoryProvider4;
    private Provider<DCMConnector.Factory> factoryProvider5;
    private Provider<DefaultConnectorExecutor.Factory> factoryProvider6;
    private Provider<InstallationIdProvider> installationIdProvider;
    private Provider<KinesisClientProvider> kinesisClientProvider;
    private Provider<Lifecycle> lifecycleProvider;
    private Provider<MwsPivotsDataHandler> mwsPivotsDataHandlerProvider;
    private Provider<MwsPivotsProtobufHandler> mwsPivotsProtobufHandlerProvider;
    private Provider<PersistentConfigStorage> persistentConfigStorageProvider;
    private MobilyticsModule_ProvideCognitoPoolIdsFactory provideCognitoPoolIdsProvider;
    private MobilyticsModule_ProvideContextFactory provideContextProvider;
    private MobilyticsModule_ProvideDefaultDataHandlersFactory provideDefaultDataHandlersProvider;
    private MobilyticsModule_ProvideGsonFactory provideGsonProvider;
    private MobilyticsModule_ProvideInstallationIdFactory provideInstallationIdProvider;
    private MobilyticsModule_ProvideJsonConverterFactory provideJsonConverterProvider;
    private MobilyticsModule_ProvideMetricsFactoryFactory provideMetricsFactoryProvider;
    private MobilyticsModule_ProvideMobilyticsConfigurationFactory provideMobilyticsConfigurationProvider;
    private MobilyticsModule_ProvideProtobufProtobufHandlersFactory provideProtobufProtobufHandlersProvider;
    private MobilyticsModule_ProvideUserProviderFactory provideUserProvider;
    private Provider<S3ClientProvider> s3ClientProvider;
    private Provider<S3ConfigPuller> s3ConfigPullerProvider;
    private Provider<SessionDataHandler> sessionDataHandlerProvider;
    private Provider<SessionManager> sessionManagerProvider;
    private Provider<SessionProtobufHandler> sessionProtobufHandlerProvider;
    private Provider<TimelineDataPublisher> timelineDataPublisherProvider;
    private Provider<TimelineManager> timelineManagerProvider;
    private Provider<TimelineStorage> timelineStorageProvider;
    private Provider<UserDataHandler> userDataHandlerProvider;
    private Provider<UserProtobufHandler> userProtobufHandlerProvider;

    /* loaded from: classes9.dex */
    public static final class Builder {
        private MobilyticsModule mobilyticsModule;

        public MobilyticsComponent build() {
            Preconditions.checkBuilderRequirement(this.mobilyticsModule, MobilyticsModule.class);
            return new DaggerMobilyticsComponent(this);
        }

        public Builder mobilyticsModule(MobilyticsModule mobilyticsModule) {
            this.mobilyticsModule = (MobilyticsModule) Preconditions.checkNotNull(mobilyticsModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideMobilyticsConfigurationProvider = MobilyticsModule_ProvideMobilyticsConfigurationFactory.create(builder.mobilyticsModule);
        this.s3ClientProvider = DoubleCheck.provider(S3ClientProvider_Factory.create());
        this.s3ConfigPullerProvider = DoubleCheck.provider(S3ConfigPuller_Factory.create(this.s3ClientProvider));
        this.provideGsonProvider = MobilyticsModule_ProvideGsonFactory.create(builder.mobilyticsModule);
        this.provideJsonConverterProvider = MobilyticsModule_ProvideJsonConverterFactory.create(builder.mobilyticsModule, this.provideGsonProvider);
        this.provideContextProvider = MobilyticsModule_ProvideContextFactory.create(builder.mobilyticsModule);
        this.factoryProvider = DoubleCheck.provider(PreferencesStorage_Factory_Factory.create(this.provideContextProvider, this.provideJsonConverterProvider));
        this.persistentConfigStorageProvider = DoubleCheck.provider(PersistentConfigStorage_Factory.create(this.factoryProvider, this.provideJsonConverterProvider));
        this.factoryProvider2 = DoubleCheck.provider(CognitoCredentialsProvider_Factory_Factory.create(this.provideContextProvider));
        this.kinesisClientProvider = DoubleCheck.provider(KinesisClientProvider_Factory.create());
        this.provideCognitoPoolIdsProvider = MobilyticsModule_ProvideCognitoPoolIdsFactory.create(builder.mobilyticsModule);
        this.configProvider = DoubleCheck.provider(Config_Factory.create());
        this.configManagerProvider = DoubleCheck.provider(ConfigManager_Factory.create(this.s3ConfigPullerProvider, this.provideJsonConverterProvider, this.persistentConfigStorageProvider, this.factoryProvider2, this.kinesisClientProvider, this.provideCognitoPoolIdsProvider, this.configProvider));
        this.lifecycleProvider = DoubleCheck.provider(Lifecycle_Factory.create());
        this.installationIdProvider = DoubleCheck.provider(InstallationIdProvider_Factory.create(this.factoryProvider, this.provideContextProvider));
        this.provideInstallationIdProvider = MobilyticsModule_ProvideInstallationIdFactory.create(builder.mobilyticsModule, this.installationIdProvider);
        this.defaultSessionStorageProvider = DoubleCheck.provider(DefaultSessionStorage_Factory.create(this.factoryProvider, this.provideInstallationIdProvider));
        this.sessionManagerProvider = DoubleCheck.provider(SessionManager_Factory.create(this.provideContextProvider, this.provideInstallationIdProvider, this.defaultSessionStorageProvider));
        this.timelineStorageProvider = DoubleCheck.provider(TimelineStorage_Factory.create(this.factoryProvider));
        this.timelineManagerProvider = DoubleCheck.provider(TimelineManager_Factory.create(this.timelineStorageProvider));
        this.timelineDataPublisherProvider = DoubleCheck.provider(TimelineDataPublisher_Factory.create(this.timelineStorageProvider, this.factoryProvider));
        this.defaultDeviceConfigurationProvider = DoubleCheck.provider(DefaultDeviceConfiguration_Factory.create(this.provideContextProvider, this.provideMobilyticsConfigurationProvider));
        this.defaultApplicationConfigurationProvider = DoubleCheck.provider(DefaultApplicationConfiguration_Factory.create(this.provideMobilyticsConfigurationProvider));
        this.provideUserProvider = MobilyticsModule_ProvideUserProviderFactory.create(builder.mobilyticsModule);
        this.defaultRecordCheckerProvider = DoubleCheck.provider(DefaultRecordChecker_Factory.create(this.defaultDeviceConfigurationProvider, this.defaultApplicationConfigurationProvider, this.configManagerProvider, this.provideUserProvider));
        this.applicationDataHandlerProvider = DoubleCheck.provider(ApplicationDataHandler_Factory.create(this.defaultApplicationConfigurationProvider));
        this.deviceDataHandlerProvider = DoubleCheck.provider(DeviceDataHandler_Factory.create(this.defaultDeviceConfigurationProvider));
        this.eventDataHandlerProvider = DoubleCheck.provider(EventDataHandler_Factory.create(this.provideMobilyticsConfigurationProvider, this.provideJsonConverterProvider));
        this.mwsPivotsDataHandlerProvider = DoubleCheck.provider(MwsPivotsDataHandler_Factory.create(this.defaultApplicationConfigurationProvider, this.defaultDeviceConfigurationProvider, this.provideUserProvider));
        this.userDataHandlerProvider = DoubleCheck.provider(UserDataHandler_Factory.create(this.provideUserProvider));
        this.sessionDataHandlerProvider = DoubleCheck.provider(SessionDataHandler_Factory.create(this.sessionManagerProvider));
        this.provideDefaultDataHandlersProvider = MobilyticsModule_ProvideDefaultDataHandlersFactory.create(builder.mobilyticsModule, this.applicationDataHandlerProvider, this.deviceDataHandlerProvider, this.eventDataHandlerProvider, this.mwsPivotsDataHandlerProvider, this.userDataHandlerProvider, this.sessionDataHandlerProvider);
        this.defaultEventSerializerProvider = DoubleCheck.provider(DefaultEventSerializer_Factory.create(this.provideDefaultDataHandlersProvider));
        this.applicationProtobufHandlerProvider = DoubleCheck.provider(ApplicationProtobufHandler_Factory.create(this.defaultApplicationConfigurationProvider));
        this.deviceProtobufHandlerProvider = DoubleCheck.provider(DeviceProtobufHandler_Factory.create(this.defaultDeviceConfigurationProvider));
        this.eventProtobufHandlerProvider = DoubleCheck.provider(EventProtobufHandler_Factory.create(this.provideMobilyticsConfigurationProvider));
        this.mwsPivotsProtobufHandlerProvider = DoubleCheck.provider(MwsPivotsProtobufHandler_Factory.create(this.defaultApplicationConfigurationProvider, this.defaultDeviceConfigurationProvider, this.provideUserProvider));
        this.userProtobufHandlerProvider = DoubleCheck.provider(UserProtobufHandler_Factory.create(this.provideUserProvider));
        this.sessionProtobufHandlerProvider = DoubleCheck.provider(SessionProtobufHandler_Factory.create(this.sessionManagerProvider));
        this.provideProtobufProtobufHandlersProvider = MobilyticsModule_ProvideProtobufProtobufHandlersFactory.create(builder.mobilyticsModule, this.applicationProtobufHandlerProvider, this.deviceProtobufHandlerProvider, this.eventProtobufHandlerProvider, this.mwsPivotsProtobufHandlerProvider, this.userProtobufHandlerProvider, this.sessionProtobufHandlerProvider);
        this.defaultProtobufSerializerProvider = DoubleCheck.provider(DefaultProtobufSerializer_Factory.create(this.provideProtobufProtobufHandlersProvider));
        this.factoryProvider3 = DoubleCheck.provider(KinesisEventRecorder_Factory_Factory.create(this.provideContextProvider));
        this.factoryProvider4 = DoubleCheck.provider(DefaultKinesisConnector_Factory_Factory.create(this.defaultRecordCheckerProvider, this.defaultEventSerializerProvider, this.defaultProtobufSerializerProvider, this.provideInstallationIdProvider, this.factoryProvider3, this.factoryProvider2));
        this.dCMMetricsFactoryProvider = DoubleCheck.provider(DCMMetricsFactoryProvider_Factory.create(this.provideMobilyticsConfigurationProvider));
        this.provideMetricsFactoryProvider = MobilyticsModule_ProvideMetricsFactoryFactory.create(builder.mobilyticsModule, this.dCMMetricsFactoryProvider);
        this.factoryProvider5 = DoubleCheck.provider(DCMConnector_Factory_Factory.create(this.defaultDeviceConfigurationProvider, this.defaultApplicationConfigurationProvider, this.provideMetricsFactoryProvider, this.defaultRecordCheckerProvider));
        this.defaultEndpointManagerProvider = DoubleCheck.provider(DefaultEndpointManager_Factory.create(this.configManagerProvider, this.provideCognitoPoolIdsProvider));
        this.factoryProvider6 = DoubleCheck.provider(DefaultConnectorExecutor_Factory_Factory.create());
        this.connectorManagerProvider = DoubleCheck.provider(ConnectorManager_Factory.create(this.factoryProvider4, this.factoryProvider5, this.defaultEndpointManagerProvider, this.factoryProvider6));
        this.defaultExecutorProvider = DoubleCheck.provider(DefaultExecutor_Factory.create(this.provideMobilyticsConfigurationProvider, this.configManagerProvider, this.lifecycleProvider, this.sessionManagerProvider, this.timelineManagerProvider, this.timelineStorageProvider, this.timelineDataPublisherProvider, this.defaultRecordCheckerProvider, this.connectorManagerProvider, this.defaultEndpointManagerProvider));
        this.defaultMobilyticsProvider = DoubleCheck.provider(DefaultMobilytics_Factory.create(this.provideMobilyticsConfigurationProvider, this.defaultExecutorProvider, this.sessionManagerProvider));
    }

    @Override // com.amazon.alexa.mobilytics.MobilyticsComponent
    public Mobilytics mobilytics() {
        return this.defaultMobilyticsProvider.mo10268get();
    }

    private DaggerMobilyticsComponent(Builder builder) {
        initialize(builder);
    }
}
