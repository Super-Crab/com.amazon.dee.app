package com.amazon.commscore.commsidentity.dependencies;

import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class CommsIdentityModule_ProvidesConfigServiceFactory implements Factory<AlexaCommsCoreRemoteConfigurationService> {
    private final CommsIdentityModule module;

    public CommsIdentityModule_ProvidesConfigServiceFactory(CommsIdentityModule commsIdentityModule) {
        this.module = commsIdentityModule;
    }

    public static CommsIdentityModule_ProvidesConfigServiceFactory create(CommsIdentityModule commsIdentityModule) {
        return new CommsIdentityModule_ProvidesConfigServiceFactory(commsIdentityModule);
    }

    public static AlexaCommsCoreRemoteConfigurationService provideInstance(CommsIdentityModule commsIdentityModule) {
        return proxyProvidesConfigService(commsIdentityModule);
    }

    public static AlexaCommsCoreRemoteConfigurationService proxyProvidesConfigService(CommsIdentityModule commsIdentityModule) {
        return (AlexaCommsCoreRemoteConfigurationService) Preconditions.checkNotNull(commsIdentityModule.providesConfigService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaCommsCoreRemoteConfigurationService mo10268get() {
        return provideInstance(this.module);
    }
}
