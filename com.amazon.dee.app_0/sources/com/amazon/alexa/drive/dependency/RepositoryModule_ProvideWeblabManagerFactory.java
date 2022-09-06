package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.weblab.WeblabManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideWeblabManagerFactory implements Factory<WeblabManager> {
    private final RepositoryModule module;

    public RepositoryModule_ProvideWeblabManagerFactory(RepositoryModule repositoryModule) {
        this.module = repositoryModule;
    }

    public static RepositoryModule_ProvideWeblabManagerFactory create(RepositoryModule repositoryModule) {
        return new RepositoryModule_ProvideWeblabManagerFactory(repositoryModule);
    }

    public static WeblabManager provideInstance(RepositoryModule repositoryModule) {
        return proxyProvideWeblabManager(repositoryModule);
    }

    public static WeblabManager proxyProvideWeblabManager(RepositoryModule repositoryModule) {
        return (WeblabManager) Preconditions.checkNotNull(repositoryModule.provideWeblabManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WeblabManager mo10268get() {
        return provideInstance(this.module);
    }
}
