package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class ImplementationModule_ProvidesTestConfigurationFactory implements Factory<FeatureServiceConfiguration> {
    private final ImplementationModule module;

    public ImplementationModule_ProvidesTestConfigurationFactory(ImplementationModule implementationModule) {
        this.module = implementationModule;
    }

    public static ImplementationModule_ProvidesTestConfigurationFactory create(ImplementationModule implementationModule) {
        return new ImplementationModule_ProvidesTestConfigurationFactory(implementationModule);
    }

    public static FeatureServiceConfiguration provideInstance(ImplementationModule implementationModule) {
        return proxyProvidesTestConfiguration(implementationModule);
    }

    public static FeatureServiceConfiguration proxyProvidesTestConfiguration(ImplementationModule implementationModule) {
        return (FeatureServiceConfiguration) Preconditions.checkNotNull(implementationModule.providesTestConfiguration(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceConfiguration mo10268get() {
        return provideInstance(this.module);
    }
}
