package com.amazon.dee.app.dependencies;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideFeatureServiceV2Factory implements Factory<FeatureServiceV2> {
    private final ServiceModule module;
    private final Provider<PlatformFeatureServiceV2> platformFeatureServiceV2Provider;

    public ServiceModule_ProvideFeatureServiceV2Factory(ServiceModule serviceModule, Provider<PlatformFeatureServiceV2> provider) {
        this.module = serviceModule;
        this.platformFeatureServiceV2Provider = provider;
    }

    public static ServiceModule_ProvideFeatureServiceV2Factory create(ServiceModule serviceModule, Provider<PlatformFeatureServiceV2> provider) {
        return new ServiceModule_ProvideFeatureServiceV2Factory(serviceModule, provider);
    }

    public static FeatureServiceV2 provideInstance(ServiceModule serviceModule, Provider<PlatformFeatureServiceV2> provider) {
        return proxyProvideFeatureServiceV2(serviceModule, provider.mo10268get());
    }

    public static FeatureServiceV2 proxyProvideFeatureServiceV2(ServiceModule serviceModule, PlatformFeatureServiceV2 platformFeatureServiceV2) {
        return (FeatureServiceV2) Preconditions.checkNotNull(serviceModule.provideFeatureServiceV2(platformFeatureServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceV2 mo10268get() {
        return provideInstance(this.module, this.platformFeatureServiceV2Provider);
    }
}
