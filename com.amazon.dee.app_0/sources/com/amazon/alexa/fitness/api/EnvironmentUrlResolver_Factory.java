package com.amazon.alexa.fitness.api;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class EnvironmentUrlResolver_Factory implements Factory<EnvironmentUrlResolver> {
    private final Provider<EnvironmentService> environmentServiceProvider;

    public EnvironmentUrlResolver_Factory(Provider<EnvironmentService> provider) {
        this.environmentServiceProvider = provider;
    }

    public static EnvironmentUrlResolver_Factory create(Provider<EnvironmentService> provider) {
        return new EnvironmentUrlResolver_Factory(provider);
    }

    public static EnvironmentUrlResolver newEnvironmentUrlResolver(EnvironmentService environmentService) {
        return new EnvironmentUrlResolver(environmentService);
    }

    public static EnvironmentUrlResolver provideInstance(Provider<EnvironmentService> provider) {
        return new EnvironmentUrlResolver(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnvironmentUrlResolver mo10268get() {
        return provideInstance(this.environmentServiceProvider);
    }
}
