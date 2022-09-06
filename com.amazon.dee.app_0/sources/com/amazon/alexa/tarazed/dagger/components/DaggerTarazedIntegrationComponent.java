package com.amazon.alexa.tarazed.dagger.components;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.tarazed.account.AccountMetadataProviderAlexaMobile;
import com.amazon.alexa.tarazed.account.AccountMetadataProviderAlexaMobile_Factory;
import com.amazon.alexa.tarazed.account.AztecTokenProviderAlexaMobile;
import com.amazon.alexa.tarazed.account.AztecTokenProviderAlexaMobile_Factory;
import com.amazon.alexa.tarazed.account.IdentityEventListener;
import com.amazon.alexa.tarazed.account.IdentityEventListener_Factory;
import com.amazon.alexa.tarazed.dagger.modules.TarazedIntegrationModule;
import com.amazon.alexa.tarazed.dagger.modules.TarazedIntegrationModule_ProvideCoralService$AlexaMobileAndroidTarazedIntegration_releaseFactory;
import com.amazon.alexa.tarazed.dagger.modules.TarazedIntegrationModule_ProvideDMPSHandler$AlexaMobileAndroidTarazedIntegration_releaseFactory;
import com.amazon.alexa.tarazed.dagger.modules.TarazedIntegrationModule_ProvideDeviceInformation$AlexaMobileAndroidTarazedIntegration_releaseFactory;
import com.amazon.alexa.tarazed.dagger.modules.TarazedIntegrationModule_ProvideEnvironmentService$AlexaMobileAndroidTarazedIntegration_releaseFactory;
import com.amazon.alexa.tarazed.dagger.modules.TarazedIntegrationModule_ProvideEventBus$AlexaMobileAndroidTarazedIntegration_releaseFactory;
import com.amazon.alexa.tarazed.dagger.modules.TarazedIntegrationModule_ProvideEventBusHandler$AlexaMobileAndroidTarazedIntegration_releaseFactory;
import com.amazon.alexa.tarazed.dagger.modules.TarazedIntegrationModule_ProvideFeatureService$AlexaMobileAndroidTarazedIntegration_releaseFactory;
import com.amazon.alexa.tarazed.dagger.modules.TarazedIntegrationModule_ProvideIdentityService$AlexaMobileAndroidTarazedIntegration_releaseFactory;
import com.amazon.alexa.tarazed.dagger.modules.TarazedIntegrationModule_ProvidePersistentStorage$AlexaMobileAndroidTarazedIntegration_releaseFactory;
import com.amazon.alexa.tarazed.dagger.modules.TarazedIntegrationModule_ProvidePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_releaseFactory;
import com.amazon.alexa.tarazed.dmps.DMPSHandler;
import com.amazon.alexa.tarazed.eventbus.EventBusHandler;
import com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService;
import com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService_MembersInjector;
import com.amazon.alexa.tarazed.utils.FeatureChecker;
import com.amazon.alexa.tarazed.utils.FeatureChecker_Factory;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.dagger.components.GlobalComponent;
import com.dee.app.http.CoralService;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes10.dex */
public final class DaggerTarazedIntegrationComponent implements TarazedIntegrationComponent {
    private Provider<AccountMetadataProviderAlexaMobile> accountMetadataProviderAlexaMobileProvider;
    private Provider<AztecTokenProviderAlexaMobile> aztecTokenProviderAlexaMobileProvider;
    private Provider<FeatureChecker> featureCheckerProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getCoroutineScope getCoroutineScopeProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getDispatcherProvider getDispatcherProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getMetricsHelper getMetricsHelperProvider;
    private com_amazon_tarazed_dagger_components_GlobalComponent_getSessionLogger getSessionLoggerProvider;
    private GlobalComponent globalComponent;
    private Provider<IdentityEventListener> identityEventListenerProvider;
    private Provider<CoralService> provideCoralService$AlexaMobileAndroidTarazedIntegration_releaseProvider;
    private Provider<DMPSHandler> provideDMPSHandler$AlexaMobileAndroidTarazedIntegration_releaseProvider;
    private Provider<DeviceInformation> provideDeviceInformation$AlexaMobileAndroidTarazedIntegration_releaseProvider;
    private Provider<EnvironmentService> provideEnvironmentService$AlexaMobileAndroidTarazedIntegration_releaseProvider;
    private Provider<EventBus> provideEventBus$AlexaMobileAndroidTarazedIntegration_releaseProvider;
    private Provider<EventBusHandler> provideEventBusHandler$AlexaMobileAndroidTarazedIntegration_releaseProvider;
    private Provider<FeatureServiceV2> provideFeatureService$AlexaMobileAndroidTarazedIntegration_releaseProvider;
    private Provider<IdentityService> provideIdentityService$AlexaMobileAndroidTarazedIntegration_releaseProvider;
    private Provider<PersistentStorage> providePersistentStorage$AlexaMobileAndroidTarazedIntegration_releaseProvider;
    private Provider<PersistentStorage.Factory> providePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_releaseProvider;

    /* loaded from: classes10.dex */
    public static final class Builder {
        private GlobalComponent globalComponent;
        private TarazedIntegrationModule tarazedIntegrationModule;

        public TarazedIntegrationComponent build() {
            Preconditions.checkBuilderRequirement(this.tarazedIntegrationModule, TarazedIntegrationModule.class);
            Preconditions.checkBuilderRequirement(this.globalComponent, GlobalComponent.class);
            return new DaggerTarazedIntegrationComponent(this);
        }

        public Builder globalComponent(GlobalComponent globalComponent) {
            this.globalComponent = (GlobalComponent) Preconditions.checkNotNull(globalComponent);
            return this;
        }

        public Builder tarazedIntegrationModule(TarazedIntegrationModule tarazedIntegrationModule) {
            this.tarazedIntegrationModule = (TarazedIntegrationModule) Preconditions.checkNotNull(tarazedIntegrationModule);
            return this;
        }

        private Builder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getCoroutineScope implements Provider<CoroutineScope> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getCoroutineScope(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        @Override // javax.inject.Provider
        /* renamed from: get  reason: collision with other method in class */
        public CoroutineScope mo10268get() {
            return (CoroutineScope) Preconditions.checkNotNull(this.globalComponent.getCoroutineScope(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getDispatcherProvider implements Provider<DispatcherProvider> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getDispatcherProvider(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DispatcherProvider mo10268get() {
            return (DispatcherProvider) Preconditions.checkNotNull(this.globalComponent.getDispatcherProvider(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getMetricsHelper implements Provider<TarazedMetricsHelper> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getMetricsHelper(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public TarazedMetricsHelper mo10268get() {
            return (TarazedMetricsHelper) Preconditions.checkNotNull(this.globalComponent.getMetricsHelper(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class com_amazon_tarazed_dagger_components_GlobalComponent_getSessionLogger implements Provider<TarazedSessionLogger> {
        private final GlobalComponent globalComponent;

        com_amazon_tarazed_dagger_components_GlobalComponent_getSessionLogger(GlobalComponent globalComponent) {
            this.globalComponent = globalComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public TarazedSessionLogger mo10268get() {
            return (TarazedSessionLogger) Preconditions.checkNotNull(this.globalComponent.getSessionLogger(), "Cannot return null from a non-@Nullable component method");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideFeatureService$AlexaMobileAndroidTarazedIntegration_releaseProvider = DoubleCheck.provider(TarazedIntegrationModule_ProvideFeatureService$AlexaMobileAndroidTarazedIntegration_releaseFactory.create(builder.tarazedIntegrationModule));
        this.getSessionLoggerProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getSessionLogger(builder.globalComponent);
        this.getMetricsHelperProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getMetricsHelper(builder.globalComponent);
        this.getDispatcherProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getDispatcherProvider(builder.globalComponent);
        this.getCoroutineScopeProvider = new com_amazon_tarazed_dagger_components_GlobalComponent_getCoroutineScope(builder.globalComponent);
        this.featureCheckerProvider = DoubleCheck.provider(FeatureChecker_Factory.create(this.provideFeatureService$AlexaMobileAndroidTarazedIntegration_releaseProvider, this.getSessionLoggerProvider, this.getMetricsHelperProvider, this.getDispatcherProvider, this.getCoroutineScopeProvider));
        this.provideDeviceInformation$AlexaMobileAndroidTarazedIntegration_releaseProvider = DoubleCheck.provider(TarazedIntegrationModule_ProvideDeviceInformation$AlexaMobileAndroidTarazedIntegration_releaseFactory.create(builder.tarazedIntegrationModule));
        this.provideIdentityService$AlexaMobileAndroidTarazedIntegration_releaseProvider = DoubleCheck.provider(TarazedIntegrationModule_ProvideIdentityService$AlexaMobileAndroidTarazedIntegration_releaseFactory.create(builder.tarazedIntegrationModule));
        this.provideCoralService$AlexaMobileAndroidTarazedIntegration_releaseProvider = DoubleCheck.provider(TarazedIntegrationModule_ProvideCoralService$AlexaMobileAndroidTarazedIntegration_releaseFactory.create(builder.tarazedIntegrationModule));
        this.providePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_releaseProvider = DoubleCheck.provider(TarazedIntegrationModule_ProvidePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_releaseFactory.create(builder.tarazedIntegrationModule));
        this.providePersistentStorage$AlexaMobileAndroidTarazedIntegration_releaseProvider = DoubleCheck.provider(TarazedIntegrationModule_ProvidePersistentStorage$AlexaMobileAndroidTarazedIntegration_releaseFactory.create(builder.tarazedIntegrationModule, this.providePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_releaseProvider));
        this.provideEventBus$AlexaMobileAndroidTarazedIntegration_releaseProvider = DoubleCheck.provider(TarazedIntegrationModule_ProvideEventBus$AlexaMobileAndroidTarazedIntegration_releaseFactory.create(builder.tarazedIntegrationModule));
        this.provideEventBusHandler$AlexaMobileAndroidTarazedIntegration_releaseProvider = DoubleCheck.provider(TarazedIntegrationModule_ProvideEventBusHandler$AlexaMobileAndroidTarazedIntegration_releaseFactory.create(builder.tarazedIntegrationModule, this.getSessionLoggerProvider, this.getMetricsHelperProvider));
        this.provideEnvironmentService$AlexaMobileAndroidTarazedIntegration_releaseProvider = DoubleCheck.provider(TarazedIntegrationModule_ProvideEnvironmentService$AlexaMobileAndroidTarazedIntegration_releaseFactory.create(builder.tarazedIntegrationModule));
        this.provideDMPSHandler$AlexaMobileAndroidTarazedIntegration_releaseProvider = DoubleCheck.provider(TarazedIntegrationModule_ProvideDMPSHandler$AlexaMobileAndroidTarazedIntegration_releaseFactory.create(builder.tarazedIntegrationModule, this.provideDeviceInformation$AlexaMobileAndroidTarazedIntegration_releaseProvider, this.provideIdentityService$AlexaMobileAndroidTarazedIntegration_releaseProvider, this.provideCoralService$AlexaMobileAndroidTarazedIntegration_releaseProvider, this.providePersistentStorage$AlexaMobileAndroidTarazedIntegration_releaseProvider, this.provideEventBus$AlexaMobileAndroidTarazedIntegration_releaseProvider, this.provideEventBusHandler$AlexaMobileAndroidTarazedIntegration_releaseProvider, this.provideEnvironmentService$AlexaMobileAndroidTarazedIntegration_releaseProvider, this.getSessionLoggerProvider, this.getMetricsHelperProvider));
        this.identityEventListenerProvider = DoubleCheck.provider(IdentityEventListener_Factory.create(this.provideEventBus$AlexaMobileAndroidTarazedIntegration_releaseProvider, this.provideEventBusHandler$AlexaMobileAndroidTarazedIntegration_releaseProvider, this.getSessionLoggerProvider));
        this.aztecTokenProviderAlexaMobileProvider = DoubleCheck.provider(AztecTokenProviderAlexaMobile_Factory.create(this.provideIdentityService$AlexaMobileAndroidTarazedIntegration_releaseProvider, this.getSessionLoggerProvider, this.getMetricsHelperProvider));
        this.accountMetadataProviderAlexaMobileProvider = DoubleCheck.provider(AccountMetadataProviderAlexaMobile_Factory.create(this.provideIdentityService$AlexaMobileAndroidTarazedIntegration_releaseProvider, this.provideDeviceInformation$AlexaMobileAndroidTarazedIntegration_releaseProvider));
    }

    private DefaultAlexaTarazedService injectDefaultAlexaTarazedService(DefaultAlexaTarazedService defaultAlexaTarazedService) {
        DefaultAlexaTarazedService_MembersInjector.injectLogger(defaultAlexaTarazedService, (TarazedSessionLogger) Preconditions.checkNotNull(this.globalComponent.getSessionLogger(), "Cannot return null from a non-@Nullable component method"));
        DefaultAlexaTarazedService_MembersInjector.injectSessionNotifier(defaultAlexaTarazedService, (TarazedSessionNotifier) Preconditions.checkNotNull(this.globalComponent.getSessionNotifier(), "Cannot return null from a non-@Nullable component method"));
        DefaultAlexaTarazedService_MembersInjector.injectFeatureChecker(defaultAlexaTarazedService, this.featureCheckerProvider.mo10268get());
        DefaultAlexaTarazedService_MembersInjector.injectDmpsHandler(defaultAlexaTarazedService, this.provideDMPSHandler$AlexaMobileAndroidTarazedIntegration_releaseProvider.mo10268get());
        DefaultAlexaTarazedService_MembersInjector.injectIdentityService(defaultAlexaTarazedService, this.provideIdentityService$AlexaMobileAndroidTarazedIntegration_releaseProvider.mo10268get());
        DefaultAlexaTarazedService_MembersInjector.injectEventBus(defaultAlexaTarazedService, this.provideEventBus$AlexaMobileAndroidTarazedIntegration_releaseProvider.mo10268get());
        DefaultAlexaTarazedService_MembersInjector.injectIdentityEventListener(defaultAlexaTarazedService, this.identityEventListenerProvider.mo10268get());
        DefaultAlexaTarazedService_MembersInjector.injectAztecTokenProvider(defaultAlexaTarazedService, this.aztecTokenProviderAlexaMobileProvider);
        DefaultAlexaTarazedService_MembersInjector.injectAccountMetadataProvider(defaultAlexaTarazedService, this.accountMetadataProviderAlexaMobileProvider);
        DefaultAlexaTarazedService_MembersInjector.injectAlexaMobileDeviceInformation(defaultAlexaTarazedService, this.provideDeviceInformation$AlexaMobileAndroidTarazedIntegration_releaseProvider.mo10268get());
        DefaultAlexaTarazedService_MembersInjector.injectMainScope(defaultAlexaTarazedService, (CoroutineScope) Preconditions.checkNotNull(this.globalComponent.getCoroutineScope(), "Cannot return null from a non-@Nullable component method"));
        return defaultAlexaTarazedService;
    }

    @Override // com.amazon.alexa.tarazed.dagger.components.TarazedIntegrationComponent
    public void inject(DefaultAlexaTarazedService defaultAlexaTarazedService) {
        injectDefaultAlexaTarazedService(defaultAlexaTarazedService);
    }

    private DaggerTarazedIntegrationComponent(Builder builder) {
        this.globalComponent = builder.globalComponent;
        initialize(builder);
    }
}
