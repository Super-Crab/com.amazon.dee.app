package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.theme.SunriseTimeProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideSunriseTimeProviderFactory implements Factory<SunriseTimeProvider> {
    private final RepositoryModule module;

    public RepositoryModule_ProvideSunriseTimeProviderFactory(RepositoryModule repositoryModule) {
        this.module = repositoryModule;
    }

    public static RepositoryModule_ProvideSunriseTimeProviderFactory create(RepositoryModule repositoryModule) {
        return new RepositoryModule_ProvideSunriseTimeProviderFactory(repositoryModule);
    }

    public static SunriseTimeProvider provideInstance(RepositoryModule repositoryModule) {
        return proxyProvideSunriseTimeProvider(repositoryModule);
    }

    public static SunriseTimeProvider proxyProvideSunriseTimeProvider(RepositoryModule repositoryModule) {
        return (SunriseTimeProvider) Preconditions.checkNotNull(repositoryModule.provideSunriseTimeProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SunriseTimeProvider mo10268get() {
        return provideInstance(this.module);
    }
}
