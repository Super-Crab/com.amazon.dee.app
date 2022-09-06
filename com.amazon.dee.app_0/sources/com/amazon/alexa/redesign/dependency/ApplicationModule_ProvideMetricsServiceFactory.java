package com.amazon.alexa.redesign.dependency;

import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideMetricsServiceFactory implements Factory<MetricsServiceV2> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideMetricsServiceFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideMetricsServiceFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideMetricsServiceFactory(applicationModule);
    }

    public static MetricsServiceV2 provideInstance(ApplicationModule applicationModule) {
        return proxyProvideMetricsService(applicationModule);
    }

    public static MetricsServiceV2 proxyProvideMetricsService(ApplicationModule applicationModule) {
        return (MetricsServiceV2) Preconditions.checkNotNull(applicationModule.provideMetricsService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsServiceV2 mo10268get() {
        return provideInstance(this.module);
    }
}
