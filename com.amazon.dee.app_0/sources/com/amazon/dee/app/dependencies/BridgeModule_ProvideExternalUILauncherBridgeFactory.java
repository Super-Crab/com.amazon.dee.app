package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.ui.web.ExternalUILauncherBridge;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.WebViewDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideExternalUILauncherBridgeFactory implements Factory<ExternalUILauncherBridge> {
    private final Provider<Activity> activityProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;
    private final Provider<WebViewDelegate> webViewDelegateProvider;

    public BridgeModule_ProvideExternalUILauncherBridgeFactory(BridgeModule bridgeModule, Provider<Activity> provider, Provider<WebViewDelegate> provider2, Provider<EnvironmentService> provider3, Provider<JavaScriptInjector> provider4, Provider<JavaScriptResponseQueue> provider5) {
        this.module = bridgeModule;
        this.activityProvider = provider;
        this.webViewDelegateProvider = provider2;
        this.environmentServiceProvider = provider3;
        this.javaScriptInjectorProvider = provider4;
        this.javaScriptResponseQueueProvider = provider5;
    }

    public static BridgeModule_ProvideExternalUILauncherBridgeFactory create(BridgeModule bridgeModule, Provider<Activity> provider, Provider<WebViewDelegate> provider2, Provider<EnvironmentService> provider3, Provider<JavaScriptInjector> provider4, Provider<JavaScriptResponseQueue> provider5) {
        return new BridgeModule_ProvideExternalUILauncherBridgeFactory(bridgeModule, provider, provider2, provider3, provider4, provider5);
    }

    public static ExternalUILauncherBridge provideInstance(BridgeModule bridgeModule, Provider<Activity> provider, Provider<WebViewDelegate> provider2, Provider<EnvironmentService> provider3, Provider<JavaScriptInjector> provider4, Provider<JavaScriptResponseQueue> provider5) {
        return proxyProvideExternalUILauncherBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static ExternalUILauncherBridge proxyProvideExternalUILauncherBridge(BridgeModule bridgeModule, Activity activity, WebViewDelegate webViewDelegate, EnvironmentService environmentService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (ExternalUILauncherBridge) Preconditions.checkNotNull(bridgeModule.provideExternalUILauncherBridge(activity, webViewDelegate, environmentService, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ExternalUILauncherBridge mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.webViewDelegateProvider, this.environmentServiceProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
