package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.ui.web.AccountManagementBridge;
import com.amazon.dee.app.ui.web.JavaScriptDelegate;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.WebViewDelegate;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.dee.app.metrics.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideAccountManagementBridgeFactory implements Factory<AccountManagementBridge> {
    private final Provider<AccountService> accountServiceProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<JavaScriptDelegate> javaScriptDelegateProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final Provider<MAPAccountManager> mapAccountManagerProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final BridgeModule module;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<WebViewDelegate> webViewDelegateProvider;

    public BridgeModule_ProvideAccountManagementBridgeFactory(BridgeModule bridgeModule, Provider<MAPAccountManager> provider, Provider<AccountService> provider2, Provider<JavaScriptInjector> provider3, Provider<JavaScriptResponseQueue> provider4, Provider<WebViewDelegate> provider5, Provider<RoutingService> provider6, Provider<IdentityService> provider7, Provider<JavaScriptDelegate> provider8, Provider<MetricsService> provider9) {
        this.module = bridgeModule;
        this.mapAccountManagerProvider = provider;
        this.accountServiceProvider = provider2;
        this.javaScriptInjectorProvider = provider3;
        this.javaScriptResponseQueueProvider = provider4;
        this.webViewDelegateProvider = provider5;
        this.routingServiceProvider = provider6;
        this.identityServiceProvider = provider7;
        this.javaScriptDelegateProvider = provider8;
        this.metricsServiceProvider = provider9;
    }

    public static BridgeModule_ProvideAccountManagementBridgeFactory create(BridgeModule bridgeModule, Provider<MAPAccountManager> provider, Provider<AccountService> provider2, Provider<JavaScriptInjector> provider3, Provider<JavaScriptResponseQueue> provider4, Provider<WebViewDelegate> provider5, Provider<RoutingService> provider6, Provider<IdentityService> provider7, Provider<JavaScriptDelegate> provider8, Provider<MetricsService> provider9) {
        return new BridgeModule_ProvideAccountManagementBridgeFactory(bridgeModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static AccountManagementBridge provideInstance(BridgeModule bridgeModule, Provider<MAPAccountManager> provider, Provider<AccountService> provider2, Provider<JavaScriptInjector> provider3, Provider<JavaScriptResponseQueue> provider4, Provider<WebViewDelegate> provider5, Provider<RoutingService> provider6, Provider<IdentityService> provider7, Provider<JavaScriptDelegate> provider8, Provider<MetricsService> provider9) {
        return proxyProvideAccountManagementBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get());
    }

    public static AccountManagementBridge proxyProvideAccountManagementBridge(BridgeModule bridgeModule, MAPAccountManager mAPAccountManager, AccountService accountService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, WebViewDelegate webViewDelegate, RoutingService routingService, IdentityService identityService, JavaScriptDelegate javaScriptDelegate, MetricsService metricsService) {
        return (AccountManagementBridge) Preconditions.checkNotNull(bridgeModule.provideAccountManagementBridge(mAPAccountManager, accountService, javaScriptInjector, javaScriptResponseQueue, webViewDelegate, routingService, identityService, javaScriptDelegate, metricsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccountManagementBridge mo10268get() {
        return provideInstance(this.module, this.mapAccountManagerProvider, this.accountServiceProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider, this.webViewDelegateProvider, this.routingServiceProvider, this.identityServiceProvider, this.javaScriptDelegateProvider, this.metricsServiceProvider);
    }
}
