package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class ModeMetricsModule_ProvideEntertainmentnMetricsFactory implements Factory<EntertainmentMetrics> {
    private final ModeMetricsModule module;

    public ModeMetricsModule_ProvideEntertainmentnMetricsFactory(ModeMetricsModule modeMetricsModule) {
        this.module = modeMetricsModule;
    }

    public static ModeMetricsModule_ProvideEntertainmentnMetricsFactory create(ModeMetricsModule modeMetricsModule) {
        return new ModeMetricsModule_ProvideEntertainmentnMetricsFactory(modeMetricsModule);
    }

    public static EntertainmentMetrics provideInstance(ModeMetricsModule modeMetricsModule) {
        return proxyProvideEntertainmentnMetrics(modeMetricsModule);
    }

    public static EntertainmentMetrics proxyProvideEntertainmentnMetrics(ModeMetricsModule modeMetricsModule) {
        return (EntertainmentMetrics) Preconditions.checkNotNull(modeMetricsModule.provideEntertainmentnMetrics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EntertainmentMetrics mo10268get() {
        return provideInstance(this.module);
    }
}
