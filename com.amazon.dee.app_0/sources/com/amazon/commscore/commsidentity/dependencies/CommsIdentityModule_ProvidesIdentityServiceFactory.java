package com.amazon.commscore.commsidentity.dependencies;

import com.amazon.alexa.identity.api.IdentityService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class CommsIdentityModule_ProvidesIdentityServiceFactory implements Factory<IdentityService> {
    private final CommsIdentityModule module;

    public CommsIdentityModule_ProvidesIdentityServiceFactory(CommsIdentityModule commsIdentityModule) {
        this.module = commsIdentityModule;
    }

    public static CommsIdentityModule_ProvidesIdentityServiceFactory create(CommsIdentityModule commsIdentityModule) {
        return new CommsIdentityModule_ProvidesIdentityServiceFactory(commsIdentityModule);
    }

    public static IdentityService provideInstance(CommsIdentityModule commsIdentityModule) {
        return proxyProvidesIdentityService(commsIdentityModule);
    }

    public static IdentityService proxyProvidesIdentityService(CommsIdentityModule commsIdentityModule) {
        return (IdentityService) Preconditions.checkNotNull(commsIdentityModule.providesIdentityService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityService mo10268get() {
        return provideInstance(this.module);
    }
}
