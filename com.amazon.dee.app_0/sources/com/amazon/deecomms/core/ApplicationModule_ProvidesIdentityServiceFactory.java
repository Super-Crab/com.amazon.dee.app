package com.amazon.deecomms.core;

import com.amazon.alexa.identity.api.IdentityService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvidesIdentityServiceFactory implements Factory<IdentityService> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesIdentityServiceFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesIdentityServiceFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesIdentityServiceFactory(applicationModule);
    }

    public static IdentityService provideInstance(ApplicationModule applicationModule) {
        return (IdentityService) Preconditions.checkNotNull(applicationModule.providesIdentityService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static IdentityService proxyProvidesIdentityService(ApplicationModule applicationModule) {
        return (IdentityService) Preconditions.checkNotNull(applicationModule.providesIdentityService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityService mo10268get() {
        return provideInstance(this.module);
    }
}
