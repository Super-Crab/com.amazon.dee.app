package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.assetManagementService.api.AssetManagementService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideAssetManagementServiceFactory implements Factory<AssetManagementService> {
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideAssetManagementServiceFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<Mobilytics> provider3) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.eventBusProvider = provider2;
        this.mobilyticsProvider = provider3;
    }

    public static ServiceModule_ProvideAssetManagementServiceFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<Mobilytics> provider3) {
        return new ServiceModule_ProvideAssetManagementServiceFactory(serviceModule, provider, provider2, provider3);
    }

    public static AssetManagementService provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<Mobilytics> provider3) {
        return proxyProvideAssetManagementService(serviceModule, provider.mo10268get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static AssetManagementService proxyProvideAssetManagementService(ServiceModule serviceModule, Context context, Lazy<EventBus> lazy, Lazy<Mobilytics> lazy2) {
        return (AssetManagementService) Preconditions.checkNotNull(serviceModule.provideAssetManagementService(context, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AssetManagementService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.eventBusProvider, this.mobilyticsProvider);
    }
}
