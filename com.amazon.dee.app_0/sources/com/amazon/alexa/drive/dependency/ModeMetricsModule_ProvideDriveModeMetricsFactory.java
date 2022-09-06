package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class ModeMetricsModule_ProvideDriveModeMetricsFactory implements Factory<DriveModeMetrics> {
    private final ModeMetricsModule module;

    public ModeMetricsModule_ProvideDriveModeMetricsFactory(ModeMetricsModule modeMetricsModule) {
        this.module = modeMetricsModule;
    }

    public static ModeMetricsModule_ProvideDriveModeMetricsFactory create(ModeMetricsModule modeMetricsModule) {
        return new ModeMetricsModule_ProvideDriveModeMetricsFactory(modeMetricsModule);
    }

    public static DriveModeMetrics provideInstance(ModeMetricsModule modeMetricsModule) {
        return proxyProvideDriveModeMetrics(modeMetricsModule);
    }

    public static DriveModeMetrics proxyProvideDriveModeMetrics(ModeMetricsModule modeMetricsModule) {
        return (DriveModeMetrics) Preconditions.checkNotNull(modeMetricsModule.provideDriveModeMetrics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeMetrics mo10268get() {
        return provideInstance(this.module);
    }
}
