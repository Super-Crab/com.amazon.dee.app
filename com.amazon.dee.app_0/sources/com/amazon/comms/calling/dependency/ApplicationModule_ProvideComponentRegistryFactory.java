package com.amazon.comms.calling.dependency;

import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideComponentRegistryFactory implements Factory<ComponentRegistry> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideComponentRegistryFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideComponentRegistryFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideComponentRegistryFactory(applicationModule);
    }

    public static ComponentRegistry provideInstance(ApplicationModule applicationModule) {
        return proxyProvideComponentRegistry(applicationModule);
    }

    public static ComponentRegistry proxyProvideComponentRegistry(ApplicationModule applicationModule) {
        return (ComponentRegistry) Preconditions.checkNotNull(applicationModule.provideComponentRegistry(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ComponentRegistry mo10268get() {
        return provideInstance(this.module);
    }
}
