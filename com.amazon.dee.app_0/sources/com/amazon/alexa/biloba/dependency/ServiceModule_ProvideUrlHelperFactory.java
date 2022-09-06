package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.UrlHelper;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ServiceModule_ProvideUrlHelperFactory implements Factory<UrlHelper> {
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final ServiceModule module;

    public ServiceModule_ProvideUrlHelperFactory(ServiceModule serviceModule, Provider<EnvironmentService> provider, Provider<CareActorsStore> provider2, Provider<FeatureServiceV2> provider3) {
        this.module = serviceModule;
        this.environmentServiceProvider = provider;
        this.careActorsStoreProvider = provider2;
        this.featureServiceV2Provider = provider3;
    }

    public static ServiceModule_ProvideUrlHelperFactory create(ServiceModule serviceModule, Provider<EnvironmentService> provider, Provider<CareActorsStore> provider2, Provider<FeatureServiceV2> provider3) {
        return new ServiceModule_ProvideUrlHelperFactory(serviceModule, provider, provider2, provider3);
    }

    public static UrlHelper provideInstance(ServiceModule serviceModule, Provider<EnvironmentService> provider, Provider<CareActorsStore> provider2, Provider<FeatureServiceV2> provider3) {
        return proxyProvideUrlHelper(serviceModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static UrlHelper proxyProvideUrlHelper(ServiceModule serviceModule, Lazy<EnvironmentService> lazy, Lazy<CareActorsStore> lazy2, Lazy<FeatureServiceV2> lazy3) {
        return (UrlHelper) Preconditions.checkNotNull(serviceModule.provideUrlHelper(lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UrlHelper mo10268get() {
        return provideInstance(this.module, this.environmentServiceProvider, this.careActorsStoreProvider, this.featureServiceV2Provider);
    }
}
