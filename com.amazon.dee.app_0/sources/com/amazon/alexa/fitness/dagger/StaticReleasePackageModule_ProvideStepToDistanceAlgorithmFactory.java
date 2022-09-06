package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.algorithms.StepToDistanceAlgorithm;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideStepToDistanceAlgorithmFactory implements Factory<StepToDistanceAlgorithm> {
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideStepToDistanceAlgorithmFactory(StaticReleasePackageModule staticReleasePackageModule) {
        this.module = staticReleasePackageModule;
    }

    public static StaticReleasePackageModule_ProvideStepToDistanceAlgorithmFactory create(StaticReleasePackageModule staticReleasePackageModule) {
        return new StaticReleasePackageModule_ProvideStepToDistanceAlgorithmFactory(staticReleasePackageModule);
    }

    public static StepToDistanceAlgorithm provideInstance(StaticReleasePackageModule staticReleasePackageModule) {
        return proxyProvideStepToDistanceAlgorithm(staticReleasePackageModule);
    }

    public static StepToDistanceAlgorithm proxyProvideStepToDistanceAlgorithm(StaticReleasePackageModule staticReleasePackageModule) {
        return (StepToDistanceAlgorithm) Preconditions.checkNotNull(staticReleasePackageModule.provideStepToDistanceAlgorithm(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public StepToDistanceAlgorithm mo10268get() {
        return provideInstance(this.module);
    }
}
