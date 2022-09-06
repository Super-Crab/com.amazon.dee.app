package com.amazon.alexa.voice.handsfree.dependencies;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class AhfModule_ProvideFeatureServiceV2Factory implements Factory<FeatureServiceV2> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final AhfModule module;

    public AhfModule_ProvideFeatureServiceV2Factory(AhfModule ahfModule, Provider<ComponentRegistry> provider) {
        this.module = ahfModule;
        this.componentRegistryProvider = provider;
    }

    public static AhfModule_ProvideFeatureServiceV2Factory create(AhfModule ahfModule, Provider<ComponentRegistry> provider) {
        return new AhfModule_ProvideFeatureServiceV2Factory(ahfModule, provider);
    }

    public static FeatureServiceV2 provideInstance(AhfModule ahfModule, Provider<ComponentRegistry> provider) {
        return proxyProvideFeatureServiceV2(ahfModule, provider.mo10268get());
    }

    public static FeatureServiceV2 proxyProvideFeatureServiceV2(AhfModule ahfModule, ComponentRegistry componentRegistry) {
        return (FeatureServiceV2) Preconditions.checkNotNull(ahfModule.provideFeatureServiceV2(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceV2 mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
