package com.amazon.alexa.fitness.session;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.wellness.io.format.abs.FitnessDataParser;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class FitnessDataHandlerImpl_Factory implements Factory<FitnessDataHandlerImpl> {
    private final Provider<FitnessDataParser> fitnessDataParserProvider;
    private final Provider<ILog> logProvider;

    public FitnessDataHandlerImpl_Factory(Provider<FitnessDataParser> provider, Provider<ILog> provider2) {
        this.fitnessDataParserProvider = provider;
        this.logProvider = provider2;
    }

    public static FitnessDataHandlerImpl_Factory create(Provider<FitnessDataParser> provider, Provider<ILog> provider2) {
        return new FitnessDataHandlerImpl_Factory(provider, provider2);
    }

    public static FitnessDataHandlerImpl newFitnessDataHandlerImpl(FitnessDataParser fitnessDataParser, ILog iLog) {
        return new FitnessDataHandlerImpl(fitnessDataParser, iLog);
    }

    public static FitnessDataHandlerImpl provideInstance(Provider<FitnessDataParser> provider, Provider<ILog> provider2) {
        return new FitnessDataHandlerImpl(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessDataHandlerImpl mo10268get() {
        return provideInstance(this.fitnessDataParserProvider, this.logProvider);
    }
}
