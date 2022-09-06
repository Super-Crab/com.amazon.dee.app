package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.configuration.RecoveryConfigurationProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.repository.RecoveryRepository;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class SessionRecoveryManagerImpl_Factory implements Factory<SessionRecoveryManagerImpl> {
    private final Provider<RecoveryConfigurationProvider> configurationProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;
    private final Provider<RecoveryRepository> recoveryRepositoryProvider;
    private final Provider<SampleStore> sampleStoreProvider;

    public SessionRecoveryManagerImpl_Factory(Provider<RecoveryRepository> provider, Provider<RecoveryConfigurationProvider> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<SampleStore> provider5, Provider<ILog> provider6) {
        this.recoveryRepositoryProvider = provider;
        this.configurationProvider = provider2;
        this.metricEventFactoryProvider = provider3;
        this.metricEventRecorderProvider = provider4;
        this.sampleStoreProvider = provider5;
        this.logProvider = provider6;
    }

    public static SessionRecoveryManagerImpl_Factory create(Provider<RecoveryRepository> provider, Provider<RecoveryConfigurationProvider> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<SampleStore> provider5, Provider<ILog> provider6) {
        return new SessionRecoveryManagerImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static SessionRecoveryManagerImpl newSessionRecoveryManagerImpl(RecoveryRepository recoveryRepository, RecoveryConfigurationProvider recoveryConfigurationProvider, MetricEventFactory metricEventFactory, MetricEventRecorder metricEventRecorder, SampleStore sampleStore, ILog iLog) {
        return new SessionRecoveryManagerImpl(recoveryRepository, recoveryConfigurationProvider, metricEventFactory, metricEventRecorder, sampleStore, iLog);
    }

    public static SessionRecoveryManagerImpl provideInstance(Provider<RecoveryRepository> provider, Provider<RecoveryConfigurationProvider> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<SampleStore> provider5, Provider<ILog> provider6) {
        return new SessionRecoveryManagerImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SessionRecoveryManagerImpl mo10268get() {
        return provideInstance(this.recoveryRepositoryProvider, this.configurationProvider, this.metricEventFactoryProvider, this.metricEventRecorderProvider, this.sampleStoreProvider, this.logProvider);
    }
}
