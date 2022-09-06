package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.dedupe.DedupeStage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideDedupeStagesFactory implements Factory<List<DedupeStage>> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideDedupeStagesFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideDedupeStagesFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideDedupeStagesFactory(discoveryModule);
    }

    public static List<DedupeStage> provideDedupeStages(DiscoveryModule discoveryModule) {
        return (List) Preconditions.checkNotNull(discoveryModule.provideDedupeStages(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public List<DedupeStage> mo10268get() {
        return provideDedupeStages(this.module);
    }
}
