package com.amazon.dee.app.dependencies;

import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import com.amazon.alexa.featureservice.dependencies.DaggerInitializer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvidesFsv2TestConfigurationFactory implements Factory<FeatureServiceConfiguration> {
    private final Provider<DaggerInitializer> daggerInitializerProvider;
    private final ServiceModule module;

    public ServiceModule_ProvidesFsv2TestConfigurationFactory(ServiceModule serviceModule, Provider<DaggerInitializer> provider) {
        this.module = serviceModule;
        this.daggerInitializerProvider = provider;
    }

    public static ServiceModule_ProvidesFsv2TestConfigurationFactory create(ServiceModule serviceModule, Provider<DaggerInitializer> provider) {
        return new ServiceModule_ProvidesFsv2TestConfigurationFactory(serviceModule, provider);
    }

    public static FeatureServiceConfiguration provideInstance(ServiceModule serviceModule, Provider<DaggerInitializer> provider) {
        return proxyProvidesFsv2TestConfiguration(serviceModule, provider.mo10268get());
    }

    public static FeatureServiceConfiguration proxyProvidesFsv2TestConfiguration(ServiceModule serviceModule, DaggerInitializer daggerInitializer) {
        return (FeatureServiceConfiguration) Preconditions.checkNotNull(serviceModule.providesFsv2TestConfiguration(daggerInitializer), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceConfiguration mo10268get() {
        return provideInstance(this.module, this.daggerInitializerProvider);
    }
}
