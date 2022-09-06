package com.amazon.alexa.fitness.algorithm;

import com.amazon.alexa.fitness.algorithms.StepToDistanceAlgorithm;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.logs.ILog;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class StepsToDistanceAlgorithmAdapter_Factory implements Factory<StepsToDistanceAlgorithmAdapter> {
    private final Provider<FeatureService> featureServiceProvider;
    private final Provider<ILog> logProvider;
    private final Provider<StepToDistanceAlgorithm> stepsToDistanceAlgorithmProvider;

    public StepsToDistanceAlgorithmAdapter_Factory(Provider<StepToDistanceAlgorithm> provider, Provider<FeatureService> provider2, Provider<ILog> provider3) {
        this.stepsToDistanceAlgorithmProvider = provider;
        this.featureServiceProvider = provider2;
        this.logProvider = provider3;
    }

    public static StepsToDistanceAlgorithmAdapter_Factory create(Provider<StepToDistanceAlgorithm> provider, Provider<FeatureService> provider2, Provider<ILog> provider3) {
        return new StepsToDistanceAlgorithmAdapter_Factory(provider, provider2, provider3);
    }

    public static StepsToDistanceAlgorithmAdapter newStepsToDistanceAlgorithmAdapter(Lazy<StepToDistanceAlgorithm> lazy, FeatureService featureService, ILog iLog) {
        return new StepsToDistanceAlgorithmAdapter(lazy, featureService, iLog);
    }

    public static StepsToDistanceAlgorithmAdapter provideInstance(Provider<StepToDistanceAlgorithm> provider, Provider<FeatureService> provider2, Provider<ILog> provider3) {
        return new StepsToDistanceAlgorithmAdapter(DoubleCheck.lazy(provider), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public StepsToDistanceAlgorithmAdapter mo10268get() {
        return provideInstance(this.stepsToDistanceAlgorithmProvider, this.featureServiceProvider, this.logProvider);
    }
}
