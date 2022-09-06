package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideRoutingServiceFactory implements Factory<RoutingService> {
    private final Provider<RoutingRegistryAdapter> adaptersProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final ServiceModule module;
    private final Provider<RoutingRegistry> routingRegistryProvider;

    public ServiceModule_ProvideRoutingServiceFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<RoutingRegistry> provider2, Provider<MetricsService> provider3, Provider<RoutingRegistryAdapter> provider4, Provider<CrashMetadata> provider5, Provider<FeatureServiceV2> provider6, Provider<IdentityService> provider7, Provider<EventBus> provider8) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.routingRegistryProvider = provider2;
        this.metricsServiceProvider = provider3;
        this.adaptersProvider = provider4;
        this.crashMetadataProvider = provider5;
        this.featureServiceV2Provider = provider6;
        this.identityServiceProvider = provider7;
        this.eventBusProvider = provider8;
    }

    public static ServiceModule_ProvideRoutingServiceFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<RoutingRegistry> provider2, Provider<MetricsService> provider3, Provider<RoutingRegistryAdapter> provider4, Provider<CrashMetadata> provider5, Provider<FeatureServiceV2> provider6, Provider<IdentityService> provider7, Provider<EventBus> provider8) {
        return new ServiceModule_ProvideRoutingServiceFactory(serviceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static RoutingService provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<RoutingRegistry> provider2, Provider<MetricsService> provider3, Provider<RoutingRegistryAdapter> provider4, Provider<CrashMetadata> provider5, Provider<FeatureServiceV2> provider6, Provider<IdentityService> provider7, Provider<EventBus> provider8) {
        return proxyProvideRoutingService(serviceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8));
    }

    public static RoutingService proxyProvideRoutingService(ServiceModule serviceModule, Context context, RoutingRegistry routingRegistry, MetricsService metricsService, RoutingRegistryAdapter routingRegistryAdapter, CrashMetadata crashMetadata, FeatureServiceV2 featureServiceV2, Lazy<IdentityService> lazy, Lazy<EventBus> lazy2) {
        return (RoutingService) Preconditions.checkNotNull(serviceModule.provideRoutingService(context, routingRegistry, metricsService, routingRegistryAdapter, crashMetadata, featureServiceV2, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoutingService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.routingRegistryProvider, this.metricsServiceProvider, this.adaptersProvider, this.crashMetadataProvider, this.featureServiceV2Provider, this.identityServiceProvider, this.eventBusProvider);
    }
}
