package com.amazon.alexa.fitness.dagger;

import com.amazon.wellness.io.format.abs.FitnessDataParser;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideFitnessDataParserFactory implements Factory<FitnessDataParser> {
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideFitnessDataParserFactory(StaticReleasePackageModule staticReleasePackageModule) {
        this.module = staticReleasePackageModule;
    }

    public static StaticReleasePackageModule_ProvideFitnessDataParserFactory create(StaticReleasePackageModule staticReleasePackageModule) {
        return new StaticReleasePackageModule_ProvideFitnessDataParserFactory(staticReleasePackageModule);
    }

    public static FitnessDataParser provideInstance(StaticReleasePackageModule staticReleasePackageModule) {
        return proxyProvideFitnessDataParser(staticReleasePackageModule);
    }

    public static FitnessDataParser proxyProvideFitnessDataParser(StaticReleasePackageModule staticReleasePackageModule) {
        return (FitnessDataParser) Preconditions.checkNotNull(staticReleasePackageModule.provideFitnessDataParser(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessDataParser mo10268get() {
        return provideInstance(this.module);
    }
}
