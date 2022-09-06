package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class FitnessSessionRepositoryImpl_Factory implements Factory<FitnessSessionRepositoryImpl> {
    private final Provider<CacheProvider> cacheProvider;
    private final Provider<ILog> logProvider;

    public FitnessSessionRepositoryImpl_Factory(Provider<CacheProvider> provider, Provider<ILog> provider2) {
        this.cacheProvider = provider;
        this.logProvider = provider2;
    }

    public static FitnessSessionRepositoryImpl_Factory create(Provider<CacheProvider> provider, Provider<ILog> provider2) {
        return new FitnessSessionRepositoryImpl_Factory(provider, provider2);
    }

    public static FitnessSessionRepositoryImpl newFitnessSessionRepositoryImpl(CacheProvider cacheProvider, ILog iLog) {
        return new FitnessSessionRepositoryImpl(cacheProvider, iLog);
    }

    public static FitnessSessionRepositoryImpl provideInstance(Provider<CacheProvider> provider, Provider<ILog> provider2) {
        return new FitnessSessionRepositoryImpl(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessSessionRepositoryImpl mo10268get() {
        return provideInstance(this.cacheProvider, this.logProvider);
    }
}
