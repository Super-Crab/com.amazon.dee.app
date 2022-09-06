package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentityStorage;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.dee.app.services.environment.DataRegionEnvironmentService;
import com.amazon.dee.app.services.environment.PersistentEndpointsStorage;
import com.dee.app.http.CoralService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideDataRegionEnvironmentServiceFactory implements Factory<DataRegionEnvironmentService> {
    private final Provider<Context> contextProvider;
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<PersistentEndpointsStorage> endpointsStorageProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final ServiceModule module;
    private final Provider<UserIdentityStorage> userStorageProvider;

    public ServiceModule_ProvideDataRegionEnvironmentServiceFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<UserIdentityStorage> provider3, Provider<PersistentEndpointsStorage> provider4, Provider<CoralService> provider5, Provider<IdentityService> provider6, Provider<Mobilytics> provider7) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.eventBusProvider = provider2;
        this.userStorageProvider = provider3;
        this.endpointsStorageProvider = provider4;
        this.coralServiceProvider = provider5;
        this.identityServiceProvider = provider6;
        this.mobilyticsProvider = provider7;
    }

    public static ServiceModule_ProvideDataRegionEnvironmentServiceFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<UserIdentityStorage> provider3, Provider<PersistentEndpointsStorage> provider4, Provider<CoralService> provider5, Provider<IdentityService> provider6, Provider<Mobilytics> provider7) {
        return new ServiceModule_ProvideDataRegionEnvironmentServiceFactory(serviceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static DataRegionEnvironmentService provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<UserIdentityStorage> provider3, Provider<PersistentEndpointsStorage> provider4, Provider<CoralService> provider5, Provider<IdentityService> provider6, Provider<Mobilytics> provider7) {
        return proxyProvideDataRegionEnvironmentService(serviceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7));
    }

    public static DataRegionEnvironmentService proxyProvideDataRegionEnvironmentService(ServiceModule serviceModule, Context context, EventBus eventBus, UserIdentityStorage userIdentityStorage, PersistentEndpointsStorage persistentEndpointsStorage, Lazy<CoralService> lazy, Lazy<IdentityService> lazy2, Lazy<Mobilytics> lazy3) {
        return (DataRegionEnvironmentService) Preconditions.checkNotNull(serviceModule.provideDataRegionEnvironmentService(context, eventBus, userIdentityStorage, persistentEndpointsStorage, lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DataRegionEnvironmentService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.eventBusProvider, this.userStorageProvider, this.endpointsStorageProvider, this.coralServiceProvider, this.identityServiceProvider, this.mobilyticsProvider);
    }
}
