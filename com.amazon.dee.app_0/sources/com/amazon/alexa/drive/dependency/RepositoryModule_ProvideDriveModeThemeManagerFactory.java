package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.drive.theme.SunriseTimeProvider;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideDriveModeThemeManagerFactory implements Factory<DriveModeThemeManager> {
    private final Provider<Context> contextProvider;
    private final Provider<DriveModeMetrics> driveModeMetricsProvider;
    private final RepositoryModule module;
    private final Provider<AlexaServicesConnection> servicesConnectionProvider;
    private final Provider<SunriseTimeProvider> sunriseTimeProvider;

    public RepositoryModule_ProvideDriveModeThemeManagerFactory(RepositoryModule repositoryModule, Provider<Context> provider, Provider<AlexaServicesConnection> provider2, Provider<DriveModeMetrics> provider3, Provider<SunriseTimeProvider> provider4) {
        this.module = repositoryModule;
        this.contextProvider = provider;
        this.servicesConnectionProvider = provider2;
        this.driveModeMetricsProvider = provider3;
        this.sunriseTimeProvider = provider4;
    }

    public static RepositoryModule_ProvideDriveModeThemeManagerFactory create(RepositoryModule repositoryModule, Provider<Context> provider, Provider<AlexaServicesConnection> provider2, Provider<DriveModeMetrics> provider3, Provider<SunriseTimeProvider> provider4) {
        return new RepositoryModule_ProvideDriveModeThemeManagerFactory(repositoryModule, provider, provider2, provider3, provider4);
    }

    public static DriveModeThemeManager provideInstance(RepositoryModule repositoryModule, Provider<Context> provider, Provider<AlexaServicesConnection> provider2, Provider<DriveModeMetrics> provider3, Provider<SunriseTimeProvider> provider4) {
        return proxyProvideDriveModeThemeManager(repositoryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static DriveModeThemeManager proxyProvideDriveModeThemeManager(RepositoryModule repositoryModule, Context context, AlexaServicesConnection alexaServicesConnection, DriveModeMetrics driveModeMetrics, SunriseTimeProvider sunriseTimeProvider) {
        return (DriveModeThemeManager) Preconditions.checkNotNull(repositoryModule.provideDriveModeThemeManager(context, alexaServicesConnection, driveModeMetrics, sunriseTimeProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeThemeManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.servicesConnectionProvider, this.driveModeMetricsProvider, this.sunriseTimeProvider);
    }
}
