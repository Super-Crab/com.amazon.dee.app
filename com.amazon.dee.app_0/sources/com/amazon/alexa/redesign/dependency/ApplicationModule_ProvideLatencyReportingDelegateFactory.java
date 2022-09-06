package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.redesign.LatencyReportingDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideLatencyReportingDelegateFactory implements Factory<LatencyReportingDelegate> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideLatencyReportingDelegateFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideLatencyReportingDelegateFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideLatencyReportingDelegateFactory(applicationModule);
    }

    public static LatencyReportingDelegate provideInstance(ApplicationModule applicationModule) {
        return proxyProvideLatencyReportingDelegate(applicationModule);
    }

    public static LatencyReportingDelegate proxyProvideLatencyReportingDelegate(ApplicationModule applicationModule) {
        return (LatencyReportingDelegate) Preconditions.checkNotNull(applicationModule.provideLatencyReportingDelegate(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LatencyReportingDelegate mo10268get() {
        return provideInstance(this.module);
    }
}
