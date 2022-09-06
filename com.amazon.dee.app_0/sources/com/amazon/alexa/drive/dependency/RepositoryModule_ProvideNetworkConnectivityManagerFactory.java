package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideNetworkConnectivityManagerFactory implements Factory<NetworkConnectivityManager> {
    private final RepositoryModule module;

    public RepositoryModule_ProvideNetworkConnectivityManagerFactory(RepositoryModule repositoryModule) {
        this.module = repositoryModule;
    }

    public static RepositoryModule_ProvideNetworkConnectivityManagerFactory create(RepositoryModule repositoryModule) {
        return new RepositoryModule_ProvideNetworkConnectivityManagerFactory(repositoryModule);
    }

    public static NetworkConnectivityManager provideInstance(RepositoryModule repositoryModule) {
        return proxyProvideNetworkConnectivityManager(repositoryModule);
    }

    public static NetworkConnectivityManager proxyProvideNetworkConnectivityManager(RepositoryModule repositoryModule) {
        return (NetworkConnectivityManager) Preconditions.checkNotNull(repositoryModule.provideNetworkConnectivityManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NetworkConnectivityManager mo10268get() {
        return provideInstance(this.module);
    }
}
