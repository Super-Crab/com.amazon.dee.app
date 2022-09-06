package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.remote.client.FeatureServiceClient;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes7.dex */
public final class NetworkModule_ProvidesFeatureServiceClientFactory implements Factory<FeatureServiceClient> {
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final NetworkModule module;
    private final Provider<OkHttpClient> okHttpClientProvider;

    public NetworkModule_ProvidesFeatureServiceClientFactory(NetworkModule networkModule, Provider<OkHttpClient> provider, Provider<EnvironmentService> provider2) {
        this.module = networkModule;
        this.okHttpClientProvider = provider;
        this.environmentServiceProvider = provider2;
    }

    public static NetworkModule_ProvidesFeatureServiceClientFactory create(NetworkModule networkModule, Provider<OkHttpClient> provider, Provider<EnvironmentService> provider2) {
        return new NetworkModule_ProvidesFeatureServiceClientFactory(networkModule, provider, provider2);
    }

    public static FeatureServiceClient provideInstance(NetworkModule networkModule, Provider<OkHttpClient> provider, Provider<EnvironmentService> provider2) {
        return proxyProvidesFeatureServiceClient(networkModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static FeatureServiceClient proxyProvidesFeatureServiceClient(NetworkModule networkModule, OkHttpClient okHttpClient, EnvironmentService environmentService) {
        return (FeatureServiceClient) Preconditions.checkNotNull(networkModule.providesFeatureServiceClient(okHttpClient, environmentService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceClient mo10268get() {
        return provideInstance(this.module, this.okHttpClientProvider, this.environmentServiceProvider);
    }
}
