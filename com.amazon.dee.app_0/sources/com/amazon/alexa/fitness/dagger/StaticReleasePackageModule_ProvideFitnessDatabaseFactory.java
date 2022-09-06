package com.amazon.alexa.fitness.dagger;

import android.content.Context;
import com.amazon.alexa.fitness.sdk.database.FitnessDatabase;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideFitnessDatabaseFactory implements Factory<FitnessDatabase> {
    private final Provider<Context> contextProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideFitnessDatabaseFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        this.module = staticReleasePackageModule;
        this.contextProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideFitnessDatabaseFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        return new StaticReleasePackageModule_ProvideFitnessDatabaseFactory(staticReleasePackageModule, provider);
    }

    public static FitnessDatabase provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        return proxyProvideFitnessDatabase(staticReleasePackageModule, provider.mo10268get());
    }

    public static FitnessDatabase proxyProvideFitnessDatabase(StaticReleasePackageModule staticReleasePackageModule, Context context) {
        return (FitnessDatabase) Preconditions.checkNotNull(staticReleasePackageModule.provideFitnessDatabase(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessDatabase mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
