package com.amazon.alexa.fitness.algorithm.calories;

import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes.dex */
public final class AutoMETCaloriesAlgorithmImpl_Factory implements Factory<AutoMETCaloriesAlgorithmImpl> {
    private final Provider<ILog> loggerProvider;

    public AutoMETCaloriesAlgorithmImpl_Factory(Provider<ILog> provider) {
        this.loggerProvider = provider;
    }

    public static AutoMETCaloriesAlgorithmImpl_Factory create(Provider<ILog> provider) {
        return new AutoMETCaloriesAlgorithmImpl_Factory(provider);
    }

    public static AutoMETCaloriesAlgorithmImpl newAutoMETCaloriesAlgorithmImpl(ILog iLog) {
        return new AutoMETCaloriesAlgorithmImpl(iLog);
    }

    public static AutoMETCaloriesAlgorithmImpl provideInstance(Provider<ILog> provider) {
        return new AutoMETCaloriesAlgorithmImpl(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutoMETCaloriesAlgorithmImpl mo10268get() {
        return provideInstance(this.loggerProvider);
    }
}
