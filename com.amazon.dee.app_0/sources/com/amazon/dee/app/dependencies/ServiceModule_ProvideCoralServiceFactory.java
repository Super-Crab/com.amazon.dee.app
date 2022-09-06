package com.amazon.dee.app.dependencies;

import android.webkit.CookieManager;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.dee.app.http.CoralService;
import com.dee.app.http.UrlResolver;
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
public final class ServiceModule_ProvideCoralServiceFactory implements Factory<CoralService> {
    private final Provider<AccountService> accountServiceProvider;
    private final Provider<CookieManager> cookieManagerProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<FeatureServiceV2> featureServiceV2LazyProvider;
    private final Provider<Gson> gsonProvider;
    private final Provider<OkHttpClient> httpClientProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final Provider<Mobilytics> mobilyticsLazyProvider;
    private final ServiceModule module;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<UrlResolver> urlResolverProvider;

    public ServiceModule_ProvideCoralServiceFactory(ServiceModule serviceModule, Provider<CookieManager> provider, Provider<OkHttpClient> provider2, Provider<Gson> provider3, Provider<UrlResolver> provider4, Provider<IdentityService> provider5, Provider<AccountService> provider6, Provider<RoutingService> provider7, Provider<MetricsServiceV2> provider8, Provider<MetricsService> provider9, Provider<Mobilytics> provider10, Provider<FeatureServiceV2> provider11, Provider<EnvironmentService> provider12) {
        this.module = serviceModule;
        this.cookieManagerProvider = provider;
        this.httpClientProvider = provider2;
        this.gsonProvider = provider3;
        this.urlResolverProvider = provider4;
        this.identityServiceProvider = provider5;
        this.accountServiceProvider = provider6;
        this.routingServiceProvider = provider7;
        this.metricsServiceV2Provider = provider8;
        this.metricsServiceProvider = provider9;
        this.mobilyticsLazyProvider = provider10;
        this.featureServiceV2LazyProvider = provider11;
        this.environmentServiceProvider = provider12;
    }

    public static ServiceModule_ProvideCoralServiceFactory create(ServiceModule serviceModule, Provider<CookieManager> provider, Provider<OkHttpClient> provider2, Provider<Gson> provider3, Provider<UrlResolver> provider4, Provider<IdentityService> provider5, Provider<AccountService> provider6, Provider<RoutingService> provider7, Provider<MetricsServiceV2> provider8, Provider<MetricsService> provider9, Provider<Mobilytics> provider10, Provider<FeatureServiceV2> provider11, Provider<EnvironmentService> provider12) {
        return new ServiceModule_ProvideCoralServiceFactory(serviceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }

    public static CoralService provideInstance(ServiceModule serviceModule, Provider<CookieManager> provider, Provider<OkHttpClient> provider2, Provider<Gson> provider3, Provider<UrlResolver> provider4, Provider<IdentityService> provider5, Provider<AccountService> provider6, Provider<RoutingService> provider7, Provider<MetricsServiceV2> provider8, Provider<MetricsService> provider9, Provider<Mobilytics> provider10, Provider<FeatureServiceV2> provider11, Provider<EnvironmentService> provider12) {
        return proxyProvideCoralService(serviceModule, DoubleCheck.lazy(provider), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), provider8.mo10268get(), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12));
    }

    public static CoralService proxyProvideCoralService(ServiceModule serviceModule, Lazy<CookieManager> lazy, OkHttpClient okHttpClient, Gson gson, UrlResolver urlResolver, Lazy<IdentityService> lazy2, Lazy<AccountService> lazy3, Lazy<RoutingService> lazy4, MetricsServiceV2 metricsServiceV2, Lazy<MetricsService> lazy5, Lazy<Mobilytics> lazy6, Lazy<FeatureServiceV2> lazy7, Lazy<EnvironmentService> lazy8) {
        return (CoralService) Preconditions.checkNotNull(serviceModule.provideCoralService(lazy, okHttpClient, gson, urlResolver, lazy2, lazy3, lazy4, metricsServiceV2, lazy5, lazy6, lazy7, lazy8), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CoralService mo10268get() {
        return provideInstance(this.module, this.cookieManagerProvider, this.httpClientProvider, this.gsonProvider, this.urlResolverProvider, this.identityServiceProvider, this.accountServiceProvider, this.routingServiceProvider, this.metricsServiceV2Provider, this.metricsServiceProvider, this.mobilyticsLazyProvider, this.featureServiceV2LazyProvider, this.environmentServiceProvider);
    }
}
