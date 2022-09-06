package com.amazon.dee.app.dependencies;

import com.amazon.alexa.assetManagementService.api.AssetManagementService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashObserverRegistrar;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.dialog.api.DialogBuilderProvider;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.location.LocationService;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.ttcf.TTCFService;
import com.amazon.dee.app.services.core.DefaultApplicationLifecycleService;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.services.export.ComponentBinder;
import com.amazon.dee.app.services.security.CertificateReaderService;
import com.amazon.dee.app.storage.JsonConverter;
import com.amazon.deecomms.api.CommsServiceV2;
import com.dee.app.data.DefaultElementLocalStorage;
import com.dee.app.http.CoralService;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideComponentFactoriesFactory implements Factory<ComponentBinder> {
    private final Provider<DefaultApplicationLifecycleService> applicationLifecycleServiceProvider;
    private final Provider<AssetManagementService> assetManagementServiceProvider;
    private final Provider<CertificateReaderService> certificateReaderServiceProvider;
    private final Provider<CommsServiceV2> commsServiceV2Provider;
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashObserverRegistrar> crashObserverRegistrarProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<DefaultElementLocalStorage> dataStoreProvider;
    private final Provider<DialogBuilderProvider> dialogBuilderProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<FeatureQuery> featureQueryProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<JsonConverter> jsonConverterProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<MainActivityLifecycleService> mainActivityLifecycleServiceProvider;
    private final Provider<MarketplaceService> marketplaceServiceProvider;
    private final Provider<MetricsServiceV2> metricServiceV2Provider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final Provider<ModeService> modeServiceProvider;
    private final ServiceModule module;
    private final Provider<PersistentStorage.Factory> persistentStorageFactoryProvider;
    private final Provider<PersonIdProvider> personIdProvider;
    private final Provider<com.amazon.alexa.protocols.marketplace.MarketplaceService> protocolsMarketplaceServiceProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<TTCFService> ttcfServiceProvider;

    public ServiceModule_ProvideComponentFactoriesFactory(ServiceModule serviceModule, Provider<EnvironmentService> provider, Provider<FeatureQuery> provider2, Provider<IdentityService> provider3, Provider<PersonIdProvider> provider4, Provider<MainActivityLifecycleService> provider5, Provider<DefaultApplicationLifecycleService> provider6, Provider<MarketplaceService> provider7, Provider<com.amazon.alexa.protocols.marketplace.MarketplaceService> provider8, Provider<CrashMetadata> provider9, Provider<CrashObserverRegistrar> provider10, Provider<CrashReporter> provider11, Provider<MetricsService> provider12, Provider<MetricsServiceV2> provider13, Provider<Mobilytics> provider14, Provider<ModeService> provider15, Provider<LocationService> provider16, Provider<CoralService> provider17, Provider<JsonConverter> provider18, Provider<PersistentStorage.Factory> provider19, Provider<RoutingService> provider20, Provider<TTCFService> provider21, Provider<CertificateReaderService> provider22, Provider<CommsServiceV2> provider23, Provider<FeatureServiceV2> provider24, Provider<AssetManagementService> provider25, Provider<DefaultElementLocalStorage> provider26, Provider<DialogBuilderProvider> provider27) {
        this.module = serviceModule;
        this.environmentServiceProvider = provider;
        this.featureQueryProvider = provider2;
        this.identityServiceProvider = provider3;
        this.personIdProvider = provider4;
        this.mainActivityLifecycleServiceProvider = provider5;
        this.applicationLifecycleServiceProvider = provider6;
        this.marketplaceServiceProvider = provider7;
        this.protocolsMarketplaceServiceProvider = provider8;
        this.crashMetadataProvider = provider9;
        this.crashObserverRegistrarProvider = provider10;
        this.crashReporterProvider = provider11;
        this.metricsServiceProvider = provider12;
        this.metricServiceV2Provider = provider13;
        this.mobilyticsProvider = provider14;
        this.modeServiceProvider = provider15;
        this.locationServiceProvider = provider16;
        this.coralServiceProvider = provider17;
        this.jsonConverterProvider = provider18;
        this.persistentStorageFactoryProvider = provider19;
        this.routingServiceProvider = provider20;
        this.ttcfServiceProvider = provider21;
        this.certificateReaderServiceProvider = provider22;
        this.commsServiceV2Provider = provider23;
        this.featureServiceV2Provider = provider24;
        this.assetManagementServiceProvider = provider25;
        this.dataStoreProvider = provider26;
        this.dialogBuilderProvider = provider27;
    }

    public static ServiceModule_ProvideComponentFactoriesFactory create(ServiceModule serviceModule, Provider<EnvironmentService> provider, Provider<FeatureQuery> provider2, Provider<IdentityService> provider3, Provider<PersonIdProvider> provider4, Provider<MainActivityLifecycleService> provider5, Provider<DefaultApplicationLifecycleService> provider6, Provider<MarketplaceService> provider7, Provider<com.amazon.alexa.protocols.marketplace.MarketplaceService> provider8, Provider<CrashMetadata> provider9, Provider<CrashObserverRegistrar> provider10, Provider<CrashReporter> provider11, Provider<MetricsService> provider12, Provider<MetricsServiceV2> provider13, Provider<Mobilytics> provider14, Provider<ModeService> provider15, Provider<LocationService> provider16, Provider<CoralService> provider17, Provider<JsonConverter> provider18, Provider<PersistentStorage.Factory> provider19, Provider<RoutingService> provider20, Provider<TTCFService> provider21, Provider<CertificateReaderService> provider22, Provider<CommsServiceV2> provider23, Provider<FeatureServiceV2> provider24, Provider<AssetManagementService> provider25, Provider<DefaultElementLocalStorage> provider26, Provider<DialogBuilderProvider> provider27) {
        return new ServiceModule_ProvideComponentFactoriesFactory(serviceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27);
    }

    public static ComponentBinder provideInstance(ServiceModule serviceModule, Provider<EnvironmentService> provider, Provider<FeatureQuery> provider2, Provider<IdentityService> provider3, Provider<PersonIdProvider> provider4, Provider<MainActivityLifecycleService> provider5, Provider<DefaultApplicationLifecycleService> provider6, Provider<MarketplaceService> provider7, Provider<com.amazon.alexa.protocols.marketplace.MarketplaceService> provider8, Provider<CrashMetadata> provider9, Provider<CrashObserverRegistrar> provider10, Provider<CrashReporter> provider11, Provider<MetricsService> provider12, Provider<MetricsServiceV2> provider13, Provider<Mobilytics> provider14, Provider<ModeService> provider15, Provider<LocationService> provider16, Provider<CoralService> provider17, Provider<JsonConverter> provider18, Provider<PersistentStorage.Factory> provider19, Provider<RoutingService> provider20, Provider<TTCFService> provider21, Provider<CertificateReaderService> provider22, Provider<CommsServiceV2> provider23, Provider<FeatureServiceV2> provider24, Provider<AssetManagementService> provider25, Provider<DefaultElementLocalStorage> provider26, Provider<DialogBuilderProvider> provider27) {
        return proxyProvideComponentFactories(serviceModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12), DoubleCheck.lazy(provider13), DoubleCheck.lazy(provider14), DoubleCheck.lazy(provider15), DoubleCheck.lazy(provider16), DoubleCheck.lazy(provider17), DoubleCheck.lazy(provider18), DoubleCheck.lazy(provider19), DoubleCheck.lazy(provider20), DoubleCheck.lazy(provider21), DoubleCheck.lazy(provider22), DoubleCheck.lazy(provider23), DoubleCheck.lazy(provider24), DoubleCheck.lazy(provider25), DoubleCheck.lazy(provider26), DoubleCheck.lazy(provider27));
    }

    public static ComponentBinder proxyProvideComponentFactories(ServiceModule serviceModule, Lazy<EnvironmentService> lazy, Lazy<FeatureQuery> lazy2, Lazy<IdentityService> lazy3, Lazy<PersonIdProvider> lazy4, Lazy<MainActivityLifecycleService> lazy5, Lazy<DefaultApplicationLifecycleService> lazy6, Lazy<MarketplaceService> lazy7, Lazy<com.amazon.alexa.protocols.marketplace.MarketplaceService> lazy8, Lazy<CrashMetadata> lazy9, Lazy<CrashObserverRegistrar> lazy10, Lazy<CrashReporter> lazy11, Lazy<MetricsService> lazy12, Lazy<MetricsServiceV2> lazy13, Lazy<Mobilytics> lazy14, Lazy<ModeService> lazy15, Lazy<LocationService> lazy16, Lazy<CoralService> lazy17, Lazy<JsonConverter> lazy18, Lazy<PersistentStorage.Factory> lazy19, Lazy<RoutingService> lazy20, Lazy<TTCFService> lazy21, Lazy<CertificateReaderService> lazy22, Lazy<CommsServiceV2> lazy23, Lazy<FeatureServiceV2> lazy24, Lazy<AssetManagementService> lazy25, Lazy<DefaultElementLocalStorage> lazy26, Lazy<DialogBuilderProvider> lazy27) {
        return (ComponentBinder) Preconditions.checkNotNull(serviceModule.provideComponentFactories(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, lazy15, lazy16, lazy17, lazy18, lazy19, lazy20, lazy21, lazy22, lazy23, lazy24, lazy25, lazy26, lazy27), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ComponentBinder mo10268get() {
        return provideInstance(this.module, this.environmentServiceProvider, this.featureQueryProvider, this.identityServiceProvider, this.personIdProvider, this.mainActivityLifecycleServiceProvider, this.applicationLifecycleServiceProvider, this.marketplaceServiceProvider, this.protocolsMarketplaceServiceProvider, this.crashMetadataProvider, this.crashObserverRegistrarProvider, this.crashReporterProvider, this.metricsServiceProvider, this.metricServiceV2Provider, this.mobilyticsProvider, this.modeServiceProvider, this.locationServiceProvider, this.coralServiceProvider, this.jsonConverterProvider, this.persistentStorageFactoryProvider, this.routingServiceProvider, this.ttcfServiceProvider, this.certificateReaderServiceProvider, this.commsServiceV2Provider, this.featureServiceV2Provider, this.assetManagementServiceProvider, this.dataStoreProvider, this.dialogBuilderProvider);
    }
}
