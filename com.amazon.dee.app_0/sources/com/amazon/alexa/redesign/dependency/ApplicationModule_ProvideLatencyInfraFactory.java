package com.amazon.alexa.redesign.dependency;

import com.amazon.latencyinfra.LatencyInfra;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideLatencyInfraFactory implements Factory<LatencyInfra> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideLatencyInfraFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideLatencyInfraFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideLatencyInfraFactory(applicationModule);
    }

    public static LatencyInfra provideInstance(ApplicationModule applicationModule) {
        return proxyProvideLatencyInfra(applicationModule);
    }

    public static LatencyInfra proxyProvideLatencyInfra(ApplicationModule applicationModule) {
        return (LatencyInfra) Preconditions.checkNotNull(applicationModule.provideLatencyInfra(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LatencyInfra mo10268get() {
        return provideInstance(this.module);
    }
}
