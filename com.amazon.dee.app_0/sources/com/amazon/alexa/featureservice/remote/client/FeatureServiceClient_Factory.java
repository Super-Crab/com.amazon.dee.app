package com.amazon.alexa.featureservice.remote.client;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.internal.Factory;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes7.dex */
public final class FeatureServiceClient_Factory implements Factory<FeatureServiceClient> {
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<OkHttpClient> okHttpClientProvider;

    public FeatureServiceClient_Factory(Provider<OkHttpClient> provider, Provider<EnvironmentService> provider2) {
        this.okHttpClientProvider = provider;
        this.environmentServiceProvider = provider2;
    }

    public static FeatureServiceClient_Factory create(Provider<OkHttpClient> provider, Provider<EnvironmentService> provider2) {
        return new FeatureServiceClient_Factory(provider, provider2);
    }

    public static FeatureServiceClient newFeatureServiceClient(OkHttpClient okHttpClient, EnvironmentService environmentService) {
        return new FeatureServiceClient(okHttpClient, environmentService);
    }

    public static FeatureServiceClient provideInstance(Provider<OkHttpClient> provider, Provider<EnvironmentService> provider2) {
        return new FeatureServiceClient(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceClient mo10268get() {
        return provideInstance(this.okHttpClientProvider, this.environmentServiceProvider);
    }
}
