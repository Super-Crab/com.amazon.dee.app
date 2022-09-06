package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.comms.contract.CommsLandingPageContract;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideCommsLandingPageInteractorFactory implements Factory<CommsLandingPageContract.Interactor> {
    private final Provider<CommsLandingPageContract.Presenter> commsLandingPagePresenterProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvideCommsLandingPageInteractorFactory(RepositoryModule repositoryModule, Provider<CommsLandingPageContract.Presenter> provider) {
        this.module = repositoryModule;
        this.commsLandingPagePresenterProvider = provider;
    }

    public static RepositoryModule_ProvideCommsLandingPageInteractorFactory create(RepositoryModule repositoryModule, Provider<CommsLandingPageContract.Presenter> provider) {
        return new RepositoryModule_ProvideCommsLandingPageInteractorFactory(repositoryModule, provider);
    }

    public static CommsLandingPageContract.Interactor provideInstance(RepositoryModule repositoryModule, Provider<CommsLandingPageContract.Presenter> provider) {
        return proxyProvideCommsLandingPageInteractor(repositoryModule, DoubleCheck.lazy(provider));
    }

    public static CommsLandingPageContract.Interactor proxyProvideCommsLandingPageInteractor(RepositoryModule repositoryModule, Lazy<CommsLandingPageContract.Presenter> lazy) {
        return (CommsLandingPageContract.Interactor) Preconditions.checkNotNull(repositoryModule.provideCommsLandingPageInteractor(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsLandingPageContract.Interactor mo10268get() {
        return provideInstance(this.module, this.commsLandingPagePresenterProvider);
    }
}
