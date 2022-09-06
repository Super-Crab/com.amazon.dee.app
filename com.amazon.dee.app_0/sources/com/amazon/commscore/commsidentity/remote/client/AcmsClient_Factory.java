package com.amazon.commscore.commsidentity.remote.client;

import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import dagger.internal.Factory;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes12.dex */
public final class AcmsClient_Factory implements Factory<AcmsClient> {
    private final Provider<OkHttpClient> okHttpClientProvider;
    private final Provider<AlexaCommsCoreRemoteConfigurationService> remoteConfigurationServiceProvider;

    public AcmsClient_Factory(Provider<OkHttpClient> provider, Provider<AlexaCommsCoreRemoteConfigurationService> provider2) {
        this.okHttpClientProvider = provider;
        this.remoteConfigurationServiceProvider = provider2;
    }

    public static AcmsClient_Factory create(Provider<OkHttpClient> provider, Provider<AlexaCommsCoreRemoteConfigurationService> provider2) {
        return new AcmsClient_Factory(provider, provider2);
    }

    public static AcmsClient newAcmsClient(OkHttpClient okHttpClient, AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService) {
        return new AcmsClient(okHttpClient, alexaCommsCoreRemoteConfigurationService);
    }

    public static AcmsClient provideInstance(Provider<OkHttpClient> provider, Provider<AlexaCommsCoreRemoteConfigurationService> provider2) {
        return new AcmsClient(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AcmsClient mo10268get() {
        return provideInstance(this.okHttpClientProvider, this.remoteConfigurationServiceProvider);
    }
}
