package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.metrics.LandingPageMetrics;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.mode.ModeService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class ModeMetricsModule_ProvideMetricsHelperFactory implements Factory<DriveModeMetricsHelper> {
    private final Provider<DriverDistractionLog> driverDistractionLogProvider;
    private final Provider<LandingPageMetrics> landingPageMetricsProvider;
    private final Provider<ModeService> modeServiceProvider;
    private final ModeMetricsModule module;

    public ModeMetricsModule_ProvideMetricsHelperFactory(ModeMetricsModule modeMetricsModule, Provider<LandingPageMetrics> provider, Provider<DriverDistractionLog> provider2, Provider<ModeService> provider3) {
        this.module = modeMetricsModule;
        this.landingPageMetricsProvider = provider;
        this.driverDistractionLogProvider = provider2;
        this.modeServiceProvider = provider3;
    }

    public static ModeMetricsModule_ProvideMetricsHelperFactory create(ModeMetricsModule modeMetricsModule, Provider<LandingPageMetrics> provider, Provider<DriverDistractionLog> provider2, Provider<ModeService> provider3) {
        return new ModeMetricsModule_ProvideMetricsHelperFactory(modeMetricsModule, provider, provider2, provider3);
    }

    public static DriveModeMetricsHelper provideInstance(ModeMetricsModule modeMetricsModule, Provider<LandingPageMetrics> provider, Provider<DriverDistractionLog> provider2, Provider<ModeService> provider3) {
        return proxyProvideMetricsHelper(modeMetricsModule, DoubleCheck.lazy(provider), provider2.mo10268get(), provider3.mo10268get());
    }

    public static DriveModeMetricsHelper proxyProvideMetricsHelper(ModeMetricsModule modeMetricsModule, Lazy<LandingPageMetrics> lazy, DriverDistractionLog driverDistractionLog, ModeService modeService) {
        return (DriveModeMetricsHelper) Preconditions.checkNotNull(modeMetricsModule.provideMetricsHelper(lazy, driverDistractionLog, modeService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeMetricsHelper mo10268get() {
        return provideInstance(this.module, this.landingPageMetricsProvider, this.driverDistractionLogProvider, this.modeServiceProvider);
    }
}
