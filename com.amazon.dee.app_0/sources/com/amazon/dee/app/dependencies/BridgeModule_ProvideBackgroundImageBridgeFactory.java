package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideBackgroundImageBridgeFactory implements Factory<AlexaDeviceBackgroundImageBridge> {
    private final Provider<Activity> activityProvider;
    private final Provider<Gson> gsonProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideBackgroundImageBridgeFactory(BridgeModule bridgeModule, Provider<Activity> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3, Provider<Gson> provider4) {
        this.module = bridgeModule;
        this.activityProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.javaScriptResponseQueueProvider = provider3;
        this.gsonProvider = provider4;
    }

    public static BridgeModule_ProvideBackgroundImageBridgeFactory create(BridgeModule bridgeModule, Provider<Activity> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3, Provider<Gson> provider4) {
        return new BridgeModule_ProvideBackgroundImageBridgeFactory(bridgeModule, provider, provider2, provider3, provider4);
    }

    public static AlexaDeviceBackgroundImageBridge provideInstance(BridgeModule bridgeModule, Provider<Activity> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3, Provider<Gson> provider4) {
        return proxyProvideBackgroundImageBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static AlexaDeviceBackgroundImageBridge proxyProvideBackgroundImageBridge(BridgeModule bridgeModule, Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, Gson gson) {
        return (AlexaDeviceBackgroundImageBridge) Preconditions.checkNotNull(bridgeModule.provideBackgroundImageBridge(activity, javaScriptInjector, javaScriptResponseQueue, gson), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaDeviceBackgroundImageBridge mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider, this.gsonProvider);
    }
}
