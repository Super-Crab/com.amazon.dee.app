package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.protocols.network.NetworkService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideNetworkServiceFactory implements Factory<NetworkService> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideNetworkServiceFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideNetworkServiceFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideNetworkServiceFactory(applicationModule);
    }

    public static NetworkService provideInstance(ApplicationModule applicationModule) {
        return proxyProvideNetworkService(applicationModule);
    }

    public static NetworkService proxyProvideNetworkService(ApplicationModule applicationModule) {
        return (NetworkService) Preconditions.checkNotNull(applicationModule.provideNetworkService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NetworkService mo10268get() {
        return provideInstance(this.module);
    }
}
