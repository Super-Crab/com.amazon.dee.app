package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class SampleRepositoryImpl_Factory implements Factory<SampleRepositoryImpl> {
    private final Provider<CacheProvider> cacheProvider;
    private final Provider<ILog> logProvider;

    public SampleRepositoryImpl_Factory(Provider<CacheProvider> provider, Provider<ILog> provider2) {
        this.cacheProvider = provider;
        this.logProvider = provider2;
    }

    public static SampleRepositoryImpl_Factory create(Provider<CacheProvider> provider, Provider<ILog> provider2) {
        return new SampleRepositoryImpl_Factory(provider, provider2);
    }

    public static SampleRepositoryImpl newSampleRepositoryImpl(CacheProvider cacheProvider, ILog iLog) {
        return new SampleRepositoryImpl(cacheProvider, iLog);
    }

    public static SampleRepositoryImpl provideInstance(Provider<CacheProvider> provider, Provider<ILog> provider2) {
        return new SampleRepositoryImpl(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SampleRepositoryImpl mo10268get() {
        return provideInstance(this.cacheProvider, this.logProvider);
    }
}
