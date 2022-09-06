package com.amazon.commscore.featureflag.dependencies;

import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class FeatureFlagModule_ProvidesFeatureServiceFactory implements Factory<AlexaCommsCoreFeatureService> {
    private final FeatureFlagModule module;

    public FeatureFlagModule_ProvidesFeatureServiceFactory(FeatureFlagModule featureFlagModule) {
        this.module = featureFlagModule;
    }

    public static FeatureFlagModule_ProvidesFeatureServiceFactory create(FeatureFlagModule featureFlagModule) {
        return new FeatureFlagModule_ProvidesFeatureServiceFactory(featureFlagModule);
    }

    public static AlexaCommsCoreFeatureService provideInstance(FeatureFlagModule featureFlagModule) {
        return proxyProvidesFeatureService(featureFlagModule);
    }

    public static AlexaCommsCoreFeatureService proxyProvidesFeatureService(FeatureFlagModule featureFlagModule) {
        return (AlexaCommsCoreFeatureService) Preconditions.checkNotNull(featureFlagModule.providesFeatureService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaCommsCoreFeatureService mo10268get() {
        return provideInstance(this.module);
    }
}
