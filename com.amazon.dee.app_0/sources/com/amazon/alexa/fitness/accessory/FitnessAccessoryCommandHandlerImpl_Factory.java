package com.amazon.alexa.fitness.accessory;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.repository.FitnessSessionRepository;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class FitnessAccessoryCommandHandlerImpl_Factory implements Factory<FitnessAccessoryCommandHandlerImpl> {
    private final Provider<AfxMessageProcessor> afxMessageProcessorProvider;
    private final Provider<FitnessSessionRepository> fitnessSessionRepositoryProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;

    public FitnessAccessoryCommandHandlerImpl_Factory(Provider<FitnessSessionRepository> provider, Provider<MetricEventRecorder> provider2, Provider<MetricEventFactory> provider3, Provider<AfxMessageProcessor> provider4, Provider<ILog> provider5) {
        this.fitnessSessionRepositoryProvider = provider;
        this.metricEventRecorderProvider = provider2;
        this.metricEventFactoryProvider = provider3;
        this.afxMessageProcessorProvider = provider4;
        this.logProvider = provider5;
    }

    public static FitnessAccessoryCommandHandlerImpl_Factory create(Provider<FitnessSessionRepository> provider, Provider<MetricEventRecorder> provider2, Provider<MetricEventFactory> provider3, Provider<AfxMessageProcessor> provider4, Provider<ILog> provider5) {
        return new FitnessAccessoryCommandHandlerImpl_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static FitnessAccessoryCommandHandlerImpl newFitnessAccessoryCommandHandlerImpl(FitnessSessionRepository fitnessSessionRepository, MetricEventRecorder metricEventRecorder, MetricEventFactory metricEventFactory, AfxMessageProcessor afxMessageProcessor, ILog iLog) {
        return new FitnessAccessoryCommandHandlerImpl(fitnessSessionRepository, metricEventRecorder, metricEventFactory, afxMessageProcessor, iLog);
    }

    public static FitnessAccessoryCommandHandlerImpl provideInstance(Provider<FitnessSessionRepository> provider, Provider<MetricEventRecorder> provider2, Provider<MetricEventFactory> provider3, Provider<AfxMessageProcessor> provider4, Provider<ILog> provider5) {
        return new FitnessAccessoryCommandHandlerImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessAccessoryCommandHandlerImpl mo10268get() {
        return provideInstance(this.fitnessSessionRepositoryProvider, this.metricEventRecorderProvider, this.metricEventFactoryProvider, this.afxMessageProcessorProvider, this.logProvider);
    }
}
