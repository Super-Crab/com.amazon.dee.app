package com.amazon.dee.app.dependencies;

import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.services.metrics.DCMMetricsConnector;
import com.amazon.dee.app.services.metrics.KinesisMetricsConnector;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideMetricsServiceFactory implements Factory<MetricsService> {
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<DCMMetricsConnector> dcmMetricsConnectorLazyProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<FeatureQuery> featureQueryLazyProvider;
    private final Provider<KinesisMetricsConnector> kinesisMetricsConnectorLazyProvider;
    private final Provider<MobilyticsEventFactory> mobilyticsEventFactoryProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final ServiceModule module;
    private final Provider<PreloadAttributionManager> preloadAttributionManagerLazyProvider;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;

    public ServiceModule_ProvideMetricsServiceFactory(ServiceModule serviceModule, Provider<EnvironmentService> provider, Provider<PersistentStorage.Factory> provider2, Provider<CrashMetadata> provider3, Provider<PreloadAttributionManager> provider4, Provider<DCMMetricsConnector> provider5, Provider<KinesisMetricsConnector> provider6, Provider<Mobilytics> provider7, Provider<MobilyticsEventFactory> provider8, Provider<FeatureQuery> provider9) {
        this.module = serviceModule;
        this.environmentServiceProvider = provider;
        this.storageFactoryProvider = provider2;
        this.crashMetadataProvider = provider3;
        this.preloadAttributionManagerLazyProvider = provider4;
        this.dcmMetricsConnectorLazyProvider = provider5;
        this.kinesisMetricsConnectorLazyProvider = provider6;
        this.mobilyticsProvider = provider7;
        this.mobilyticsEventFactoryProvider = provider8;
        this.featureQueryLazyProvider = provider9;
    }

    public static ServiceModule_ProvideMetricsServiceFactory create(ServiceModule serviceModule, Provider<EnvironmentService> provider, Provider<PersistentStorage.Factory> provider2, Provider<CrashMetadata> provider3, Provider<PreloadAttributionManager> provider4, Provider<DCMMetricsConnector> provider5, Provider<KinesisMetricsConnector> provider6, Provider<Mobilytics> provider7, Provider<MobilyticsEventFactory> provider8, Provider<FeatureQuery> provider9) {
        return new ServiceModule_ProvideMetricsServiceFactory(serviceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static MetricsService provideInstance(ServiceModule serviceModule, Provider<EnvironmentService> provider, Provider<PersistentStorage.Factory> provider2, Provider<CrashMetadata> provider3, Provider<PreloadAttributionManager> provider4, Provider<DCMMetricsConnector> provider5, Provider<KinesisMetricsConnector> provider6, Provider<Mobilytics> provider7, Provider<MobilyticsEventFactory> provider8, Provider<FeatureQuery> provider9) {
        return proxyProvideMetricsService(serviceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9));
    }

    public static MetricsService proxyProvideMetricsService(ServiceModule serviceModule, EnvironmentService environmentService, PersistentStorage.Factory factory, CrashMetadata crashMetadata, Lazy<PreloadAttributionManager> lazy, Lazy<DCMMetricsConnector> lazy2, Lazy<KinesisMetricsConnector> lazy3, Lazy<Mobilytics> lazy4, Lazy<MobilyticsEventFactory> lazy5, Lazy<FeatureQuery> lazy6) {
        return (MetricsService) Preconditions.checkNotNull(serviceModule.provideMetricsService(environmentService, factory, crashMetadata, lazy, lazy2, lazy3, lazy4, lazy5, lazy6), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsService mo10268get() {
        return provideInstance(this.module, this.environmentServiceProvider, this.storageFactoryProvider, this.crashMetadataProvider, this.preloadAttributionManagerLazyProvider, this.dcmMetricsConnectorLazyProvider, this.kinesisMetricsConnectorLazyProvider, this.mobilyticsProvider, this.mobilyticsEventFactoryProvider, this.featureQueryLazyProvider);
    }
}
