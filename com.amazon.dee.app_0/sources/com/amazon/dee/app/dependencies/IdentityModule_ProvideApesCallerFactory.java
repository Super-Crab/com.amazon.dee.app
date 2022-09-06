package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.ApesCallerInterface;
import com.dee.app.http.CoralService;
import com.dee.app.metrics.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideApesCallerFactory implements Factory<ApesCallerInterface> {
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideApesCallerFactory(IdentityModule identityModule, Provider<CoralService> provider, Provider<MetricsService> provider2) {
        this.module = identityModule;
        this.coralServiceProvider = provider;
        this.metricsServiceProvider = provider2;
    }

    public static IdentityModule_ProvideApesCallerFactory create(IdentityModule identityModule, Provider<CoralService> provider, Provider<MetricsService> provider2) {
        return new IdentityModule_ProvideApesCallerFactory(identityModule, provider, provider2);
    }

    public static ApesCallerInterface provideInstance(IdentityModule identityModule, Provider<CoralService> provider, Provider<MetricsService> provider2) {
        return proxyProvideApesCaller(identityModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static ApesCallerInterface proxyProvideApesCaller(IdentityModule identityModule, CoralService coralService, MetricsService metricsService) {
        return (ApesCallerInterface) Preconditions.checkNotNull(identityModule.provideApesCaller(coralService, metricsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ApesCallerInterface mo10268get() {
        return provideInstance(this.module, this.coralServiceProvider, this.metricsServiceProvider);
    }
}
