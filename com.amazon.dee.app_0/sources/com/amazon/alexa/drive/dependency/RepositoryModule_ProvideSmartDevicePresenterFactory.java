package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.drive.smart.device.SmartDevicePresenter;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideSmartDevicePresenterFactory implements Factory<SmartDevicePresenter> {
    private final Provider<Context> contextProvider;
    private final Provider<DriveModeThemeManager> driveModeThemeManagerProvider;
    private final RepositoryModule module;
    private final Provider<WeblabManager> weblabManagerProvider;

    public RepositoryModule_ProvideSmartDevicePresenterFactory(RepositoryModule repositoryModule, Provider<Context> provider, Provider<DriveModeThemeManager> provider2, Provider<WeblabManager> provider3) {
        this.module = repositoryModule;
        this.contextProvider = provider;
        this.driveModeThemeManagerProvider = provider2;
        this.weblabManagerProvider = provider3;
    }

    public static RepositoryModule_ProvideSmartDevicePresenterFactory create(RepositoryModule repositoryModule, Provider<Context> provider, Provider<DriveModeThemeManager> provider2, Provider<WeblabManager> provider3) {
        return new RepositoryModule_ProvideSmartDevicePresenterFactory(repositoryModule, provider, provider2, provider3);
    }

    public static SmartDevicePresenter provideInstance(RepositoryModule repositoryModule, Provider<Context> provider, Provider<DriveModeThemeManager> provider2, Provider<WeblabManager> provider3) {
        return proxyProvideSmartDevicePresenter(repositoryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static SmartDevicePresenter proxyProvideSmartDevicePresenter(RepositoryModule repositoryModule, Context context, DriveModeThemeManager driveModeThemeManager, WeblabManager weblabManager) {
        return (SmartDevicePresenter) Preconditions.checkNotNull(repositoryModule.provideSmartDevicePresenter(context, driveModeThemeManager, weblabManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SmartDevicePresenter mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.driveModeThemeManagerProvider, this.weblabManagerProvider);
    }
}
