package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.comms.contract.CommsLandingPageContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class PresenterModule_ProvideCommsLandingPagePresenterFactory implements Factory<CommsLandingPageContract.Presenter> {
    private final Provider<CommsLandingPageContract.Interactor> interactorProvider;
    private final PresenterModule module;

    public PresenterModule_ProvideCommsLandingPagePresenterFactory(PresenterModule presenterModule, Provider<CommsLandingPageContract.Interactor> provider) {
        this.module = presenterModule;
        this.interactorProvider = provider;
    }

    public static PresenterModule_ProvideCommsLandingPagePresenterFactory create(PresenterModule presenterModule, Provider<CommsLandingPageContract.Interactor> provider) {
        return new PresenterModule_ProvideCommsLandingPagePresenterFactory(presenterModule, provider);
    }

    public static CommsLandingPageContract.Presenter provideInstance(PresenterModule presenterModule, Provider<CommsLandingPageContract.Interactor> provider) {
        return proxyProvideCommsLandingPagePresenter(presenterModule, provider.mo10268get());
    }

    public static CommsLandingPageContract.Presenter proxyProvideCommsLandingPagePresenter(PresenterModule presenterModule, CommsLandingPageContract.Interactor interactor) {
        return (CommsLandingPageContract.Presenter) Preconditions.checkNotNull(presenterModule.provideCommsLandingPagePresenter(interactor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsLandingPageContract.Presenter mo10268get() {
        return provideInstance(this.module, this.interactorProvider);
    }
}
