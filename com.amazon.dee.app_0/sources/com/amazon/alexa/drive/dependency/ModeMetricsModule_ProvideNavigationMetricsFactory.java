package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.metrics.NavigationMetrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class ModeMetricsModule_ProvideNavigationMetricsFactory implements Factory<NavigationMetrics> {
    private final ModeMetricsModule module;

    public ModeMetricsModule_ProvideNavigationMetricsFactory(ModeMetricsModule modeMetricsModule) {
        this.module = modeMetricsModule;
    }

    public static ModeMetricsModule_ProvideNavigationMetricsFactory create(ModeMetricsModule modeMetricsModule) {
        return new ModeMetricsModule_ProvideNavigationMetricsFactory(modeMetricsModule);
    }

    public static NavigationMetrics provideInstance(ModeMetricsModule modeMetricsModule) {
        return proxyProvideNavigationMetrics(modeMetricsModule);
    }

    public static NavigationMetrics proxyProvideNavigationMetrics(ModeMetricsModule modeMetricsModule) {
        return (NavigationMetrics) Preconditions.checkNotNull(modeMetricsModule.provideNavigationMetrics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NavigationMetrics mo10268get() {
        return provideInstance(this.module);
    }
}
