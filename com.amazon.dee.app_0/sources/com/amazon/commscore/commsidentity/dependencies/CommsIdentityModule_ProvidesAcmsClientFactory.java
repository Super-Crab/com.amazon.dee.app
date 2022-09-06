package com.amazon.commscore.commsidentity.dependencies;

import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commscore.commsidentity.remote.client.AcmsClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes12.dex */
public final class CommsIdentityModule_ProvidesAcmsClientFactory implements Factory<AcmsClient> {
    private final Provider<AlexaCommsCoreRemoteConfigurationService> alexaCommsCoreRemoteConfigurationServiceProvider;
    private final CommsIdentityModule module;
    private final Provider<OkHttpClient> okHttpClientProvider;

    public CommsIdentityModule_ProvidesAcmsClientFactory(CommsIdentityModule commsIdentityModule, Provider<OkHttpClient> provider, Provider<AlexaCommsCoreRemoteConfigurationService> provider2) {
        this.module = commsIdentityModule;
        this.okHttpClientProvider = provider;
        this.alexaCommsCoreRemoteConfigurationServiceProvider = provider2;
    }

    public static CommsIdentityModule_ProvidesAcmsClientFactory create(CommsIdentityModule commsIdentityModule, Provider<OkHttpClient> provider, Provider<AlexaCommsCoreRemoteConfigurationService> provider2) {
        return new CommsIdentityModule_ProvidesAcmsClientFactory(commsIdentityModule, provider, provider2);
    }

    public static AcmsClient provideInstance(CommsIdentityModule commsIdentityModule, Provider<OkHttpClient> provider, Provider<AlexaCommsCoreRemoteConfigurationService> provider2) {
        return proxyProvidesAcmsClient(commsIdentityModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static AcmsClient proxyProvidesAcmsClient(CommsIdentityModule commsIdentityModule, OkHttpClient okHttpClient, AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService) {
        return (AcmsClient) Preconditions.checkNotNull(commsIdentityModule.providesAcmsClient(okHttpClient, alexaCommsCoreRemoteConfigurationService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AcmsClient mo10268get() {
        return provideInstance(this.module, this.okHttpClientProvider, this.alexaCommsCoreRemoteConfigurationServiceProvider);
    }
}
