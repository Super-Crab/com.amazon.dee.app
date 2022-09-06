package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmV2;
import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvidesCaloriesAlgorithmV2Factory implements Factory<CaloriesAlgorithmV2> {
    private final Provider<ILog> logProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvidesCaloriesAlgorithmV2Factory(StaticReleasePackageModule staticReleasePackageModule, Provider<ILog> provider) {
        this.module = staticReleasePackageModule;
        this.logProvider = provider;
    }

    public static StaticReleasePackageModule_ProvidesCaloriesAlgorithmV2Factory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ILog> provider) {
        return new StaticReleasePackageModule_ProvidesCaloriesAlgorithmV2Factory(staticReleasePackageModule, provider);
    }

    public static CaloriesAlgorithmV2 provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ILog> provider) {
        return proxyProvidesCaloriesAlgorithmV2(staticReleasePackageModule, provider.mo10268get());
    }

    public static CaloriesAlgorithmV2 proxyProvidesCaloriesAlgorithmV2(StaticReleasePackageModule staticReleasePackageModule, ILog iLog) {
        return (CaloriesAlgorithmV2) Preconditions.checkNotNull(staticReleasePackageModule.providesCaloriesAlgorithmV2(iLog), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CaloriesAlgorithmV2 mo10268get() {
        return provideInstance(this.module, this.logProvider);
    }
}
