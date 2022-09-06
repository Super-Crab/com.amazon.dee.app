package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.main.DriveModeMainContract;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.weblab.WeblabManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class PresenterModule_ProvideMainPresenterFactory implements Factory<DriveModeMainContract.Presenter> {
    private final Provider<DriveModeMetricsHelper> driveModeMetricsHelperProvider;
    private final PresenterModule module;
    private final Provider<WeblabManager> weblabManagerProvider;

    public PresenterModule_ProvideMainPresenterFactory(PresenterModule presenterModule, Provider<DriveModeMetricsHelper> provider, Provider<WeblabManager> provider2) {
        this.module = presenterModule;
        this.driveModeMetricsHelperProvider = provider;
        this.weblabManagerProvider = provider2;
    }

    public static PresenterModule_ProvideMainPresenterFactory create(PresenterModule presenterModule, Provider<DriveModeMetricsHelper> provider, Provider<WeblabManager> provider2) {
        return new PresenterModule_ProvideMainPresenterFactory(presenterModule, provider, provider2);
    }

    public static DriveModeMainContract.Presenter provideInstance(PresenterModule presenterModule, Provider<DriveModeMetricsHelper> provider, Provider<WeblabManager> provider2) {
        return proxyProvideMainPresenter(presenterModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static DriveModeMainContract.Presenter proxyProvideMainPresenter(PresenterModule presenterModule, DriveModeMetricsHelper driveModeMetricsHelper, WeblabManager weblabManager) {
        return (DriveModeMainContract.Presenter) Preconditions.checkNotNull(presenterModule.provideMainPresenter(driveModeMetricsHelper, weblabManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeMainContract.Presenter mo10268get() {
        return provideInstance(this.module, this.driveModeMetricsHelperProvider, this.weblabManagerProvider);
    }
}
