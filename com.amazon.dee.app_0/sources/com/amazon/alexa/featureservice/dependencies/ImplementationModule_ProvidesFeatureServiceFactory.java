package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class ImplementationModule_ProvidesFeatureServiceFactory implements Factory<FeatureServiceV2> {
    private final ImplementationModule module;
    private final Provider<PlatformFeatureServiceV2> platformFeatureServiceProvider;

    public ImplementationModule_ProvidesFeatureServiceFactory(ImplementationModule implementationModule, Provider<PlatformFeatureServiceV2> provider) {
        this.module = implementationModule;
        this.platformFeatureServiceProvider = provider;
    }

    public static ImplementationModule_ProvidesFeatureServiceFactory create(ImplementationModule implementationModule, Provider<PlatformFeatureServiceV2> provider) {
        return new ImplementationModule_ProvidesFeatureServiceFactory(implementationModule, provider);
    }

    public static FeatureServiceV2 provideInstance(ImplementationModule implementationModule, Provider<PlatformFeatureServiceV2> provider) {
        return proxyProvidesFeatureService(implementationModule, provider.mo10268get());
    }

    public static FeatureServiceV2 proxyProvidesFeatureService(ImplementationModule implementationModule, PlatformFeatureServiceV2 platformFeatureServiceV2) {
        return (FeatureServiceV2) Preconditions.checkNotNull(implementationModule.providesFeatureService(platformFeatureServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceV2 mo10268get() {
        return provideInstance(this.module, this.platformFeatureServiceProvider);
    }
}
