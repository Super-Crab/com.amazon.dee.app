package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.protocols.network.NetworkService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class NetworkServiceModule_ProvideNetworkServiceFactory implements Factory<NetworkService> {
    private static final NetworkServiceModule_ProvideNetworkServiceFactory INSTANCE = new NetworkServiceModule_ProvideNetworkServiceFactory();

    public static NetworkServiceModule_ProvideNetworkServiceFactory create() {
        return INSTANCE;
    }

    public static NetworkService provideInstance() {
        return proxyProvideNetworkService();
    }

    public static NetworkService proxyProvideNetworkService() {
        return (NetworkService) Preconditions.checkNotNull(NetworkServiceModule.provideNetworkService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NetworkService mo10268get() {
        return provideInstance();
    }
}
