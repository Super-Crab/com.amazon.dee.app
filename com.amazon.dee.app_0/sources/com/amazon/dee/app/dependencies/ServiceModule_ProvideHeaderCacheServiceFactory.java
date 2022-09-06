package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.header.HeaderCacheService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideHeaderCacheServiceFactory implements Factory<HeaderCacheService> {
    private final ServiceModule module;

    public ServiceModule_ProvideHeaderCacheServiceFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public static ServiceModule_ProvideHeaderCacheServiceFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideHeaderCacheServiceFactory(serviceModule);
    }

    public static HeaderCacheService provideInstance(ServiceModule serviceModule) {
        return proxyProvideHeaderCacheService(serviceModule);
    }

    public static HeaderCacheService proxyProvideHeaderCacheService(ServiceModule serviceModule) {
        return (HeaderCacheService) Preconditions.checkNotNull(serviceModule.provideHeaderCacheService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HeaderCacheService mo10268get() {
        return provideInstance(this.module);
    }
}
