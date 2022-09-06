package com.amazon.alexa.fitness.context;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class SessionSummaryProviderImpl_Factory implements Factory<SessionSummaryProviderImpl> {
    private final Provider<ILog> logProvider;
    private final Provider<SampleStore> sampleStoreProvider;

    public SessionSummaryProviderImpl_Factory(Provider<SampleStore> provider, Provider<ILog> provider2) {
        this.sampleStoreProvider = provider;
        this.logProvider = provider2;
    }

    public static SessionSummaryProviderImpl_Factory create(Provider<SampleStore> provider, Provider<ILog> provider2) {
        return new SessionSummaryProviderImpl_Factory(provider, provider2);
    }

    public static SessionSummaryProviderImpl newSessionSummaryProviderImpl(SampleStore sampleStore, ILog iLog) {
        return new SessionSummaryProviderImpl(sampleStore, iLog);
    }

    public static SessionSummaryProviderImpl provideInstance(Provider<SampleStore> provider, Provider<ILog> provider2) {
        return new SessionSummaryProviderImpl(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SessionSummaryProviderImpl mo10268get() {
        return provideInstance(this.sampleStoreProvider, this.logProvider);
    }
}
