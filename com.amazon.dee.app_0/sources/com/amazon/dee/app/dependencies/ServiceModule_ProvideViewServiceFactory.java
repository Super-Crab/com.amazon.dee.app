package com.amazon.dee.app.dependencies;

import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.api.RoutingViewService;
import com.amazon.alexa.ttcf.api.TTCFRoutingDelegate;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideViewServiceFactory implements Factory<RoutingViewService> {
    private final Provider<RoutingRegistryAdapter> adaptersProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final ServiceModule module;
    private final Provider<RoutingRegistry> routingRegistryProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<TTCFRoutingDelegate> ttcfServiceLazyProvider;

    public ServiceModule_ProvideViewServiceFactory(ServiceModule serviceModule, Provider<RoutingService> provider, Provider<MetricsService> provider2, Provider<RoutingRegistryAdapter> provider3, Provider<RoutingRegistry> provider4, Provider<IdentityService> provider5, Provider<TTCFRoutingDelegate> provider6, Provider<CrashMetadata> provider7, Provider<FeatureServiceV2> provider8) {
        this.module = serviceModule;
        this.routingServiceProvider = provider;
        this.metricsServiceProvider = provider2;
        this.adaptersProvider = provider3;
        this.routingRegistryProvider = provider4;
        this.identityServiceProvider = provider5;
        this.ttcfServiceLazyProvider = provider6;
        this.crashMetadataProvider = provider7;
        this.featureServiceV2Provider = provider8;
    }

    public static ServiceModule_ProvideViewServiceFactory create(ServiceModule serviceModule, Provider<RoutingService> provider, Provider<MetricsService> provider2, Provider<RoutingRegistryAdapter> provider3, Provider<RoutingRegistry> provider4, Provider<IdentityService> provider5, Provider<TTCFRoutingDelegate> provider6, Provider<CrashMetadata> provider7, Provider<FeatureServiceV2> provider8) {
        return new ServiceModule_ProvideViewServiceFactory(serviceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static RoutingViewService provideInstance(ServiceModule serviceModule, Provider<RoutingService> provider, Provider<MetricsService> provider2, Provider<RoutingRegistryAdapter> provider3, Provider<RoutingRegistry> provider4, Provider<IdentityService> provider5, Provider<TTCFRoutingDelegate> provider6, Provider<CrashMetadata> provider7, Provider<FeatureServiceV2> provider8) {
        return proxyProvideViewService(serviceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), provider7.mo10268get(), provider8.mo10268get());
    }

    public static RoutingViewService proxyProvideViewService(ServiceModule serviceModule, RoutingService routingService, MetricsService metricsService, RoutingRegistryAdapter routingRegistryAdapter, RoutingRegistry routingRegistry, Lazy<IdentityService> lazy, Lazy<TTCFRoutingDelegate> lazy2, CrashMetadata crashMetadata, FeatureServiceV2 featureServiceV2) {
        return (RoutingViewService) Preconditions.checkNotNull(serviceModule.provideViewService(routingService, metricsService, routingRegistryAdapter, routingRegistry, lazy, lazy2, crashMetadata, featureServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoutingViewService mo10268get() {
        return provideInstance(this.module, this.routingServiceProvider, this.metricsServiceProvider, this.adaptersProvider, this.routingRegistryProvider, this.identityServiceProvider, this.ttcfServiceLazyProvider, this.crashMetadataProvider, this.featureServiceV2Provider);
    }
}
