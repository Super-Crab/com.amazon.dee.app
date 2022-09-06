package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.NavigationBridge;
import com.amazon.dee.app.ui.web.WebViewDelegate;
import com.amazon.deecomms.api.CommsServiceV2;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideNavigationBridgeFactory implements Factory<NavigationBridge> {
    private final Provider<CommsServiceV2> commsServiceV2Provider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<WebViewDelegate> webViewDelegateProvider;

    public BridgeModule_ProvideNavigationBridgeFactory(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<RoutingService> provider3, Provider<WebViewDelegate> provider4, Provider<IdentityService> provider5, Provider<CommsServiceV2> provider6) {
        this.module = bridgeModule;
        this.javaScriptInjectorProvider = provider;
        this.javaScriptResponseQueueProvider = provider2;
        this.routingServiceProvider = provider3;
        this.webViewDelegateProvider = provider4;
        this.identityServiceProvider = provider5;
        this.commsServiceV2Provider = provider6;
    }

    public static BridgeModule_ProvideNavigationBridgeFactory create(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<RoutingService> provider3, Provider<WebViewDelegate> provider4, Provider<IdentityService> provider5, Provider<CommsServiceV2> provider6) {
        return new BridgeModule_ProvideNavigationBridgeFactory(bridgeModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static NavigationBridge provideInstance(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<RoutingService> provider3, Provider<WebViewDelegate> provider4, Provider<IdentityService> provider5, Provider<CommsServiceV2> provider6) {
        return proxyProvideNavigationBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), DoubleCheck.lazy(provider6));
    }

    public static NavigationBridge proxyProvideNavigationBridge(BridgeModule bridgeModule, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, RoutingService routingService, WebViewDelegate webViewDelegate, IdentityService identityService, Lazy<CommsServiceV2> lazy) {
        return (NavigationBridge) Preconditions.checkNotNull(bridgeModule.provideNavigationBridge(javaScriptInjector, javaScriptResponseQueue, routingService, webViewDelegate, identityService, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NavigationBridge mo10268get() {
        return provideInstance(this.module, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider, this.routingServiceProvider, this.webViewDelegateProvider, this.identityServiceProvider, this.commsServiceV2Provider);
    }
}
