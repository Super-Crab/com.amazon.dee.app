package com.amazon.dee.app.dependencies;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.dee.app.http.UrlResolver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideUrlResolverFactory implements Factory<UrlResolver> {
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideUrlResolverFactory(ServiceModule serviceModule, Provider<EnvironmentService> provider) {
        this.module = serviceModule;
        this.environmentServiceProvider = provider;
    }

    public static ServiceModule_ProvideUrlResolverFactory create(ServiceModule serviceModule, Provider<EnvironmentService> provider) {
        return new ServiceModule_ProvideUrlResolverFactory(serviceModule, provider);
    }

    public static UrlResolver provideInstance(ServiceModule serviceModule, Provider<EnvironmentService> provider) {
        return proxyProvideUrlResolver(serviceModule, provider.mo10268get());
    }

    public static UrlResolver proxyProvideUrlResolver(ServiceModule serviceModule, EnvironmentService environmentService) {
        return (UrlResolver) Preconditions.checkNotNull(serviceModule.provideUrlResolver(environmentService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UrlResolver mo10268get() {
        return provideInstance(this.module, this.environmentServiceProvider);
    }
}
