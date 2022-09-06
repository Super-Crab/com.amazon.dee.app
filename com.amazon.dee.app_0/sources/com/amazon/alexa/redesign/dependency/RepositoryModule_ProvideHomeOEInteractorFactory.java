package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.redesign.utils.HomeOEInteractor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class RepositoryModule_ProvideHomeOEInteractorFactory implements Factory<HomeOEInteractor> {
    private final Provider<Mobilytics> mobilyticsProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvideHomeOEInteractorFactory(RepositoryModule repositoryModule, Provider<Mobilytics> provider) {
        this.module = repositoryModule;
        this.mobilyticsProvider = provider;
    }

    public static RepositoryModule_ProvideHomeOEInteractorFactory create(RepositoryModule repositoryModule, Provider<Mobilytics> provider) {
        return new RepositoryModule_ProvideHomeOEInteractorFactory(repositoryModule, provider);
    }

    public static HomeOEInteractor provideInstance(RepositoryModule repositoryModule, Provider<Mobilytics> provider) {
        return proxyProvideHomeOEInteractor(repositoryModule, provider.mo10268get());
    }

    public static HomeOEInteractor proxyProvideHomeOEInteractor(RepositoryModule repositoryModule, Mobilytics mobilytics) {
        return (HomeOEInteractor) Preconditions.checkNotNull(repositoryModule.provideHomeOEInteractor(mobilytics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HomeOEInteractor mo10268get() {
        return provideInstance(this.module, this.mobilyticsProvider);
    }
}
