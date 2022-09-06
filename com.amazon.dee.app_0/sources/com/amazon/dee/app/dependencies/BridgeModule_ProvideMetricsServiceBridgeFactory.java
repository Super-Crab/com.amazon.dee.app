package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.JavaScriptDelegate;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.MetricsServiceBridge;
import com.dee.app.metrics.MetricsService;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideMetricsServiceBridgeFactory implements Factory<MetricsServiceBridge> {
    private final Provider<Gson> gsonProvider;
    private final Provider<JavaScriptDelegate> javaScriptDelegateProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideMetricsServiceBridgeFactory(BridgeModule bridgeModule, Provider<JavaScriptDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3, Provider<MetricsService> provider4, Provider<Gson> provider5) {
        this.module = bridgeModule;
        this.javaScriptDelegateProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.javaScriptResponseQueueProvider = provider3;
        this.metricsServiceProvider = provider4;
        this.gsonProvider = provider5;
    }

    public static BridgeModule_ProvideMetricsServiceBridgeFactory create(BridgeModule bridgeModule, Provider<JavaScriptDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3, Provider<MetricsService> provider4, Provider<Gson> provider5) {
        return new BridgeModule_ProvideMetricsServiceBridgeFactory(bridgeModule, provider, provider2, provider3, provider4, provider5);
    }

    public static MetricsServiceBridge provideInstance(BridgeModule bridgeModule, Provider<JavaScriptDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3, Provider<MetricsService> provider4, Provider<Gson> provider5) {
        return proxyProvideMetricsServiceBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static MetricsServiceBridge proxyProvideMetricsServiceBridge(BridgeModule bridgeModule, JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, MetricsService metricsService, Gson gson) {
        return (MetricsServiceBridge) Preconditions.checkNotNull(bridgeModule.provideMetricsServiceBridge(javaScriptDelegate, javaScriptInjector, javaScriptResponseQueue, metricsService, gson), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsServiceBridge mo10268get() {
        return provideInstance(this.module, this.javaScriptDelegateProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider, this.metricsServiceProvider, this.gsonProvider);
    }
}
