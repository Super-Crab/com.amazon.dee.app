package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class PresenterModule_ProvideEntertainmentLandingPagePresenterFactory implements Factory<EntertainmentLandingPageContract.Presenter> {
    private final Provider<EntertainmentLandingPageContract.EntertainmentInteractor> entertainmentInteractorProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideEntertainmentLandingPagePresenterFactory(PresenterModule presenterModule, Provider<EntertainmentLandingPageContract.EntertainmentInteractor> provider) {
        this.module = presenterModule;
        this.entertainmentInteractorProvider = provider;
    }

    public static PresenterModule_ProvideEntertainmentLandingPagePresenterFactory create(PresenterModule presenterModule, Provider<EntertainmentLandingPageContract.EntertainmentInteractor> provider) {
        return new PresenterModule_ProvideEntertainmentLandingPagePresenterFactory(presenterModule, provider);
    }

    public static EntertainmentLandingPageContract.Presenter provideInstance(PresenterModule presenterModule, Provider<EntertainmentLandingPageContract.EntertainmentInteractor> provider) {
        return proxyProvideEntertainmentLandingPagePresenter(presenterModule, provider.mo10268get());
    }

    public static EntertainmentLandingPageContract.Presenter proxyProvideEntertainmentLandingPagePresenter(PresenterModule presenterModule, EntertainmentLandingPageContract.EntertainmentInteractor entertainmentInteractor) {
        return (EntertainmentLandingPageContract.Presenter) Preconditions.checkNotNull(presenterModule.provideEntertainmentLandingPagePresenter(entertainmentInteractor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EntertainmentLandingPageContract.Presenter mo10268get() {
        return provideInstance(this.module, this.entertainmentInteractorProvider);
    }
}
