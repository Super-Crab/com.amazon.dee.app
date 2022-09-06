package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.dee.app.services.wifi.WifiService;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.NetworkEventBridge;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideNetworkEventBridgeFactory implements Factory<NetworkEventBridge> {
    private final Provider<Context> contextProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;
    private final Provider<NetworkService> networkServiceProvider;
    private final Provider<WifiService> wifiServiceProvider;

    public BridgeModule_ProvideNetworkEventBridgeFactory(BridgeModule bridgeModule, Provider<Context> provider, Provider<NetworkService> provider2, Provider<WifiService> provider3, Provider<JavaScriptInjector> provider4, Provider<JavaScriptResponseQueue> provider5) {
        this.module = bridgeModule;
        this.contextProvider = provider;
        this.networkServiceProvider = provider2;
        this.wifiServiceProvider = provider3;
        this.javaScriptInjectorProvider = provider4;
        this.javaScriptResponseQueueProvider = provider5;
    }

    public static BridgeModule_ProvideNetworkEventBridgeFactory create(BridgeModule bridgeModule, Provider<Context> provider, Provider<NetworkService> provider2, Provider<WifiService> provider3, Provider<JavaScriptInjector> provider4, Provider<JavaScriptResponseQueue> provider5) {
        return new BridgeModule_ProvideNetworkEventBridgeFactory(bridgeModule, provider, provider2, provider3, provider4, provider5);
    }

    public static NetworkEventBridge provideInstance(BridgeModule bridgeModule, Provider<Context> provider, Provider<NetworkService> provider2, Provider<WifiService> provider3, Provider<JavaScriptInjector> provider4, Provider<JavaScriptResponseQueue> provider5) {
        return proxyProvideNetworkEventBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static NetworkEventBridge proxyProvideNetworkEventBridge(BridgeModule bridgeModule, Context context, NetworkService networkService, WifiService wifiService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (NetworkEventBridge) Preconditions.checkNotNull(bridgeModule.provideNetworkEventBridge(context, networkService, wifiService, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NetworkEventBridge mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.networkServiceProvider, this.wifiServiceProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
