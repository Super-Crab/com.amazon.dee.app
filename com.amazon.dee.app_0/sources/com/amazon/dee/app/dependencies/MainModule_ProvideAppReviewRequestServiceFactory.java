package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.services.appreviewrequest.AppReviewRequestService;
import com.amazon.dee.app.services.wifi.WifiService;
import com.google.android.play.core.review.ReviewManager;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideAppReviewRequestServiceFactory implements Factory<AppReviewRequestService> {
    private final Provider<Context> contextProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<FeatureServiceV2> featureServiceV2LazyProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<Mobilytics> mobilyticsLazyProvider;
    private final Provider<ModeService> modeServiceProvider;
    private final MainModule module;
    private final Provider<ReviewManager> reviewManagerProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<WifiService> wifiServiceProvider;

    public MainModule_ProvideAppReviewRequestServiceFactory(MainModule mainModule, Provider<EventBus> provider, Provider<RoutingService> provider2, Provider<IdentityService> provider3, Provider<WifiService> provider4, Provider<EnvironmentService> provider5, Provider<ModeService> provider6, Provider<Context> provider7, Provider<ReviewManager> provider8, Provider<FeatureServiceV2> provider9, Provider<Mobilytics> provider10) {
        this.module = mainModule;
        this.eventBusProvider = provider;
        this.routingServiceProvider = provider2;
        this.identityServiceProvider = provider3;
        this.wifiServiceProvider = provider4;
        this.environmentServiceProvider = provider5;
        this.modeServiceProvider = provider6;
        this.contextProvider = provider7;
        this.reviewManagerProvider = provider8;
        this.featureServiceV2LazyProvider = provider9;
        this.mobilyticsLazyProvider = provider10;
    }

    public static MainModule_ProvideAppReviewRequestServiceFactory create(MainModule mainModule, Provider<EventBus> provider, Provider<RoutingService> provider2, Provider<IdentityService> provider3, Provider<WifiService> provider4, Provider<EnvironmentService> provider5, Provider<ModeService> provider6, Provider<Context> provider7, Provider<ReviewManager> provider8, Provider<FeatureServiceV2> provider9, Provider<Mobilytics> provider10) {
        return new MainModule_ProvideAppReviewRequestServiceFactory(mainModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static AppReviewRequestService provideInstance(MainModule mainModule, Provider<EventBus> provider, Provider<RoutingService> provider2, Provider<IdentityService> provider3, Provider<WifiService> provider4, Provider<EnvironmentService> provider5, Provider<ModeService> provider6, Provider<Context> provider7, Provider<ReviewManager> provider8, Provider<FeatureServiceV2> provider9, Provider<Mobilytics> provider10) {
        return proxyProvideAppReviewRequestService(mainModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), DoubleCheck.lazy(provider6), provider7.mo10268get(), provider8.mo10268get(), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10));
    }

    public static AppReviewRequestService proxyProvideAppReviewRequestService(MainModule mainModule, EventBus eventBus, RoutingService routingService, IdentityService identityService, WifiService wifiService, EnvironmentService environmentService, Lazy<ModeService> lazy, Context context, ReviewManager reviewManager, Lazy<FeatureServiceV2> lazy2, Lazy<Mobilytics> lazy3) {
        return (AppReviewRequestService) Preconditions.checkNotNull(mainModule.provideAppReviewRequestService(eventBus, routingService, identityService, wifiService, environmentService, lazy, context, reviewManager, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AppReviewRequestService mo10268get() {
        return provideInstance(this.module, this.eventBusProvider, this.routingServiceProvider, this.identityServiceProvider, this.wifiServiceProvider, this.environmentServiceProvider, this.modeServiceProvider, this.contextProvider, this.reviewManagerProvider, this.featureServiceV2LazyProvider, this.mobilyticsLazyProvider);
    }
}
