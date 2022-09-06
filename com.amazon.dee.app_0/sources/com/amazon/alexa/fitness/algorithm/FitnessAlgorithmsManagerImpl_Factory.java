package com.amazon.alexa.fitness.algorithm;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.SessionRecoveryManager;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class FitnessAlgorithmsManagerImpl_Factory implements Factory<FitnessAlgorithmsManagerImpl> {
    private final Provider<ILog> logProvider;
    private final Provider<SessionRecoveryManager> recoveryManagerProvider;
    private final Provider<SampleStore> sampleStoreProvider;

    public FitnessAlgorithmsManagerImpl_Factory(Provider<SessionRecoveryManager> provider, Provider<SampleStore> provider2, Provider<ILog> provider3) {
        this.recoveryManagerProvider = provider;
        this.sampleStoreProvider = provider2;
        this.logProvider = provider3;
    }

    public static FitnessAlgorithmsManagerImpl_Factory create(Provider<SessionRecoveryManager> provider, Provider<SampleStore> provider2, Provider<ILog> provider3) {
        return new FitnessAlgorithmsManagerImpl_Factory(provider, provider2, provider3);
    }

    public static FitnessAlgorithmsManagerImpl newFitnessAlgorithmsManagerImpl(SessionRecoveryManager sessionRecoveryManager, SampleStore sampleStore, ILog iLog) {
        return new FitnessAlgorithmsManagerImpl(sessionRecoveryManager, sampleStore, iLog);
    }

    public static FitnessAlgorithmsManagerImpl provideInstance(Provider<SessionRecoveryManager> provider, Provider<SampleStore> provider2, Provider<ILog> provider3) {
        return new FitnessAlgorithmsManagerImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessAlgorithmsManagerImpl mo10268get() {
        return provideInstance(this.recoveryManagerProvider, this.sampleStoreProvider, this.logProvider);
    }
}
