package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesCommsConfigServiceFactory implements Factory<AlexaCommsCoreRemoteConfigurationService> {
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesCommsConfigServiceFactory(AlexaSharingModule alexaSharingModule) {
        this.module = alexaSharingModule;
    }

    public static AlexaSharingModule_ProvidesCommsConfigServiceFactory create(AlexaSharingModule alexaSharingModule) {
        return new AlexaSharingModule_ProvidesCommsConfigServiceFactory(alexaSharingModule);
    }

    public static AlexaCommsCoreRemoteConfigurationService provideInstance(AlexaSharingModule alexaSharingModule) {
        return proxyProvidesCommsConfigService(alexaSharingModule);
    }

    public static AlexaCommsCoreRemoteConfigurationService proxyProvidesCommsConfigService(AlexaSharingModule alexaSharingModule) {
        return (AlexaCommsCoreRemoteConfigurationService) Preconditions.checkNotNull(alexaSharingModule.providesCommsConfigService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaCommsCoreRemoteConfigurationService mo10268get() {
        return provideInstance(this.module);
    }
}
