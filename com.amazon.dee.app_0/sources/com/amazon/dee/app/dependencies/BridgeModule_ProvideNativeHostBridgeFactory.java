package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.JavaScriptDelegate;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.NativeHostBridge;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideNativeHostBridgeFactory implements Factory<NativeHostBridge> {
    private final Provider<JavaScriptDelegate> javaScriptDelegateProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideNativeHostBridgeFactory(BridgeModule bridgeModule, Provider<JavaScriptDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        this.module = bridgeModule;
        this.javaScriptDelegateProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.javaScriptResponseQueueProvider = provider3;
    }

    public static BridgeModule_ProvideNativeHostBridgeFactory create(BridgeModule bridgeModule, Provider<JavaScriptDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return new BridgeModule_ProvideNativeHostBridgeFactory(bridgeModule, provider, provider2, provider3);
    }

    public static NativeHostBridge provideInstance(BridgeModule bridgeModule, Provider<JavaScriptDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return proxyProvideNativeHostBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static NativeHostBridge proxyProvideNativeHostBridge(BridgeModule bridgeModule, JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (NativeHostBridge) Preconditions.checkNotNull(bridgeModule.provideNativeHostBridge(javaScriptDelegate, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NativeHostBridge mo10268get() {
        return provideInstance(this.module, this.javaScriptDelegateProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
