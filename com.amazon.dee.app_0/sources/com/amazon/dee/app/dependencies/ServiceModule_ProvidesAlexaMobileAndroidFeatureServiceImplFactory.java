package com.amazon.dee.app.dependencies;

import com.amazon.alexa.featureservice.dependencies.DaggerInitializer;
import com.amazon.alexa.featureservice.implementation.AlexaMobileAndroidFeatureServiceImpl;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvidesAlexaMobileAndroidFeatureServiceImplFactory implements Factory<AlexaMobileAndroidFeatureServiceImpl> {
    private final Provider<DaggerInitializer> daggerInitializerProvider;
    private final ServiceModule module;

    public ServiceModule_ProvidesAlexaMobileAndroidFeatureServiceImplFactory(ServiceModule serviceModule, Provider<DaggerInitializer> provider) {
        this.module = serviceModule;
        this.daggerInitializerProvider = provider;
    }

    public static ServiceModule_ProvidesAlexaMobileAndroidFeatureServiceImplFactory create(ServiceModule serviceModule, Provider<DaggerInitializer> provider) {
        return new ServiceModule_ProvidesAlexaMobileAndroidFeatureServiceImplFactory(serviceModule, provider);
    }

    public static AlexaMobileAndroidFeatureServiceImpl provideInstance(ServiceModule serviceModule, Provider<DaggerInitializer> provider) {
        return proxyProvidesAlexaMobileAndroidFeatureServiceImpl(serviceModule, provider.mo10268get());
    }

    public static AlexaMobileAndroidFeatureServiceImpl proxyProvidesAlexaMobileAndroidFeatureServiceImpl(ServiceModule serviceModule, DaggerInitializer daggerInitializer) {
        return (AlexaMobileAndroidFeatureServiceImpl) Preconditions.checkNotNull(serviceModule.providesAlexaMobileAndroidFeatureServiceImpl(daggerInitializer), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaMobileAndroidFeatureServiceImpl mo10268get() {
        return provideInstance(this.module, this.daggerInitializerProvider);
    }
}
