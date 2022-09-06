package com.amazon.alexa.voice.app;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class ApplicationModule_ProvideEnvironmentServiceFactory implements Factory<EnvironmentService> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideEnvironmentServiceFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideEnvironmentServiceFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideEnvironmentServiceFactory(applicationModule);
    }

    public static EnvironmentService provideInstance(ApplicationModule applicationModule) {
        return proxyProvideEnvironmentService(applicationModule);
    }

    public static EnvironmentService proxyProvideEnvironmentService(ApplicationModule applicationModule) {
        return (EnvironmentService) Preconditions.checkNotNull(applicationModule.provideEnvironmentService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnvironmentService mo10268get() {
        return provideInstance(this.module);
    }
}
