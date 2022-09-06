package com.amazon.alexa.fitness.message;

import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class FitnessHomeCardPublisher_Factory implements Factory<FitnessHomeCardPublisher> {
    private final Provider<ILog> logProvider;

    public FitnessHomeCardPublisher_Factory(Provider<ILog> provider) {
        this.logProvider = provider;
    }

    public static FitnessHomeCardPublisher_Factory create(Provider<ILog> provider) {
        return new FitnessHomeCardPublisher_Factory(provider);
    }

    public static FitnessHomeCardPublisher newFitnessHomeCardPublisher(ILog iLog) {
        return new FitnessHomeCardPublisher(iLog);
    }

    public static FitnessHomeCardPublisher provideInstance(Provider<ILog> provider) {
        return new FitnessHomeCardPublisher(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessHomeCardPublisher mo10268get() {
        return provideInstance(this.logProvider);
    }
}
