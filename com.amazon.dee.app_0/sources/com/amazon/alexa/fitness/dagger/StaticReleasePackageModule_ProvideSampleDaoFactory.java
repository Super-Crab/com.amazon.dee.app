package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.sdk.database.FitnessDatabase;
import com.amazon.alexa.fitness.sdk.database.SampleDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideSampleDaoFactory implements Factory<SampleDao> {
    private final Provider<FitnessDatabase> fitnessDatabaseProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideSampleDaoFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<FitnessDatabase> provider) {
        this.module = staticReleasePackageModule;
        this.fitnessDatabaseProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideSampleDaoFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<FitnessDatabase> provider) {
        return new StaticReleasePackageModule_ProvideSampleDaoFactory(staticReleasePackageModule, provider);
    }

    public static SampleDao provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<FitnessDatabase> provider) {
        return proxyProvideSampleDao(staticReleasePackageModule, provider.mo10268get());
    }

    public static SampleDao proxyProvideSampleDao(StaticReleasePackageModule staticReleasePackageModule, FitnessDatabase fitnessDatabase) {
        return (SampleDao) Preconditions.checkNotNull(staticReleasePackageModule.provideSampleDao(fitnessDatabase), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SampleDao mo10268get() {
        return provideInstance(this.module, this.fitnessDatabaseProvider);
    }
}
