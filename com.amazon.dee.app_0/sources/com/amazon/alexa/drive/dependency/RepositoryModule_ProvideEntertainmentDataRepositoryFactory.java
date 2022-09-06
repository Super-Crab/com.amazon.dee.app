package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.entertainment.repository.EntertainmentDataRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideEntertainmentDataRepositoryFactory implements Factory<EntertainmentDataRepository> {
    private final RepositoryModule module;

    public RepositoryModule_ProvideEntertainmentDataRepositoryFactory(RepositoryModule repositoryModule) {
        this.module = repositoryModule;
    }

    public static RepositoryModule_ProvideEntertainmentDataRepositoryFactory create(RepositoryModule repositoryModule) {
        return new RepositoryModule_ProvideEntertainmentDataRepositoryFactory(repositoryModule);
    }

    public static EntertainmentDataRepository provideInstance(RepositoryModule repositoryModule) {
        return proxyProvideEntertainmentDataRepository(repositoryModule);
    }

    public static EntertainmentDataRepository proxyProvideEntertainmentDataRepository(RepositoryModule repositoryModule) {
        return (EntertainmentDataRepository) Preconditions.checkNotNull(repositoryModule.provideEntertainmentDataRepository(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EntertainmentDataRepository mo10268get() {
        return provideInstance(this.module);
    }
}
