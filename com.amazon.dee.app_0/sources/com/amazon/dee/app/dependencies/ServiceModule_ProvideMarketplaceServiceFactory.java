package com.amazon.dee.app.dependencies;

import com.amazon.alexa.marketplace.api.MarketplaceService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.dee.app.http.CoralService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideMarketplaceServiceFactory implements Factory<MarketplaceService> {
    private final Provider<CoralService> coralServiceProvider;
    private final ServiceModule module;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;

    public ServiceModule_ProvideMarketplaceServiceFactory(ServiceModule serviceModule, Provider<PersistentStorage.Factory> provider, Provider<CoralService> provider2) {
        this.module = serviceModule;
        this.storageFactoryProvider = provider;
        this.coralServiceProvider = provider2;
    }

    public static ServiceModule_ProvideMarketplaceServiceFactory create(ServiceModule serviceModule, Provider<PersistentStorage.Factory> provider, Provider<CoralService> provider2) {
        return new ServiceModule_ProvideMarketplaceServiceFactory(serviceModule, provider, provider2);
    }

    public static MarketplaceService provideInstance(ServiceModule serviceModule, Provider<PersistentStorage.Factory> provider, Provider<CoralService> provider2) {
        return proxyProvideMarketplaceService(serviceModule, provider.mo10268get(), DoubleCheck.lazy(provider2));
    }

    public static MarketplaceService proxyProvideMarketplaceService(ServiceModule serviceModule, PersistentStorage.Factory factory, Lazy<CoralService> lazy) {
        return (MarketplaceService) Preconditions.checkNotNull(serviceModule.provideMarketplaceService(factory, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MarketplaceService mo10268get() {
        return provideInstance(this.module, this.storageFactoryProvider, this.coralServiceProvider);
    }
}
