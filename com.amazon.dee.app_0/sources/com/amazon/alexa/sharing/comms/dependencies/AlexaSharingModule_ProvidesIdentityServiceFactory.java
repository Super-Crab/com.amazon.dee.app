package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.alexa.identity.api.IdentityService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesIdentityServiceFactory implements Factory<IdentityService> {
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesIdentityServiceFactory(AlexaSharingModule alexaSharingModule) {
        this.module = alexaSharingModule;
    }

    public static AlexaSharingModule_ProvidesIdentityServiceFactory create(AlexaSharingModule alexaSharingModule) {
        return new AlexaSharingModule_ProvidesIdentityServiceFactory(alexaSharingModule);
    }

    public static IdentityService provideInstance(AlexaSharingModule alexaSharingModule) {
        return proxyProvidesIdentityService(alexaSharingModule);
    }

    public static IdentityService proxyProvidesIdentityService(AlexaSharingModule alexaSharingModule) {
        return (IdentityService) Preconditions.checkNotNull(alexaSharingModule.providesIdentityService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityService mo10268get() {
        return provideInstance(this.module);
    }
}
