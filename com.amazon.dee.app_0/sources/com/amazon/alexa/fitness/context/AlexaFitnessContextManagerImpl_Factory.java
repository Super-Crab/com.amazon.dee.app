package com.amazon.alexa.fitness.context;

import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection;
import com.amazon.alexa.fitness.session.FitnessSessionStateService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class AlexaFitnessContextManagerImpl_Factory implements Factory<AlexaFitnessContextManagerImpl> {
    private final Provider<AlexaContextsProvider> alexaContextsProvider;
    private final Provider<FitnessSessionStateService> fitnessSessionStateServiceProvider;
    private final Provider<InstrumentedAlexaServicesConnection> instrumentedAlexaServicesConnectionProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;

    public AlexaFitnessContextManagerImpl_Factory(Provider<InstrumentedAlexaServicesConnection> provider, Provider<AlexaContextsProvider> provider2, Provider<FitnessSessionStateService> provider3, Provider<ILog> provider4, Provider<MetricEventFactory> provider5) {
        this.instrumentedAlexaServicesConnectionProvider = provider;
        this.alexaContextsProvider = provider2;
        this.fitnessSessionStateServiceProvider = provider3;
        this.logProvider = provider4;
        this.metricEventFactoryProvider = provider5;
    }

    public static AlexaFitnessContextManagerImpl_Factory create(Provider<InstrumentedAlexaServicesConnection> provider, Provider<AlexaContextsProvider> provider2, Provider<FitnessSessionStateService> provider3, Provider<ILog> provider4, Provider<MetricEventFactory> provider5) {
        return new AlexaFitnessContextManagerImpl_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static AlexaFitnessContextManagerImpl newAlexaFitnessContextManagerImpl(InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection, AlexaContextsProvider alexaContextsProvider, FitnessSessionStateService fitnessSessionStateService, ILog iLog, MetricEventFactory metricEventFactory) {
        return new AlexaFitnessContextManagerImpl(instrumentedAlexaServicesConnection, alexaContextsProvider, fitnessSessionStateService, iLog, metricEventFactory);
    }

    public static AlexaFitnessContextManagerImpl provideInstance(Provider<InstrumentedAlexaServicesConnection> provider, Provider<AlexaContextsProvider> provider2, Provider<FitnessSessionStateService> provider3, Provider<ILog> provider4, Provider<MetricEventFactory> provider5) {
        return new AlexaFitnessContextManagerImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaFitnessContextManagerImpl mo10268get() {
        return provideInstance(this.instrumentedAlexaServicesConnectionProvider, this.alexaContextsProvider, this.fitnessSessionStateServiceProvider, this.logProvider, this.metricEventFactoryProvider);
    }
}
