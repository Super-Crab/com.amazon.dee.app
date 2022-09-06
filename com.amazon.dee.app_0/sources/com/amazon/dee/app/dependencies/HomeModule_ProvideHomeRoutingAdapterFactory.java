package com.amazon.dee.app.dependencies;

import android.app.Activity;
import android.content.Context;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.location.LocationProvider;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.redesign.HomeViewDelegate;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.dee.app.elements.ReactBridgeService;
import com.amazon.dee.app.ui.main.MainHandler;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class HomeModule_ProvideHomeRoutingAdapterFactory implements Factory<RoutingAdapter> {
    private final Provider<Activity> activityProvider;
    private final Provider<BridgeStatusService> bridgeStatusServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<HomeViewDelegate> homeViewDelegateProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<LocationProvider> locationProvider;
    private final Provider<MainHandler> mainHandlerProvider;
    private final HomeModule module;
    private final Provider<ReactBridgeService> reactBridgeServiceProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<TTCFCheckpoint> ttcfServiceLazyProvider;
    private final Provider<UserMessageService> userMessageServiceLazyProvider;
    private final Provider<VoiceService> voiceServiceProvider;

    public HomeModule_ProvideHomeRoutingAdapterFactory(HomeModule homeModule, Provider<Context> provider, Provider<LocationProvider> provider2, Provider<ReactBridgeService> provider3, Provider<MainHandler> provider4, Provider<HomeViewDelegate> provider5, Provider<BridgeStatusService> provider6, Provider<IdentityService> provider7, Provider<RoutingService> provider8, Provider<VoiceService> provider9, Provider<TTCFCheckpoint> provider10, Provider<UserMessageService> provider11, Provider<Activity> provider12) {
        this.module = homeModule;
        this.contextProvider = provider;
        this.locationProvider = provider2;
        this.reactBridgeServiceProvider = provider3;
        this.mainHandlerProvider = provider4;
        this.homeViewDelegateProvider = provider5;
        this.bridgeStatusServiceProvider = provider6;
        this.identityServiceProvider = provider7;
        this.routingServiceProvider = provider8;
        this.voiceServiceProvider = provider9;
        this.ttcfServiceLazyProvider = provider10;
        this.userMessageServiceLazyProvider = provider11;
        this.activityProvider = provider12;
    }

    public static HomeModule_ProvideHomeRoutingAdapterFactory create(HomeModule homeModule, Provider<Context> provider, Provider<LocationProvider> provider2, Provider<ReactBridgeService> provider3, Provider<MainHandler> provider4, Provider<HomeViewDelegate> provider5, Provider<BridgeStatusService> provider6, Provider<IdentityService> provider7, Provider<RoutingService> provider8, Provider<VoiceService> provider9, Provider<TTCFCheckpoint> provider10, Provider<UserMessageService> provider11, Provider<Activity> provider12) {
        return new HomeModule_ProvideHomeRoutingAdapterFactory(homeModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }

    public static RoutingAdapter provideInstance(HomeModule homeModule, Provider<Context> provider, Provider<LocationProvider> provider2, Provider<ReactBridgeService> provider3, Provider<MainHandler> provider4, Provider<HomeViewDelegate> provider5, Provider<BridgeStatusService> provider6, Provider<IdentityService> provider7, Provider<RoutingService> provider8, Provider<VoiceService> provider9, Provider<TTCFCheckpoint> provider10, Provider<UserMessageService> provider11, Provider<Activity> provider12) {
        return proxyProvideHomeRoutingAdapter(homeModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), provider12.mo10268get());
    }

    public static RoutingAdapter proxyProvideHomeRoutingAdapter(HomeModule homeModule, Lazy<Context> lazy, Lazy<LocationProvider> lazy2, Lazy<ReactBridgeService> lazy3, Lazy<MainHandler> lazy4, Lazy<HomeViewDelegate> lazy5, Lazy<BridgeStatusService> lazy6, Lazy<IdentityService> lazy7, Lazy<RoutingService> lazy8, Lazy<VoiceService> lazy9, Lazy<TTCFCheckpoint> lazy10, Lazy<UserMessageService> lazy11, Activity activity) {
        return (RoutingAdapter) Preconditions.checkNotNull(homeModule.provideHomeRoutingAdapter(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, activity), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoutingAdapter mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.locationProvider, this.reactBridgeServiceProvider, this.mainHandlerProvider, this.homeViewDelegateProvider, this.bridgeStatusServiceProvider, this.identityServiceProvider, this.routingServiceProvider, this.voiceServiceProvider, this.ttcfServiceLazyProvider, this.userMessageServiceLazyProvider, this.activityProvider);
    }
}
