package com.amazon.alexa.mode.dependencies;

import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class ModeModule_ProvideDriveModeMetricsFactory implements Factory<DriveModeMetrics> {
    private final ModeModule module;

    public ModeModule_ProvideDriveModeMetricsFactory(ModeModule modeModule) {
        this.module = modeModule;
    }

    public static ModeModule_ProvideDriveModeMetricsFactory create(ModeModule modeModule) {
        return new ModeModule_ProvideDriveModeMetricsFactory(modeModule);
    }

    public static DriveModeMetrics provideInstance(ModeModule modeModule) {
        return proxyProvideDriveModeMetrics(modeModule);
    }

    public static DriveModeMetrics proxyProvideDriveModeMetrics(ModeModule modeModule) {
        return (DriveModeMetrics) Preconditions.checkNotNull(modeModule.provideDriveModeMetrics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeMetrics mo10268get() {
        return provideInstance(this.module);
    }
}
