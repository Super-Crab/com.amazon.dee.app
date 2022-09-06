package com.amazon.dee.app.dependencies;

import android.app.Activity;
import android.content.Context;
import com.amazon.dee.app.ui.web.AppLauncherBridge;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideAppLauncherBridgeFactory implements Factory<AppLauncherBridge> {
    private final Provider<Activity> activityProvider;
    private final Provider<Context> contextProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideAppLauncherBridgeFactory(BridgeModule bridgeModule, Provider<Context> provider, Provider<Activity> provider2, Provider<JavaScriptInjector> provider3, Provider<JavaScriptResponseQueue> provider4) {
        this.module = bridgeModule;
        this.contextProvider = provider;
        this.activityProvider = provider2;
        this.javaScriptInjectorProvider = provider3;
        this.javaScriptResponseQueueProvider = provider4;
    }

    public static BridgeModule_ProvideAppLauncherBridgeFactory create(BridgeModule bridgeModule, Provider<Context> provider, Provider<Activity> provider2, Provider<JavaScriptInjector> provider3, Provider<JavaScriptResponseQueue> provider4) {
        return new BridgeModule_ProvideAppLauncherBridgeFactory(bridgeModule, provider, provider2, provider3, provider4);
    }

    public static AppLauncherBridge provideInstance(BridgeModule bridgeModule, Provider<Context> provider, Provider<Activity> provider2, Provider<JavaScriptInjector> provider3, Provider<JavaScriptResponseQueue> provider4) {
        return proxyProvideAppLauncherBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static AppLauncherBridge proxyProvideAppLauncherBridge(BridgeModule bridgeModule, Context context, Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (AppLauncherBridge) Preconditions.checkNotNull(bridgeModule.provideAppLauncherBridge(context, activity, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AppLauncherBridge mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.activityProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
