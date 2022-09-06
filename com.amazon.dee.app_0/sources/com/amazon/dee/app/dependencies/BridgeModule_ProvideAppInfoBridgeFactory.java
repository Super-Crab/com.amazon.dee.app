package com.amazon.dee.app.dependencies;

import android.app.Activity;
import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.dee.app.ui.web.AppInfoBridge;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideAppInfoBridgeFactory implements Factory<AppInfoBridge> {
    private final Provider<Activity> activityProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideAppInfoBridgeFactory(BridgeModule bridgeModule, Provider<Context> provider, Provider<Activity> provider2, Provider<DeviceInformation> provider3, Provider<JavaScriptInjector> provider4, Provider<JavaScriptResponseQueue> provider5) {
        this.module = bridgeModule;
        this.contextProvider = provider;
        this.activityProvider = provider2;
        this.deviceInformationProvider = provider3;
        this.javaScriptInjectorProvider = provider4;
        this.javaScriptResponseQueueProvider = provider5;
    }

    public static BridgeModule_ProvideAppInfoBridgeFactory create(BridgeModule bridgeModule, Provider<Context> provider, Provider<Activity> provider2, Provider<DeviceInformation> provider3, Provider<JavaScriptInjector> provider4, Provider<JavaScriptResponseQueue> provider5) {
        return new BridgeModule_ProvideAppInfoBridgeFactory(bridgeModule, provider, provider2, provider3, provider4, provider5);
    }

    public static AppInfoBridge provideInstance(BridgeModule bridgeModule, Provider<Context> provider, Provider<Activity> provider2, Provider<DeviceInformation> provider3, Provider<JavaScriptInjector> provider4, Provider<JavaScriptResponseQueue> provider5) {
        return proxyProvideAppInfoBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static AppInfoBridge proxyProvideAppInfoBridge(BridgeModule bridgeModule, Context context, Activity activity, DeviceInformation deviceInformation, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (AppInfoBridge) Preconditions.checkNotNull(bridgeModule.provideAppInfoBridge(context, activity, deviceInformation, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AppInfoBridge mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.activityProvider, this.deviceInformationProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
