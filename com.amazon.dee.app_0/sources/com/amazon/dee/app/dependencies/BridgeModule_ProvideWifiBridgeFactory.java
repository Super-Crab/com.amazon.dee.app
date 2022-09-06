package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.dee.app.services.wifi.WifiService;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.WifiBridge;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideWifiBridgeFactory implements Factory<WifiBridge> {
    private final Provider<Activity> activityProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;
    private final Provider<WifiService> wifiServiceProvider;

    public BridgeModule_ProvideWifiBridgeFactory(BridgeModule bridgeModule, Provider<Activity> provider, Provider<WifiService> provider2, Provider<JavaScriptInjector> provider3, Provider<JavaScriptResponseQueue> provider4) {
        this.module = bridgeModule;
        this.activityProvider = provider;
        this.wifiServiceProvider = provider2;
        this.javaScriptInjectorProvider = provider3;
        this.javaScriptResponseQueueProvider = provider4;
    }

    public static BridgeModule_ProvideWifiBridgeFactory create(BridgeModule bridgeModule, Provider<Activity> provider, Provider<WifiService> provider2, Provider<JavaScriptInjector> provider3, Provider<JavaScriptResponseQueue> provider4) {
        return new BridgeModule_ProvideWifiBridgeFactory(bridgeModule, provider, provider2, provider3, provider4);
    }

    public static WifiBridge provideInstance(BridgeModule bridgeModule, Provider<Activity> provider, Provider<WifiService> provider2, Provider<JavaScriptInjector> provider3, Provider<JavaScriptResponseQueue> provider4) {
        return proxyProvideWifiBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static WifiBridge proxyProvideWifiBridge(BridgeModule bridgeModule, Activity activity, WifiService wifiService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (WifiBridge) Preconditions.checkNotNull(bridgeModule.provideWifiBridge(activity, wifiService, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WifiBridge mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.wifiServiceProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
