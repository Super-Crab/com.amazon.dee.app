package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.AppCacheBridge;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideAppCacheBridgeFactory implements Factory<AppCacheBridge> {
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideAppCacheBridgeFactory(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2) {
        this.module = bridgeModule;
        this.javaScriptInjectorProvider = provider;
        this.javaScriptResponseQueueProvider = provider2;
    }

    public static BridgeModule_ProvideAppCacheBridgeFactory create(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2) {
        return new BridgeModule_ProvideAppCacheBridgeFactory(bridgeModule, provider, provider2);
    }

    public static AppCacheBridge provideInstance(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2) {
        return proxyProvideAppCacheBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static AppCacheBridge proxyProvideAppCacheBridge(BridgeModule bridgeModule, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (AppCacheBridge) Preconditions.checkNotNull(bridgeModule.provideAppCacheBridge(javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AppCacheBridge mo10268get() {
        return provideInstance(this.module, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
