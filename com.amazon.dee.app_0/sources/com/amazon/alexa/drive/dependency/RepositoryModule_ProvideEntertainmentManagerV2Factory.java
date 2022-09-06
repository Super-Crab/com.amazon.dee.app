package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.drive.entertainment.EntertainmentManagerV2;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideEntertainmentManagerV2Factory implements Factory<EntertainmentManagerV2> {
    private final Provider<Context> contextProvider;
    private final Provider<DriveModeThemeManager> driveModeThemeManagerProvider;
    private final Provider<EntertainmentLandingPageContract.EntertainmentInteractor> entertainmentInteractorProvider;
    private final Provider<EntertainmentMetrics> entertainmentMetricsProvider;
    private final RepositoryModule module;
    private final Provider<NetworkConnectivityManager> networkConnectivityManagerProvider;
    private final Provider<NowPlayingScreenContract.Interactor> nowPlayingScreenInteractorProvider;

    public RepositoryModule_ProvideEntertainmentManagerV2Factory(RepositoryModule repositoryModule, Provider<Context> provider, Provider<EntertainmentLandingPageContract.EntertainmentInteractor> provider2, Provider<NowPlayingScreenContract.Interactor> provider3, Provider<NetworkConnectivityManager> provider4, Provider<EntertainmentMetrics> provider5, Provider<DriveModeThemeManager> provider6) {
        this.module = repositoryModule;
        this.contextProvider = provider;
        this.entertainmentInteractorProvider = provider2;
        this.nowPlayingScreenInteractorProvider = provider3;
        this.networkConnectivityManagerProvider = provider4;
        this.entertainmentMetricsProvider = provider5;
        this.driveModeThemeManagerProvider = provider6;
    }

    public static RepositoryModule_ProvideEntertainmentManagerV2Factory create(RepositoryModule repositoryModule, Provider<Context> provider, Provider<EntertainmentLandingPageContract.EntertainmentInteractor> provider2, Provider<NowPlayingScreenContract.Interactor> provider3, Provider<NetworkConnectivityManager> provider4, Provider<EntertainmentMetrics> provider5, Provider<DriveModeThemeManager> provider6) {
        return new RepositoryModule_ProvideEntertainmentManagerV2Factory(repositoryModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static EntertainmentManagerV2 provideInstance(RepositoryModule repositoryModule, Provider<Context> provider, Provider<EntertainmentLandingPageContract.EntertainmentInteractor> provider2, Provider<NowPlayingScreenContract.Interactor> provider3, Provider<NetworkConnectivityManager> provider4, Provider<EntertainmentMetrics> provider5, Provider<DriveModeThemeManager> provider6) {
        return proxyProvideEntertainmentManagerV2(repositoryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    public static EntertainmentManagerV2 proxyProvideEntertainmentManagerV2(RepositoryModule repositoryModule, Context context, EntertainmentLandingPageContract.EntertainmentInteractor entertainmentInteractor, NowPlayingScreenContract.Interactor interactor, NetworkConnectivityManager networkConnectivityManager, EntertainmentMetrics entertainmentMetrics, DriveModeThemeManager driveModeThemeManager) {
        return (EntertainmentManagerV2) Preconditions.checkNotNull(repositoryModule.provideEntertainmentManagerV2(context, entertainmentInteractor, interactor, networkConnectivityManager, entertainmentMetrics, driveModeThemeManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EntertainmentManagerV2 mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.entertainmentInteractorProvider, this.nowPlayingScreenInteractorProvider, this.networkConnectivityManagerProvider, this.entertainmentMetricsProvider, this.driveModeThemeManagerProvider);
    }
}
