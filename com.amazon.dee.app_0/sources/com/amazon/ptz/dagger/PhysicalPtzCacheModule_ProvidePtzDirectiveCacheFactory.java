package com.amazon.ptz.dagger;

import com.amazon.alexa.directive.AlexaDirective;
import com.google.common.cache.Cache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class PhysicalPtzCacheModule_ProvidePtzDirectiveCacheFactory implements Factory<Cache<String, AlexaDirective>> {
    private final PhysicalPtzCacheModule module;

    public PhysicalPtzCacheModule_ProvidePtzDirectiveCacheFactory(PhysicalPtzCacheModule physicalPtzCacheModule) {
        this.module = physicalPtzCacheModule;
    }

    public static PhysicalPtzCacheModule_ProvidePtzDirectiveCacheFactory create(PhysicalPtzCacheModule physicalPtzCacheModule) {
        return new PhysicalPtzCacheModule_ProvidePtzDirectiveCacheFactory(physicalPtzCacheModule);
    }

    public static Cache<String, AlexaDirective> provideInstance(PhysicalPtzCacheModule physicalPtzCacheModule) {
        return proxyProvidePtzDirectiveCache(physicalPtzCacheModule);
    }

    public static Cache<String, AlexaDirective> proxyProvidePtzDirectiveCache(PhysicalPtzCacheModule physicalPtzCacheModule) {
        return (Cache) Preconditions.checkNotNull(physicalPtzCacheModule.providePtzDirectiveCache(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Cache<String, AlexaDirective> mo10268get() {
        return provideInstance(this.module);
    }
}
