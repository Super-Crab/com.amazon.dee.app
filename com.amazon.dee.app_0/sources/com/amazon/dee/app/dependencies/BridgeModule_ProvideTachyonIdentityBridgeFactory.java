package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.TachyonIdentityBridge;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideTachyonIdentityBridgeFactory implements Factory<TachyonIdentityBridge> {
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<CommsServiceV2> commsServiceV2Provider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideTachyonIdentityBridgeFactory(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<CommsManager> provider3, Provider<CommsServiceV2> provider4) {
        this.module = bridgeModule;
        this.javaScriptInjectorProvider = provider;
        this.javaScriptResponseQueueProvider = provider2;
        this.commsManagerProvider = provider3;
        this.commsServiceV2Provider = provider4;
    }

    public static BridgeModule_ProvideTachyonIdentityBridgeFactory create(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<CommsManager> provider3, Provider<CommsServiceV2> provider4) {
        return new BridgeModule_ProvideTachyonIdentityBridgeFactory(bridgeModule, provider, provider2, provider3, provider4);
    }

    public static TachyonIdentityBridge provideInstance(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<CommsManager> provider3, Provider<CommsServiceV2> provider4) {
        return proxyProvideTachyonIdentityBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), DoubleCheck.lazy(provider4));
    }

    public static TachyonIdentityBridge proxyProvideTachyonIdentityBridge(BridgeModule bridgeModule, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, CommsManager commsManager, Lazy<CommsServiceV2> lazy) {
        return (TachyonIdentityBridge) Preconditions.checkNotNull(bridgeModule.provideTachyonIdentityBridge(javaScriptInjector, javaScriptResponseQueue, commsManager, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TachyonIdentityBridge mo10268get() {
        return provideInstance(this.module, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider, this.commsManagerProvider, this.commsServiceV2Provider);
    }
}
