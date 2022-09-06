package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.clouddrive.configuration.ClientConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvideClientConfigurationFactory implements Factory<ClientConfiguration> {
    private final AlexaSharingModule module;
    private final Provider<OkHttpClient> okHttpClientProvider;

    public AlexaSharingModule_ProvideClientConfigurationFactory(AlexaSharingModule alexaSharingModule, Provider<OkHttpClient> provider) {
        this.module = alexaSharingModule;
        this.okHttpClientProvider = provider;
    }

    public static AlexaSharingModule_ProvideClientConfigurationFactory create(AlexaSharingModule alexaSharingModule, Provider<OkHttpClient> provider) {
        return new AlexaSharingModule_ProvideClientConfigurationFactory(alexaSharingModule, provider);
    }

    public static ClientConfiguration provideInstance(AlexaSharingModule alexaSharingModule, Provider<OkHttpClient> provider) {
        return proxyProvideClientConfiguration(alexaSharingModule, provider.mo10268get());
    }

    public static ClientConfiguration proxyProvideClientConfiguration(AlexaSharingModule alexaSharingModule, OkHttpClient okHttpClient) {
        return (ClientConfiguration) Preconditions.checkNotNull(alexaSharingModule.provideClientConfiguration(okHttpClient), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ClientConfiguration mo10268get() {
        return provideInstance(this.module, this.okHttpClientProvider);
    }
}
