package com.amazon.photos.discovery.internal.dagger.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Map;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideNextDedupeStageIdMapFactory implements Factory<Map<Integer, Integer>> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideNextDedupeStageIdMapFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideNextDedupeStageIdMapFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideNextDedupeStageIdMapFactory(discoveryModule);
    }

    public static Map<Integer, Integer> provideNextDedupeStageIdMap(DiscoveryModule discoveryModule) {
        return (Map) Preconditions.checkNotNull(discoveryModule.provideNextDedupeStageIdMap(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Map<Integer, Integer> mo10268get() {
        return provideNextDedupeStageIdMap(this.module);
    }
}
