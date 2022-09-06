package com.amazon.commscore.commsidentity.dependencies;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class CommsIdentityModule_ProvidesEnvironmentServiceFactory implements Factory<EnvironmentService> {
    private final CommsIdentityModule module;

    public CommsIdentityModule_ProvidesEnvironmentServiceFactory(CommsIdentityModule commsIdentityModule) {
        this.module = commsIdentityModule;
    }

    public static CommsIdentityModule_ProvidesEnvironmentServiceFactory create(CommsIdentityModule commsIdentityModule) {
        return new CommsIdentityModule_ProvidesEnvironmentServiceFactory(commsIdentityModule);
    }

    public static EnvironmentService provideInstance(CommsIdentityModule commsIdentityModule) {
        return proxyProvidesEnvironmentService(commsIdentityModule);
    }

    public static EnvironmentService proxyProvidesEnvironmentService(CommsIdentityModule commsIdentityModule) {
        return (EnvironmentService) Preconditions.checkNotNull(commsIdentityModule.providesEnvironmentService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnvironmentService mo10268get() {
        return provideInstance(this.module);
    }
}
