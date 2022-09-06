package com.amazon.dee.app.dependencies;

import android.content.Context;
import android.webkit.CookieManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.dependencies.DaggerInitializer;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.routing.api.RoutingService;
import com.dee.app.http.CoralService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvidesFsv2DaggerInitializerFactory implements Factory<DaggerInitializer> {
    private final Provider<AccountService> accountServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CookieManager> cookieManagerProvider;
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<FeatureQuery> featureQueryProvider;
    private final Provider<FeatureServiceV2> featureServiceV2LazyProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final ServiceModule module;
    private final Provider<OkHttpClient> okHttpClientProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public ServiceModule_ProvidesFsv2DaggerInitializerFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<OkHttpClient> provider2, Provider<EnvironmentService> provider3, Provider<EventBus> provider4, Provider<CoralService> provider5, Provider<IdentityService> provider6, Provider<CookieManager> provider7, Provider<AccountService> provider8, Provider<RoutingService> provider9, Provider<Mobilytics> provider10, Provider<FeatureQuery> provider11, Provider<FeatureServiceV2> provider12) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.okHttpClientProvider = provider2;
        this.environmentServiceProvider = provider3;
        this.eventBusProvider = provider4;
        this.coralServiceProvider = provider5;
        this.identityServiceProvider = provider6;
        this.cookieManagerProvider = provider7;
        this.accountServiceProvider = provider8;
        this.routingServiceProvider = provider9;
        this.mobilyticsProvider = provider10;
        this.featureQueryProvider = provider11;
        this.featureServiceV2LazyProvider = provider12;
    }

    public static ServiceModule_ProvidesFsv2DaggerInitializerFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<OkHttpClient> provider2, Provider<EnvironmentService> provider3, Provider<EventBus> provider4, Provider<CoralService> provider5, Provider<IdentityService> provider6, Provider<CookieManager> provider7, Provider<AccountService> provider8, Provider<RoutingService> provider9, Provider<Mobilytics> provider10, Provider<FeatureQuery> provider11, Provider<FeatureServiceV2> provider12) {
        return new ServiceModule_ProvidesFsv2DaggerInitializerFactory(serviceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }

    public static DaggerInitializer provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<OkHttpClient> provider2, Provider<EnvironmentService> provider3, Provider<EventBus> provider4, Provider<CoralService> provider5, Provider<IdentityService> provider6, Provider<CookieManager> provider7, Provider<AccountService> provider8, Provider<RoutingService> provider9, Provider<Mobilytics> provider10, Provider<FeatureQuery> provider11, Provider<FeatureServiceV2> provider12) {
        return proxyProvidesFsv2DaggerInitializer(serviceModule, provider.mo10268get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12));
    }

    public static DaggerInitializer proxyProvidesFsv2DaggerInitializer(ServiceModule serviceModule, Context context, Lazy<OkHttpClient> lazy, Lazy<EnvironmentService> lazy2, Lazy<EventBus> lazy3, Lazy<CoralService> lazy4, Lazy<IdentityService> lazy5, Lazy<CookieManager> lazy6, Lazy<AccountService> lazy7, Lazy<RoutingService> lazy8, Lazy<Mobilytics> lazy9, Lazy<FeatureQuery> lazy10, Lazy<FeatureServiceV2> lazy11) {
        return (DaggerInitializer) Preconditions.checkNotNull(serviceModule.providesFsv2DaggerInitializer(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DaggerInitializer mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.okHttpClientProvider, this.environmentServiceProvider, this.eventBusProvider, this.coralServiceProvider, this.identityServiceProvider, this.cookieManagerProvider, this.accountServiceProvider, this.routingServiceProvider, this.mobilyticsProvider, this.featureQueryProvider, this.featureServiceV2LazyProvider);
    }
}
