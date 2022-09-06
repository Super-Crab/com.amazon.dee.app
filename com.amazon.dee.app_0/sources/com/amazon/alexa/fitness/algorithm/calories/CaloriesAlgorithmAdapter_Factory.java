package com.amazon.alexa.fitness.algorithm.calories;

import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes.dex */
public final class CaloriesAlgorithmAdapter_Factory implements Factory<CaloriesAlgorithmAdapter> {
    private final Provider<CaloriesAlgorithmV2> caloriesAlgorithmProvider;
    private final Provider<ILog> logProvider;

    public CaloriesAlgorithmAdapter_Factory(Provider<CaloriesAlgorithmV2> provider, Provider<ILog> provider2) {
        this.caloriesAlgorithmProvider = provider;
        this.logProvider = provider2;
    }

    public static CaloriesAlgorithmAdapter_Factory create(Provider<CaloriesAlgorithmV2> provider, Provider<ILog> provider2) {
        return new CaloriesAlgorithmAdapter_Factory(provider, provider2);
    }

    public static CaloriesAlgorithmAdapter newCaloriesAlgorithmAdapter(CaloriesAlgorithmV2 caloriesAlgorithmV2, ILog iLog) {
        return new CaloriesAlgorithmAdapter(caloriesAlgorithmV2, iLog);
    }

    public static CaloriesAlgorithmAdapter provideInstance(Provider<CaloriesAlgorithmV2> provider, Provider<ILog> provider2) {
        return new CaloriesAlgorithmAdapter(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CaloriesAlgorithmAdapter mo10268get() {
        return provideInstance(this.caloriesAlgorithmProvider, this.logProvider);
    }
}
