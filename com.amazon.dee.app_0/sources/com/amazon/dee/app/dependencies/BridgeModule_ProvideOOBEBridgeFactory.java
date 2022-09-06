package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.OOBEBridge;
import com.amazon.dee.app.ui.web.WebViewDelegate;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.conversation.ConversationService;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideOOBEBridgeFactory implements Factory<OOBEBridge> {
    private final Provider<AccountService> accountServiceProvider;
    private final Provider<Activity> activityProvider;
    private final Provider<CommsServiceV2> commsServiceV2Provider;
    private final Provider<ConversationService> conversationServiceProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<HandsFreeSetup> handsFreeSetupProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final BridgeModule module;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<WebViewDelegate> webViewDelegateProvider;

    public BridgeModule_ProvideOOBEBridgeFactory(BridgeModule bridgeModule, Provider<Activity> provider, Provider<ConversationService> provider2, Provider<IdentityService> provider3, Provider<EnvironmentService> provider4, Provider<JavaScriptInjector> provider5, Provider<JavaScriptResponseQueue> provider6, Provider<WebViewDelegate> provider7, Provider<AccountService> provider8, Provider<MetricsService> provider9, Provider<RoutingService> provider10, Provider<EventBus> provider11, Provider<HandsFreeSetup> provider12, Provider<CommsServiceV2> provider13) {
        this.module = bridgeModule;
        this.activityProvider = provider;
        this.conversationServiceProvider = provider2;
        this.identityServiceProvider = provider3;
        this.environmentServiceProvider = provider4;
        this.javaScriptInjectorProvider = provider5;
        this.javaScriptResponseQueueProvider = provider6;
        this.webViewDelegateProvider = provider7;
        this.accountServiceProvider = provider8;
        this.metricsServiceProvider = provider9;
        this.routingServiceProvider = provider10;
        this.eventBusProvider = provider11;
        this.handsFreeSetupProvider = provider12;
        this.commsServiceV2Provider = provider13;
    }

    public static BridgeModule_ProvideOOBEBridgeFactory create(BridgeModule bridgeModule, Provider<Activity> provider, Provider<ConversationService> provider2, Provider<IdentityService> provider3, Provider<EnvironmentService> provider4, Provider<JavaScriptInjector> provider5, Provider<JavaScriptResponseQueue> provider6, Provider<WebViewDelegate> provider7, Provider<AccountService> provider8, Provider<MetricsService> provider9, Provider<RoutingService> provider10, Provider<EventBus> provider11, Provider<HandsFreeSetup> provider12, Provider<CommsServiceV2> provider13) {
        return new BridgeModule_ProvideOOBEBridgeFactory(bridgeModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
    }

    public static OOBEBridge provideInstance(BridgeModule bridgeModule, Provider<Activity> provider, Provider<ConversationService> provider2, Provider<IdentityService> provider3, Provider<EnvironmentService> provider4, Provider<JavaScriptInjector> provider5, Provider<JavaScriptResponseQueue> provider6, Provider<WebViewDelegate> provider7, Provider<AccountService> provider8, Provider<MetricsService> provider9, Provider<RoutingService> provider10, Provider<EventBus> provider11, Provider<HandsFreeSetup> provider12, Provider<CommsServiceV2> provider13) {
        return proxyProvideOOBEBridge(bridgeModule, provider.mo10268get(), DoubleCheck.lazy(provider2), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get(), provider12.mo10268get(), DoubleCheck.lazy(provider13));
    }

    public static OOBEBridge proxyProvideOOBEBridge(BridgeModule bridgeModule, Activity activity, Lazy<ConversationService> lazy, IdentityService identityService, EnvironmentService environmentService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, WebViewDelegate webViewDelegate, AccountService accountService, MetricsService metricsService, RoutingService routingService, EventBus eventBus, HandsFreeSetup handsFreeSetup, Lazy<CommsServiceV2> lazy2) {
        return (OOBEBridge) Preconditions.checkNotNull(bridgeModule.provideOOBEBridge(activity, lazy, identityService, environmentService, javaScriptInjector, javaScriptResponseQueue, webViewDelegate, accountService, metricsService, routingService, eventBus, handsFreeSetup, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public OOBEBridge mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.conversationServiceProvider, this.identityServiceProvider, this.environmentServiceProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider, this.webViewDelegateProvider, this.accountServiceProvider, this.metricsServiceProvider, this.routingServiceProvider, this.eventBusProvider, this.handsFreeSetupProvider, this.commsServiceV2Provider);
    }
}
