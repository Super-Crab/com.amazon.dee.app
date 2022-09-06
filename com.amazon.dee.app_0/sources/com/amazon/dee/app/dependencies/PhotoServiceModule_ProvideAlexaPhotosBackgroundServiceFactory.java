package com.amazon.dee.app.dependencies;

import android.content.Context;
import android.webkit.CookieManager;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.services.photos.AlexaPhotosBackgroundService;
import com.amazon.dee.app.services.photos.AlexaPhotosBackgroundServiceUrlResolver;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsServiceV2;
import com.google.gson.Gson;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes12.dex */
public final class PhotoServiceModule_ProvideAlexaPhotosBackgroundServiceFactory implements Factory<AlexaPhotosBackgroundService> {
    private final Provider<AccountService> accountServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CookieManager> cookieManagerProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<FeatureServiceV2> featureServiceV2LazyProvider;
    private final Provider<Gson> gsonProvider;
    private final Provider<OkHttpClient> httpClientProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final Provider<Mobilytics> mobilyticsLazyProvider;
    private final PhotoServiceModule module;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<AlexaPhotosBackgroundServiceUrlResolver> urlResolverProvider;

    public PhotoServiceModule_ProvideAlexaPhotosBackgroundServiceFactory(PhotoServiceModule photoServiceModule, Provider<Context> provider, Provider<CookieManager> provider2, Provider<OkHttpClient> provider3, Provider<Gson> provider4, Provider<IdentityService> provider5, Provider<AccountService> provider6, Provider<RoutingService> provider7, Provider<AlexaPhotosBackgroundServiceUrlResolver> provider8, Provider<MetricsService> provider9, Provider<MetricsServiceV2> provider10, Provider<Mobilytics> provider11, Provider<FeatureServiceV2> provider12, Provider<EnvironmentService> provider13) {
        this.module = photoServiceModule;
        this.contextProvider = provider;
        this.cookieManagerProvider = provider2;
        this.httpClientProvider = provider3;
        this.gsonProvider = provider4;
        this.identityServiceProvider = provider5;
        this.accountServiceProvider = provider6;
        this.routingServiceProvider = provider7;
        this.urlResolverProvider = provider8;
        this.metricsServiceProvider = provider9;
        this.metricsServiceV2Provider = provider10;
        this.mobilyticsLazyProvider = provider11;
        this.featureServiceV2LazyProvider = provider12;
        this.environmentServiceProvider = provider13;
    }

    public static PhotoServiceModule_ProvideAlexaPhotosBackgroundServiceFactory create(PhotoServiceModule photoServiceModule, Provider<Context> provider, Provider<CookieManager> provider2, Provider<OkHttpClient> provider3, Provider<Gson> provider4, Provider<IdentityService> provider5, Provider<AccountService> provider6, Provider<RoutingService> provider7, Provider<AlexaPhotosBackgroundServiceUrlResolver> provider8, Provider<MetricsService> provider9, Provider<MetricsServiceV2> provider10, Provider<Mobilytics> provider11, Provider<FeatureServiceV2> provider12, Provider<EnvironmentService> provider13) {
        return new PhotoServiceModule_ProvideAlexaPhotosBackgroundServiceFactory(photoServiceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
    }

    public static AlexaPhotosBackgroundService provideInstance(PhotoServiceModule photoServiceModule, Provider<Context> provider, Provider<CookieManager> provider2, Provider<OkHttpClient> provider3, Provider<Gson> provider4, Provider<IdentityService> provider5, Provider<AccountService> provider6, Provider<RoutingService> provider7, Provider<AlexaPhotosBackgroundServiceUrlResolver> provider8, Provider<MetricsService> provider9, Provider<MetricsServiceV2> provider10, Provider<Mobilytics> provider11, Provider<FeatureServiceV2> provider12, Provider<EnvironmentService> provider13) {
        return proxyProvideAlexaPhotosBackgroundService(photoServiceModule, provider.mo10268get(), DoubleCheck.lazy(provider2), provider3.mo10268get(), provider4.mo10268get(), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), provider8.mo10268get(), DoubleCheck.lazy(provider9), provider10.mo10268get(), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12), DoubleCheck.lazy(provider13));
    }

    public static AlexaPhotosBackgroundService proxyProvideAlexaPhotosBackgroundService(PhotoServiceModule photoServiceModule, Context context, Lazy<CookieManager> lazy, OkHttpClient okHttpClient, Gson gson, Lazy<IdentityService> lazy2, Lazy<AccountService> lazy3, Lazy<RoutingService> lazy4, AlexaPhotosBackgroundServiceUrlResolver alexaPhotosBackgroundServiceUrlResolver, Lazy<MetricsService> lazy5, MetricsServiceV2 metricsServiceV2, Lazy<Mobilytics> lazy6, Lazy<FeatureServiceV2> lazy7, Lazy<EnvironmentService> lazy8) {
        return (AlexaPhotosBackgroundService) Preconditions.checkNotNull(photoServiceModule.provideAlexaPhotosBackgroundService(context, lazy, okHttpClient, gson, lazy2, lazy3, lazy4, alexaPhotosBackgroundServiceUrlResolver, lazy5, metricsServiceV2, lazy6, lazy7, lazy8), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaPhotosBackgroundService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.cookieManagerProvider, this.httpClientProvider, this.gsonProvider, this.identityServiceProvider, this.accountServiceProvider, this.routingServiceProvider, this.urlResolverProvider, this.metricsServiceProvider, this.metricsServiceV2Provider, this.mobilyticsLazyProvider, this.featureServiceV2LazyProvider, this.environmentServiceProvider);
    }
}
