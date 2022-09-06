package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.mode.ModeService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideModeServiceFactory implements Factory<ModeService> {
    private final RepositoryModule module;

    public RepositoryModule_ProvideModeServiceFactory(RepositoryModule repositoryModule) {
        this.module = repositoryModule;
    }

    public static RepositoryModule_ProvideModeServiceFactory create(RepositoryModule repositoryModule) {
        return new RepositoryModule_ProvideModeServiceFactory(repositoryModule);
    }

    public static ModeService provideInstance(RepositoryModule repositoryModule) {
        return proxyProvideModeService(repositoryModule);
    }

    public static ModeService proxyProvideModeService(RepositoryModule repositoryModule) {
        return (ModeService) Preconditions.checkNotNull(repositoryModule.provideModeService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ModeService mo10268get() {
        return provideInstance(this.module);
    }
}
