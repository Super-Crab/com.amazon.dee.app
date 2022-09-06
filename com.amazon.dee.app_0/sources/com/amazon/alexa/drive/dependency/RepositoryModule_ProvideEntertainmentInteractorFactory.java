package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.interactor.EntertainmentAsyncTaskFactory;
import com.amazon.alexa.drive.entertainment.repository.EntertainmentDataRepository;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideEntertainmentInteractorFactory implements Factory<EntertainmentLandingPageContract.EntertainmentInteractor> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<EntertainmentAsyncTaskFactory> entertainmentAsyncTaskFactoryProvider;
    private final Provider<EntertainmentDataRepository> entertainmentDataRepositoryProvider;
    private final Provider<EntertainmentLandingPageContract.Presenter> entertainmentLandingPagePresenterProvider;
    private final Provider<EntertainmentMetrics> entertainmentMetricsProvider;
    private final RepositoryModule module;
    private final Provider<NowPlayingScreenContract.Interactor> nowPlayingScreenInteractorProvider;

    public RepositoryModule_ProvideEntertainmentInteractorFactory(RepositoryModule repositoryModule, Provider<EntertainmentLandingPageContract.Presenter> provider, Provider<AlexaServicesConnection> provider2, Provider<EntertainmentDataRepository> provider3, Provider<NowPlayingScreenContract.Interactor> provider4, Provider<EntertainmentAsyncTaskFactory> provider5, Provider<EntertainmentMetrics> provider6) {
        this.module = repositoryModule;
        this.entertainmentLandingPagePresenterProvider = provider;
        this.alexaServicesConnectionProvider = provider2;
        this.entertainmentDataRepositoryProvider = provider3;
        this.nowPlayingScreenInteractorProvider = provider4;
        this.entertainmentAsyncTaskFactoryProvider = provider5;
        this.entertainmentMetricsProvider = provider6;
    }

    public static RepositoryModule_ProvideEntertainmentInteractorFactory create(RepositoryModule repositoryModule, Provider<EntertainmentLandingPageContract.Presenter> provider, Provider<AlexaServicesConnection> provider2, Provider<EntertainmentDataRepository> provider3, Provider<NowPlayingScreenContract.Interactor> provider4, Provider<EntertainmentAsyncTaskFactory> provider5, Provider<EntertainmentMetrics> provider6) {
        return new RepositoryModule_ProvideEntertainmentInteractorFactory(repositoryModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static EntertainmentLandingPageContract.EntertainmentInteractor provideInstance(RepositoryModule repositoryModule, Provider<EntertainmentLandingPageContract.Presenter> provider, Provider<AlexaServicesConnection> provider2, Provider<EntertainmentDataRepository> provider3, Provider<NowPlayingScreenContract.Interactor> provider4, Provider<EntertainmentAsyncTaskFactory> provider5, Provider<EntertainmentMetrics> provider6) {
        return proxyProvideEntertainmentInteractor(repositoryModule, DoubleCheck.lazy(provider), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    public static EntertainmentLandingPageContract.EntertainmentInteractor proxyProvideEntertainmentInteractor(RepositoryModule repositoryModule, Lazy<EntertainmentLandingPageContract.Presenter> lazy, AlexaServicesConnection alexaServicesConnection, EntertainmentDataRepository entertainmentDataRepository, NowPlayingScreenContract.Interactor interactor, EntertainmentAsyncTaskFactory entertainmentAsyncTaskFactory, EntertainmentMetrics entertainmentMetrics) {
        return (EntertainmentLandingPageContract.EntertainmentInteractor) Preconditions.checkNotNull(repositoryModule.provideEntertainmentInteractor(lazy, alexaServicesConnection, entertainmentDataRepository, interactor, entertainmentAsyncTaskFactory, entertainmentMetrics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EntertainmentLandingPageContract.EntertainmentInteractor mo10268get() {
        return provideInstance(this.module, this.entertainmentLandingPagePresenterProvider, this.alexaServicesConnectionProvider, this.entertainmentDataRepositoryProvider, this.nowPlayingScreenInteractorProvider, this.entertainmentAsyncTaskFactoryProvider, this.entertainmentMetricsProvider);
    }
}
