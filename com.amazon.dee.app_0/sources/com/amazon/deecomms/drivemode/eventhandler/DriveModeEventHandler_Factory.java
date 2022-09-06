package com.amazon.deecomms.drivemode.eventhandler;

import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DriveModeEventHandler_Factory implements Factory<DriveModeEventHandler> {
    private final Provider<DriveModeSharedPreferencesUseCase> driveModeSharedPreferencesUseCaseProvider;
    private final Provider<MetricsService> metricsServiceProvider;

    public DriveModeEventHandler_Factory(Provider<DriveModeSharedPreferencesUseCase> provider, Provider<MetricsService> provider2) {
        this.driveModeSharedPreferencesUseCaseProvider = provider;
        this.metricsServiceProvider = provider2;
    }

    public static DriveModeEventHandler_Factory create(Provider<DriveModeSharedPreferencesUseCase> provider, Provider<MetricsService> provider2) {
        return new DriveModeEventHandler_Factory(provider, provider2);
    }

    public static DriveModeEventHandler newDriveModeEventHandler(DriveModeSharedPreferencesUseCase driveModeSharedPreferencesUseCase, MetricsService metricsService) {
        return new DriveModeEventHandler(driveModeSharedPreferencesUseCase, metricsService);
    }

    public static DriveModeEventHandler provideInstance(Provider<DriveModeSharedPreferencesUseCase> provider, Provider<MetricsService> provider2) {
        return new DriveModeEventHandler(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeEventHandler mo10268get() {
        return provideInstance(this.driveModeSharedPreferencesUseCaseProvider, this.metricsServiceProvider);
    }
}
