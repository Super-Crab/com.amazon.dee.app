package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.dedupe.DedupeStage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Map;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideDedupeIdMapFactory implements Factory<Map<Integer, DedupeStage>> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideDedupeIdMapFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideDedupeIdMapFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideDedupeIdMapFactory(discoveryModule);
    }

    public static Map<Integer, DedupeStage> provideDedupeIdMap(DiscoveryModule discoveryModule) {
        return (Map) Preconditions.checkNotNull(discoveryModule.provideDedupeIdMap(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Map<Integer, DedupeStage> mo10268get() {
        return provideDedupeIdMap(this.module);
    }
}
