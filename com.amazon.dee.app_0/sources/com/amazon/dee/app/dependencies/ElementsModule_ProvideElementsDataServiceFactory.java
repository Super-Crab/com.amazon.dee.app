package com.amazon.dee.app.dependencies;

import android.webkit.CookieManager;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.HttpResponseWrapper;
import com.dee.app.data.reactnative.ElementsDataService;
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
public final class ElementsModule_ProvideElementsDataServiceFactory implements Factory<ElementsDataService> {
    private final Provider<AccountService> accountServiceProvider;
    private final Provider<Cache<HttpResponseWrapper>> cacheProvider;
    private final Provider<CookieManager> cookieManagerProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<Gson> gsonProvider;
    private final Provider<OkHttpClient> httpClientProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final Provider<Mobilytics> mobilyticsLazyProvider;
    private final ElementsModule module;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<UrlResolver> urlResolverProvider;

    public ElementsModule_ProvideElementsDataServiceFactory(ElementsModule elementsModule, Provider<CookieManager> provider, Provider<OkHttpClient> provider2, Provider<Gson> provider3, Provider<MetricsServiceV2> provider4, Provider<UrlResolver> provider5, Provider<IdentityService> provider6, Provider<AccountService> provider7, Provider<RoutingService> provider8, Provider<Cache<HttpResponseWrapper>> provider9, Provider<MetricsService> provider10, Provider<FeatureServiceV2> provider11, Provider<EnvironmentService> provider12, Provider<Mobilytics> provider13) {
        this.module = elementsModule;
        this.cookieManagerProvider = provider;
        this.httpClientProvider = provider2;
        this.gsonProvider = provider3;
        this.metricsServiceV2Provider = provider4;
        this.urlResolverProvider = provider5;
        this.identityServiceProvider = provider6;
        this.accountServiceProvider = provider7;
        this.routingServiceProvider = provider8;
        this.cacheProvider = provider9;
        this.metricsServiceProvider = provider10;
        this.featureServiceV2Provider = provider11;
        this.environmentServiceProvider = provider12;
        this.mobilyticsLazyProvider = provider13;
    }

    public static ElementsModule_ProvideElementsDataServiceFactory create(ElementsModule elementsModule, Provider<CookieManager> provider, Provider<OkHttpClient> provider2, Provider<Gson> provider3, Provider<MetricsServiceV2> provider4, Provider<UrlResolver> provider5, Provider<IdentityService> provider6, Provider<AccountService> provider7, Provider<RoutingService> provider8, Provider<Cache<HttpResponseWrapper>> provider9, Provider<MetricsService> provider10, Provider<FeatureServiceV2> provider11, Provider<EnvironmentService> provider12, Provider<Mobilytics> provider13) {
        return new ElementsModule_ProvideElementsDataServiceFactory(elementsModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
    }

    public static ElementsDataService provideInstance(ElementsModule elementsModule, Provider<CookieManager> provider, Provider<OkHttpClient> provider2, Provider<Gson> provider3, Provider<MetricsServiceV2> provider4, Provider<UrlResolver> provider5, Provider<IdentityService> provider6, Provider<AccountService> provider7, Provider<RoutingService> provider8, Provider<Cache<HttpResponseWrapper>> provider9, Provider<MetricsService> provider10, Provider<FeatureServiceV2> provider11, Provider<EnvironmentService> provider12, Provider<Mobilytics> provider13) {
        return proxyProvideElementsDataService(elementsModule, DoubleCheck.lazy(provider), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), provider9.mo10268get(), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12), DoubleCheck.lazy(provider13));
    }

    public static ElementsDataService proxyProvideElementsDataService(ElementsModule elementsModule, Lazy<CookieManager> lazy, OkHttpClient okHttpClient, Gson gson, MetricsServiceV2 metricsServiceV2, UrlResolver urlResolver, Lazy<IdentityService> lazy2, Lazy<AccountService> lazy3, Lazy<RoutingService> lazy4, Cache<HttpResponseWrapper> cache, Lazy<MetricsService> lazy5, Lazy<FeatureServiceV2> lazy6, Lazy<EnvironmentService> lazy7, Lazy<Mobilytics> lazy8) {
        return (ElementsDataService) Preconditions.checkNotNull(elementsModule.provideElementsDataService(lazy, okHttpClient, gson, metricsServiceV2, urlResolver, lazy2, lazy3, lazy4, cache, lazy5, lazy6, lazy7, lazy8), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ElementsDataService mo10268get() {
        return provideInstance(this.module, this.cookieManagerProvider, this.httpClientProvider, this.gsonProvider, this.metricsServiceV2Provider, this.urlResolverProvider, this.identityServiceProvider, this.accountServiceProvider, this.routingServiceProvider, this.cacheProvider, this.metricsServiceProvider, this.featureServiceV2Provider, this.environmentServiceProvider, this.mobilyticsLazyProvider);
    }
}
