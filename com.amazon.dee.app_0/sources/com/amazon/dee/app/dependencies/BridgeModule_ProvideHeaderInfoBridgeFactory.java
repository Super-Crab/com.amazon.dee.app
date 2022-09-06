package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.header.HeaderCacheService;
import com.amazon.dee.app.ui.web.HeaderInfoBridge;
import com.amazon.dee.app.ui.web.JavaScriptDelegate;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideHeaderInfoBridgeFactory implements Factory<HeaderInfoBridge> {
    private final Provider<HeaderCacheService> headerCacheServiceProvider;
    private final Provider<JavaScriptDelegate> javaScriptDelegateProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideHeaderInfoBridgeFactory(BridgeModule bridgeModule, Provider<JavaScriptDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3, Provider<HeaderCacheService> provider4) {
        this.module = bridgeModule;
        this.javaScriptDelegateProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.javaScriptResponseQueueProvider = provider3;
        this.headerCacheServiceProvider = provider4;
    }

    public static BridgeModule_ProvideHeaderInfoBridgeFactory create(BridgeModule bridgeModule, Provider<JavaScriptDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3, Provider<HeaderCacheService> provider4) {
        return new BridgeModule_ProvideHeaderInfoBridgeFactory(bridgeModule, provider, provider2, provider3, provider4);
    }

    public static HeaderInfoBridge provideInstance(BridgeModule bridgeModule, Provider<JavaScriptDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3, Provider<HeaderCacheService> provider4) {
        return proxyProvideHeaderInfoBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static HeaderInfoBridge proxyProvideHeaderInfoBridge(BridgeModule bridgeModule, JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, HeaderCacheService headerCacheService) {
        return (HeaderInfoBridge) Preconditions.checkNotNull(bridgeModule.provideHeaderInfoBridge(javaScriptDelegate, javaScriptInjector, javaScriptResponseQueue, headerCacheService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HeaderInfoBridge mo10268get() {
        return provideInstance(this.module, this.javaScriptDelegateProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider, this.headerCacheServiceProvider);
    }
}
