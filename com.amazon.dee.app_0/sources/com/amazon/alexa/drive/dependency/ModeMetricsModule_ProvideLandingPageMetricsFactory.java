package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.metrics.LandingPageMetrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class ModeMetricsModule_ProvideLandingPageMetricsFactory implements Factory<LandingPageMetrics> {
    private final ModeMetricsModule module;

    public ModeMetricsModule_ProvideLandingPageMetricsFactory(ModeMetricsModule modeMetricsModule) {
        this.module = modeMetricsModule;
    }

    public static ModeMetricsModule_ProvideLandingPageMetricsFactory create(ModeMetricsModule modeMetricsModule) {
        return new ModeMetricsModule_ProvideLandingPageMetricsFactory(modeMetricsModule);
    }

    public static LandingPageMetrics provideInstance(ModeMetricsModule modeMetricsModule) {
        return proxyProvideLandingPageMetrics(modeMetricsModule);
    }

    public static LandingPageMetrics proxyProvideLandingPageMetrics(ModeMetricsModule modeMetricsModule) {
        return (LandingPageMetrics) Preconditions.checkNotNull(modeMetricsModule.provideLandingPageMetrics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LandingPageMetrics mo10268get() {
        return provideInstance(this.module);
    }
}
