package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.location.LocationServiceConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideLocationServiceConfigurationFactory implements Factory<LocationServiceConfiguration> {
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideLocationServiceConfigurationFactory(StaticReleasePackageModule staticReleasePackageModule) {
        this.module = staticReleasePackageModule;
    }

    public static StaticReleasePackageModule_ProvideLocationServiceConfigurationFactory create(StaticReleasePackageModule staticReleasePackageModule) {
        return new StaticReleasePackageModule_ProvideLocationServiceConfigurationFactory(staticReleasePackageModule);
    }

    public static LocationServiceConfiguration provideInstance(StaticReleasePackageModule staticReleasePackageModule) {
        return proxyProvideLocationServiceConfiguration(staticReleasePackageModule);
    }

    public static LocationServiceConfiguration proxyProvideLocationServiceConfiguration(StaticReleasePackageModule staticReleasePackageModule) {
        return (LocationServiceConfiguration) Preconditions.checkNotNull(staticReleasePackageModule.provideLocationServiceConfiguration(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocationServiceConfiguration mo10268get() {
        return provideInstance(this.module);
    }
}
