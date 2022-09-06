package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.datastore.DataStoreService;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.NativeLocalStorageBridge;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideNativeLocalStorageBridgeFactory implements Factory<NativeLocalStorageBridge> {
    private final Provider<DataStoreService> dataStoreServiceProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideNativeLocalStorageBridgeFactory(BridgeModule bridgeModule, Provider<DataStoreService> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        this.module = bridgeModule;
        this.dataStoreServiceProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.javaScriptResponseQueueProvider = provider3;
    }

    public static BridgeModule_ProvideNativeLocalStorageBridgeFactory create(BridgeModule bridgeModule, Provider<DataStoreService> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return new BridgeModule_ProvideNativeLocalStorageBridgeFactory(bridgeModule, provider, provider2, provider3);
    }

    public static NativeLocalStorageBridge provideInstance(BridgeModule bridgeModule, Provider<DataStoreService> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return proxyProvideNativeLocalStorageBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static NativeLocalStorageBridge proxyProvideNativeLocalStorageBridge(BridgeModule bridgeModule, DataStoreService dataStoreService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (NativeLocalStorageBridge) Preconditions.checkNotNull(bridgeModule.provideNativeLocalStorageBridge(dataStoreService, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NativeLocalStorageBridge mo10268get() {
        return provideInstance(this.module, this.dataStoreServiceProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
