package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.protocols.network.NetworkService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideNetworkServiceFactory implements Factory<NetworkService> {
    private final Provider<Context> contextProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideNetworkServiceFactory(ServiceModule serviceModule, Provider<Context> provider) {
        this.module = serviceModule;
        this.contextProvider = provider;
    }

    public static ServiceModule_ProvideNetworkServiceFactory create(ServiceModule serviceModule, Provider<Context> provider) {
        return new ServiceModule_ProvideNetworkServiceFactory(serviceModule, provider);
    }

    public static NetworkService provideInstance(ServiceModule serviceModule, Provider<Context> provider) {
        return proxyProvideNetworkService(serviceModule, provider.mo10268get());
    }

    public static NetworkService proxyProvideNetworkService(ServiceModule serviceModule, Context context) {
        return (NetworkService) Preconditions.checkNotNull(serviceModule.provideNetworkService(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NetworkService mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
