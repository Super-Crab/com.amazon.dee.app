package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.interactor.EntertainmentAsyncTaskFactory;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideNowPlayingScreenInteractorFactory implements Factory<NowPlayingScreenContract.Interactor> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<EntertainmentAsyncTaskFactory> entertainmentAsyncTaskFactoryProvider;
    private final RepositoryModule module;
    private final Provider<NowPlayingScreenContract.Presenter> nowPlayingScreenPresenterProvider;

    public RepositoryModule_ProvideNowPlayingScreenInteractorFactory(RepositoryModule repositoryModule, Provider<NowPlayingScreenContract.Presenter> provider, Provider<AlexaServicesConnection> provider2, Provider<EntertainmentAsyncTaskFactory> provider3) {
        this.module = repositoryModule;
        this.nowPlayingScreenPresenterProvider = provider;
        this.alexaServicesConnectionProvider = provider2;
        this.entertainmentAsyncTaskFactoryProvider = provider3;
    }

    public static RepositoryModule_ProvideNowPlayingScreenInteractorFactory create(RepositoryModule repositoryModule, Provider<NowPlayingScreenContract.Presenter> provider, Provider<AlexaServicesConnection> provider2, Provider<EntertainmentAsyncTaskFactory> provider3) {
        return new RepositoryModule_ProvideNowPlayingScreenInteractorFactory(repositoryModule, provider, provider2, provider3);
    }

    public static NowPlayingScreenContract.Interactor provideInstance(RepositoryModule repositoryModule, Provider<NowPlayingScreenContract.Presenter> provider, Provider<AlexaServicesConnection> provider2, Provider<EntertainmentAsyncTaskFactory> provider3) {
        return proxyProvideNowPlayingScreenInteractor(repositoryModule, DoubleCheck.lazy(provider), provider2.mo10268get(), provider3.mo10268get());
    }

    public static NowPlayingScreenContract.Interactor proxyProvideNowPlayingScreenInteractor(RepositoryModule repositoryModule, Lazy<NowPlayingScreenContract.Presenter> lazy, AlexaServicesConnection alexaServicesConnection, EntertainmentAsyncTaskFactory entertainmentAsyncTaskFactory) {
        return (NowPlayingScreenContract.Interactor) Preconditions.checkNotNull(repositoryModule.provideNowPlayingScreenInteractor(lazy, alexaServicesConnection, entertainmentAsyncTaskFactory), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NowPlayingScreenContract.Interactor mo10268get() {
        return provideInstance(this.module, this.nowPlayingScreenPresenterProvider, this.alexaServicesConnectionProvider, this.entertainmentAsyncTaskFactoryProvider);
    }
}
