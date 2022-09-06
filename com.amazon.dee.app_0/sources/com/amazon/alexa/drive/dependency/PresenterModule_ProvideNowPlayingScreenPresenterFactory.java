package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class PresenterModule_ProvideNowPlayingScreenPresenterFactory implements Factory<NowPlayingScreenContract.Presenter> {
    private final Provider<NowPlayingScreenContract.Interactor> interactorProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideNowPlayingScreenPresenterFactory(PresenterModule presenterModule, Provider<NowPlayingScreenContract.Interactor> provider) {
        this.module = presenterModule;
        this.interactorProvider = provider;
    }

    public static PresenterModule_ProvideNowPlayingScreenPresenterFactory create(PresenterModule presenterModule, Provider<NowPlayingScreenContract.Interactor> provider) {
        return new PresenterModule_ProvideNowPlayingScreenPresenterFactory(presenterModule, provider);
    }

    public static NowPlayingScreenContract.Presenter provideInstance(PresenterModule presenterModule, Provider<NowPlayingScreenContract.Interactor> provider) {
        return proxyProvideNowPlayingScreenPresenter(presenterModule, provider.mo10268get());
    }

    public static NowPlayingScreenContract.Presenter proxyProvideNowPlayingScreenPresenter(PresenterModule presenterModule, NowPlayingScreenContract.Interactor interactor) {
        return (NowPlayingScreenContract.Presenter) Preconditions.checkNotNull(presenterModule.provideNowPlayingScreenPresenter(interactor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NowPlayingScreenContract.Presenter mo10268get() {
        return provideInstance(this.module, this.interactorProvider);
    }
}
