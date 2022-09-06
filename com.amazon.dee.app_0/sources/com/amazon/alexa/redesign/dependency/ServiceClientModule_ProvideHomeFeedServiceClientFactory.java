package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class ServiceClientModule_ProvideHomeFeedServiceClientFactory implements Factory<HomeFeedServiceClient> {
    private final Provider<CoralService> coralServiceProvider;
    private final ServiceClientModule module;

    public ServiceClientModule_ProvideHomeFeedServiceClientFactory(ServiceClientModule serviceClientModule, Provider<CoralService> provider) {
        this.module = serviceClientModule;
        this.coralServiceProvider = provider;
    }

    public static ServiceClientModule_ProvideHomeFeedServiceClientFactory create(ServiceClientModule serviceClientModule, Provider<CoralService> provider) {
        return new ServiceClientModule_ProvideHomeFeedServiceClientFactory(serviceClientModule, provider);
    }

    public static HomeFeedServiceClient provideInstance(ServiceClientModule serviceClientModule, Provider<CoralService> provider) {
        return proxyProvideHomeFeedServiceClient(serviceClientModule, provider.mo10268get());
    }

    public static HomeFeedServiceClient proxyProvideHomeFeedServiceClient(ServiceClientModule serviceClientModule, CoralService coralService) {
        return (HomeFeedServiceClient) Preconditions.checkNotNull(serviceClientModule.provideHomeFeedServiceClient(coralService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HomeFeedServiceClient mo10268get() {
        return provideInstance(this.module, this.coralServiceProvider);
    }
}
