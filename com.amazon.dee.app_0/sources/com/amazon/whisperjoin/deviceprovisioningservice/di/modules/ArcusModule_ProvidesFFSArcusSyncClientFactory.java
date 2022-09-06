package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusClient;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ArcusModule_ProvidesFFSArcusSyncClientFactory implements Factory<FFSArcusClient> {
    private final ArcusModule module;
    private final Provider<RemoteConfigurationManager> remoteConfigurationManagerProvider;

    public ArcusModule_ProvidesFFSArcusSyncClientFactory(ArcusModule arcusModule, Provider<RemoteConfigurationManager> provider) {
        this.module = arcusModule;
        this.remoteConfigurationManagerProvider = provider;
    }

    public static ArcusModule_ProvidesFFSArcusSyncClientFactory create(ArcusModule arcusModule, Provider<RemoteConfigurationManager> provider) {
        return new ArcusModule_ProvidesFFSArcusSyncClientFactory(arcusModule, provider);
    }

    public static FFSArcusClient provideInstance(ArcusModule arcusModule, Provider<RemoteConfigurationManager> provider) {
        return proxyProvidesFFSArcusSyncClient(arcusModule, provider.mo10268get());
    }

    public static FFSArcusClient proxyProvidesFFSArcusSyncClient(ArcusModule arcusModule, RemoteConfigurationManager remoteConfigurationManager) {
        return (FFSArcusClient) Preconditions.checkNotNull(arcusModule.providesFFSArcusSyncClient(remoteConfigurationManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FFSArcusClient mo10268get() {
        return provideInstance(this.module, this.remoteConfigurationManagerProvider);
    }
}
