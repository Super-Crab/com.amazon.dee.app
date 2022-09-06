package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesCommsIdentityServiceFactory implements Factory<AlexaCommsCoreIdentityService> {
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesCommsIdentityServiceFactory(AlexaSharingModule alexaSharingModule) {
        this.module = alexaSharingModule;
    }

    public static AlexaSharingModule_ProvidesCommsIdentityServiceFactory create(AlexaSharingModule alexaSharingModule) {
        return new AlexaSharingModule_ProvidesCommsIdentityServiceFactory(alexaSharingModule);
    }

    public static AlexaCommsCoreIdentityService provideInstance(AlexaSharingModule alexaSharingModule) {
        return proxyProvidesCommsIdentityService(alexaSharingModule);
    }

    public static AlexaCommsCoreIdentityService proxyProvidesCommsIdentityService(AlexaSharingModule alexaSharingModule) {
        return (AlexaCommsCoreIdentityService) Preconditions.checkNotNull(alexaSharingModule.providesCommsIdentityService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaCommsCoreIdentityService mo10268get() {
        return provideInstance(this.module);
    }
}
