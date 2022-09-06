package com.amazon.dee.app.dependencies;

import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import com.amazon.alexa.featureservice.implementation.AlexaMobileAndroidFeatureServiceImpl;
import com.amazon.alexa.featureservice.service.DefaultFeatureServiceV2;
import com.amazon.alexa.protocols.features.FeatureQuery;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvidePlatformFeatureServiceV2Factory implements Factory<PlatformFeatureServiceV2> {
    private final Provider<AlexaMobileAndroidFeatureServiceImpl> alexaMobileAndroidFeatureServiceProvider;
    private final Provider<DefaultFeatureServiceV2> defaultFeatureServiceV2Provider;
    private final Provider<FeatureQuery> featureQueryProvider;
    private final ServiceModule module;

    public ServiceModule_ProvidePlatformFeatureServiceV2Factory(ServiceModule serviceModule, Provider<FeatureQuery> provider, Provider<AlexaMobileAndroidFeatureServiceImpl> provider2, Provider<DefaultFeatureServiceV2> provider3) {
        this.module = serviceModule;
        this.featureQueryProvider = provider;
        this.alexaMobileAndroidFeatureServiceProvider = provider2;
        this.defaultFeatureServiceV2Provider = provider3;
    }

    public static ServiceModule_ProvidePlatformFeatureServiceV2Factory create(ServiceModule serviceModule, Provider<FeatureQuery> provider, Provider<AlexaMobileAndroidFeatureServiceImpl> provider2, Provider<DefaultFeatureServiceV2> provider3) {
        return new ServiceModule_ProvidePlatformFeatureServiceV2Factory(serviceModule, provider, provider2, provider3);
    }

    public static PlatformFeatureServiceV2 provideInstance(ServiceModule serviceModule, Provider<FeatureQuery> provider, Provider<AlexaMobileAndroidFeatureServiceImpl> provider2, Provider<DefaultFeatureServiceV2> provider3) {
        return proxyProvidePlatformFeatureServiceV2(serviceModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), provider3.mo10268get());
    }

    public static PlatformFeatureServiceV2 proxyProvidePlatformFeatureServiceV2(ServiceModule serviceModule, Lazy<FeatureQuery> lazy, Lazy<AlexaMobileAndroidFeatureServiceImpl> lazy2, DefaultFeatureServiceV2 defaultFeatureServiceV2) {
        return (PlatformFeatureServiceV2) Preconditions.checkNotNull(serviceModule.providePlatformFeatureServiceV2(lazy, lazy2, defaultFeatureServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PlatformFeatureServiceV2 mo10268get() {
        return provideInstance(this.module, this.featureQueryProvider, this.alexaMobileAndroidFeatureServiceProvider, this.defaultFeatureServiceV2Provider);
    }
}
