package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.drive.comms.CommsManager;
import com.amazon.alexa.drive.comms.contract.CommsLandingPageContract;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideCommsManagerFactory implements Factory<CommsManager> {
    private final Provider<CommsLandingPageContract.Interactor> commsInteractorProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DriveModeThemeManager> driveModeThemeManagerProvider;
    private final RepositoryModule module;
    private final Provider<WeblabManager> weblabManagerProvider;

    public RepositoryModule_ProvideCommsManagerFactory(RepositoryModule repositoryModule, Provider<Context> provider, Provider<CommsLandingPageContract.Interactor> provider2, Provider<DriveModeThemeManager> provider3, Provider<WeblabManager> provider4) {
        this.module = repositoryModule;
        this.contextProvider = provider;
        this.commsInteractorProvider = provider2;
        this.driveModeThemeManagerProvider = provider3;
        this.weblabManagerProvider = provider4;
    }

    public static RepositoryModule_ProvideCommsManagerFactory create(RepositoryModule repositoryModule, Provider<Context> provider, Provider<CommsLandingPageContract.Interactor> provider2, Provider<DriveModeThemeManager> provider3, Provider<WeblabManager> provider4) {
        return new RepositoryModule_ProvideCommsManagerFactory(repositoryModule, provider, provider2, provider3, provider4);
    }

    public static CommsManager provideInstance(RepositoryModule repositoryModule, Provider<Context> provider, Provider<CommsLandingPageContract.Interactor> provider2, Provider<DriveModeThemeManager> provider3, Provider<WeblabManager> provider4) {
        return proxyProvideCommsManager(repositoryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static CommsManager proxyProvideCommsManager(RepositoryModule repositoryModule, Context context, CommsLandingPageContract.Interactor interactor, DriveModeThemeManager driveModeThemeManager, WeblabManager weblabManager) {
        return (CommsManager) Preconditions.checkNotNull(repositoryModule.provideCommsManager(context, interactor, driveModeThemeManager, weblabManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.commsInteractorProvider, this.driveModeThemeManagerProvider, this.weblabManagerProvider);
    }
}
