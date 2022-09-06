package com.amazon.deecomms.core;

import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideClientConfigurationFactory implements Factory<ClientConfiguration> {
    private final Provider<ArcusConfig> arcusConfigProvider;
    private final CloudDriveModule module;
    private final Provider<OkHttpClientWrapper> okHttpClientWrapperProvider;

    public CloudDriveModule_ProvideClientConfigurationFactory(CloudDriveModule cloudDriveModule, Provider<ArcusConfig> provider, Provider<OkHttpClientWrapper> provider2) {
        this.module = cloudDriveModule;
        this.arcusConfigProvider = provider;
        this.okHttpClientWrapperProvider = provider2;
    }

    public static CloudDriveModule_ProvideClientConfigurationFactory create(CloudDriveModule cloudDriveModule, Provider<ArcusConfig> provider, Provider<OkHttpClientWrapper> provider2) {
        return new CloudDriveModule_ProvideClientConfigurationFactory(cloudDriveModule, provider, provider2);
    }

    public static ClientConfiguration provideInstance(CloudDriveModule cloudDriveModule, Provider<ArcusConfig> provider, Provider<OkHttpClientWrapper> provider2) {
        return (ClientConfiguration) Preconditions.checkNotNull(cloudDriveModule.provideClientConfiguration(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ClientConfiguration proxyProvideClientConfiguration(CloudDriveModule cloudDriveModule, ArcusConfig arcusConfig, OkHttpClientWrapper okHttpClientWrapper) {
        return (ClientConfiguration) Preconditions.checkNotNull(cloudDriveModule.provideClientConfiguration(arcusConfig, okHttpClientWrapper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ClientConfiguration mo10268get() {
        return provideInstance(this.module, this.arcusConfigProvider, this.okHttpClientWrapperProvider);
    }
}
