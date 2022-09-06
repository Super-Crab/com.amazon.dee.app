package com.amazon.ptz.dagger;

import android.os.Handler;
import com.amazon.alexa.directive.AlexaDirective;
import com.amazon.ptz.physical.communication.PhysicalPtzCommandCache;
import com.google.common.cache.Cache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes13.dex */
public final class PhysicalPtzCacheModule_ProvidePhysicalPtzCommandCacheFactory implements Factory<PhysicalPtzCommandCache> {
    private final Provider<Cache<String, AlexaDirective>> cacheProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<Handler> handlerProvider;
    private final PhysicalPtzCacheModule module;

    public PhysicalPtzCacheModule_ProvidePhysicalPtzCommandCacheFactory(PhysicalPtzCacheModule physicalPtzCacheModule, Provider<Cache<String, AlexaDirective>> provider, Provider<Handler> provider2, Provider<EventBus> provider3) {
        this.module = physicalPtzCacheModule;
        this.cacheProvider = provider;
        this.handlerProvider = provider2;
        this.eventBusProvider = provider3;
    }

    public static PhysicalPtzCacheModule_ProvidePhysicalPtzCommandCacheFactory create(PhysicalPtzCacheModule physicalPtzCacheModule, Provider<Cache<String, AlexaDirective>> provider, Provider<Handler> provider2, Provider<EventBus> provider3) {
        return new PhysicalPtzCacheModule_ProvidePhysicalPtzCommandCacheFactory(physicalPtzCacheModule, provider, provider2, provider3);
    }

    public static PhysicalPtzCommandCache provideInstance(PhysicalPtzCacheModule physicalPtzCacheModule, Provider<Cache<String, AlexaDirective>> provider, Provider<Handler> provider2, Provider<EventBus> provider3) {
        return proxyProvidePhysicalPtzCommandCache(physicalPtzCacheModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static PhysicalPtzCommandCache proxyProvidePhysicalPtzCommandCache(PhysicalPtzCacheModule physicalPtzCacheModule, Cache<String, AlexaDirective> cache, Handler handler, EventBus eventBus) {
        return (PhysicalPtzCommandCache) Preconditions.checkNotNull(physicalPtzCacheModule.providePhysicalPtzCommandCache(cache, handler, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhysicalPtzCommandCache mo10268get() {
        return provideInstance(this.module, this.cacheProvider, this.handlerProvider, this.eventBusProvider);
    }
}
