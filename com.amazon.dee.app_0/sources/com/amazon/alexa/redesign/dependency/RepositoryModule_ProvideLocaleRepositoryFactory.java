package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.redesign.repository.LocaleRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class RepositoryModule_ProvideLocaleRepositoryFactory implements Factory<LocaleRepository> {
    private final RepositoryModule module;

    public RepositoryModule_ProvideLocaleRepositoryFactory(RepositoryModule repositoryModule) {
        this.module = repositoryModule;
    }

    public static RepositoryModule_ProvideLocaleRepositoryFactory create(RepositoryModule repositoryModule) {
        return new RepositoryModule_ProvideLocaleRepositoryFactory(repositoryModule);
    }

    public static LocaleRepository provideInstance(RepositoryModule repositoryModule) {
        return proxyProvideLocaleRepository(repositoryModule);
    }

    public static LocaleRepository proxyProvideLocaleRepository(RepositoryModule repositoryModule) {
        return (LocaleRepository) Preconditions.checkNotNull(repositoryModule.provideLocaleRepository(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocaleRepository mo10268get() {
        return provideInstance(this.module);
    }
}
