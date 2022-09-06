package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class BaseModule_ProvidesEnvironmentServiceFactory implements Factory<EnvironmentService> {
    private final BaseModule module;

    public BaseModule_ProvidesEnvironmentServiceFactory(BaseModule baseModule) {
        this.module = baseModule;
    }

    public static BaseModule_ProvidesEnvironmentServiceFactory create(BaseModule baseModule) {
        return new BaseModule_ProvidesEnvironmentServiceFactory(baseModule);
    }

    public static EnvironmentService provideInstance(BaseModule baseModule) {
        return proxyProvidesEnvironmentService(baseModule);
    }

    public static EnvironmentService proxyProvidesEnvironmentService(BaseModule baseModule) {
        return (EnvironmentService) Preconditions.checkNotNull(baseModule.providesEnvironmentService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnvironmentService mo10268get() {
        return provideInstance(this.module);
    }
}
