package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.AppReloadBridge;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.WebViewDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideAppReloadBridgeFactory implements Factory<AppReloadBridge> {
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;
    private final Provider<WebViewDelegate> webViewDelegateProvider;

    public BridgeModule_ProvideAppReloadBridgeFactory(BridgeModule bridgeModule, Provider<WebViewDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        this.module = bridgeModule;
        this.webViewDelegateProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.javaScriptResponseQueueProvider = provider3;
    }

    public static BridgeModule_ProvideAppReloadBridgeFactory create(BridgeModule bridgeModule, Provider<WebViewDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return new BridgeModule_ProvideAppReloadBridgeFactory(bridgeModule, provider, provider2, provider3);
    }

    public static AppReloadBridge provideInstance(BridgeModule bridgeModule, Provider<WebViewDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return proxyProvideAppReloadBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static AppReloadBridge proxyProvideAppReloadBridge(BridgeModule bridgeModule, WebViewDelegate webViewDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (AppReloadBridge) Preconditions.checkNotNull(bridgeModule.provideAppReloadBridge(webViewDelegate, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AppReloadBridge mo10268get() {
        return provideInstance(this.module, this.webViewDelegateProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
