package com.amazon.alexa.fitness.context;

import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class AlexaFitnessContextsProvider_Factory implements Factory<AlexaFitnessContextsProvider> {
    private final Provider<FitnessSessionOrchestrator> fitnessSessionOrchestratorProvider;
    private final Provider<ILog> logProvider;

    public AlexaFitnessContextsProvider_Factory(Provider<FitnessSessionOrchestrator> provider, Provider<ILog> provider2) {
        this.fitnessSessionOrchestratorProvider = provider;
        this.logProvider = provider2;
    }

    public static AlexaFitnessContextsProvider_Factory create(Provider<FitnessSessionOrchestrator> provider, Provider<ILog> provider2) {
        return new AlexaFitnessContextsProvider_Factory(provider, provider2);
    }

    public static AlexaFitnessContextsProvider newAlexaFitnessContextsProvider(FitnessSessionOrchestrator fitnessSessionOrchestrator, ILog iLog) {
        return new AlexaFitnessContextsProvider(fitnessSessionOrchestrator, iLog);
    }

    public static AlexaFitnessContextsProvider provideInstance(Provider<FitnessSessionOrchestrator> provider, Provider<ILog> provider2) {
        return new AlexaFitnessContextsProvider(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaFitnessContextsProvider mo10268get() {
        return provideInstance(this.fitnessSessionOrchestratorProvider, this.logProvider);
    }
}
