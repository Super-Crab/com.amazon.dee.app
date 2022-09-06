package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.location.LocationProvider;
import com.amazon.alexa.location.LocationService;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideLocationServiceFactory implements Factory<LocationService> {
    private final Provider<Cache<AppDataCacheEntry>> cacheProvider;
    private final Provider<Context> contextProvider;
    private final Provider<LocationProvider> locationProvider;
    private final ServiceModule module;
    private final Provider<OkHttpClient> okHttpClientProvider;

    public ServiceModule_ProvideLocationServiceFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<LocationProvider> provider2, Provider<OkHttpClient> provider3, Provider<Cache<AppDataCacheEntry>> provider4) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.locationProvider = provider2;
        this.okHttpClientProvider = provider3;
        this.cacheProvider = provider4;
    }

    public static ServiceModule_ProvideLocationServiceFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<LocationProvider> provider2, Provider<OkHttpClient> provider3, Provider<Cache<AppDataCacheEntry>> provider4) {
        return new ServiceModule_ProvideLocationServiceFactory(serviceModule, provider, provider2, provider3, provider4);
    }

    public static LocationService provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<LocationProvider> provider2, Provider<OkHttpClient> provider3, Provider<Cache<AppDataCacheEntry>> provider4) {
        return proxyProvideLocationService(serviceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static LocationService proxyProvideLocationService(ServiceModule serviceModule, Context context, LocationProvider locationProvider, OkHttpClient okHttpClient, Cache<AppDataCacheEntry> cache) {
        return (LocationService) Preconditions.checkNotNull(serviceModule.provideLocationService(context, locationProvider, okHttpClient, cache), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocationService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.locationProvider, this.okHttpClientProvider, this.cacheProvider);
    }
}
