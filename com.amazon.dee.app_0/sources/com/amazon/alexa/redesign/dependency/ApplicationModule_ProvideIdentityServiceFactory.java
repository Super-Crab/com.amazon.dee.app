package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.identity.api.IdentityService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideIdentityServiceFactory implements Factory<IdentityService> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideIdentityServiceFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideIdentityServiceFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideIdentityServiceFactory(applicationModule);
    }

    public static IdentityService provideInstance(ApplicationModule applicationModule) {
        return proxyProvideIdentityService(applicationModule);
    }

    public static IdentityService proxyProvideIdentityService(ApplicationModule applicationModule) {
        return (IdentityService) Preconditions.checkNotNull(applicationModule.provideIdentityService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityService mo10268get() {
        return provideInstance(this.module);
    }
}
