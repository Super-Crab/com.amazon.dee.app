package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.dee.app.metrics.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvidePersonIdProviderFactory implements Factory<PersonIdProvider> {
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final IdentityModule module;

    public IdentityModule_ProvidePersonIdProviderFactory(IdentityModule identityModule, Provider<IdentityService> provider, Provider<MetricsService> provider2) {
        this.module = identityModule;
        this.identityServiceProvider = provider;
        this.metricsServiceProvider = provider2;
    }

    public static IdentityModule_ProvidePersonIdProviderFactory create(IdentityModule identityModule, Provider<IdentityService> provider, Provider<MetricsService> provider2) {
        return new IdentityModule_ProvidePersonIdProviderFactory(identityModule, provider, provider2);
    }

    public static PersonIdProvider provideInstance(IdentityModule identityModule, Provider<IdentityService> provider, Provider<MetricsService> provider2) {
        return proxyProvidePersonIdProvider(identityModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static PersonIdProvider proxyProvidePersonIdProvider(IdentityModule identityModule, IdentityService identityService, MetricsService metricsService) {
        return (PersonIdProvider) Preconditions.checkNotNull(identityModule.providePersonIdProvider(identityService, metricsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersonIdProvider mo10268get() {
        return provideInstance(this.module, this.identityServiceProvider, this.metricsServiceProvider);
    }
}
