package com.amazon.dee.app.dependencies;

import android.app.Application;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.elements.AlexaNativeModuleCallExceptionHandler;
import com.amazon.dee.app.elements.ReactBridgeService;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.dee.app.metrics.MetricsServiceV2;
import com.facebook.react.ReactPackage;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideReactBridgeServiceFactory implements Factory<ReactBridgeService> {
    private final Provider<AlexaNativeModuleCallExceptionHandler> alexaNativeModuleCallExceptionHandlerProvider;
    private final Provider<Application> applicationProvider;
    private final Provider<MetricsServiceV2> metricsProvider;
    private final ElementsModule module;
    private final Provider<Boolean> reactDeveloperSupportEnabledProvider;
    private final Provider<Set<ReactPackage>> reactPackagesProvider;
    private final Provider<MainActivityLifecycleService> registrarProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public ElementsModule_ProvideReactBridgeServiceFactory(ElementsModule elementsModule, Provider<Application> provider, Provider<Set<ReactPackage>> provider2, Provider<Boolean> provider3, Provider<AlexaNativeModuleCallExceptionHandler> provider4, Provider<MetricsServiceV2> provider5, Provider<RoutingService> provider6, Provider<MainActivityLifecycleService> provider7) {
        this.module = elementsModule;
        this.applicationProvider = provider;
        this.reactPackagesProvider = provider2;
        this.reactDeveloperSupportEnabledProvider = provider3;
        this.alexaNativeModuleCallExceptionHandlerProvider = provider4;
        this.metricsProvider = provider5;
        this.routingServiceProvider = provider6;
        this.registrarProvider = provider7;
    }

    public static ElementsModule_ProvideReactBridgeServiceFactory create(ElementsModule elementsModule, Provider<Application> provider, Provider<Set<ReactPackage>> provider2, Provider<Boolean> provider3, Provider<AlexaNativeModuleCallExceptionHandler> provider4, Provider<MetricsServiceV2> provider5, Provider<RoutingService> provider6, Provider<MainActivityLifecycleService> provider7) {
        return new ElementsModule_ProvideReactBridgeServiceFactory(elementsModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static ReactBridgeService provideInstance(ElementsModule elementsModule, Provider<Application> provider, Provider<Set<ReactPackage>> provider2, Provider<Boolean> provider3, Provider<AlexaNativeModuleCallExceptionHandler> provider4, Provider<MetricsServiceV2> provider5, Provider<RoutingService> provider6, Provider<MainActivityLifecycleService> provider7) {
        return proxyProvideReactBridgeService(elementsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get().booleanValue(), provider4.mo10268get(), provider5.mo10268get(), DoubleCheck.lazy(provider6), provider7.mo10268get());
    }

    public static ReactBridgeService proxyProvideReactBridgeService(ElementsModule elementsModule, Application application, Set<ReactPackage> set, boolean z, AlexaNativeModuleCallExceptionHandler alexaNativeModuleCallExceptionHandler, MetricsServiceV2 metricsServiceV2, Lazy<RoutingService> lazy, MainActivityLifecycleService mainActivityLifecycleService) {
        return (ReactBridgeService) Preconditions.checkNotNull(elementsModule.provideReactBridgeService(application, set, z, alexaNativeModuleCallExceptionHandler, metricsServiceV2, lazy, mainActivityLifecycleService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactBridgeService mo10268get() {
        return provideInstance(this.module, this.applicationProvider, this.reactPackagesProvider, this.reactDeveloperSupportEnabledProvider, this.alexaNativeModuleCallExceptionHandlerProvider, this.metricsProvider, this.routingServiceProvider, this.registrarProvider);
    }
}
