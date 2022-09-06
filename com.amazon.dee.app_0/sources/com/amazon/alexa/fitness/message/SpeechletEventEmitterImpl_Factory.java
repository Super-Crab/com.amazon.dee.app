package com.amazon.alexa.fitness.message;

import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.configuration.SpeechletEventEmitterConfigurationProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection;
import com.amazon.alexa.fitness.session.FitnessSessionStateService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class SpeechletEventEmitterImpl_Factory implements Factory<SpeechletEventEmitterImpl> {
    private final Provider<SpeechletEventEmitterConfigurationProvider> configurationProvider;
    private final Provider<FitnessSessionOrchestrator> fitnessSessionOrchestratorProvider;
    private final Provider<FitnessSessionStateService> fitnessSessionStateServiceProvider;
    private final Provider<InstrumentedAlexaServicesConnection> instrumentedAlexaServicesConnectionProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;

    public SpeechletEventEmitterImpl_Factory(Provider<SpeechletEventEmitterConfigurationProvider> provider, Provider<InstrumentedAlexaServicesConnection> provider2, Provider<FitnessSessionOrchestrator> provider3, Provider<FitnessSessionStateService> provider4, Provider<MetricEventFactory> provider5, Provider<MetricEventRecorder> provider6, Provider<ILog> provider7) {
        this.configurationProvider = provider;
        this.instrumentedAlexaServicesConnectionProvider = provider2;
        this.fitnessSessionOrchestratorProvider = provider3;
        this.fitnessSessionStateServiceProvider = provider4;
        this.metricEventFactoryProvider = provider5;
        this.metricEventRecorderProvider = provider6;
        this.logProvider = provider7;
    }

    public static SpeechletEventEmitterImpl_Factory create(Provider<SpeechletEventEmitterConfigurationProvider> provider, Provider<InstrumentedAlexaServicesConnection> provider2, Provider<FitnessSessionOrchestrator> provider3, Provider<FitnessSessionStateService> provider4, Provider<MetricEventFactory> provider5, Provider<MetricEventRecorder> provider6, Provider<ILog> provider7) {
        return new SpeechletEventEmitterImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static SpeechletEventEmitterImpl newSpeechletEventEmitterImpl(SpeechletEventEmitterConfigurationProvider speechletEventEmitterConfigurationProvider, InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection, FitnessSessionOrchestrator fitnessSessionOrchestrator, FitnessSessionStateService fitnessSessionStateService, MetricEventFactory metricEventFactory, MetricEventRecorder metricEventRecorder, ILog iLog) {
        return new SpeechletEventEmitterImpl(speechletEventEmitterConfigurationProvider, instrumentedAlexaServicesConnection, fitnessSessionOrchestrator, fitnessSessionStateService, metricEventFactory, metricEventRecorder, iLog);
    }

    public static SpeechletEventEmitterImpl provideInstance(Provider<SpeechletEventEmitterConfigurationProvider> provider, Provider<InstrumentedAlexaServicesConnection> provider2, Provider<FitnessSessionOrchestrator> provider3, Provider<FitnessSessionStateService> provider4, Provider<MetricEventFactory> provider5, Provider<MetricEventRecorder> provider6, Provider<ILog> provider7) {
        return new SpeechletEventEmitterImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SpeechletEventEmitterImpl mo10268get() {
        return provideInstance(this.configurationProvider, this.instrumentedAlexaServicesConnectionProvider, this.fitnessSessionOrchestratorProvider, this.fitnessSessionStateServiceProvider, this.metricEventFactoryProvider, this.metricEventRecorderProvider, this.logProvider);
    }
}
