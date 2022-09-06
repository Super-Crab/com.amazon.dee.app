package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.configuration.FeatureServiceConfiguration;
import com.amazon.alexa.featureservice.sessionManagement.SessionManager;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class ImplementationModule_ProvidesSessionManagerFactory implements Factory<SessionManager> {
    private final Provider<EventBus> eventBusLazyProvider;
    private final Provider<FeatureServiceConfiguration> featureServiceConfigurationProvider;
    private final ImplementationModule module;

    public ImplementationModule_ProvidesSessionManagerFactory(ImplementationModule implementationModule, Provider<EventBus> provider, Provider<FeatureServiceConfiguration> provider2) {
        this.module = implementationModule;
        this.eventBusLazyProvider = provider;
        this.featureServiceConfigurationProvider = provider2;
    }

    public static ImplementationModule_ProvidesSessionManagerFactory create(ImplementationModule implementationModule, Provider<EventBus> provider, Provider<FeatureServiceConfiguration> provider2) {
        return new ImplementationModule_ProvidesSessionManagerFactory(implementationModule, provider, provider2);
    }

    public static SessionManager provideInstance(ImplementationModule implementationModule, Provider<EventBus> provider, Provider<FeatureServiceConfiguration> provider2) {
        return proxyProvidesSessionManager(implementationModule, DoubleCheck.lazy(provider), provider2.mo10268get());
    }

    public static SessionManager proxyProvidesSessionManager(ImplementationModule implementationModule, Lazy<EventBus> lazy, FeatureServiceConfiguration featureServiceConfiguration) {
        return (SessionManager) Preconditions.checkNotNull(implementationModule.providesSessionManager(lazy, featureServiceConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SessionManager mo10268get() {
        return provideInstance(this.module, this.eventBusLazyProvider, this.featureServiceConfigurationProvider);
    }
}
